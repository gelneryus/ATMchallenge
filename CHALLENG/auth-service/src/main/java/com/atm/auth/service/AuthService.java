package com.atm.auth.service;

import com.atm.auth.model.TarjetaEntity;
import com.atm.auth.repository.TarjetaRepository;
import com.atm.commons.dto.LoginRequestDTO;
import com.atm.commons.dto.LoginResponseDTO;
import com.atm.commons.dto.TransaccionDTO;
import com.atm.auth.client.TransactionRestClientAuth;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private TransactionRestClientAuth transactionRestClientAuth;

    @Retry(name = "authService", fallbackMethod = "fallbackLogin")
    @CircuitBreaker(name = "authService")
    public LoginResponseDTO login(LoginRequestDTO request) {
        log.info("🔐 Intentando login para tarjeta {}", request.getNumeroTarjeta());

        LoginResponseDTO response = new LoginResponseDTO();
        try {
            TarjetaEntity tarjeta = tarjetaRepository.findByNumero(request.getNumeroTarjeta());
            if (tarjeta != null && tarjeta.isActiva()) {
                response.setLoginExitoso(true);
                response.setMensaje("Ingreso exitoso");
                log.info("✅ Login exitoso para tarjeta {}", tarjeta.getNumero());
            } else {
                response.setLoginExitoso(false);
                response.setMensaje("Tarjeta no válida o inactiva");
                log.warn("⚠️ Login fallido: tarjeta no válida o inactiva - {}", request.getNumeroTarjeta());
            }

            // Auditoría de login
            registrarAuditoriaLogin(request.getNumeroTarjeta(), response.isLoginExitoso(), response.getMensaje());

            return response;
        } catch (Exception ex) {
            log.error("❌ Error en login para tarjeta {}: {}", request.getNumeroTarjeta(), ex.getMessage(), ex);
            throw ex;
        }
    }

    public LoginResponseDTO fallbackLogin(LoginRequestDTO request, Throwable ex) {
        log.warn("🛑 Fallback activado en login - tarjeta: {}, error: {}", request.getNumeroTarjeta(), ex.getMessage());

        LoginResponseDTO fallbackResponse = new LoginResponseDTO();
        fallbackResponse.setLoginExitoso(false);
        fallbackResponse.setMensaje("Servicio de login temporalmente no disponible");

        registrarAuditoriaLogin(request.getNumeroTarjeta(), false, "Fallback activado: " + ex.getMessage());

        return fallbackResponse;
    }

    private void registrarAuditoriaLogin(String numeroTarjeta, boolean exitosa, String mensaje) {
        TransaccionDTO dto = new TransaccionDTO();
        dto.setTipoOperacion("LOGIN");
        dto.setNumeroTarjeta(numeroTarjeta);
        dto.setNumeroCuenta(null); // no aplica
        dto.setImporte(null);      // no aplica
        dto.setExitosa(exitosa);
        dto.setMensaje(mensaje);
        dto.setFechaHora(LocalDateTime.now());

        transactionRestClientAuth.enviarTransaccion(dto);
    }
}

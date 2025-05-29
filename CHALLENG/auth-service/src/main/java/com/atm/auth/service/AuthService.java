package com.atm.auth.service;

import com.atm.auth.model.TarjetaEntity;
import com.atm.auth.repository.TarjetaRepository;
import com.atm.commons.dto.LoginRequestDTO;
import com.atm.commons.dto.LoginResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Retry(name = "fallbackLogin", fallbackMethod = "fallbackLogin")
@CircuitBreaker(name = "fallbackLogin")
public LoginResponseDTO login(LoginRequestDTO request) {
        LoginResponseDTO response = new LoginResponseDTO();
        TarjetaEntity tarjeta = tarjetaRepository.findByNumero(request.getNumeroTarjeta());
        if (tarjeta != null && tarjeta.isActiva()) {
            response.setLoginExitoso(true);
            response.setMensaje("Ingreso exitoso");
        } else {
            response.setLoginExitoso(false);
            response.setMensaje("Ingreso no exitoso");
        }
        return response;
    }
}

package com.atm.account.service;

import com.atm.account.client.TransactionRestClientAccount;
import com.atm.account.exception.CuentaNotFoundException;
import com.atm.account.model.CuentaEntity;
import com.atm.account.repository.CuentaRepository;
import com.atm.commons.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private TransactionRestClientAccount transactionRestClientAccount;

    @Autowired
    private CuentaRepository cuentaRepository;

    public SaldoResponseDTO extraer(ExtraccionRequestDTO request) {
        log.info("🟡 Solicitud de extracción recibida. Cuenta: {}, Monto: {}", request.getNumeroCuenta(), request.getImporte());
        try {
            CuentaEntity cuenta = cuentaRepository.findById(request.getNumeroCuenta())
                    .orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada: " + request.getNumeroCuenta()));

            if (cuenta.getSaldo().compareTo(request.getImporte()) < 0) {
                log.warn("❌ Fondos insuficientes. Saldo actual: {}, Monto solicitado: {}",
                        cuenta.getSaldo(), request.getImporte());
                throw new IllegalArgumentException("Fondos insuficientes.");
            }

            cuenta.setSaldo(cuenta.getSaldo().subtract(request.getImporte()));
            cuentaRepository.save(cuenta);

            SaldoResponseDTO response = new SaldoResponseDTO();
            response.setSaldo(cuenta.getSaldo()); // Asegurate de tener este setter
            log.info("✅ Extracción completada. Nuevo saldo: {}", cuenta.getSaldo());

            registrarAuditoria("EXTRACCION", cuenta.getNumeroCuenta(), request.getNumeroTarjeta(), request.getImporte(), true, "Extracción exitosa");

            return response;
        } catch (Exception ex) {
            log.error("❌ Error al realizar extracción en cuenta {}: {}", request.getNumeroCuenta(), ex.getMessage(), ex);
            registrarAuditoria("EXTRACCION", request.getNumeroCuenta(), request.getNumeroTarjeta(), request.getImporte(), false, ex.getMessage());
            throw ex;
        }
    }

    public SaldoResponseDTO depositar(DepositoRequestDTO request) {
        log.info("🟡 Solicitud de depósito recibida. Cuenta: {}, Monto: {}", request.getCbuDestino(), request.getImporte());
        try {
            // TODO: lógica real de depósito
            SaldoResponseDTO response = new SaldoResponseDTO();
            log.info("✅ Depósito exitoso en cuenta {}", request.getNumeroTarjeta());

            registrarAuditoria("DEPOSITO", request.getCbuDestino(), request.getNumeroTarjeta(), request.getImporte(), true, "Depósito exitoso");

            return response;
        } catch (Exception ex) {
            log.error("❌ Error al realizar depósito en cuenta {}: {}", request.getNumeroTarjeta(), ex.getMessage(), ex);
            registrarAuditoria("DEPOSITO", request.getCbuDestino(), request.getNumeroTarjeta(), request.getImporte(), false, ex.getMessage());
            throw ex;
        }
    }

    public SaldoResponseDTO consultarSaldo(SaldoRequestDTO request) {
        log.info("🟡 Consulta de saldo iniciada para cuenta {}", request.getNumeroCuenta());
        try {
            // TODO: lógica real de saldo
            SaldoResponseDTO response = new SaldoResponseDTO();
            log.info("✅ Consulta de saldo exitosa para cuenta {}", request.getNumeroCuenta());

            registrarAuditoria("SALDO", request.getNumeroCuenta(), request.getNumeroTarjeta(), BigDecimal.ZERO, true, "Consulta de saldo exitosa");

            return response;
        } catch (Exception ex) {
            log.error("❌ Error al consultar saldo en cuenta {}: {}", request.getNumeroCuenta(), ex.getMessage(), ex);
            registrarAuditoria("SALDO", request.getNumeroCuenta(), request.getNumeroTarjeta(), BigDecimal.ZERO, false, ex.getMessage());
            throw ex;
        }
    }

    public CuentaDTO fallbackCuenta(Long cuentaId, Exception ex) {
        log.warn("⚠️ Fallback activado para obtenerCuentaPorId - Cuenta: {}, Error: {}", cuentaId, ex.toString());
        return new CuentaDTO();
    }

    public void fallbackExtraccion(Long cuentaId, BigDecimal monto, Exception ex) {
        log.warn("⚠️ Fallback activado para extraerDinero - Cuenta: {}, Monto: {}, Error: {}", cuentaId, monto, ex.toString());
    }

    private void registrarAuditoria(String tipo, String cuenta, String tarjeta, BigDecimal monto, boolean exitosa, String mensaje) {
        TransaccionDTO dto = new TransaccionDTO();
        dto.setTipoOperacion(tipo);
        dto.setNumeroCuenta(cuenta);
        dto.setNumeroTarjeta(tarjeta);
        dto.setImporte(monto);
        dto.setExitosa(exitosa);
        dto.setMensaje(mensaje);
        dto.setFechaHora(LocalDateTime.now());
        transactionRestClientAccount.enviarTransaccion(dto);
    }
}

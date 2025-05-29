package com.atm.account.service;

import com.atm.commons.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    public SaldoResponseDTO extraer(ExtraccionRequestDTO request) {
        // Lógica de extracción aquí
        return new SaldoResponseDTO();
    }

    public SaldoResponseDTO depositar(DepositoRequestDTO request) {
        // Lógica de depósito aquí
        return new SaldoResponseDTO();
    }

    public SaldoResponseDTO consultarSaldo(SaldoRequestDTO request) {
        // Lógica de saldo aquí
        return new SaldoResponseDTO();
    }

    // 👇 Fallbacks DENTRO de la clase
    public CuentaDTO fallbackCuenta(Long cuentaId, Exception ex) {
        log.warn("⚠️ Fallback activado para obtenerCuentaPorId - Cuenta: {}, Error: {}", cuentaId, ex.toString());
        return new CuentaDTO(); // o null si no querés devolver nada
    }

    public void fallbackExtraccion(Long cuentaId, BigDecimal monto, Exception ex) {
        log.warn("⚠️ Fallback activado para extraerDinero - Cuenta: {}, Monto: {}, Error: {}", cuentaId, monto, ex.toString());
    }
}

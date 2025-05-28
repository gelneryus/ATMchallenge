package com.atm.account.service;

import com.atm.commons.dto.*;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
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
}

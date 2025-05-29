package com.atm.account.service;

import com.atm.commons.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(); // sin mocks si no hay repo aún
    }

    @Test
    void testExtraccionBasica() {
        ExtraccionRequestDTO request = new ExtraccionRequestDTO();
        request.setNumeroCuenta("0001234567");
        request.setNumeroTarjeta("1234567890123456");
        request.setImporte(new BigDecimal("500"));

        SaldoResponseDTO response = accountService.extraer(request);

        assertNotNull(response);
        // assertEquals(new BigDecimal("500"), response.getNuevoSaldo()); ← si implementás lógica real
    }

    @Test
    void testDepositoBasico() {
        DepositoRequestDTO request = new DepositoRequestDTO();
        request.setCbuDestino("0001234567");
        request.setNumeroTarjeta("1234567890123456");
        request.setImporte(new BigDecimal("300"));

        SaldoResponseDTO response = accountService.depositar(request);

        assertNotNull(response);
    }

    @Test
    void testConsultaSaldoBasica() {
        SaldoRequestDTO request = new SaldoRequestDTO();
        request.setNumeroCuenta("0001234567");
        request.setNumeroTarjeta("1234567890123456");

        SaldoResponseDTO response = accountService.consultarSaldo(request);

        assertNotNull(response);
    }
}

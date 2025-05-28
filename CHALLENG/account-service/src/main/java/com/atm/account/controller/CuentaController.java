package com.atm.account.controller;

import com.atm.account.service.AccountService;
import com.atm.commons.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/extraer")
    public SaldoResponseDTO extraer(@RequestBody ExtraccionRequestDTO request) {
        return accountService.extraer(request);
    }

    @PostMapping("/depositar")
    public SaldoResponseDTO depositar(@RequestBody DepositoRequestDTO request) {
        return accountService.depositar(request);
    }

    @PostMapping("/saldo")
    public SaldoResponseDTO consultarSaldo(@RequestBody SaldoRequestDTO request) {
        return accountService.consultarSaldo(request);
    }
}

package com.atm.console.command;

import com.atm.console.client.AccountClient;

public class DepositarCommand {
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Uso: depositar <tarjeta> <cbu> <importe>");
            return;
        }
        boolean exito = new AccountClient().depositar(args[1], args[2], args[3]);
        System.out.println(exito ? "Depósito exitoso" : "Error en el depósito");
    }
}

package com.atm.console.command;

import com.atm.console.client.AccountClient;

public class SaldoCommand {
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: saldo <tarjeta> <cuenta>");
            return;
        }
        String saldo = new AccountClient().consultarSaldo(args[1], args[2]);
        System.out.println(saldo != null ? "Su saldo es $" + saldo : "Error en la consulta de saldo");
    }
}

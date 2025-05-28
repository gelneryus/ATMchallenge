package com.atm.console.command;

import com.atm.console.client.AccountClient;

public class ExtraerCommand {
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Uso: extraer <tarjeta> <cuenta> <importe>");
            return;
        }
        boolean exito = new AccountClient().extraer(args[1], args[2], args[3]);
        System.out.println(exito ? "Retire su dinero" : "Error en la extracción");
    }
}

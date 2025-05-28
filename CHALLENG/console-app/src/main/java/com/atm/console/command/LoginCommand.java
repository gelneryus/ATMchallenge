package com.atm.console.command;

import com.atm.console.client.AuthClient;

public class LoginCommand {
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: login <tarjeta>");
            return;
        }
        boolean resultado = new AuthClient().login(args[1]);
        System.out.println(resultado ? "Ingreso exitoso" : "Ingreso no exitoso");
    }
}

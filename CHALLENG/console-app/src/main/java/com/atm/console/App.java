package com.atm.console;

import com.atm.console.command.*;
public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: login | extraer | depositar | saldo");
            return;
        }
        switch (args[0].toLowerCase()) {
            case "login":
                new LoginCommand().execute(args);
                break;
            case "extraer":
                new ExtraerCommand().execute(args);
                break;
            case "depositar":
                new DepositarCommand().execute(args);
                break;
            case "saldo":
                new SaldoCommand().execute(args);
                break;
            default:
                System.out.println("Comando no reconocido.");
        }
    }
}

package com.atm.console;

import com.atm.console.client.AtmRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AtmRestClient atmRestClient;

    public ConsoleRunner(AtmRestClient atmRestClient) {
        this.atmRestClient = atmRestClient;
    }

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            System.out.println("Comando no especificado");
            return;
        }

        try {
            switch (args[0].toLowerCase()) {
                case "login":
                    System.out.println(atmRestClient.login(args[1]));
                    break;
                case "extraer":
                    System.out.println(atmRestClient.extraer(args[1], args[2], new BigDecimal(args[3])));
                    break;
                case "depositar":
                    System.out.println(atmRestClient.depositar(args[1], args[2], new BigDecimal(args[3])));
                    break;
                case "saldo":
                    System.out.println(atmRestClient.consultarSaldo(args[1], args[2]));
                    break;
                default:
                    System.out.println("Comando no reconocido");
            }
        } catch (Exception e) {
            System.out.println("Error ejecutando comando: " + e.getMessage());
        }
    }
}
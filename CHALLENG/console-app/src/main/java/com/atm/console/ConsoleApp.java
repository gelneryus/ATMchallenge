package com.atm.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.atm.console.CliApp;

@SpringBootApplication
public class ConsoleApp {
    public static void main(String[] args) {
        if (args.length > 0) {
            CliApp.main(args); // Ejecuta modo CLI por flags
        } else {
            SpringApplication.run(ConsoleApp.class, args); // Modo interactivo clásico
        }
    }
}

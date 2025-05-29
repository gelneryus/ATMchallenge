package com.atm.console;

import com.atm.console.client.AtmRestClient;
import picocli.CommandLine;
import picocli.CommandLine.*;

import java.math.BigDecimal;

@Command(name = "atm", mixinStandardHelpOptions = true, version = "1.0",
        description = "CLI para operaciones del cajero ATM",
        subcommands = {
                CliApp.Login.class,
                CliApp.Saldo.class,
                CliApp.Depositar.class,
                CliApp.Extraer.class
        })
public class CliApp implements Runnable {

    @Override
    public void run() {
        System.out.println("ATM CLI - Usa '--help' para ver comandos disponibles.");
    }

    @Command(name = "login", description = "Iniciar sesión con DNI")
    static class Login implements Runnable {

        @Option(names = "--dni", required = true, description = "DNI del titular")
        String dni;

        @Override
        public void run() {
            AtmRestClient client = new AtmRestClient();
            try {
                String response = client.login(dni);
                System.out.println("✔ Login exitoso: " + response);
            } catch (Exception e) {
                System.err.println("❌ Error en login: " + e.getMessage());
            }
        }
    }

    @Command(name = "saldo", description = "Consultar saldo de cuenta")
    static class Saldo implements Runnable {

        @Option(names = "--dni", required = true)
        String dni;

        @Option(names = "--cuenta", required = true)
        String cuenta;

        @Override
        public void run() {
            AtmRestClient client = new AtmRestClient();
            try {
                String response = client.consultarSaldo(dni, cuenta);
                System.out.println("💰 Saldo: " + response);
            } catch (Exception e) {
                System.err.println("❌ Error al consultar saldo: " + e.getMessage());
            }
        }
    }

    @Command(name = "depositar", description = "Depositar dinero en una cuenta")
    static class Depositar implements Runnable {

        @Option(names = "--dni", required = true)
        String dni;

        @Option(names = "--cuenta", required = true)
        String cuenta;

        @Option(names = "--monto", required = true)
        BigDecimal monto;

        @Override
        public void run() {
            AtmRestClient client = new AtmRestClient();
            try {
                String response = client.depositar(dni, cuenta, monto);
                System.out.println("✅ Depósito exitoso: " + response);
            } catch (Exception e) {
                System.err.println("❌ Error al depositar: " + e.getMessage());
            }
        }
    }

    @Command(name = "extraer", description = "Extraer dinero de una cuenta")
    static class Extraer implements Runnable {

        @Option(names = "--dni", required = true)
        String dni;

        @Option(names = "--cuenta", required = true)
        String cuenta;

        @Option(names = "--monto", required = true)
        BigDecimal monto;

        @Override
        public void run() {
            AtmRestClient client = new AtmRestClient();
            try {
                String response = client.extraer(dni, cuenta, monto);
                System.out.println("✅ Extracción exitosa: " + response);
            } catch (Exception e) {
                System.err.println("❌ Error al extraer: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new CommandLine(new CliApp()).execute(args);
    }
}

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

    @Command(name = "login", description = "Iniciar sesión con número de tarjeta")
    static class Login implements Runnable {

        @Option(names = "--tarjeta", required = true, description = "Número de tarjeta del titular")
        String tarjeta;

        @Override
        public void run() {
            AtmRestClient client = new AtmRestClient();
            try {
                String response = client.login(tarjeta);
                System.out.println("✔ Ingreso exitoso: " + response);
            } catch (Exception e) {
                System.err.println("❌ Ingreso no exitoso: " + e.getMessage());
            }
        }
    }

    @Command(name = "saldo", description = "Consultar saldo de cuenta")
    static class Saldo implements Runnable {

        @Option(names = "--tarjeta", required = true, description = "Número de tarjeta")
        String tarjeta;

        @Option(names = "--cuenta", required = true, description = "Número de cuenta")
        String cuenta;

        @Override
        public void run() {
            AtmRestClient client = new AtmRestClient();
            try {
                String response = client.consultarSaldo(tarjeta, cuenta);
                System.out.println("💰 Su saldo es: " + response);
            } catch (Exception e) {
                System.err.println("❌ Error al consultar saldo: " + e.getMessage());
            }
        }
    }

    @Command(name = "depositar", description = "Depositar dinero en una cuenta")
    static class Depositar implements Runnable {

        @Option(names = "--tarjeta", required = true, description = "Número de tarjeta")
        String tarjeta;

        @Option(names = "--cuenta", required = true, description = "Cuenta destino (CBU)")
        String cuenta;

        @Option(names = "--monto", required = true, description = "Monto a depositar")
        BigDecimal monto;

        @Override
        public void run() {
            AtmRestClient client = new AtmRestClient();
            try {
                String response = client.depositar(tarjeta, cuenta, monto);
                System.out.println("✅ Depósito exitoso: " + response);
            } catch (Exception e) {
                System.err.println("❌ Error al depositar: " + e.getMessage());
            }
        }
    }

    @Command(name = "extraer", description = "Extraer dinero de una cuenta")
    static class Extraer implements Runnable {

        @Option(names = "--tarjeta", required = true, description = "Número de tarjeta")
        String tarjeta;

        @Option(names = "--cuenta", required = true, description = "Cuenta origen")
        String cuenta;

        @Option(names = "--monto", required = true, description = "Monto a extraer")
        BigDecimal monto;

        @Override
        public void run() {
            AtmRestClient client = new AtmRestClient();
            try {
                String response = client.extraer(tarjeta, cuenta, monto);
                System.out.println("✅ Retire su dinero: " + response);
            } catch (Exception e) {
                System.err.println("❌ Error en la extracción: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new CommandLine(new CliApp()).execute(args);
    }
}

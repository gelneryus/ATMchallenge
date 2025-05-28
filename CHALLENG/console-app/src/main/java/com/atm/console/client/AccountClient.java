package com.atm.console.client;

public class AccountClient {
    public boolean extraer(String tarjeta, String cuenta, String importe) {
        // Simula llamada REST a account-service
        return true; // demo
    }
    public boolean depositar(String tarjeta, String cbu, String importe) {
        // Simula llamada REST a account-service
        return true; // demo
    }
    public String consultarSaldo(String tarjeta, String cuenta) {
        // Simula llamada REST a account-service
        return "99999.99"; // demo
    }
}

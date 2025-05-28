package com.atm.console.client;

public class AuthClient {
    public boolean login(String tarjeta) {
        // Simula llamada REST a auth-service
        return "12345".equals(tarjeta); // demo
    }
}

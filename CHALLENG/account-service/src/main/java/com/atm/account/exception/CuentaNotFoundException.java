package com.atm.account.exception;

public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(String msg) {
        super(msg);
    }
}

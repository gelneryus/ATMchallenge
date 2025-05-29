package com.atm.commons.dto;
import lombok.Data;

public class SaldoRequestDTO {
    private String numeroTarjeta;
    private String numeroCuenta;

    public SaldoRequestDTO() {
    }

    public SaldoRequestDTO(String numeroCuenta, String numeroTarjeta) {
        this.numeroCuenta = numeroCuenta;
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public SaldoRequestDTO setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        return this;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public SaldoRequestDTO setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }
}
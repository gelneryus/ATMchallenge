package com.atm.commons.dto;
import lombok.Data;
import java.math.BigDecimal;

public class ExtraccionRequestDTO {
    private String numeroTarjeta;
    private String numeroCuenta;
    private BigDecimal importe;

    public ExtraccionRequestDTO() {
    }

    public ExtraccionRequestDTO(String numeroTarjeta, String numeroCuenta, BigDecimal importe) {
        this.numeroTarjeta = numeroTarjeta;
        this.numeroCuenta = numeroCuenta;
        this.importe = importe;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public ExtraccionRequestDTO setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        return this;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public ExtraccionRequestDTO setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public ExtraccionRequestDTO setImporte(BigDecimal importe) {
        this.importe = importe;
        return this;
    }
}
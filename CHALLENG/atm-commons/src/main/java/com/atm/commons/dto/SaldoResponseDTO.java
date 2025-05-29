package com.atm.commons.dto;
import lombok.Data;
import java.math.BigDecimal;

public class SaldoResponseDTO {
    private BigDecimal saldo;
    private String mensaje;

    public SaldoResponseDTO() {
    }

    public SaldoResponseDTO(BigDecimal saldo, String mensaje) {
        this.saldo = saldo;
        this.mensaje = mensaje;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public SaldoResponseDTO setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
        return this;
    }

    public String getMensaje() {
        return mensaje;
    }

    public SaldoResponseDTO setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }
}
package com.atm.commons.dto;

import java.math.BigDecimal;

public class CuentaDTO {

    private Long id;
    private String tipo; // Caja de ahorro, corriente, etc.
    private BigDecimal saldo;

    public CuentaDTO() {}

    public CuentaDTO(Long id, String tipo, BigDecimal saldo) {
        this.id = id;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}

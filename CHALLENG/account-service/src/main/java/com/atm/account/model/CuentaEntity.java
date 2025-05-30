package com.atm.account.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;



@Entity
public class CuentaEntity {
    @Id
    private String numeroCuenta;
    private String cbu;
    private BigDecimal saldo;
    private String titular;


    public CuentaEntity setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getCbu() {
        return cbu;
    }

    public String getTitular() {
        return titular;
    }

    public CuentaEntity setTitular(String titular) {
        this.titular = titular;
        return this;
    }

    public CuentaEntity setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
        return this;
    }

    public CuentaEntity setCbu(String cbu) {
        this.cbu = cbu;
        return this;
    }



}

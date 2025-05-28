package com.atm.commons.model;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class Cuenta {
    private String numeroCuenta;
    private String cbu;
    private BigDecimal saldo;
    private String titular;
}
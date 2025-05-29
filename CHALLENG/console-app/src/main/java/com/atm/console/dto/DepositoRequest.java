package com.atm.console.dto;

import java.math.BigDecimal;

public class DepositoRequest {
    public String tarjeta;
    public String cbu;
    public BigDecimal importe;

    public DepositoRequest(String tarjeta, String cbu, BigDecimal importe) {
        this.tarjeta = tarjeta;
        this.cbu = cbu;
        this.importe = importe;
    }
}
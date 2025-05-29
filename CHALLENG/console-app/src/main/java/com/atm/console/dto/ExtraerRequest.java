package com.atm.console.dto;

import java.math.BigDecimal;

public class ExtraerRequest {
    public String tarjeta;
    public String cuenta;
    public BigDecimal importe;

    public ExtraerRequest(String tarjeta, String cuenta, BigDecimal importe) {
        this.tarjeta = tarjeta;
        this.cuenta = cuenta;
        this.importe = importe;
    }
}
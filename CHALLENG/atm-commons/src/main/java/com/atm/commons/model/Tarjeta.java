package com.atm.commons.model;
import lombok.Data;
@Data
public class Tarjeta {
    private String numero;
    private boolean activa;
    private String titular;
}
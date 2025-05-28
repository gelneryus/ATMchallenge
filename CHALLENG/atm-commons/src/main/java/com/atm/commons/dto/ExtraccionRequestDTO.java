package com.atm.commons.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class ExtraccionRequestDTO {
    private String numeroTarjeta;
    private String numeroCuenta;
    private BigDecimal importe;
}
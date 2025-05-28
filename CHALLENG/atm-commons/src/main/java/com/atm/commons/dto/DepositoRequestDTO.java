package com.atm.commons.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class DepositoRequestDTO {
    private String numeroTarjeta;
    private String cbuDestino;
    private BigDecimal importe;
}
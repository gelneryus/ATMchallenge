package com.atm.commons.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class SaldoResponseDTO {
    private BigDecimal saldo;
    private String mensaje;
}
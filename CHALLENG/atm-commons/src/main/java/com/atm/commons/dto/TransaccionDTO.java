package com.atm.commons.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class TransaccionDTO {
    private String tipoOperacion; // EXTRACCION, DEPOSITO, SALDO, LOGIN
    private String numeroTarjeta;
    private String numeroCuenta;
    private BigDecimal importe;
    private LocalDateTime fechaHora;
    private boolean exitosa;
    private String mensaje;
}
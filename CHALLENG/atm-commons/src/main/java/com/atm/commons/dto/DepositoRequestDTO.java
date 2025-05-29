package com.atm.commons.dto;
import lombok.Data;
import java.math.BigDecimal;

public class DepositoRequestDTO {
    private String numeroTarjeta;
    private String cbuDestino;
    private BigDecimal importe;


    public DepositoRequestDTO() {
    }

    public DepositoRequestDTO(String numeroTarjeta, String cbuDestino, BigDecimal importe) {
        this.numeroTarjeta = numeroTarjeta;
        this.cbuDestino = cbuDestino;
        this.importe = importe;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public DepositoRequestDTO setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        return this;
    }

    public String getCbuDestino() {
        return cbuDestino;
    }

    public DepositoRequestDTO setCbuDestino(String cbuDestino) {
        this.cbuDestino = cbuDestino;
        return this;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public DepositoRequestDTO setImporte(BigDecimal importe) {
        this.importe = importe;
        return this;
    }
}
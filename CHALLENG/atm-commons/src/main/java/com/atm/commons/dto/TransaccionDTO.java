package com.atm.commons.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionDTO {
    private String tipoOperacion; // EXTRACCION, DEPOSITO, SALDO, LOGIN
    private String numeroTarjeta;
    private String numeroCuenta;
    private BigDecimal importe;
    private LocalDateTime fechaHora;
    private boolean exitosa;
    private String mensaje;

    public TransaccionDTO() {
    }

    public TransaccionDTO(String tipoOperacion, String numeroTarjeta, String numeroCuenta, BigDecimal importe, LocalDateTime fechaHora, boolean exitosa, String mensaje) {
        this.tipoOperacion = tipoOperacion;
        this.numeroTarjeta = numeroTarjeta;
        this.numeroCuenta = numeroCuenta;
        this.importe = importe;
        this.fechaHora = fechaHora;
        this.exitosa = exitosa;
        this.mensaje = mensaje;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public TransaccionDTO setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
        return this;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public TransaccionDTO setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        return this;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public TransaccionDTO setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public TransaccionDTO setImporte(BigDecimal importe) {
        this.importe = importe;
        return this;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public TransaccionDTO setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
        return this;
    }

    public String getMensaje() {
        return mensaje;
    }

    public TransaccionDTO setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public boolean isExitosa() {
        return exitosa;
    }

    public TransaccionDTO setExitosa(boolean exitosa) {
        this.exitosa = exitosa;
        return this;
    }
}
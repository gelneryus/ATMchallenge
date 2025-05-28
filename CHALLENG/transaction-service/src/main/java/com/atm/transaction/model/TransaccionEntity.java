package com.atm.transaction.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class TransaccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoOperacion; // EXTRACCION, DEPOSITO, SALDO, LOGIN
    private String numeroTarjeta;
    private String numeroCuenta;
    private BigDecimal importe;
    private LocalDateTime fechaHora;
    private boolean exitosa;
    private String mensaje;

    public TransaccionEntity() {
    }

    public TransaccionEntity(String tipoOperacion, String numeroTarjeta, String numeroCuenta, BigDecimal importe, LocalDateTime fechaHora, boolean exitosa, String mensaje, Long id) {
        this.tipoOperacion = tipoOperacion;
        this.numeroTarjeta = numeroTarjeta;
        this.numeroCuenta = numeroCuenta;
        this.importe = importe;
        this.fechaHora = fechaHora;
        this.exitosa = exitosa;
        this.mensaje = mensaje;
        this.id = id;
    }

    public boolean isExitosa() {
        return exitosa;
    }

    public TransaccionEntity setExitosa(boolean exitosa) {
        this.exitosa = exitosa;
        return this;
    }

    public String getMensaje() {
        return mensaje;
    }

    public TransaccionEntity setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public TransaccionEntity setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
        return this;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public TransaccionEntity setImporte(BigDecimal importe) {
        this.importe = importe;
        return this;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public TransaccionEntity setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public TransaccionEntity setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
        return this;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public TransaccionEntity setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        return this;
    }
}

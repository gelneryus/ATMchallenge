package com.atm.auth.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class TarjetaEntity {

    @Id
    private String numero;
    private boolean activa;
    private String titular;

    public boolean isActiva() {
        return activa;
    }

    public TarjetaEntity setActiva(boolean activa) {
        this.activa = activa;
        return this;
    }

    public String getNumero() {
        return numero;
    }

    public TarjetaEntity setNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public String getTitular() {
        return titular;
    }

    public TarjetaEntity setTitular(String titular) {
        this.titular = titular;
        return this;
    }



}

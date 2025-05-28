package com.atm.commons.dto;
import lombok.*;


public class LoginRequestDTO {

    private String numeroTarjeta;




    public LoginRequestDTO(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public LoginRequestDTO() {
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public LoginRequestDTO setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        return this;
    }




}

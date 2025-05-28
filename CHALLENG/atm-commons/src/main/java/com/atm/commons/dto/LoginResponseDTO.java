package com.atm.commons.dto;
import lombok.*;


public class LoginResponseDTO {
    private boolean loginExitoso;
    private String mensaje;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(boolean loginExitoso, String mensaje) {
        this.loginExitoso = loginExitoso;
        this.mensaje = mensaje;
    }
    public String getMensaje() {
        return mensaje;
    }

    public LoginResponseDTO setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public boolean isLoginExitoso() {
        return loginExitoso;
    }

    public LoginResponseDTO setLoginExitoso(boolean loginExitoso) {
        this.loginExitoso = loginExitoso;
        return this;
    }

}
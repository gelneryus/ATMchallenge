package com.atm.commons.dto;

import lombok.Data;


public class ErrorResponseDTO {

    private String mensaje;
    private String detalle;

    public ErrorResponseDTO(String mensaje, String detalle) {
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public ErrorResponseDTO() {
    }




    public String getMensaje() {
        return mensaje;
    }

    public ErrorResponseDTO setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public String getDetalle() {
        return detalle;
    }

    public ErrorResponseDTO setDetalle(String detalle) {
        this.detalle = detalle;
        return this;
    }










}

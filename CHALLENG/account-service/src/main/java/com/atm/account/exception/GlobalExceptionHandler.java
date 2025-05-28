package com.atm.account.exception;

import com.atm.commons.dto.ErrorResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CuentaNotFoundException.class)
    public ErrorResponseDTO handleCuentaNotFound(CuentaNotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setMensaje("Cuenta no encontrada");
        error.setDetalle(ex.getMessage());
        return error;
    }
}

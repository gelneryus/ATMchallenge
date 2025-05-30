package com.atm.transaction.controller;

import com.atm.transaction.model.TransaccionEntity;
import com.atm.transaction.service.TransaccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    private static final Logger log = LoggerFactory.getLogger(TransaccionController.class);

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/registrar")
    public TransaccionEntity registrar(@RequestBody TransaccionEntity entity) {
        log.info("📩 Recibiendo solicitud para registrar transacción tipo={} cuenta={}", entity.getTipoOperacion(), entity.getNumeroCuenta());
        return transaccionService.registrarTransaccion(entity);
    }

    @GetMapping
    public List<TransaccionEntity> todas() {
        log.info("📤 Obteniendo todas las transacciones registradas");
        return transaccionService.listarTodas();
    }
}

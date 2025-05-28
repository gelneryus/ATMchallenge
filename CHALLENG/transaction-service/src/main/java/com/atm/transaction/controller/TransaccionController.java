package com.atm.transaction.controller;

import com.atm.transaction.model.TransaccionEntity;
import com.atm.transaction.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/registrar")
    public TransaccionEntity registrar(@RequestBody TransaccionEntity entity) {
        return transaccionService.registrarTransaccion(entity);
    }

    @GetMapping
    public List<TransaccionEntity> todas() {
        return transaccionService.listarTodas();
    }
}

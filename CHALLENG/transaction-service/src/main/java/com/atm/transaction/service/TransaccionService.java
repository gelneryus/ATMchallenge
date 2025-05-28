package com.atm.transaction.service;

import com.atm.transaction.model.TransaccionEntity;
import com.atm.transaction.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository repository;

    public TransaccionEntity registrarTransaccion(TransaccionEntity entity) {
        return repository.save(entity);
    }

    public List<TransaccionEntity> listarTodas() {
        return repository.findAll();
    }
}

package com.atm.transaction.service;

import com.atm.transaction.model.TransaccionEntity;
import com.atm.transaction.repository.TransaccionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository repository;

    @Retry(name = "fallbackRegistrar", fallbackMethod = "fallbackRegistrar")
@CircuitBreaker(name = "fallbackRegistrar")
public TransaccionEntity registrarTransaccion(TransaccionEntity entity) {
        return repository.save(entity);
    }

    public List<TransaccionEntity> listarTodas() {
        return repository.findAll();
    }
}

package com.atm.transaction.service;

import com.atm.transaction.model.TransaccionEntity;
import com.atm.transaction.repository.TransaccionRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    private static final Logger log = LoggerFactory.getLogger(TransaccionService.class);

    @Autowired
    private TransaccionRepository repository;

    @Retry(name = "transactionService", fallbackMethod = "fallbackRegistrar")
    @CircuitBreaker(name = "transactionService")
    public TransaccionEntity registrarTransaccion(TransaccionEntity entity) {
        try {
            TransaccionEntity guardada = repository.save(entity);
            log.info("✅ Transacción registrada: tipo={}, cuenta={}, tarjeta={}, importe={}, éxito={}",
                    guardada.getTipoOperacion(), guardada.getNumeroCuenta(),
                    guardada.getNumeroTarjeta(), guardada.getImporte(), guardada.isExitosa());
            return guardada;
        } catch (Exception ex) {
            log.error("❌ Error al guardar transacción: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    public List<TransaccionEntity> listarTodas() {
        log.info("📋 Solicitando listado de todas las transacciones");
        return repository.findAll();
    }

    public TransaccionEntity fallbackRegistrar(TransaccionEntity entity, Throwable ex) {
        log.warn("⚠️ Fallback activado: No se pudo guardar transacción para cuenta {}. Error: {}",
                entity.getNumeroCuenta(), ex.getMessage());
        return null; // o se podría encolar para retry posterior
    }
}

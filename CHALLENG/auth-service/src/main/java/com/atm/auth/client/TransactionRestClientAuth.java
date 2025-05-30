package com.atm.auth.client;

import com.atm.commons.dto.TransaccionDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TransactionRestClientAuth {

    private static final Logger log = LoggerFactory.getLogger(TransactionRestClientAuth.class);

    private final RestTemplate restTemplate;

    @Value("${atm.services.transaction-url}")
    private String transactionUrl;

    public TransactionRestClientAuth(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retry(name = "atmRetry")
    @CircuitBreaker(name = "atmCircuitBreaker", fallbackMethod = "fallbackTransaccion")
    public void enviarTransaccion(TransaccionDTO dto) {
        log.info("📨 Enviando transacción a auditoría: {}", dto.getTipoOperacion());
        restTemplate.postForEntity(transactionUrl, dto, Void.class);
    }

    public void fallbackTransaccion(TransaccionDTO dto, Throwable t) {
        log.error("❌ No se pudo auditar la transacción. Tipo={}, Motivo={}", dto.getTipoOperacion(), t.getMessage(), t);
    }
}
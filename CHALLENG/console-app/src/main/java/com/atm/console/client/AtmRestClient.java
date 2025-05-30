package com.atm.console.client;

import com.atm.console.dto.DepositoRequest;
import com.atm.console.dto.ExtraerRequest;
import com.atm.commons.dto.LoginRequestDTO;
import com.atm.commons.dto.SaldoRequestDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class AtmRestClient {

    private static final Logger log = LoggerFactory.getLogger(AtmRestClient.class);
    private final RestTemplate restTemplate;

    @Value("${atm.services.base-url}")
    private String gatewayUrl;

    public AtmRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private void validarGateway() {
        if (gatewayUrl == null || gatewayUrl.isBlank()) {
            throw new IllegalStateException("❌ El valor de 'atm.services.base-url' no está configurado correctamente.");
        }
    }

    private String post(String path, Object request) {
        validarGateway();
        return restTemplate.postForObject(gatewayUrl + path, request, String.class);
    }

    @Retry(name = "atmRetry")
    @CircuitBreaker(name = "atmCircuitBreaker", fallbackMethod = "fallbackLogin")
    public String login(String tarjeta) {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setNumeroTarjeta(tarjeta);
        return post("/auth/login", dto);
    }

    public String fallbackLogin(String tarjeta, Throwable t) {
        log.error("🛑 Fallback login falló: {}", t.getMessage(), t);
        return "🔌 Servicio de autenticación no disponible. Intente más tarde.";
    }

    @Retry(name = "atmRetry")
    @CircuitBreaker(name = "atmCircuitBreaker", fallbackMethod = "fallbackExtraer")
    public String extraer(String tarjeta, String cuenta, BigDecimal importe) {
        ExtraerRequest req = new ExtraerRequest(tarjeta, cuenta, importe);
        return post("/cuenta/extraer", req);
    }

    public String fallbackExtraer(String tarjeta, String cuenta, BigDecimal importe, Throwable t) {
        log.error("🛑 Fallback extracción falló: {}", t.getMessage(), t);
        return "🚫 No se pudo realizar la extracción. Intente más tarde.";
    }

    @Retry(name = "atmRetry")
    @CircuitBreaker(name = "atmCircuitBreaker", fallbackMethod = "fallbackDepositar")
    public String depositar(String tarjeta, String cbu, BigDecimal importe) {
        DepositoRequest req = new DepositoRequest(tarjeta, cbu, importe);
        return post("/cuenta/depositar", req);
    }

    public String fallbackDepositar(String tarjeta, String cbu, BigDecimal importe, Throwable t) {
        log.error("🛑 Fallback depósito falló: {}", t.getMessage(), t);
        return "⚠️ No se pudo procesar el depósito. Intente más tarde.";
    }

    @Retry(name = "atmRetry")
    @CircuitBreaker(name = "atmCircuitBreaker", fallbackMethod = "fallbackConsultarSaldo")
    public String consultarSaldo(String tarjeta, String cuenta) {
        SaldoRequestDTO dto = new SaldoRequestDTO();
        dto.setNumeroTarjeta(tarjeta);
        dto.setNumeroCuenta(cuenta);
        return post("/cuenta/saldo", dto);
    }

    public String fallbackConsultarSaldo(String tarjeta, String cuenta, Throwable t) {
        log.error("🛑 Fallback saldo falló: {}", t.getMessage(), t);
        return "⛔ Servicio de saldo no disponible. Intente más tarde.";
    }
}

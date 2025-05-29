package com.atm.console.client;

import com.atm.console.dto.DepositoRequest;
import com.atm.console.dto.ExtraerRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class AtmRestClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080/api";

    public AtmRestClient() {
        this.restTemplate = new RestTemplate();
    }

    public String login(String tarjeta) {
        return restTemplate.getForObject(baseUrl + "/login/" + tarjeta, String.class);
    }

    public String extraer(String tarjeta, String cuenta, BigDecimal importe) {
        ExtraerRequest req = new ExtraerRequest(tarjeta, cuenta, importe);
        return restTemplate.postForObject(baseUrl + "/extraer", req, String.class);
    }

    public String depositar(String tarjeta, String cbu, BigDecimal importe) {
        DepositoRequest req = new DepositoRequest(tarjeta, cbu, importe);
        return restTemplate.postForObject(baseUrl + "/depositar", req, String.class);
    }

    public String consultarSaldo(String tarjeta, String cuenta) {
        return restTemplate.getForObject(baseUrl + "/saldo?tarjeta=" + tarjeta + "&cuenta=" + cuenta, String.class);
    }
}
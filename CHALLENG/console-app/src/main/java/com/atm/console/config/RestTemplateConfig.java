
package com.atm.console.config;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class RestTemplateConfig {

    @Bean
    @CircuitBreaker(name = "atmCircuitBreaker")
    @Retry(name = "atmRetry")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add((request, body, execution) -> {
            String traceId = UUID.randomUUID().toString();
            request.getHeaders().add("X-Trace-Id", traceId);
            System.out.println("[TRACE-ID] " + traceId + " -> " + request.getMethod() + " " + request.getURI());
            return execution.execute(request, body);
        });

        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}

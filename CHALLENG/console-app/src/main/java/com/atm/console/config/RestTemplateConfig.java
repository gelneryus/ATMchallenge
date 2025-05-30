package com.atm.console.config;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            String traceId = MDC.get("traceId"); // <- ahora reutiliza el existente
            if (traceId != null) {
                request.getHeaders().add("X-Trace-Id", traceId);
                System.out.println("[TRACE-ID] " + traceId + " -> " + request.getMethod() + " " + request.getURI());
            }
            return execution.execute(request, body);
        };

        restTemplate.setInterceptors(Collections.singletonList(interceptor));
        return restTemplate;
    }
}

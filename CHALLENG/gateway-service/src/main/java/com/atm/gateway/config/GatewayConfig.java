package com.atm.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("account_service", r -> r.path("/cuenta/**")
                .uri("http://localhost:8081"))
            .route("auth_service", r -> r.path("/auth/**")
                .uri("http://localhost:8082"))
            .route("transaction_service", r -> r.path("/transaccion/**")
                .uri("http://localhost:8083"))
            .build();
    }
}

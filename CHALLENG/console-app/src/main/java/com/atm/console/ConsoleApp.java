package com.atm.console;

import com.atm.console.CliApp;
import com.atm.console.config.TraceIdInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class ConsoleApp {

    public static void main(String[] args) {
        if (args.length > 0) {
            CliApp.main(args); // Ejecuta modo CLI por flags
        } else {
            SpringApplication.run(ConsoleApp.class, args); // Modo interactivo clásico
        }
    }

    // 👇 Este es el bean que registra el interceptor de traceId
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(new TraceIdInterceptor()));
        return restTemplate;
    }
}

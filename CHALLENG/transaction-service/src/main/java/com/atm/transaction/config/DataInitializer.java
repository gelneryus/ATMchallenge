package com.atm.transaction.config;

import com.atm.transaction.model.TransaccionEntity;
import com.atm.transaction.repository.TransaccionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(TransaccionRepository repo) {
        return args -> {
            TransaccionEntity tx = new TransaccionEntity();
            tx.setTipoOperacion("DEMO");
            tx.setNumeroTarjeta("12345");
            tx.setNumeroCuenta("67890");
            tx.setImporte(java.math.BigDecimal.ZERO);
            tx.setFechaHora(LocalDateTime.now());
            tx.setExitosa(true);
            tx.setMensaje("Registro demo");
            repo.save(tx);
        };
    }
}

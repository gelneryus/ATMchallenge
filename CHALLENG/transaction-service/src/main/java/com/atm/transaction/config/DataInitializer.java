package com.atm.transaction.config;

import com.atm.transaction.model.TransaccionEntity;
import com.atm.transaction.repository.TransaccionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(TransaccionRepository repo) {
        return args -> {
            TransaccionEntity demo = new TransaccionEntity();
            demo.setTipoOperacion("DEMO");
            demo.setNumeroTarjeta("12345");
            demo.setNumeroCuenta("67890");
            demo.setImporte(BigDecimal.ZERO);
            demo.setFechaHora(LocalDateTime.now());
            demo.setExitosa(true);
            demo.setMensaje("Registro demo");

            TransaccionEntity tx1 = new TransaccionEntity();
            tx1.setTipoOperacion("DEPOSITO");
            tx1.setNumeroTarjeta("12345");
            tx1.setNumeroCuenta("99999");
            tx1.setImporte(new BigDecimal("2000.00"));
            tx1.setFechaHora(LocalDateTime.now().minusDays(1));
            tx1.setExitosa(true);
            tx1.setMensaje("Depósito exitoso a cuenta ajena");

            TransaccionEntity tx2 = new TransaccionEntity();
            tx2.setTipoOperacion("EXTRACCION");
            tx2.setNumeroTarjeta("12345");
            tx2.setNumeroCuenta("12345");
            tx2.setImporte(new BigDecimal("500.00"));
            tx2.setFechaHora(LocalDateTime.now().minusHours(6));
            tx2.setExitosa(true);
            tx2.setMensaje("Extracción propia");

            TransaccionEntity tx3 = new TransaccionEntity();
            tx3.setTipoOperacion("SALDO");
            tx3.setNumeroTarjeta("67890");
            tx3.setNumeroCuenta("67890");
            tx3.setImporte(BigDecimal.ZERO);
            tx3.setFechaHora(LocalDateTime.now().minusMinutes(30));
            tx3.setExitosa(true);
            tx3.setMensaje("Consulta de saldo");

            repo.save(demo);
            repo.save(tx1);
            repo.save(tx2);
            repo.save(tx3);
        };
    }
}

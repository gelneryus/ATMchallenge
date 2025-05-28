package com.atm.auth.config;

import com.atm.auth.model.TarjetaEntity;
import com.atm.auth.repository.TarjetaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(TarjetaRepository repo) {
        return args -> {
            TarjetaEntity tarjeta = new TarjetaEntity();
            tarjeta.setNumero("12345");
            tarjeta.setActiva(true);
            tarjeta.setTitular("Joel Vallejos");
            repo.save(tarjeta);
        };
    }
}

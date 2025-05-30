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
            TarjetaEntity tarjeta1 = new TarjetaEntity();
            tarjeta1.setNumero("12345");
            tarjeta1.setActiva(true);
            tarjeta1.setTitular("Joel Vallejos");

            TarjetaEntity tarjeta2 = new TarjetaEntity();
            tarjeta2.setNumero("67890");
            tarjeta2.setActiva(true);
            tarjeta2.setTitular("Joel Vallejos");

            TarjetaEntity tarjeta3 = new TarjetaEntity();
            tarjeta3.setNumero("99999");
            tarjeta3.setActiva(true);
            tarjeta3.setTitular("Maria Perez");

            TarjetaEntity tarjeta4 = new TarjetaEntity();
            tarjeta4.setNumero("00000");
            tarjeta4.setActiva(false); // Tarjeta inactiva
            tarjeta4.setTitular("Carlos Ramirez");

            repo.save(tarjeta1);
            repo.save(tarjeta2);
            repo.save(tarjeta3);
            repo.save(tarjeta4);
        };
    }
}

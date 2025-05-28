package com.atm.account.config;

import com.atm.account.model.CuentaEntity;
import com.atm.account.repository.CuentaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(CuentaRepository repo) {
        return args -> {
            CuentaEntity cuenta = new CuentaEntity();
            cuenta.setNumeroCuenta("12345");
            cuenta.setCbu("CBU12345");
            cuenta.setSaldo(new BigDecimal("100000.00"));
            cuenta.setTitular("Joel Vallejos");
            repo.save(cuenta);
        };
    }
}

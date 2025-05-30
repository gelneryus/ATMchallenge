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
            CuentaEntity cuenta1 = new CuentaEntity();
            cuenta1.setNumeroCuenta("12345");
            cuenta1.setCbu("CBU12345");
            cuenta1.setSaldo(new BigDecimal("100000.00"));
            cuenta1.setTitular("Joel Vallejos");

            CuentaEntity cuenta2 = new CuentaEntity();
            cuenta2.setNumeroCuenta("67890");
            cuenta2.setCbu("CBU67890");
            cuenta2.setSaldo(new BigDecimal("50000.00"));
            cuenta2.setTitular("Joel Vallejos");

            CuentaEntity cuenta3 = new CuentaEntity();
            cuenta3.setNumeroCuenta("99999");
            cuenta3.setCbu("CBU99999");
            cuenta3.setSaldo(new BigDecimal("2500.00"));
            cuenta3.setTitular("Maria Perez");

            CuentaEntity cuenta4 = new CuentaEntity();
            cuenta4.setNumeroCuenta("11111");
            cuenta4.setCbu("CBU11111");
            cuenta4.setSaldo(new BigDecimal("300000.00"));
            cuenta4.setTitular("Carlos Ramirez");

            repo.save(cuenta1);
            repo.save(cuenta2);
            repo.save(cuenta3);
            repo.save(cuenta4);
        };
    }
}

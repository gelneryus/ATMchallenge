package com.atm.auth.repository;

import com.atm.auth.model.TarjetaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends CrudRepository<TarjetaEntity, String> {
    TarjetaEntity findByNumero(String numero);
}

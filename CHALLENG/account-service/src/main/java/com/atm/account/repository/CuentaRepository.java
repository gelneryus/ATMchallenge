package com.atm.account.repository;

import com.atm.account.model.CuentaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends CrudRepository<CuentaEntity, String> {

}

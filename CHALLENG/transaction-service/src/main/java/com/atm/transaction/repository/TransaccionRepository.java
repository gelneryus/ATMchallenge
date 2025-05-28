package com.atm.transaction.repository;

import com.atm.transaction.model.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Long> {
}

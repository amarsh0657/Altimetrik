package org.altimetrik.automotiveims.repo;

import org.altimetrik.automotiveims.entites.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesTransactionRepository extends JpaRepository<SalesTransaction, Long> {
}

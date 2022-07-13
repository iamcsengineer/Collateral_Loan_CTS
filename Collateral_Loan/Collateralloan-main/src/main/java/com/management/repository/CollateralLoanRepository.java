package com.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.entity.CollateralLoan;

@Repository
public interface CollateralLoanRepository extends JpaRepository<CollateralLoan, Integer> {
}

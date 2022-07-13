package com.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.entity.CollateralCashdeposit;

@Repository
public interface CollateralCashDepositRepository extends JpaRepository<CollateralCashdeposit, Integer> {
	
	}

package com.risk.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.risk.entity.CollateralRisk;

@Repository
public interface CollateralRiskRepository extends JpaRepository<CollateralRisk, Integer> {	
	
 
}

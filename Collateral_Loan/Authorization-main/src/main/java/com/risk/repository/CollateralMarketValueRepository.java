package com.risk.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.risk.entity.CollateralMarketValue;

@Repository
public interface CollateralMarketValueRepository extends JpaRepository<CollateralMarketValue, Integer> {
	
	public Optional<CollateralMarketValue> findByAddress(String address) ;

	public Optional<CollateralMarketValue> findByBankname(String bankname) ;

	
}

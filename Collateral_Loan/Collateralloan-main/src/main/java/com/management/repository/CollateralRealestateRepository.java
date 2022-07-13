package com.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.entity.CollateralRealestate;

@Repository
public interface CollateralRealestateRepository extends JpaRepository<CollateralRealestate, Integer> {

}

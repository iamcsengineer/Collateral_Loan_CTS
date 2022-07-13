package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loan.entity.CustomerLoan;

@Repository
public interface CustomerLoanRepo extends JpaRepository<CustomerLoan, Integer> {
}

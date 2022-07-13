package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loan.entity.Loan;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Integer>{

}

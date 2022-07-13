package com.management.service;

import com.management.entity.CollateralLoan;
import com.management.exception.NoCollateralLoanFoundException;
import com.management.pojo.DataCollateralLoan;

public interface ManagementService {
	
	public DataCollateralLoan getCollateralLoan(int loanid) throws NoCollateralLoanFoundException;
	public String saveCollateralLoan(CollateralLoan collateralLoan);
}

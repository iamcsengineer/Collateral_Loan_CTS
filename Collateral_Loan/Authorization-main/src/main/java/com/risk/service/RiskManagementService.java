package com.risk.service;

import java.io.IOException;

import com.risk.exception.NoCollateralLoanFoundException;
import com.risk.pojo.DataCollateralRisk;

public interface RiskManagementService {
	
	public DataCollateralRisk getCollateralRisk(String token ,int loanid) throws NoCollateralLoanFoundException;
	public String readfile() throws IOException;
}

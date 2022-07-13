package com.risk.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.risk.pojo.DataCollateralLoan;

// Interaction with CollateralLoan Management Microservice
@FeignClient(url = "${collateral.url}", name = "${collateral.name}")
public interface CollateralManagementClient {

	@GetMapping("/getCollaterals/{loanid}")
	public DataCollateralLoan getCollateralByLoanId(@RequestHeader(name = "Authorization") String token,
			@PathVariable int loanid);
}

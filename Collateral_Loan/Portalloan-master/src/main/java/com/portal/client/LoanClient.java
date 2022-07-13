package com.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.portal.pojo.DataCollateralLoan;

//For Interaction with Loan Management Microservice
@FeignClient(url="${loan.url}",name="${loan.name}")
public interface LoanClient {
	
	@GetMapping(path = "/loan/getLoanDetails/{loanId}")
	public DataCollateralLoan getLoanDetails(@RequestHeader(name = "Authorization") String token,
			@PathVariable int loanId);

	@PostMapping("/loan/saveCollaterals")
	public String saveCollaterals(@RequestHeader(name = "Authorization") String token,
			@RequestBody DataCollateralLoan dataCollateralLoan);
}

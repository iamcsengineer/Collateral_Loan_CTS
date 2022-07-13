package com.loan.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.loan.pojo.DataCollateralLoan;

//Used Fiegnclient for interaction with 
@FeignClient(url = "${collateral.url}", name = "${collateral.name}")
public interface CollateralManagementClient {
	
//	Getting Collateral Details by loanid
	@GetMapping("/getCollaterals/{loanid}")
	public DataCollateralLoan getCollateralByLoanId(@RequestHeader(name = "Authorization") String token,
			@PathVariable int loanid);

//	Saving Loan Details in Database	
	@PostMapping("/saveCollateral")
	public String saveCollateral(@RequestHeader(name = "Authorization") String token,
			@RequestBody DataCollateralLoan collateralLoan);
}

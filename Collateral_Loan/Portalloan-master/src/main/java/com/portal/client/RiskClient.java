package com.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.portal.pojo.DataCollateralRisk;

//For Interaction with Risk Management Microservice
@FeignClient(url="${risk.url}",name="${risk.name}")
public interface RiskClient {

	@GetMapping("/risk/getCollateralRisk/{loanid}")
	public DataCollateralRisk getCollateralRiskByLoanId(@RequestHeader(name = "Authorization") String token,
			@PathVariable int loanid);

	@GetMapping("/risk/getCollateralRisk")
	public String updateCollateralMarketValue(@RequestHeader(name = "Authorization") String token);
}

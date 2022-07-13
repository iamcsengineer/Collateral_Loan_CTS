package com.risk.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.risk.client.AuthClient;
import com.risk.exception.NoCollateralLoanFoundException;
import com.risk.pojo.AuthResponse;
import com.risk.pojo.DataCollateralRisk;
import com.risk.service.RiskManagementService;

@RestController
public class RiskController {

	@Autowired
	AuthClient authclient;

	@Autowired
	RiskManagementService managementService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RiskController.class);

	@GetMapping("/")
	public ResponseEntity<?> healthCheckup() {
		LOGGER.info("AWS Health Check");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

//	Retrieving Risk Details From Loan Id 
	@GetMapping("/risk/getCollateralRisk/{loanid}")
	public ResponseEntity<?> getCollateralRiskByLoanId(@RequestHeader(name = "Authorization") String token,
			@PathVariable int loanid) {
		LOGGER.info("Starting Collateral Risk By Loan Id");
		AuthResponse authResponse = authclient.verifyToken(token);
		LOGGER.info(authResponse.toString());
		if (authResponse.isValid()) {
			DataCollateralRisk collateralLoan;
			try {
				collateralLoan = managementService.getCollateralRisk(token, loanid);
				LOGGER.info(collateralLoan.toString());
				LOGGER.info("Ending Collateral Risk By Loan Id");
				return new ResponseEntity<DataCollateralRisk>(collateralLoan, HttpStatus.OK);
			} catch (NoCollateralLoanFoundException e) {
				LOGGER.error(e.getMessage());
				LOGGER.info("Ending  Collateral Risk By Loan Id");
				return new ResponseEntity<>("Loan not Found", HttpStatus.NOT_FOUND);
			}

		} else {
			LOGGER.error("Token Expired Please Create new to Use");
		    LOGGER.info("Ending Collateral Risk By Loan Id");
			return new ResponseEntity<>("Token Expired Please Create new to Use", HttpStatus.FORBIDDEN);
		}
	}

//  Getting Collateral Risk 
	@GetMapping("/risk/getCollateralRisk")
	public ResponseEntity<?> updateCollateralMarketValue(@RequestHeader(name = "Authorization") String token) {
		LOGGER.info("Starting Updating Collateral Market Value");
		String readfile;
		try {
			readfile = managementService.readfile();
			LOGGER.info(readfile);
			LOGGER.info("Ending Updating Collateral Market Value");
			return new ResponseEntity<>(readfile, HttpStatus.OK);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			LOGGER.info("Ending Updating Collateral Market Value");
			return new ResponseEntity<>("Not Updated", HttpStatus.OK);
		}

	}
}

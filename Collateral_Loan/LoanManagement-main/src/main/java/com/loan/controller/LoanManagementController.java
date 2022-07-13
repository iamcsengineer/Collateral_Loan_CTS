package com.loan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.loan.client.AuthClient;
import com.loan.exception.CollateralLoanAlreadyException;
import com.loan.exception.DataMissingException;
import com.loan.exception.NoCollateralLoanFoundException;
import com.loan.pojo.AuthResponse;
import com.loan.pojo.DataCollateralLoan;
import com.loan.service.LoanService;

@RestController
public class LoanManagementController {
	@Autowired
	AuthClient authclient;
	@Autowired
	LoanService loanService;

//	Logger Configuration or We can also use @log4j for that.
	private static final Logger LOGGER = LoggerFactory.getLogger(LoanManagementController.class);
	
	@GetMapping("/")
	public ResponseEntity<?> healthCheckup() {
		LOGGER.info("AWS Health Check");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

//  Retrieving loan Details 
	@GetMapping("/loan/getLoanDetails/{loanId}")
	public ResponseEntity<?> getLoanDetails(@RequestHeader(name = "Authorization") String token,
			@PathVariable int loanId) {
		LOGGER.info("Starting Get Loan Details");
		AuthResponse authResponse = authclient.verifyToken(token);
		// LOGGER.info(authResponse.toString());
		if (authResponse.isValid()) {
			DataCollateralLoan loanDetails;
			try {
				loanDetails = loanService.getLoanDetails(loanId, token);
//				LOGGER.info(loanDetails.toString());
//				LOGGER.info("Ending Get Loan Details");
				return new ResponseEntity<DataCollateralLoan>(loanDetails, HttpStatus.OK);
			} catch (NoCollateralLoanFoundException e) {
				LOGGER.error(e.getMessage());
//				LOGGER.info("Ending Get Loan Details");
				return new ResponseEntity<>("Loan not Found", HttpStatus.NOT_FOUND);
			}
		} else {
			LOGGER.error("Token Expired Please Create New to Use");
//			LOGGER.info("Ending Get Loan Details");
			return new ResponseEntity<>("Token Expired Please Create New to Use", HttpStatus.FORBIDDEN);
		}
	}

//  Saving Collateral Details 
	@PostMapping("/loan/saveCollaterals")
	public ResponseEntity<?> saveCollaterals(@RequestHeader(name = "Authorization") String token,
			@RequestBody DataCollateralLoan dataCollateralLoan) {
		LOGGER.info("Starting Save Collaterals");
		AuthResponse authResponse = authclient.verifyToken(token);
//		LOGGER.info(authResponse.toString());
		if (authResponse.isValid()) {
			String saveCollaterals;
			try {
				saveCollaterals = loanService.saveCollaterals(dataCollateralLoan, token);
//				LOGGER.info(saveCollaterals);
//				LOGGER.info("Ending Save Collaterals");
				return new ResponseEntity<>(saveCollaterals, HttpStatus.OK);
			} catch (DataMissingException e) {
				LOGGER.error(e.getMessage());
//				LOGGER.info("Ending Get Loan Details");
				return new ResponseEntity<>("Data Missing", HttpStatus.NOT_FOUND);
			} catch (CollateralLoanAlreadyException e) {
				LOGGER.error(e.getMessage());
//				LOGGER.info("Ending Get Loan Details");
				return new ResponseEntity<>("loan with this Id is Already present", HttpStatus.NOT_FOUND);
			}

		} else {
			LOGGER.info("Token Expired Please Create New to Use");
//			LOGGER.info("Ending Save Collaterals");
			return new ResponseEntity<>("Token Expired Please Create New to Use", HttpStatus.FORBIDDEN);
		}
	}

}

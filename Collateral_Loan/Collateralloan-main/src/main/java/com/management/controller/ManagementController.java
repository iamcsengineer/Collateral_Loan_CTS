package com.management.controller;

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

import com.management.client.AuthClient;
import com.management.entity.CollateralLoan;
import com.management.exception.NoCollateralLoanFoundException;
import com.management.pojo.AuthResponse;
import com.management.pojo.DataCollateralLoan;
import com.management.service.ManagementService;

@RestController
public class ManagementController {

	@Autowired
	AuthClient authclient;

	@Autowired
	ManagementService managementService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);

	//REST Endpoint for health check by AWS
	@GetMapping(path = "/")
	public ResponseEntity<?> healthCheckup() {
		LOGGER.info("AWS Health Check");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	//REST Endpoint to get the collateral details using the loan id
	@GetMapping(path = "/collateral/getCollaterals/{loanid}")
	public ResponseEntity<?> getCollateralByLoanId(@RequestHeader(name = "Authorization") String token,
			@PathVariable int loanid) {
		LOGGER.info("Starting Collateral By Loan Id");
		AuthResponse authResponse = authclient.verifyToken(token);
		// LOGGER.info(authResponse.toString());
		if (authResponse.isValid()) {
			try {
				DataCollateralLoan collateralLoan = managementService.getCollateralLoan(loanid);
				// LOGGER.info(collateralLoan.toString());
				// LOGGER.info("Ending Collateral By Loan Id");
				return new ResponseEntity<DataCollateralLoan>(collateralLoan, HttpStatus.OK);

			} catch (NoCollateralLoanFoundException e) {
				LOGGER.error(e.getMessage());
				// LOGGER.info("Ending Collateral By Loan Id");
				return new ResponseEntity<>("Loan not Found", HttpStatus.NOT_FOUND);
			}

		} else {
			LOGGER.error("Token Expired Please Create new to Use");
			// LOGGER.info("Ending Get Menu Item By ID");
			return new ResponseEntity<>("Token Expired Please Create new to Use", HttpStatus.FORBIDDEN);
		}
	}

	//Saving the collateral details of a loan using the loan id
	@PostMapping(path = "/collateral/saveCollateral")
	public ResponseEntity<?> saveCollateral(@RequestHeader(name = "Authorization") String token,
			@RequestBody CollateralLoan collateralLoan) {
		LOGGER.info("Starting save Collateral");
		AuthResponse authResponse = authclient.verifyToken(token);
		// LOGGER.info(authResponse.toString());
		if (authResponse.isValid()) {
			String saveCollateralLoan = managementService.saveCollateralLoan(collateralLoan);
			// LOGGER.info(saveCollateralLoan.toString());
			// LOGGER.info("Ending save Collateral");
			return new ResponseEntity<>(saveCollateralLoan, HttpStatus.OK);
		} else {
			LOGGER.error("Token Expired Please Create new to Use");
			// LOGGER.info("Ending Get Menu Item By ID");
			return new ResponseEntity<>("Token Expired Please Create new to Use", HttpStatus.FORBIDDEN);
		}
	}

}

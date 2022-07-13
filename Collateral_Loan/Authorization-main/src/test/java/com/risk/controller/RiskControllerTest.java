package com.risk.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.risk.client.AuthClient;
import com.risk.exception.NoCollateralLoanFoundException;
import com.risk.pojo.AuthResponse;
import com.risk.pojo.DataCollateralRisk;
import com.risk.service.RiskManagementService;

@SpringBootTest
public class RiskControllerTest {

	@InjectMocks
	RiskController riskController;

	@Mock
	AuthClient authclient;

	AuthResponse authResponse;

	DataCollateralRisk dataCollateralRisk;

	@Mock
	RiskManagementService riskManagementService;

	@Test
	void testGetCollateralRiskByLoanId() throws NoCollateralLoanFoundException {

		dataCollateralRisk = new DataCollateralRisk();
		dataCollateralRisk.setLoanid(1);
		dataCollateralRisk.setDateAssessed(new Date());
		dataCollateralRisk.setMarketCurrentValue(100);
		dataCollateralRisk.setRiskpercent(100);
		dataCollateralRisk.setSanctionedLoan(1000.0);

		authResponse = new AuthResponse("admin", "admin", true);
		when(authclient.verifyToken("token")).thenReturn(authResponse);

		when(riskManagementService.getCollateralRisk("token", 1)).thenReturn(dataCollateralRisk);
		ResponseEntity<?> collateralRiskByLoanId = riskController.getCollateralRiskByLoanId("token", 1);
		assertEquals(collateralRiskByLoanId.getStatusCodeValue(), 200);
	}

	@Test
	public void testGetCollateralByLoanIdFailed() throws NoCollateralLoanFoundException {
		when(authclient.verifyToken("token")).thenReturn(new AuthResponse("admin", "admin", false));
		when(riskManagementService.getCollateralRisk("token", 1)).thenReturn(null);
		ResponseEntity<?> collateralRiskByLoanId = riskController.getCollateralRiskByLoanId("token", 1);
		assertEquals(403, collateralRiskByLoanId.getStatusCodeValue());
	}

	@Test
	public void testGetLoanDetailsNotPresent() throws NoCollateralLoanFoundException {
		authResponse = new AuthResponse("admin", "admin", true);
		when(authclient.verifyToken("token")).thenReturn(authResponse);
		when(riskManagementService.getCollateralRisk("token", 1)).thenThrow(NoCollateralLoanFoundException.class);
		ResponseEntity<?> collateralRiskByLoanId = riskController.getCollateralRiskByLoanId("token", 1);
		assertEquals(collateralRiskByLoanId.getStatusCodeValue(), 404);
	}

	@Test
	public void testUpadateMarketValue() throws Exception {
		when(authclient.verifyToken("token")).thenReturn(new AuthResponse("admin", "admin", true));
		when(riskManagementService.readfile()).thenReturn("Updated");
		ResponseEntity<?> updateCollateralMarketValue = riskController.updateCollateralMarketValue("token");
		assertEquals(updateCollateralMarketValue.getStatusCodeValue(), 200);
	}

	@Test
	public void testUpdateMarketException() throws Exception {
		when(authclient.verifyToken("token")).thenReturn(new AuthResponse("admin", "admin", true));
		when(riskManagementService.readfile()).thenThrow(IOException.class);
		ResponseEntity<?> updateCollateralMarketValue = riskController.updateCollateralMarketValue("token");
		assertEquals(updateCollateralMarketValue.getStatusCodeValue(), 200);
	}

	@Test
	public void testhealth() {
		ResponseEntity<?> healthCheckup = riskController.healthCheckup();
		assertEquals(healthCheckup.getStatusCodeValue(), 200);

	}
}

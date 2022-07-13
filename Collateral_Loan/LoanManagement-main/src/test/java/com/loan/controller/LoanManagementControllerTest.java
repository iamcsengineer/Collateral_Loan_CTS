package com.loan.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.loan.client.AuthClient;
import com.loan.exception.CollateralLoanAlreadyException;
import com.loan.exception.DataMissingException;
import com.loan.exception.NoCollateralLoanFoundException;
import com.loan.pojo.AuthResponse;
import com.loan.pojo.DataCollateralLoan;
import com.loan.service.LoanService;

@SpringBootTest
public class LoanManagementControllerTest {

	@InjectMocks
	LoanManagementController loancontroller;

	@Mock
	AuthClient authclient;

	AuthResponse authResponse;
	DataCollateralLoan value;

	@Mock
	LoanService loanService;

	@Test
	public void testGetLoanDetails() throws NoCollateralLoanFoundException {
		authResponse = new AuthResponse("admin", "admin", true);
		when(authclient.verifyToken("token")).thenReturn(authResponse);
		value = new DataCollateralLoan();
		value.setLoanId(1);
		when(loanService.getLoanDetails(1, "token")).thenReturn(value);
		ResponseEntity<?> loanDetails = loancontroller.getLoanDetails("token", 1);
		assertEquals(loanDetails.getStatusCodeValue(), 200);
	}

	@Test
	public void testGetLoanDetailsFailed() throws NoCollateralLoanFoundException {
		authResponse = new AuthResponse("admin", "admin", false);
		when(authclient.verifyToken("token")).thenReturn(authResponse);
		when(loanService.getLoanDetails(1, "token")).thenReturn(null);
		ResponseEntity<?> loanDetails = loancontroller.getLoanDetails("token", 1);
		assertEquals(loanDetails.getStatusCodeValue(), 403);
	}

	@Test
	public void testGetLoanDetailsNotPresent() throws NoCollateralLoanFoundException {
		authResponse = new AuthResponse("admin", "admin", true);
		when(authclient.verifyToken("token")).thenReturn(authResponse);
		when(loanService.getLoanDetails(1, "token")).thenThrow(NoCollateralLoanFoundException.class);
		ResponseEntity<?> loanDetails = loancontroller.getLoanDetails("token", 1);
		assertEquals(loanDetails.getStatusCodeValue(), 404);
	}

	@Test
	public void testSaveCollaterals() throws DataMissingException, CollateralLoanAlreadyException {
		authResponse = new AuthResponse("admin", "admin", true);
		when(authclient.verifyToken("token")).thenReturn(authResponse);
		value = new DataCollateralLoan();
		value.setLoanId(1);
		when(loanService.saveCollaterals(value, "token")).thenReturn("Inserted");
		ResponseEntity<?> loanDetails = loancontroller.saveCollaterals("token", value);
		assertEquals(loanDetails.getStatusCodeValue(), 200);
	}

	@Test
    void testSaveCollateralsFailed() throws DataMissingException, CollateralLoanAlreadyException {
		authResponse = new AuthResponse("admin", "admin", false);
		when(authclient.verifyToken("token")).thenReturn(authResponse);
		when(loanService.saveCollaterals(value, "token")).thenReturn(null);
		ResponseEntity<?> loanDetails = loancontroller.saveCollaterals("token", value);
		assertEquals(loanDetails.getStatusCodeValue(), 403);
	}

	@Test
	public void testSaveLoanDetailsEmpty() throws DataMissingException, CollateralLoanAlreadyException {
		authResponse = new AuthResponse("admin", "admin", true);
		when(authclient.verifyToken("token")).thenReturn(authResponse);
		when(loanService.saveCollaterals(null, "token")).thenThrow(DataMissingException.class);
		ResponseEntity<?> loanDetails = loancontroller.saveCollaterals("token", null);
		assertEquals(loanDetails.getStatusCodeValue(), 404);
	}

	@Test
	public void testSaveCollateralsLoanIdPresent() throws DataMissingException, CollateralLoanAlreadyException {
		authResponse = new AuthResponse("admin", "admin", true);
		when(authclient.verifyToken("token")).thenReturn(authResponse);
		value = new DataCollateralLoan();
		value.setLoanId(1);
		when(loanService.saveCollaterals(value, "token")).thenThrow(CollateralLoanAlreadyException.class);
		;
		ResponseEntity<?> loanDetails = loancontroller.saveCollaterals("token", value);
		assertEquals(loanDetails.getStatusCodeValue(), 404);
	}

	@Test
	public void testhealth() {
		ResponseEntity<?> healthCheckup = loancontroller.healthCheckup();
		assertEquals(healthCheckup.getStatusCodeValue(), 200);

	}

}

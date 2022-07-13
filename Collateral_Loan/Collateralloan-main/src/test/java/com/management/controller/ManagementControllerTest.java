package com.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.management.client.AuthClient;
import com.management.entity.CollateralLoan;
import com.management.exception.NoCollateralLoanFoundException;
import com.management.pojo.AuthResponse;
import com.management.pojo.DataCollateralLoan;
import com.management.repository.CollateralLoanRepository;
import com.management.service.ManagementService;

@SpringBootTest
public class ManagementControllerTest {

	@InjectMocks
	ManagementController managementController;

	@Mock
	AuthClient authclient;

	AuthResponse authResponse;

	CollateralLoan user2;

	@Mock
	ManagementService managementService;

	@Mock
	CollateralLoanRepository collateralLoanRepository;

	@Test
	void testGetCollateralByLoanId() throws NoCollateralLoanFoundException {
		authResponse = new AuthResponse("admin", "admin", true);
		DataCollateralLoan user1 = new DataCollateralLoan();
		user1.setLoanId(1);
		user1.setCollateralValue(200.0);
		user1.setPledgedDate(new Date());
		user1.setCollateralName("Ad");

		when(authclient.verifyToken("token")).thenReturn(authResponse);
		when(managementService.getCollateralLoan(1)).thenReturn(user1);
		ResponseEntity<?> collateralByLoanId = managementController.getCollateralByLoanId("token", 1);
		assertEquals(200,collateralByLoanId.getStatusCodeValue());
	}

	@Test
	public void testGetCollateralByLoanIdFailed() throws NoCollateralLoanFoundException {
		when(authclient.verifyToken("token")).thenReturn(new AuthResponse("admin", "admin", false));
		when(managementService.getCollateralLoan(1)).thenReturn(null);
		ResponseEntity<?> insertNewOrderItem = managementController.getCollateralByLoanId("token", 1);
		assertEquals(403, insertNewOrderItem.getStatusCodeValue());
	}

	@Test
	public void testGetCollateralByLoanIdException() throws NoCollateralLoanFoundException {
		when(authclient.verifyToken("token")).thenReturn(new AuthResponse("admin", "admin", true));
		when(managementService.getCollateralLoan(6)).thenThrow(NoCollateralLoanFoundException.class);
		ResponseEntity<?> insertNewOrderItem = managementController.getCollateralByLoanId("token", 6);
		assertEquals(404, insertNewOrderItem.getStatusCodeValue());
	}

	@Test
	public void testsaveCollateralFailed() throws NoCollateralLoanFoundException {
		when(authclient.verifyToken("token")).thenReturn(new AuthResponse("admin", "admin", false));

		user2 = new CollateralLoan();
		user2.setLoanId(1);
		user2.setCollateralValue(200.0);
		user2.setPledgedDate(new Date());
		user2.setCollateralName("Ad");

		when(managementService.saveCollateralLoan(user2)).thenReturn(null);
		ResponseEntity<?> insertNewOrderItem = managementController.saveCollateral("token", user2);
		assertEquals(403, insertNewOrderItem.getStatusCodeValue());
	}

	@Test
	public void testsaveCollateral() throws NoCollateralLoanFoundException {
		when(authclient.verifyToken("token")).thenReturn(new AuthResponse("admin", "admin", true));

		user2 = new CollateralLoan();
		user2.setLoanId(1);
		user2.setCollateralValue(200.0);
		user2.setPledgedDate(new Date());
		user2.setCollateralName("Ad");

		when(managementService.saveCollateralLoan(user2)).thenReturn("inserted");
		ResponseEntity<?> insertNewOrderItem = managementController.saveCollateral("token", user2);
		assertEquals(200, insertNewOrderItem.getStatusCodeValue());
	}

	@Test
	public void testhealth() {
		ResponseEntity<?> healthCheckup = managementController.healthCheckup();
		assertEquals(200,healthCheckup.getStatusCodeValue());

	}

}

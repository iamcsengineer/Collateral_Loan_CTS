package com.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.management.entity.CollateralCashdeposit;
import com.management.entity.CollateralLoan;
import com.management.entity.CollateralRealestate;
import com.management.exception.NoCollateralLoanFoundException;
import com.management.pojo.DataCollateralLoan;

@SpringBootTest
public class ManagementServiceImplTest {

	@Autowired
	ManagementService service;
	
	@Test
	void testGetCollateralLoanRealEstate() throws NoCollateralLoanFoundException {
		DataCollateralLoan collateralLoan = service.getCollateralLoan(1);
		assertEquals(1,collateralLoan.getLoanId());
	}
	
	@Test
	void testGetCollateralLoancashDeposit() throws NoCollateralLoanFoundException {
		DataCollateralLoan collateralLoan = service.getCollateralLoan(4);
		assertEquals(4,collateralLoan.getLoanId());
	}

	@Test
	void testGetCollateralException() throws NoCollateralLoanFoundException {
		assertThrows(NoCollateralLoanFoundException.class, () -> service.getCollateralLoan(20),
				"No loan with this Id");
		
	}
	
	@Test
	void testSaveCollateralLoanCashDeposit() {
		CollateralLoan collateralLoan = new CollateralLoan();
		collateralLoan.setLoanId(1);
		collateralLoan.setCollateralValue(200.0);
		collateralLoan.setPledgedDate(new Date());
		collateralLoan.setCollateralName("Ad");
		CollateralCashdeposit collateralCashdeposit = new CollateralCashdeposit();
		collateralCashdeposit.setId(1);
		collateralLoan.setCollateralCashdeposit(collateralCashdeposit);
		String saveCollateralLoan = service.saveCollateralLoan(collateralLoan);
		assertEquals("Collateral Loan Inserted",saveCollateralLoan);
		
	}
	
	@Test
	void testSaveCollateralLoanRealEstate() {
		CollateralLoan collateralLoan = new CollateralLoan();
		collateralLoan.setLoanId(3);
		collateralLoan.setCollateralValue(200.0);
		collateralLoan.setPledgedDate(new Date());
		collateralLoan.setCollateralName("Ad");
		CollateralRealestate collateralRealestate = new CollateralRealestate();
		collateralRealestate.setId(3);
		collateralLoan.setCollateralRealestate(collateralRealestate);
		String saveCollateralLoan = service.saveCollateralLoan(collateralLoan);
		assertEquals("Collateral Loan Inserted",saveCollateralLoan);
		
	}	

}

package com.loan.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoanTest {

	Loan loan = new Loan();
	
	@Test
	void testSetId() {
		loan.setId(1);
		assertEquals(1,loan.getId());
	}

	@Test
	void testSetMaximumLoanEligibilityAmount() {
		loan.setMaximumLoanEligibilityAmount(100);
		assertEquals(100,loan.getMaximumLoanEligibilityAmount());
	}

	@Test
	void testSetInterest() {
		loan.setInterest(1);
		assertEquals(1,loan.getInterest());
	}

	@Test
	void testSetTenure() {
		loan.setTenure(1);
		assertEquals(1,loan.getTenure());
	}

	@Test
	void testToString() {
		String string = loan.toString();
		assertEquals(string,loan.toString());
	}

}

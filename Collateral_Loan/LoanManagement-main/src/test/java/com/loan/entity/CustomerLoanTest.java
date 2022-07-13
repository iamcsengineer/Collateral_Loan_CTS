package com.loan.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class CustomerLoanTest {

	CustomerLoan customerloan = new CustomerLoan();
	@Test
	void testSetLoanProductId() {
		customerloan.setLoanProductId(1);
		assertEquals(1, customerloan.getLoanProductId());
	}

	@Test
	void testSetLoanPrincipal() {
		customerloan.setLoanPrincipal(1);
		assertEquals(1, customerloan.getLoanPrincipal());
	}

	@Test
	void testSetTenure() {
		customerloan.setTenure(1);
		assertEquals(1, customerloan.getTenure());
	}

	@Test
	void testSetInterest() {
		customerloan.setInterest(1);
		assertEquals(1, customerloan.getInterest());
	}

	@Test
	void testSetEmi() {
		customerloan.setEmi(1);
		assertEquals(1, customerloan.getEmi());
	}

	@Test
	void testToString() {
		String string = customerloan.toString();
		assertEquals(string,customerloan.toString());
	}

}

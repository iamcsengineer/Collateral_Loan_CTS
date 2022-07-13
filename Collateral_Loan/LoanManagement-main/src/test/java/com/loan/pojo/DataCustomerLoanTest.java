package com.loan.pojo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataCustomerLoanTest {

	DataCustomerLoan data = new DataCustomerLoan();
	@Test
	void testSetLoanProductId() {
		data.setLoanProductId(1);
		assertEquals(1, data.getLoanProductId());
	}

	@Test
	void testSetLoanPrincipal() {
		data.setLoanPrincipal(1);
		assertEquals(1, data.getLoanPrincipal());
	}

	@Test
	void testSetTenure() {
		data.setTenure(1);
		assertEquals(1, data.getTenure());
	}

	@Test
	void testSetInterest() {
		data.setInterest(1);
		assertEquals(1, data.getInterest());
	}

	@Test
	void testSetEmi() {
		data.setEmi(1);
		assertEquals(1, data.getEmi());
	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string,data.toString());
	}

}

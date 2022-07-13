package com.loan.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class DataLoanTest {
	
	DataLoan data =new DataLoan();
	
	@Test
	void testSetId() {
		data.setId(1);
		assertEquals(data.getId(),1);
	}

	@Test
	void testSetMaximumLoanEligibilityAmount() {
		data.setMaximumLoanEligibilityAmount(1);
		assertEquals(data.getMaximumLoanEligibilityAmount(),1);
	}

	@Test
	void testSetInterest() {
		data.setInterest(1);
		assertEquals(data.getInterest(),1);
	}

	@Test
	void testSetTenure() {
		data.setTenure(1);
		assertEquals(data.getTenure(),1);
	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string,data.toString());
	}

}

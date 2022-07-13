package com.risk.pojo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class DataCollateralLoanTest {

	DataCollateralLoan data = new DataCollateralLoan();

	@Test
	void testSetLoanId() {
		data.setLoanId(5);
		assertEquals(data.getLoanId(), 5);
	}

	@Test
	void testSetCollateralName() {
		data.setCollateralName("aa");
		assertEquals(data.getCollateralName(), "aa");
	}

	@Test
	void testSetCollateralValue() {
		data.setCollateralValue(500);
		assertEquals(data.getCollateralValue(), 500);
	}

	@Test
	void testSetPledgedDate() {
		data.setPledgedDate(new Date());
		assertEquals(data.getPledgedDate(), data.getPledgedDate());
	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string, data.toString());
	}

}

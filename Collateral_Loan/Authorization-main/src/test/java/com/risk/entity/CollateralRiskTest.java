package com.risk.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;


class CollateralRiskTest {

CollateralRisk data = new CollateralRisk();
	
	@Test
	void testSetLoanid() {
		data.setLoanid(1);
		assertEquals(data.getLoanid(),1);
	}

	@Test
	void testSetRiskpercent() {
		data.setRiskpercent(20);
		assertEquals(data.getRiskpercent(),20);
	}

	@Test
	void testSetDateAssessed() {
		data.setDateAssessed(new Date());
		assertEquals(data.getDateAssessed(),data.getDateAssessed());
	}

	@Test
	void testSetSanctionedLoan() {
		data.setSanctionedLoan(100);
		assertEquals(data.getSanctionedLoan(),100);
	}

	@Test
	void testSetMarketCurrentValue() {
		data.setMarketCurrentValue(15);
		assertEquals(data.getMarketCurrentValue(),15);
	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string,data.toString());
	}

}

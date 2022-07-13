package com.risk.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class CollateralMarketValueTest {

	CollateralMarketValue data = new CollateralMarketValue();
	
	@Test
	void testSetId() {
		data.setId(1);
		assertEquals(data.getId(),1);
	}

	@Test
	void testSetInterestRate() {
		data.setInterestrate(5);
		assertEquals(data.getInterestrate(),5);
	
	}

	@Test
	void testSetBankname() {
		data.setBankname("A");
		assertEquals(data.getBankname(),"A");
	
	}

	@Test
	void testSetAddress() {
		data.setAddress("a");
		assertEquals(data.getAddress(),"a");
	
	}

	@Test
	void testSetRatepersqft() {
		data.setRatepersqft(5);
		assertEquals(data.getRatepersqft(),5);
	
	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string,data.toString());
	}

}

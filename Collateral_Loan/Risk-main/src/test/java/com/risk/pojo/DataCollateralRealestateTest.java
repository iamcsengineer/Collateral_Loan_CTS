package com.risk.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class DataCollateralRealestateTest {

	DataCollateralRealestate data = new DataCollateralRealestate();
	
	@Test
	void testSetId() {
		data.setId(1);
		assertEquals(data.getId(),1);
	}

	@Test
	void testSetCollateralType() {
		data.setCollateralType("a");
		assertEquals(data.getCollateralType(),"a");
	
	}

	@Test
	void testSetAddress() {
		data.setAddress("a");
		assertEquals(data.getAddress(),"a");
	
	}

	@Test
	void testSetCurrentvalue() {
		data.setCurrentvalue(1);
		assertEquals(data.getCurrentvalue(),1);
	
	}

	@Test
	void testSetRatepersqft() {
		data.setRatepersqft(1);
		assertEquals(data.getRatepersqft(),1);

	}

	@Test
	void testSetDepreciationrate() {
		data.setDepreciationrate(1);
		assertEquals(data.getDepreciationrate(),1);

	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string,data.toString());
	}

}

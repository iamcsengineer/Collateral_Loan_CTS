package com.management.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CollateralRealestateTest {

	CollateralRealestate data = new CollateralRealestate();

	@Test
	void testSetId() {
		data.setId(1);
		assertEquals(1, data.getId());
	}

	@Test
	void testSetCollateralType() {
		data.setCollateralType("a");
		assertEquals("a", data.getCollateralType());

	}

	@Test
	void testSetAddress() {
		data.setAddress("a");
		assertEquals("a", data.getAddress());

	}

	@Test
	void testSetCurrentvalue() {
		data.setCurrentvalue(1);
		assertEquals(1, data.getCurrentvalue());

	}

	@Test
	void testSetRatepersqft() {
		data.setRatepersqft(1);
		assertEquals(1, data.getRatepersqft());

	}

	@Test
	void testSetDepreciationrate() {
		data.setDepreciationrate(1);
		assertEquals(1, data.getDepreciationrate());

	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string, data.toString());
	}

}

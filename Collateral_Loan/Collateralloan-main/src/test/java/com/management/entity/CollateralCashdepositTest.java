package com.management.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CollateralCashdepositTest {

	CollateralCashdeposit data = new CollateralCashdeposit();

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
	void testSetBankname() {
		data.setBankname("a");
		assertEquals("a", data.getBankname());
	}

	@Test
	void testSetCurrentvalue() {
		data.setCurrentvalue(1);
		assertEquals(1, data.getCurrentvalue());
	}

	@Test
	void testSetInterestrate() {
		data.setInterestrate(1);
		assertEquals(1, data.getInterestrate());
	}

	@Test
	void testSetLockperiod() {
		data.setLockperiod(1);
		assertEquals(1, data.getLockperiod());
	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string, data.toString());
	}

}

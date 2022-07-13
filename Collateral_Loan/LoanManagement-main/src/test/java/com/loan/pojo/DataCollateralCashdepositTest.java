package com.loan.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class DataCollateralCashdepositTest {
	
	DataCollateralCashdeposit data = new DataCollateralCashdeposit();
	
	@Test
	void testSetCollateralType() {
		data.setCollateralType("a");
		assertEquals(data.getCollateralType(),"a");
	}
	@Test
	void testSetBankname() {
		data.setBankname("a");
		assertEquals(data.getBankname(),"a");
	}

	@Test
	void testSetCurrentvalue() {
		data.setCurrentvalue(1);
		assertEquals(data.getCurrentvalue(),1);
	}

	@Test
	void testSetInterestrate() {
		data.setInterestrate(1);
		assertEquals(data.getInterestrate(),1);
	}
	@Test
	void testSetLockperiod() {
		data.setLockperiod(1);
		assertEquals(data.getLockperiod(),1);
	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string,data.toString());
	}

}

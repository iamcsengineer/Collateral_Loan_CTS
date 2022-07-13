package com.loan.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class DataCustomerTest {

	DataCustomer data = new DataCustomer();
	
	@Test
	void testSetName() {
		data.setName("a");
		assertEquals("a", data.getName());
	}

	@Test
	void testSetAddress() {
		data.setAddress("a");
		assertEquals("a", data.getAddress());
	}

	@Test
	void testSetPhoneNo() {
		data.setPhoneNo(10);
		assertEquals(10, data.getPhoneNo());
	}

	@Test
	void testToString() {
		String string = data.toString();
		assertEquals(string,data.toString());
	}

}

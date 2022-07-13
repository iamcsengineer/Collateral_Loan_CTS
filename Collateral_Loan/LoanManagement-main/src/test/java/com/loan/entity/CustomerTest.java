package com.loan.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerTest {

	Customer customer = new Customer();
	@Test
	void testSetCustomerid() {
		customer.setCustomerid(1);
		assertEquals(1,customer.getCustomerid());
	}

	@Test
	void testSetName() {
		customer.setName("a");
		assertEquals("a",customer.getName());
	}

	@Test
	void testSetAddress() {
		customer.setAddress("a");
		assertEquals("a",customer.getAddress());
	}

	@Test
	void testSetPhoneNo() {
		customer.setPhoneNo(1233);
		assertEquals(1233,customer.getPhoneNo());
	}

	@Test
	void testToString() {
		String string = customer.toString();
		assertEquals(string,customer.toString());
	}
}

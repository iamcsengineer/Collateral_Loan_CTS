package com.management.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class AuthResponseTest {
	
	AuthResponse auth = new AuthResponse();

	@Test
	void testSetUid() {
		auth.setName("a");
		assertEquals("a",auth.getName());
	}

	@Test
	void testSetName() {
		auth.setUid("a");
		assertEquals("a",auth.getUid());
	}

	@Test
	void testSetValid() {
		auth.setValid(true);
		assertEquals(true,auth.isValid());
	}
}

package com.risk.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class AuthResponseTest {
	
	AuthResponse auth = new AuthResponse();

	@Test
	void testSetUid() {
		auth.setName("a");
		assertEquals(auth.getName(),"a");
	}

	@Test
	void testSetName() {
		auth.setUid("a");
		assertEquals(auth.getUid(),"a");
	}

	@Test
	void testSetValid() {
		auth.setValid(true);
		assertEquals(auth.isValid(),true);
	}
}

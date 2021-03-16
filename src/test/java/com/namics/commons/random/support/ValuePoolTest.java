/*
 * Copyright 2000-2021 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ValuePoolTest.
 *
 * @author aschaefer, Namics AG
 * @since 16.03.21 16:03
 */
class ValuePoolTest {

	@Test
	void internationalNameSwitch() {
		System.setProperty("random.names.international", "false");
		assertFalse(ValuePool.getFemaleNames().contains("Žaklina"));

		System.setProperty("random.names.international", "true");
		assertTrue(ValuePool.getFemaleNames().contains("Žaklina"));

		System.clearProperty("random.names.international");
		assertFalse(ValuePool.getFemaleNames().contains("Žaklina"));
	}
}

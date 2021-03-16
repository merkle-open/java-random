/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * LoremIpsumTest.
 *
 * @author aschaefer
 * @since 27.02.14 19:13
 */
class LoremIpsumTest {
	@Test
	void testGetWords() throws Exception {
		assertEquals(195, LoremIpsum.getWords().size());
	}
}

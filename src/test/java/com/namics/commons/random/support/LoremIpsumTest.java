/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import org.junit.Test;

/**
 * LoremIpsumTest.
 *
 * @author aschaefer
 * @since 27.02.14 19:13
 */
public class LoremIpsumTest {
	@Test
	public void testGetWords() throws Exception {
		System.out.println(LoremIpsum.getWords().size());
	}
}

/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

/**
 * LocalDateTimeGeneratorTest.
 *
 * @author aschaefer, Namics AG
 * @since 19.05.15 16:27
 */
public class ZonedDateTimeGeneratorTest {

	@Test
	public void testRandom() throws Exception {
		System.out.println(RandomData.random(ZonedDateTime.class));
		System.out.println(RandomData.random(ZonedDateTime.class));
		System.out.println(RandomData.random(ZonedDateTime.class));
	}
}

/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * LocalDateTimeGeneratorTest.
 *
 * @author aschaefer, Namics AG
 * @since 19.05.15 16:27
 */
public class LocalDateTimeGeneratorTest {

	@Test
	public void testRandom() throws Exception {
		System.out.println(RandomData.random(LocalDateTime.class));
		System.out.println(RandomData.random(LocalDateTime.class));
		System.out.println(RandomData.random(LocalDateTime.class));
		System.out.println(RandomData.random(LocalDateTime.class));
		System.out.println(RandomData.random(LocalDateTime.class));
		System.out.println(RandomData.random(LocalDateTime.class));
	}
}
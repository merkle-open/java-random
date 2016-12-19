/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * LocalDateGenerator.
 *
 * @author aschaefer
 * @since 09.07.2015 16:58
 */
public class LocalDateGenerator implements RandomGenerator<LocalTime> {

	@Override
	public LocalTime random() {
		LocalTime now = LocalTime.now();
		int day = 60 * 60 * 24;
		return now.plusSeconds((long) RandomData.randomInteger(-1 * day, day));// +- 1 day;
	}

	@Override
	public Iterable<Class<LocalTime>> supportedTypes() {
		return Arrays.asList(LocalTime.class);
	}

}

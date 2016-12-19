/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * LocalTimeGenerator.
 *
 * @author aschaefer
 * @since 09.07.2015 16:58
 */
public class LocalTimeGenerator implements RandomGenerator<LocalDate> {

	@Override
	public LocalDate random() {
		LocalDate now = LocalDate.now();
		int year = 365;
		return now.plusDays((long) RandomData.randomInteger(-2 * year, 2 * year));// +- 2 years;
	}

	@Override
	public Iterable<Class<LocalDate>> supportedTypes() {
		return Arrays.asList(LocalDate.class);
	}

}

/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

/**
 * LocalDateTimeGenerator.
 *
 * @author aschaefer
 * @since 09.07.2015 16:58
 */
public class ZonedDateTimeGenerator implements RandomGenerator<ZonedDateTime> {

	@Override
	public ZonedDateTime random() {
		ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(RandomData.random(ZoneId.getAvailableZoneIds())));
		int year = 60 * 60 * 24 * 365;
		return now.plusSeconds((long) RandomData.randomInteger(-2 * year, 2 * year));// +- 2 years;
	}

	@Override
	public Iterable<Class<ZonedDateTime>> supportedTypes() {
		return Arrays.asList(ZonedDateTime.class);
	}

}

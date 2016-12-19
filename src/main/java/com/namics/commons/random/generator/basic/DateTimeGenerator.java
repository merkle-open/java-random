/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.generator.RandomGenerator;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;
import org.joda.time.Years;

import java.util.Arrays;

/**
 * IntegerGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class DateTimeGenerator implements RandomGenerator<DateTime> {

	@Override
	public DateTime random() {
		DateTime now = DateTime.now();
		long min = now.minus(Years.TWO).getMillis();
		long max = now.plus(Years.TWO).getMillis();
		double random = RandomUtils.nextDouble() * (max - min) + min;
		return new DateTime(Double.valueOf(random).longValue());
	}

	@Override
	public Iterable<Class<DateTime>> supportedTypes() {
		return Arrays.asList(DateTime.class);
	}

}

/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Arrays;

/**
 * JodaPeriodGenerator.
 *
 * @author aschaefer, Namics AG
 * @since 09.01.15 08:52
 */
public class JodaPeriodGenerator implements RandomGenerator<Period> {
	@Override
	public Period random() {
		return new Period(RandomData.randomLong(0, 1000), RandomData.random(
				Arrays.asList(
						PeriodType.millis(),
						PeriodType.seconds(),
						PeriodType.minutes(),
						PeriodType.hours(),
						PeriodType.days(),
						PeriodType.weeks(),
						PeriodType.months(),
						PeriodType.years()
				             )));
	}

	@Override
	public Iterable<Class<Period>> supportedTypes() {
		return Arrays.asList(Period.class);
	}
}

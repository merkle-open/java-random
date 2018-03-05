/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.generator.RandomGenerator;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

/**
 * DateGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class DateGenerator implements RandomGenerator<Date> {

	@Override
	public Date random() {
		return Date.from(new LocalDateTimeGenerator().random().atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public Iterable<Class<Date>> supportedTypes() {
		return Arrays.asList(Date.class);
	}

}

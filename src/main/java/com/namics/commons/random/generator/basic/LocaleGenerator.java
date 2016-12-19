/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;

import java.util.Arrays;
import java.util.Locale;

/**
 * IntegerGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class LocaleGenerator implements RandomGenerator<Locale> {

	@Override
	public Locale random() {
		return RandomData.random(Locale.getAvailableLocales());
	}

	@Override
	public Iterable<Class<Locale>> supportedTypes() {
		return Arrays.asList(Locale.class);
	}

}

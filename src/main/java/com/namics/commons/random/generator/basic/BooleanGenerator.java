/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.generator.RandomGenerator;
import org.apache.commons.lang.math.RandomUtils;

import java.util.Arrays;

/**
 * IntegerGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class BooleanGenerator implements RandomGenerator<Boolean> {

	@Override
	public Boolean random() {
		return RandomUtils.nextBoolean();
	}

	@Override
	public Iterable<Class<Boolean>> supportedTypes() {
		return Arrays.asList(Boolean.class,boolean.class);
	}

}

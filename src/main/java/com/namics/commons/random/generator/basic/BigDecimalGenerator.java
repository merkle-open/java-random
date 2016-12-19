/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.generator.RandomGenerator;
import org.apache.commons.lang.math.RandomUtils;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * IntegerGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class BigDecimalGenerator implements RandomGenerator<BigDecimal> {

	@Override
	public BigDecimal random() {
		return BigDecimal.valueOf(RandomUtils.nextDouble());
	}

	@Override
	public Iterable<Class<BigDecimal>> supportedTypes() {
		return Arrays.asList(BigDecimal.class);
	}

}

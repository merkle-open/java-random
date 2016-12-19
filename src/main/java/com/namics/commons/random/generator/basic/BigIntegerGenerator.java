/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.generator.RandomGenerator;
import org.apache.commons.lang.math.RandomUtils;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * BigIntegerGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class BigIntegerGenerator implements RandomGenerator<BigInteger> {

	@Override
	public BigInteger random() {
		return BigInteger.valueOf(RandomUtils.nextInt());
	}

	@Override
	public Iterable<Class<BigInteger>> supportedTypes() {
		return Arrays.asList(BigInteger.class);
	}

}

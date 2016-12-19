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
public class IntegerGenerator implements RandomGenerator<Integer> {

	@Override
	public Integer random() {
		return RandomUtils.nextInt();
	}

	@Override
	public Iterable<Class<Integer>> supportedTypes() {
		return Arrays.asList(Integer.class,int.class);
	}

}

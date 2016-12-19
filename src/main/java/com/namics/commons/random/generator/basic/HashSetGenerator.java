/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.generator.InformedRandomGenerator;
import com.namics.commons.random.generator.RandomGenerator;

import java.util.*;

/**
 * TreeMapGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class HashSetGenerator extends AbstractGenericCollectionGenerator<HashSet> implements RandomGenerator, InformedRandomGenerator {

	@Override
	public HashSet random() {
		return new HashSet();
	}

	@Override
	public Iterable<Class> supportedTypes() {
		return Arrays.<Class>asList(Set.class, HashSet.class, AbstractSet.class);
	}
}

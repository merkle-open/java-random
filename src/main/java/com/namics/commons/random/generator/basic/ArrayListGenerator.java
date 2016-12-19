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
public class ArrayListGenerator extends AbstractGenericCollectionGenerator<ArrayList> implements RandomGenerator, InformedRandomGenerator {

	@Override
	public ArrayList random() {
		return new ArrayList();
	}


	@Override
	public Iterable<Class> supportedTypes() {
		return Arrays.<Class>asList(Collection.class, List.class, RandomAccess.class, AbstractCollection.class, AbstractList.class, ArrayList.class);
	}


}

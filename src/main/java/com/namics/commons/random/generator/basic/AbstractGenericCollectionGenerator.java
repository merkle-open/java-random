/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.InformedRandomGenerator;
import com.namics.commons.random.generator.RandomGenerator;
import com.namics.commons.random.support.Utils;

import java.lang.reflect.Type;
import java.util.Collection;

/**
 * AbstractGenericCollectionGenerator.
 *
 * @author aschaefer, Namics AG
 * @since 20.08.14 15:18
 */
public abstract class AbstractGenericCollectionGenerator<T extends Collection> implements RandomGenerator, InformedRandomGenerator {
	@Override
	public abstract T random();

	@Override
	public T random(Object... information) {
		T result = random();

		Type[] generics = Utils.objectsOfType(Type.class, information);
		String[] fields = Utils.objectsOfType(String.class, information);

		if (generics.length == 1) {
			Type clazz =  generics[0];
			for (int count = 0; count < RandomData.randomInteger(1, 10); count++) {
				result.add(RandomData.random(clazz, fields));
			}
		}
		return result;
	}

	@Override
	public abstract Iterable<Class> supportedTypes();
}

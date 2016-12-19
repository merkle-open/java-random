/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.InformedRandomGenerator;
import com.namics.commons.random.generator.RandomGenerator;
import com.namics.commons.random.support.Utils;

import java.lang.reflect.Type;
import java.util.*;

/**
 * TreeMapGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class TreeMapGenerator implements RandomGenerator, InformedRandomGenerator {

	@Override
	public TreeMap random() {
		return new TreeMap();
	}

	@Override
	public Object random(Object... information) {
		TreeMap result = random();
		Type[] generics = Utils.objectsOfType(Type.class, information);
		String[] fields = Utils.objectsOfType(String.class, information);
		if (generics.length == 2) {
			Type keyClass =  generics[0];
			Type valueClass = generics[1];
			for (int count = 0; count < RandomData.randomInteger(1, 10); count++) {
				Object key = RandomData.random(keyClass, fields);
				Object value = RandomData.random(valueClass, fields);
				result.put(key, value);
			}
		}
		return result;
	}

	@Override
	public Iterable<Class> supportedTypes() {
		return Arrays.<Class>asList(TreeMap.class, SortedMap.class, NavigableMap.class);
	}

}

package com.namics.commons.random.known;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;

import java.util.Arrays;

public class TestAttributeKnownGenerator implements RandomGenerator<TestAttributeKnown> {

	public TestAttributeKnown random() {
		return new TestAttributeKnown(RandomData.alphabetic(5));
	}

	public Iterable<Class<TestAttributeKnown>> supportedTypes() {
		return Arrays.asList(TestAttributeKnown.class);
	}
}
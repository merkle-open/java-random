package com.namics.commons.random.unknown;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;
import com.namics.commons.random.known.TestAttributeManualRegisterClass;

import java.util.Arrays;

public class TestAttributeManualRegisterClassGenerator implements RandomGenerator<TestAttributeManualRegisterClass> {

	public TestAttributeManualRegisterClass random() {
		return new TestAttributeManualRegisterClass(RandomData.alphabetic(5));
	}

	public Iterable<Class<TestAttributeManualRegisterClass>> supportedTypes() {
		return Arrays.asList(TestAttributeManualRegisterClass.class);
	}
}
package com.namics.commons.random.unknown;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;
import com.namics.commons.random.known.TestAttributeManualRegisterBean;

import java.util.Arrays;

public class TestAttributeManualRegisterBeanGenerator implements RandomGenerator<TestAttributeManualRegisterBean> {

	public TestAttributeManualRegisterBean random() {
		return new TestAttributeManualRegisterBean(RandomData.alphabetic(5));
	}

	public Iterable<Class<TestAttributeManualRegisterBean>> supportedTypes() {
		return Arrays.asList(TestAttributeManualRegisterBean.class);
	}
}
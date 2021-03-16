/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator;

import com.namics.commons.random.known.TestAttributeManualRegisterBean;
import com.namics.commons.random.known.TestAttributeManualRegisterClass;
import com.namics.commons.random.unknown.TestAttributeManualRegisterBeanGenerator;
import com.namics.commons.random.unknown.TestAttributeManualRegisterClassGenerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RandomGeneratorFactoryTest.
 *
 * @author aschaefer
 * @since 21.02.14 13:06
 */
class RandomGeneratorFactoryTest {

	@Test
	void testGeneratorByClassExists() throws Exception {
		assertNotNull(RandomGeneratorFactory.generator(Integer.class));
	}

	@Test
	void testGeneratorByClassNotExists() throws Exception {
//	   	assertNull(RandomGeneratorFactory.generator(TestAttributeManualRegisterClass.class));
		RandomGeneratorFactory.addRandomGenerator(TestAttributeManualRegisterClassGenerator.class);
		assertNotNull(RandomGeneratorFactory.generator(TestAttributeManualRegisterClass.class));
	}

	@Test
	void testGeneratorByBeanNotExists() throws Exception {
//		assertNull(RandomGeneratorFactory.generator(TestAttributeManualRegisterBean.class));
		RandomGeneratorFactory.addRandomGenerator(new TestAttributeManualRegisterBeanGenerator());
		assertNotNull(RandomGeneratorFactory.generator(TestAttributeManualRegisterBean.class));
	}

	@Test
	void testGeneratorByName() throws Exception {
		assertNotNull(RandomGeneratorFactory.generator(Integer.class.getName()));
	}

	@Test
	void testGeneratorByNameWrongClassname() throws Exception {
		assertNull(RandomGeneratorFactory.generator("unknown"));
	}

}

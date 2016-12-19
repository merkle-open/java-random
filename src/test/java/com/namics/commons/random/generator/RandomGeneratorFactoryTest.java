/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator;

import com.namics.commons.random.known.TestAttributeManualRegisterBean;
import com.namics.commons.random.known.TestAttributeManualRegisterClass;
import com.namics.commons.random.unknown.TestAttributeManualRegisterBeanGenerator;
import com.namics.commons.random.unknown.TestAttributeManualRegisterClassGenerator;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * RandomGeneratorFactoryTest.
 *
 * @author aschaefer
 * @since 21.02.14 13:06
 */
public class RandomGeneratorFactoryTest {

	@Test
	public void testGeneratorByClassExists() throws Exception {
		assertNotNull(RandomGeneratorFactory.generator(Integer.class));
	}

	@Test
	public void testGeneratorByClassNotExists() throws Exception {
	   	assertNull(RandomGeneratorFactory.generator(TestAttributeManualRegisterClass.class));
		RandomGeneratorFactory.addRandomGenerator(TestAttributeManualRegisterClassGenerator.class);
		assertNotNull(RandomGeneratorFactory.generator(TestAttributeManualRegisterClass.class));
	}

	@Test
	public void testGeneratorByBeanNotExists() throws Exception {
		assertNull(RandomGeneratorFactory.generator(TestAttributeManualRegisterBean.class));
		RandomGeneratorFactory.addRandomGenerator(new TestAttributeManualRegisterBeanGenerator());
		assertNotNull(RandomGeneratorFactory.generator(TestAttributeManualRegisterBean.class));
	}

	@Test
	public void testGeneratorByName() throws Exception {
		assertNotNull(RandomGeneratorFactory.generator(Integer.class.getName()));
	}

	@Test
	public void testGeneratorByNameWrongClassname() throws Exception {
		assertNull(RandomGeneratorFactory.generator("unknown"));
	}

}

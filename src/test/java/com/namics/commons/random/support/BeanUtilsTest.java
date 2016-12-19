/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * BeanUtilsTest.
 *
 * @author aschaefer
 * @since 21.02.14 10:05
 */
public class BeanUtilsTest {
	private static final Logger LOG = LoggerFactory.getLogger(BeanUtilsTest.class);

	@Test
	public void testGetPropertyDescriptors() throws Exception {
		List<PropertyDescriptor> propertyDescriptors = BeanUtils.getPropertyDescriptors(TestBean.class);
		LOG.info("{}", propertyDescriptors);
		assertNotNull(propertyDescriptors);
		assertEquals(3, propertyDescriptors.size());
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			assertNotNull(descriptor.getName() + " read method is null ",descriptor.getReadMethod());
			if ( !"class".equals(descriptor.getName())){
				assertNotNull(descriptor.getName() + " write method is null ",descriptor.getWriteMethod());
			}
		}
	}

	public static class TestBean{
		private String standard;
		private String extended;

		public String getStandard() {
			return standard;
		}

		public void setStandard(String standard) {
			this.standard = standard;
		}

		public String getExtended() {
			return extended;
		}

		public TestBean setExtended(String extended) {
			this.extended = extended;
			return this;
		}
	}
}

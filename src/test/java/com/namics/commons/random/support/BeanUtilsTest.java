/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * BeanUtilsTest.
 *
 * @author aschaefer
 * @since 21.02.14 10:05
 */
class BeanUtilsTest {
	private static final Logger LOG = LoggerFactory.getLogger(BeanUtilsTest.class);

	@Test
	void testGetPropertyDescriptors() throws Exception {
		List<PropertyDescriptor> propertyDescriptors = BeanUtils.getPropertyDescriptors(TestBean.class);
		LOG.info("{}", propertyDescriptors);
		assertNotNull(propertyDescriptors);
		assertEquals(3, propertyDescriptors.size());
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			assertNotNull(descriptor.getReadMethod(), descriptor.getName() + " read method is null ");
			if (!"class".equals(descriptor.getName())) {
				assertNotNull(descriptor.getWriteMethod(), descriptor.getName() + " write method is null ");
			}
		}
	}

	static class TestBean {
		private String standard;
		private String extended;

		String getStandard() {
			return standard;
		}

		void setStandard(String standard) {
			this.standard = standard;
		}

		String getExtended() {
			return extended;
		}

		TestBean setExtended(String extended) {
			this.extended = extended;
			return this;
		}
	}
}

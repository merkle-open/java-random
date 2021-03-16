/*
 * Copyright 2000-2012 Namics AG. All rights reserved.
 */

package com.namics.commons.random;

import com.namics.commons.random.support.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Assert {

	public static void assertPropertiesNotNull(Object object, String... ignoreProperties) {
		List<String> ignores = Arrays.asList(ignoreProperties);

		assertNotNull(object, "object must not be null");

		List<PropertyDescriptor> descriptors = BeanUtils.getPropertyDescriptors(object.getClass());
		if (descriptors != null) {
			for (PropertyDescriptor descriptor : descriptors) {
				if (descriptor != null && descriptor.getReadMethod() != null && !ignores.contains(descriptor.getName())) {
					try {
						assertNotNull(descriptor.getReadMethod().invoke(object),
						              "property " + descriptor.getName() + " must not be null");
					} catch (AssertionError e) {
						throw e;
					} catch (Exception e) {
						fail("unexpected invocation exception " + e);
					}
				}
			}
		}
	}
}

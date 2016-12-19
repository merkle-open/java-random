/*
 * Copyright 2000-2012 Namics AG. All rights reserved.
 */

package com.namics.commons.random;

import com.namics.commons.random.support.BeanUtils;
import org.joda.time.DateTime;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Assert extends org.junit.Assert
{
	public static void assertEquals(DateTime expected,
									Date actual)
	{
		if (expected == null)
		{
			assertNull(actual);
		}
		else
		{
			assertEquals(expected.toDate(), actual);
		}
	}

	public static void assertEquals(Date expected,
									DateTime actual)
	{
		if (expected == null)
		{
			assertNull(actual);
		}
		else
		{
			assertNotNull(actual);
			assertEquals(expected, actual.toDate());
		}
	}

	public static void assertPropertiesNotNull(Object object,String... ignoreProperties)
	{
		List<String> ignores = Arrays.asList(ignoreProperties);

		assertNotNull("object must not be null", object);

		List<PropertyDescriptor> descriptors = BeanUtils.getPropertyDescriptors(object.getClass());
		if (descriptors != null)
		{
			for (PropertyDescriptor descriptor : descriptors)
			{
				if (descriptor != null && descriptor.getReadMethod() != null && !ignores.contains(descriptor.getName()))
				{
					try
					{
						assertNotNull("property " + descriptor.getName() + " must not be null",
								descriptor.getReadMethod().invoke(object, new Object[0]));
					}
					catch (AssertionError e)
					{
						throw e;
					}
					catch (Exception e)
					{
						fail("unexpected invocation exception " + e);
					}
				}
			}
		}
	}
}

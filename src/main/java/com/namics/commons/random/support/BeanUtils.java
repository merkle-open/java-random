/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * BeanUtils.
 *
 * @author aschaefer
 * @since 21.02.14 09:55
 */
public class BeanUtils {


	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	public static List<PropertyDescriptor> getPropertyDescriptors(Class beanClass) {
		try {
			BeanInfo beanInfo = null;

			beanInfo = new ExtendedBeanInfo(Introspector.getBeanInfo(beanClass));

			// This call is slow so we do it once.
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			return Arrays.asList(pds);
		} catch (IntrospectionException e) {
			return Collections.emptyList();
		}
	}
}

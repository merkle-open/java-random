/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support.reflections;

import org.reflections.vfs.Vfs;

/**
 * UrlTypeRegistration.
 *
 * @author aschaefer, Namics AG
 * @since 02.03.15 14:04
 */
public class ReflectionsInitializer {
	public static void init() {
		Vfs.addDefaultURLTypes(new JnilibIgnoringUrlType());
	}
}

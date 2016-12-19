/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * UrlGenerator.
 *
 * @author aschaefer, Namics AG
 * @since 10.09.14 10:10
 */
public class UrlGenerator implements RandomGenerator<URL> {
	@Override
	public URL random() {
		try {
			return new URL("https", RandomData.lastname().toLowerCase() + "." + RandomData.countryCode().toLowerCase(),"/test");
		} catch (MalformedURLException e) {
			return random();
		}
	}

	@Override
	public Iterable<Class<URL>> supportedTypes() {
		return Arrays.asList(URL.class);
	}
}

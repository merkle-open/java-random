/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.RandomData;
import com.namics.commons.random.generator.RandomGenerator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * UrlGenerator.
 *
 * @author aschaefer, Namics AG
 * @since 10.09.14 10:10
 */
public class UriGenerator implements RandomGenerator<URI> {
	@Override
	public URI random() {
		try {
			return new URI("https", RandomData.lastname().toLowerCase() + "." + RandomData.countryCode().toLowerCase(),"/test","dasd");
		} catch (URISyntaxException e) {
			return random();
		}
	}

	@Override
	public Iterable<Class<URI>> supportedTypes() {
		return Arrays.asList(URI.class);
	}
}

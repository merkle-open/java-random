/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import com.namics.commons.random.RandomData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Helper Class to read lorem ipsum data.
 *
 * @author aschaefer
 * @since 21.02.14 10:52
 */
public final class LoremIpsum {
	private static final Logger LOG = LoggerFactory.getLogger(LoremIpsum.class);

	public static final String TEST_DATA_FILE = "/testdata/lorem.txt";

	private static final Set<String> words = new HashSet<>();

	static {
		readLorem();
	}

	public static Set<String> getWords() {
		return words;
	}

	private static void readLorem() {
		Pattern wordPattern = Pattern.compile("\\w+");
		try (InputStream resource = RandomData.class.getResourceAsStream(TEST_DATA_FILE)) {
			new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))
					.lines()
					.map(wordPattern::matcher)
					.forEach(matcher -> {
						while (matcher.find()) {
							words.add(matcher.group());
						}
					});

		} catch (IOException e) {
			LOG.warn("Load LoremIpsum failed", e);
		}
	}

	private LoremIpsum() {
		// hide
	}
}

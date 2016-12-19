/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import com.namics.commons.random.RandomData;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper Class to read lorem ipsum data.
 *
 * @author aschaefer
 * @since 21.02.14 10:52
 */
public class LoremIpsum {
	private static final Logger LOG = LoggerFactory.getLogger(LoremIpsum.class);

	public static final String TEST_DATA_FILE = "/testdata/lorem.txt";

	protected static Set<String> words = new HashSet<String>();

	static {
		readLorem();
	}

	protected static void readLorem() {
		try {
			List<String> strings = IOUtils.readLines(RandomData.class.getResourceAsStream(TEST_DATA_FILE));
			for (String line : strings) {
				Matcher matcher = Pattern.compile("\\w+").matcher(line);
				while (matcher.find()) {
					words.add(matcher.group());
				}
			}
		} catch (IOException e) {
			LOG.warn("{}", e);
		}
	}

	public static Set<String> getWords() {
		return words;
	}
}

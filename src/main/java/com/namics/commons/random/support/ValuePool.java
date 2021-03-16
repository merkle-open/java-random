/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import com.namics.commons.random.RandomData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Helper class to read name data.
 *
 * @author aschaefer
 * @since 21.02.14 11:00
 */
public final class ValuePool {

	protected static Set<String> maleNames;
	protected static Set<String> femaleNames;
	protected static Set<String> surnames;
	protected static Set<String> firstNames;
	protected static Set<String> companies;
	protected static Set<String> manufacturers;
	protected static Set<String> countryTLDs;
	protected static Set<String> cities = new HashSet<>();
	public static String[] streetSuffix = new String[] { "street", "place", "square", "lane", "strasse", "platz", "gasse", "weg" };
	public static String[] streetSuffixesExtra = new String[] { " Street", " Place", " Square", " Lane", " Ave.", " Strasse", " Platz", };

	static {
		maleNames = readLines("/testdata/male_names.txt");
		femaleNames = readLines("/testdata/female_names.txt");
		surnames = readLines("/testdata/surnames.txt");
		companies = readLines("/testdata/companies.txt");
		manufacturers = readLines("/testdata/manufacturers.txt");
		countryTLDs = readLines("/testdata/country_tld.txt");
		firstNames = new HashSet<String>();
		firstNames.addAll(maleNames);
		firstNames.addAll(femaleNames);

		Set<String> cityInfos = readLines("/testdata/cities.txt");
		for (String cityInfo : cityInfos) {
			String[] info = cityInfo.split(";");
			cities.add(info[1]);
		}

	}

	private static Set<String> readLines(String name) {
		try (InputStream resource = RandomData.class.getResourceAsStream(name)) {
			return new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(toSet());
		} catch (IOException e) {
			return Collections.emptySet();
		}
	}

	public static Set<String> getMaleNames() {
		return maleNames;
	}

	public static Set<String> getFemaleNames() {
		return femaleNames;
	}

	public static Set<String> getSurnames() {
		return surnames;
	}

	public static Set<String> getFirstNames() {
		return firstNames;
	}

	public static Set<String> getCities() {
		return cities;
	}

	public static Set<String> getCompanies() {
		return companies;
	}

	public static Set<String> getManufacturers() {
		return manufacturers;
	}

	public static Set<String> getCountryTLDs() {
		return countryTLDs;
	}

	public static String[] getStreetSuffix() {
		return streetSuffix;
	}

	public static String[] getStreetSuffixesExtra() {
		return streetSuffixesExtra;
	}

	private ValuePool() {
		// hide
	}
}

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
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toSet;

/**
 * Helper class to read name data.
 *
 * @author aschaefer
 * @since 21.02.14 11:00
 */
public final class ValuePool {

	private static final ValuePool INTERNATIONAL = new ValuePool(true);
	private static final ValuePool COMPATIBLE = new ValuePool(false);

	private final Set<String> maleNames;
	private final Set<String> femaleNames;
	private final Set<String> surnames;
	private final Set<String> firstNames;
	private final Set<String> companies;
	private final Set<String> manufacturers;
	private final Set<String> countryTLDs;
	private final Set<String> cities;
	private final List<String> streetSuffix = unmodifiableList(asList("street", "place", "square", "lane", "strasse", "platz", "gasse", "weg"));
	private final List<String> streetSuffixesExtra = unmodifiableList(asList(" Street", " Place", " Square", " Lane", " Ave.", " Strasse", " Platz"));


	private ValuePool(boolean international) {
		if (international) {
			maleNames = readLines("/testdata/male_names_international.txt");
			femaleNames = readLines("/testdata/female_names_international.txt");
			surnames = readLines("/testdata/surnames_international.txt");
		} else {
			maleNames = readLines("/testdata/male_names.txt");
			femaleNames = readLines("/testdata/female_names.txt");
			surnames = readLines("/testdata/surnames.txt");
		}
		companies = readLines("/testdata/companies.txt");
		manufacturers = readLines("/testdata/manufacturers.txt");
		countryTLDs = readLines("/testdata/country_tld.txt");
		firstNames = new HashSet<>();
		firstNames.addAll(maleNames);
		firstNames.addAll(femaleNames);

		Set<String> cityValues = new HashSet<>();
		Set<String> cityInfos = readLines("/testdata/cities.txt");
		for (String cityInfo : cityInfos) {
			String[] info = cityInfo.split(";");
			cityValues.add(info[1]);
		}
		cities = Collections.unmodifiableSet(cityValues);

	}

	private static Set<String> readLines(String name) {
		try (InputStream resource = RandomData.class.getResourceAsStream(name)) {
			return new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(toSet());
		} catch (IOException e) {
			return Collections.emptySet();
		}
	}

	public static Set<String> getMaleNames() {
		return pool().maleNames;
	}

	public static Set<String> getFemaleNames() {
		return pool().femaleNames;
	}

	public static Set<String> getSurnames() {
		return pool().surnames;
	}

	public static Set<String> getFirstNames() {
		return pool().firstNames;
	}

	public static Set<String> getCities() {
		return pool().cities;
	}

	public static Set<String> getCompanies() {
		return pool().companies;
	}

	public static Set<String> getManufacturers() {
		return pool().manufacturers;
	}

	public static Set<String> getCountryTLDs() {
		return pool().countryTLDs;
	}

	public static List<String> getStreetSuffix() {
		return pool().streetSuffix;
	}

	public static List<String> getStreetSuffixesExtra() {
		return pool().streetSuffixesExtra;
	}

	private static ValuePool pool() {
		if ("true".equalsIgnoreCase(System.getProperty("random.names.international", "false"))
		    || "true".equalsIgnoreCase(System.getenv("RANDOM_NAMES_INTERNATIONAL"))) {
			return INTERNATIONAL;
		} else {
			return COMPATIBLE;
		}
	}
}

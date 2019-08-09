/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import com.namics.commons.random.RandomData;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helper class to read name data.
 *
 * @author aschaefer
 * @since 21.02.14 11:00
 */
public class Names {

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


	protected static Set<String> readLines(String name) {
		try {
			List<String> strings = IOUtils.readLines(RandomData.class.getResourceAsStream(name));
			HashSet<String> result = new HashSet<String>();
			result.addAll(strings);
			return result;
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
}

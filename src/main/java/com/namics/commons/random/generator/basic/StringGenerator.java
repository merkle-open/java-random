/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.generator.InformedRandomGenerator;
import com.namics.commons.random.generator.RandomGenerator;
import com.namics.commons.random.support.Utils;
import org.apache.commons.lang.RandomStringUtils;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.namics.commons.random.RandomData.*;

/**
 * IntegerGenerator.
 *
 * @author aschaefer
 * @since 20.02.14 16:58
 */
public class StringGenerator implements RandomGenerator<String>, InformedRandomGenerator<String> {


	@Override
	public String random() {
		return this.random(null);
	}

	/**
	 * Method that creates the random object of SupportedType with additional name information.
	 *
	 * @param info
	 * @return Random object of SupportedType.
	 */
	@Override
	public String random(Object... info) {
		String[] strings = Utils.objectsOfType(String.class, info);
		if (strings.length > 0) {
			StringBuilder aggregate = new StringBuilder();
			for (String item : strings) {
				aggregate.append(item.toLowerCase());
			}
			String name = aggregate.toString();

			if (name.contains("regex")) {
				return Pattern.quote(alphanumeric(randomInteger(0, 255)));
			}
			if (name.contains("guid")
			    || name.contains("id")
			    || name.contains("uuid")) {
				return UUID.randomUUID().toString();
			}
			if (name.contains("timestamp")
			    || (name.contains("date") && name.contains("time"))) {
				return DateFormat.getDateTimeInstance().format(dateTime());
			}
			if (name.contains("date")) {
				return DateFormat.getDateInstance().format(date());
			}
			if (name.contains("time")) {
				return DateFormat.getTimeInstance().format(dateTime());
			}
			if (name.contains("name")) {
				if (name.contains("user")) {
					return username();
				}
				if (name.contains("first")
				    || name.contains("given")) {
					return firstname();
				}
				if (name.contains("last")
				    || name.contains("sur")
				    || name.contains("family")) {
					return lastname();
				}
				return name();
			}
			if (name.contains("author")
			    || name.contains("writer")
			    || name.contains("director")
			    || name.contains("customer")
			    || name.contains("actor")
					) {
				return name();
			}
			if (name.contains("mail")) {
				return email();
			}
			if (name.contains("lang")) {
				return lang();
			}
			if (name.contains("country")) {
				return countryCode();
			}
			if (name.contains("title")) {
				return title(1, 5);
			}
			if (name.contains("content")
			    || name.contains("description")
			    || name.contains("article")
			    || name.contains("body")
			    || name.contains("paragraph")
			    || name.contains("text")
			    || name.contains("summary")) {
				return paragraphs(1, 4);
			}
			if (name.contains("city")
			    || name.contains("town")
			    || name.contains("ort")
			    || name.contains("locality")
			    || name.contains("dorf")
			    || name.contains("stadt")
			    || name.contains("localite")
			    || name.contains("village")) {
				return city();
			}
			if (name.contains("postal")
			    || name.contains("postcode")
			    || name.contains("zip")
			    || name.contains("plz")
			    || name.contains("postleitzahl")
					) {
				return zip();
			}
			if ((name.contains("street")
			     || name.contains("strasse")
			     || name.contains("rue"))) {

				if (!(name.contains("nr")
				      || name.contains("number")
				      || name.contains("no")
				      || name.contains("nummer"))) {
					return street();
				}
			}
			if (name.contains("tel")
			    || name.contains("phone")
			    || name.contains("mobile")) {
				return "00" + RandomStringUtils.random(11, false, true);
			}
			if (name.contains("nr")
			    || name.contains("number")
			    || name.contains("no")
			    || name.contains("nummer")) {
				return streetNumber();
			}
			if (name.contains("adress")
			    || name.contains("address")
			    || name.contains("adresse")
			    || name.contains("addresse")) {
				return address();
			}
			if (name.contains("isbn")) {
				return eanIsbn();
			}
			if (name.contains("ean")) {
				return ean13();
			}
			if (name.contains("firma")
			    || name.contains("company")
			    || name.contains("companies")
			    || name.contains("firm")
			    || name.contains("concern")
			    || name.contains("unternehmen")
					) {
				return company();
			}
			if (name.contains("hersteller")
			    || name.contains("marke")
			    || name.contains("manufacturer")
			    || name.contains("brand")) {
				return manufacturer();
			}
			if (name.contains("url")) {
				return new UrlGenerator().random().toString();
			}
			if (name.contains("uri")) {
				return new UriGenerator().random().toString();
			}
		}
		return alphanumeric(randomInteger(0, 255));
	}


	@Override
	public Iterable<Class<String>> supportedTypes() {
		return Arrays.asList(String.class);
	}

}

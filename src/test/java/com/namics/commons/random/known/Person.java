/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.known;

import java.util.Date;

/**
 * Person.
 *
 * @author aschaefer
 * @since 27.02.14 18:27
 */
public class Person {

	String language;

	String firstname;
	String lastname;

	String name;

	String email;
	String street;
	String streetnumber;
	String address;


	String zip;
	String city;
	String country;

	String description;
	String birthdate;
	Date dateOfBirth;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Person{" +
				"language='" + language + '\'' +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", street='" + street + '\'' +
				", streetnumber='" + streetnumber + '\'' +
				", zip='" + zip + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", description='" + description + '\'' +
				", birthdate='" + birthdate + '\'' +
				", dateOfBirth=" + dateOfBirth +
				'}';
	}
}

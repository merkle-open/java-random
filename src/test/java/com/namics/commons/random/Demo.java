/*
 * Copyright 2000-2016 Namics AG. All rights reserved.
 */

package com.namics.commons.random;

import org.junit.Test;

/**
 * Demo.
 *
 * @author aschaefer, Namics AG
 * @since 19.12.16 11:59
 */
public class Demo {
	public static class Person{
		private String name;
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
	}

	@Test
	public void simplePerson() throws Exception {
		Person person = RandomData.random(Person.class);
		System.out.println(person.getName()); // Leonel Bowers
	}
}

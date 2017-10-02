/*
 * Copyright 2000-2017 Namics AG. All rights reserved.
 */

package com.namics.commons.random.test;

/**
 * NoPublicConstructor.
 *
 * @author aschaefer, Namics AG
 * @since 02.10.17 14:09
 */
public class NoDefaultConstructor {

	protected String firstName;

//	protected NoPublicDefaultConstructor() {
//	}

	public NoDefaultConstructor(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}

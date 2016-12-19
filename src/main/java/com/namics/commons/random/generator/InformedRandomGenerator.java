/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator;

/**
 * InformedRandomGenerator.
 *
 * @author aschaefer
 * @since 27.02.14 17:02
 */
public interface InformedRandomGenerator<SupportedType> {
	/**
	 * Method that creates the random object of SupportedType with additional name information.
	 *
	 * @param information additional information that may help to create instances fitting more restrictions, e.g. Generic types, field names,...
	 * @return Random object od SupportedType.
	 */
	public SupportedType random(Object... information);
}

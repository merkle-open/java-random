/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator;

/**
 * Interface to be Implemented to add a custom RandomGenerator for a custom SupportedType.
 * The generator can be registered in several ways:
 * <li>automatically when placed in same package as the SupportedType</li>
 * <li>Manually by registering either class or instance directly to {@link com.namics.commons.random.RandomData#addRandomGenerator}</li>
 * <li>Manually by registering package for scanning  to {@link com.namics.commons.random.RandomData#addRandomGenerators(String)}</li>
 *
 * @param <SupportedType> Type that can be created with this Generator.
 */
public interface RandomGenerator<SupportedType> {

	/**
	 * Method that creates the random object of SupportedType.
	 *
	 * @return Random object of SupportedType.
	 */
	public SupportedType random();

	/**
	 * Method must return the actual classes of SupportedType (SupportedType.class).
	 *
	 * @return Types supported by this generator implementation.
	 */
	public Iterable<Class<SupportedType>> supportedTypes();
}

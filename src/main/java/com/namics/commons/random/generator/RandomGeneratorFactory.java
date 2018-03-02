/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator;

import com.namics.commons.random.support.reflections.ReflectionsInitializer;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Factory to manage custom {@link RandomGenerator} instances and provide easy resolving facilities.
 *
 * @author aschaefer
 * @since 20.02.14 16:07
 */
public abstract class RandomGeneratorFactory {
	private static final Logger LOG = LoggerFactory.getLogger(RandomGeneratorFactory.class);

	/**
	 * Initially only default {@link RandomGenerator}s
	 * in package <code>com.namics.commons.random.generator.basic</code> are registered.
	 */
	public static final String DEFAULT_SCAN_PACKAGE = "com.namics.commons.random.generator.basic";

	private static final Map<String, Reflections> PACKAGE_REFLECTIONS = new TreeMap<>();
	private static final Map<Class<?>, RandomGenerator> generators = new TreeMap<>(Comparator.comparing(Class::getName));

	static {
		ReflectionsInitializer.init();
		addRandomGenerators(DEFAULT_SCAN_PACKAGE);
	}

	/**
	 * Registers {@link com.namics.commons.random.generator.RandomGenerator} implementations found in the package provided.
	 *
	 * @param scanpackage package to scan for implementation of {@link RandomGenerator} to be registered.
	 */
	public static void addRandomGenerators(String scanpackage) {
		if (!PACKAGE_REFLECTIONS.containsKey(scanpackage)) {
			LOG.debug("Scan package {}", scanpackage);
			Reflections reflections = new Reflections(scanpackage);
			PACKAGE_REFLECTIONS.put(scanpackage, reflections);
			Set<Class<? extends RandomGenerator>> randomGenerators = reflections.getSubTypesOf(RandomGenerator.class);
			for (Class<? extends RandomGenerator> generatorClazz : randomGenerators) {
				addRandomGenerator(generatorClazz);
			}
		}
	}

	/**
	 * Create and register an instance of the {@link RandomGenerator} implementation.
	 *
	 * @param generatorClazz {@link RandomGenerator} implementation to register.
	 */
	public static void addRandomGenerator(Class<? extends RandomGenerator> generatorClazz) {
		try {
			if (!Modifier.isAbstract(generatorClazz.getModifiers())) {
				RandomGenerator generator = generatorClazz.newInstance();
				addRandomGenerator(generator);
			}
		} catch (InstantiationException | IllegalAccessException | NoClassDefFoundError e) {
			LOG.warn("Could not add generator {} {}", generatorClazz, e.toString());
		}
	}

	/**
	 * Register the instance of the {@link RandomGenerator} implementation.
	 *
	 * @param generator {@link RandomGenerator} implementations instance to register.
	 */
	public static void addRandomGenerator(RandomGenerator<?> generator) {
		for (Class<?> type : generator.supportedTypes()) {
			generators.put(type, generator);
		}
	}


	/**
	 * Try to resolve a generator for the requested type.
	 * Tries to load a  {@link RandomGenerator} instance of known generators.
	 * Tries to scan package of SupportedType for  {@link RandomGenerator<SupportedType>} implementations,
	 * registers them and returns the instance if an implementation is found.
	 * If no suitable {@link RandomGenerator<SupportedType>} is found, <code>null</code> is returned
	 *
	 * @param supportedType   Type to find a suitable generator for.
	 * @param <SupportedType> Type to find a suitable generator for.
	 * @return suitable generator if found, null else.
	 */
	public static <SupportedType> RandomGenerator<SupportedType> generator(Class<SupportedType> supportedType) {
		RandomGenerator resolved = generators.get(supportedType);

		if (resolved == null && supportedType != null && Class.class != supportedType && supportedType.getPackage() != null) {
			String supportedTypePackage = supportedType.getPackage().getName();
			if (!PACKAGE_REFLECTIONS.containsKey(supportedTypePackage)) {
				LOG.debug("No generator for class {}, scan its package {} for generator", supportedType, supportedTypePackage);
				addRandomGenerators(supportedTypePackage);
				resolved = generators.get(supportedType);
			}
		}
		return resolved;

	}

	/**
	 * Try to resolve a generator for the requested type.
	 * Tries to load a  {@link RandomGenerator} instance of known generators.
	 * Tries to scan package of SupportedType for  {@link RandomGenerator<SupportedType>} implementations,
	 * registers them and returns the instance if an implementation is found.
	 * If no suitable {@link RandomGenerator<SupportedType>} is found, <code>null</code> is returned
	 *
	 * @param supportedType   Name of the type to find a suitable generator for.
	 * @param <SupportedType> Type to find a suitable generator for.
	 * @return suitable generator if found, null else.
	 */
	public static <SupportedType> RandomGenerator<SupportedType> generator(String supportedType) {
		try {
			return (RandomGenerator<SupportedType>) generator(Class.forName(supportedType));
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}

/*
 * Copyright 2000-2012 Namics AG. All rights reserved.
 */

package com.namics.commons.random;

import com.googlecode.gentyref.GenericTypeReflector;
import com.namics.commons.random.generator.InformedRandomGenerator;
import com.namics.commons.random.generator.RandomGenerator;
import com.namics.commons.random.generator.RandomGeneratorFactory;
import com.namics.commons.random.support.BarcodeUtils;
import com.namics.commons.random.support.BeanUtils;
import com.namics.commons.random.support.LoremIpsum;
import com.namics.commons.random.support.Names;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static com.namics.commons.random.support.BeanUtils.makeAccessible;

/**
 * Utility class to generate random data.
 * TODO: improve documentation.
 *
 * @author aschaefer
 * @author pnueesch
 * @since 21.02.14 13:43
 */
public class RandomData {
	private static final Logger LOG = LoggerFactory.getLogger(RandomData.class);

	/**
	 * Creates a random instance of the class provided, if a suitable {@link RandomGenerator} is registered.
	 * A suitable generator can be registered in several ways:
	 * <li>automatically when placed in same package as the SupportedType</li>
	 * <li>Manually by registering either class or instance directly to {@link com.namics.commons.random.RandomData#addRandomGenerator}</li>
	 * <li>Manually by registering package for scanning  to {@link com.namics.commons.random.RandomData#addRandomGenerators(String)}</li>
	 *
	 * @param type class to create a random object for.
	 * @param info optional additional information for the object to create, e.g. a field name.
	 * @return Random instance of the class.
	 * @throws java.lang.IllegalArgumentException if no suitable {@link RandomGenerator} is registered.
	 */
	public static <T> T random(Type type, Object... info) throws IllegalArgumentException {
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type[] genericTypes = parameterizedType.getActualTypeArguments();
			Object[] information = new Object[0];
			information = ArrayUtils.addAll(information, genericTypes);
			information = ArrayUtils.addAll(information, info);
			return (T) random((Class) parameterizedType.getRawType(), information);
		} else if (type instanceof Class) {
			return (T) random((Class) type, info);
		}
		throw new IllegalArgumentException(type.toString() + " not supported, only ParameterizedType and Class are supported.");
	}

	/**
	 * Creates a random instance of the class provided, if a suitable {@link RandomGenerator} is registered.
	 * A suitable generator can be registered in several ways:
	 * <li>automatically when placed in same package as the SupportedType</li>
	 * <li>Manually by registering either class or instance directly to {@link com.namics.commons.random.RandomData#addRandomGenerator}</li>
	 * <li>Manually by registering package for scanning  to {@link com.namics.commons.random.RandomData#addRandomGenerators(String)}</li>
	 *
	 * @param clazz class to create a random object for.
	 * @param info
	 * @return Random instance of the class.
	 * @throws java.lang.IllegalArgumentException if no suitable {@link RandomGenerator} is registered.
	 */
	public static <T> T random(Class<T> clazz, Object... info) throws IllegalArgumentException {
		if (clazz == null) {
			return null;
		}

		// check for enum
		T[] enumConstants = clazz.getEnumConstants();
		if (enumConstants != null) {
			return RandomData.random(enumConstants);
		}

		RandomGenerator<T> generator = RandomGeneratorFactory.generator(clazz);
		if (generator != null) {
			if (info != null && generator instanceof InformedRandomGenerator) {
				return ((InformedRandomGenerator<T>) generator).random(info);
			}
			return generator.random();
		}
		if (Class.class != clazz) {
			if (clazz.isArray()) {
				int length = RandomData.randomInteger(1, 5);
				Object array = Array.newInstance(clazz.getComponentType(), length);
				for (int i = 0; i < length; i++) {
					Array.set(array, i, RandomData.random(clazz.getComponentType()));
				}
				return (T) array;
			} else {
				T bean = createInstance(clazz);
				populateBean(bean);
				return bean;
			}
		}
		throw new IllegalArgumentException("No random generator for type " + clazz + GENERATOR_REGISTER_INFO);

	}

	private static <T> T createInstance(Class<T> clazz) {
		try {
			Constructor<T> constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException(
					"Could not create random instance for " + clazz + ", maybe there is no default constructor available, see exception cause for details." + GENERATOR_REGISTER_INFO, e);
		} catch (NoSuchMethodException e) {
			LOG.debug("No constructor without arguments {}", e, null);
			return createWithNoDefaultConstructor(clazz);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T createWithNoDefaultConstructor(Class<T> clazz) {
		try {
			Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
			for (Constructor<T> constructor : constructors) {
				if (constructor != null) {
					Parameter[] parameters = constructor.getParameters();
					Object[] args = new Object[parameters.length];
					for (int i = 0; i < parameters.length; i++) {
						args[i] = random(parameters[i].getType(), parameters[i].getName());
					}
					constructor.setAccessible(true);
					return constructor.newInstance(args);
				}
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException(
					"Could not create random instance for " + clazz + ", maybe there is no default constructor available, see exception cause for details." + GENERATOR_REGISTER_INFO, e);
		}
		throw new IllegalArgumentException(
				"Could not create random instance for " + clazz + ", maybe there is no default constructor available, see exception cause for details." + GENERATOR_REGISTER_INFO);
	}

	/**
	 * Pickes a random value of the array / vararg provided.
	 *
	 * @param items array / vararg to pick an items from
	 * @param <I>   Item class
	 * @return Randomly picked items
	 */
	public static <I> I random(I[] items) {
		return random(Arrays.asList(items));
	}

	/**
	 * Pickes a random value of the collection provided.
	 *
	 * @param items collection to pick an items from
	 * @param <I>   Item class
	 * @return Randomly picked items
	 */
	public static <I> I random(Collection<I> items) {
		if (items == null || items.size() == 0) {
			return null;
		}
		int size = items.size();
		int random = randomInteger(0, size);
		int current = 0;
		for (I i : items) {
			if (current == random) {
				return i;
			}
			current++;
		}
		return items.iterator().next();
	}

	public static boolean randomBoolean() {
		return random(Boolean.class);
	}

	public static Integer randomInteger() {
		return random(Integer.class);
	}

	public static Long randomLong() {
		return random(Long.class);
	}

	public static float randomFloat() {
		return random(Float.class);
	}

	public static BigDecimal randomBigDecimal() {
		return random(BigDecimal.class);
	}

	public static String randomString() {
		return random(String.class);
	}

	public static Date date() {
		return random(Date.class);
	}

	public static LocalDateTime dateTime() {
		return random(LocalDateTime.class);
	}

	public static String alphabetic(int count) {
		return RandomStringUtils.randomAlphabetic(count);
	}

	public static String alphanumeric(int count) {
		return RandomStringUtils.randomAlphanumeric(count);
	}


	public static int randomInteger(int min, int max) {
		return min + RandomUtils.nextInt(max - min + 1);
	}

	public static long randomLong(int min, int max) {
		return min + RandomUtils.nextInt(max - min + 1);
	}

	public static float randomFloat0to1() {
		return RandomUtils.nextFloat();
	}

	public static float randomFloat(int min, int max) {
		return RandomUtils.nextFloat() + RandomUtils.nextInt(max - min);
	}

	public static float randomFloat(float min, float max) {
		if (Float.isInfinite(max - min)) {
			throw new IllegalArgumentException("range of float is infinty");
		}
		return min + RandomUtils.nextFloat() * (max - min);
	}

	public static double randomDouble(int min, int max) {
		return RandomUtils.nextDouble() + RandomUtils.nextInt(max - min);
	}

	public static double randomDouble(double min, double max) {
		if (Double.isInfinite(max - min)) {
			throw new IllegalArgumentException("range of double is infinty");
		}
		return min + RandomUtils.nextDouble() * (max - min);
	}


	public static String lang() {
		return languageCode();
	}


	public static String htmlParagraphs(int min, int max) {
		int count = randomInteger(min, max);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append("<p>");
			sb.append(paragraphs(1, 1));
			sb.append("</p>");
		}
		return sb.toString().trim();
	}

	public static String paragraphs(int min, int max) {
		int count = randomInteger(min, max);
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < count; j++) {
			int sentences = randomInteger(2, 10);
			for (int i = 0; i < sentences; i++) {
				sb.append(words(1, 20, false));
				sb.append(". ");
			}
			sb.append("\n");
		}
		return sb.toString().trim();
	}

	public static String words(int min, int max, boolean title) {
		int count = randomInteger(min, max);
		return words(count, title);
	}

	public static String words(int count, boolean title) {
		StringBuilder sb = new StringBuilder();
		int wordCount = 0;
		while (wordCount < count) {
			String word = random(LoremIpsum.getWords());
			if (title) {
				if (wordCount == 0 || word.length() > 3) {
					word = word.substring(0, 1).toUpperCase() + word.substring(1);
				}
			}
			sb.append(word);
			sb.append(" ");
			wordCount++;
		}
		return sb.toString().trim();
	}


	public static String languageCode() {
		return locale().getLanguage();
	}

	public static Locale locale() {
		return random(Locale.class);
	}

	public static String countryCode() {
		return random(Locale.getISOCountries());
	}


	public static String email() {
		return email(firstname(), lastname());
	}

	public static String email(String firstname, String lastname) {
		return email(firstname, lastname, lastname + "." + random(Names.getCountryTLDs()));
	}

	public static String email(String firstname, String lastname, String domain) {
		String email = firstname + "." + lastname + "@" + domain;
		return removeAccents(email).toLowerCase().replaceAll("[^A-Za-z\\.@\\-\\+]+", "");
	}

	/**
	 * Generate a Hex encoded block formatted type 4 (pseudo randomly generated) UUID.
	 *
	 * @see {@link UUID#randomUUID}
	 * @return uuid string representation.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Generate a short UUID - 22 digit base64 representation of a UUID.
	 * Details: http://www.shortguid.com
	 *
	 * @return 22 digit base64 representation of a UUID
	 */
	public static String shortGuid() {
		UUID uuid = UUID.randomUUID();
		byte[] array = toByteArray(uuid);
		return Base64.getEncoder().withoutPadding().encodeToString(array);
	}

	/**
	 * Convert UUID to 16 byte array.
	 *
	 * @param uuid uuid to get byte representation
	 * @return 16 byte array, null for null
	 */
	private static byte[] toByteArray(UUID uuid) {
		if (uuid == null) {
			return null;
		}
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		return bb.array();
	}

	public static String firstname() {
		return random(Names.getFirstNames());
	}

	public static String firstnameMale() {
		return random(Names.getMaleNames());
	}

	public static String firstnameFemale() {
		return random(Names.getFemaleNames());
	}

	public static String lastname() {
		return random(Names.getSurnames());
	}

	public static String username() {
		return removeAccents(firstname().toLowerCase()) + randomInteger(0, 9999);
	}

	public static String name() {
		return firstname() + " " + lastname();
	}

	public static String nameMale() {
		return firstnameMale() + " " + lastname();
	}

	public static String nameFemale() {
		return firstnameFemale() + " " + lastname();
	}

	public static String street() {
		return randomInteger(0, 1) == 0 ? lastname() + RandomData.random(Names.getStreetSuffix()) : name() + RandomData.random(Names.getStreetSuffixesExtra());
	}

	public static String streetNumber() {
		return Integer.toString(randomInteger(1, 999)) + (randomInteger(0, 4) == 0 ? alphabetic(1) : "");
	}

	public static String address() {
		return street() + " " + streetNumber();
	}

	public static String city() {
		return random(Names.getCities());
	}

	public static String zipCH() {
		return Integer.toString(randomInteger(1000, 9999));
	}

	public static String zipDE() {
		return String.format("%05d", randomInteger(0, 99999));
	}

	public static String zip() {
		final int type = randomInteger(0, 1);
		switch (type) {
		case 0:
			return zipCH();
		default:
			return zipDE();
		}
	}

	public static String company() {
		return random(Names.getCompanies());
	}

	public static String manufacturer() {
		return random(Names.getManufacturers());
	}

	public static String title(int count) {
		return words(count, count, true);
	}

	public static String title(int min, int max) {
		return words(min, max, true);
	}

	public static String words(int count) {
		return words(count, count, false);
	}

	public static String words(int min, int max) {
		return words(min, max, false);
	}

	public static String isbn10() {
		StringBuilder isbn10 = new StringBuilder();
		isbn10.append(randomInteger(10, 99)); // group (2 digits)
		isbn10.append(randomInteger(1000, 9999)); // publisher (4 digits)
		isbn10.append(randomInteger(100, 999)); // title (3 digits)
		isbn10.append(BarcodeUtils.createIsbn10CheckSumDigit(isbn10.toString())); // checksum (1 digit)
		return isbn10.toString();
	}

	public static String eanIsbn() {
		StringBuilder eanIsbn = new StringBuilder();
		eanIsbn.append("978"); // bookland (3 digits)
		eanIsbn.append(randomInteger(10, 99)); // group (2 digits)
		eanIsbn.append(randomInteger(1000, 9999)); // publisher (4 digits)
		eanIsbn.append(randomInteger(100, 999)); // title (3 digits)
		eanIsbn.append(BarcodeUtils.createEanCheckSumDigit(eanIsbn.toString())); // checksum (1 digit)
		return eanIsbn.toString();
	}

	public static String ean13() {
		StringBuilder ean13 = new StringBuilder();
		ean13.append(randomInteger(100, 999)); // country (3 digits)
		ean13.append(randomInteger(1000, 9999)); // company (4 digits)
		ean13.append(randomInteger(10000, 99999)); // item (5 digits)
		ean13.append(BarcodeUtils.createEanCheckSumDigit(ean13.toString())); // checksum (1 digit)
		return ean13.toString();
	}

	public static String ean8() {
		StringBuilder ean8 = new StringBuilder();
		ean8.append(randomInteger(100, 999)); // country (3 digits)
		ean8.append(randomInteger(1000, 9999)); // item (4 digits)
		ean8.append(BarcodeUtils.createEanCheckSumDigit(ean8.toString())); // checksum (1 digit)
		return ean8.toString();
	}

	/**
	 * Populates a bean instance with random values for properties of a supported types of RandomGenerators.
	 *
	 * @param bean bean to be populated
	 * @param <T>  Type of the bean to be populated
	 * @return populated bean, the original object is populated, so the returned object is the same instance as provided as parameter.
	 * @see com.namics.commons.random.generator.RandomGenerator
	 * @see RandomData#random(Class, Object...)
	 */
	public static <T> T populateBean(T bean) {
		Class<?> beanClass = bean.getClass();
		List<PropertyDescriptor> propertyDescriptors = BeanUtils.getPropertyDescriptors(beanClass);
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			Class<?> propertyType = descriptor.getPropertyType();

			if (Class.class != propertyType) {
				try {
					Method writeMethod = descriptor.getWriteMethod();
					if (writeMethod != null) {
						LOG.info("Property {} ", descriptor);
						String propertyName = descriptor.getName();

						Type[] genericParameterTypes = writeMethod.getGenericParameterTypes();
						Type genericFieldType = genericParameterTypes[0];
						Object random;
						if (beanClass == propertyType) {
							random = bean;
						} else if (genericFieldType instanceof TypeVariable) {
							Type[] exactGenericTypes = GenericTypeReflector.getExactParameterTypes(writeMethod, beanClass);
							random = random(exactGenericTypes[0], propertyName);
						} else if (genericFieldType instanceof ParameterizedType) {
							random = random(genericFieldType, propertyName);
						} else {
							random = random(propertyType, propertyName);
						}
						if (random != null) {
							makeAccessible(writeMethod);
							writeMethod.invoke(bean, random);
						}
					}
				} catch (Throwable e) {
					LOG.info("Property {} could not be processed", descriptor, e);
				}
			}
		}
		return bean;
	}

	/**
	 * Creates a HashMap, key and value class must be provided, since Generics are unknown at runtime.
	 *
	 * @param keyClass   class of key
	 * @param valueClass class of value
	 * @return Map populated with random values.
	 */

	public static Map map(Class<? extends Comparable> keyClass, Class<?> valueClass) {
		Map map = new HashMap();
		for (int i = 0; i < 10; i++) {
			Object key = random(keyClass);
			Object value = random(valueClass);
			if (key != null && value != null) {
				map.put(key, value);
			}
		}
		return map;
	}

	public static String removeAccents(final String value) {
		if (value == null) {
			return null;
		}
		String result = Normalizer.normalize(value, Normalizer.Form.NFD);
		result = result.replaceAll("\\p{M}", "");
		return result;
	}


	/**
	 * Register the instance of the {@link RandomGenerator} implementation.
	 *
	 * @param generator {@link RandomGenerator} implementations instance to register.
	 */
	public static void addRandomGenerator(RandomGenerator generator) {
		RandomGeneratorFactory.addRandomGenerator(generator);
	}

	/**
	 * Create and register an instance of the {@link RandomGenerator} implementation.
	 *
	 * @param generatorClazz {@link RandomGenerator} implementation to register.
	 */
	public static void addRandomGenerator(Class<? extends RandomGenerator> generatorClazz) {
		RandomGeneratorFactory.addRandomGenerator(generatorClazz);
	}

	/**
	 * Registers {@link com.namics.commons.random.generator.RandomGenerator} implementations found in the package provided.
	 *
	 * @param scanpackage package to scan for implementation of {@link RandomGenerator} to be registered.
	 */
	public static void addRandomGenerators(String scanpackage) {
		RandomGeneratorFactory.addRandomGenerators(scanpackage);
	}

	private static final String GENERATOR_REGISTER_INFO = ".\nA suitable generator can be registered in several ways:\n"
	                                                      + "\t- automatically when placed in same package as the SupportedType\n"
	                                                      + "\t- Manually by registering either class or instance directly toRandomData.addRandomGenerator\n"
	                                                      + "\t- Manually by registering package for scanning  to RandomData.addRandomGenerators";
}

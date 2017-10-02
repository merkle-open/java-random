/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random;

import com.namics.commons.random.known.Person;
import com.namics.commons.random.known.TestAttributeKnown;
import com.namics.commons.random.known.TestBean;
import com.namics.commons.random.test.ComparableCollectionsTestBean;
import com.namics.commons.random.test.GenericChild;
import com.namics.commons.random.test.GenericGrandDaughter;
import com.namics.commons.random.test.GenericGrandSon;
import com.namics.commons.random.test.GenericsTestBean;
import com.namics.commons.random.test.GenericsWrappedTestBean;
import com.namics.commons.random.test.NoDefaultConstructor;
import com.namics.commons.random.test.NoPublicDefaultConstructor;
import com.namics.commons.random.test.NonComparable;
import com.namics.commons.random.test.RecursionTestBean;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import static com.namics.commons.random.RandomData.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * TestTestDataHelper.
 *
 * @author aschaefer
 * @since 31.01.14 16:21
 */
public class RandomDataTest {

	@Test
	public void testParagraphs() {
		assertNotNull(RandomData.paragraphs(0, 100));
	}

	@Test
	public void testPerson() {
		Person person = RandomData.random(Person.class);
		assertNotNull(person);
		Assert.assertPropertiesNotNull(person);
	}

	@Test
	public void testRandomInteger() throws Exception {
		Integer random = RandomData.random(Integer.class);
		assertNotNull(random);
	}

	@Test
	public void testRandomDateTime() {
		assertNotNull(RandomData.dateTime());
	}

	@Test
	public void testPopulateBean() {
		TestBean bean = new TestBean();
		RandomData.populateBean(bean);
		Assert.assertPropertiesNotNull(bean);
	}

	@Test
	public void testGetRandom() {
		assertEquals("test", RandomData.random(Arrays.asList("test")));
	}

	@Test
	public void testRandomForClassExists() {
		assertNotNull(RandomData.random(TestAttributeKnown.class));
	}

	@Test
	public void testZipDE() {
		String zip;
		do {
			zip = RandomData.zipDE();
			assertEquals(5, zip.length());
		} while (!zip.startsWith("0"));
	}

	@Test
	public void testRandomCreateForClassNotExists() {
		assertNotNull(RandomData.random(TestBean.class));
	}

	
	@Test
	public void testBasicGenetator() throws Exception {
		List<Class<? extends Serializable>> classes = Arrays.asList(BigDecimal.class, Boolean.class, Date.class, DateTime.class, Double.class, Float.class, Integer.class, Locale.class, Long.class, String.class);
		for (Class<? extends Serializable> aClass : classes) {
			assertEquals(aClass, RandomData.random(aClass).getClass());
		}
	}


	@Test
	public void testArray() throws Exception {
		int[] random = RandomData.random(int[].class, true);
		assertNotNull(random);
		assertTrue(random.length > 0);

		byte[] array2 = RandomData.random(byte[].class, true);
		assertNotNull(array2);
		assertTrue(array2.length > 0);
	}


	@Test
	public void testGenericFieldTypes() {

		GenericsTestBean listBean = RandomData.random(GenericsTestBean.class);
		assertNotNull(listBean);

		assertNotNull(listBean.getNames());
		assertTrue(listBean.getNames().size() > 0);
		for (String string : listBean.getNames()) {
			assertTrue(string instanceof String);
			assertTrue(string.contains(" "));
		}

		assertNotNull(listBean.getCompanies());
		assertTrue(listBean.getCompanies().size() > 0);
		for (Map.Entry<String, Integer> entry : listBean.getCompanies().entrySet()) {
			assertTrue(entry.getKey() instanceof String);
			assertTrue(entry.getValue() instanceof Integer);
		}

		assertNotNull(listBean.getLocales());
		assertTrue(listBean.getLocales().size() > 0);
		for (String locale : listBean.getLocales()) {
			assertTrue(locale instanceof String);
		}
	}

	@Test
	public void testGenericWrappedFieldTypes() {

		GenericsWrappedTestBean listBean = RandomData.random(GenericsWrappedTestBean.class);
		assertNotNull(listBean);

		assertNotNull(listBean.getNames());
		assertTrue(listBean.getNames().size() > 0);
		for (List<String> strings : listBean.getNames()) {
			for (String s : strings) {
				assertTrue(s instanceof String);
				assertTrue(s.contains(" "));
			}
		}

		assertNotNull(listBean.getWrappedMap());
		assertTrue(listBean.getWrappedMap().size() > 0);
		for (Map<String, String> subMap : listBean.getWrappedMap().values()) {
			for (Map.Entry<String, String> entry : subMap.entrySet()) {
				assertTrue(entry.getKey() instanceof String);
				assertTrue(entry.getValue() instanceof String);
			}
		}
	}

	@Test
	public void testMap() {
		assertTrue(RandomData.random(Map.class) instanceof Map);
		assertTrue(RandomData.random(SortedMap.class) instanceof SortedMap);
		assertTrue(RandomData.random(TreeMap.class) instanceof TreeMap);
	}

	@Test
	public void testSet() {
		assertTrue(RandomData.random(Set.class) instanceof Set);
		assertTrue(RandomData.random(SortedSet.class) instanceof SortedSet);
		assertTrue(RandomData.random(TreeSet.class) instanceof TreeSet);
	}


	@Test
	public void testList() {
		assertTrue(RandomData.random(List.class) instanceof List);
		assertTrue(RandomData.random(ArrayList.class) instanceof ArrayList);
	}

	@Test
	public void testDirectRecursion() {
		assertThat(RandomData.random(RecursionTestBean.class), allOf(
				isA(RecursionTestBean.class),
				hasProperty("recursive", isA(RecursionTestBean.class))
		                                                            ));
	}

	@Test
	public void testGenericProperties() throws Exception {
		assertThat(RandomData.random(GenericChild.class), allOf(
				isA(GenericChild.class),
				hasProperty("id", isA(String.class))
				));
	}
	@Test
	public void testGenericPropertiesHierarchie() throws Exception {
		assertThat(RandomData.random(GenericGrandSon.class), allOf(
				isA(GenericGrandSon.class),
				hasProperty("id", isA(String.class))
				));
	}
	@Test
	public void testGenericPropertiesHierarchieBetween() throws Exception {
		assertThat(RandomData.random(GenericGrandDaughter.class), allOf(
				isA(GenericGrandDaughter.class),
				hasProperty("id", isA(String.class))
				));
	}

	@Test
	public void testNonComparableCollections(){
		ComparableCollectionsTestBean actual = RandomData.random(ComparableCollectionsTestBean.class);
		assertThat(actual,hasProperty("map", instanceOf(Map.class)));
		actual.getMap().put("test", new NonComparable());
		actual.getList().add(new NonComparable());
		actual.getSet().add(new NonComparable());
	}

	@Test
	public void testEmail() throws Exception {
		assertThat(email("Hans!","O'Neal","dasd'#.com"),equalTo("hans.oneal@dasd.com"));

	}

	@Test
	public void testRemoveAccents() throws Exception {
		assertThat(removeAccents("André"), equalTo("Andre"));
		assertThat(removeAccents("Schäfer"), equalTo("Schafer"));
		assertThat(removeAccents(""), equalTo(""));
		assertThat(removeAccents(null), nullValue());
	}

	@Test
	public void testNoPublicDefaultConstructor() {
		NoPublicDefaultConstructor random = RandomData.random(NoPublicDefaultConstructor.class);
		assertNotNull(random);
		Assert.assertPropertiesNotNull(random);
	}

	@Test
	public void testNoDefaultConstructor() {
		NoDefaultConstructor random = RandomData.random(NoDefaultConstructor.class);
		assertNotNull(random);
		Assert.assertPropertiesNotNull(random);
	}


}

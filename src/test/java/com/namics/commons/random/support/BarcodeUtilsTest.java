/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * BarcodeUtilsTest.
 *
 * @author pnueesch
 * @since 08.05.14 13:00
 */
public class BarcodeUtilsTest {

	@Test
	public void testCheckDigitEan8() throws Exception {
		String checkDigit = BarcodeUtils.createEanCheckSumDigit("8376273");
		assertEquals("4", checkDigit);
		checkDigit = BarcodeUtils.createEanCheckSumDigit("8272736");
		assertEquals("9", checkDigit);
		checkDigit = BarcodeUtils.createEanCheckSumDigit("2823737");
		assertEquals("2", checkDigit);
	}

	@Test
	public void testCheckDigitEan13() throws Exception {
		String checkDigit = BarcodeUtils.createEanCheckSumDigit("827368376273");
		assertEquals("8", checkDigit);
		checkDigit = BarcodeUtils.createEanCheckSumDigit("923927277289");
		assertEquals("1", checkDigit);
		checkDigit = BarcodeUtils.createEanCheckSumDigit("921837743737");
		assertEquals("9", checkDigit);
	}
	
	@Test
	public void testCheckDigitInvalidEan() throws Exception {
		String checkDigit = BarcodeUtils.createEanCheckSumDigit("82736b376271");
		assertNull(checkDigit);
	}
	
	@Test
	public void testCheckDigitIsbn10() throws Exception {
		String checkDigit = BarcodeUtils.createIsbn10CheckSumDigit("386680192");
		assertEquals("0", checkDigit);
		checkDigit = BarcodeUtils.createIsbn10CheckSumDigit("368008783");
		assertEquals("7", checkDigit);
		checkDigit = BarcodeUtils.createIsbn10CheckSumDigit("668008783");
		assertEquals("X", checkDigit);
	}
	
	@Test
	public void testCheckDigitInvalidIsbn() throws Exception {
		String checkDigit = BarcodeUtils.createIsbn10CheckSumDigit("82397329g");
		assertNull(checkDigit);
	}

}

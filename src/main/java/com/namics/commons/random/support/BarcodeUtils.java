/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

/**
 * BarcodeUtils.
 * 
 * @author pnueesch
 * @since 08.05.14 13:00
 */
public class BarcodeUtils {

	public static String createEanCheckSumDigit(String value) {
		try {
			int total = 0;
			for (int i = 0; i < value.length(); i++) {
				int digit = Integer.parseInt(value.substring(value.length() - i - 1, value.length() - i));
				total += digit * ((i % 2 == 1) ? 1 : 3);
			}
			return String.valueOf(10 - (total % 10));
		} catch (NumberFormatException nfe) {
			return null;
		}
	}

	public static String createIsbn10CheckSumDigit(String value) {
		try {
			int total = 0;
			for (int i = 1; i <= value.length(); i++) {
				int digit = Integer.parseInt(value.substring(i - 1, i));
				total += i * digit;
			}
			int modulo11 = total % 11;
			return (modulo11 != 10) ? String.valueOf(modulo11) : "X";
		} catch (NumberFormatException nfe) {
			return null;
		}
	}
}

/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.support;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Utils.
 *
 * @author aschaefer, Namics AG
 * @since 20.08.14 14:26
 */
public class Utils {

	/**
	 * Filter values for objects of the specified type.
	 *
	 * @param clazz       Object type to get
	 * @param information objects to filter
	 * @param <T>         Generic Type for Items in filtered result array
	 * @return array with type and objects of T
	 */
	public static <T> T[] objectsOfType(Class<T> clazz, Object... information) {
		ArrayList<T> items = new ArrayList<>();
		if (information != null && information.length > 0) {
			for (Object info : information) {
				if (info != null && clazz.isAssignableFrom(info.getClass())) {
					items.add((T) info);
				}
			}
		}
		T[] result = (T[]) Array.newInstance(clazz, items.size());
		return items.toArray(result);
	}

}

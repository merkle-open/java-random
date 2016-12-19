/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.test;

import java.util.List;
import java.util.Map;

/**
 * ListBean.
 *
 * @author aschaefer, Namics AG
 * @since 20.08.14 13:32
 */
public class GenericsWrappedTestBean {
	private List<List<String>> names;

	private Map<String,Map<String,String>> wrappedMap;

	public List<List<String>> getNames() {
		return names;
	}

	public void setNames(List<List<String>> names) {
		this.names = names;
	}

	public GenericsWrappedTestBean names(List<List<String>> names) {
		setNames(names);
		return this;
	}

	public Map<String, Map<String, String>> getWrappedMap() {
		return wrappedMap;
	}

	public void setWrappedMap(Map<String, Map<String, String>> wrappedMap) {
		this.wrappedMap = wrappedMap;
	}

	public GenericsWrappedTestBean wrappedMap(Map<String, Map<String, String>> wrappedMap) {
		setWrappedMap(wrappedMap);
		return this;
	}
}

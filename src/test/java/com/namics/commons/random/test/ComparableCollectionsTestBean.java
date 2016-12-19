/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.commons.random.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ComparableCollectionsTestBean.
 *
 * @author aschaefer, Namics AG
 * @since 31.07.15 13:37
 */
public class ComparableCollectionsTestBean {
	private Map<String,NonComparable> map;
	private List<NonComparable> list;
	private Set<NonComparable> set;

	public Map<String, NonComparable> getMap() {
		return map;
	}

	public void setMap(Map<String, NonComparable> map) {
		this.map = map;
	}

	public List<NonComparable> getList() {
		return list;
	}

	public void setList(List<NonComparable> list) {
		this.list = list;
	}

	public Set<NonComparable> getSet() {
		return set;
	}

	public void setSet(Set<NonComparable> set) {
		this.set = set;
	}
}

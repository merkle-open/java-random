/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ListBean.
 *
 * @author aschaefer, Namics AG
 * @since 20.08.14 13:32
 */
public class GenericsTestBean {
	private String id;
	private List<String> names;
	private Map<String,Integer> companies;
	private Set<String> locales;

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public GenericsTestBean names(List<String> names) {
		setNames(names);
		return this;
	}

	public Map<String, Integer> getCompanies() {
		return companies;
	}

	public void setCompanies(Map<String, Integer> companies) {
		this.companies = companies;
	}

	public GenericsTestBean companies(Map<String, Integer> companies) {
		setCompanies(companies);
		return this;
	}

	public Set<String> getLocales() {
		return locales;
	}

	public void setLocales(Set<String> locales) {
		this.locales = locales;
	}

	public GenericsTestBean locales(Set<String> locales) {
		setLocales(locales);
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GenericsTestBean id(String id) {
		setId(id);
		return this;
	}
}

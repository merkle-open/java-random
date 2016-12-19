/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.commons.random.test;

/**
 * RecursionTestBean.
 *
 * @author aschaefer, Namics AG
 * @since 02.03.15 13:33
 */
public class RecursionTestBean {

	private RecursionTestBean recursive;

	public RecursionTestBean getRecursive() {
		return recursive;
	}

	public void setRecursive(RecursionTestBean recursive) {
		this.recursive = recursive;
	}
}

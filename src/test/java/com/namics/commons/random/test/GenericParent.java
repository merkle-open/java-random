/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.commons.random.test;

import java.io.Serializable;

/**
 * GenericParent.
 *
 * @author aschaefer, Namics AG
 * @since 09.07.15 09:26
 */
public class GenericParent<ENTITY, ID extends Serializable> {
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}


	public ENTITY id(ID id) {
		setId(id);
		return self();
	}

	public ENTITY self() {
		return (ENTITY) this;
	}
}

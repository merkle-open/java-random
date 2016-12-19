/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */

package com.namics.commons.random.test;

import java.io.Serializable;

/**
 * GenericChild.
 *
 * @author aschaefer, Namics AG
 * @since 09.07.15 09:27
 */
public class GenericSon<ENTITY extends GenericSon, ID extends Serializable> extends GenericParent<ENTITY, ID> {

}

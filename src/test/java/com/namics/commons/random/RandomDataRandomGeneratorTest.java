/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random;

import com.namics.commons.random.known.TestAttributeKnown;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * RandomDataRandomGeneratorTest.
 *
 * @author aschaefer
 * @since 20.02.14 15:39
 */
class RandomDataRandomGeneratorTest {

	@Test
	void testFullStack(){
		TestBean testBean = new TestBean();
		RandomData.populateBean(testBean);
		assertNotNull(testBean.getAttribute());
		assertNotNull(testBean.getAttribute().getTest());
	}

	public static class TestBean {
		private TestAttributeKnown attribute;

		public TestAttributeKnown getAttribute() {
			return attribute;
		}

		public void setAttribute(TestAttributeKnown attribute) {
			this.attribute = attribute;
		}
	}

}

/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random;

import com.namics.commons.random.known.TestAttributeKnown;
import org.junit.Test;

/**
 * RandomDataRandomGeneratorTest.
 *
 * @author aschaefer
 * @since 20.02.14 15:39
 */
public class RandomDataRandomGeneratorTest {

	@Test
	public void testFullStack(){
		TestBean testBean = new TestBean();
		RandomData.populateBean(testBean);
		Assert.assertNotNull(testBean.getAttribute());
		Assert.assertNotNull(testBean.getAttribute().getTest());
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

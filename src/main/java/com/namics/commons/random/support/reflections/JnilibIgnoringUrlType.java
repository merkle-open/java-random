/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */
package com.namics.commons.random.support.reflections;

import java.net.URL;

public class JnilibIgnoringUrlType extends IgnoringUrlType {
    @Override
    public boolean matches(URL url) throws Exception {
        return url.getPath().endsWith("jnilib");
    }
}

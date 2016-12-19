/*
 * Copyright 2000-2015 Namics AG. All rights reserved.
 */
package com.namics.commons.random.support.reflections;

import org.reflections.vfs.Vfs;

import java.net.URL;
import java.util.ArrayList;

public abstract class IgnoringUrlType implements Vfs.UrlType {
    @Override
    public Vfs.Dir createDir(URL url) throws Exception {
        return new Vfs.Dir() {
            @Override
            public String getPath() {
                return "";
            }

            @Override
            public Iterable<Vfs.File> getFiles() {
                return new ArrayList<Vfs.File>();
            }

            @Override
            public void close() {
                //do nothing
            }
        };
    }
}

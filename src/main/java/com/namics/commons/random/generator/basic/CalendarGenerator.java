/*
 * Copyright 2000-2014 Namics AG. All rights reserved.
 */

package com.namics.commons.random.generator.basic;

import com.namics.commons.random.generator.RandomGenerator;

import java.util.Arrays;
import java.util.Calendar;

/**
 * CalendarGenerator.
 *
 * @author sbaur
 * @since 03.03.15 08:58
 */
public class CalendarGenerator implements RandomGenerator<Calendar>
    {

        @Override
        public Calendar random()
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new DateGenerator().random());
            return calendar;
        }

        @Override
        public Iterable<Class<Calendar>> supportedTypes()
        {
            return Arrays.asList(Calendar.class);
        }

    }

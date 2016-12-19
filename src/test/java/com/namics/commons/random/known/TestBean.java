package com.namics.commons.random.known;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

public class TestBean
	{
		private String stringValue;
		private Integer integerValue;
		private Long longValue;
		private Date dateValue;
		private DateTime dateTimeValue;
		private Calendar calendarValue;
		private Boolean bool;


		public Boolean getBool()
		{
			return bool;
		}

		public void setBool(Boolean bool)
		{
			this.bool = bool;
		}

		public String getStringValue()
		{
			return stringValue;
		}

		public void setStringValue(String stringValue)
		{
			this.stringValue = stringValue;
		}

		public Integer getIntegerValue()
		{
			return integerValue;
		}

		public void setIntegerValue(Integer integerValue)
		{
			this.integerValue = integerValue;
		}

		public Long getLongValue()
		{
			return longValue;
		}

		public void setLongValue(Long longValue)
		{
			this.longValue = longValue;
		}

		public Date getDateValue()
		{
			return dateValue;
		}

		public void setDateValue(Date dateValue)
		{
			this.dateValue = dateValue;
		}

		public DateTime getDateTimeValue()
		{
			return dateTimeValue;
		}

		public void setDateTimeValue(DateTime dateTimeValue)
		{
			this.dateTimeValue = dateTimeValue;
		}

        public Calendar getCalendarValue()
        {
            return calendarValue;
        }

        public void setCalendarValue(Calendar calendarValue)
        {
            this.calendarValue = calendarValue;
        }
    }

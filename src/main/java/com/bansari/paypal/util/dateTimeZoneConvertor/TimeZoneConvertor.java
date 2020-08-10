package com.bansari.paypal.util.dateTimeZoneConvertor;

import static com.bansari.paypal.constant.UserTransactionConstants.TRANSACTION_DATE_TIME_FORMAT;
import static com.bansari.paypal.constant.UserTransactionConstants.UTC_TIMEZONE;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneConvertor {

	@SuppressWarnings("deprecation")
	public static Date localToGMT(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TRANSACTION_DATE_TIME_FORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE));
		Date gmt = new Date(sdf.format(date));
		return gmt;
	}

	public static Date gmttoLocalDate(Date date) {

		String timeZone = Calendar.getInstance().getTimeZone().getID();
		Date local = new Date(date.getTime() + TimeZone.getTimeZone(timeZone).getOffset(date.getTime()));
		return local;
	}
}

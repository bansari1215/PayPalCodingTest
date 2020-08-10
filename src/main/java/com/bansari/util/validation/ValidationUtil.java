package com.bansari.util.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtil {

	public static boolean isEmailIdValid(String emailId) {

		if (emailId == null) {
			return false;
		}
		String regexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		return emailId.matches(regexEmail);
	}

	public static boolean isYearValid(String year) {

		String regexYear = "^\\d{4}$";
		return year.matches(regexYear);
	}

	public static boolean isMonthValid(String month) {

		String regexMonth = "^\\d{2}$";
		if (month.matches(regexMonth)) {
			int monthInt = Integer.parseInt(month);
			if (monthInt > 0 && monthInt <= 12) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDayValid(String day) {

		String regexDay = "^\\d{2}$";
		if (day.matches(regexDay)) {
			int dayInt = Integer.parseInt(day);
			if (dayInt > 0 && dayInt <= 31) {
				return true;
			}
		}
		return false;
	}

	public static boolean isHourValid(String hour) {

		String regexHour = "^\\d{2}$";
		if (hour.matches(regexHour)) {
			int hourInt = Integer.parseInt(hour);
			if (hourInt > 0 && hourInt <= 24) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDateValid(String dateStr, String dateFormat) {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}

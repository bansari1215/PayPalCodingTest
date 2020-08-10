package com.bansari.util.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserTransDateBaseValidation {

	public static Map<Boolean, Map<String, String>> getUserTransDetailsByDate_RequestParamValidation(String userId,
			String transactionType, String year, String month, String day, String hour) {

		Map<String, String> validations = new HashMap<>();
		Boolean validationFlag = true;

		if (!ValidationUtil.isEmailIdValid(userId)) {
			validations.put("UserId", "Please enter valid UserId");
			validationFlag = false;
		}

		if (year != null && !ValidationUtil.isYearValid(year)) {
			validations.put("Year", "Please enter valid Year in 'yyyy' format");
			validationFlag = false;
		}

		if (month != null && !ValidationUtil.isMonthValid(month)) {
			validations.put("Month", "Please enter valid Month in 'MM' format");
			validationFlag = false;
		}

		if (day != null && !ValidationUtil.isDayValid(day)) {
			validations.put("Day", "Please enter valid Day in 'dd' format");
			validationFlag = false;
		}

		if (hour != null && !ValidationUtil.isHourValid(hour)) {
			validations.put("Hour", "Please enter valid Hour in 'hh' format");
			validationFlag = false;
		}

		if (transactionType != null && transactionType.equals("")) {
			validations.put("Transaction Type", "Please enter valid Transaction type");
			validationFlag = false;
		}

		Map<Boolean, Map<String, String>> validationResult = new HashMap<>();
		validationResult.put(validationFlag, validations);

		return validationResult;
	}

}

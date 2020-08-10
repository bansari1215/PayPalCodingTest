package com.bansari.paypal.resource;

import static com.bansari.paypal.constant.UserTransactionConstants.TRANSACTION_DATE_FORMAT;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.service.TransactionBO;
import com.bansari.paypal.service.UserTransactionBO;
import com.bansari.paypal.util.validation.UserTransDateBaseValidation;
import com.bansari.paypal.util.validation.ValidationUtil;

@RestController
@Validated
@RequestMapping("/paypal/userTransactions")
public class HomeResource {

	@Autowired
	private UserTransactionBO userTransactionBO;

	@Autowired
	private TransactionBO transactionBO;

	@GetMapping
	public ResponseEntity<String> invalidURI() {
		return new ResponseEntity<>("Please enter valid input.", HttpStatus.BAD_REQUEST);
	}

	@GetMapping(params = "date")
	public ResponseEntity<Map<String, Object>> getAllUserTransDetailsByDate(
			@RequestParam(name = "date") String transactionDate) {

		Map<String, Object> result = new HashMap<>();

		if (ValidationUtil.isDateValid(transactionDate, TRANSACTION_DATE_FORMAT)) {
			result.put("UserTransaction", userTransactionBO.getUserTransDetailsByDate(transactionDate));
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			result.put("Transaction Date", "Please enter valid transaction date in 'yyyy-MM-dd' format");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(params = "user")
	public ResponseEntity<Map<String, Object>> getAllUserTransDetailsByUserAndTrans(
			@RequestParam(name = "user") String userId, @RequestParam(required = false) String year,
			@RequestParam(required = false) String month, @RequestParam(required = false) String day,
			@RequestParam(required = false) String hour, @RequestParam(required = false) String transactionType)
			throws ParseException {

		Map<String, Object> result = new HashMap<>();

		Map<Boolean, Map<String, String>> validationResult = new HashMap<>();
		validationResult = UserTransDateBaseValidation.getUserTransDetailsByDate_RequestParamValidation(userId,
				transactionType, year, month, day, hour);

		Transaction transaction = null;

		if(transactionType != null) {
			transaction = getTransactionByTransactionType(transactionType);
			if (transaction != null && transaction.getTransactionId() == null) {
				if(validationResult.containsKey(false)) {
					validationResult.get(false).put("Transaction Type", "Please enter valid Transaction type");
				}else {
					validationResult.remove(true);
					Map<String, String> validations = new HashMap<>();
					validations.put("Transaction Type", "Please enter valid Transaction type");
					validationResult.put(false, validations);
				}
			}
		}
		
		if (validationResult.containsKey(false)) {
			result.put("UserTransaction", validationResult.get(false));
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		year = year == null ? "%" : year;
		month = month == null ? "%" : month;
		day = day == null ? "%" : day;
		hour = hour == null ? "%" : hour;

		User user = new User();
		user.setUserId(userId);
		result.put("UserTransaction",
				userTransactionBO.getUserTransDetailsByUserAndDateTimeAndTransType(user, transaction, year, month, day, hour));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public Transaction getTransactionByTransactionType(String transactionType) {
		return transactionBO.getTransactionByTransactionType(transactionType);
	}
}

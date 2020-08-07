package com.bansari.paypal.resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bansari.paypal.dto.TransactionDTO;
import com.bansari.paypal.dto.UserTransactionDTO;
import com.bansari.paypal.dto.UserTransactionDateDTO;
import com.bansari.paypal.entityDtoConvertor.TransactionConvertor;
import com.bansari.paypal.entityDtoConvertor.UserTransactionConvertor;
import com.bansari.paypal.entityDtoConvertor.UserTransactionDateConvertor;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.model.UserTransaction;
import com.bansari.paypal.service.TransactionBO;
import com.bansari.paypal.service.UserTransactionBO;

@RestController
@RequestMapping("/paypal")
public class HomeResource {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserTransactionBO userTransactionBO;

	@Autowired
	private TransactionBO transactionBO;

	@Autowired
	private UserTransactionConvertor userTransactionConvertor;
	
	@Autowired
	private UserTransactionDateConvertor userTransactionDateConvertor;
	
	@Autowired
	private TransactionConvertor transactionConvertor;

	/**
	 * Actual [1,2,3] Map<String, Object> trythis->{UserTransactions : [ ]}
	 * iferror->{UserTransaction: "Transaction Date missing"}
	 */
	@GetMapping("/userTransactions/byDate/{transactionDate}")
	public Map<String, Object> getUserTransDetailsByDate(@PathVariable String transactionDate) {

		Map<String, Object> result = new HashMap<>();

		if (transactionDate != null) {
			if (isDateValid(transactionDate)) {
				List<UserTransaction> userTransactions = userTransactionBO.getUserTransDetailsByDate(transactionDate);

				TypeMap<UserTransaction, UserTransactionDTO> typeMap = modelMapper.getTypeMap(UserTransaction.class,
						UserTransactionDTO.class);
				if (typeMap == null) {
					modelMapper.addMappings(userTransactionConvertor);
				}
				List<UserTransactionDTO> userTransactionDTO = userTransactions.stream()
						.map(this::convertToUserTransactionDTO).collect(Collectors.toList());
				result.put("UserTransaction", userTransactionDTO);

			} else {
				result.put("UserTransaction", "Transaction Date Invalid");
			}
		} else {
			result.put("UserTransaction", "Transaction Date missing");
		}
		return result;
	}

	@GetMapping("/userTransactions/byUserAndTransactionDate/{userId}/{transactionDateTime}")
	public List<TransactionDTO> getUserTransDetailsByDate(@PathVariable String userId,
			@PathVariable String transactionDateTime) throws ParseException {

		User user = new User();
		user.setUserId(userId);

		List<Transaction> userTransactionsDemo = userTransactionBO.getUserTransactionByDateUserId(transactionDateTime,
				user);

		TypeMap<Transaction, TransactionDTO> typeMap = modelMapper.getTypeMap(Transaction.class, TransactionDTO.class);
		if (typeMap == null) {
			modelMapper.addMappings(transactionConvertor);
		}
		return userTransactionsDemo.stream().map(this::convertToTransactionDTO).collect(Collectors.toList());
	}

	@GetMapping("/userTransactions/byUserAndTransactionType/{userId}/{transactionType}")
	public Map<String, Object> getUserTransDetailsByTransactionTypeUserId(@PathVariable String transactionType,
			@PathVariable String userId) throws ParseException {

		Map<String, Object> result = new HashMap<>();

		if (userId != null && transactionType != null) {

			String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

			Pattern pattern = Pattern.compile(regex);

			Matcher matcher = pattern.matcher(userId);

			if (matcher.matches()) {

				Transaction transaction = getTransactionByTransactionType(transactionType);

				if (transaction != null) {

					User user = new User();
					user.setUserId(userId);

					List<UserTransaction> userTransactions = userTransactionBO
							.getUserTransactionByTransactionTypeUserId(transaction, user);

					TypeMap<UserTransaction, UserTransactionDateDTO> typeMap = modelMapper
							.getTypeMap(UserTransaction.class, UserTransactionDateDTO.class);
					if (typeMap == null) {
						modelMapper.addMappings(userTransactionDateConvertor);
					}

					result.put("UserTransaction", userTransactions.stream().map(this::convertToUserTransactionDateDTO)
							.collect(Collectors.toList()));

				} else {
					result.put("UserTransaction", "Transaction type is invalid");
				}
			} else {
				result.put("UserTransaction", "UserId is invalid");
			}
		} else {
			result.put("UserTransaction", "Input is missing");
		}

		return result;
	}

	public Transaction getTransactionByTransactionType(String transactionType) {
		return transactionBO.getTransactionByTransactionType(transactionType);
	}

	private boolean isDateValid(String date) {
		try {
			Date validDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			if (validDate != null)
				return true;
			else
				return false;
		} catch (java.text.ParseException e) {
			return false;
		}
	}

	private UserTransactionDTO convertToUserTransactionDTO(UserTransaction userTransaction) {

		UserTransactionDTO userTransactionDTO = modelMapper.map(userTransaction, UserTransactionDTO.class);
		return userTransactionDTO;
	}

	private UserTransactionDateDTO convertToUserTransactionDateDTO(UserTransaction userTransaction) {

		UserTransactionDateDTO userTransactionDTO = modelMapper.map(userTransaction, UserTransactionDateDTO.class);
		return userTransactionDTO;
	}

	private TransactionDTO convertToTransactionDTO(Transaction transaction) {

		TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
		return transactionDTO;
	}

}
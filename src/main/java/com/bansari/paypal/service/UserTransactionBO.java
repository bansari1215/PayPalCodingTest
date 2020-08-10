package com.bansari.paypal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bansari.paypal.dto.TransactionWithTypeAndDateDTO;
import com.bansari.paypal.dto.UserTransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;

@Service
public interface UserTransactionBO {

	public List<UserTransactionDTO> getUserTransDetailsByDate(String transactionDate);

	public List<TransactionWithTypeAndDateDTO> getUserTransDetailsByUserAndDateTimeAndTransType(User user, Transaction transaction,
			String year, String month, String day, String hour);
}

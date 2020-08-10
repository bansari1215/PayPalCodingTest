package com.bansari.paypal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bansari.paypal.dto.TransactionWithTypeAndDateDTO;
import com.bansari.paypal.dto.UserTransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;

@Component
public interface UserTransactionDAO {

	public List<UserTransactionDTO> getUserTransactionByDate(String transactionDate);

	public List<TransactionWithTypeAndDateDTO> getUserTransDetailsByUserAndDateTimeAndTransType(User user, Transaction transaction, String year,
			String month, String day, String hour);
}

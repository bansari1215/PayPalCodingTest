package com.bansari.paypal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bansari.paypal.dto.TransactionDTO;
import com.bansari.paypal.dto.UserTransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.model.UserTransaction;

@Component
public interface UserTransactionDAO {

	public List<UserTransaction> getUserTransactionByDate(String transactionDate);

	public List<Transaction> getUserTransactionByDateUserId(String transaction, User user);
	
	public List<UserTransaction> getUserTransactionByTransactionTypeUserId(Transaction transaction, User user);
}

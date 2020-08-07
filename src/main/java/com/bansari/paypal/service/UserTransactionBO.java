package com.bansari.paypal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bansari.paypal.dto.TransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.model.UserTransaction;

@Service
public interface UserTransactionBO {

	public List<UserTransaction> getUserTransDetailsByDate(String transactionDate);
	
	public List<Transaction> getUserTransactionByDateUserId(String transaction, User user);
	
	public List<UserTransaction> getUserTransactionByTransactionTypeUserId(Transaction transaction, User user);
}

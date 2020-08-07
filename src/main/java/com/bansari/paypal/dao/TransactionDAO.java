package com.bansari.paypal.dao;

import org.springframework.stereotype.Component;

import com.bansari.paypal.model.Transaction;

@Component
public interface TransactionDAO {
	
	public Transaction getTransactionByTransactionType(String transactionType);
}

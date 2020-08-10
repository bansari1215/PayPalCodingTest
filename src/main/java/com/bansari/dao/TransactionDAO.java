package com.bansari.dao;

import org.springframework.stereotype.Component;

import com.bansari.model.Transaction;

@Component
public interface TransactionDAO {

	public Transaction getTransactionByTransactionType(String transactionType);
}

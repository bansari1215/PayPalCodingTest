package com.bansari.service;

import org.springframework.stereotype.Service;

import com.bansari.model.Transaction;

@Service
public interface TransactionBO {

	public Transaction getTransactionByTransactionType(String transactionType);
}

package com.bansari.paypal.service;

import org.springframework.stereotype.Service;

import com.bansari.paypal.model.Transaction;

@Service
public interface TransactionBO {

	public Transaction getTransactionByTransactionType(String transactionType);
}

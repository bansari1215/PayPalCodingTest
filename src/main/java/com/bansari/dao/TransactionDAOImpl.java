package com.bansari.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bansari.model.Transaction;
import com.bansari.repository.TransactionRepository;

@Component
public class TransactionDAOImpl implements TransactionDAO {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction getTransactionByTransactionType(String transactionType) {

		return transactionRepository.findByTransactionType(transactionType);
	}

}

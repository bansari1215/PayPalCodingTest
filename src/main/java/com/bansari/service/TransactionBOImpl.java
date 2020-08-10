package com.bansari.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bansari.dao.TransactionDAO;
import com.bansari.model.Transaction;

@Service
public class TransactionBOImpl implements TransactionBO {

	@Autowired
	private TransactionDAO transactionDAO;

	@Override
	public Transaction getTransactionByTransactionType(String transactionType) {

		return transactionDAO.getTransactionByTransactionType(transactionType);
	}

}

package com.bansari.paypal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bansari.paypal.dao.TransactionDAO;
import com.bansari.paypal.model.Transaction;

@Service
public class TransactionBOImpl implements TransactionBO {

	@Autowired
	private TransactionDAO transactionDAO;

	@Override
	public Transaction getTransactionByTransactionType(String transactionType) {

		return transactionDAO.getTransactionByTransactionType(transactionType);
	}

}

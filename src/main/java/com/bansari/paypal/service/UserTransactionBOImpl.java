package com.bansari.paypal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bansari.paypal.dao.UserTransactionDAO;
import com.bansari.paypal.dto.TransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.model.UserTransaction;

import lombok.NoArgsConstructor;
@NoArgsConstructor
@Service
public class UserTransactionBOImpl implements UserTransactionBO {

	@Autowired
	private UserTransactionDAO userTransactionDAO;

	@Override
	public List<UserTransaction> getUserTransDetailsByDate(String transactionDate) {

		List<UserTransaction> userTransactionDetailsList = userTransactionDAO.getUserTransactionByDate(transactionDate);

		return userTransactionDetailsList;
	}

	@Override
	public List<Transaction> getUserTransactionByDateUserId(String transactionDate, User user) {
		List<Transaction> list = userTransactionDAO.getUserTransactionByDateUserId(transactionDate, user);
		return list;
	}

	@Override
	public List<UserTransaction> getUserTransactionByTransactionTypeUserId(Transaction transactionType, User user) {
		List<UserTransaction> list = userTransactionDAO.getUserTransactionByTransactionTypeUserId(transactionType, user);
		return list;
	}
}

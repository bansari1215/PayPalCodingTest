package com.bansari.paypal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bansari.paypal.dto.TransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.model.UserTransaction;
import com.bansari.paypal.repository.UserTransactionRepository;

@Component
public class UserTransactionDAOImpl implements UserTransactionDAO {

	@Autowired
	private UserTransactionRepository userTransactionRepository;

	@Override
	public List<UserTransaction> getUserTransactionByDate(String transactionDate) {

		List<UserTransaction> list = userTransactionRepository.findByTransactionDateTime(transactionDate);
		return list;
	}

	@Override
	public List<Transaction> getUserTransactionByDateUserId(String transaction, User user) {
		
		List<Transaction> list = userTransactionRepository.findByDateUserId(transaction, user);
		return list;
	}

	@Override
	public List<UserTransaction> getUserTransactionByTransactionTypeUserId(Transaction transaction, User user) {
		
		List<UserTransaction> list = userTransactionRepository.findByTransactionTypeUserId(transaction, user);
		return list;
	}
}

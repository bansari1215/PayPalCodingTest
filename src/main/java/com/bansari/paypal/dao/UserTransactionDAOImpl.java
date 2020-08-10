package com.bansari.paypal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bansari.paypal.dto.TransactionWithTypeAndDateDTO;
import com.bansari.paypal.dto.UserTransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.repository.UserTransactionRepository;

@Component
public class UserTransactionDAOImpl implements UserTransactionDAO {

	@Autowired
	private UserTransactionRepository userTransactionRepository;

	@Override
	public List<UserTransactionDTO> getUserTransactionByDate(String transactionDate) {

		List<UserTransactionDTO> listUserTrans = userTransactionRepository.findAllUserTransByTransactionDateTime(transactionDate);
		return listUserTrans;
	}

	@Override
	public List<TransactionWithTypeAndDateDTO> getUserTransDetailsByUserAndDateTimeAndTransType(User user, Transaction transaction, String year, String month, String day,
			String hour) {

		return userTransactionRepository.findUserTransDetailByUserAndDateTime(user, transaction, year, month, day, hour);
	}
}

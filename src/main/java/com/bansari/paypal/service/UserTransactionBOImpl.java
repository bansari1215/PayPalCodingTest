package com.bansari.paypal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bansari.paypal.dao.UserTransactionDAO;
import com.bansari.paypal.dto.TransactionWithTypeAndDateDTO;
import com.bansari.paypal.dto.UserTransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class UserTransactionBOImpl implements UserTransactionBO {

	@Autowired
	private UserTransactionDAO userTransactionDAO;

	@Override
	public List<UserTransactionDTO> getUserTransDetailsByDate(String transactionDate) {

		List<UserTransactionDTO> listUserTrans = userTransactionDAO.getUserTransactionByDate(transactionDate);

		return listUserTrans;
	}

	@Override
	public List<TransactionWithTypeAndDateDTO> getUserTransDetailsByUserAndDateTimeAndTransType(User user, Transaction transaction,
			String year, String month, String day, String hour) {
		return userTransactionDAO.getUserTransDetailsByUserAndDateTimeAndTransType(user, transaction, year, month, day, hour);
	}
}

package com.bansari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bansari.dao.UserTransactionDAO;
import com.bansari.dto.TransactionWithTypeAndDateDTO;
import com.bansari.dto.UserTransactionDTO;
import com.bansari.model.Transaction;
import com.bansari.model.User;

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

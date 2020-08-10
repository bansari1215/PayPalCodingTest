package com.bansari.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bansari.dto.TransactionWithTypeAndDateDTO;
import com.bansari.dto.UserTransactionDTO;
import com.bansari.model.Transaction;
import com.bansari.model.User;

@Component
public interface UserTransactionDAO {

	public List<UserTransactionDTO> getUserTransactionByDate(String transactionDate);

	public List<TransactionWithTypeAndDateDTO> getUserTransDetailsByUserAndDateTimeAndTransType(User user, Transaction transaction, String year,
			String month, String day, String hour);
}

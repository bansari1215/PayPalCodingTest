package com.bansari.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bansari.dto.TransactionWithTypeAndDateDTO;
import com.bansari.dto.UserTransactionDTO;
import com.bansari.model.Transaction;
import com.bansari.model.User;

@Service
public interface UserTransactionBO {

	public List<UserTransactionDTO> getUserTransDetailsByDate(String transactionDate);

	public List<TransactionWithTypeAndDateDTO> getUserTransDetailsByUserAndDateTimeAndTransType(User user, Transaction transaction,
			String year, String month, String day, String hour);
}

package com.bansari.paypal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bansari.paypal.dao.UserTransactionDAO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.model.UserTransaction;

@ExtendWith(MockitoExtension.class)
public class UserTransactionBOTests {

	@Mock
	private UserTransactionDAO userTransactionDAO;

	@InjectMocks
	private UserTransactionBO userTransactionBO = new UserTransactionBOImpl();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTransactionByTransactionType() {

		List<UserTransaction> mockList = new ArrayList<>();
		when(userTransactionDAO.getUserTransactionByDate(Mockito.any())).thenReturn(mockList);

		List<UserTransaction> ActualList = userTransactionBO.getUserTransDetailsByDate("2020-10-21");
		assertEquals(mockList, ActualList);
	}

	@Test
	public void getUserTransactionByDateUserId() {

		List<Transaction> mockList = new ArrayList<>();
		User user = new User();
		when(userTransactionDAO.getUserTransactionByDateUserId(Mockito.any(), Mockito.any())).thenReturn(mockList);

		List<Transaction> ActualList = userTransactionBO.getUserTransactionByDateUserId("2020-10-21", user);
		assertEquals(mockList, ActualList);
	}

	@Test
	public void getUserTransactionByTransactionTypeUserId() {

		List<UserTransaction> mockList = new ArrayList<>();
		User user = new User();
		Transaction transaction = new Transaction();
		when(userTransactionDAO.getUserTransactionByTransactionTypeUserId(Mockito.any(), Mockito.any()))
				.thenReturn(mockList);

		List<UserTransaction> ActualList = userTransactionBO.getUserTransactionByTransactionTypeUserId(transaction,
				user);
		assertEquals(mockList, ActualList);
	}
}

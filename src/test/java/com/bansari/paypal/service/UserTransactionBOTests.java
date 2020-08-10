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

import com.bansari.dao.UserTransactionDAO;
import com.bansari.dto.TransactionWithTypeAndDateDTO;
import com.bansari.dto.UserTransactionDTO;
import com.bansari.model.Transaction;
import com.bansari.model.User;
import com.bansari.service.UserTransactionBO;
import com.bansari.service.UserTransactionBOImpl;

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

		List<UserTransactionDTO> mockList = new ArrayList<>();
		when(userTransactionDAO.getUserTransactionByDate(Mockito.any())).thenReturn(mockList);

		List<UserTransactionDTO> ActualList = userTransactionBO.getUserTransDetailsByDate("2020-10-21");
		assertEquals(mockList, ActualList);
	}

	@Test
	public void getUserTransDetailsByDateTime_withAllInputData() {

		List<TransactionWithTypeAndDateDTO> mockList = new ArrayList<>();
		User user = new User();
		Transaction transaction = new Transaction();
		when(userTransactionDAO.getUserTransDetailsByUserAndDateTimeAndTransType(Mockito.any(), Mockito.any(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
						.thenReturn(mockList);

		List<TransactionWithTypeAndDateDTO> ActualList = userTransactionBO
				.getUserTransDetailsByUserAndDateTimeAndTransType(user, transaction, "2020", "05", "12", "11");
		assertEquals(mockList, ActualList);
	}

	@Test
	public void getUserTransDetailsByDateTime_withOnlyUserData_defaultDateRelatedInputData() {

		List<TransactionWithTypeAndDateDTO> mockList = new ArrayList<>();
		User user = new User();
		Transaction transaction = new Transaction();
		when(userTransactionDAO.getUserTransDetailsByUserAndDateTimeAndTransType(Mockito.any(), Mockito.any(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
						.thenReturn(mockList);

		List<TransactionWithTypeAndDateDTO> ActualList = userTransactionBO
				.getUserTransDetailsByUserAndDateTimeAndTransType(user, transaction, "%", "%", "%", "%");
		assertEquals(mockList, ActualList);
	}
}

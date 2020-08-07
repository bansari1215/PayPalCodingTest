package com.bansari.paypal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.bansari.paypal.entityDtoConvertor.TransactionConvertor;
import com.bansari.paypal.entityDtoConvertor.UserTransactionConvertor;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.UserTransaction;
import com.bansari.paypal.resource.HomeResource;
import com.bansari.paypal.service.TransactionBO;
import com.bansari.paypal.service.UserTransactionBO;

@ExtendWith(MockitoExtension.class)
public class HomeResourceTest {

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private UserTransactionBO userTransactionBO;

	@Mock
	private TransactionBO transactionBO;

	@Mock
	private UserTransactionConvertor userTransactionConvertor;

	@Mock
	private TransactionConvertor transactionConvertor;

	@InjectMocks
	private HomeResource homeResource;

	@BeforeEach
	public void setup() {
		homeResource = new HomeResource();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUserTransDetailsByDate_WhenDateMissing_thenReturnErrorMessage() {
		Map<String, Object> result = homeResource.getUserTransDetailsByDate(null);
		assertEquals("Transaction Date missing", result.get("UserTransaction"));
	}

	@Test
	public void getUserTransDetailsByDate_WhenDateIsInValid() {
		Map<String, Object> result = homeResource.getUserTransDetailsByDate("InValid Date");
		assertEquals("Transaction Date Invalid", result.get("UserTransaction"));
	}

	@Test
	public void getUserTransDetailsByDate_WhenDateIsValid() {
		List<UserTransaction> mockList = new ArrayList<>();
		when(userTransactionBO.getUserTransDetailsByDate(Mockito.any())).thenReturn(mockList);
		Map<String, Object> result = homeResource.getUserTransDetailsByDate("2020-03-22");
		assertEquals(mockList, result.get("UserTransaction"));
	}

	@Test
	public void getUserTransDetailsByTransactionTypeUserId_WhenInputIsNull() {
		Map<String, Object> result = homeResource.getUserTransDetailsByTransactionTypeUserId(null, null);
		assertEquals("Input is missing", result.get("UserTransaction"));
	}

	@Test
	public void getUserTransDetailsByTransactionTypeUserId_WhenUserIdIsInvalid() {
		Map<String, Object> result = homeResource.getUserTransDetailsByTransactionTypeUserId("Invoice",
				"Invalid UserId");
		assertEquals("UserId is invalid", result.get("UserTransaction"));
	}

	@Test
	public void getUserTransDetailsByTransactionTypeUserId_WhenUserIdIsNull() {
		Map<String, Object> result = homeResource.getUserTransDetailsByTransactionTypeUserId("Invoice", null);
		assertEquals("Input is missing", result.get("UserTransaction"));
	}

	@Test
	public void getUserTransDetailsByTransactionTypeUserId_WhenTransactionTypeIsNull() {
		Map<String, Object> result = homeResource.getUserTransDetailsByTransactionTypeUserId(null, "bansari@gmail.com");
		assertEquals("Input is missing", result.get("UserTransaction"));
	}

	@Test
	public void getUserTransDetailsByTransactionTypeUserId_WhenTransactionTypeIsInvalid() {
		Map<String, Object> result = homeResource.getUserTransDetailsByTransactionTypeUserId("Transaction Type Invalid",
				"B@B.com");
		assertEquals("Transaction type is invalid", result.get("UserTransaction"));
	}

	@Test
	public void getUserTransDetailsByDate_WhenInputIsValid() {
		List<UserTransaction> mockList = new ArrayList<>();
		when(userTransactionBO.getUserTransactionByTransactionTypeUserId(Mockito.any(), Mockito.any()))
				.thenReturn(mockList);

		Transaction transaction = new Transaction();
		transaction.setTransactionType("Invoice");

		when(transactionBO.getTransactionByTransactionType(Mockito.any())).thenReturn(transaction);

		Map<String, Object> result = homeResource.getUserTransDetailsByTransactionTypeUserId("Invoice",
				"bansari@gmail.com");
		assertEquals(mockList, result.get("UserTransaction"));
	}
}

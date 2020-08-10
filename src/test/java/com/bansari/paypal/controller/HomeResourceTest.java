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
import org.springframework.http.ResponseEntity;

import com.bansari.paypal.dto.TransactionWithTypeAndDateDTO;
import com.bansari.paypal.dto.UserTransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.resource.HomeResource;
import com.bansari.paypal.service.TransactionBO;
import com.bansari.paypal.service.UserTransactionBO;

@ExtendWith(MockitoExtension.class)
public class HomeResourceTest {

	@Mock
	private UserTransactionBO userTransactionBO;

	@Mock
	private TransactionBO transactionBO;

	@InjectMocks
	private HomeResource homeResource;

	@BeforeEach
	public void setup() {
		homeResource = new HomeResource();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllUserTransDetailsByDate_whenDateMissing_thenReturnErrorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByDate(null);
		assertEquals("Please enter valid transaction date in 'yyyy-MM-dd' format",
				actualResult.getBody().get("Transaction Date"));
	}

	@Test
	public void getAllUserTransDetailsByDate_whenDateIsInValid_thenReturnErrorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByDate("InValid Date");
		assertEquals("Please enter valid transaction date in 'yyyy-MM-dd' format",
				actualResult.getBody().get("Transaction Date"));
	}

	@Test
	public void getAllUserTransDetailsByDate_whenDateIsValid_thenReturnData() {
		List<UserTransactionDTO> mockList = new ArrayList<>();
		when(userTransactionBO.getUserTransDetailsByDate(Mockito.any())).thenReturn(mockList);
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByDate("2020-05-22");
		assertEquals(mockList, actualResult.getBody().get("UserTransaction"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_whenAllInputDataIsEmpty_thenReturnErorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("", "", "",
				"", "", "");
		assertEquals("Please enter valid UserId",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("UserId"));
		assertEquals("Please enter valid Year in 'yyyy' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Year"));
		assertEquals("Please enter valid Month in 'MM' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Month"));
		assertEquals("Please enter valid Day in 'dd' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Day"));
		assertEquals("Please enter valid Hour in 'hh' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Hour"));
		assertEquals("Please enter valid Transaction type",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Transaction Type"));
	}

	@Test
	public void getAllUserTransDetailsByUserAndTrans_whenAllInputDataIsNull_thenReturnErorMessage() {
		ResponseEntity<String> actualResult = homeResource.invalidURI();
		assertEquals("Please enter valid input.", actualResult.getBody());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_userIdIsEmpty_otherInputNull_thenReturnErorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("", null,
				null, null, null, null);
		assertEquals("Please enter valid UserId",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("UserId"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_inValidUserId_otherInputNull_thenReturnErorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("bansari",
				null, null, null, null, null);
		assertEquals("Please enter valid UserId",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("UserId"));
	}

	@Test
	public void getAllUserTransDetailsByUserAndTrans_validUserId_otherInputNull_thenReturnData() {
		List<TransactionWithTypeAndDateDTO> mockList = new ArrayList<>();
		when(userTransactionBO.getUserTransDetailsByUserAndDateTimeAndTransType(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(),
				Mockito.any(), Mockito.any())).thenReturn(mockList);
		ResponseEntity<Map<String, Object>> actualResult = homeResource
				.getAllUserTransDetailsByUserAndTrans("bansari@gmail.com", null, null, null, null, null);
		assertEquals(mockList, actualResult.getBody().get("UserTransaction"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_inValidUserIdYear_otherInputNull_thenReturnErorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("bansari",
				"abcd", null, null, null, null);
		assertEquals("Please enter valid UserId",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("UserId"));
		assertEquals("Please enter valid Year in 'yyyy' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Year"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_inValidUserIdMonth_otherInputNull_thenReturnErorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("bansari",
				null, "abcd", null, null, null);
		assertEquals("Please enter valid UserId",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("UserId"));
		assertEquals("Please enter valid Month in 'MM' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Month"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_inValidUserIdDay_otherInputNull_thenReturnErorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("bansari",
				null, null, "abcd", null, null);
		assertEquals("Please enter valid UserId",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("UserId"));
		assertEquals("Please enter valid Day in 'dd' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Day"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_inValidUserIdHour_otherInputNull_thenReturnErorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("bansari",
				null, null, null, "abcd", null);
		assertEquals("Please enter valid UserId",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("UserId"));
		assertEquals("Please enter valid Hour in 'hh' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Hour"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_inValidUserIdTransactionType_otherInputNull_thenReturnErorMessage() {

		Transaction transaction = new Transaction();
		when(homeResource.getTransactionByTransactionType(Mockito.anyString())).thenReturn(transaction);

		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("bansari",
				null, null, null, null, "abcd");
		assertEquals("Please enter valid UserId",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("UserId"));
		assertEquals("Please enter valid Transaction type",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Transaction Type"));
	}

	@Test
	public void getTransactionByTransactionType_inValidTransactionType_thenReturnErorMessage() {
		Transaction actualResult = homeResource.getTransactionByTransactionType("InValid Transaction Type");
		assertEquals(null, actualResult);
	}

	@Test
	public void getTransactionByTransactionType_transactionTypeNull_thenReturnErorMessage() {
		Transaction actualResult = homeResource.getTransactionByTransactionType(null);
		assertEquals(null, actualResult);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getAllUserTransDetailsByUserAndTrans_inValidMonth_thenReturnErorMessage() {
		ResponseEntity<Map<String, Object>> actualResult = homeResource.getAllUserTransDetailsByUserAndTrans("bansari@gmail.com",
				null, "15", null, null, null);
		assertEquals("Please enter valid Month in 'MM' format",
				((Map<String, Object>) actualResult.getBody().get("UserTransaction")).get("Month"));
	}

	@Test
	public void getAllUserTransDetailsByUserAndTrans_validAllInput_thenReturnData() {
		List<TransactionWithTypeAndDateDTO> mockList = new ArrayList<>();
		when(userTransactionBO.getUserTransDetailsByUserAndDateTimeAndTransType(Mockito.any(), Mockito.any(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(mockList);
		ResponseEntity<Map<String, Object>> actualResult = homeResource
				.getAllUserTransDetailsByUserAndTrans("bansari@gmail.com", "2020", "05", "12", "11", "Invoice");
		assertEquals(mockList, actualResult.getBody().get("UserTransaction"));
	}
}

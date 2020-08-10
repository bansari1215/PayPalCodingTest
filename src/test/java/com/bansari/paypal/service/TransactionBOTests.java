package com.bansari.paypal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bansari.dao.TransactionDAO;
import com.bansari.model.Transaction;
import com.bansari.service.TransactionBO;
import com.bansari.service.TransactionBOImpl;

@ExtendWith(MockitoExtension.class)
public class TransactionBOTests {

	@Mock
	private TransactionDAO transactionDAO;

	@InjectMocks
	private TransactionBO transactionBO = new TransactionBOImpl();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getTransactionByTransactionType() {

		
		Transaction transactionObjExpected = new Transaction();
		transactionObjExpected.setTransactionType("Invoice");

		when(transactionDAO.getTransactionByTransactionType(Mockito.any())).thenReturn(transactionObjExpected);

		Transaction transactionActual = transactionBO.getTransactionByTransactionType("Invoice");
		assertEquals(transactionObjExpected, transactionActual);
	}
}

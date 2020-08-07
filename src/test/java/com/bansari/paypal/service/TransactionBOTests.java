package com.bansari.paypal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.bansari.paypal.dao.TransactionDAO;
import com.bansari.paypal.model.Transaction;

@RunWith(MockitoJUnitRunner.class)
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

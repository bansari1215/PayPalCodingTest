package com.bansari.paypal.entityDtoConvertor;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.bansari.paypal.dto.TransactionDTO;
import com.bansari.paypal.model.Transaction;

@Component
public class TransactionConvertor extends PropertyMap<Transaction, TransactionDTO> {

	@Override
	protected void configure() {
		map().setTransactionType(source.getTransactionType());
	}

}

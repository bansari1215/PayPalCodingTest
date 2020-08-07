package com.bansari.paypal.entityDtoConvertor;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.bansari.paypal.dto.UserTransactionDTO;
import com.bansari.paypal.model.UserTransaction;

@Component
public class UserTransactionConvertor extends PropertyMap<UserTransaction, UserTransactionDTO> {

	@Override
	protected void configure() {
		map().setFirstName(source.getUser().getFirstName());
		map().setLastName(source.getUser().getLastName());
		map().setUserId(source.getUser().getUserId());
		map().setTransactionType(source.getTransaction().getTransactionType());
	}

}

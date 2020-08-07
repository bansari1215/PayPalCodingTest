package com.bansari.paypal.entityDtoConvertor;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.bansari.paypal.dto.UserTransactionDateDTO;
import com.bansari.paypal.model.UserTransaction;

@Component
public class UserTransactionDateConvertor extends PropertyMap<UserTransaction, UserTransactionDateDTO> {

	@Override
		protected void configure() {
			map().setTransactionDate(source.getTransactionDateTime());
		}
}

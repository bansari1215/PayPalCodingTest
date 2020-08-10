package com.bansari.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.bansari.model.Transaction;
import com.bansari.util.dateTimeZoneConvertor.TimeZoneConvertor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TransactionWithTypeAndDateDTO {

	private String transactionType;
	private Date transactionDate;
	
	public TransactionWithTypeAndDateDTO(Transaction transaction, Date transactionDate) {
		this.transactionType = transaction.getTransactionType();
		this.transactionDate = TimeZoneConvertor.gmttoLocalDate(transactionDate);
	}
}

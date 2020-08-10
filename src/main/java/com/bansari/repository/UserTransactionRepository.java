package com.bansari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bansari.dto.TransactionWithTypeAndDateDTO;
import com.bansari.dto.UserTransactionDTO;
import com.bansari.model.Transaction;
import com.bansari.model.User;
import com.bansari.model.UserTransaction;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {

	@Query("select new com.bansari.dto.UserTransactionDTO(d.transaction, d.user) from UserTransaction d where FORMATDATETIME(d.transactionDateTime,'yyyy-MM-dd') = :transactionDateTime")
	List<UserTransactionDTO> findAllUserTransByTransactionDateTime(String transactionDateTime);

	@Query("select new com.bansari.dto.TransactionWithTypeAndDateDTO(d.transaction, d.transactionDateTime) "
			+ "from UserTransaction d " + "where FORMATDATETIME(d.transactionDateTime, 'yyyy') like :year and "
			+ "FORMATDATETIME(d.transactionDateTime, 'MM') like :month and "
			+ "FORMATDATETIME(d.transactionDateTime, 'dd') like :day and "
			+ "FORMATDATETIME(d.transactionDateTime, 'HH') like :hour and " + "d.user = :user and "
			+ "d.transaction like Case When :transaction Is Not Null Then :transaction Else '%' End")
	List<TransactionWithTypeAndDateDTO> findUserTransDetailByUserAndDateTime(User user, Transaction transaction,
			String year, String month, String day, String hour);
}

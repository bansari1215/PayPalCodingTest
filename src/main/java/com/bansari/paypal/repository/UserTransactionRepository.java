package com.bansari.paypal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bansari.paypal.dto.TransactionDTO;
import com.bansari.paypal.model.Transaction;
import com.bansari.paypal.model.User;
import com.bansari.paypal.model.UserTransaction;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {

	@Query("select d from UserTransaction d where FORMATDATETIME(d.transactionDateTime,'yyyy-MM-dd') = ?1")
	List<UserTransaction> findByTransactionDateTime(String transactionDateTime);

	@Query("select d.transaction from UserTransaction d where FORMATDATETIME(d.transactionDateTime,'yyyy-MM-dd') = ?1 and d.user = ?2 Group By d.transaction")
	List<Transaction> findByDateUserId(String transactionDateTime, User user);
	
	@Query("select d from UserTransaction d where transaction = ?1 and user = ?2")
	List<UserTransaction> findByTransactionTypeUserId(Transaction transaction, User user);
}

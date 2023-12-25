package com.example.springbankapplication.repositories;

import com.example.springbankapplication.entities.Account;
import com.example.springbankapplication.entities.Client;
import com.example.springbankapplication.entities.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account IN (SELECT a FROM Account a WHERE a.client = ?1)")
    List<Transaction> findByAccount_CreditCard_Client(Client client);
}

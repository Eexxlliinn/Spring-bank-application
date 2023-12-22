package com.example.springbankapplication.repositories;

import com.example.springbankapplication.entities.Account;
import com.example.springbankapplication.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByStatus(String status);

    List<Account> findByClient(Client client);
    List<Account> findByBlock(String block);
}

package com.example.springbankapplication.repositories;

import com.example.springbankapplication.entities.Client;
import com.example.springbankapplication.entities.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
    List<CreditCard> findByClient(Client client);

}

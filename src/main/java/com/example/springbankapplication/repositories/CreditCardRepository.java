package com.example.springbankapplication.repositories;

import com.example.springbankapplication.entities.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
}

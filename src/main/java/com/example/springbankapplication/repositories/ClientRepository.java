package com.example.springbankapplication.repositories;

import com.example.springbankapplication.entities.Client;
import com.example.springbankapplication.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByUser(User user);
}

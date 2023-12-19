package com.example.springbankapplication.repositories;

import com.example.springbankapplication.entities.Role;
import com.example.springbankapplication.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsernameAndRole(String username, Role role);
}

package com.example.springbankapplication.forms;


import com.example.springbankapplication.entities.Role;
import com.example.springbankapplication.entities.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Slf4j
public class RegistrationForm {
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        Role role = new Role();
        role.setId(1L);
        return new User(username, passwordEncoder.encode(password), role);
    }
}


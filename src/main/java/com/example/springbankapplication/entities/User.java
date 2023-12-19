package com.example.springbankapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    private Long id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
}

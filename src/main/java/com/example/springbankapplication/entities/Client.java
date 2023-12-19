package com.example.springbankapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Client {

    @Id
    private Long id;

}

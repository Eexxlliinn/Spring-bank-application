package com.example.springbankapplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CreditCard {

    @Id
    private Long id;
}

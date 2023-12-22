package com.example.springbankapplication.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@RequiredArgsConstructor
@Table(name = "CreditCards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cardNumber")
    private final String cardNumber;

    @Column(name = "cardExpiration")
    private final String cardExpiration;

    @Column(name = "cardCVV")
    private final String cardCVV;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private Client client;

}

package com.example.springbankapplication.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "Accounts")
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "accountNumber")
    private final String accountNumber;

    @Column(name = "moneyAmount")
    private String moneyAmount;

    @Column(name = "currency")
    private final String currency;

    @OneToOne
    @JoinColumn(name = "creditCard", referencedColumnName = "id")
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private final Client client;

    @Column(name = "status")
    private String status;

    @Column(name = "block")
    private String block;
}

package com.example.springbankapplication.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "Transactions")
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "creationDate")
    private Date creationDate;

    @Column(name = "amount")
    private String amount;

    @ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Account account;

    private final String type;

    public Transaction(String description, Date date, String amount, Account account, String type) {
        this.description = description;
        this.creationDate = date;
        this.amount = amount;
        this.account = account;
        this.type = type;
    }
}

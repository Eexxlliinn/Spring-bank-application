package com.example.springbankapplication.forms;

import com.example.springbankapplication.entities.Account;
import com.example.springbankapplication.entities.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TransactionForm {

    private String description;

    private String amount;

    private Account account;

    private String type;

    public Transaction toTransaction() {
        return new Transaction(description, new Date(), amount, account, type);
    }
}

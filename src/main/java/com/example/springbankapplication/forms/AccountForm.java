package com.example.springbankapplication.forms;

import com.example.springbankapplication.entities.Account;
import com.example.springbankapplication.entities.Client;
import lombok.Data;

@Data
public class AccountForm {
    private String accountNumber;
    private String currency;
    private Client client;

    public Account toAccount() {
        Account account = new Account(accountNumber, currency, client);
        account.setMoneyAmount("0");
        account.setStatus("Available");
        account.setBlock("Not blocked");
        return account;
    }
}

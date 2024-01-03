package com.example.springbankapplication.forms;

import com.example.springbankapplication.entities.Client;
import com.example.springbankapplication.entities.CreditCard;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CreditCardForm {

    private String cardNumber;

    private String cardExpiration;

    private String cardCVV;

    private Long account;
    public CreditCard toCreditCard() {
        return new CreditCard(cardNumber, cardExpiration, cardCVV);
    }
}

package com.example.springbankapplication.forms;

import com.example.springbankapplication.entities.Client;
import com.example.springbankapplication.entities.User;
import lombok.Data;

@Data
public class ClientForm {

    private String fullName;

    private String age;

    private String address;

    private String phoneNumber;

    private User user;

    public Client toClient() {
        return new Client(fullName, age, address, phoneNumber, user);
    }
}

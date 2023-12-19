package com.example.springbankapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBankApplication.class, args);
    }
    /*
    Система Платежи. Клиент имеет одну или несколько Кредитных Карт,
     каждая из которых соответствует некоторому Счету в системе платежей.
      Клиент может при помощи Счета сделать Платеж, заблокировать Счет и
       пополнить Счет. Администратор снимает блокировку
     */

}

package com.va.week11.shoppinginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Assign4ShoppingInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(Assign4ShoppingInfoApplication.class, args);
    }
}

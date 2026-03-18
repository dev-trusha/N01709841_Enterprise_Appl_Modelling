package com.va.week9.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderMgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMgmtApplication.class, args);
    }

    // RestTemplate is used to call other microservices (AcctMgmt, FeeMgmt, MarketMgmt)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

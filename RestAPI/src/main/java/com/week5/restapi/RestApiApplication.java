package com.week5.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    private static final Logger log = LoggerFactory.getLogger(RestApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            String url = "https://topups.reloadly.com/countries/US";

            Countries countries = restTemplate.getForObject(url, Countries.class);

            if (countries == null) {
                log.error("No response received from API.");
            } else {
                log.info("API Response mapped to Countries object:");
                log.info(countries.toString());
            }
        };
    }
}

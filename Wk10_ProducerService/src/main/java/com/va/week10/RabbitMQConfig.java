package com.va.week10;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String NAME_QUEUE  = "nameQueue";
    public static final String AGE_QUEUE   = "ageQueue";

    @Bean
    public Queue nameQueue() {
        return new Queue(NAME_QUEUE, false);
    }

    @Bean
    public Queue ageQueue() {
        return new Queue(AGE_QUEUE, false);
    }
}

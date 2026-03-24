package com.va.week10;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Method 1: Send name message to nameQueue
    public void sendName(String name) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.NAME_QUEUE, name);
        System.out.println("Sent Name: " + name);
    }

    // Method 2: Send age message to ageQueue
    public void sendAge(String age) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.AGE_QUEUE, age);
        System.out.println("Sent Age: " + age);
    }
}

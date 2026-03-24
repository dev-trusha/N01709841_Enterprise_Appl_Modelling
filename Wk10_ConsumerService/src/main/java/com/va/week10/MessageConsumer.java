package com.va.week10;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    // Listener Method 1: Receives name from nameQueue
    @RabbitListener(queues = RabbitMQConfig.NAME_QUEUE)
    public void receiveName(String name) {
        System.out.println("Received Name: " + name);
    }

    // Listener Method 2: Receives age from ageQueue
    @RabbitListener(queues = RabbitMQConfig.AGE_QUEUE)
    public void receiveAge(String age) {
        System.out.println("Received Age: " + age);
    }
}

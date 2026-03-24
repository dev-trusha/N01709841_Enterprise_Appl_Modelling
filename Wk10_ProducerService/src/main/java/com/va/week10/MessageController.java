package com.va.week10;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    // Endpoint 1: Send your name
    // GET http://localhost:8080/send/name?name=Trusha
    @GetMapping("/send/name")
    public String sendName(@RequestParam String name) {
        messageProducer.sendName(name);
        return "Name sent: " + name;
    }

    // Endpoint 2: Send your age
    // GET http://localhost:8080/send/age?age=21
    @GetMapping("/send/age")
    public String sendAge(@RequestParam String age) {
        messageProducer.sendAge(age);
        return "Age sent: " + age;
    }
}

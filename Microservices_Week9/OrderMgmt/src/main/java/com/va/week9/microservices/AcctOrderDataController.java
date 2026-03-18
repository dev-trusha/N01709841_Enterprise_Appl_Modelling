package com.va.week9.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController returns JSON (not HTML)
// This lets OTHER microservices read our order data
@RestController
@RequestMapping("/api/orders")
public class AcctOrderDataController {

    @Autowired
    private OrderRepository orderRepository;

    // ============================================================
    // Operation: GET ALL ORDERS as JSON
    // URL: http://localhost:8080/api/orders
    // Other services can call this to read order data
    // ============================================================
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();  // returns as JSON automatically
    }

    // ============================================================
    // Operation: GET ONE ORDER by ID as JSON
    // URL: http://localhost:8080/api/orders/{id}
    // ============================================================
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

}

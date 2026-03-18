package com.va.week9.microservices;

import jakarta.persistence.*;

// This is our Entity class - maps to the "orders" table in MySQL
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;        // auto-generated primary key

    private String date;         // e.g., "2026-03-16"
    private String stockQuote;   // stock symbol e.g., "AAPL", "TSLA"
    private double price;        // price per share
    private int quantity;        // number of shares to sell/buy
    private String accountId;    // account e.g., "ABC"

    // ---- Default constructor (required by JPA/Hibernate) ----
    public Order() {}

    // ---- Getters and Setters ----

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getStockQuote() {
        return stockQuote;
    }
    public void setStockQuote(String stockQuote) {
        this.stockQuote = stockQuote;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}

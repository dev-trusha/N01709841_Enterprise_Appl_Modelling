package com.va.week9.microservices;

import jakarta.persistence.*;

// Entity class - maps to "acct_transactions" table in MySQL
@Entity
@Table(name = "acct_transactions")
public class AcctTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;   // auto-generated primary key

    private Long orderId;         // which order this transaction belongs to
    private String date;          // transaction date
    private double price;         // price per share at time of transaction
    private int quantity;         // number of shares
    private String accountId;     // account holder e.g., "ABC"

    // ---- Default constructor (required by JPA) ----
    public AcctTransaction() {}

    // ---- Getters and Setters ----

    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

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

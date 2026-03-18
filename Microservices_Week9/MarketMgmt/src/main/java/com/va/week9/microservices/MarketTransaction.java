package com.va.week9.microservices;

import jakarta.persistence.*;

// Entity class - maps to "market_transactions" table in MySQL
// Represents an order placed on the stock exchange
@Entity
@Table(name = "market_transactions")
public class MarketTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long marketTxnId;   // auto-generated primary key

    private Long orderId;        // which order this market transaction belongs to
    private String stockQuote;   // stock symbol e.g., "AAPL"
    private double price;        // price per share
    private int quantity;        // number of shares
    private String date;         // date placed
    private String status;       // e.g., "PLACED", "EXECUTED"

    // ---- Default constructor (required by JPA) ----
    public MarketTransaction() {}

    // ---- Getters and Setters ----

    public Long getMarketTxnId() {
        return marketTxnId;
    }
    public void setMarketTxnId(Long marketTxnId) {
        this.marketTxnId = marketTxnId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}

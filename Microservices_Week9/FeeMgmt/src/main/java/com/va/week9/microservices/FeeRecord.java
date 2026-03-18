package com.va.week9.microservices;

import jakarta.persistence.*;

// Entity class - maps to "fee_records" table in MySQL
// This is the "logbook" of all fees charged per transaction
@Entity
@Table(name = "fee_records")
public class FeeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeId;         // auto-generated primary key

    private Long orderId;       // which order this fee belongs to
    private String date;        // date of the transaction
    private int quantity;       // number of shares (fee can be per quantity)
    private double fees;        // fee charged for this transaction (e.g., 1% of trade value)
    private double totalFees;   // running total (for now, same as fees)

    // ---- Default constructor (required by JPA) ----
    public FeeRecord() {}

    // ---- Getters and Setters ----

    public Long getFeeId() {
        return feeId;
    }
    public void setFeeId(Long feeId) {
        this.feeId = feeId;
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

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFees() {
        return fees;
    }
    public void setFees(double fees) {
        this.fees = fees;
    }

    public double getTotalFees() {
        return totalFees;
    }
    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }

}

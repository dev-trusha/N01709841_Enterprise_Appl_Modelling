//N01709841
package com.spring.web.flux.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "savings_accounts")
public class Savings extends Account {

    @Id
    private String id;

    private double interestRate;

    // Default Constructor
    public Savings() {
    }

    // Constructor without id
    public Savings(String accountHolder, double balance, double interestRate) {
        super(accountHolder, balance);
        this.interestRate = interestRate;
    }

    // Full Constructor
    public Savings(String id, String accountHolder, double balance, double interestRate) {
        super(accountHolder, balance);
        this.id = id;
        this.interestRate = interestRate;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // depositMonthlyInterest
    public double depositMonthlyInterest() {
        double monthlyInterest = getBalance() * (interestRate / 12);
        setBalance(getBalance() + monthlyInterest);
        return getBalance();
    }
}
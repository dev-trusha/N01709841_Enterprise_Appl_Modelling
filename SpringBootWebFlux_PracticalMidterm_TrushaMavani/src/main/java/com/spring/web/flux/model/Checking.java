//N01709841
package com.spring.web.flux.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "checking_accounts")
public class Checking extends Account {

    @Id
    private String id;

    private double insufficientFundFee;

    // Default Constructor
    public Checking() {
    }

    // Constructor without id
    public Checking(String accountHolder, double balance, double insufficientFundFee) {
        super(accountHolder, balance);
        this.insufficientFundFee = insufficientFundFee;
    }

    // Full Constructor
    public Checking(String id, String accountHolder, double balance, double insufficientFundFee) {
        super(accountHolder, balance);
        this.id = id;
        this.insufficientFundFee = insufficientFundFee;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getInsufficientFundFee() {
        return insufficientFundFee;
    }

    public void setInsufficientFundFee(double insufficientFundFee) {
        this.insufficientFundFee = insufficientFundFee;
    }

    // processingCheck method
    public double processingCheck(double checkAmount) {

        if (checkAmount <= 0) {
            throw new IllegalArgumentException("Check amount must be positive");
        }

        if (checkAmount > getBalance()) {
            // apply insufficient fund fee
            setBalance(getBalance() - insufficientFundFee);
            throw new IllegalArgumentException("Insufficient funds. NSF fee applied.");
        }

        setBalance(getBalance() - checkAmount);
        return getBalance();
    }
}
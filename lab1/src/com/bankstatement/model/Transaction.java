package com.bankstatement.model;

public class Transaction {
    private String date;
    private double amount;
    private String category;

    // Constructor
    public Transaction(String date, double amount, String category) {
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    // Getter và Setter
    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Amount: " + amount + ", Category: " + category;
    }
}

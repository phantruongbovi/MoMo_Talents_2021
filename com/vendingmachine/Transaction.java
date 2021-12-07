package com.vendingmachine;

public class Transaction {
    final protected double LEGAL_MONEY[] = {10000, 20000, 50000, 100000, 200000};
    protected double balance;
    public Transaction(){
        this.balance = 0;
    }

    double getBalance(){
        return this.balance;
    }

    void setBalance(double money){
        this.balance = money;
    }
}

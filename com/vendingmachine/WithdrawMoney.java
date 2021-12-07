package com.vendingmachine;

public class WithdrawMoney extends Transaction{
    public WithdrawMoney(){}

    public void deleteCustomer(double amountMoney){
        System.out.println("Số tiền quý khách nhận lại là: " + Double.toString(amountMoney));
        System.out.println("Tạm biệt quý khách!!");
    }
}

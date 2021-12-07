package com.vendingmachine;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.DoubleStream;

public class DepositMoney extends Transaction{

    public DepositMoney(){
        this.balance = 0;
    }

    public void newCustomer() {
        System.out.println("------------------");
        System.out.println("Xin chào quý khách!");
        System.out.println("------------------");
        this.setBalance(0);
        this.depositMoney();
    }

    public void currentCustomer(){
        this.depositMoney();
    }

    private void depositMoney(){
        boolean checkStatusDeposit = true;
        Scanner inputMoney = new Scanner(System.in);
        double currentMoneyInput;

        System.out.println("Quý khách vui lòng nạp số tiền lần lượt vào máy");
        System.out.println("(Máy chỉ chấp nhận tiền có mệnh giá lần lượt là 10000, 20000, 50000, 100000, 200000)");

        System.out.print("Nhập số tiền bạn muốn (Nếu không muốn nạp tiếp thì ấn số 1, số 2 để xem số tiền hiện có): ");
        while (checkStatusDeposit){

            currentMoneyInput = inputMoney.nextDouble();
            if (currentMoneyInput == 1){
                checkStatusDeposit = false;
            }
            else if(currentMoneyInput == 2){
                System.out.println("------------------");
                System.out.println("Tổng số tiền bạn hiện có: " + Double.toString(getBalance()) + " VND");
                System.out.println("------------------");
                System.out.print("Nhập lần lượt số tiền bạn muốn (Nếu không muốn nạp tiếp thì ấn số 1, số 2 để xem số tiền hiện có): ");
            }
            else {
                double finalCurrentMoneyInput = currentMoneyInput;
                if(DoubleStream.of(this.LEGAL_MONEY).anyMatch(x -> x == finalCurrentMoneyInput)){
                    this.setBalance(this.getBalance() + finalCurrentMoneyInput);
                }
                else{
                    System.out.println("Bạn nhập sai mệnh giá cho phép!");
                    System.out.println("Mời bạn nhập lại...");
                }
            }
        }
        System.out.println("------------------");
        System.out.println("Tổng số tiền bạn hiện có: " + Double.toString(getBalance()) + " VND");
        System.out.println("------------------");
    }

}

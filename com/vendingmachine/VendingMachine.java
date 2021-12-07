package com.vendingmachine;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class VendingMachine extends Transaction{
    private final DepositMoney depositMoney;
    private final WithdrawMoney withdrawMoney;
    private final SelectionProduct selectionProduct;
    private final CartCheckout cartCheckout;
    private int timePurchaseSameProduct = 0;
    private int lastPurchase = -1;
    private double winRate = 0.1;
    private double budget = 50000;
    private LocalDate currentDay;



    public VendingMachine(){
        this.setBalance(0);
        this.setCurrentDay(java.time.LocalDate.now());
        depositMoney = new DepositMoney();
        withdrawMoney = new WithdrawMoney();
        selectionProduct = new SelectionProduct();
        cartCheckout = new CartCheckout();
    }

    public void run(){
        this.runMachine();
    }

    private void runMachine(){
        Scanner inputChooseOption = new Scanner(System.in);
        int chooseOption;
        while(true){
            boolean checkStatusCustomer = true;
            depositMoney.newCustomer();
            selectionProduct.deleteCart();
            this.setBalance(depositMoney.getBalance());
            while(checkStatusCustomer) {
                System.out.println("------------------");
                System.out.println("Các lựa chọn hiện có: ");
                System.out.println("1. Nạp tiền tiếp.");
                System.out.println("2. Xem số tiền hiện có.");
                System.out.println("3. Chọn nước uống.");
                System.out.println("4. Xem nước uống hiện có.");
                System.out.println("5. Thanh toán.");
                System.out.println("6. Thoát máy bán hàng.");
                System.out.println("------------------");
                System.out.print("Nhập lựa chọn của bạn: ");
                chooseOption = inputChooseOption.nextInt();
                this.checkChangeDate();
                if (chooseOption == 1) {
                    System.out.println("-----------------");
                    depositMoney.currentCustomer();
                    this.setBalance(depositMoney.getBalance());
                }
                else if (chooseOption == 2) {
                    System.out.println("-----------------");
                    System.out.println("Tổng số tiền bạn hiện có: " + Double.toString(getBalance()) + " VND");
                }
                else if (chooseOption == 3) {
                    System.out.println("-----------------");
                    selectionProduct.run();
                }
                else if(chooseOption == 4){
                    System.out.println("-----------------");
                    selectionProduct.getCurrentProduct();
                }
                else if(chooseOption == 5){
                    double[] lastPayment;
                    double checkStatus;
                    System.out.println("-----------------");
                    lastPayment = cartCheckout.payProduct(depositMoney.getBalance(), selectionProduct.getBill(), selectionProduct.getAmountProduct(), this.getBudget(), this.getLastPurchase(), this.getWinRate(), this.getTimePurchaseSameProduct());
                    checkStatus = lastPayment[3];
                    if(checkStatus == 1){
                        this.setBudget(lastPayment[0]);
                        this.setLastPurchase((int) lastPayment[1]);
                        this.setTimePurchaseSameProduct((int) lastPayment[2]);
                        this.runMachine();
                    }
                }
                else if (chooseOption == 6){
                    System.out.println("-----------------");
                    withdrawMoney.deleteCustomer(this.getBalance());
                    this.resetMachine();
                    checkStatusCustomer = false;
                }
                else {
                    System.out.println("-----------------");
                    System.out.println("Bạn đã nhập sai lựa chọn. Vui lòng nhập lại!");
                }
            }

        }
    }


    private void checkChangeDate(){
        if(!Objects.equals(this.getCurrentDay(), LocalDate.now())){
            if(this.getBudget() > 0){
                this.setWinRate(0.5);
            }
            else{
                this.setWinRate(0.1);
            }
            this.setBudget(50000);
        }
    }

    public LocalDate getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(LocalDate currentDay) {
        this.currentDay = currentDay;
    }
    private void resetMachine(){
        this.setBalance(0);
        depositMoney.setBalance(0);
        withdrawMoney.setBalance(0);
    }

    public int getTimePurchaseSameProduct() {
        return timePurchaseSameProduct;
    }

    public void setTimePurchaseSameProduct(int timePurchaseSameProduct) {
        this.timePurchaseSameProduct = timePurchaseSameProduct;
    }

    public int getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(int lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}

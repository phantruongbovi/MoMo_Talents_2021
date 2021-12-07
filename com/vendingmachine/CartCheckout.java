package com.vendingmachine;

import java.util.Random;

public class CartCheckout{
    private double excessCash;
    private double balance;
    private double totalBill;
    private double budget;
    private int lastPurchase;
    private double winRate;
    private int timePurchaseSameProduct;


    public CartCheckout(){
    }

    public double[] payProduct(double balance, double totalBill, int[] amountProduct, double budget, int lastPurchase, double winRate, int timePurchaseSameProduct){
        boolean checkPromotion;
        double checkStatus = 1;
        this.setBalance(balance);
        this.setTotalBill(totalBill);
        this.setBudget(budget);
        this.setLastPurchase(lastPurchase);
        this.setWinRate(winRate);
        this.setTimePurchaseSameProduct(timePurchaseSameProduct);
        this.setExcessCash(balance);

        if (balance < totalBill){
            System.out.println("Số tiền quý khách không đủ thanh toán, vui lòng nạp thêm!");
            System.out.println("Số tiền hiện có: " + Double.toString(balance));
            System.out.println("Tổng số tiền cần thanh toán: " + Double.toString(totalBill));
            checkStatus = 0;
        }
        else{
            checkPromotion = checkPromotion(amountProduct);
            if(checkPromotion){
                this.setBudget(this.getBudget() - this.getTotalBill());
                System.out.println("Chúc mừng bạn đã được mua nước miễn phí!");
                System.out.println("Số tiền thừa là: " + this.getBalance());
            }
            else{
                this.setExcessCash(this.getBalance() - this.getTotalBill());
                System.out.println("Số tiền thừa là: " + this.getExcessCash());
            }

        }
        return new double[]{this.getBudget(), this.getLastPurchase(), this.getTimePurchaseSameProduct(), checkStatus, this.getWinRate()};
    }

    private boolean checkPromotion(int[] amountProduct){
        if(this.getBudget() == 0){
            return false;
        }
        int totalPurchase = 0;
        int indexPurchase = -1;
        for (int i = 0; i < 3 ; i++){
            totalPurchase += amountProduct[i];
            if(amountProduct[i] == 1){
                indexPurchase = i;
            }
            if(totalPurchase > 1){
                this.setTimePurchaseSameProduct(0);
                this.setLastPurchase(-1);
                return false;
            }

        }
        if(totalPurchase == 0){
            return false;
        }
        if (this.getLastPurchase() == -1){
            this.setLastPurchase(indexPurchase);
            this.setTimePurchaseSameProduct(1);
            return false;
        }
        if (amountProduct[this.getLastPurchase()] == 0){
            this.setLastPurchase(indexPurchase);
            this.setTimePurchaseSameProduct(1);
            return false;
        }
        else if(amountProduct[this.getLastPurchase()] == 1 && this.getTimePurchaseSameProduct() < 2){
            this.setTimePurchaseSameProduct(this.getTimePurchaseSameProduct() + 1);
            return false;
        }
        else{
            Random random = new Random();
            if(this.getWinRate() == 0.1){
                int i = random.nextInt(10);
                if(i == 0){
                    this.setLastPurchase(-1);
                    this.setTimePurchaseSameProduct(0);
                    return true;
                }
            }
            else if(this.getWinRate() == 0.5){
                int i = random.nextInt(2);
                if(i == 0){
                    this.setLastPurchase(-1);
                    this.setTimePurchaseSameProduct(0);
                    return true;
                }
            }
            this.setLastPurchase(-1);
            this.setTimePurchaseSameProduct(0);
            return false;
        }
    }

    public int getTimePurchaseSameProduct() {
        return timePurchaseSameProduct;
    }

    public void setTimePurchaseSameProduct(int timePurchaseSameProduct) {
        this.timePurchaseSameProduct = timePurchaseSameProduct;
    }

    private double getExcessCash() {
        return this.balance - this.totalBill;
    }
    private void setExcessCash(double excessCash){
        this.excessCash = excessCash;
    }

    private void setTotalBill(double totalBill){
        this.totalBill = totalBill;
    }

    private double getTotalBill(){
        return this.totalBill;
    }

    private void setBalance(double balance){
        this.balance = balance;
    }

    private double getBalance(){
        return this.balance;
    }

    private void setBudget(double budget){
        this.budget = budget;
    }

    private double getBudget(){
        return  this.budget;
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
}

package com.vendingmachine;

import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class SelectionProduct extends Transaction {
    final protected String PRODUCT[] = {"Coke", "Pepsi", "Soda"};
    private int amountProduct[] = {0, 0, 0};

    public SelectionProduct(){

    }

    public void run(){
        Scanner inputChooseRenewProduct = new Scanner(System.in);
        int chooseRenewProduct;
        boolean chooseRightOptionRenewProduct = true;
        if (this.getBill() == 0){
            this.chooseProduct();
        }
        else{
            this.getCurrentProduct();
            while(chooseRightOptionRenewProduct) {
                System.out.println("Bạn muốn làm mới đồ uống (nhấn số 1) hay cập nhật nước uống (nhấn số 2) hoặc quay trở về trang chủ (nhấn số 3)!");
                chooseRenewProduct = inputChooseRenewProduct.nextInt();
                if (chooseRenewProduct == 1) {
                    this.deleteCart();
                    this.run();
                }
                else if (chooseRenewProduct == 2) {
                    this.updateCart();
                }
                else if(chooseRenewProduct == 3){
                    break;
                }
                else {
                    System.out.println("Bạn đã nhập sai vui lòng nhập lại!!!!");
                }
            }


        }
    }

    private void updateCart(){
        Scanner inputIndexProduct = new Scanner(System.in);
        Scanner inputAmountProduct = new Scanner(System.in);
        int indexProduct;
        int amountProduct;
        boolean checkStatus = true;
        while(checkStatus){
            System.out.println("Coke nhấn phím 1");
            System.out.println("Pepsi nhấn phím 2");
            System.out.println("Soda nhấn phím 3");
            System.out.print("Mời bạn nhập chỉ số sản phẩm muốn cập nhật (nhấn phím 4 để quay về):");
            indexProduct = inputIndexProduct.nextInt();
            if(indexProduct == 1){
                System.out.print("Nhập số lượng Coke bạn muốn mua: " );
                amountProduct = inputAmountProduct.nextInt();
                this.setAmountProduct(0,amountProduct);
            }
            else if(indexProduct == 2){
                System.out.print("Nhập số lượng Pepsi bạn muốn mua: " );
                amountProduct = inputAmountProduct.nextInt();
                this.setAmountProduct(1,amountProduct);
            }
            else if(indexProduct == 3){
                System.out.print("Nhập số lượng Soda bạn muốn mua: " );
                amountProduct = inputAmountProduct.nextInt();
                this.setAmountProduct(2,amountProduct);
            }
            else if(indexProduct == 4){
                break;
            }
            else{
                System.out.println("Bạn đã nhập sai vui lòng nhập lại!!!!");
            }
            this.getCurrentProduct();

        }
    }

    public void deleteCart(){
        this.setBalance(0);
        for(int item = 0; item < 3; item++){
            this.setAmountProduct(item, 0);
        }
    }

    private void chooseProduct(){
        Scanner inputAmountProduct = new Scanner(System.in);
        int amountProduct;
        System.out.println("Bạn vui lòng chọn số lượng nước cho từng loại: ");
        for(int item = 0; item < 3; item++){
            System.out.print(this.PRODUCT[item] + ": ");
            amountProduct = inputAmountProduct.nextInt();
            this.updateAmountProduct(item, amountProduct);
        }
        this.getCurrentProduct();
    }

    private void updateAmountProduct(int indexProduct, int amountProduct){
        this.amountProduct[indexProduct] += amountProduct;
    }

    private void setAmountProduct(int indexProduct, int amountProduct){
        this.amountProduct[indexProduct] = amountProduct;
    }

    public void getCurrentProduct(){
        System.out.println("-----------------");
        System.out.println("Thức uống hiện có là: ");
        System.out.println("Coke: " + Integer.toString(this.amountProduct[0]));
        System.out.println("Pepsi: " + Integer.toString(this.amountProduct[1]));
        System.out.println("Soda: " + Integer.toString(this.amountProduct[2]));
        System.out.println("Tổng số tiền cần thanh toán là: " +  Double.toString(this.getBill()));
        System.out.println("-----------------");
    }

    public int[] getAmountProduct(){
        return this.amountProduct;
    }

    public double getBill(){
        double totalBill = 0;
        for(int item = 0 ; item < 3; item++){
            totalBill += this.amountProduct[item]*this.getValueOfProduct(item);
        }
        return totalBill;
    }

    private double getValueOfProduct(int indexProduct){
        double valueOfProduct = 0;
        if (indexProduct == 0){
            valueOfProduct = 10000;
        }
        else if (indexProduct == 1){
            valueOfProduct = 10000;
        }
        else if (indexProduct == 2){
            valueOfProduct = 30000;
        }
        return valueOfProduct;
    }

}

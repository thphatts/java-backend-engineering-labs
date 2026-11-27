package com.thphatts.concurrency.day003;

public class UnsafeInventory {
    private int stock;
    public UnsafeInventory(int stock) {
        this.stock = stock;
    }
    public boolean reserve(){
        if(stock > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                Thread.currentThread().interrupt();
                return false;
            }
            stock--;
            return true;
        }
        return false;
    }
    public int getStock() {
        return stock;
    }

}

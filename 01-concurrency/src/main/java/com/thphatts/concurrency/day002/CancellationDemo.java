package com.thphatts.concurrency.day002;

public class CancellationDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread orderWorker = new Thread(new OrderWorker());
        orderWorker.start();
        Thread.sleep(3500);
        orderWorker.interrupt();
        orderWorker.join();
        System.out.println(orderWorker.getState());
    }
}

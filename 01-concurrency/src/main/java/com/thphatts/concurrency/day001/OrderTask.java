package com.thphatts.concurrency.day001;
public class OrderTask implements Runnable {
    private final String orderId;
    public OrderTask(String orderId) {
        this.orderId = orderId;
    }
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " processing " + orderId);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        System.out.println(threadName + " completed " + orderId);
    }
}
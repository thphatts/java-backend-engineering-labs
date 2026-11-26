package com.thphatts.concurrency.day002;

public class OrderWorker implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Processing Order");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Worker interrupted");
            }
        }
        System.out.println("Worker stopped");
    }
}

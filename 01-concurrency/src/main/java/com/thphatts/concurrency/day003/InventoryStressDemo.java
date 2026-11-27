package com.thphatts.concurrency.day003;

import java.util.concurrent.atomic.AtomicInteger;

public class InventoryStressDemo {
    public static void main(String[] args) throws InterruptedException {
        UnsafeInventory inventory = new UnsafeInventory(100);
        AtomicInteger successCount = new AtomicInteger(0);
        Thread[] threads = new Thread[150];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(() -> {
                boolean success = inventory.reserve();
                System.out.println(Thread.currentThread().getName() + ": " + success);
                if (success) {
                    successCount.incrementAndGet();
                }
            },"worker-"+i);
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Success Count Stock: "+successCount.get());
        System.out.println("Stock remaining: "+inventory.getStock());
        System.out.println("Failed Count Stock: "+ (threads.length - successCount.get()));
    }
}

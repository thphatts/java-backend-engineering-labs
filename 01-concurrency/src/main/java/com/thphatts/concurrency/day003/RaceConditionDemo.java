package com.thphatts.concurrency.day003;

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        UnsafeInventory inventory = new UnsafeInventory(1);
        Thread worker1 = new Thread(() -> {
            boolean success = inventory.reserve();

            System.out.println(
                    Thread.currentThread().getName() + ": " + success
            );
        }, "Worker-1");
        Thread worker2 = new Thread(() -> {
            boolean success = inventory.reserve();

            System.out.println(
                    Thread.currentThread().getName() + ": " + success
            );
        }, "Worker-2");

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();
        System.out.println("Remaining stock: "
                + inventory.getStock());
    }
}

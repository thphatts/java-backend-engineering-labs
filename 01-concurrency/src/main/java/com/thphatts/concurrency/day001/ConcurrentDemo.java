package com.thphatts.concurrency.day001;

public class ConcurrentDemo {
    public static void main(String[] agrs) throws InterruptedException {
        OrderTask order1 = new OrderTask("ORD-001");
        OrderTask order2 = new OrderTask("ORD-002");
        OrderTask order3 = new OrderTask("ORD-003");

        Thread t1 = new Thread(order1, "worker-1");
        Thread t2 = new Thread(order2, "worker-2");
        Thread t3 = new Thread(order3, "worker-3");

        long startTime = System.nanoTime();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTime = System.nanoTime();

        System.out.println(
                "Elapsed: " + (endTime - startTime) / 1_000_000.0 + " ms");
    }
}
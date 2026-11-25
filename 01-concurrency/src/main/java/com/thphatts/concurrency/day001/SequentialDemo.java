package com.thphatts.concurrency.day001;

public class SequentialDemo {

    public static void main(String[] args) {
        OrderTask orderTask1 = new OrderTask("ORD-001");
        OrderTask orderTask2 = new OrderTask("ORD-002");
        OrderTask orderTask3 = new OrderTask("ORD-003");

        long startTime = System.nanoTime();
        orderTask1.run();
        orderTask2.run();
        orderTask3.run();

        long endTime = System.nanoTime();

        double elapsedMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("Elapsed time: " + elapsedMs + " ms");
    }
}
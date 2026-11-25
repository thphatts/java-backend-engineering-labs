package com.thphatts.concurrency;

public class Main {
    public static void main(String[] args) {
        System.out.println("EventFlow Engineering Labs");
        Thread current = Thread.currentThread();
        System.out.println("Current thread: " + current.getName());
        System.out.println("Current thread ID: " + current.threadId());
        System.out.println("Current thread priority: " + current.getPriority());
        System.out.println("Is virtula: " + current.isVirtual());
        System.out.println("Avaible processor: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Process ID: " + ProcessHandle.current().pid());
        try {
            Thread.sleep(60_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Runnable task = () -> {
            System.out.println(
                    Thread.currentThread().getName());
        };
        Thread worker = new Thread(task, "order-worker");
        worker.start();
    }
}
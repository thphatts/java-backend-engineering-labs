package com.thphatts.concurrency;

import com.thphatts.concurrency.day002.OrderWorker;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.State.TERMINATED;
import static java.lang.Thread.State.TIMED_WAITING;
import static org.junit.jupiter.api.Assertions.*;

public class OrderWorkerTest {
    @Test
    void shouldTerminateAfterInterrupt() throws InterruptedException {
        // Arrange
        Thread orderWorker = new Thread(new OrderWorker());
        orderWorker.setDaemon(true);
        orderWorker.start();
        try {
            // Act
            orderWorker.interrupt();
            orderWorker.join(2000);

            // Assert
            assertFalse(orderWorker.isAlive());
            assertEquals(TERMINATED, orderWorker.getState());

        } finally {
            if(orderWorker.isAlive()) {
                orderWorker.interrupt();
                orderWorker.join(2000);
            }
        }
    }
    @Test
    void shouldTerminateWhenInterruptedDuringSleep() throws InterruptedException {
        // Arrange
        Thread orderWorker = new Thread(new OrderWorker());
        orderWorker.setDaemon(true);
        orderWorker.start();
        try {
            // Await
            long deadline = (System.nanoTime() + TimeUnit.SECONDS.toNanos(2));
            while(orderWorker.isAlive() && orderWorker.getState() != TIMED_WAITING && System.nanoTime() - deadline  < 0) {
                Thread.sleep(10);
            }
            // Action
            assertEquals(TIMED_WAITING, orderWorker.getState(), "Worker did not enter TIMED_WAITING");
            orderWorker.interrupt();
            orderWorker.join(2000);
            // Assert
            assertEquals(TERMINATED, orderWorker.getState());
            assertFalse(orderWorker.isAlive());

        } finally {
            if(orderWorker.isAlive()) {
                orderWorker.interrupt();
                orderWorker.join(2000);
            }
        }

    }
}

package com.thphatts.concurrency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ThreadExecuteTest {
    @Test
    void runShouldExecuteOnCallingThread() {

        Thread callingThread = Thread.currentThread();
        Thread[] actualThread = new Thread[1];
        Runnable task = () -> {
            actualThread[0] = Thread.currentThread();
        };
        Thread worker = new Thread(task);
        worker.run();
        assertSame(callingThread, actualThread[0]);
    }

    @Test
    void startShouldExecuteOnNewThread() throws  InterruptedException {
        // Arrange
        Thread callingThread = Thread.currentThread();
        Thread[] actualThread = new Thread[1];
        Runnable task = () -> {
            actualThread[0] = Thread.currentThread();
        };
        Thread worker = new Thread(task);
        // Action
        worker.start();
        worker.join();

        // Assert
        assertNotSame(callingThread, actualThread[0]);
        assertSame(worker, actualThread[0]);
    }

}

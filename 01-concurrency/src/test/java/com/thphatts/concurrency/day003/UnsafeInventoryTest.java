package com.thphatts.concurrency.day003;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class UnsafeInventoryTest {
    @Test
    void shouldDecreaseStockWhenReservationSucceeds() {
        // Arrange
        UnsafeInventory inventory = new UnsafeInventory(5);

        // Act
        boolean success = inventory.reserve();

        // Assert
        assertTrue(success);
        assertEquals(4, inventory.getStock());
    }

    @Test
    void shouldRejectReservationWhenStockIsEmpty() {
        // Arrange
        UnsafeInventory inventory = new UnsafeInventory(0);
        // Act
        boolean success = inventory.reserve();
        // Assert
        assertFalse(success);
        assertEquals(0, inventory.getStock());
    }
    @Test
    void shouldRejectReservationsAfterStockIsExhausted() {
        int initialStock = 2;
        UnsafeInventory inventory = new UnsafeInventory(initialStock);

        for (int i = 0; i < initialStock; i++) {
            assertTrue(inventory.reserve());
            assertEquals(initialStock - i - 1, inventory.getStock());
        }

        assertFalse(inventory.reserve());
        assertEquals(0, inventory.getStock());
    }

    @Test
    void shouldNotOversellWhenReservationsAreConcurrent()
            throws InterruptedException {

        // Arrange
        UnsafeInventory inventory = new UnsafeInventory(1);
        AtomicInteger successCount = new AtomicInteger(0);

        // Act
        Runnable reservationTask = () -> {
            if(inventory.reserve()){
                successCount.incrementAndGet();
            }
        };

        Thread worker1 = new Thread(reservationTask);
        Thread worker2 = new Thread(reservationTask);

        worker1.start();
        worker2.start();
        worker1.join();
        worker2.join();
        // Assert
        assertAll(
                () -> assertEquals(1, successCount.get()),
                () -> assertTrue(inventory.getStock() >= 0),
                () -> assertEquals(1 - inventory.getStock(), successCount.get()),
                () -> assertEquals(0, inventory.getStock())
        );
    }
}

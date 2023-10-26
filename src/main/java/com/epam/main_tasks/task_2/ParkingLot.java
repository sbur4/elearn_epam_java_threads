package com.epam.main_tasks.task_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {
    private final AtomicInteger occupiedSpaces = new AtomicInteger(0);
    private final Lock parkingLock = new ReentrantLock();
    private final int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean parkCar() {
        boolean parked = false;
        try {
            if (parkingLock.tryLock(1, TimeUnit.SECONDS) && (occupiedSpaces.get() < capacity)) {
                    occupiedSpaces.incrementAndGet();
                    parked = true;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            parkingLock.unlock();
        }
        return parked;
    }

    public void exitCar() {
        occupiedSpaces.decrementAndGet();
    }
}
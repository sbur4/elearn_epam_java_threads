package com.epam.main_tasks.task_3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition trainsCanEnter = lock.newCondition();
    private int trainCount = 0;

    public void enterTunnel(String trainName, int direction) throws InterruptedException {
        lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            while (trainCount > 0) {
                int maxWaitTime = 10;
                if (System.currentTimeMillis() - startTime > maxWaitTime * 1000) {
                    System.out.println(trainName + " waited too long and is redirected.");
                    return;
                }
                trainsCanEnter.await(1, TimeUnit.SECONDS);
            }
            trainCount++;
            System.out.println(trainName + " enters the tunnel in direction " + direction);
        } finally {
            lock.unlock();
        }
    }

    public void exitTunnel(String trainName, int direction) {
        lock.lock();
        try {
            trainCount--;
            System.out.println(trainName + " exits the tunnel in direction " + direction);
            trainsCanEnter.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
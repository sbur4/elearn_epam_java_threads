package com.epam.main_tasks.task_5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Airport {
    private final int terminalCount = 3;
    private final int airstairCount = 2;
    protected static final int MIN_DESTINATION = 4;
    protected static final int MAX_DESTINATION = 6;
    protected static final int MIN_RANGE = 2;
    protected static final int MAX_RANGE = 3;

    private final Lock lock = new ReentrantLock();
    private final Condition[] terminals = new Condition[terminalCount];
    private final Condition[] airstairs = new Condition[airstairCount];

    public Airport() {
        for (int i = 0; i < terminalCount; i++) {
            terminals[i] = lock.newCondition();
        }
        for (int i = 0; i < airstairCount; i++) {
            airstairs[i] = lock.newCondition();
        }
    }

    public void land(int destination, int range) {
        lock.lock();
        try {
            while (destination < MIN_DESTINATION || destination > MAX_DESTINATION ||
                    range < MIN_RANGE || range > MAX_RANGE) {
                try {
                    System.out.println("Waiting for a suitable terminal/airstair...");
                    terminals[0].await();
                    airstairs[0].await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Plane with destination " + destination + " and range " + range + " landed.");
        } finally {
            lock.unlock();
        }
    }

    public void depart(int destination, int range) {
        lock.lock();
        try {
            System.out.println("Plane with destination " + destination + " and range " + range + " departed.");
            terminals[0].signal();
            airstairs[0].signal();
        } finally {
            lock.unlock();
        }
    }
}
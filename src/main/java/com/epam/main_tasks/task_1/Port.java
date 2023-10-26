package com.epam.main_tasks.task_1;

public class Port {
    private final int maxCapacity;
    private int currentCapacity;

    public Port(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = maxCapacity;
    }

    public synchronized void load(int amount) {
        if (currentCapacity >= amount) {
            currentCapacity -= amount;
            System.out.println(generateMessage(amount));
        } else {
            System.out.println(Thread.currentThread().getName() + " can't load " + amount
                    + " containers. Not enough containers at the port.");
        }
    }

    public synchronized void unload(int amount) {
        if (currentCapacity + amount <= maxCapacity) {
            currentCapacity += amount;
            System.out.println(generateMessage(amount));
        } else {
            System.out.println(Thread.currentThread().getName() + " can't load " + amount + " containers. Port crowded.");
        }
    }

    private String generateMessage(int amount) {
        return Thread.currentThread().getName() + " was loaded " + amount + " containers. "
                + "Left " + currentCapacity + " containers at the port.";
    }
}
package com.epam.main_tasks.task_5;

public class Plane implements Runnable {
    private final Airport airport;
    private final int destination;
    private final int range;

    public Plane(Airport airport, int destination, int range) {
        this.airport = airport;
        this.destination = destination;
        this.range = range;
    }

    @Override
    public void run() {
        airport.land(destination, range);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        airport.depart(destination, range);
    }
}
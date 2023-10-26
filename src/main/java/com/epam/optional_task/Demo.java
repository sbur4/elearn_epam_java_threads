package com.epam.optional_task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    public static void main(String[] args) {
        Airport airport = new Airport();
        ExecutorService executor = Executors.newFixedThreadPool(15);

        for (int i = 1; i <= 10; i++) {
            Runnable takingOffPlane = new Plane(i, airport, true);
            executor.execute(takingOffPlane);
        }

        for (int i = 11; i <= 15; i++) {
            Runnable landingPlane = new Plane(i, airport, false);
            executor.execute(landingPlane);
        }

        executor.shutdown();
    }
}
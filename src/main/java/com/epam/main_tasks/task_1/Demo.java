package com.epam.main_tasks.task_1;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * use Semaphore
 */
public class Demo {

    public static void main(String[] args) {
        int portCapacity = 100;
        int dockCount = 5;
        int arrivedShips = 10;

        Port port = new Port(portCapacity);
        Dock docks = new Dock(dockCount);

        ExecutorService executor = Executors.newFixedThreadPool(arrivedShips);

        for (int i = 1; i <= arrivedShips; i++) {
            Ship ship = new Ship("The ship " + i, port, new Random().nextInt(30) + 1, docks);
            executor.execute(ship);
        }

        executor.shutdown();
    }
}
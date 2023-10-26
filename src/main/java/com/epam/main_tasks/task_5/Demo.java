package com.epam.main_tasks.task_5;

/**
 * use Monitor(Lock+Condition)
 */
public class Demo {
    public static void main(String[] args) {
        Airport airport = new Airport();

        int numPlanes = 5;

        for (int i = 1; i <= numPlanes; i++) {
            int destination = (int) (Math.random() * (Airport.MAX_DESTINATION - Airport.MIN_DESTINATION + 1)) + Airport.MIN_DESTINATION;
            int range = (int) (Math.random() * (Airport.MAX_RANGE - Airport.MIN_RANGE + 1)) + Airport.MIN_RANGE;

            Runnable plane = new Plane(airport, destination, range);
            new Thread(plane).start();
        }
    }
}

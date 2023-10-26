package com.epam.main_tasks.task_2;

/**
 * use Lock
 */
public class Demo {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5); // 5 parking spaces

        int numCars = 10;
        for (int i = 1; i <= numCars; i++) {
            Runnable car = new Car(i, parkingLot);
            new Thread(car).start();
        }
    }
}

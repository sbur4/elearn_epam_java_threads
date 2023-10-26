package com.epam.main_tasks.task_2;

public class Car implements Runnable {
    private final int id;
    private final ParkingLot parkingLot;

    public Car(int id, ParkingLot parkingLot) {
        this.id = id;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        System.out.println("Car " + id + " is trying to park.");
        if (parkingLot.parkCar()) {
            System.out.println("Car " + id + " has parked.");
            try {
                Thread.sleep(2000); // Simulate car being parked for 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            parkingLot.exitCar();
            System.out.println("Car " + id + " has left the parking lot.");
        } else {
            System.out.println("Car " + id + " couldn't find a parking space and left.");
        }
    }
}
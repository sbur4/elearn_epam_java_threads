package com.epam.main_tasks.task_1;

import java.util.Random;

class Ship implements Runnable {
    private final Random random = new Random();
    private final String shipName;
    private final Port port;
    private final int shipCapacity;
    private final Dock docks;

    public Ship(String shipName, Port port, int shipCapacity, Dock docks) {
        this.shipName = shipName;
        this.port = port;
        this.shipCapacity = shipCapacity;
        this.docks = docks;
    }

    @Override
    public void run() {
        System.out.println(shipName + " arrived to the port.");
        if (docks.blockDock()) {
            int loadAmount = this.random.nextInt(shipCapacity) + 1;
            int unloadAmount = this.random.nextInt(shipCapacity) + 1;

            port.load(loadAmount);
            port.unload(unloadAmount);

            docks.releaseDock();
        } else {
            System.out.println(shipName + " cannot access the pier. Returns later.");
        }
    }
}
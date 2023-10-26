package com.epam.optional_task;

import java.util.concurrent.TimeUnit;

class Plane implements Runnable {
    private final int id;
    private final Airport airport;
    private final boolean isTakingOff;

    public Plane(int id, Airport airport, boolean isTakingOff) {
        this.id = id;
        this.airport = airport;
        this.isTakingOff = isTakingOff;
    }

    @Override
    public void run() {
        if (isTakingOff) {
            System.out.printf("The plane {%d} began moving on the lane to take off%n", id);
        } else {
            System.out.printf("The plane {%d} began approaching for landing%n", id);
        }

        int runwayId = airport.requestRunway();

        if (runwayId != -1) {
            if (isTakingOff) {
                System.out.printf("The plane {%d} on lane {%d} and starting take off%n", id, runwayId);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.printf("The plane {%d} took off%n", id);
            } else {
                System.out.printf("The plane {%d} is landed on the lane {%d}%n", id, runwayId);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.printf("The plane {%d} is landed successfully%n", id);
            }

            airport.releaseRunway(runwayId);
            System.out.printf("The lane {%d} is free%n", runwayId);

        } else {
            System.out.printf("The plane {%d} can't take off/landing, no lanes available%n", id);
        }
    }
}
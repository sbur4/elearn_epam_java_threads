package com.epam.optional_task;

class Airport {
    private static final int RUNWAY_COUNT = 5;
    private final boolean[] runways = new boolean[RUNWAY_COUNT];

    public synchronized int requestRunway() {
        for (int i = 0; i < RUNWAY_COUNT; i++) {
            if (!runways[i]) {
                runways[i] = true;
                return i;
            }
        }
        return -1; // No available runway
    }

    public synchronized void releaseRunway(int runwayId) {
        runways[runwayId] = false;
    }
}
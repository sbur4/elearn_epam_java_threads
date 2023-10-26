package com.epam.main_tasks.task_1;

import java.util.concurrent.Semaphore;

public class Dock {
    private final Semaphore semaphore;

    public Dock(int dockCount) {
        semaphore = new Semaphore(dockCount);
    }

    public boolean blockDock() {
        return semaphore.tryAcquire();
    }

    public void releaseDock() {
        semaphore.release();
    }
}
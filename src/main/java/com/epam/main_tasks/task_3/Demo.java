package com.epam.main_tasks.task_3;

import java.util.Random;

/**
 * use Mutex(ReentrantLock + Condition)
 */
public class Demo {
    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel();
        int numTrains = 10;

        for (int i = 1; i <= numTrains; i++) {
            int direction = new Random().nextInt(2) + 1;
            Runnable train = new Train("Train " + i, tunnel, direction);
            new Thread(train).start();
        }
    }
}

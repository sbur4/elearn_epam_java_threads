package com.epam.main_tasks.task_4;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Auction {
    private final Phaser phaser = new Phaser(1);
    private final Lock lock = new ReentrantLock();
    private int currentLot = 1;
    private final int maxLots = 3;
    private final int maxBids = 3;

    public void startAuction() {
        while (currentLot <= maxLots) {
            System.out.println("Lot " + currentLot + " is open for participants.");
            phaser.register();
            for (int i = 0; i < maxBids; i++) {
                new Thread(new Participant("Participant " + (i + 1))).start();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("Lot " + currentLot + " is closed for participants.");
            currentLot++;
        }
    }

    private class Participant implements Runnable {
        private final String name;
        private int currentBid;

        public Participant(String name) {
            this.name = name;
            this.currentBid = 0;
        }

        @Override
        public void run() {
            for (int i = 0; i < maxBids; i++) {
                lock.lock();
                try {
                    currentBid += 10;
                    System.out.println(name + " places a bid of " + currentBid + " on Lot " + currentLot);
                } finally {
                    lock.unlock();
                }

                if (phaser.getRegisteredParties() == maxBids) {
                    phaser.arriveAndDeregister();
                } else {
                    phaser.arriveAndAwaitAdvance();
                }
            }

            try {
                int maxPaymentTimeSeconds = 10;
                Thread.sleep(TimeUnit.SECONDS.toMillis(maxPaymentTimeSeconds));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            lock.lock();
            try {
                if (currentLot == maxLots) {
                    System.out.println(name + " wins Lot " + currentLot + " with a final bid of " + currentBid);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
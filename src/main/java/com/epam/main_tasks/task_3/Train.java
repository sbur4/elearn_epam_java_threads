package com.epam.main_tasks.task_3;

public class Train implements Runnable {
    private final String name;
    private final Tunnel tunnel;
    private final int direction;

    public Train(String name, Tunnel tunnel, int direction) {
        this.name = name;
        this.tunnel = tunnel;
        this.direction = direction;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 1000)); // Simulate train arrival
            tunnel.enterTunnel(name, direction);
            Thread.sleep((int) (Math.random() * 1000)); // Simulate time spent in the tunnel
            tunnel.exitTunnel(name, direction);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

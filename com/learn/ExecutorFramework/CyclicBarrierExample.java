package com.learn.ExecutorFramework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class SubSystem implements Runnable{
    private String name;
    private int initializationTime;
    private CyclicBarrier barrier;

    public SubSystem(String name, int initializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }
    @Override
    public void run() {
        try {
            System.out.println(name + " initialization started");
            Thread.sleep(initializationTime);
            System.out.println(name + " initialization completed");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {

        }
    }
}
public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("All system is up and running. System startup complete");
            }
        });

        Thread webServerThread = new Thread(new SubSystem("web server", 4000, barrier));
        Thread cacheThread = new Thread(new SubSystem("cache server", 3000, barrier));
        Thread messagingThread = new Thread(new SubSystem("messaging server", 5000, barrier));
        Thread dataBaseThread = new Thread(new SubSystem("database server", 1000, barrier));

        webServerThread.start();
        cacheThread.start();
        messagingThread.start();
        dataBaseThread.start();

        barrier.reset();
        // we can wait for all the task to finish its execution. it waits for all worker thread to finish
//        barrier.getParties();
    }
}

package com.learn.ExecutorFramework;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {

        System.out.println("count down example from 10 to 0");
        System.out.println("current time:" + Calendar.getInstance().get(Calendar.SECOND));
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()-> System.out.println("task executed"), 5,5, TimeUnit.SECONDS);
        scheduler.schedule(()-> {
            System.out.println("initializing shutdown");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);
        for (int i = 10; i >= 0; i--) {
            scheduler.schedule(new Task1(i), 10 - i, TimeUnit.SECONDS);
        }

        scheduler.shutdown();
    }
}
class Task1 implements Runnable{

    private int num;
    public Task1(int num) {
        this.num = num;
    }
    @Override
    public void run() {
        System.out.println("Number " + num + " current time:" + Calendar.getInstance().get(Calendar.SECOND));
    }
}

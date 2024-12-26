package com.learn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnfairLockExample {
    private final Lock unfairLock = new ReentrantLock();

    private final Lock fairLock = new ReentrantLock(true);
    // this fair flag will make sure, the resource is accessed in the order in which it is called
    // also it makes sure that every thread gets chance to execute and access the resource
    public void accessResource() {
        unfairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " accessing the resource");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " release the lock.");
            unfairLock.unlock();
        }
    }
    public static void main(String[] args) {
        UnfairLockExample example = new UnfairLockExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResource();
            }
        };
        Thread thread1 = new Thread(task, "Thread1");
        Thread thread2 = new Thread(task,"Thread2");
        Thread thread3 = new Thread(task, "Thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

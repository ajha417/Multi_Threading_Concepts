package com.learn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;
    private final Lock lock = new ReentrantLock();
//    public synchronized void withdrawal(int amount) {
//        System.out.println(Thread.currentThread().getName() + " attempting to withdraw amount: " + amount);
//        if (balance >= amount) {
//            System.out.println(Thread.currentThread().getName() + " proceeding withdrawal...");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//
//            }
//            balance -= amount;
//            System.out.println("Withdrawal completed and amount left is: " + balance);
//        } else {
//            System.out.println(Thread.currentThread().getName() + " insufficient balance");
//        }
//    }
    public void withdrawal(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw amount: " + amount);
        try{
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal...");
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " withdrawal completed and amount remaining is:" + balance);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("Insufficient balance...");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " couldn't acquire lock, please try again later!!!");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Interrupted");
        }
    }
}

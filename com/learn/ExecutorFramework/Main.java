package com.learn.ExecutorFramework;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
//        Thread[] threads = new Thread[9];
//        for (int i = 1; i < 10; i++) {
//            int finalI = i;
//            threads[i-1] = new Thread(
//                    () -> {
//                        factorial(finalI);
//                    }
//            );
//            threads[i-1].start();
//        }
//        for (Thread thread: threads) {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

        // using Executor service
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        for (int i = 1; i < 10; i++) {
//            int finalI = i;
//            executor.submit(
//                    () -> {
//                        factorial(finalI);
//                    }
//            );
//        }
//        executor.shutdown();

        //to wait for next lines to execute we can await termination
//        try {
//            while (!(executor.awaitTermination(100, TimeUnit.MILLISECONDS))) {
//                System.out.println("Waiting...");
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        submit method inside return Future which means we can do certain task based on that
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(()->"hello");

        System.out.println(future.get());
        if (future.isDone()) {
            System.out.println("Completed task");
        }
        executorService.shutdown();
        System.out.println("time taken:" + (System.currentTimeMillis() - startTime));
    }

    public static void factorial(int num) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int result = 1;
        for (int i = 1; i <= num; i ++) {
            result *= i;
        }
        System.out.println("factorial is:" + result);
    }
}

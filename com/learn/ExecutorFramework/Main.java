package com.learn.ExecutorFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args)  {
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
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<?> future = executorService.submit(()->"hello");
//
//        System.out.println(future.get());
//        if (future.isDone()) {
//            System.out.println("Completed task");
//        }
//        executorService.shutdown();


        // taking the example of invoke all method

        Callable<Integer> callable1 = () -> {
            Thread.sleep(1000);
            System.out.println("task1");
            return 1;
        };

        Callable<Integer> callable2 = () -> {
            Thread.sleep(1000);
            System.out.println("task2");
            return 2;
        };

        Callable<Integer> callable3 = () -> {
            Thread.sleep(1000);
            System.out.println("task3");
            return 3;
        };

        List<Callable<Integer>> taskList = Arrays.asList(callable1, callable2, callable3);

        ExecutorService service = Executors.newFixedThreadPool(2);
//        List<Future<Integer>> futures  = service.invokeAll(taskList);
//        for (Future<Integer> future: futures) {
//            future.get();
//        }

        // invokeAll() method has 2 version. first it takes collections of tasks. second it takes collection of tasks as well as time and unit
        List<Future<Integer>> futureList = null;
        try {
            futureList = service.invokeAll(taskList, 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

        }

        assert futureList != null;
        for (Future<Integer> future: futureList) {
            try {
                System.out.println(future.get());
            } catch (CancellationException e) {

            }catch (InterruptedException e) {

            } catch (ExecutionException e) {

            }
        }

        service.shutdown();
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

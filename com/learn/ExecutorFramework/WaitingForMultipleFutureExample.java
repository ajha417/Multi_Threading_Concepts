package com.learn.ExecutorFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class Task implements Callable<Integer>{

    int taskId;
    public Task(int taskId) {
        this.taskId = taskId;
    }
    @Override
    public Integer call() throws Exception {
        int sleepTime = new Random().nextInt(3) + 1;
        System.out.println("Task:" + taskId + " waiting for: " + sleepTime + " seconds");
        Thread.sleep(sleepTime * 1000);
        return taskId*10;
    }
}
public class WaitingForMultipleFutureExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            futureList.add(service.submit(new Task(i)));
        }
        service.awaitTermination(20, TimeUnit.SECONDS);
        service.shutdown();
        for (Future<Integer> future: futureList) {
            try {
                Integer result  = future.get();
                System.out.println("task completed with result:" + result);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread was interrupted");
            } catch (ExecutionException e) {
                System.out.println("Task failed");
            }
        }
    }
}

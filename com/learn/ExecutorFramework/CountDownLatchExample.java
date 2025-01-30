package com.learn.ExecutorFramework;

import java.util.concurrent.*;

//class DependentService implements Callable<String>{
//
//    @Override
//    public String call() throws Exception {
//        System.out.println("executing:" + Thread.currentThread().getName());
//        Thread.sleep(2000);
//        return "ok";
//    }
//}

class DependentService implements Runnable{

    private CountDownLatch latch;
    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("executing:" + Thread.currentThread().getName() + " service started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown(); // to finish latch its execution
        }
    }
}

public class CountDownLatchExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numberOfThreads = 3;
//        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
//       Future<String> future1 =  service.submit(new DependentService());
//       Future<String> future2 =  service.submit(new DependentService());
//       Future<String> future3 =  service.submit(new DependentService());
//
//       future1.get();
//       future2.get();
//       future3.get();
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new DependentService(latch)).start();
        }

        latch.await();

        System.out.println("All dependent service finished executing its tasks. Main thread restarting");
//        service.shutdown();
    }
}

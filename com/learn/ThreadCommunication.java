package com.learn;

class SharedResource {

    private int data;
    private boolean hasData;
    public void consume() {

    }

    public void produce(int data) {

    }
}

class Producer implements Runnable {

    private SharedResource resource;
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.produce(i);
            System.out.println("produced: " + i);
        }
    }
}
class Consumer implements Runnable {

    private SharedResource resource;
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

        }
    }
}
public class ThreadCommunication {
    public static void main(String[] args) {

    }
}

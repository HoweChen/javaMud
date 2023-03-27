package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AsyncQueueExample {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        //生产者线程
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    String message = "Hello World!";
                    queue.put(message);
                    System.out.println("Produced: " + message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //消费者线程
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    String message = queue.take();
                    System.out.println("Consumed: " + message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
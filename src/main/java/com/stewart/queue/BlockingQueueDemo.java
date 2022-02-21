package com.stewart.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Stewart
 * @create 2022/2/21
 * @Description
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("1"));
        System.out.println(blockingQueue.add("2"));
        System.out.println(blockingQueue.add("3"));
        //Exception in thread "main" java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println("=================");


        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("w"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println("=================");


        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }
}

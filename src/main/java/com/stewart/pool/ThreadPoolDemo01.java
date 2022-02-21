package com.stewart.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Stewart
 * @create 2022/2/21
 * @Description  线程池的演示
 */
public class ThreadPoolDemo01 {
    public static void main(String[] args) {
        //一池n线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        //一池一线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        //一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 50; i++) {
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        }finally {
            threadPool3.shutdown();
        }


    }
}

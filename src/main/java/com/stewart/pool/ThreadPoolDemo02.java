package com.stewart.pool;

import java.util.concurrent.*;

/**
 * @author Stewart
 * @create 2022/2/21
 * @Description  自定义线程池的创建
 */
public class ThreadPoolDemo02 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, 5, 2L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            for (int i = 0; i < 50; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        }finally {
            threadPool.shutdown();
        }
    }
}

package com.stewart.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Stewart
 * @create 2022/2/21
 * @Description
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用，没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"completableFuture1");
        });
        completableFuture1.get();


        //异步调用，有返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"completableFuture2");
//            int i = 1/0;
            return 1025;
        });
        completableFuture2.whenComplete((t,u)->{
            //t是方法的返回值
            System.out.println("--t"+t);
            //异常的返回值信息
            System.out.println("--u"+u);
        });
        completableFuture2.get();
    }
}

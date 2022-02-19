package com.stewart.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Stewart
 * @create 2022/2/19
 * @Description 比较Runable和Callable接口
 */
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread2 implements Callable{

    @Override
    public Object call() throws Exception {
        return 200;
    }
}
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用Runable的方式
        new Thread(new MyThread1(),"A").start();

        //Callable的方式
        //需要通过FutureTask
        FutureTask futureTask1 = new FutureTask<>(new MyThread2());

        //简写
        FutureTask futureTask2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+"come in callable");
            return 1024;
        });

        //创建一个线程
        new Thread(futureTask2,"lucy").start();
        new Thread(futureTask1,"mary").start();


        while (!futureTask2.isDone()){
            System.out.println("wait.....");
        }
        System.out.println(futureTask2.get());
        System.out.println(futureTask2.get());
        System.out.println(Thread.currentThread().getName()+"结束");

        //FutureTask 未来任务
    }
}

package com.stewart.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author Stewart
 * @create 2022/2/19
 * @Description 演示CountDownLatchDemo的demo
 */
public class CountDownLatchDemo {
    // 6个同学陆续离开教师，才能锁门
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                //每次计数减一
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+"离开了教师");
            },String.valueOf(i)).start();
        }
        //等待
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"班长锁门走人了");
    }
}

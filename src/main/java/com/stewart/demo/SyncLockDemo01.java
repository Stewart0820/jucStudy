package com.stewart.demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stewart
 * @create 2022/2/19
 * @Description lock（显式）的可重入锁
 */
public class SyncLockDemo01 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"外层");
                try{
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+"内层");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"T1").start();
    }
}

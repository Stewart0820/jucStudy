package com.stewart.demo;

/**
 * @author Stewart
 * @create 2022/2/16
 * @Description
 */
public class SyncLockDemo {
//    public synchronized void add(){
//        add();
//    }
    public static void main(String[] args) {
//        new SyncLockDemo().add();
        Object o = new Object();
        new Thread(()->{
            synchronized (o){
                System.out.println(Thread.currentThread().getName()+"外层");
                synchronized (o){
                    System.out.println(Thread.currentThread().getName()+"中层");
                    synchronized (o){
                        System.out.println(Thread.currentThread().getName()+"内层");
                    }
                }
            }

        },"T1").start();
    }
}

package com.stewart.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stewart
 * @create 2022/2/15
 * @Description
 */
class ShareResource{
    /**
     * 1 :AA 2:BB 3:cc
     */
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    //创建3个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    /**
     * 打印5次，参数几轮
     */
    public void print5(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            //判断
            while (flag !=1){
                //等待
                c1.await();
            }
            //干活
            for (int i = 0; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"轮"+loop);
            }
            //通知
            //修改标志位2
            flag = 2;
            c2.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag!=2){
                c2.await();
            }
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"轮"+loop);
            }
            flag=3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag!=3){
                c2.await();
            }
            for (int i = 0; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+"轮"+loop);
            }
            flag=1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }
}
public class ThreadDemo03 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}

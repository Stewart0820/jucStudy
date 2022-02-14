package com.stewart.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stewart
 * @create 2022/2/14
 * @Description lock处理线程间的通信
 */

class Share{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //+1
    public void incr() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number!=0){
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            //通知
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //-1
    public void decr(){
        lock.lock();
        try {
            //判断
            while(number!=1){
                lock.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadDemo02 {
}

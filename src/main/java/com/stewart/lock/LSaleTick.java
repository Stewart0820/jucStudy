package com.stewart.lock;

import sun.security.krb5.internal.Ticket;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Stewart
 * @create 2022/2/14
 * @Description 可重入锁
 */
class LTicket {
    /**
     * 票数量
     */
    private int number = 30;

    /**
     * 创建可重入锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        //上锁
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "剩下" + number);
            }
        } finally {
            //解锁
            lock.unlock();
        }

    }
}

public class LSaleTick {
    /**
     * 三个线程卖票
     * @param args
     */
    public static void main(String[] args) {
        final LTicket ticket = new LTicket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"CC").start();
    }
}

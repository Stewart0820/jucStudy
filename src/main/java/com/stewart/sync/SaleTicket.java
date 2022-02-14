package com.stewart.sync;

/**
 * @author Stewart
 * @create 2022/2/14
 * @Description 卖票操作
 */
class Ticket {
    /**
     * 票数
     */
    private int number = 100;

    /**
     * 操作方法
     */
    public synchronized void sale() {
        //判断，是否还有票
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "剩下" + number);
        }
    }
}

public class SaleTicket {
    /**
     * 三个线程卖票
     * @param args
     */
    public static void main(String[] args) {
        final Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 110; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 110; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 110; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();
    }
}

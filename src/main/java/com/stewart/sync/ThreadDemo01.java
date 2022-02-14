package com.stewart.sync;

/**
 * @author Stewart
 * @create 2022/2/14
 * @Description synchronized处理线程间的通信
 */
class Share{
    //初始值
    private int number = 0;
    // +1的方法
    public synchronized void incr() throws InterruptedException {
        //判断 干活 通知
        //判断number值是否为0，如果不是0，等待
        //要使用while来进行判断，不然可能会出现虚假唤醒
        while (number!=0){
            this.wait();
        }
        //如果number值是0，就+1
        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知其他线程
        this.notifyAll();
    }

    // -1的方法
    public synchronized void decr() throws InterruptedException {
        while(number!=1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        this.notifyAll();
    }
}
public class ThreadDemo01 {

    public static void main(String[] args) {
        Share share = new Share();
        /**
         * 加1
         */
        new Thread(()->{
            for (int i = 0; i <=10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        /**
         * 减1
         */
        new Thread(()->{
            for (int i = 0; i <=10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}

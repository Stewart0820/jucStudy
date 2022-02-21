package com.stewart.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Stewart
 * @create 2022/2/19
 * @Description
 */
class MyCache {
    //创建一个map集合
    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwlock = new ReentrantReadWriteLock();
    //放数据
    public void put(String key, Object value) {
        rwlock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "写操作" + key);

            TimeUnit.MICROSECONDS.sleep(300);
            //放数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完成" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放读写锁
            rwlock.writeLock().unlock();
        }

    }

    public Object get(String key) {
        rwlock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取操作" + key);

            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完了" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwlock.readLock().unlock();
        }

        return result;
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //创建线程往里面放数据
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }

        //创建线程取数据
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }
}

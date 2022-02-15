package com.stewart.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Stewart
 * @create 2022/2/15
 * @Description  演示list线程不安全问题
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
        /**
         * 通过vector解决
         */
//        List<String> list = new Vector<>();

        /**
         * 通过Collections的工具来解决
         */
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());

        /**
         * 通过juc里面的CopyOnWriteArrayList
         *
         * 写时复制操作
         */
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                //向集合里面添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //取出内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}

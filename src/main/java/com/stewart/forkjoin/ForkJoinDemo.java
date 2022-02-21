package com.stewart.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Stewart
 * @create 2022/2/21
 * @Description 从1加到100的拆分
 */
class MyTask extends RecursiveTask<Integer>{

    //拆分差值不能超过10，计算10以内的运算
    private static final Integer VALUE = 10;

    private int begin;
    private int end;
    private int result;

    //创建一个有参数的构造
    public MyTask(int begin,int end){
        this.begin = begin;
        this.end = end;
    }

    //拆分和合并的过程
    @Override
    protected Integer compute() {
        //判断相加的两个数是否大于10
        if((end-begin)<=VALUE){
            //相加操作
            for (int i = begin; i <= end; i++) {
                result = result+i;
            }
        }else{
            //进一步拆分
            int mid = (begin+end)/2;
            //拆分左边
            MyTask task01 = new MyTask(begin,mid);
            //拆分右边
            MyTask task02 = new MyTask(mid+1,end);
            //调用方法拆分
            task01.fork();
            task02.fork();

            //合并结果
            result = task01.join()+task02.join();
        }
        return result;
    }
}
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask task = new MyTask(0,100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(task);
        //获取合并后的结果
        Integer result = forkJoinTask.get();
        System.out.println(result);

        //关闭池对象
        forkJoinPool.shutdown();
    }
}

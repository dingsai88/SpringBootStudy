package com.ding.study.concurrent.jkjuc.juc26ForkJoin;

import java.util.concurrent.ForkJoinPool;

/**
 * @author daniel 2019-11-8 0008.
 */
public class ForkJoinTest {

    public static void main(String[] args) throws Exception {
        //创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        //0，1，1，2，3，5，8，13，21，34
        Fibonacci fib = new Fibonacci(9);
         //启动分治任务
        Integer result = fjp.invoke(fib);
        //输出结果
        System.out.println(result);

    }
}

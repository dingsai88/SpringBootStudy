package com.ding.study.concurrent.jkjuc.juc26ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel 2019-11-8 0008.
 */
public class ForkJoinTest {

    public static void main(String[] args) throws Exception {

        //RecursiveTask 带返回值的计算

        //创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        //0，1，1，2，3，5，8，13，21，34
        FibonacciRecursiveTask fib = new FibonacciRecursiveTask(9);
         //启动分治任务
        Integer result = fjp.invoke(fib);
        // 关闭线程池
        fjp.shutdown();
        //输出结果
        System.out.println("带返回值的RecursiveTask:返回:"+result);




        //RecursiveAction 不带返回值的计算

        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);

        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new RecursiveActionDemo(0, 1000));

        //阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);

        // 关闭线程池
        forkJoinPool.shutdown();

        System.out.println("带返回值的RecursiveAction:返回:");





    }
}

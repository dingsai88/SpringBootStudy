package com.ding.study.concurrent.jkjuc.juc26ForkJoin;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

/**
 * @author daniel 2019-11-8 0008.
 */
public class Fibonacci extends RecursiveTask<Integer> {

    private int n;

    Fibonacci(int n) {
        this.n = n;
    }


    @Override
    protected Integer compute() {
        try {
            System.out.println(Thread.currentThread().getName() + ":进入compute方法:" + n);

            if (n <= 1) {
                System.out.println(Thread.currentThread().getName() + ":小于等于1返回:" + n);
                //第一位是0，1。  第0返回0，第一返回1.
                return n;
            }
            Fibonacci f1 = new Fibonacci(n - 1); //创建子任务 1
            System.out.println(Thread.currentThread().getName() + "：开始fork：" + n);
            f1.fork();
            //Thread.sleep(new Random().nextInt(200));
            Thread.sleep(1000);
            Fibonacci f2 = new Fibonacci(n - 2);//1
            //等待子任务结果，并合并结果
            Integer result = f2.compute() + f1.join();
            System.out.println(Thread.currentThread().getName() + ":返回:" + result);
            return result;
        } catch (Exception e) {

        }
        return null;
    }
}

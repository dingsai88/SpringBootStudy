package com.ding.study.concurrent.jkjuc.juc19CountDownLatchCyclicBarrier;

import java.util.concurrent.*;

/**
 * @author daniel 2019-10-17 0017.
 */
public class CountDownLatchDemo {
    public static String getOrder1(String t) {
        try {
            Thread.sleep(new java.util.Random().nextInt(100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getOrder1:结束");

        return "getOrder1" + t;
    }

    public static String getOrder2(String t) {
        try {
            Thread.sleep(new java.util.Random().nextInt(100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getOrder2:结束");
        return "getOrder2" + t;
    }


    public static void main(String[] args) throws Exception {
        //超过就报错-2个线程1个等待
        Executor executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1));
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("getOrder1:开始");
                getOrder1("1");
                countDownLatch.countDown();
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("getOrder2:开始");

                getOrder2("2");
                countDownLatch.countDown();
            }
        };
        try {
            executor.execute(runnable1);
            executor.execute(runnable2);
            executor.execute(runnable1);
            executor.execute(runnable2);
            executor.execute(runnable1);
            executor.execute(runnable2);
        }catch (RejectedExecutionException e){
            e.printStackTrace();
            System.out.println("启动过多:");
        }

        System.out.println("开始等待");
        //注意是await 不是wait
        countDownLatch.await();
        System.out.println("全部结束");


    }


}

package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import java.util.Random;

/**
 * @author daniel 2019-11-6 0006.
 */
public class RunnableImpl implements Runnable{
    @Override
    public void run() {
        System.out.println("RunnableImpl:print1:"+Thread.currentThread().getName());
        try {
            Thread.sleep((new Random()).nextInt(100));
         } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("RunnableImpl:print2:"+Thread.currentThread().getName());

    }
}

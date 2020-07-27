package com.ding.study.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * @author daniel 2020-7-27 0027.
 */
public class WaitNotiyTestMain {
    public static WaitNotiyStack stack = new WaitNotiyStack();


    public static void main(String[] args) throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println("放入开始:" + i);
                        stack.push(i);
                        System.out.println("放入结束:" + i);
                        Thread.sleep(22);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println("弹出开始:" + i);
                        stack.pop();
                        System.out.println("弹出结束:" + i);
                        Thread.sleep(11);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };


        System.out.println("开始启动线程:" );
        new Thread(runnable).start();

        new Thread(runnable2).start();

        new CountDownLatch(2).await();

    }
}

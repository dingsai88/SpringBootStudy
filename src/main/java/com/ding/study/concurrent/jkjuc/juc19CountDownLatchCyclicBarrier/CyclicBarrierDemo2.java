package com.ding.study.concurrent.jkjuc.juc19CountDownLatchCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo2 {
    public static int i = 0;
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("两个到了：" +i);

        }
    });


    public static Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            i++;
            System.out.println("run:开始:" + i);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 10; i++) {
            new Thread(runnable1).start();
        }
        System.out.println("都结束");

    }
}

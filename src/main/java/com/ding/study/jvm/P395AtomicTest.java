package com.ding.study.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daniel 2019-5-31 0031.
 */
public class P395AtomicTest {
    //public static volatile int race = 0;
    public static AtomicInteger race=new AtomicInteger(0);

  //  public static void increase() {race++;}
  public static void increase() {
      race.incrementAndGet();
  }


    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        increase();
                    }

                }
            });
            threads[i].start();
        }
        //  System.out.println(race);
        /**
         * 会有两个线程     Thread[Monitor Ctrl-Break,5,main] 线程详细说明地址:
         * https://docs.oracle.com/cd/E13188_01/jrockit/docs50/userguide/apstkdmp.html
         * Thread[main,5,main]
         */
        //此段代码线上慎用
    /*    while (Thread.activeCount() > 2) {
            //打印现有线程 debug用
            //  Thread.currentThread().getThreadGroup().list();
            //让出CPU
            Thread.yield();
        }*/

        Thread.sleep(1555);
        System.out.println(race);
    }

}

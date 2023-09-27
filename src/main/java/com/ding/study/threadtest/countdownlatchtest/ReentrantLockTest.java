package com.ding.study.threadtest.countdownlatchtest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest {
    private static int state = 0;
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition a = reentrantLock.newCondition();
    private static Condition b = reentrantLock.newCondition();
    private static Condition c = reentrantLock.newCondition();

    public static void main(String[] args) {
        threada.setName("a");
        threadb.setName("b");
        threadc.setName("c");
        threada.start();
        threadb.start();
        threadc.start();

    }

  private static   Thread threada = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    reentrantLock.lock();

                    if (state % 3 != 0) {
                        a.await();
                    }
                    log.info(state + " 打印:线程" + Thread.currentThread().getName());
                    state++;

                    b.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });


    private static  Thread threadb = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    reentrantLock.lock();

                    if (state % 3 != 1) {
                        b.await();
                    }
                    log.info(state + " 打印:线程" + Thread.currentThread().getName());
                    state++;
                    c.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    private static   Thread threadc = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    reentrantLock.lock();

                    if (state % 3 != 2) {
                        c.await();
                    }
                    log.info(state + " 打印:线程" + Thread.currentThread().getName());
                    state++;
                    a.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

}

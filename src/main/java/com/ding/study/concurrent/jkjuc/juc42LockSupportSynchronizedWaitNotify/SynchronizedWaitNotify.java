package com.ding.study.concurrent.jkjuc.juc42LockSupportSynchronizedWaitNotify;

import java.util.concurrent.locks.LockSupport;

public class SynchronizedWaitNotify {


    static char[] a = {'a', 'b', 'c', 'd', 'e', 'f'};
    static char[] b = {1, 2, 3, 4, 5};
    static Thread thread1 = null;
    static Thread thread2 = null;

    public static void main(String[] args) {
        final Object obj = new Object();
        thread1 = new Thread(() -> {
            try {
                synchronized (obj) {
                    for (char c : a) {
                        System.out.println(c);
                        obj.notify();
                        obj.wait();

                    }
                }
                obj.notify();
            } catch (InterruptedException e) {

            }
        });

        thread2 = new Thread(() -> {
            try {
                synchronized (obj) {
                    for (char c : b) {

                        System.out.println(c);
                        obj.notify();
                        obj.wait();

                    }
                }
                obj.notify();
            } catch (InterruptedException e) {

            }

        });

        thread1.start();
        thread2.start();

        System.out.println("中文");
    }


}

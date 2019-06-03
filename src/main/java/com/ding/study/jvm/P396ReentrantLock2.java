package com.ding.study.jvm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author daniel 2019-5-31 0031.
 */
public class P396ReentrantLock2 {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            System.out.println(Thread.currentThread().getName() + ": put:count:" + count + ";putptr:" + putptr + ";takeptr:" + takeptr);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            System.out.println(Thread.currentThread().getName() + ": take:count:" + count + ";putptr:" + putptr + ";takeptr:" + takeptr);
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final P396ReentrantLock2 t = new P396ReentrantLock2();
        System.out.println(Thread.currentThread().getName() + ": mainBegin:count:" + t.count + ";putptr:" + t.putptr + ";takeptr:" + t.takeptr);

        Thread.sleep(1555);
        final int z = 20;
        Thread[] threads = new Thread[z];
        for (int i = 0; i < z; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < z; i++) {
                        try {
                            t.put(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            threads[i].start();

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < z; i++) {
                        try {
                            t.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            threads[i].start();
        }


        Thread.sleep(1555);
        System.out.println(Thread.currentThread().getName() + ": end:count:" + t.count + ";putptr:" + t.putptr + ";takeptr:" + t.takeptr);


    }


}

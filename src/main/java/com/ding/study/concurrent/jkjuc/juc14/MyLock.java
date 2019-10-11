package com.ding.study.concurrent.jkjuc.juc14;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author daniel 2019-10-10 0010.
 */
public class MyLock {
    private Lock lock = new ReentrantLock();



    public void lock() {
        lock.lock();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
        }
        lock.unlock();
    }
    public void tryLock() {
        if (lock.tryLock()) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
            lock.unlock();
        }
    }

    public void lockInterrupt() {
        try {
            lock.lockInterruptibly();
            Thread.sleep(1119);
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "中断" );
            try {
                Thread.sleep(1111);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + "-Exception" );
        }finally {
            lock.unlock();
        }


    }


    public static void main(String[] args) throws Exception {
        System.out.println(1 << 20);
        MyLock myLock = new MyLock();
        new Thread(myLock::lock,"lock.1").start();
        new Thread(myLock::lock,"lock.3").start();
        new Thread(myLock::lock,"lock.2").start();
        new Thread(myLock::lock,"lock.4").start();

        Thread.sleep(111);
        new Thread(myLock::tryLock,"tryLock.1").start();
        new Thread(myLock::tryLock,"tryLock.3").start();
        new Thread(myLock::tryLock,"tryLock.2").start();
        new Thread(myLock::tryLock,"tryLock.4").start();
        Thread.sleep(111);
        System.out.println("\n\n\n");
        Thread thread=  new Thread(myLock::lockInterrupt,"lockInte3rrupt.1");
        thread.start();
        Thread.sleep(111);
        thread.interrupt();

        new Thread(myLock::lockInterrupt,"lockIn4terrupt.3").start();
        new Thread(myLock::lockInterrupt,"lockI5nterrupt.2").start();
        new Thread(myLock::lockInterrupt,"lockI6nterrupt.4").start();
        new Thread(myLock::lockInterrupt,"lockI6nterrupt.1").start();
        new Thread(myLock::lockInterrupt,"lockIn3terrupt.3").start();
        new Thread(myLock::lockInterrupt,"lock2Interrupt.2").start();
        new Thread(myLock::lockInterrupt,"lockIntearrupt.4").start();

        new Thread(myLock::lockInterrupt,"lockIn2terrupt.1").start();
        new Thread(myLock::lockInterrupt,"lockI1nterrupt.3").start();
        new Thread(myLock::lockInterrupt,"lockIfnterrupt.2").start();
        new Thread(myLock::lockInterrupt,"lockaInterrupt.4").start();





    }


}

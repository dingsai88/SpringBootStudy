package com.ding.study.jvm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author daniel 2019-5-31 0031.
 */
public class P396ReentrantLock implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("Thread is waiting! : " + System.currentTimeMillis());
            condition.await();
            System.out.println("Thread is going on! : " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //测试
    public static void main(String[] args) throws InterruptedException {
        P396ReentrantLock reen = new P396ReentrantLock();
        Thread thread = new Thread(reen);
        thread.start();
        Thread.sleep(3000);
        //通知线程reen继续执行
      //  lock.lock();
        condition.signal();
       // lock.unlock();
    }

}

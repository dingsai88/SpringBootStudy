package com.ding.study.concurrent.jkjuc.juc18StampedLock;

import java.util.concurrent.locks.StampedLock;

/**
 * 对比readWriteLock
 * 1.所有读写都返回一个stamp邮戳-
 * 2.比readWriteLock多了一个乐观读;RW写操作会等待读完成才竞争，比较悲观;乐观读不会影响写锁进入
 * @author daniel 2019-10-15 0015.
 */
public class StampedLockTest {
    private double x, y;
    private final StampedLock stampedLock = new StampedLock();

    public String write(double tx, double ty) {
        System.out.println("写锁开始:" + Thread.currentThread().getName() + ":" + tx+":"+ty);
        long stamp = stampedLock.writeLock();
        try {
            try {
                Thread.sleep(new java.util.Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("写锁写入成功:" + Thread.currentThread().getName() + ":" + tx+":"+ty);
            x = tx;
            y = ty;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
        return stamp +  Thread.currentThread().getName() + ":" + tx+":"+ty;
    }


    public String read(String str) {
        long optimistic = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;

        String result = (currentX + currentY) + "";
        try {
            Thread.sleep(new java.util.Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!stampedLock.validate(optimistic)) {
            optimistic = stampedLock.readLock();
            System.out.println("乐观读升级为悲观读:" + Thread.currentThread().getName() + ":" + str);
            try {
                currentX = x;
                currentY = y;
                System.out.println("悲观读返回:" + Thread.currentThread().getName() + ":" + str);
                return result = (currentX + currentY) + ""+Thread.currentThread().getName()+ ":" + str;
            } finally {
                stampedLock.unlockRead(optimistic);
            }
        }
        System.out.println("乐观读返回:" + Thread.currentThread().getName() + ":" + str);
        return result+ Thread.currentThread().getName() + ":" + str;
    }


    public static void main(String[] args) throws Exception {
        StampedLockTest t = new StampedLockTest();

        for (int i = 0; i < 20; i++) {
            String temp = i + "";
            new Thread(() -> t.read(temp)).start();
        }

        for (int i = 0; i < 20; i++) {
            double temp = i ;
            new Thread(() -> System.out.println("返回--写--"+t.write(temp,temp+1))).start();
        }
    }


}

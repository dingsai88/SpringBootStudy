package com.ding.study.concurrent.jkjuc.juc17ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author daniel 2019-10-14 0014.
 */
public class ReadWriteLockTest {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Map<String, String> map = new HashMap<>();

    public String get(String key) {

        try {
            System.out.println("get.begin:"+key);
            Thread.sleep(new Random().nextInt(100));
            readWriteLock.readLock().lock();


            System.out.println("get.end:"+key);
            return map.get(key);
        } catch (InterruptedException e) {
            return "";
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public String set(String key) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println("set.begin:"+key);
            Thread.sleep(3);
            System.out.println("set.end:"+key);

            return map.put(key, key);
        } catch (InterruptedException e) {
            return "";

        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    public static void main(String[] args) {
        System.out.println("中文");
        ReadWriteLockTest t = new ReadWriteLockTest();
        for (int i = 0; i < 10; i++) {
            t.set(i + "");
        }

        for (int i = 0; i < 10; i++) {
            String temp = i + "";
            new Thread(() -> t.set(temp)).start();
        }
        for (int i = 0; i < 100; i++) {
            String temp = i + "";
            new Thread(() -> t.get(temp)).start();
        }


    }

}

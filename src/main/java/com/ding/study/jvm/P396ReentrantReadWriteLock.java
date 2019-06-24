package com.ding.study.jvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author daniel 2019-5-31 0031.
 */
public class P396ReentrantReadWriteLock {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = reentrantReadWriteLock.readLock();
    private Lock writeLock = reentrantReadWriteLock.writeLock();
    private Map<String, String> map = new HashMap<String, String>();

    public String set(String str) throws Exception{
        writeLock.lock();
        System.out.println("开始写都停下:"+str);
        Thread.sleep(1111);
        map.put(str, str);
        writeLock.unlock();
        return str;
    }

    public String get(String str)throws Exception {
         readLock.lock();
        String t = map.get(str);
        System.out.println("读:"+str+"不停");
        readLock.unlock();
        return t;
    }


    public static void main(String[] args) throws Exception {
        final  P396ReentrantReadWriteLock temp=new P396ReentrantReadWriteLock();
        for (int i = 0; i < 30; i++) {
         temp.map.put(i+"",i+"");
        }
        Thread.sleep(1555);
        final int z = 20;
        Thread[] threads = new Thread[z];
        Thread[] threads2 = new Thread[z];
        for (int i = 0; i < z; i++) {

            threads2[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < z; i++) {
                        try {
                            //  System.out.println(Thread.currentThread().getName() + ": get:count:" + temp.get(i+""));
                            temp.get(i+"");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            threads2[i].start();

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 2; i++) {
                        try {
                           // System.out.println(Thread.currentThread().getName() + ": set:count:" + temp.set(i+""));
                            temp.set(i+"");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            threads[i].start();

        }


        Thread.sleep(1555);
        System.out.println(Thread.currentThread().getName() + ": end:count:" +temp.map.size());


    }
}

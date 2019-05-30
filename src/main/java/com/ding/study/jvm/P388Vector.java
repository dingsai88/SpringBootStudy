package com.ding.study.jvm;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  很明显，尽管这里使用到的vector的getremove和size方法都是同步的，但是在
 多线程的环境中，如果不再方法调用点做额外的同步措施的话，使用这段代码仍然是不安全的
 ，因为如果另一个线程恰好在错误的时间删除了一个元素，导致序号I已经不再可用的话
 ，再用I访问数组就会抛出一个arrayindexoutofboundsexception。
 * @author daniel 2019-5-30 0030.
 */
public  class P388Vector {
    private   static Vector<Integer> v = new Vector<Integer>();

    public static void main(String[] args) throws Exception {

        while (true) {
            for (int i = 0; i < 10; i++) {
                v.add(i);
                System.out.println("add:" + i);
            }
            final Random random = new Random();
            Thread removeThread = new Thread(new Runnable() {
                @Override
                public  void run() {
                    synchronized(v) {
                        for (int i = 0; i < v.size(); i++) {
                            System.out.println(Thread.currentThread().getName() + "  remove:" + v.remove(i) + ";i:" + i + ";size:" + v.size());
                        }
                    }
                }
            });

            Thread getThread = new Thread(new Runnable() {
                @Override
                public  void run() {
                    synchronized(v) {
                        for (int i = 0; i < v.size(); i++) {

                            try {
                                Thread.sleep(random.nextInt(100));
                            } catch (Exception e) {

                            }
                            System.out.println(Thread.currentThread().getName() + "  get:" + v.get(i) + ";i:" + i + ";size:" + v.size());
                        }
                    }
                }
            });
            getThread.start();
            removeThread.start();


            // new CountDownLatch(1).await();
            while (Thread.activeCount() > 20) ;



        }
    }

}

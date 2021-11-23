package com.ding.study.collection;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author daniel 2019-6-25 0025.
 */
public class TestCollectionQueueMain {
    private static SynchronousQueue synchronousQueue = new SynchronousQueue();

    public static void main(String[] args) throws Exception {
    ArrayDeque queue=new ArrayDeque(2);
        System.out.println(queue.add("1"));
        System.out.println(queue.element());
        System.out.println(queue.peek());
        System.out.println("element1:"+queue.peek());
        System.out.println(queue.poll());
        System.out.println("element2:"+queue.peek());
        // System.out.println(queue.poll());
        //   System.out.println(queue.remove());


        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        linkedBlockingQueue.add(2);
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
        arrayBlockingQueue.add(2);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {

                        synchronousQueue.put(i);
                        System.out.println(i + "插入线程:");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i + "读取线程:" + synchronousQueue.take());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            }
        };

        new Thread(runnable2).start();
        new Thread(runnable).start();
        Thread.sleep(222222);
        System.out.println("结束");
    }
}

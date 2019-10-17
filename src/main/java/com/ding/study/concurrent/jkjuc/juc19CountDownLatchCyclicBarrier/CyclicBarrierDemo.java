package com.ding.study.concurrent.jkjuc.juc19CountDownLatchCyclicBarrier;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author daniel 2019-10-17 0017.
 */
public class CyclicBarrierDemo {
    public static String getOrder1(String t) {
        try {
            Thread.sleep(new java.util.Random().nextInt(100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getOrder1:结束");

        return "getOrder1" + t;
    }

    public static String getOrder2(String t) {
        try {
            Thread.sleep(new java.util.Random().nextInt(100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getOrder2:结束");
        return "getOrder2" + t;
    }


    public static void main(String[] args) throws Exception {
        //超过就报错-2个线程1个等待
      Executor executor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50));
        Vector<String> vector=new Vector<>();
   /*     CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                if(vector.size()>1){
                    System.out.println("回调1:"+vector.get(0));
                    System.out.println("回调2:"+vector.get(1));
                }
                vector.clear();
                System.out.println("回调删光");

            }
        });*/

        CyclicBarrier cyclicBarrier=new CyclicBarrier(2,()->executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(111));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(vector.size()>1){
                    System.out.println("回调1:"+vector.get(0));
                    System.out.println("回调2:"+vector.get(1));
                }
                vector.clear();
                System.out.println("回调删光");
            }
        }));


        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("getOrder1:开始");
                getOrder1("1");
                vector.add(Thread.currentThread().getName()+":1");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("getOrder2:开始");

                getOrder2("2");
                vector.add(Thread.currentThread().getName()+":2");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            try {
                new Thread(runnable1).start();
                new Thread(runnable2).start();
                System.out.println("启动完成:" + i);
            } catch (RejectedExecutionException e) {
                e.printStackTrace();
                System.out.println("启动过多:");
            }

        }


        System.out.println("全部结束");


    }

}
  class RunnableTest implements Runnable {
    String name = "";

    public RunnableTest(String t) {
        name = t;
    }

    public RunnableTest() {

    }

    @Override
    public void run() {
        System.out.println("RunnableTest.cyclicBarrier:回调" + name);
    }
}
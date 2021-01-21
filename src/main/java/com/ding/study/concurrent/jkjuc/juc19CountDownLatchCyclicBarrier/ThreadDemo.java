package com.ding.study.concurrent.jkjuc.juc19CountDownLatchCyclicBarrier;

/**
 * @author daniel 2019-10-17 0017.
 */
public class ThreadDemo extends  Thread{
    @Override
    public void run() {

    }
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
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("getOrder1:开始");
                getOrder1("1");
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("getOrder2:开始");

                getOrder2("2");
            }
        };

        Thread thread = new Thread(runnable1);
        thread.start();

        Thread thread2 = new Thread(runnable2);
        thread2.start();

        System.out.println("开始等待");
        thread.join();
        thread2.join();
        System.out.println("全部结束");


        System.out.println("中文");
    }
}

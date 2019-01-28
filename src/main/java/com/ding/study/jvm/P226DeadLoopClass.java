package com.ding.study.jvm;

/**
 * @author daniel 2019-1-24 0024.
 */
public class P226DeadLoopClass {
    static {
        if (true) {
            System.out.println(Thread.currentThread() + "init DeadLoopClass");

          //  while (true) { }
        }
    }


    public static void main(String[] args) {
        Runnable script =new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "Start");
                P226DeadLoopClass dd=new P226DeadLoopClass();
                System.out.println(Thread.currentThread() + "over");
            }
        };

        Thread thread=new Thread(script);
        Thread thread2=new Thread(script);
        thread.start();
        thread2.start();

        thread.start();

    }


}

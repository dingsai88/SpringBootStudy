package com.ding.study.jvm;

/**
 * P227
 * Thread[Thread-1,5,main]Start
 Thread[Thread-0,5,main]Start
 Thread[Thread-1,5,main]init DeadLoopClass

 * @author daniel 2018-5-23 0023.
 */
public class Study15DeadLoopClass {

    static class DeadLoopClass {
        static {
            if (true) {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                while (true) {
                }
            }
        }

    }


    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "Start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);

        thread1.start();
        thread2.start();

    }

}

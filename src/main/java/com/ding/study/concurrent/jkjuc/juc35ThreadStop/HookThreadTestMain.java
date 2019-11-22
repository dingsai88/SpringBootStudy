package com.ding.study.concurrent.jkjuc.juc35ThreadStop;

/**
 * @author daniel 2019-11-21 0021.
 */
public class HookThreadTestMain {

    public static void main(String[] args)throws Exception {

        System.out.println("1111111");
        HookThread hookThread=new HookThread();

        Runtime.getRuntime().addShutdownHook(new Thread(hookThread));
        new Thread(()->{
            System.out.println("线程内");
            HookThread hookThread2=new HookThread("线程内的hook");

            Runtime.getRuntime().addShutdownHook(new Thread(hookThread2));

        }).start();

        Thread.sleep(3000);


        System.out.println("99999999999999");
    }

}

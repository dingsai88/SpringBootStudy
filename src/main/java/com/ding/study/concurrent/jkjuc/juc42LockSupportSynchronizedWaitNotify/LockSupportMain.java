package com.ding.study.concurrent.jkjuc.juc42LockSupportSynchronizedWaitNotify;

import java.util.concurrent.locks.LockSupport;

/**
 * 循环打印a 1 b 2 c 3
 */
public class LockSupportMain {
   static char []a={'a','b','c','d','e','f'};
    static   char []b={1,2,3,4,5};
    static  Thread thread1=null;
    static  Thread thread2=null;
    public static void main(String[] args){

          thread1=new Thread(()->{
            for(char c:a){
                System.out.println(c);
                LockSupport.unpark(thread2);
                LockSupport.park();
            }

        });

          thread2=new Thread(()->{
                for(char c:b){
                    LockSupport.park();
                    System.out.println(c);
                    LockSupport.unpark(thread1);

            }

        });

        thread1.start();
        thread2.start();

        System.out.println("中文");
    }


}

package com.ding.study.concurrent.jkjuc.juc91;

/**
 * @author daniel 2021-12-2 0002.
 */
public class InterrupterRunnableBean2  implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("线程开始:"+Thread.currentThread().getName());
            //  blockMethod();
           while(!Thread.currentThread().isInterrupted()){
               System.out.println(Thread.currentThread().isInterrupted());
           }
            System.out.println(Thread.currentThread().isInterrupted());
        }catch (Exception e){
            System.out.println("Exception");
            e.printStackTrace();
        }
    }


}

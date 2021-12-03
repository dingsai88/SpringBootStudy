package com.ding.study.concurrent.jkjuc.juc91;

/**
 * @author daniel 2021-12-2 0002.
 */

public class InterrupterRunnableBean implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("线程开始:"+Thread.currentThread().getName());
          //  blockMethod();
            for(int i=0;i<5;i++){
              Thread.sleep(1000);

            }
            System.out.println("线程结束:"+Thread.currentThread().getName());
        }catch (InterruptedException e){
            System.out.println("InterruptedException");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("Exception");
            e.printStackTrace();
        }
    }






}

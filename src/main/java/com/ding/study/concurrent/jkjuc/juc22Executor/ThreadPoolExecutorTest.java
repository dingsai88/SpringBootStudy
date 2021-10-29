package com.ding.study.concurrent.jkjuc.juc22Executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @see com.ding.study.util.ThreadPollExecutorUtil
 *
 *
 */
public class ThreadPoolExecutorTest  implements Runnable{
    Integer i;
    public ThreadPoolExecutorTest(Integer data){
        i=data;
    }


    @Override
    public void run() {

        System.out.println(i+""+Thread.currentThread().getName()+":开始");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(i+":异常");
           // e.printStackTrace();
        }


    }



    public static void main(String[] args) throws Exception {


        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1,5,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(60));


        for(int i=0;i<50;i++){
            try {
            Thread.sleep(2);
            threadPoolExecutor.execute(new ThreadPoolExecutorTest(i));
            } catch (Exception e) {
                System.out.println(i+":异常");
                // e.printStackTrace();
            }
        }

        System.out.println("全部:结束");

    }

}

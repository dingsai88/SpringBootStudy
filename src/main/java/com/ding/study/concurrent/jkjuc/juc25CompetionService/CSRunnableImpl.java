package com.ding.study.concurrent.jkjuc.juc25CompetionService;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;

/**
 * @author daniel 2019-11-5 0005.
 */
public class CSRunnableImpl implements Runnable {
    private static int count=1;
    private TestBean test;
    public CSRunnableImpl(TestBean testBean){
        test=testBean;
    }
    @Override
    public void run() {
        try {
            System.out.println(test.getName()+"--"+Thread.currentThread().getName()+"-begin");
            Thread.sleep(1111);
            if(count%2==0){
              //  Thread.sleep(new Random().nextInt(5000));
            }

            test.setName(test.getName()+(count++));
            System.out.println(test.getName()+"--"+System.currentTimeMillis()+"-end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

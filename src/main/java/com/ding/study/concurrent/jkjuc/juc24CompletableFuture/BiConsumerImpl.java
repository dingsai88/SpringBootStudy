package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;
import java.util.function.BiConsumer;

/**
 * @author daniel 2019-11-6 0006.
 */
public class BiConsumerImpl implements BiConsumer {
    @Override
    public void accept(Object o, Object o2) {
        TestBean testBean1 = (TestBean)o;
        TestBean testBean2 = (TestBean)o2;
        TestBean testBean = new TestBean();

        testBean.setName("BiConsumerImpl" );
        try {
            Thread.sleep((new Random()).nextInt(1002));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testBean.setName(testBean1.getName()+"11"+testBean2.getName());
        System.out.println("BiConsumerImpl:print:" + Thread.currentThread().getName() + " testBean:" + testBean.getName());


    }
}

package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;
import java.util.function.BiFunction;

/**
 * @author daniel 2019-11-1 0001.
 */
public class BiFunctionImpl implements BiFunction {
    private String test;

    public BiFunctionImpl() {

    }

    public BiFunctionImpl(String t) {
        test = t;
    }


    @Override
    public Object apply(Object o, Object o2) {
        TestBean testBean1 = (TestBean)o;
        TestBean testBean2 = (TestBean)o2;
        TestBean testBean = new TestBean();

        testBean.setName("BiFunctionImpl" + test);
        try {
            Thread.sleep((new Random()).nextInt(1002));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testBean.setName(testBean1.getName()+"11"+testBean2.getName());
        System.out.println("BiFunctionImpl:print:" + Thread.currentThread().getName() + " testBean:" + testBean.getName());


        return testBean;
    }
}

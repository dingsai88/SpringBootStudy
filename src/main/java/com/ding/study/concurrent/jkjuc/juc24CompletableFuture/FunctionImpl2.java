package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

/**
 * @author daniel 2019-11-6 0006.
 */
public class FunctionImpl2 implements Function   {

    private String test;

    public FunctionImpl2(String str) {
        test = str;
    }

    @Override
    public Object apply(Object o) {
        TestBean testBean=(TestBean) o;
        System.out.println("FunctionImpl:print1:"+Thread.currentThread().getName()+" testBean:"+testBean.getName()+":"+test);
        try {
            Thread.sleep((new Random()).nextInt(100));
            testBean.setName(test+":"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FunctionImpl:print2:"+Thread.currentThread().getName()+" testBean:"+testBean.getName()+":"+test);

        return testBean;
    }
}
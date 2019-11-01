package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;
import java.util.function.Consumer;

/**
 * @author daniel 2019-11-1 0001.
 */
public class ConsumerImpl  implements Consumer {
    private String test;

    public ConsumerImpl() {

    }

    public ConsumerImpl(String t) {
        test = t;
    }
    @Override
    public void accept(Object o) {
        TestBean testBean=(TestBean) o;
        System.out.println("ConsumerImpl:print1:"+Thread.currentThread().getName()+" testBean:"+testBean.getName()+":"+test);
        try {
            Thread.sleep((new Random()).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ConsumerImpl:print2:"+Thread.currentThread().getName()+" testBean:"+testBean.getName()+":"+test);

    }
}

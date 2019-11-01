package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @author daniel 2019-11-1 0001.
 */
public class SupplierImpl implements Supplier {
    private String test;

    public SupplierImpl() {

    }

    public SupplierImpl(String t) {
        test = t;
    }

    @Override
    public Object get() {
        TestBean testBean = new TestBean();
        testBean.setName("SupplierImpl" + test);
        try {
            Thread.sleep((new Random()).nextInt(1002));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SupplierImpl:print:" + Thread.currentThread().getName() + " testBean:" + testBean.getName());

        if((new Random()).nextInt(1002)%2==0){

                throw new RuntimeException("222");

        }

        return testBean;
    }
}

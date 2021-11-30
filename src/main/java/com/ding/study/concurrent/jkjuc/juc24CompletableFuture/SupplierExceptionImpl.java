package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author daniel 2019-11-6 0006.
 */
public class SupplierExceptionImpl implements Supplier {
    private String test;

    public SupplierExceptionImpl() {

    }

    public SupplierExceptionImpl(String t) {
        test = t;
    }

    @Override
    public Object get() {
        TestBean testBean = new TestBean();
        testBean.setName("SupplierExceptionImpl" + test);
        System.out.println("1SupplierExceptionImpl:print:" + Thread.currentThread().getName() + " testBean:" + testBean.getName());
        try {
            Thread.sleep((new Random()).nextInt(1002));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2SupplierExceptionImpl:print:" + Thread.currentThread().getName() + " testBean:" + testBean.getName());

        if (1 == 1) {
           throw new RuntimeException();
            //testBean.setName((7/0)+"");
        }


        return testBean;
    }
}

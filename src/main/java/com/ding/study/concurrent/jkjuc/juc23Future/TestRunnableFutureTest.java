package com.ding.study.concurrent.jkjuc.juc23Future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author daniel 2021-11-23 0023.
 */
public class TestRunnableFutureTest {


    public static void main(String[] args) throws Exception {
        TestBean testBean = new TestBean();
        testBean.setName("1");

        System.out.println("RunnableFuture实现类FutureTask 使用Runnable方式调用 前----:" + testBean);
        //FutureTask 方式调用
        RunnableFuture futureTask = new FutureTask(new Runnable() {
            @Override
            public void run() {
                testBean.setName("Runnable");
            }
        }, testBean);

        new Thread(futureTask).start();
        System.out.println("RunnableFuture实现类FutureTask 使用Runnable方式调用 后----:" + futureTask.get());
        System.out.println(" \n\n RunnableFuture实现类FutureTask 使用Runnable方式调用 结束 \n\n" );





        System.out.println("RunnableFuture实现类FutureTask 使用 Callable 方式调用 前----:" + testBean);
        //FutureTask 方式调用
        RunnableFuture futureTaskCallable = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                testBean.setName("callable");
                return testBean;
            }
        });
        new Thread(futureTaskCallable).start();
        System.out.println("RunnableFuture实现类FutureTask 使用 Callable 方式调用 后----:" + futureTaskCallable.get());
        System.out.println(" \n\n RunnableFuture实现类FutureTask 使用 Runnable 方式调用 结束 \n\n" );








    }
}

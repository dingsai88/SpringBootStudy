package com.ding.study.concurrent.jkjuc.juc25CompletionService;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author daniel 2019-11-7 0007.
 */
public class CallableImpl implements Callable<TestBean> {
    @Override
    public TestBean call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"-begin");

            Thread.sleep(new Random().nextInt(3000));

        System.out.println(Thread.currentThread().getName()+"-end");
        TestBean testBean = new TestBean();
        testBean.setName("初始化:"+System.currentTimeMillis());
        return testBean;
    }
}

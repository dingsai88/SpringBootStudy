package com.ding.study.concurrent.jkjuc.juc23Future;

import java.util.concurrent.Callable;

/**
 * @author daniel 2019-10-25 0025.
 */
public class CallableDemo implements Callable {
    private TestBean bean;

    public TestBean getBean() {
        return bean;
    }

    public void setBean(TestBean bean) {
        this.bean = bean;
    }

    @Override
    public Object call() throws Exception {
        bean.setName("aaa");
        if (1 != 2) {
           // throw new Exception("111");
        }
        return bean;
    }
}

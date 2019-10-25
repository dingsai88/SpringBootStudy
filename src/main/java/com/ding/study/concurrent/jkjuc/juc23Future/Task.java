package com.ding.study.concurrent.jkjuc.juc23Future;

/**
 * @author daniel 2019-10-25 0025.
 */
public class Task  implements Runnable {
    private TestBean bean;

    public TestBean getBean() {
        return bean;
    }

    public void setBean(TestBean bean) {
        this.bean = bean;
    }

    @Override
    public void run() {
        bean.setName("3");
    }
}

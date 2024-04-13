package com.ding.study.concurrent.jkjuc.juc23Future;

import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel 2019-10-25 0025.
 */
@Slf4j
public class TestBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sleep(int  i) {
        try {
            log.info(" 开始sleep  {} 睡眠 {} s",name,i);
            Thread.sleep(i*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TestBean{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

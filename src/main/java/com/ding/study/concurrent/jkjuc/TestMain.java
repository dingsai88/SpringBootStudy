package com.ding.study.concurrent.jkjuc;

/**
 * @author daniel 2019-9-24 0024.
 */
public class TestMain {


    public static void main(String[] args) throws Exception {
        System.out.println("开始");
        ThreadDemo demo = new ThreadDemo();
        demo.start();
        System.out.println("结束");
    }
}

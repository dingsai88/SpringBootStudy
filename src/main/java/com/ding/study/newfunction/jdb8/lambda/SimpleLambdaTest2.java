package com.ding.study.newfunction.jdb8.lambda;

public class SimpleLambdaTest2 {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> System.out.println("2.bb"));
        thread.start();

    }
}

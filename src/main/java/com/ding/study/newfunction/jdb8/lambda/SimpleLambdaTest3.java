package com.ding.study.newfunction.jdb8.lambda;

public class SimpleLambdaTest3 {
    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1.aa");
            }
        }).start();


    }
}

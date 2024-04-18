package com.ding.study.concurrent.jkjuc.juc23Future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestMain {


    public static void main(String[] args) throws Exception {

        for(int i=0;i<50;i++) {
            //异步注解
            TestInterface testInterface = new TestInterfaceImpl();
            Future<String> str1 = testInterface.query1("调用A");
            Future<String> str2 = testInterface.query2("调用B");

            String a = str1.get(50, TimeUnit.SECONDS);
            String b = str2.get(50, TimeUnit.SECONDS);

            log.info(i+"  次  a：{}  ---   b：{}", a, b);
        }
    }
}

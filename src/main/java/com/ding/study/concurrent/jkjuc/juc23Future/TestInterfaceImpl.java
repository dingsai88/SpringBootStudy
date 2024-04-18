package com.ding.study.concurrent.jkjuc.juc23Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

public class TestInterfaceImpl implements TestInterface{
    @Async
    @Override
    public Future<String> query1(String str) {
        try {
            Thread.sleep(3000);
        }catch (Exception e){

        }
        return new AsyncResult<String>("方法1返回"+str);
    }

    @Async
    @Override
    public Future<String> query2(String str) {
        try {
            Thread.sleep(1000);
        }catch (Exception e){

        }
        return new AsyncResult<String>("方法2返回"+str);
    }
}

package com.ding.study.concurrent.jkjuc.juc23Future;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

public interface TestInterface {

    Future<String> query1(String str);

    Future<String> query2(String str);
}

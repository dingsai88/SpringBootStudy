package com.ding.study.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author daniel 2019-6-26 0026.
 */
public class TestConcurrentMain {

    public static void main(String[] args) throws Exception {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1, 1);
        //HashMap concurrentHashMap=new HashMap(1,1);
        System.out.println(40 >>> 1);
        System.out.println(InterfaceTest.str2);
        for (int i = 0; i < 50; i++) {
            concurrentHashMap.put("a" + i, "a" + i);
        }
        System.out.println(concurrentHashMap);

        Map map=new HashMap<>();

        Iterable iterable=new ArrayList<>();
        Collection collection=new ArrayList<>();
        Queue queue=new ArrayDeque<>();
        List list=new ArrayList();
        Set set=new HashSet();


    }
}

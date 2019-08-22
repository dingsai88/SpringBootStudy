package com.ding.study.collection;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * 基于红黑树
 * key排序的输出key输出1、2、3、4
 * @author daniel 2019-8-20 0020.
 */
public class C1813TreeMap {
    public static void main(String[] args) {
        TreeMap<String ,String> hm=new TreeMap<>();
        hm.put("3","c");
        hm.put("1","a");
        hm.put("2","b");
        hm.put("4","d");
        hm.put("2","e");
        Set<String> set=hm.keySet();
        for (String k:set){
            System.out.println(k+"--"+hm.get(k));
        }
    }
}

package com.ding.study.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author daniel 2019-8-20 0020.
 */
public class C1801Map {
    public static void main(String[] args) {
        HashMap<String ,String> map=new HashMap<>();
        /**
         * null
         value1
         {key=value2}
         */
        //新插入的key put方法返回 null
        System.out.println(map.put("文章","马伊琍"));
        //已存在的key返回第一次的 value  马伊琍
        System.out.println(map.put("文章","姚笛"));
        //{文章=姚笛}
        System.out.println(map);

       //遍历keySet
        Set<String> set=map.keySet();
        for (String k:set){
            System.out.println(k+"--"+map.get(k));
        }

        //遍历2 entrySet
        Set<Map.Entry<String, String>> set2=map.entrySet();
        for (Map.Entry<String, String> k:set2){
            System.out.println(k.getKey()+"--"+k.getValue());
        }

        for(int i=0;i<30;i++){
            map.put(i+"key",i+"value");
        }


    }
}

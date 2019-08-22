package com.ding.study.collection;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**插入顺序有序；输出key是1、2、3、4  不是1\3\4\2
 * @author daniel 2019-8-20 0020.
 */
public class C1812LinkedHashMap {
    public static void main(String[] args) {
        LinkedHashMap<String ,String> hm=new LinkedHashMap<>(16, 0.75f, true);
        hm.put("1","a");
        hm.put("2","b");
        hm.put("3","c");
        hm.put("4","d");
        hm.put("2","e");
        System.out.println("11--"+hm.get("2"));
        Set<String> set=hm.keySet();
        for (String k:set){
            System.out.println(k+"--"+hm.get(k));
        }
        ConcurrentHashMap<String,String> concurrent=new ConcurrentHashMap<>();
        concurrent.put("","");

    }
}

package com.ding.study.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**注释 特殊注释
 * @author daniel 2020-5-18 0018.
 */
public class ZhuShiMainTest {
    public static void main(String[] args) {
        String name = "沉默王二";
        // \u000dname="沉默王三";
        System.out.println(name);
        String a1=new String("age1");
        String a2=new String("age1");
        System.out.println("==:"+a1==a2);
        HashSet<String> set=new HashSet<>();
        set.add(a1);
        set.add(a2);
        System.out.println("==a1:"+a1.hashCode());
        System.out.println("==a2:"+a2.hashCode());
        int j=0;
        for (int i = 0; i < 10; j+=i,i++){};

        System.out.println("==a2:"+j);
    }
}

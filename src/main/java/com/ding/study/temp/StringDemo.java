package com.ding.study.temp;

import java.lang.reflect.Field;

/**
 * @author daniel 2019-6-20 0020.
 */
public class StringDemo {
    public static void main(String[] args) throws Exception {
        String str01 = "aaa";
        String str02 = "aaa";
        String str03 = "bbbb";
        System.out.println(str01.hashCode() + "/" + str02.hashCode() + "/" + str03.hashCode());
        Field field = str02.getClass().getDeclaredField("value");
        field.setAccessible(true);
        field.set(str02, new char[]{'b', 'b', 'b', 'b'});
        System.out.println(str01 + "/" + str02);
 /* jdk7以后已不包含 private final int count;
   field = str02.getClass().getDeclaredField("count");
        field.setAccessible(true);
        field.set(str02, 4);*/

        System.out.println(str01 + "/" + str02);
        System.out.println(str01.hashCode() + "/" + str02.hashCode() + "/" + str03.hashCode());
        field = str02.getClass().getDeclaredField("hash");
        field.setAccessible(true);
        field.set(str02, 0);
        System.out.println(str01.hashCode() + "/" + str02.hashCode() + "/" + str03.hashCode());
        System.out.println(str01 == str02);
        System.out.println(str02 == str03);
    }

}

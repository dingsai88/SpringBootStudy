package com.ding.study.util;

import com.ding.study.newfunction.jdb8.myoptional.OptionalTest;

import java.util.Optional;

/**
 * @author daniel 2020-8-31 0031.
 */
public class NullOptionalTest {
    public static void main(String args[]){

         Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);



        // 如果为null NullPointerException;正常get调用
        Optional<Integer> b = Optional.of(value2);



        System.out.println(NullOptionalTest.sum(a,b));
    }

    /**
     * 求和
     * @param a
     * @param b
     * @return
     */
    public static Integer sum(Optional<Integer> a, Optional<Integer> b){

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}

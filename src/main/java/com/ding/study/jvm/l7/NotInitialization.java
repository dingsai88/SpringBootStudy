package com.ding.study.jvm.l7;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel 2018-12-17 0017.
 */
public class NotInitialization {

    public static void main(String[] args) throws Throwable {

        /**
         * SuperClass init
         * 123
         * 通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
         * 至于是否触发子类的加载和验证，在虚拟机规范中并未明确规定，这点取决于虚拟机实现
         *
         */
       // System.out.println(SubClass.value);

        /**
         * 什么都不输出，通过数组定义来引用，不会触发此类的初始化
         */
       // SuperClass[] sca=new SuperClass[10];



        System.out.println(ConstClass.HELLOWORLD);
    }

}

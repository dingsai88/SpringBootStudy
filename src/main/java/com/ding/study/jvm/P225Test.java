package com.ding.study.jvm;

/**
 * 静态语句块中只能访问到定义在静态语句块之前的变量；
 * 在前面的静态语句块可以赋值但是不能访问
 *
 * @author daniel 2019-1-24 0024.
 */
public class P225Test {

    static {
        //可以赋值
        i = 0;
        //不能访问
        // System.out.println(i);


    }

    static int i = 1;

    public static void main (String []args ){
        System.out.println(i);
    }
}

package com.ding.study.jvm;

/**
 * @author daniel 2019-2-18 0018.
 */
public class P259Study259 {


    public static void main(String[] args) {
        //java.lang.NegativeArraySizeException编译能通过运行报错
        int [][][] array=new int[1][0][-1];
        System.out.println("正常");

    }



}

package com.ding.study.jvm;

/**
 * Created by 老丁 on 2018-1-5 0005.
 */
public class RuntimeConstantPoolOOM {

    public static void main(String [] args){
        String str1=new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);

        String str2=new StringBuilder("ja").append("va").toString();

        System.out.println(str2.intern()==str2);

    }
}

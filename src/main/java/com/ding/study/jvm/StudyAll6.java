package com.ding.study.jvm;

/**
 * @author daniel 2018-11-8 0008.
 */
public class StudyAll6 {


    /**
     * 类型转换指令 窄化
     *
     * 输出：
     * test窄化1:2147483647
     test窄化3:99
     test窄化3:0
     test窄化4:NaN
     test窄化5:Infinity
     test窄化6:-Infinity

     * @param args
     */
    public static void main (String [] args){
        System.out.println("test窄化1:"+(int)9999999999999999999999999.9);
        System.out.println("test窄化3:"+(int)99.9);
        System.out.println("test窄化3:"+(int)Double.NaN);
        System.out.println("test窄化4:"+(float)Double.NaN);
        System.out.println("test窄化5:"+(float)999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9);
        System.out.println("test窄化6:"+(float)-999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999.9);
    }
}

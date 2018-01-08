package com.ding.study.jvm;

/**
 * @author daniel 2018-1-8 0008.
 */
public class Study5RuntimeConstantPoolOOM {

    /**
     *
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        String str1=new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);

        String str2=new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);


    }

}

package com.ding.study.jvm;

import java.io.Serializable;

/**
 * char>int>long>float>double>character>Serializable=Comparable>Object>char ...
 *
 * @author daniel 2019-2-12 0012.
 */
public class P249Overload {
/*

    public static void sayHello(char obj) {
        System.out.println("hello char");
    }
*/

    public static void sayHello(int obj) {
        //输出97
        System.out.println("hello int:"+obj);
    }

    public static void sayHello(long obj) {
        System.out.println("hello long");
    }

    public static void sayHello(Character obj) {
        System.out.println("hello Character");
    }
    public static void sayHello(Serializable obj) {
        System.out.println("hello Serializable");
    }
  /*  public static void sayHello(Comparable obj){
        System.out.println("hello Comparable");
    }*/


    public static void sayHello(Object obj) {
        System.out.println("hello object");
    }


    public static void sayHello(char... obj) {
        System.out.println("hello char ...");
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        sayHello('a');
    }

}

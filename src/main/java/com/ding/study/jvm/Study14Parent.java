package com.ding.study.jvm;

/**
 * p226
 * @author daniel 2018-5-23 0023.
 */
public class Study14Parent {

    static class Parent {
        public static int A = 1;
        static {
            A = 2;
        }
    }


    static class Sub extends Parent {
        public static int B = A;
    }


    public static void main(String [] args){
        System.out.println(Sub.B);
        System.out.println(Parent.A);
    }


}

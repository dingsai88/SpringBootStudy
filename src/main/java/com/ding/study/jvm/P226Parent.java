package com.ding.study.jvm;

/**
 * @author daniel 2019-1-24 0024.
 */
public class P226Parent {
    public static void main(String[] args) {

            System.out.println(Sub.B);



    }
}

 class Parent {
    public static int A = 1;

    static {
        A = 2;
    }
}


 class Sub extends Parent {
    public static int B = A;

}

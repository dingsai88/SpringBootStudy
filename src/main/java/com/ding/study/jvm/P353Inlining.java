package com.ding.study.jvm;

/**1025.14
 * @author daniel 2019-5-16 0016.
 */
public class P353Inlining {

    public static void foo(Object obj){
        if(obj!=null){
            System.out.println("输出");
        }
    }

    public static void testInline(){
        Object obj=null;
        foo(obj);
    }

    public static void main(String []args){
        testInline();
        System.out.println("3");

    }

}

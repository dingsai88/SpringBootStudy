package com.ding.study.jvm;

/**
 *
 * 不出输出ConstClass init
 * 常量在编译阶段会存入调用类的常量池，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类初始化
 * @author daniel 2018-5-3 0003.
 */
public class Study12NotInitialization {
    public static void main(String [] args) throws Throwable{
        System.out.println(ConstClass.HELLOWORLD);
    }
}
   class ConstClass{

    static {
        System.out.println("ConstClass init ");

}

public static  final   String HELLOWORLD="hello world";

        }


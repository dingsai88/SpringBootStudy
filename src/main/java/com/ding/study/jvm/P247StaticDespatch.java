package com.ding.study.jvm;

/**
 * @author daniel 2019-2-1 0001.
 */
public class P247StaticDespatch {

    static abstract class Human{

    }

    static class Man extends Human{

    }

    static class Woman extends Human{

    }

    public void sayHello(Human guy){
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy){
        System.out.println("hello,Man!");
    }
    public void sayHello(Woman guy){
        System.out.println("hello,Woman!");
    }

    /**
     * 输出结果:
     * hello,guy!
     hello,guy!
     * @param args
     */
    public static void main(String [] args){
        Human man=new Man();
        Human woman=new Woman();
        P247StaticDespatch sr=new P247StaticDespatch();
        sr.sayHello(man);
        sr.sayHello(woman);

    }

}

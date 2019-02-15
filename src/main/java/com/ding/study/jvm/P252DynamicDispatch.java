package com.ding.study.jvm;

/**
 *
 * E:\DingSai\DingProjectAs_new\SpringBootStudy\target\classes\com\ding\study\jvm>javap -c P252DynamicDispatch.class
 Compiled from "P252DynamicDispatch.java"
 public class com.ding.study.jvm.P252DynamicDispatch {
 public com.ding.study.jvm.P252DynamicDispatch();
 Code:
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: return

 public static void main(java.lang.String[]);
 Code:
 0: new           #2                  // class com/ding/study/jvm/P252DynamicDispatch$Man
 3: dup
 4: invokespecial #3                  // Method com/ding/study/jvm/P252DynamicDispatch$Man."<init>":()V
 7: astore_1
 8: new           #4                  // class com/ding/study/jvm/P252DynamicDispatch$Woman
 11: dup
 12: invokespecial #5                  // Method com/ding/study/jvm/P252DynamicDispatch$Woman."<init>":()V
 15: astore_2
 16: aload_1
 17: invokevirtual #6                  // Method com/ding/study/jvm/P252DynamicDispatch$Human.sayHello:()V
 20: aload_2
 21: invokevirtual #6                  // Method com/ding/study/jvm/P252DynamicDispatch$Human.sayHello:()V
 24: new           #4                  // class com/ding/study/jvm/P252DynamicDispatch$Woman
 27: dup
 28: invokespecial #5                  // Method com/ding/study/jvm/P252DynamicDispatch$Woman."<init>":()V
 31: astore_1
 32: aload_1
 33: invokevirtual #6                  // Method com/ding/study/jvm/P252DynamicDispatch$Human.sayHello:()V
 36: return
 }





 * @author daniel 2019-2-15 0015.
 */
public class P252DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }


    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("Woman say hello");
        }
    }


    public static void main(String[] args) {
        Human man=new Man();
        Human woman=new Woman();
        man.sayHello();
        woman.sayHello();
        man=new Woman();
        man.sayHello();
    }


}

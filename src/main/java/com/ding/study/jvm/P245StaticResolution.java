package com.ding.study.jvm;

/**
 * E:\DingSai\DingProjectAs_new\SpringBootStudy\target\classes\com\ding\study\jvm>javap -v P245StaticResolution.class
 *
 *
 Classfile /E:/DingSai/DingProjectAs_new/SpringBootStudy/target/classes/com/ding/study/jvm/P245StaticResolution.class
 Last modified 2019-1-31; size 668 bytes
 MD5 checksum fed5eefcd83ce618b5e7791a5c602129
 Compiled from "P245StaticResolution.java"
 public class com.ding.study.jvm.P245StaticResolution
 SourceFile: "P245StaticResolution.java"
 minor version: 0
 major version: 51
 flags: ACC_PUBLIC, ACC_SUPER
 Constant pool:
 #1 = Methodref          #7.#22         //  java/lang/Object."<init>":()V
 #2 = Fieldref           #23.#24        //  java/lang/System.out:Ljava/io/PrintStream;
 #3 = String             #25            //  hello world
 #4 = Methodref          #26.#27        //  java/io/PrintStream.println:(Ljava/lang/String;)V
 #5 = Methodref          #6.#28         //  com/ding/study/jvm/P245StaticResolution.sayHello:()V
 #6 = Class              #29            //  com/ding/study/jvm/P245StaticResolution
 #7 = Class              #30            //  java/lang/Object
 #8 = Utf8               <init>
 #9 = Utf8               ()V
 #10 = Utf8               Code
 #11 = Utf8               LineNumberTable
 #12 = Utf8               LocalVariableTable
 #13 = Utf8               this
 #14 = Utf8               Lcom/ding/study/jvm/P245StaticResolution;
 #15 = Utf8               sayHello
 #16 = Utf8               main
 #17 = Utf8               ([Ljava/lang/String;)V
 #18 = Utf8               args
 #19 = Utf8               [Ljava/lang/String;
 #20 = Utf8               SourceFile
 #21 = Utf8               P245StaticResolution.java
 #22 = NameAndType        #8:#9          //  "<init>":()V
 #23 = Class              #31            //  java/lang/System
 #24 = NameAndType        #32:#33        //  out:Ljava/io/PrintStream;
 #25 = Utf8               hello world
 #26 = Class              #34            //  java/io/PrintStream
 #27 = NameAndType        #35:#36        //  println:(Ljava/lang/String;)V
 #28 = NameAndType        #15:#9         //  sayHello:()V
 #29 = Utf8               com/ding/study/jvm/P245StaticResolution
 #30 = Utf8               java/lang/Object
 #31 = Utf8               java/lang/System
 #32 = Utf8               out
 #33 = Utf8               Ljava/io/PrintStream;
 #34 = Utf8               java/io/PrintStream
 #35 = Utf8               println
 #36 = Utf8               (Ljava/lang/String;)V
 {
 public com.ding.study.jvm.P245StaticResolution();
 flags: ACC_PUBLIC
 Code:
 stack=1, locals=1, args_size=1
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: return
 LineNumberTable:
 line 6: 0
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0       5     0  this   Lcom/ding/study/jvm/P245StaticResolution;

 public static void sayHello();
 flags: ACC_PUBLIC, ACC_STATIC
 Code:
 stack=2, locals=0, args_size=0
 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 3: ldc           #3                  // String hello world
 5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 8: return
 LineNumberTable:
 line 10: 0
 line 11: 8

 public static void main(java.lang.String[]);
 flags: ACC_PUBLIC, ACC_STATIC
 Code:
 stack=0, locals=1, args_size=1
 0: invokestatic  #5                  // Method sayHello:()V
 3: return
 LineNumberTable:
 line 14: 0
 line 15: 3
 LocalVariableTable:
 Start  Length  Slot  Name   Signature
 0       4     0  args   [Ljava/lang/String;
 }












 * @author daniel 2019-1-31 0031.
 */
public class P245StaticResolution {

    public static void sayHello(){

        System.out.println("hello world");
    }

    /**
     * 使用invokestatic 指令调用静态方法sayHello
     * 1.invokestatic :调用静态方法
     * 2.invokespecial（特殊）：调用实例构造器<init>方法、私有方法和父类方法
     * 3.invokevirtual:调用所有的虚方法。
     * 4.invokeinterface:调用接口方法，会在运行时再确定一个实现此接口的对象。
     * 5.invokedynamic:是由用户所设定的引导方法决定。
     *
     * @param args
     */
    public static void main(String [] args){
        P245StaticResolution.sayHello();
    }

}

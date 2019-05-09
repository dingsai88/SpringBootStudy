package com.ding.study.jvm;

/**
 * 解语法糖：预编译
 *
 public final class P318Enum extends Enum
 {

 public static final P318Enum DING;
 public static final P318Enum SAI;
 private static final P318Enum $VALUES[];

 public static P318Enum[] values()
 {
 return (P318Enum[])$VALUES.clone();
 }

 public static P318Enum valueOf(String name)
 {
 return (P318Enum)Enum.valueOf(com/ding/study/jvm/P318Enum, name);
 }

 private P318Enum(String s, int i)
 {
 super(s, i);
 }

 static
 {
 DING = new P318Enum("DING", 0);
 SAI = new P318Enum("SAI", 1);
 $VALUES = (new P318Enum[] {
 DING, SAI
 });
 }
 }


 *
 * @author daniel 2019-5-8 0008.
 */
public enum P318Enum {
    DING,
    SAI
}

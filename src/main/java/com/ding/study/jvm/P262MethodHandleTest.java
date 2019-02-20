package com.ding.study.jvm;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @author daniel 2019-2-18 0018.
 */
public class P262MethodHandleTest {

    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        //  Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        //  getPrintlnMH(obj).invokeExact("dingsai88");
        //  System.out.println("正常");

        MethodHandle methodHandle =  MethodHandles.lookup().findStatic(P262MethodHandleTest.class, "dingSum", MethodType.methodType(String.class, int.class, int.class));
        System.out.println(methodHandle.invoke(22,43));

    }

    private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
    }


    public static String dingSum(int i,int j) {
        return i + 10+j+"";
    }


}

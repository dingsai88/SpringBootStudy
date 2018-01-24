package com.ding.study.jvm;

/**
 * @author daniel 2018-1-22 0022.
 * 依然会被回收。
 */
public class Study7T3ReferenceCountingGC {

    public Object instance = null;
    private static final int _1BM = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1BM];

    public static void main(String [] args) {
        Study7T3ReferenceCountingGC objA = new Study7T3ReferenceCountingGC();
        Study7T3ReferenceCountingGC objB = new Study7T3ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        System.gc();


    }

}

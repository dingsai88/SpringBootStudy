package com.ding.study.jvm;

import java.lang.ref.SoftReference;

/**
 idea增加GC vm Options 增加参数: -XX:+PrintGCDetails
 * @author daniel 2018-1-22 0022.
 *         依然会被回收。page63
 *
[GC [PSYoungGen: 7411K->1189K(37888K)] 7411K->1197K(124544K), 0.0014046 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[Full GC (System) [PSYoungGen: 1189K->0K(37888K)] [ParOldGen: 8K->1023K(86656K)] 1197K->1023K(124544K) [PSPermGen: 2990K->2988K(21248K)], 0.0046859 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]

5.617（时间戳）: [GC（Young GC） 5.617（时间戳）: [ParNew（使用ParNew作为年轻代的垃圾回收期）: 43296K（年轻代垃圾回收前的大小）->7006K（年轻代垃圾回收以后的大小）(47808K)（年轻代的总大小）, 0.0136826 secs（回收时间）] 44992K（堆区垃圾回收前的大小）->8702K（堆区垃圾回收后的大小）(252608K)（堆区总大小）, 0.0137904 secs（回收时间）] [Times: user=0.03（Young GC用户耗时） sys=0.00（Young GC系统耗时）, real=0.02 secs（Young GC实际耗时）]

 */
public class Study8T3ReferenceCountingGC {

    public Object instance = null;
    private static final int _1BM = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1BM];

    /**
     * @param args
     */
    public static void main(String[] args)throws Exception {
        Study8T3ReferenceCountingGC objA = new Study8T3ReferenceCountingGC();
        Study8T3ReferenceCountingGC objB = new Study8T3ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        System.gc();


        Thread.sleep(111);
    }

}

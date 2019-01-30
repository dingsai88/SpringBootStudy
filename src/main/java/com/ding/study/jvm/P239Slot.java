package com.ding.study.jvm;

/**
 * 虚拟机参数增加-verbose:gc  输出垃圾收集过程
 *
 * @author daniel 2019-1-30 0030.
 */
public class P239Slot {

    /**
     * 参数增加-verbose:gc
     * 输出结果:
     * [GC 68851K->66697K(124544K), 0.0013312 secs]
     * [Full GC 66697K->66567K(124544K), 0.0122378 secs]
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();
    }


}

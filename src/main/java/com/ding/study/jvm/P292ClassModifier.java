package com.ding.study.jvm;

/**
 * @author daniel 2019-3-28 0028.
 */
public class P292ClassModifier {
    /**
     * class文件中常量池的起始偏移
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * constant_tuf8 info 常量的tag标志
     */
    private static final int CONSTANT_UTF8_INFO = 1;

    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    private static final int u1=1;
    private static final int u2=2;
    private  byte[] classByte;

    public P292ClassModifier(byte[] classByte){
        this.classByte=classByte;
    }



}

package com.ding.study.jvm;

/**VM参数:
 *
 * @author daniel 2019-6-13 0013.
 */
public class P93Allocation {
    private static final int _1MB=1024*1024;

    /**
     [GC [PSYoungGen: 6802K->1001K(9216K)] 6802K->5203K(19456K), 0.0138030 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
     Heap
     PSYoungGen      total 9216K, used 7750K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     eden space 8192K, 82% used [0x00000000ff600000,0x00000000ffc975d0,0x00000000ffe00000)
     from space 1024K, 97% used [0x00000000ffe00000,0x00000000ffefa580,0x00000000fff00000)
     to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     ParOldGen       total 10240K, used 4201K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     object space 10240K, 41% used [0x00000000fec00000,0x00000000ff01a788,0x00000000ff600000)
     PSPermGen       total 21248K, used 3000K [0x00000000f9a00000, 0x00000000faec0000, 0x00000000fec00000)
     object space 21248K, 14% used [0x00000000f9a00000,0x00000000f9cee1a8,0x00000000faec0000)

     -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * @param args
     */
    public static void main1(String[] args){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1=new byte[2*_1MB];
        allocation2=new byte[2*_1MB];
        allocation3=new byte[2*_1MB];
        allocation4=new byte[4*_1MB];
    }


    /**
     *
     * Heap
     PSYoungGen      total 9216K, used 6802K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     eden space 8192K, 83% used [0x00000000ff600000,0x00000000ffca4bd0,0x00000000ffe00000)
     from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
     ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
     PSPermGen       total 21248K, used 2909K [0x00000000f9a00000, 0x00000000faec0000, 0x00000000fec00000)
     object space 21248K, 13% used [0x00000000f9a00000,0x00000000f9cd74c8,0x00000000faec0000)
     大于3MB直接分配到老年代
     *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * @param args
     */
    public static void main2(String[] args){
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation4=new byte[4*_1MB];
    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * @param args
     */
    public static void main(String[] args){
        byte[] allocation1,allocation2,allocation3;
        allocation1=new byte[_1MB/4];
        allocation2=new byte[4*_1MB];
        allocation3=new byte[4*_1MB];
        allocation3=null;
        allocation3=new byte[4*_1MB];

    }
/**
 * Heap
 PSYoungGen      total 9216K, used 835K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
 eden space 8192K, 10% used [0x00000000ff600000,0x00000000ff6d0fa0,0x00000000ffe00000)
 from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
 ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
 object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
 PSPermGen       total 21248K, used 2261K [0x00000000f9a00000, 0x00000000faec0000, 0x00000000fec00000)
 object space 21248K, 10% used [0x00000000f9a00000,0x00000000f9c355d0,0x00000000faec0000)

 */
}



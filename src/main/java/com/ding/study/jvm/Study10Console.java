package com.ding.study.jvm;

/**
 * 104页附近
 * @author daniel 2018-4-3 0003.
 */
public class Study10Console {

    /**
     * I.jsp 查看java进程
     * jsp -l  查看启动的主类全名，或者启动类路径。
     * jps -v  显示启动时候的JVM参数
     * jps -q  只显示JVM启动时候的进程ID和系统的ID一样
     * jps -m  显示main函数启动的参数
     *
     *
     * II.jstat 查看运行状态信息的命令
     * jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]
     * jstat -gc 25277  1000  30
     * 查看25277gc信息  一秒显示一次 显示30次
     *
     * III.jinfo 查看jvm设置信息
     * jinfo -flags 10898  查看全部jvm配置信息
     * jinfo -sysprops 10898 查看System.getProperties();
     *
     *
     *
     * IV.jmap 内存映像工具
     *  jmap -heap 222 查看堆信息 新生代老年代等
     *
     *   jmap -histo 10898 |less  显示各个类的占用大小
     *   jmap -dump:live,format=b,file=heap.bin 10898   dump内存文件为heap.bin文件
     *
     * V.jhat  虚拟机堆转存快照分析工具
     *  jmap -dump出来的  heap.bin文件 使用
     *  jhat heap.bin 命令 访问localhost:7000查看dump分析
     *
     *VI.jvisualvm  内存分析工具和jhat类似 直接打开看 开源的有visulvm
     *
     *VII.jstack  栈
     *    jstack -l 10898 | less   显示栈信息 分析线程问题
     *
     * VIII.jconsole
     *
     *IX.javap -c DocFooter
     * java编译器生成的字节码
     *p153
     *
     *
     *
     *---------------------------------------------------------------------------------------------------------------------
     * 垃圾回收
     * 1.引用计数算法
     *
     *
     *
     *
     *
     *
     *
     *
     */
}

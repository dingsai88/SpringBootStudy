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
     *
     */
}

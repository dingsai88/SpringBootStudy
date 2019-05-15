package com.ding.study.jvm;

/**
 * VM options
 *
 * -XX:+UnlockDiagnosticVMOptions :诊断模式，开启后才能用下面的命令
 *
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly:输出反汇编信息
 *
 * -XX:+PrintCompilation:要求虚拟机在即时编译时打印出方法名
 *
 *-XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining:打印方法内联信息
 * @author daniel 2019-5-15 0015.
 */
public class P340 {
    public static final int NUM = 15000;

    public static int doubleValue(int i) {
        for (int j = 0; j < 100000; j++) ;
        return i * 2;
    }

    public static long calcSum() {
        long sum = 0;
        for (int j = 1; j <= 100; j++) {
            sum += doubleValue(j);
        }
        return sum;
    }

    /**
     * -XX:+PrintCompilation:要求虚拟机在即时编译时打印出方法名
     282    3 %           com.ding.study.jvm.P340::doubleValue @ 2 (18 bytes)  带有%是回边计数器触发的OSR编译
     282   22             com.ding.study.jvm.P340::doubleValue (18 bytes)  已编译
     283   23             com.ding.study.jvm.P340::calcSum (26 bytes)      已编译
     284    4 %           com.ding.study.jvm.P340::main @ 2 (28 bytes)      已编译

     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
        System.out.println("3");
    }
}

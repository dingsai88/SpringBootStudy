package com.ding.study.jvm;

/**
 * 两个特性
 * 1:对所有线程课件
 * 2:禁止指令重排序优化
 *
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly  输出反汇编 更严谨
 */
public class P367VolatileTest {
    public static volatile int race = 0;

    /**
     *   public static void increase();
     flags: ACC_PUBLIC, ACC_STATIC
     Code:
     stack=2, locals=0, args_size=0
     0: getstatic     #2                  // Field race:I 静态方法调用
     3: iconst_1                           //常量进栈
     4: iadd                               //自加
     5: putstatic     #2                  // Field race:I
     8: return
     LineNumberTable:
     line 13: 0
     line 14: 8

     */
    /**
     * 汇编结果
     * [Constants]
     # {method} 'increase' '()V' in 'com/ding/study/jvm/P367VolatileTest'
     #           [sp+0x20]  (sp of caller)
     0x0000000002a39e00: sub    $0x18,%rsp
     0x0000000002a39e07: mov    %rbp,0x10(%rsp)    ;*synchronization entry
     ; - com.ding.study.jvm.P367VolatileTest::increase@-1 (line 27)
     0x0000000002a39e0c: movabs $0x7d5da75c0,%r10  ;   {oop(a 'java/lang/Class' = 'com/ding/study/jvm/P367VolatileTest')}
     0x0000000002a39e16: mov    0x70(%r10),%r8d
     0x0000000002a39e1a: inc    %r8d
     0x0000000002a39e1d: mov    %r8d,0x70(%r10)
     0x0000000002a39e21: lock addl $0x0,(%rsp)     ;*getstatic race
     ; - com.ding.study.jvm.P367VolatileTest::increase@0 (line 27)
     0x0000000002a39e26: add    $0x10,%rsp
     0x0000000002a39e2a: pop    %rbp
     0x0000000002a39e2b: test   %eax,-0x9c9e31(%rip)        # 0x0000000002070000
     ;   {poll_return}
     0x0000000002a39e31: retq
     0x0000000002a39e32: hlt
     0x0000000002a39e33: hlt
     0x0000000002a39e34: hlt
     0x0000000002a39e35: hlt
     0x0000000002a39e36: hlt
     0x0000000002a39e37: hlt
     0x0000000002a39e38: hlt
     0x0000000002a39e39: hlt
     0x0000000002a39e3a: hlt
     0x0000000002a39e3b: hlt
     0x0000000002a39e3c: hlt
     0x0000000002a39e3d: hlt
     0x0000000002a39e3e: hlt
     0x0000000002a39e3f: hlt
     */
    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }

                }
            });
            threads[i].start();
        }
      //  System.out.println(race);
        /**
         * 会有两个线程     Thread[Monitor Ctrl-Break,5,main] 线程详细说明地址:
         * https://docs.oracle.com/cd/E13188_01/jrockit/docs50/userguide/apstkdmp.html
         * Thread[main,5,main]
         */
        //此段代码线上慎用
        while (Thread.activeCount() > 2) {
            //打印现有线程 debug用
          //  Thread.currentThread().getThreadGroup().list();
            //让出CPU
            Thread.yield();
        }


      //  System.out.println(race);
    }

}

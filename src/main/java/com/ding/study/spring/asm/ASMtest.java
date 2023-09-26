package com.ding.study.spring.asm;


/**
 * https://asm.ow2.io/
 *
 * @author daniel 2021-4-2 0002.
 */
public class ASMtest {

    public static void main(String[] args) {
        int a = 8;
        a = a++;
        System.out.println("1:"+a);
        int b = a++;
        System.out.println("2:"+a);
        System.out.println("3:"+b);
    }
}

package com.ding.study.jvm;

/**
 * @author daniel 2018-2-2 0002.
 */
public class TestMain {

    public static void main(String args[]){

        //
        /**转化为2进制数字 都是1返回1，否则就是0（0，0）也是0
         *110010=50
         *101101=45
         *100000=32
         *
         */
        System.out.println("1:"+(50&45));
        System.out.println("2:"+(34&34));
        System.out.println("22:"+(false&false));
        System.out.println("3:"+(34|36));
        System.out.println("34:"+(34|34));
        System.out.println("4:"+(false|true));
        System.out.println("344:"+(false|false));

    }
}


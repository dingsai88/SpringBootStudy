package com.ding.study.jvm;

/**
 * 编译后只剩下
 * 	public static void main(String args[])
 {
 System.out.println("1111111");
 }
 * @author daniel 2019-5-8 0008.
 */
public class P318IfWhile {

    public static void main(String []args){

        if(true){
            System.out.println("1111111");
        }else {
            System.out.println("222");

        }
   /*   拒绝编译  while (false){
            System.out.println("3333333");
        }*/

    }



}

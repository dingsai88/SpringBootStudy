package com.ding.study.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel 2019-5-7 0007.
 */
public class P314GenericType {
/*    public static String test(List<String> i){
        System.out.println("String");
        return "";
    }*/
    public static int test(List<Integer> i) {
        System.out.println("integer");
        return 1;
    }



    public static void main(String []args){
        System.out.println(test(new ArrayList<Integer>()));
     //  System.out.println(test(new ArrayList<String>()));
    }

}

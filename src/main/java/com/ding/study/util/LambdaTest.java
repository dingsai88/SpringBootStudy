package com.ding.study.util;

import java.util.function.Function;

/**
 * @see com.ding.study.newfunction.jdb8.lambda.SimpleLambdaTest
 * @author daniel 2020-4-14 0014.
 */
public class LambdaTest {
    interface  Printer{
       String print(String a1,String a2);
    }

    public String testPrint(String str1,String str2,Printer p){
        return p.print(str1,str2);
    }
    public static void main(String[] args) throws Exception {
        LambdaTest test=new LambdaTest();
        String a1="11";
        String a2="22";

        String a3 = test.testPrint(a1, a2, new Printer() {
            @Override
            public String print(String a1, String a2) {
                System.out.println("内部输出:"+a1+a2);
                return a1+a2;
            }
        });
        System.out.println("外部输出:"+a3);


       // a3=  test.testPrint(a1,a2,()-> System.out.println("内部输出:"+a1+a2));

    }
}

package com.ding.study.jvm;

import java.util.Arrays;
import java.util.List;

/**
 * P237
 *
 * 当前栈帧current Stack Frame
 *局部变量表 local variable table
 *
 * @author daniel 2018-6-7 0007.
 */
public class Study17Stack {


    public static void main (String [] args){
        List<String> aa= Arrays.asList("1","2","3");
        List<String> bb= Arrays.asList("4","5","3");
        String test="3";
        if (aa.contains(test)) {
            System.out.println("11111");
        } else if (bb.contains(test)) {
            System.out.println("2222");
        }

    }

}

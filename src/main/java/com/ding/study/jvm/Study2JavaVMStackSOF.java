package com.ding.study.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args:-Xss128k
 * @author daniel 2018-1-5 0005.
 * 说明:hotspot
 */
public class Study2JavaVMStackSOF {

    private int stackLength = 1;

    public void statckLeak() {
        stackLength++;
    }

    /**
     * java.lang.OutOfMemoryError:java heap space
     * 堆放对象溢出
     *
     * @param args
     */
    public static void main(String[] args) {
        Study2JavaVMStackSOF oom=new Study2JavaVMStackSOF();

        try {
            oom.statckLeak();
        }catch (Throwable e){
            System.out.println("stack length:"+oom.stackLength);
            e.printStackTrace();
        }


    }
}

package com.ding.study.concurrent.jkjuc.juc30ThreadLocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author daniel 2019-11-13 0013.
 */
public class ThreadLocalTest {
    public static void main(String[] args){
        ThreadLocal threadLocal=new ThreadLocal();
        threadLocal.set(null);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        simpleDateFormat.format(null);

    }


}

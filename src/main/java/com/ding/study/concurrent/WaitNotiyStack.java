package com.ding.study.concurrent;

import java.util.LinkedList;

/**
 * @author daniel 2020-7-27 0027.
 */
public class WaitNotiyStack {
    LinkedList list = new LinkedList();
    public synchronized void push(Object x) {
        System.out.println("工具类push进入:" + x);
        synchronized (list) {
            System.out.println("工具类push进入synchronized:" + x);
            list.addLast(x);
            notify();
        }
    }


    public synchronized Object pop()
            throws Exception {
        System.out.println("工具类pop进入:");
        synchronized (list) {
            System.out.println("工具类pop进入synchronized:");
            if (list.size() <= 0) {
                this.wait();
            }
            return list.removeLast();
        }
    }
}

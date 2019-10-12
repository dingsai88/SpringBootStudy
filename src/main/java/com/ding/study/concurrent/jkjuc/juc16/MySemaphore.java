package com.ding.study.concurrent.jkjuc.juc16;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author daniel 2019-10-12 0012.
 */
public class MySemaphore<T> {
    /**
     * 信号量 PV
     */
    public Semaphore semaphore = new Semaphore(10);

    /**
     * 最多只能放入10个，再多就等待
     */
    public List<T> list = new ArrayList<>();

    /**
     * 放入数据并且减一  最多放入10个
     *
     * @param t
     * @return
     */
    public boolean up(T t) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t + "--up--" + semaphore.availablePermits());
        list.add(t);
        return true;
    }

    public T down() {

        semaphore.release();
        if (list.size() == 0) {
            System.out.println("acquire--" + list.size());
            return null;
        }

        T t = list.get(list.size() - 1);
        list.remove(t);
        System.out.println(t + "--down--" + semaphore.availablePermits());

        return t;
    }


    public static void main(String[] args) throws Exception {
        MySemaphore semaphore = new MySemaphore();
        //只能放入10个
        for (int i = 0; i < 20; i++) {
            //  System.out.println(i + "--" + semaphore.semaphore.availablePermits());
            String t = i + "";
            new Thread(() -> semaphore.up(t)).start();
        }

        //拿出后再多拿出
        for (int i = 0; i < 10; i++) {
            //   System.out.println(i + "--" + semaphore.semaphore.availablePermits());
            new Thread(() -> semaphore.down()).start();
        }

        Thread.sleep(111);
        System.out.println("-555-" + semaphore.semaphore.availablePermits() + ":" + semaphore.list);


        System.out.println("TT开始:" + tt);
        System.out.println("TT RUN:" + testFinally(5));
        System.out.println("TT end:" + tt);

    }

    private static Integer tt = 10;

    public static Integer testFinally(Integer i) {
        try {
            tt -= i;
            return tt;
        } finally {
            tt += i;
            System.out.println(tt);
        }


    }
}

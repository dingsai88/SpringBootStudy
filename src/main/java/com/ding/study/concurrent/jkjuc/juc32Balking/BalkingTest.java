package com.ding.study.concurrent.jkjuc.juc32Balking;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel 2019-11-18 0018.
 */
public class BalkingTest {
    boolean changed = false;
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();


    void startAutoSave() {
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("autoSave--线程内:" + Thread.currentThread().getName());
                autoSave();
            }
        }, 5, 5, TimeUnit.SECONDS);
    }


    void autoSave() {
        if (!changed) {
            return;
        }
        changed = false;
        System.out.println("autoSave方法真实存储--线程内:" + Thread.currentThread().getName());
    }

    void edit() {
        changed = true;
    }


    void autoSave2() {
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed = false;
        }
        System.out.println("autoSave方法真实存储--线程内:" + Thread.currentThread().getName());
    }

    void edit2() {
        synchronized (this) {
            changed = true;
        }
    }


}

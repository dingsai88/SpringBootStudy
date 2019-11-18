package com.ding.study.concurrent.jkjuc.juc32Balking;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author daniel 2019-11-18 0018.
 */
public class RouterTable {

    ConcurrentHashMap<String, CopyOnWriteArraySet<Object>> rt = new ConcurrentHashMap<>();

    volatile boolean changed;

    ScheduledExecutorService ses= Executors.newSingleThreadScheduledExecutor();

    public void startLocalSaver(){
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

            }
        },1,1, TimeUnit.MINUTES);
    }

    void autoSave(){
        if(!changed){
            return;
        }
        changed=false;
        //TODO 写入到路由表
    }

    public void remove(Object router){
        Set<Object> set=rt.get(router);
        if(set!=null){
            set.remove(router);
            changed=true;
        }
    }





}

package com.ding.study.concurrent.jkjuc.juc35ThreadStop;

/**
 * @author daniel 2019-11-21 0021.
 */
public class HookThread  implements Runnable {
    public String name="";
    public HookThread(){
        this.name="默认";

    }
    public HookThread(String name){
        this.name=name;

    }
    @Override
    public void run() {
        System.out.println("HookThread:"+name);

    }
}

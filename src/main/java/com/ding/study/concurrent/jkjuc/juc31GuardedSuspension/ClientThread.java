package com.ding.study.concurrent.jkjuc.juc31GuardedSuspension;

import java.util.Random;

/**
 * @author daniel 2019-11-22 0022.
 */
public class ClientThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue,String name ,long seed){
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("N0."+i);
            System.out.println(Thread.currentThread().getName()+" requests "+request);
            requestQueue.putRequest(request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

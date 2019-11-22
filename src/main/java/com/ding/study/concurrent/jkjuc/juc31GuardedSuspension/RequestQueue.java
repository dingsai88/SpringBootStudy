package com.ding.study.concurrent.jkjuc.juc31GuardedSuspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author daniel 2019-11-22 0022.
 */
public class RequestQueue {
    private Queue<Request> queue = new LinkedList<>();
    public synchronized void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }
    public synchronized Request getRequest(){
        while (queue.peek() == null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return queue.remove();
    }
}

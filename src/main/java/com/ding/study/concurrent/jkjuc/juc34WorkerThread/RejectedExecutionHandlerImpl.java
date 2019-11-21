package com.ding.study.concurrent.jkjuc.juc34WorkerThread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author daniel 2019-11-20 0020.
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("RejectedExecutionHandlerImpl:"+r.toString()+"--executor:"+executor.toString());
    }
}

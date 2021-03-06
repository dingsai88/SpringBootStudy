package com.ding.study.concurrent.jkjuc.juc38RateLimiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Guava限流器
 * @author daniel 2019-11-27 0027.
 */
public class RateLimiterTest {
    private static   long  prev;
    public static void main(String []args)throws Exception{

        // 限流器流速：2 个请求 / 秒
        RateLimiter limiter =
                RateLimiter.create(1.0);
        // 执行任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
// 记录上一次执行时间
        prev = System.nanoTime();
        Thread.sleep(2000);
// 测试执行 20 次
        for (int i=0; i<20; i++){
            System.out.println("开始限流");
            // 限流器限流
            limiter.acquire();

            // 提交任务异步执行
            es.execute(()->{
                long cur=System.nanoTime();
                // 打印时间间隔：毫秒
                System.out.println(
                        (cur-prev)/1000_000);
                prev = cur;
            });
        }
        es.shutdown();
    }




}

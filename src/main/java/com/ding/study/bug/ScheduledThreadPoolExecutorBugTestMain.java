package com.ding.study.bug;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * https://mp.weixin.qq.com/s/DKN1pJEa9kg7F4fenEjyPw
 * https://bugs.openjdk.org/browse/JDK-8065320
 * 敖丙cpu100
 *
 *
 * java.util.concurrent.ThreadPoolExecutor#getTask()
 *
 *   Runnable r = timed ? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :workQueue.take();
 *
 *
 * 核心线程等于0时       boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;
 * 等于true
 *
 *
 *
 *
 * 下边走 :poll 移除并返回队列头部的元素 ,队列空返回null
 * workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS)
 *
 * 等于fasle时 take 移除并返回队列头部的元素 ,队列空阻塞
 * 下边走
 * workQueue.take();
 *
 *
 *
 *
 * @author daniel
 * @date 2023/1/31 14:56
 **/
@Slf4j
public class ScheduledThreadPoolExecutorBugTestMain {

    public static void main(String[] args) throws Exception{
      log.info("开始");
        ScheduledExecutorService scheduledThreadPoolExecutor= Executors.newScheduledThreadPool(0);

        scheduledThreadPoolExecutor.schedule(()->{
            log.info("线程内执行");
        },60, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.shutdown();
        log.info("结束");
        new CountDownLatch(2).await();

    }
}

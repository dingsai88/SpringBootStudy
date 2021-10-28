package com.ding.study.concurrent.jkjuc.juc22Executor;

import com.ding.study.concurrent.jkjuc.juc22Executor.workQueue.DelayQueueBean;

import java.util.concurrent.*;

/**
 * com.ding.study.util.ThreadPollExecutorUtil
 *
 * 工具类
 * @param <E>
 */
public class ThreadPoolExecutorWorkQueueTest<E> implements Runnable, Delayed {
    private long excuteTime;
    private long time;
    private String data;

    public ThreadPoolExecutorWorkQueueTest(String data, long delayTime) {
      //  System.out.println("创建对象："+delayTime + "倒数几秒开始："+data);
        this.excuteTime = TimeUnit.SECONDS.convert(delayTime, TimeUnit.SECONDS)+System.currentTimeMillis();
        this.time=delayTime;
        this.data = data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
   System.out.println("getDelay："+this.time+"  "+excuteTime);
   //     return unit.convert(this.excuteTime- System.nanoTime() , TimeUnit.SECONDS);
        return unit.convert(excuteTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
       // return 99999;
     }

    @Override
    public int compareTo(Delayed o) {
        ThreadPoolExecutorWorkQueueTest msg = (ThreadPoolExecutorWorkQueueTest)o;
        return this.excuteTime>msg.excuteTime?1:( this.excuteTime<msg.excuteTime?-1:0);
    }

    @Override
    public void run() {

        System.out.println("run方法倒数几秒开始:"+time + "：" + Thread.currentThread().getName() + ":开始："+data);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(excuteTime + ":异常");
            // e.printStackTrace();
        }

    }


    public static void main(String[] args) throws Exception {
        DelayQueue delayQueue = new DelayQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                20, 0L, TimeUnit.DAYS, delayQueue,
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        DelayQueueBean test2=new DelayQueueBean("aaa哈哈a",50);
        threadPoolExecutor.execute(test2);

        test2=new DelayQueueBean("aaa哈哈a",12);
        threadPoolExecutor.execute(test2);

        test2=new DelayQueueBean("aaa哈哈a",11);
        threadPoolExecutor.execute(test2);

        test2=new DelayQueueBean("aaa哈哈a",13);
        threadPoolExecutor.execute(test2);

        test2=new DelayQueueBean("aaa哈哈a",15);
        threadPoolExecutor.execute(test2);

        test2=new DelayQueueBean("aaa哈哈a",3);
        threadPoolExecutor.execute(test2);

        for (int i = 0; i < 50; i++) {
            try {
                DelayQueueBean test=new DelayQueueBean(i+"哈哈",i*3);
                threadPoolExecutor.execute(test);
            } catch (Exception e) {
                System.out.println(i + ":异常");
                // e.printStackTrace();
            }
        }

        System.out.println("全部:结束");


    }


}

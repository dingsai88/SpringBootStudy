package com.ding.study.concurrent.jkjuc.juc22Executor.workQueue;

import com.ding.study.concurrent.jkjuc.juc22Executor.ThreadPoolExecutorWorkQueueTest;

import java.util.concurrent.*;

/**
 * 必须实现 java.util.concurrent.Delayed接口，
 * 需要重写:
 * getDelay方法:延迟多久执行
 * compareTo排序:优先级
 *
 *DelayQueue阻塞的是其内部元素，DelayQueue中的元素必须实现 java.util.concurrent.Delayed接口，该接口只有一个方法就是long getDelay(TimeUnit unit)，返回值就是队列元素被释放前的保持时间，如果返回0或者一个负值，就意味着该元素已经到期需要被释放，此时DelayedQueue会通过其take()方法释放此对象，DelayQueue可应用于定时关闭连接、缓存对象，超时处理等各种场景；
 *
 * @param <E>
 */
public class DelayQueueBean <E> implements Runnable, Delayed {
    /**
     *开始执行时间: 延迟时间+当前时间
     */
    private long executeTime;
    /**
     * 记录时间用：没有业务功能
     */
    private long delayTime;
    private E data;
    public DelayQueueBean(E data, long delayTime) {
    System.out.println("创建对象："+ data+ "延迟几秒开始："+delayTime);
        this.executeTime = TimeUnit.SECONDS.convert(delayTime, TimeUnit.SECONDS)+System.currentTimeMillis();
        this.delayTime=delayTime;
        this.data = data;
    }

    /**
     * 延迟时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        System.out.println("getDelay：延迟时间:"+this.delayTime+"  开始执行:"+delayTime);
        return unit.convert(executeTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);

    }

    @Override
    public int compareTo(Delayed o) {
        DelayQueueBean msg = (DelayQueueBean)o;
        return this.executeTime>msg.executeTime?1:( this.executeTime<msg.executeTime?-1:0);
    }

    @Override
    public void run() {

        System.out.println("run方法倒数几秒开始:"+delayTime + "：" + Thread.currentThread().getName() + ":开始："+data);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(delayTime + ":异常");
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

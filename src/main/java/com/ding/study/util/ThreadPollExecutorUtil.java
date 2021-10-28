package com.ding.study.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 玉新TDC工具类
 * @author daniel 2021-8-27 0027.
 */
public class ThreadPollExecutorUtil {
    /**
     * I.Executor与线程池 ThreadPoolExecutor
     * corePoolSize ：核心线程数量
     * maximumPoolSize ：线程最大线程数     *
     * keepAliveTime ：线程没有任务时最多保持多久时间终止
     * unit ：keepAliveTime的时间单位
     * workQueue ：阻塞队列，存储等待执行的任务 很重要 会对线程池运行产生重大影响
     * threadFactory ：线程工厂，用来创建线程   Executors.defaultThreadFactory()
     * rejectHandler ：当拒绝处理任务时的策略




     * II.  拒绝策略
     * AbortPolicy：让调用者抛出 RejectedExecutionException 异常，这是默认策略
     * CallerRunsPolicy：让调用者运行任务
     * DiscardPolicy：放弃本次任务
     * DiscardOldestPolicy：放弃队列中最早的任务，本任务取而代之




     * II. 阻塞队列
     * ArrayBlockingQueue(数组阻塞队列):
     * LinkedBlockingQueue(链表阻塞队列,不设置默认最大):
     * PriorityBlockingQueue(排序的队列)：是一个没有边界的队列，它的排序规则和 java.util.PriorityQueue一样。需要注意，PriorityBlockingQueue中允许插入null对象。所有插入PriorityBlockingQueue的对象必须实现 java.lang.Comparable接口，队列优先级的排序规则就是按照我们对这个接口的实现来定义的。
     * DelayQueue(延迟多久+排序):阻塞的是其内部元素,元素必须实现 java.util.concurrent.Delayed接口，该接口只有一个方法就是long getDelay(TimeUnit unit)，返回值就是队列元素被释放前的保持时间，如果返回0或者一个负值，就意味着该元素已经到期需要被释放，此时DelayedQueue会通过其take()方法释放此对象，DelayQueue可应用于定时关闭连接、缓存对象，超时处理等各种场景；
     * SynchronousQueue(没有容量直接new新线程Executors.newCachedThreadPool)：队列内部仅允许容纳一个元素。当一个线程插入一个元素后会被阻塞，除非这个元素被另一个线程消费。
     */
    private static final ExecutorService pool =
            new ThreadPoolExecutor(5, 5, 2, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(2),
                    new NewNameThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

/*    private static final ExecutorService pool =
            new ThreadPoolExecutor(1, 1, 2, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(2),
                    Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());*/
    /**
     * 修改线程池名字用:可以不使用  Executors.defaultThreadFactory()
     */
    private static class NewNameThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NewNameThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "DingThread-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    public static void execute(Runnable runnable) {
        pool.execute(runnable);
    }


    public static void main(String[] arg) throws Exception {
        System.out.println("开始:");
            for (int i = 0; i < 20; i++) {
                try {
                    ThreadPollExecutorUtil.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println("InterruptedException:"+e);
                        }
                        System.out.println("正常结束:");
                    }
                });
            } catch(Exception e){
                System.out.println("error:"+i);
            }

        }
        System.out.println("error3333:");
    }


}

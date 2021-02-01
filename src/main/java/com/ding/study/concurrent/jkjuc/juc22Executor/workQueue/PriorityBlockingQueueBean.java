package com.ding.study.concurrent.jkjuc.juc22Executor.workQueue;

import java.util.concurrent.*;

/**
 *必须实现 接口:Comparable  .compareTo方法
 *
 *PriorityBlockingQueue(排序的队列)：是一个没有边界的队列，它的排序规则和 java.util.PriorityQueue一样。需要注意，PriorityBlockingQueue中允许插入null对象。所有插入PriorityBlockingQueue的对象必须实现 java.lang.Comparable接口，队列优先级的排序规则就是按照我们对这个接口的实现来定义的。
 *
 *
 *
 */
public class PriorityBlockingQueueBean implements Runnable,Comparable{
    private  int i;
    public  PriorityBlockingQueueBean(Integer num){
        i=num;
    }

    @Override
    public int compareTo(Object o) {
        PriorityBlockingQueueBean msg = (PriorityBlockingQueueBean)o;
        return this.i>msg.i?1:( this.i<msg.i?-1:0);

    }

    @Override
    public void run() {
        System.out.println("run方法倒数几秒开始:：" + Thread.currentThread().getName() + ":开始："+i);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println( ":异常");
            // e.printStackTrace();
        }
    }







    public static void main(String[] args) throws Exception {
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                20, 0L, TimeUnit.DAYS, priorityBlockingQueue,
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        PriorityBlockingQueueBean test=new PriorityBlockingQueueBean(21);
        threadPoolExecutor.execute(test);
         test=new PriorityBlockingQueueBean(22);
        threadPoolExecutor.execute(test);

         test=new PriorityBlockingQueueBean(23);
        threadPoolExecutor.execute(test);
         test=new PriorityBlockingQueueBean(25);
        threadPoolExecutor.execute(test);
         test=new PriorityBlockingQueueBean(27);
        threadPoolExecutor.execute(test);

        for (int i = 0; i < 50; i++) {
            try {
                PriorityBlockingQueueBean test2=new PriorityBlockingQueueBean(i*3);
                threadPoolExecutor.execute(test2);
            } catch (Exception e) {
                System.out.println(i + ":异常");
                e.printStackTrace();
            }
        }

        System.out.println("全部:结束");


    }

}

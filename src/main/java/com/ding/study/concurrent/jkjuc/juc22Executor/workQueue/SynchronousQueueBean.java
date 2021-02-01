package com.ding.study.concurrent.jkjuc.juc22Executor.workQueue;

import java.util.concurrent.*;

/**
 * 字节new新线程
 * SynchronousQueue(没有容量直接new新线程Executors.newCachedThreadPool)：队列内部仅允许容纳一个元素。当一个线程插入一个元素后会被阻塞，除非这个元素被另一个线程消费。
 * 参考源码：
 * Executors.newCachedThreadPool();
 */
public class SynchronousQueueBean implements Runnable{
    private  int i;
    public  SynchronousQueueBean(Integer num){
        i=num;
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
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                2, 0L, TimeUnit.DAYS, synchronousQueue,
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        SynchronousQueueBean test=new SynchronousQueueBean(21);
        threadPoolExecutor.execute(test);
        test=new SynchronousQueueBean(22);
        threadPoolExecutor.execute(test);

        test=new SynchronousQueueBean(23);
        threadPoolExecutor.execute(test);
        test=new SynchronousQueueBean(25);
        threadPoolExecutor.execute(test);
        test=new SynchronousQueueBean(27);
        threadPoolExecutor.execute(test);

        for (int i = 0; i < 50; i++) {
            try {
                SynchronousQueueBean test2=new SynchronousQueueBean(i*3);
                threadPoolExecutor.execute(test2);
            } catch (Exception e) {
                System.out.println(i + ":异常");
                e.printStackTrace();
            }
        }

        System.out.println("全部:结束");


    }

}

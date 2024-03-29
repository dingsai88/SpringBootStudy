package com.ding.study.concurrent.jkjuc.juc22Executor;

import java.util.concurrent.*;

/**
 * 工具类
 *@see com.ding.study.util.ThreadPollExecutorUtil
 *
 *
 * @author daniel 2019-10-25 0025.
 */
public class ExecutorTest {

    public static void main(String[] args) throws Exception {
        //超过就报错-2
        Executor executor = new ThreadPoolExecutor(2, 2, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
     /*   Executor executor = new ThreadPoolExecutor(2, 2, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy ());*/
/*
        Executor executor = new ThreadPoolExecutor(2, 2, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy ());*/

/*
        Executor executor = new ThreadPoolExecutor(2, 2, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy ());*/
    /*    Executors.newCachedThreadPool（无界线程池，自动线程回收）
        Executors.newFixedThreadPool（固定大小的线程池）；
        Executors.newSingleThreadExecutor（单一后台线程）；*/

        Executor   executorSingle=   Executors.newSingleThreadExecutor();
        Executor   executorCached=   Executors.newCachedThreadPool();
        Executor   executorFixed=   Executors.newFixedThreadPool(1);
        Executor   executorScheduled=   Executors.newScheduledThreadPool(2);
        Executor   executorWork=   Executors.newWorkStealingPool();


        System.out.println("开始");
          for(int i=0;i<22;i++){
              System.out.println("开始技术:"+i);
              executor.execute(new Runnable() {
                  @Override
                  public void run() {
                      try {
                          System.out.println(Thread.currentThread().getName()+"线程启动");
                          Thread.sleep(9111);
                      }catch (Exception e){
                          e.printStackTrace();
                      }
                  }
              });
          }
        System.out.println("结束");






    }













}

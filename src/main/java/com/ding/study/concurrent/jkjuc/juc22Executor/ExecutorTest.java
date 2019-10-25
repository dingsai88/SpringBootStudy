package com.ding.study.concurrent.jkjuc.juc22Executor;

import java.util.concurrent.*;

/**
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

        System.out.println("开始");
          for(int i=0;i<22;i++){
              System.out.println("开始技术:"+i);
              executor.execute(new Runnable() {
                  @Override
                  public void run() {
                      try {
                          System.out.println(Thread.currentThread().getName()+"线程启动");
                          Thread.sleep(1111);
                      }catch (Exception e){
                          e.printStackTrace();
                      }
                  }
              });
          }
        System.out.println("结束");
    }
}

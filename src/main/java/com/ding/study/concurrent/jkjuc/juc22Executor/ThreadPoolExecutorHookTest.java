package com.ding.study.concurrent.jkjuc.juc22Executor;

import java.util.concurrent.*;

/**
 * 线程执行前后钩子方法
 * @author daniel 2021-11-25 0025.
 */
public class ThreadPoolExecutorHookTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (int i = 0; i < 20; i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Running...!");
                    try {
                        Thread.sleep(1111);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(111);
        System.out.println("活跃线程数量:"+exec.getActiveCount());
        exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 100;
            }
        });


        exec.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        System.out.println("shutdown前...!");
        exec.shutdown();
        System.out.println("shutdown后...!");

    }


    /**
     * 测试hook方法
     */

  private static   ThreadPoolExecutor exec = new ThreadPoolExecutor(500, 500, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>()) {
        @Override
        protected void terminated() {
            System.out.println("Executor Has Terminated! 只执行一次，shutdown线程池才执行");
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("before!");
            super.beforeExecute(t, r);
        }


        @Override
        protected void afterExecute(Runnable r, Throwable t) {

            super.afterExecute(r, t);

            if (t == null && r instanceof Future<?>) {
                try {
                    Object result = ((Future<?>) r).get();
                } catch (CancellationException ce) {
                    t = ce;
                } catch (ExecutionException ee) {
                    t = ee.getCause();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // ignore/reset
                }
            }
            if (t != null) {
                System.out.println("afterExecute Throwable"+t);
            }

            System.out.println("after  Execute  ");
        }
    };



}

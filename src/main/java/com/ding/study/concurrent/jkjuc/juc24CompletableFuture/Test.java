package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.concurrent.*;

public class Test {

    // 创建一个ExecutorService来管理线程
    static  ExecutorService executor = Executors.newCachedThreadPool();


    public static  void main1(String [] args) throws Exception{


        Future<String>[] arr=new Future[5];

        for (int i=0;i<5;i++){
            TestBean testBean = new TestBean();
            testBean.setName("name"+i);
            final int j=i;
            // 提交一个Callable任务，并得到一个Future对象
            Future<String> futureResult = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    if(j==3){
                        testBean.sleep(10);
                    }else {
                        testBean.sleep(5);
                    }

                    return "Hello, Future!"+testBean.getName();
                }
            });

            arr[i]=futureResult;
        }

        for( Future<String> t :arr){
            String result = t.get();
            todoMethod("方法1返回 "+result);
        }

        // 关闭ExecutorService
   //     executor.shutdown();


    }

    public static void main2(String[] args) throws Exception {
/*        // 创建一个ExecutorService来管理线程
        ExecutorService executor = Executors.newCachedThreadPool();*/
        // 创建一个CompletionService来管理异步任务的结果
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < 5; i++) {
            TestBean testBean = new TestBean();
            testBean.setName("name" + i);
            final int j = i;
            // 提交一个Callable任务
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    if (j == 3) {
                        testBean.sleep(10);
                    } else {
                        testBean.sleep(5);
                    }
                    return "Hello, Future!" + testBean.getName();
                }
            });
        }

        // 处理异步任务的结果
        for (int i = 0; i < 5; i++) {
            try {
                //  方法会阻塞直到有任务完成
                Future<String> future = completionService.take();
                String result = future.get();
                todoMethod("方法2返回 "+result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 关闭ExecutorService
       // executor.shutdown();
    }


    public static void main3(String[] args) throws Exception{


        CompletableFuture<?>[] futures = new CompletableFuture[5];
        for (int i = 0; i < 5; i++) {
            TestBean testBean = new TestBean();
            testBean.setName("name" + i);
            final int j = i;
            // 使用CompletableFuture异步执行任务，并处理每个任务的结果
            futures[i] = CompletableFuture.supplyAsync(() -> {
                try {
                    if (j == 3) {
                        testBean.sleep(10);
                    } else {
                        testBean.sleep(5);
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
                return "Hello, Future!" + testBean.getName();
            }, executor).thenAccept(result -> todoMethod("方法3返回 "+result)); // 直接处理结果
        }

        // 等待所有的CompletableFuture任务完成
        CompletableFuture.allOf(futures).join();


        // 关闭ExecutorService
       // executor.shutdown();
    }

    public static void todoMethod(String  str){
        try {
            System.out.println(" 返回" + str);
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception{
        Thread.sleep(5000);
        System.out.println(" 开始 " );

        long startTime1 = System.currentTimeMillis();

        for (int i=0;i<50;i++){
            main1(null);
        }
        long startTime2 = System.currentTimeMillis();
        for (int i=0;i<50;i++){
            main2(null);
        }
        long startTime3 = System.currentTimeMillis();
        for (int i=0;i<50;i++){
            main3(null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(" Future.get() 耗时 " + (startTime2-startTime1));
        System.out.println(" completionService.take()方法2 耗时 " + (startTime3-startTime2));
        System.out.println(" CompletableFuture.allOf.join方法3 耗时 " + (endTime-startTime3));
        // 关闭ExecutorService
        executor.shutdown();
    }
}

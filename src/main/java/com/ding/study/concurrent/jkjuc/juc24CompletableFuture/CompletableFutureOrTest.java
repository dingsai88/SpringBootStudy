package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * @author daniel 2019-11-1 0001.
 */
public class CompletableFutureOrTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


       //根据不同的业务类型创建不同的线程池，以避免互相干扰。
        // 默认情况下 CompletableFuture 会使用公共的 ForkJoinPool 线程池，这个线程池默认创建的线程数是 CPU 的核数
        Executor executor= Executors.newFixedThreadPool(3);
        //开一个线程 做操作
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(2111) + "线程a"));
        //开一个线程 做操作
        CompletableFuture completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(2111) + "线程b"));
        //两个回来一个
        CompletableFuture result = completableFuture1.applyToEither(completableFuture2, new FunctionImpl("1"));

        System.out.println(Thread.currentThread().getName() + "-------:bean.join1:" + result.join());
        System.out.println(Thread.currentThread().getName() + "--------:bean.get1:" + result.get());
         try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "------------------------------:bean.join2:" + result.join());
        System.out.println(Thread.currentThread().getName() + "-------------------------------:bean.get2:" + result.get());
        System.out.println("\n\n\n\n--------------主线程 1  已经结束 \n\n\n\n\n");







        //开一个线程 做操作
          completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(2111) + "线程a"));
        //开一个线程 做操作
          completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(2111) + "线程b"));
        //两个回来一个
          result = completableFuture1.acceptEither(completableFuture2, new ConsumerImpl("1"));

        System.out.println(Thread.currentThread().getName() + ":bean.join1-----------------------:" + result.join());
        System.out.println(Thread.currentThread().getName() + ":bean.get1-------------------------:" + result.get());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-----------------------------------:bean.join2:" + result.join());
        System.out.println(Thread.currentThread().getName() + "------------------------------------:bean.get2:" + result.get());
        System.out.println("\n\n\n\n--------------主线程 3 已经结束 \n\n\n\n\n");






        //开一个线程 做操作
        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(2111) + "线程a"));
        //开一个线程 做操作
        completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(2111) + "线程b"));
        //两个回来一个
        result = completableFuture1.runAfterEither(completableFuture2, new RunnableImpl());

        System.out.println(Thread.currentThread().getName() + ":bean.join1-----------------------:" + result.join());
        System.out.println(Thread.currentThread().getName() + ":bean.get1-------------------------:" + result.get());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-----------------------------------:bean.join2:" + result.join());
        System.out.println(Thread.currentThread().getName() + "------------------------------------:bean.get2:" + result.get());
        System.out.println("\n\n\n\n--------------主线程 4  已经结束 \n\n\n\n\n");





    }
}

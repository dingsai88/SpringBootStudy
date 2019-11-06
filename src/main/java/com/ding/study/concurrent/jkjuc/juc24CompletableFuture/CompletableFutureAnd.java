package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author daniel 2019-11-1 0001.
 */
public class CompletableFutureAnd {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> Thread.currentThread().getName() + "-Hello World")      //①
                        .thenApply(s -> s + " QQ-" + Thread.currentThread().getName())  //②
                        .thenApply(String::toUpperCase);//③
        System.out.println(f0.join() + "\n\n\n\n");
        //根据不同的业务类型创建不同的线程池，以避免互相干扰。
        Executor executor= Executors.newFixedThreadPool(3);

        //开一个线程 做操作
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""),executor);
        //开一个线程 做操作
        CompletableFuture completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""),executor);
        //合并1.这个方法既能接收参数也支持返回值
        CompletableFuture result = completableFuture1.thenCombine(completableFuture2, new BiFunctionImpl((new Random()).nextInt(10) + ""));

        System.out.println(Thread.currentThread().getName() + ":thenCombine:bean.join:" + result.join());
        System.out.println(Thread.currentThread().getName() + ":thenCombine:bean.get:" + result.get());
        System.out.println("\n\n主线程1已结束\n\n");



        //开一个线程 做操作
         completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""),executor);
        //开一个线程 做操作
         completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""),executor);
        //合并2.支持入参不支持出参
         result = completableFuture1.thenAcceptBoth(completableFuture2, new BiConsumerImpl());



        System.out.println(Thread.currentThread().getName() + ":thenCombine:bean.join:" + result.join());
        System.out.println(Thread.currentThread().getName() + ":thenCombine:bean.get:" + result.get());
        System.out.println("\n\n主线程2已结束\n\n");






        //开一个线程 做操作
        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""),executor);
        //开一个线程 做操作
        completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""),executor);
        //合并3.不支持入参出参
        result = completableFuture1.runAfterBoth(completableFuture2, new RunnableImpl());



        System.out.println(Thread.currentThread().getName() + ":runAfterBoth:bean.join:" + result.join());
        System.out.println(Thread.currentThread().getName() + ":runAfterBoth:bean.get:" + result.get());
        System.out.println("\n\n主线程3已结束\n\n");









        //开一个线程 做操作
        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""),executor);
        //开一个线程 做操作
        completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""),executor);
        //合并3.不支持入参出参
        result = completableFuture1.runAfterBoth(completableFuture2, new RunnableImpl());



        System.out.println(Thread.currentThread().getName() + ":runAfterBoth:bean.join:" + result.join());
        System.out.println(Thread.currentThread().getName() + ":runAfterBoth:bean.get:" + result.get());
        System.out.println("\n\n主线程3已结束\n\n");


    }









}

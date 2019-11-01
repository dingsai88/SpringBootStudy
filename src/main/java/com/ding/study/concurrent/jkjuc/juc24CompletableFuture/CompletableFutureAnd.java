package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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


        //开一个线程 做操作
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""));
        //开一个线程 做操作
        CompletableFuture completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""));
        //合并
        CompletableFuture result = completableFuture1.thenCombine(completableFuture2, new BiFunctionImpl((new Random()).nextInt(10) + ""));

        System.out.println(Thread.currentThread().getName() + ":bean.join:" + result.join());
        System.out.println(Thread.currentThread().getName() + ":bean.get:" + result.get());

    }

}

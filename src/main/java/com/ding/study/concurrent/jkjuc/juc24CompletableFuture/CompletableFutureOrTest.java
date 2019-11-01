package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @author daniel 2019-11-1 0001.
 */
public class CompletableFutureOrTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {



        //开一个线程 做操作
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + "线程a"));
        //开一个线程 做操作
        CompletableFuture completableFuture2 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + "线程b"));
        //两个回来一个
        CompletableFuture result = completableFuture1.acceptEither(completableFuture2, new ConsumerImpl("1"));
        result.exceptionally(new Function() {
            @Override
            public Object apply(Object o) {
                System.out.println(Thread.currentThread().getName() + ":exceptionally`````````````````.:" +o);
                return null;
            }
        });

        System.out.println(Thread.currentThread().getName() + ":bean.join1:" + result.join());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ":bean.join2:" + result.join());


    }
}

package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author daniel 2019-11-1 0001.
 */
public class CompletableFutureBingXingTest {


    public static void main(String[] args)throws Exception {

        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> Thread.currentThread().getName() + "-Hello World")      //①
                        .thenApply(s -> s + " QQ-" + Thread.currentThread().getName())  //②
                        .thenApply(String::toUpperCase);//③
        System.out.println(f0.join()+"\n\n\n\n");

        //根据不同的业务类型创建不同的线程池，以避免互相干扰。
        Executor executor= Executors.newFixedThreadPool(3);

        //开一个线程 做操作
        CompletableFuture  completableFuture = CompletableFuture.supplyAsync( new SupplierImpl((new Random()).nextInt(10)+""),executor);
        //异常处理
        completableFuture.completeExceptionally(new RuntimeException());
        //等上一个线程完成以后，在consumerImpl里消费
        completableFuture.thenAccept(new ConsumerImpl("1"));
        //另外一个 consumer
        completableFuture.thenAccept(new ConsumerImpl("2"));
        //异常处理2
        completableFuture.exceptionally(new Function() {
            @Override
            public Object apply(Object o) {
                System.out.println(Thread.currentThread().getName() + ":exceptionally`````````````````.:" +o);
                return null;
            }
        });
        System.out.println(Thread.currentThread().getName() + ":bean11111111:");
        Thread.sleep((new Random()).nextInt(500));
     //   System.out.println(Thread.currentThread().getName() + ":bean:" + completableFuture.join());

    }


}

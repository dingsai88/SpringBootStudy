package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author daniel 2019-11-6 0006.
 */
public class CompletableFutureChuanXing {

    public static void main(String[] args) throws Exception {

   /*     CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> Thread.currentThread().getName() + "-Hello World")      //①
                        .thenApply(s -> s + " QQ-" + Thread.currentThread().getName())  //②
                        .thenApply(String::toUpperCase);//③*/

        //根据不同的业务类型创建不同的线程池，以避免互相干扰。
        Executor executor = Executors.newFixedThreadPool(1);

        //开一个线程 做操作
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""), executor);
        FunctionImpl function = new FunctionImpl("function");
        //串行1
        //thenApply 系列函数里参数 fn 的类型是接口 Function，这个接口里与 CompletionStage 相关的方法是 R apply(T t)，
        // 这个方法既能接收参数也支持返回值，所以 thenApply 系列方法返回的是CompletionStage。
        CompletableFuture result = completableFuture1.thenApply(function);

        System.out.println(Thread.currentThread().getName() + ":11bean.get:" + result.get());
        System.out.println(Thread.currentThread().getName() + ":11bean.join:" + result.join());


        System.out.println("--------------------------主线程分隔符1\n\n\n");


        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""), executor);
        ConsumerImpl consumer = new ConsumerImpl("ConsumerImpl");
        //串行2
        //这个接口里与 CompletionStage 相关的方法是 void accept(T t)，这个方法虽然支持参数，
        // 但却不支持回值，所以 thenAccept 系列方法返回的是CompletionStage。
        result = completableFuture1.thenAccept(consumer);
        System.out.println(Thread.currentThread().getName() + ":22bean.get:" + result.get());
        System.out.println(Thread.currentThread().getName() + ":22bean.join:" + result.join());

        System.out.println("------------------------主线程分隔符2\n\n\n");


        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""), executor);
        RunnableImpl runnable = new RunnableImpl();
        //串行3
        //所以 action 既不能接收参数也不支持返回值，所以 thenRun 系列方法返回的也是CompletionStage。
        result = completableFuture1.thenRun(runnable);
        System.out.println(Thread.currentThread().getName() + ":33bean.get:" + result.get());
        System.out.println(Thread.currentThread().getName() + ":33bean.join:" + result.join());


        System.out.println("------------------------主线程分隔符3\n\n\n");


        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""), executor);
        //串行4
        //thenCompose 系列方法，这个系列的方法会新创建出一个子流程，
        // 最终结果和 thenApply 系列是相同的。
        result = completableFuture1.thenCompose(new Function() {
            @Override
            public Object apply(Object o) {
                System.out.println("thenComposeAsync中执行:"+Thread.currentThread().getName());

                return CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + "线程b"));
            }
        });
        System.out.println(Thread.currentThread().getName() + ":44bean.get:" + result.get());
        System.out.println(Thread.currentThread().getName() + ":44bean.join:" + result.join());

        System.out.println("------------------------主线程分隔符不开线程的已结束\n\n\n\n\n\n\n\n\n");
        Thread.sleep((new Random()).nextInt(1002));

        //异步版本
         async();

    }


    public static void async()throws Exception {
        Executor executor =new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1));
        //异步版本
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""), executor);
        FunctionImpl function = new FunctionImpl("function");
        //串行1
        //thenApply 系列函数里参数 fn 的类型是接口 Function，这个接口里与 CompletionStage 相关的方法是 R apply(T t)，
        // 这个方法既能接收参数也支持返回值，所以 thenApply 系列方法返回的是CompletionStage。
        CompletableFuture result = completableFuture1.thenApplyAsync(function);

        System.out.println(Thread.currentThread().getName() + ":Async:11bean.get:" + result.get());
        System.out.println(Thread.currentThread().getName() + ":Async:11bean.join:" + result.join());


        System.out.println("--------------------------:Async主线程分隔符1\n\n\n");


        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""), executor);
        ConsumerImpl consumer = new ConsumerImpl("ConsumerImpl");
        //串行2
        //这个接口里与 CompletionStage 相关的方法是 void accept(T t)，这个方法虽然支持参数，
        // 但却不支持回值，所以 thenAccept 系列方法返回的是CompletionStage。
        result = completableFuture1.thenAcceptAsync(consumer);
        System.out.println(Thread.currentThread().getName() + ":Async:22bean.get:" + result.get());
        System.out.println(Thread.currentThread().getName() + ":Async:22bean.join:" + result.join());


        System.out.println("------------------------:Async主线程分隔符2\n\n\n");


        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""), executor);
        RunnableImpl runnable = new RunnableImpl();
        //串行3
        //所以 action 既不能接收参数也不支持返回值，所以 thenRun 系列方法返回的也是CompletionStage。
        result = completableFuture1.thenRunAsync(runnable);
        System.out.println(Thread.currentThread().getName() + ":Async:33bean.get:" + result.get());
        System.out.println(Thread.currentThread().getName() + ":Async:33bean.join:" + result.join());


        System.out.println("------------------------:Async主线程分隔符3\n\n\n");


        completableFuture1 = CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + ""), executor);
        //串行4
        //thenCompose 系列方法，这个系列的方法会新创建出一个子流程，
        // 最终结果和 thenApply 系列是相同的。
        result = completableFuture1.thenComposeAsync(new Function() {
            @Override
            public Object apply(Object o) {


                System.out.println("thenComposeAsync中执行:"+Thread.currentThread().getName());

                return CompletableFuture.supplyAsync(new SupplierImpl((new Random()).nextInt(10) + "线程b"));
            }
        });
        System.out.println(Thread.currentThread().getName() + ":Async:44bean.get:" + result.get());
        System.out.println(Thread.currentThread().getName() + ":Async:44bean.join:" + result.join());
    }


}

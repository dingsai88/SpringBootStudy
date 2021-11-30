package com.ding.study.concurrent.jkjuc.juc24CompletableFuture;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 1. 异常处理必须要写在一起
 2.exceptionally() 方法来处理异常
 3.finally1>whenComplete()无返回值
 4.finally2有返回值>handle
 * @author daniel 2019-11-6 0006.
 */
public class CompletableFutureException {


    public static void main(String[] args) throws Exception {


        //开一个线程 做操作
        CompletableFuture<SupplierExceptionImpl> completableFuture1 =
                CompletableFuture.supplyAsync(new SupplierExceptionImpl()).exceptionally(new Function<Throwable, SupplierExceptionImpl>() {
                    @Override
                    public SupplierExceptionImpl apply(Throwable throwable) {
                        System.out.println("3运行异常\n\n");
                        return null;
                    }
                }).thenApply(new Function() {
                    @Override
                    public Object apply(Object o) {
                        System.out.println("4最后串行执行\n\n");
                        return null;
                    }
                }).whenComplete(new BiConsumer() {
                    @Override
                    public void accept(Object o, Object o2) {
                        System.out.println("5运行finally无返回值\n\n");
                    }
                }).handle(new BiFunction() {
                    @Override
                    public Object apply(Object o, Object o2) {
                        System.out.println("6运行finally2有返回值\n\n");
                        return null;
                    }
                });

        //completableFuture1.exceptionally(new FunctionExceptionImpl("1"));


        System.out.println("\n\n主线程1已结束\n\n");
    //    System.out.println(Thread.currentThread().getName() + ":thenCombine:bean.join:" + completableFuture1.join());
        System.out.println(Thread.currentThread().getName() + ":thenCombine:bean.get:" + completableFuture1.get(2, TimeUnit.SECONDS));
        Thread.sleep(1111);
        System.out.println("\n\n主线程1已结束2\n\n");
    }


}

package com.ding.study.concurrent.jkjuc.juc23Future;

import java.util.concurrent.*;

/**
 * @author daniel 2019-10-25 0025.
 */
public class FutureTest {


    public static void main(String[] args) throws Exception {

        Executor executor = new ThreadPoolExecutor(2, 2, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        ExecutorService executorService = Executors.newFixedThreadPool(1);


        TestBean testBean = new TestBean();
        testBean.setName("1");
        Task task = new Task();
        task.setBean(testBean);
        System.out.println(testBean.getName());
        //Runable方式调用
        Future future = executorService.submit(task, testBean);
        System.out.println(future.get());
        System.out.println(testBean.getName() + "\n\n");
        ////////////////
        CallableDemo callableDemo = new CallableDemo();
        callableDemo.setBean(testBean);
        //Callable方式调用
        Future future2 = executorService.submit(callableDemo);
        System.out.println(future2.get());
        TestBean testBean3 = (TestBean) future2.get();
        System.out.println(testBean3.getName());
        System.out.println(testBean.getName());


        //////////
        //FutureTask 方式调用
        FutureTask futureTask = new FutureTask(new Runnable() {
            @Override
            public void run() {
                testBean.setName("zzz");
            }
        }, testBean);


        new Thread(futureTask).start();

        System.out.println("----:" + futureTask.get());
    }


}

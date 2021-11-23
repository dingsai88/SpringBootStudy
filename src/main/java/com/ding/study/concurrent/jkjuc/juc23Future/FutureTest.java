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

        TestBean testBean = new TestBean();
        testBean.setName("1");

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        //创建线程 实现Callable的call方法，可以返回异常和返回值
        CallableDemo callableDemo = new CallableDemo();
        callableDemo.setBean(testBean);
        //Callable方式调用
        Future future2 = executorService.submit(callableDemo);
        System.out.println(future2.get());
        TestBean testBean3 = (TestBean) future2.get();
        System.out.println(testBean3.getName());
        System.out.println(testBean.getName());






        Task task = new Task();
        task.setBean(testBean);
        System.out.println("线程启动前:"+testBean.getName());
        //Runable方式调用
        Future future = executorService.submit(task, testBean);
        System.out.println("线程返回"+future.get());
        System.out.println("原始对象"+testBean.getName() + "\n\n");





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

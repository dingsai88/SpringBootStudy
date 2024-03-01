package com.ding.study.concurrent.jkjuc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author daniel 2019-9-24 0024.
 */
public class TestMain {




    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);


        List<Future<BeanTest>> futures = new ArrayList<>();

        List<BeanTest> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            BeanTest aa=new BeanTest();
            aa.setId(i+"");
            list.add(aa);
        }

        System.out.println("开始 ");

        for (BeanTest t : list) {
            Future<BeanTest> future = executor.submit(() -> {
                System.out.println("线程开始 "+ Thread.currentThread().getName());
                t.sleep();
                return t;
            });
            Thread.sleep(1000);
            futures.add(future);
        }
        System.out.println("futures size  "+futures.size());

// 等待所有任务完成
        for (Future<BeanTest> future : futures) {
            BeanTest result = future.get();
            System.out.println("更新bean "+result);
        }
        System.out.println("结束 ");

        executor.shutdown();
    }
}

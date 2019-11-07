package com.ding.study.concurrent.jkjuc.juc25CompetionService;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.concurrent.*;

/**
 * @author daniel 2019-11-5 0005.
 */
public class CompetionServiceTest {
    public static void main(String[] args) throws Exception {
        System.out.println("开始:");
        CompletionService cs = new ExecutorCompletionService(Executors.newSingleThreadExecutor());
        TestBean testBean = new TestBean();
        testBean.setName("初始化:");
        CSRunnableImpl name = new CSRunnableImpl(testBean);
        cs.submit(name, testBean);

        cs.submit(name, testBean);
        cs.submit(name, testBean);
        cs.submit(name, testBean);
        System.out.println("都提交:");
        for (int i = 0; i < 4; i++) {
            //一直等
            // System.out.println("最终返回:"+ cs.take().get()+"\n\n\n");
            //等待20秒；不加时间不等待，没有就返回null
            System.out.println("最终返回:" + cs.poll(20, TimeUnit.SECONDS).get() + "\n\n\n");
        }

        System.out.println("分割线·····:");
        cs = new ExecutorCompletionService(Executors.newSingleThreadExecutor());
        CallableImpl callable = new CallableImpl();
        cs.submit(callable);
        cs.submit(callable);
        cs.submit(callable);
        cs.submit(callable);
        for (int i = 0; i < 4; i++) {
            System.out.println("最终返回22:"+ cs.take().get()+"\n\n\n");

        }
    }
}

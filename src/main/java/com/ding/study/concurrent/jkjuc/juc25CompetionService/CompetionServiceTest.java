package com.ding.study.concurrent.jkjuc.juc25CompetionService;

import com.ding.study.concurrent.jkjuc.juc23Future.TestBean;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * @author daniel 2019-11-5 0005.
 */
public class CompetionServiceTest {
    public static void main(String[] args) throws Exception {
        System.out.println("开始:");
        CompletionService cs=new ExecutorCompletionService(Executors.newSingleThreadExecutor());
        TestBean testBean=new TestBean();
        testBean.setName("初始化:");
        CSRunnableImpl name=new CSRunnableImpl(testBean);
        cs.submit(name,testBean);

        cs.submit(name,testBean);
        cs.submit(name,testBean);
        cs.submit(name,testBean);
        System.out.println("都提交:");
        for (int i=0;i<4;i++){
            System.out.println("最终返回:"+ cs.take().get());
        }

    }
}

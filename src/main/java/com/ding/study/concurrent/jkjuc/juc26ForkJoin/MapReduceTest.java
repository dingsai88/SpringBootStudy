package com.ding.study.concurrent.jkjuc.juc26ForkJoin;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 用ForkJoinPool模拟mapreduce 切词
 * @author daniel 2019-11-8 0008.
 */
public class MapReduceTest  {


  public   static void main(String[] args){
        String[] fc = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"};
        //创建ForkJoin线程池
        ForkJoinPool fjp =new ForkJoinPool(3);
        //创建任务
      MapReduceBean mr = new MapReduceBean(fc, 0, fc.length);
        //启动任务
        Map<String, Long> result = fjp.invoke(mr);
        //输出结果
        result.forEach((k, v)->System.out.println(k+":"+v));
    }

}
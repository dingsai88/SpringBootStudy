package com.ding.study.concurrent.jkjuc.juc36Log4j;

import com.ding.study.concurrent.jkjuc.juc23Future.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author daniel 2019-11-21 0021.
 */
public class Log4jTest {

    public static void main(String []args){

    }

    // 任务队列
    BlockingQueue<Task> bq=new
            LinkedBlockingQueue<>(2000);        // 启动 5 个消费者线程
    // 执行批量任务
    void start() {
        ExecutorService es= Executors.newFixedThreadPool(5);
        for (int i=0; i<5; i++) {
            es.execute(()->{
                try {
                    while (true) {
                        // 获取批量任务
                        List<Task> ts=pollTasks();
                        // 执行批量任务
                        // execTasks(ts);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
    // 从任务队列中获取批量任务
    List<Task> pollTasks()
            throws InterruptedException{
        List<Task> ts=new LinkedList<>();
        // 阻塞式获取一条任务
        Task t = bq.take();
        while (t != null) {
            ts.add(t);
            // 非阻塞式获取一条任务
            t = bq.poll();
        }
        return ts;
    }
    // 批量执行任务
  //  execTasks(List<Task> ts) {
        // 省略具体代码无数
    //}

}

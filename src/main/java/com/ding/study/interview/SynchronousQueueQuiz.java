package com.ding.study.interview;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author daniel 2019-8-5 0005.
 */
public class SynchronousQueueQuiz {
    public static void main(String[] args) throws Exception {



        BlockingQueue<Integer> queue = new
                SynchronousQueue<>();
        System. out .print(queue.offer(1) + " ");
        System. out .print(queue.offer(2) + " ");
        System. out .print(queue.offer(3) + " ");
        System. out .print(queue.take() + " ");
        System. out .println(queue.size());
    }
}

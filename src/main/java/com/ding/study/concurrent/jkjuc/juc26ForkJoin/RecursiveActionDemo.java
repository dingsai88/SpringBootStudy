package com.ding.study.concurrent.jkjuc.juc26ForkJoin;

import java.util.concurrent.RecursiveAction;

/**
 * @author daniel 2021-11-23 0023.
 */
public class RecursiveActionDemo extends RecursiveAction {
    /**
     *  每个"小任务"最多只打印20个数
     */
    private static final int MAX = 20;

    private int start;
    private int end;

    public RecursiveActionDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        //当end-start的值小于MAX时，开始打印
        if((end-start) < MAX) {
            for(int i= start; i<end;i++) {
                System.out.println(Thread.currentThread().getName()+" i的值 "+i);
            }
        }else {
            // 将大任务分解成两个小任务
            int middle = (start + end) / 2;
            RecursiveActionDemo left = new RecursiveActionDemo(start, middle);
            RecursiveActionDemo right = new RecursiveActionDemo(middle, end);
            left.fork();
            right.fork();
        }
    }

}

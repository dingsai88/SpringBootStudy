package com.ding.study.concurrent.jkjuc.juc91;

/**
 * @author daniel 2021-12-2 0002.
 */
public class InterrupteTest {

    public static void main(String[] args) throws Exception {
        System.out.println("main线程开始:");


        // 用法1：sleep+InterrupterException
        InterrupterRunnableBean interrupterRunnableBean = new InterrupterRunnableBean();
        Thread thread = new Thread(interrupterRunnableBean);
        thread.start();

        Thread.sleep(1000);
        //线程中断
      thread.interrupt();
        //不会重置状态
     //   thread.isInterrupted();
        //会重置状态
     //   thread.interrupted();
        try {
           Thread.currentThread().interrupt();
           Thread.interrupted();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("main线程结束");
        Thread.sleep(3000);
        System.out.println("main线程结束睡完了\n\n\n\n");



        //用法2:threadObj.interrupt()+InterruptedException

        InterrupterRunnableBean2 interrupterRunnableBean2 = new InterrupterRunnableBean2();
        Thread thread2 = new Thread(interrupterRunnableBean2);
        thread2.start();

        Thread.sleep(100);
        //线程中断
        thread2.interrupt();
        System.out.println("main2线程结束");
        Thread.sleep(3000);
        System.out.println("main2线程结束\n\n\n\n\n\n\n\n\n");


        //用法2:threadObj.interrupt()+InterruptedException
        InterrupterRunnableBeanWait interrupterRunnableBean3 = new InterrupterRunnableBeanWait();
        Thread thread3 = new Thread(interrupterRunnableBean3);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InterrupterRunnableBeanWait.waitTestStatic();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();


        Thread.sleep(100);
        thread3.start();
        //线程中断
        thread3.interrupt();
        System.out.println("main4线程结束");
        Thread.sleep(3000);







    }


}

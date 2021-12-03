package com.ding.study.concurrent.jkjuc.juc91;

/**
 * @author daniel 2021-12-2 0002.
 */
public class InterrupterRunnableBeanWait implements Runnable {
    private String str = "key";

    @Override
    public void run() {
        try {
            /**1.
             * 非本对象会异常:IllegalMonitorStateException
             */
           /* synchronized (str) {
                this.wait();
            }*/

            /////////////////
            /**2.
             * 带有synchronized 的方法可以 wait();
             *
             */
          //  waitTest();

            /////////////////
            /**3.
             * 带有synchronized 本对象可以
             *
             */
       /*    synchronized (this){
               wait();
           }
*/
            waitTestStatic();
        }catch (InterruptedException e){
            System.out.println("InterruptedException");
            e.printStackTrace();

        }catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }

    public synchronized void  waitTest() throws InterruptedException {
            this.wait();
    }


    public static synchronized void  waitTestStatic() throws InterruptedException {
        try {
            System.out.println("waitTestStatic线程开始:"+Thread.currentThread().getName());
            //  blockMethod();
            for(int i=0;i<5;i++){
                Thread.sleep(1000);
                System.out.println("waitTestStatic线程结束:"+Thread.currentThread().getName()+"aaaa:"+i);

            }
            System.out.println("waitTestStatic线程结束:"+Thread.currentThread().getName());
        }catch (InterruptedException e){
            System.out.println("waitTestStaticInterruptedException");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("waitTestStaticException");
            e.printStackTrace();
        }
    }
}

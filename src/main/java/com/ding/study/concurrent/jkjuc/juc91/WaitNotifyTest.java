package com.ding.study.concurrent.jkjuc.juc91;

/**
 * @author daniel 2021-12-2 0002.
 */
public class WaitNotifyTest {

    public static void main(String[] args) throws Exception {
        System.out.println("方法开始:main");
        WaitNotifyTest test = new WaitNotifyTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.methodWaitSyn();
            }
        }).start();
        Thread.sleep(100);
        System.out.println("方法开始:main1");

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.methodNotifyAllSyn();

            }
        }).start();

        System.out.println("方法开始:main20");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // test.methodWaitSyn();
                test.methodNotifyAllSyn();
            }
        }).start();
        Thread.sleep(100000);

        //  test.methodWaitSyn();


        //  test.methodWaitSyn();
        System.out.println("方法开始:main20");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // test.methodWaitSyn();
                test.methodNotifyAllSyn();
            }
        }).start();
        System.out.println("方法开始:main30");

        System.out.println("\n\n\n方法开始:main结束\n\n\n");

        Thread.sleep(50000);
/////////////////////////////////////////////


        System.out.println("方法开始2:main0");
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.methodWait();
            }
        }).start();


        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.methodNotifyAll();
            }
        }).start();

        Thread.sleep(1000);

        System.out.println("方法开始:end\n\n\n\n\n");
    }

    public synchronized void methodWaitSyn() {
        for (int i = 0; i < 10; i++) {
        System.out.println(i+"methodSyn:" + Thread.currentThread().getName());
        try {
            // Thread.sleep(3000);

                wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i+"methodSyn:" + Thread.currentThread().getName());
        }
    }


    public synchronized void methodNotifyAllSyn() {

            System.out.println(":methodNotifyAllSyn:" + Thread.currentThread().getName());
            notifyAll();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(":methodNotifyAllSyn:" + Thread.currentThread().getName());

    }

    public void methodWait() {
        System.out.println("方法开始:methodWait" + Thread.currentThread().getName());
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法结束:methodSyn" + Thread.currentThread().getName());
    }


    public void methodNotifyAll() {
        System.out.println("方法开始:methodNotifyAll" + Thread.currentThread().getName());
        synchronized (this) {
            notifyAll();
        }
        System.out.println("方法结束:methodNotifyAllSyn" + Thread.currentThread().getName());
    }


}

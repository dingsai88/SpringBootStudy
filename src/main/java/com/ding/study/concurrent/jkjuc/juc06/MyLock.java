package com.ding.study.concurrent.jkjuc.juc06;

import java.util.ArrayList;
import java.util.List;

/**synchronized
 * 等待通知优化循环等待
 * synchronized wait notifyAll
 * @author daniel 2019-9-24 0024.
 */
public class MyLock {
    // 测试转账的main方法
    public static void main(String[] args) throws InterruptedException {
        Account src = new Account(10000);
        Account target = new Account(10000);
        for (int i = 0; i < 9999; i++) {
            new Thread(() -> {
                src.transactionToTarget(1, target);
            }).start();
        }
        Thread.sleep(1111);
        System.out.println("src=" + src.getbalance());
        System.out.println("target=" + target.getbalance());
             /* CountDownLatch countDownLatch = new CountDownLatch(9999);
        countDownLatch.countDown();
        countDownLatch.await();*/

    }

    /**
     * 账户类
     */
    static class Account {
        public Account(Integer balance) {
            this.balance = balance;
        }

        private Integer balance;

        /**
         * 转账方法
         *
         * @param money  金额
         * @param target
         */
        public  void transactionToTarget(Integer money, Account target) {
            //锁定两个账户
            Allocator.getInstance().apply(this, target);

            // Thread.sleep(11);

            this.balance -= money;
            target.setbalance(target.getbalance() + money);
            //释放两个账户
            Allocator.getInstance().release(this, target);
        }

        public Integer getbalance() {
            return balance;
        }

        public void setbalance(Integer balance) {
            this.balance = balance;
        }
    }

    /**
     * 单例锁类
     */
    static class Allocator {
        private static Allocator install = new Allocator();
        /**
         * 锁集合
         */
        private List<Account> locks = new ArrayList<>();

        private Allocator() {
        }

        public static Allocator getInstance() {
            return install;
        }

        /**
         * 申请锁定
         *
         * @param src
         * @param tag
         */
        public synchronized void apply(Account src, Account tag) {
            while (locks.contains(src) || locks.contains(tag)) {
                try {
                    System.out.println("wait" + Thread.currentThread().getName());
                    //等待-避免while ture占用CPU
                    //在线线程中调用wait方法的时候 要用synchronized锁住对象，确保代码段不会被多个线程调用
                    this.wait();
                } catch (InterruptedException e) {
                }
            }
            locks.add(src);
            locks.add(tag);
        }

        /**
         * 是否锁
         *
         * @param src
         * @param tag
         */
        public synchronized void release(Account src, Account tag) {
            locks.remove(src);
            locks.remove(tag);
            //通知全部等待的线程执行
            this.notifyAll();
        }


    }
}

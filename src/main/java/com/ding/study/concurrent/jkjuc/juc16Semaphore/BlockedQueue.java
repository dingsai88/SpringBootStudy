package com.ding.study.concurrent.jkjuc.juc16Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 注意:Condition 调用的是await方法 不是wait!!!!!!
 * @author daniel 2019-10-11 0011.
 */

/**
 *

 15 Lock和Condition下:dubbo如何用管程实现异步转同步
 我们讲到了javaSDK并发包里的lock有别与synchronized隐士锁的三个特性
 ：能够响应中断、支持超时和非阻塞地获取锁。那今天我们接着再来详细聊聊javasdk并发
 包里condition，condition实现了管程模型里面的条件变量。

 在管程里我们提到过java语言内置的管程里只有一个条件变量
 ，而lockcondition实现的管程是支持多个条件变量的。这是二者的一个重要区别

 在很多并发场景下，支持多个条件变量能够让我们的并发程序可读性更好，实现起来也更容易。
 例如，实现一个阻塞对垒，就需要两个条件变量。

 那如何利用两个条件变量快速实现阻塞队列呢

 一个阻塞队列，需要两个条件变量，一个队列不空，另一个是队列不满
 这个例子我们前面在介绍管程的时候详细说过，这里就不赘述。相关
 的代码，我这里重新列出来，你可以温故知新一下。

 不过，这里你需要注意，lock和condition实现的管程，线程等待和通知需要调用 await()
 signal、signalAll,他们的寓意和waitnotify notifyall相同。但是不一样的是
 lock 和condition实现的管程里只能使用前面的await signal signalall而后面的
 wait notify notify all只有在synchronized实现管程里才能使用。如果一步小心在
 lock condition实现的管程里调用了wait notify notifyall那程序可就彻底玩完了。

 javasdk并发包里lock和condition不过就是管程的一种实现而已。管程你已经很熟悉了
 那lock和condition的使用自然是小菜一碟。下面我们就来看看在指明项目dubbo中
 lock和condition是怎么用的。不过在开始介绍源码之前，我们还要先介绍两个概念：同步和异步

 同步和异步
 我们平时写的代码，基本都是同步的。但最近几年，异步编程大火。那同步和异步的区别到底是什么
 呢？通俗点来讲就是调用方式是否需要等待结果，如果需要等待结果，就是同步；如果不需要等待结果，就是异步。

 比如在下面的代码里，有一个计算圆周率小数点后100万位的方法牌m，这个方法可能需要
 执行两礼拜，如果调用pait1
 后，线程一直等着计算结果，等两立牌后结果返回，就可以执行printyg了，这个属于同步；如果调用pai之后
 线程不用等待计算结果，立刻就可以执行printf这个就属于异步。

 同步，是java代码默认的处理方式。如果你想让你的程序支持异步，可以通过下面两种方式来
 实现：
 1。调用方床架你一个子线程，在子线程中执行方法调用，这种调用我们称为异步调用；
 2。方法实现的时候，创建一个新的线程执行主要逻辑，主线程直接return，这种方法我们一般称为
 异步方法

 dubbo源码分析
 其实在编程领域，异步场景还是挺多的，比如tcp协议本身就是异步的，我们工作中经常用到
 的rpc调用，在tcp协议层面，发送完RPC之后，线程是不会等待RPC的响应结果的。可能
 你会觉得奇怪，平时工作中的rpc调用大多数都是同步的啊，
 其实很简单，一定是有人帮你做了异步转同步的事情。例如目前RPC dubbo就给我们
 做了异步转同步的事情，那它是怎么做的呢？下面我们就来分析下dubbo源码

 对于下面一个简单RPC调用，默认情况下sayhello方法，是个同步方法，也即是说，执行
 service。sayhello的时候，线程会停下来。
 如果你将dump出来的话，会是下图这个样子，你会发现调用线程阻塞了，线程状态
 是timed waiting .本来发送请求时异步的，但是调用线程阻塞了，说明dubbo帮我们
 做了异步转同步的事情。通过调用栈，你能看到线程是阻塞在defualtFuture.get方法上，所以
 我们可以推断：dubbo异步转同步的功能应该是通过defaultFuture这个类实现的。

 不过为了理清前后关系，还是有必要分析下调用defualtFutureget之前发生了什么
 dubboInvolker的108行调用了DefaultFuture.get这一银行很关键，我们稍微修改了一下列在
 了下面。这一行先调用了request这个方法其实就是发送rpc请求，之后通过gety方法等待RPC返回结果。

 DefaultFuture这个类是很关键，我把相关的代码精简之后，列到了下面。不过在看代码之前，
 你还是由必要重复一下我们的需求：当RPC返回结果之前，阻塞调用线程，让调用线程等待；
 当RPC返回结果后，唤醒调用线程，让调用线程重新执行。不知道你有没有似曾相识的感觉，
 这不就是经典的等待-通知机制吗，这个时候想必你的脑海里应该能够浮现出管程的解决方法了。
 有了自己的方案之后，我们再来看看dubbo是怎么实现的。

 调用线程通过调用get方法等待RPC返回结果，这个方法里面，你看到的都是熟悉的面孔
 ：调用lock获取锁，在finally里面调用unlock释放锁，获取锁后，通过经典的再循环
 中调用await方法来实现等待。

 当rpc结果返回时，会调用do Received 方法，这个方法里面，调用lock获取锁，在
 finally里面调用unlock释放锁，获取锁后通过调用signal来通知调用线程，结果已经返回
 ，不用继续等待了。

 至此，dubbo里面的异步转同步的源码就分析完了，有没有觉得还挺简单的？最近这几年，工作
 中需要异步处理的越来越多了，其中有一个主要原因就是有些API本事就是异步API 。例如
 websocket也是一个异步的通信协议，如果基于这个协议实现一个简单的rpc你也会遇到
 异步转同步的问题。现在很多公有云的API本身也是异步的，例如创建云主机，就是一个异步的
 API，调用虽然成功了，但是云主机并没有创建成功，你需要调用另外一个api去轮训云主机的状态
 。如果你需要在项目内部封装创建云主机的API，你也会面临异步转同步的问题，因为同步api更易用。

 总结

 lock condition 是管程的一种实现，所以能否用好lcok和condition 要看你对管程模型理解
 得怎么样。管程技术前面我们已经专门用一篇文章做了介绍，你可以结合着来学，理论联系实践

 lock confition实现的管程相对于synchronized实现的管程来说更加灵活、功能也更丰富。

 结果我自己的经验，我认为了解原理比了解实现更能让你快速学好并发编程，所以没有介绍太多
 javasdk并发包里锁和条件变量时如何实现的。但如果你对实现感兴趣，可以参考java并发
 编程的艺术艺术中的第五章 java中的锁。里面详细介绍了实现原理，我觉得写得非常好。


 * @param <T>
 */
public class BlockedQueue<T> {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    public List<T> list = new ArrayList<>();


    public boolean set(T t) {
        System.out.println(Thread.currentThread().getName() + "：set:" + t + "  开始");
        lock.lock();
        try {
            while (list.size() > 10) {
                System.out.println(Thread.currentThread().getName() + "：set:" + t + " 满了");
                notFull.await();
            }
            System.out.println(Thread.currentThread().getName() + "：set:" + t);
            list.add(t);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return true;
    }

    public T get() {
        System.out.println(Thread.currentThread().getName() + "：get:  开始");
        lock.lock();
        T t = null;
        try {
            while (list.size() < 1) {
                System.out.println(Thread.currentThread().getName() + "：get: 空了");
                notEmpty.await();
            }
            t = list.get(list.size() - 1);
            list.remove(t);
            System.out.println(Thread.currentThread().getName() + "：get:" + t+":剩余:"+list);
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return t;
    }


    public static void main(String[] args)throws Exception {
        BlockedQueue<String> queue = new BlockedQueue();
        for (int i = 0; i < 20; i++) {
            String t = i + "";
            new Thread(() -> queue.set(t)).start();
        }
        for (int i = 0; i < 20; i++) {
            new Thread(() -> queue.get()).start();
        }
        System.out.println("结束"+queue.list);
        Thread.sleep(111);
        System.out.println("结束"+queue.list);
       // new CountDownLatch(1).await();
    }

}

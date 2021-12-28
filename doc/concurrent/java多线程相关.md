 

# I.线程基础

**II.通用线程状态**
1.初始状态:编程语言的状态，操作系统还不知道
2.可运行状态:可以分配CPU执行
3.运行状态
4.休眠状态
5.终止状态

**II.java线程状态 Thread.State内部类**
一个线程在给定的时间点只能处于一种状态。 这些状态是虚拟机状态，**不反映任何操作系统线程状态。**
![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/Thread状态图.jpg)


1.新建new:创建后尚未启动的线程处于这种状态。 **(尚未启动的线程处于此状态)**
2.运行runnable:runnable包括了操作系统线程状态中的running和ready，也就是处于此状态的线程有可能正在执行，也有可能正在等待着CPU为它分配执行时间。**(Java 虚拟机中执行的线程处于此状态)**
3.阻塞blocked :synchronized隐式锁转换为blocked **(一个线程被阻塞等待监视器锁处于这种状态)**
4.无限期等待waiting：不会被分配CPU执行时间等待被其他线程显示的唤醒。Object.wait、Thread.join、LockSupport.park
5.限期等待timed_waiting ：不会被分配CPU执行，无须等待被其他线程显示地唤醒，自动唤醒。Thread.sleep(long)、Object.wait(long)、Thread.join(long)、LockSupport.parkNanos()、LockSupport.parkUntil()
6.结束terminated :已终止线程的线程状态，线程已经结束执行。**(已退出的线程处于此状态)**

**阻塞和等待区别**
只有synchronized会导致线程进入Blocked状态，
Waiting状态只能进入Blocked状态，获取锁之后才能恢复执行。

**Interrupte中断** 可以中断本线程，也可以中断其他线程
用法1:threadObj.interrupt()+InterruptedException
用法2:threadObj.interrupt()+   while(!Thread.currentThread().isInterrupted())

触发InterrupteException的方式
1.Object.wait()
2.ThreadO.join()、sleep()
3.当interrupte的线程时被synchronized阻塞住，interrupte并不能触发异常。

相关方法:
obj.interrupted(); 中断obj线程
obj.isInterrupted();判断是否中断
Thread.interrupted(); 静态方法，清除当前线程中断标志



**一、Object.wait、 notify** 必须和synchronized一起使用:wait(long), wait(long, int)
没有同步方法单独用抛异常:IllegalMonitorStateException
II.wait()方法:    调用线程block、将线程放入本对象的等待队列wait set
1.执行必须持有当前对象的锁
2.执行wait会释放synchronized的锁。 并将当前线程放入到本对象的waitset等待队列。
3.等待notify、超时、Interrupte方法：会唤醒当前对象，随意唤醒等待队列中的一个。

III.wait虚假唤醒:
一个wait线程可以在没有被 notify、 超时、interrupte时唤醒。
所以建议永远在循环里wait
synchronized (obj) {
        while (condition)
            obj.wait(timeout);
     }
java.lang.Object.wait(long) 源码注释写的

II.notify(), notifyAll()方法：
1.执行必须持有当前对象的锁
2.

方法内锁本对象:
synchronized(this){
this.wait();
}

同步方法内:
public synchronized void  waitTest() throws InterruptedException {
this.wait();
}


**I.创建线程的三个方法:**
Thread、
Runnable.run、 
Callable.call 有 返异常和结果 。

Thread.start启动线程。
Thread继承了 Runnable接口，  Callable接口能抛异常有返回值的线程


官方说法两种:thread代码注释里
继承 Thread
实现 Runable 接口

Callable.call返异常和结果

准确地讲，创建线程只有一种方式，那就是构建 Thread 类，而实现线程的执行单元有两种方式:
实现 Runnable 接口的 run 方法，并把 Runnable 实例传给 Thread 类
继承 Thread 类并重写 run 方法

start 开启多线程 执行run方法
https://zhuanlan.zhihu.com/p/363283919

**I.Callable.call接口方法**
1.实现Callable.call方法 或 带返回参数的 Runnable, T result
2.submit线程:ExecutorService.submit(Callable<T> task)
3.Future.get获得阻塞返回值(可设置等待时间)+ Future.isDone(实时判断是否执行完成-不阻塞)











# I.Future 获得异步任务结果接口
https://blog.csdn.net/u014209205/article/details/80598209


![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/future接口.jpg)


** Future接口 5个接口**

尝试取消执行此任务。  
boolean cancel(boolean mayInterruptIfRunning)

等待计算完成，然后检索其结果。  
V get()

如果需要等待最多在给定的时间计算完成，然后检索其结果（如果可用）。  
V get(long timeout, TimeUnit unit)

如果此任务在正常完成之前被取消，则返回 true 。
boolean isCancelled()

返回 true如果任务已完成。
boolean isDone()



## **II.RunnableFuture接口** 继承Runnable和继承Future异步获得结果
这个接口同时继承Future接口和Runnable接口，在成功执行run（）方法后，可以通过Future访问执行结果。

异步获得Runnable结果
public interface RunnableFuture<V> extends Runnable, Future<V> {
  void run();
}

### **III.FutureTask类** 实现了RunnableFuture接口

 
java.util.concurrent.ScheduledThreadPoolExecutor.ScheduledFutureTask类 继承FutureTask 。是内部类


## **II.ScheduledFuture 接口** 继承 Delayed 接口, Future接口  最终实现都是内部类
        这个接口表示一个延时的行为可以被取消。通常一个安排好的future是定时任务SchedualedExecutorService的结果
III.RunnableScheduledFuture
RunnableScheduledFuture
java.util.concurrent.ScheduledThreadPoolExecutor.ScheduledFutureTask类 继承FutureTask 。是内部类



## **II.CompletableFuture 类老版本功能**    继承 CompletionStage 接口和Future接口

CompletionStage 接口(完成阶段)
//TODO 有详细解释


## **II.ForkJoinTask 抽象类**  分治思想，切模块进行

RecursiveTask 抽象类继承 ForkJoinTask

ForkJoinTask需要通过ForkJoinPool来执行。

https://github.com/dingsai88/SpringBootStudy/blob/master/src/main/java/com/ding/study/concurrent/jkjuc/juc26ForkJoin/ForkJoinTest.java

        //创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        //0，1，1，2，3，5，8，13，21，34
        Fibonacci fib = new Fibonacci(9);
         //启动分治任务
        Integer result = fjp.invoke(fib);




# I.线程池

https://www.cnblogs.com/zeussbook/p/11895829.html

虽然在 Java 语言中创建线程看上去就像创建一个对象一样简单，只需要 new Thread() 就可以了，但实际上创建线程远不是创建一个对象那么简单。
创建对象，仅仅是在 JVM 的堆里分配一块内存而已；而创建一个线程，却需要调用操作系统内核的 API，然后操作系统要为线程分配一系列的资源，
这个成本就很高了，所以线程是一个重量级的对象，应该避免频繁创建和销毁。


## I.Executor 接口
执行提交的对象Runnable任务

该界面提供了一种将任务提交从每个任务的运行机制分解的方式，包括线程使用，调度等的Executor 。
通常使用Executor而不是显式创建线程。 

简单的标准化接口，用于定义线程类自定义子系统，包括线程池，异步I / O和轻量级任务框架。

在将来的某个时间执行给定的命令。
void execute(Runnable command)



### II.ExecutorService接口 继承 Executor 提供了一个更完整的异步任务执行框架。
提供方法可以管理生成、终止方法，可以产生Future为跟踪一个或多个异步任务执行。

ExecutorService可以关闭， 提供了两种不同的方法来关闭ExecutorService

shutdown()方法将允许先前提交的任务在终止之前执行，
shutdownNow()方法可以防止等待任务启动并尝试停止当前正在执行的任务。

应关闭未使用的ExecutorService以允许资源的回收。
submit延伸的基方法Executor.execute(Runnable)通过创建并返回一个Future可用于取消执行和/或等待完成。

方法invokeAny和invokeAll执行invokeAll执行最常用的形式，执行任务集合，然后等待至少一个或全部完成。

Executors类为此包中提供工厂方法。



#### 方法
awaitTermination(long timeout, TimeUnit unit)
阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准。
isShutdown()
如果此执行者已关闭，则返回 true 。
isTerminated()
如果所有任务在关闭后完成，则返回 true 。
shutdown()
启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
shutdownNow()
尝试停止所有主动执行的任务，停止等待任务的处理，并返回正在等待执行的任务列表。
submit(Callable<T> task)
提交值返回任务以执行，并返回代表任务待处理结果的Future。
submit(Runnable task)
提交一个可运行的任务执行，并返回一个表示该任务的未来。
submit(Runnable task, T result)
提交一个可运行的任务执行，并返回一个表示该任务的未来。


### III.AbstractExecutorService抽象类 实现 ExecutorService接口








#### IIII.ThreadPoolExceutor 类  继承AbstractExecutor抽象类   **重要**

整体模式:生产者 - 消费者模式
**生产者:线程池的使用方**
**消费者:线程池本身是消费者**

 

线程池解决两个不同问题:
1.提升性能:由于减少了每个任务调用的开销，它们通常可以在执行大量异步任务时提供增强的性能，提供绑定和管理资源的方法
2.统计信息:每个ThreadPoolExecutor保持一些基本的统计信息，例如完成的任务数量。

ExecutorService.execute无返回值
ExecutorService.submit有返回值


workQueue.offer



1 	corePoolSize 	int 	核心线程池大小
2 	maximumPoolSize 	int 	最大线程池大小
3 	keepAliveTime 	long 	线程最大空闲时间
4 	unit 	TimeUnit 	时间单位
5 	workQueue 	BlockingQueue<Runnable> 	线程等待队列
6 	threadFactory 	ThreadFactory 	线程创建工厂
7 	handler 	RejectedExecutionHandler 	拒绝策略


**核心线程数和最大线程数** corePoolSize、 maximumPoolSize
当新任务在方法 execute 中提交时，如果运行的线程少于 corePoolSize，则创建新线程来处理请求，即使其他辅助线程是空闲的。
如果运行的线程多于 corePoolSize 而少于 maximumPoolSize，则仅当队列满时才创建新线程。
设置的 corePoolSize 和 maximumPoolSize 相同，则创建了固定大小的线程池。
如果将 maximumPoolSize 设置为基本的无界值（如 Integer.MAX_VALUE），则允许池适应任意数量的并发任务
在大多数情况下，核心和最大池大小仅基于构造来设置，不过也可以使用 setCorePoolSize(int) 和 setMaximumPoolSize(int) 进行动态更改。

**按需构造核心线程数，预先启动** 预先启动核心线程
核心线程最初只是在新任务到达时才创建和启动的。
prestartCoreThread()  预先启动一条核心线程
prestartAllCoreThreads() 预先启动全部核心线程
对其进行动态重写。如果构造带有非空队列的池，则可能希望预先启动线程。


**创建新线程** ThreadFactory线程创建工厂 threadFactory
使用 ThreadFactory接口 创建新线程。
默认Executors.defaultThreadFactory()创建线程 。DefaultThreadFactory类是工厂类Executors内部类。

可自定义ThreadFactory接口的实现

**保持活动时间** keepAliveTime unit

池中当前有多于 corePoolSize 的线程，则这些多出的线程在空闲时间超过 keepAliveTime 时将会终止。


**队列** workQueue、 BlockingQueue<Runnable> 	线程等待队列

所有 BlockingQueue 都可用于传输和保持提交的任务。可以使用此队列与池大小进行交互：

1.队列和线程池大小**关系**:
新增核心:线程少于 corePoolSize，则 Executor 始终首选添加新的线程，而不进行排队。
多余核心放入队列：线程等于或多于 corePoolSize，则 Executor 始终首选将请求加入队列，而不添加新的线程。
超过max拒绝策略:如果无法将请求加入队列，则创建新的线程，除非创建此线程超出 maximumPoolSize，在这种情况下，任务将被拒绝。

2.官方三种通用策略:
SynchronousQueue:直接提交，它将任务直接提交给线程而不保持它们。
LinkedBlockingQueue:无界队列，不设置大小的link
ArrayBlockingQueue:有界队列

3.自己总结:
ArrayBlockingQueue(数组阻塞队列):有边界的阻塞队列，数组。初始化的时候指定它的容量大小，容量大小一旦指定就不可改变。
LinkedBlockingQueue(链表阻塞队列,不设置默认最大):阻塞队列可选大小的，初始化时指定一个大小，它就是有边界的，如果不指定，它就是无边界的。说是无边界Integer.MAX_VALUE的容量 。
DelayQueue(延迟多久+排序):阻塞的是其内部元素,元素必须实现 java.util.concurrent.Delayed接口，该接口只有一个方法就是long getDelay(TimeUnit unit)，返回值就是队列元素被释放前的保持时间，如果返回0或者一个负值，就意味着该元素已经到期需要被释放，此时DelayedQueue会通过其take()方法释放此对象，DelayQueue可应用于定时关闭连接、缓存对象，超时处理等各种场景；
PriorityBlockingQueue(排序的队列)：是一个没有边界的队列，它的排序规则和 java.util.PriorityQueue一样。需要注意，PriorityBlockingQueue中允许插入null对象。所有插入PriorityBlockingQueue的对象必须实现 java.lang.Comparable接口，队列优先级的排序规则就是按照我们对这个接口的实现来定义的。
SynchronousQueue(没有容量直接new新线程Executors.newCachedThreadPool)：队列内部仅允许容纳一个元素。当一个线程插入一个元素后会被阻塞，除非这个元素被另一个线程消费。


4. getQueue() 允许出于监控和调试目的而访问工作队列。
remove(java.lang.Runnable) 和 purge() 这两种方法可用于在取消大量已排队任务时帮助进行存储回收。

5.核心线程回收:
allowCoreThreadTimeOut为true
线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭


**拒绝策略** handler 	RejectedExecutionHandler 	拒绝策略
抛出 RejectedExecutionHandler

1.在默认的 ThreadPoolExecutor.AbortPolicy 
中，处理程序遭到拒绝将抛出运行时 RejectedExecutionException。

2.在 ThreadPoolExecutor.CallerRunsPolicy 
中，线程调用运行该任务的 execute 本身。此策略提供简单的反馈控制机制，能够减缓新任务的提交速度。

3.在 ThreadPoolExecutor.DiscardPolicy 
中，不能执行的任务将被删除。(放弃本次任务)

4.在 ThreadPoolExecutor.DiscardOldestPolicy 中，
位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）。放弃队列中最早的任务，本任务取而代之
 

    

**扩展钩子 (hook)**
https://www.cnblogs.com/iuyy/p/13622154.html
beforeExecute每次执行线程前执行
afterExecute 每次执行线程后执行
terminated只有shutdown线程池才执行:Executor 完全终止后需要完成的所有特殊处理


重新初始化 ThreadLocal、搜集统计信息或添加日志条目。

如果钩子 (hook) 或回调方法抛出异常，则内部辅助线程将依次失败并突然终止。



**Worker内部类** 创建、运行、清理线程的方法 。继承AQS 带有锁功能
ThreadPoolExecutor内部类Worker

ThreadPoolExecutor内部有一个worker 集合
private final HashSet<Worker> workers = new HashSet<Worker>();

ThreadPoolExecutor.addWorker方法 **新增一个线程**:  <coreSize
1）CAS操作来将线程数加1；
2）新建一个线程并启用。


ThreadPoolExecutor.runworker方法  **线程继续运行线程**  消耗队列里的线程
当前任务不null或者队列返回不null线程就继续运行




### III.ScheduledExecutorService接口 继承 ExecutorService接口

可安排在给定的延迟后运行或定期执行的命令


所有的 schedule 方法都接受相对 延迟和周期作为参数，而不是绝对的时间或日期。

schedule(task, date.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS)

网络时间同步协议、时钟漂移或其他因素的存在，因此相对延迟的期满日期不必与启用任务的当前 Date 相符。


## I.CompletionService 接口 无父接口父类 维护完成队列: 管理新线程 和 管理已完成任务 

将生产新的异步任务与使用已完成任务的结果分离开来的服务。
CompletionService当有多个任务时，可以返回一个处理一个。
ExecutorService 只能future特定的线程。

**生产者: submit 执行的任务**

 **使用者: take (阻塞等待)** 完成队列，获取后移除
 已完成的任务，并按照完成这些任务的顺序处理它们的结果。
 获取并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。


**使用者: poll() 没有返回null** 完成队列，获取后移除
poll(long timeout, TimeUnit unit)



通常，CompletionService 依赖于一个单独的 Executor 来实际执行任务，在这种情况下，
CompletionService 只管理一个内部完成队列。
ExecutorCompletionService 类提供了此方法的一个实现。 



### II.ExecutorCompletionService 类

    private final Executor executor;
    private final AbstractExecutorService aes;
    private final BlockingQueue<Future<V>> completionQueue;

使用提供的 Executor 来执行任务的 CompletionService。此类将安排那些完成时提交的任务，把它们放置在可使用 take 访问的队列上。该类非常轻便，适合于在执行几组任务时临时使用。 

多维护一个完成队列，当任务完成时。
completionQueue.add(task)。加入到完成队列中。








# I.Executors 工厂类
Executors  
newCachedThreadPool() 可灵活回收空闲线程，若无可回收，则新建线程 1、无限、  0
newFixedThreadPool 无界的工作队列 固定的 可控制线程最大并发数，超出的线程会在队列中等待  x、x、 无界
newSingleThreadExecutor()  单独的 指定顺序(FIFO, LIFO, 优先级) 1、1、 无界
newScheduledThreadPool 支持定时及周期性任务执行 定时延迟三秒执行
newWorkStealingPool(int parallelism)，并行地处理任务，不保证处理顺序






# * I.java.util.lock.*

# *I.AOS使用 AbstractOwnableSynchronizer  独占方式拥有同步器
让线程已独占的方式拥有同步器

子类和工具可以使用适当维护的值帮助控制和监视访问以及提供诊断。

II.getExclusiveOwnerThread():返回由 setExclusiveOwnerThread 最后设置的线程；如果从未设置，则返回 null。

II.setExclusiveOwnerThread(Thread t) 设置当前拥有独占访问的线程。


#I.AQS AbstractQueuedSynchronizer 类  抽象队列同步器 说明
----------------------------------------------------AQS方法开始------------------------------------------------------------

## II.Node 等待队列节点类.**
Wait queue node class.

## II.private transient volatile Node head;等待队列头节点**
transient 不会序列化
Head of the wait queue, lazily initialized.

## II.private transient volatile Node tail; 等待队列尾节点**
Tail of the wait queue, lazily initialized.

## II.private volatile int state; 同步器状态**
getState、setState、compareAndSetState


## II.Queuing utilities 队列公用
static final long spinForTimeoutThreshold = 1000L; 超时时间 100纳秒(小于这个值就不等待阻塞park,直接cas)

III.enq放入队尾、或初始化;
III.addWaiter 设置当前线程模式,放入队尾、或初始化:Node.SHARED共享、Node.EXCLUSIVE独占
III.setHead 设置传入节点为头节点，并且弹出队列
III.unparkSuccessor 唤醒后继节点

III.doReleaseShared 释放共享
III.setHeadAndPropagate 设置头并传播

## II. Utilities for various versions of acquire  公共acquire获取方法(各种版本)


III.cancelAcquire   取消尝试获取
III.shouldParkAfterFailedAcquire  检查和更新未能accquire获取的节点的状态
III.selfInterrupt 中断当前线程
III.parkAndCheckInterrupt()  当前线程无限等待并且检查中断


##　II.Various flavors of acquire, varying in exclusive/shared and control modes.  各种acquire获取，不同模式exclusive\shared独占共享


III.acquireQueued　独占方式在队列中获取
III.doAcquireInterruptibly 独占方式获取，中断则中止
III.doAcquireNanos     独占时间模式获取
III.doAcquireShared  共享模式获取
III.doAcquireSharedInterruptibly 共享模式方式获取，中断则中止
III.doAcquireSharedNanos 共享时间模式获取


## II.Main exported methods 主要输出方法 (外部调用)
tryAcquire 试图在独占模式下获取对象状态。
tryRelease 释放独占模式下对象状态

tryAcquireShared  试图在共享模式下获取对象状态
tryReleaseShared  释放共享模式下对象状态

isHeldExclusively 如果对于当前（正调用的）线程，同步是以独占方式进行的，则返回 true。

acquire 以独占模式获取对象，忽略中断。
acquireInterruptibly 以独占模式获取对象，如果被中断则中止。
tryAcquireNanos   试图以独占模式获取对象，如果被中断则中止，如果到了给定超时时间，则会失败。
release  以独占模式释放对象

acquireShared 以共享模式获取对象，忽略中断。
acquireSharedInterruptibly  以共享模式获取对象，如果被中断则中止。
tryAcquireSharedNanos 试图以共享模式获取对象，如果被中断则中止，带超时。
releaseShared  以共享模式释放对象


## II。Queue inspection methods 队列检查方法

hasQueuedThreads 查看队列中是否有线程
hasContended   是否有竞争 head!=null
getFirstQueuedThread 返回队列第一个线程
fullGetFirstQueuedThread  返回队列第一个线程 实现方法
isQueued(Thread thread)   线程在队列中 返回 true
apparentlyFirstQueuedIsExclusive   独占模式，队列下一个值不为空返回true  ReentrantReadWriteLock用
hasQueuedPredecessors   队列下一个值不等于空返回true


## II.Instrumentation and monitoring methods  仪表盘和监视方法
getQueueLength  返回等待的队列数量  非同步，不准确。
getQueuedThreads 返回等待队列的线程集合
getExclusiveQueuedThreads  返回等待队列里的  独占线程集合
getSharedQueuedThreads     返回等待队列里的  共享线程集合


##  II.Internal support methods for Conditions 条件的 内部支持方法
isOnSyncQueue(Node node) 判断节点是否在同步队列中 https://blog.csdn.net/weixin_38106322/article/details/107192310
findNodeFromTail(Node node) 从队尾找本节点，找到返回true
transferForSignal(Node node)  将节点从条件队列转入到同步队列
transferAfterCancelledWait   转移后取消等待
fullyRelease 释放节点


##   II. Instrumentation methods for conditions 条件的 仪表盘方法
owns(ConditionObject condition) 查询给定的 ConditionObject 是否使用了此同步器作为其锁
hasWaiters(ConditionObject condition) 查询是否有线程正在等待给定的、与此同步器相关的条件。
getWaitQueueLength  返回正在等待与此同步器有关的给定条件的线程数估计值。
getWaitingThreads  返回一个 集合 包含 正在等待与此同步器有关的给定条件的那些线程。

## II.ConditionObject条件对象节点

##  本地不安全的CAS相关

compareAndSetHead 设置头 cas 方式，仅enq方法用
compareAndSetTail 设置尾 cas 方式，仅enq方法用
compareAndSetWaitStatus 设置WaitStatus
compareAndSetNext 


I.Node节点
volatile int waitStatus;  0 初始、SIGNAL -1 、CONDITION -2、 PROPAGATE -3、CANCELLED 1
volatile Node prev; 前节点
volatile Node next; 
volatile Thread thread;

Node nextWaiter;  TODO 不知道干什么用的condition连接到下一节点、




I.Condition接口  ： 相当于Ojbect的wait 、notify、synchronized
Condition 将 Object 监视器方法（wait、notify 和 notifyAll）分解成截然不同的对象，
以便通过将这些对象与任意 Lock 实现组合使用，为每个对象提供多个等待 set（wait-set）。

其中，Lock 替代了 synchronized 方法和语句的使用，
Condition 替代了 Object 监视器方法的使用。

Object.wait  ==Condition.await() 、awaitNanos、awaitUninterruptibly()一直等
Object.notify(notifyAll)==Condition.signal() 、signalAll()
synchronized(Obj)===Condition.Lock();


I.ConditionObject 节点类
await() :实现不可中断的条件等待。
await(long time, TimeUnit unit)实现定时的条件等待。
awaitNanos(long nanosTimeout)实现定时的条件等待。
awaitUninterruptibly()实现不可中断的条件等待。
awaitUntil(Date deadline)实现绝对定时条件等待。
getWaitingThreads()返回包含那些可能正在等待此条件的线程 collection。
getWaitQueueLength()返回正在等待此条件的线程数估计值。
hasWaiters()查询是否有正在等待此条件的任何线程。
signal()将等待时间最长的线程（如果存在）从此条件的等待队列中移动到拥有锁的等待队列。
signal()将等待时间最长的线程（如果存在）从此条件的等待队列中移动到拥有锁的等待队列。


----------------------------------------------------AQS方法结束------------------------------------------------------------


# *I.AQS** AbstractQueuedSynchronizer 类  抽象队列同步器

独占锁(ReentrantLock)：即其他线程只有在占有锁的线程释放后才能竞争锁，有且只有一个线程能竞争成功（医生只有一个，一次只能看一个病人）

共享锁(ReadWriteLock, CountdownLatch)：即共享资源可以被多个线程同时占有，直到共享资源被占用完毕（多个医生，可以看多个病人），常见的有读写锁 ReadWriteLock, CountdownLatch


管程组成:(互斥锁mutex) 、(状态condition一个或多个条件变量)、(共享变量及方法)
I.volatile int state（代表共享资源） CAS
I.FIFO线程等待队列（多线程争用资源被阻塞时会进入此队列）先进先出


**II. AbstractQueuedSynchronizer包含：**
:（Node类与ConditionObject类） + 属性 (头结点head，尾结点tail，状态state、自旋时间spinForTimeoutThreshold)

https://www.cnblogs.com/leesf456/p/5350186.html

**III.核心属性**
头结点head，尾结点tail，状态state、自旋时间spinForTimeoutThreshold
volatile int state;

**III.内部类1:ConditionObject 实现 Condition接口:**
主要作用：await（）等待、signal或singalAl唤醒

**III.内部类2:Node类**
主要作用：各个节点的数据

**III.核心方法**
AbstractQueuedSynchronizer模版方法: 独占锁(一个条件变量)  + 共享锁(多个条件变量)

状态判断：getState()、setState()、compareAndSetState()
isHeldExclusively()：该线程是否正在独占资源。只有用到condition才需要去实现它。

tryAcquire(int)：独占方式。尝试获取资源，成功则返回true，失败则返回false。
tryRelease(int)：独占方式。尝试释放资源，成功则返回true，失败则返回false。

tryAcquireShared(int)：共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
tryReleaseShared(int)：共享方式。尝试释放资源，成功则返回true，失败则返回false。



**ReentrantLock 是独占锁**

ReentrantLock.Sync(公平不公平的公共类) extends AbstractQueuedSynchronizer
ReentrantLock.NonfairSync extends Sync(非公平锁)
ReentrantLock.FairSync extends Sync(公平锁)


**公平锁与非公平锁**
非公平锁（NonfairSync）
CAS 来获取 state 资源  setExclusiveOwnerThread(Thread.currentThread());



 











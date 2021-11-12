

ThreadPoolExecutor



1 	corePoolSize 	int 	核心线程池大小
2 	maximumPoolSize 	int 	最大线程池大小
3 	keepAliveTime 	long 	线程最大空闲时间
4 	unit 	TimeUnit 	时间单位
5 	workQueue 	BlockingQueue<Runnable> 	线程等待队列
6 	threadFactory 	ThreadFactory 	线程创建工厂
7 	handler 	RejectedExecutionHandler 	拒绝策略


拒绝策略
    AbortPolicy：让调用者抛出 RejectedExecutionException 异常，这是默认策略
    CallerRunsPolicy：让调用者运行任务
    DiscardPolicy：放弃本次任务
    DiscardOldestPolicy：放弃队列中最早的任务，本任务取而代之




字节填充 提高性能  排除cpu缓存行


**I.workQueue**
ArrayBlockingQueue(数组阻塞队列):有边界的阻塞队列，数组。初始化的时候指定它的容量大小，容量大小一旦指定就不可改变。
LinkedBlockingQueue(链表阻塞队列,不设置默认最大):阻塞队列可选大小的，初始化时指定一个大小，它就是有边界的，如果不指定，它就是无边界的。说是无边界Integer.MAX_VALUE的容量 。
DelayQueue(延迟多久+排序):阻塞的是其内部元素,元素必须实现 java.util.concurrent.Delayed接口，该接口只有一个方法就是long getDelay(TimeUnit unit)，返回值就是队列元素被释放前的保持时间，如果返回0或者一个负值，就意味着该元素已经到期需要被释放，此时DelayedQueue会通过其take()方法释放此对象，DelayQueue可应用于定时关闭连接、缓存对象，超时处理等各种场景；
PriorityBlockingQueue(排序的队列)：是一个没有边界的队列，它的排序规则和 java.util.PriorityQueue一样。需要注意，PriorityBlockingQueue中允许插入null对象。所有插入PriorityBlockingQueue的对象必须实现 java.lang.Comparable接口，队列优先级的排序规则就是按照我们对这个接口的实现来定义的。
SynchronousQueue(没有容量直接new新线程Executors.newCachedThreadPool)：队列内部仅允许容纳一个元素。当一个线程插入一个元素后会被阻塞，除非这个元素被另一个线程消费。

当线程池中的线程数，超过核心线程数时。
此时如果任务量下降，肯定会出现有一些线程处于无任务执行的空闲状态。
那么如果这个线程的空闲时间超过了 keepAliveTime&unit 配置的时长后，就会被回收。


当线程处于无任务执行的空闲状态，超过了设置时间就会被回收。


创建线程的三个方法:Thread、Runnable.run、 Callable.call
https://zhuanlan.zhihu.com/p/363283919



I.获得线程返回内容(Callable.call  和 返回参数Runnable,T result)：
I.获得线程返回内容（重新Callable.call  +  获得结果Future.get）
1.实现Callable.call方法 或 带返回参数的 (Runnable, T result)
2.submit线程:ExecutorService.submit(Callable<T> task)
3.Future.get获得阻塞返回值(可设置等待时间)+ Future.isDone(实时判断是否执行完成-不阻塞)



I.Executor.execute(Runnable)(Executors)、ExecutorService（单个.拿返回值）、CompletionService（多个任务拿返回值ExecutorCompletionService）

II.Executor.execute(Runnable);只有一个方法

II.ExecutorService 带返回值的
ExecutorService.submit(Callable<T> task)：是Executor子接口，新增了submit支持获得返回值
ExecutorService.submit(Runnable task, T result); 变相拿返回值
ExecutorService.shutdown();平滑的关闭线程池

II.CompletionService 升级版本可提交多个任务(ExecutorService的升级版)
CompletionService当有多个任务时，可以返回一个处理一个。
ExecutorService只能限定顺序



II.Executors
Executors.newSingleThreadExecutor();
Executors.newCachedThreadPool();
Executors.newFixedThreadPool(1);
Executors.newScheduledThreadPool(2);
Executors.newWorkStealingPool();





**I.AQS**

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








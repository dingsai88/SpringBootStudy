
网络模式
1.阻塞 I/O（blocking IO）BIO
2.非阻塞 I/O（nonblocking IO）NIO
3.I/O 多路复用（ IO multiplexing）非阻塞
   3.1.select只支持1024个(每次都遍历)
   3.2.poll和select没有区别于链表来存储的.没有个数限制(每次都遍历)：
   3.3.epoll（注册—监听事件—处理—再注册） epoll只有触发监听才访问 ：（等待队列--有值时--就绪队列---调用方法）
4.信号驱动 I/O（ signal driven IO）
5.异步 I/O（asynchronous IO）
epoll_create, epoll_ctl和epoll_wait。

   Java 1.4引NIO框架（java.nio 包）
提供了Channel(socket)、Selector(单线程管理多个)、Buffer（容器）可以构建多路复用的、
同步非阻塞 IO 程序。

   Java 7NIO有改进NIO2异步非阻塞AIO（Asynchronous IO）
异步 IO 操作基于事件和回调机制；操作直接返回，不阻塞。后台处理完成会通知

java文件拷贝
FileInputStream
Files.copy
NIO transferTo/From 的方式可能更快

**零拷贝**
(磁盘>内核态空间>用户空间>内核空间>网卡  )
(磁盘>内核态>网卡) 使用 DMA硬件控制
 


CPU密集型计算线程数:理论上=CPU核数。实际上会设置CPU核数+1
IO密集:1+(IO耗时/CPU耗时)

I.kernel内核
II.最终线程调用
strace -ff -o out /usr/bin/java SocetBIO

----------------------------------------------------------------------------------------------------------------------
 I.线程状态
通用线程状态:
1.初始状态:编程语言的状态，操作系统还不知道
2.可运行状态:可以分配CPU执行
3.运行状态
4.休眠状态
5.终止状态

java线程状态:
1.新建new:创建后尚未启动的线程处于这种状态 重写Thread.run或者实现Runnable接口:Start
2.运行runnable:runnable包括了操作系统线程状态中的running和ready，也就是处于此状态的线程有可能正在执行，也有可能正在等待着CPU为它分配执行时间。
3.无限期等待waiting：不会被分配CPU执行时间等待被其他线程显示的唤醒。Object.wait、Thread.join、LockSupport.park
4.限期等待timed waiting ：不会被分配CPU执行，无须等待被其他线程显示地唤醒，自动唤醒。Thread.sleep(long)、Object.wait(long)、Thread.join(long)、LockSupport.parkNanos()、LockSupport.parkUntil()
5.阻塞blocked 线程被阻塞了，阻塞状态与等待状态的区别是:阻塞状态运行状态runnable遇到synchronized隐式锁转换为blocked
6.结束terminated :已终止线程的线程状态，线程已经结束执行。



II.线程安全方案:
同步互斥（多个线程访问共享数据时同一时刻只有一个线程可以访问共享数据） synchronized ReentrantLock可重入锁（jdk6以后性能一样） 读写锁ReentrantReadWriteLock

非阻塞同步:CAS(CompareAndSwap)

无同步方案:线程本地存储（一个数据被一个线程独享）、copyonwirt、final不变




-------------------------------------------------高并发开始-----------------------------------------------------------


1.进程：每个控制流都是一个进程，内核维护空间独享，互相通信需要IPC机制

2.线程：线程是运行在单一进程上下文逻辑流，内核调度。前两种混合体(进程样内核调度，IO共享地址)


3.IO多路复用：应用程序在一个进程的上下文中显示调用逻辑流.共享空间 (减少了内存开销和上下文切换的CPU开销)
1.select只支持1024个(每次都遍历)
2.poll和select没有区别于链表来存储的.没有个数限制(每次都遍历)：
3.epoll（注册—监听事件—处理—再注册） 对比：select和poll每次都全部轮训；epoll只有触发监听才访问


----------------------------------------------------------------------------------------------------



I.信号量semaphore：信号量s是非负整数的全局变量。只能由两个操作来处理

I.管程(Monitor):描述:比信号量更高的抽象，没有这种实体存在于系统或编程语言中(一种机制、一种解决方法)。编程语言和操作系统都提供了实现管程的重要部件(条件变量)
管程组成:互斥锁mutex 、状态condition一个或多个条件变量、共享变量及方法

II.管程模型：
1.Hansan管程:要求notify放在代码最后，这样T2通知完T1后，T2结束，然后T1再执行。
2.Hoare管程:T2通知T1后，T2阻塞，T1马上执行；T1执行完，再唤醒T2。相比Hasen模型多一次阻塞唤醒
3.MESA管程 (java内置管程:synchronized):T2通知T1后，T2继续执行，T1不立刻执行，仅从条件变量等待队列进到入库等待队列。
优点:notify不用放在最后，T2没有多余的阻塞唤醒操作。
缺点:T1再次执行时，可能曾满足的条件，现在不满足，所以要循环方式检查条件变量。




II.Java内存模型JMM:保证解决三种特性的问题(JMM来屏蔽各种硬件和操作系统的内存访问差异)
                     volatile易变（特性）禁用缓存(CPU缓存)、禁止优化(指令排序)
                     final告诉编译器：生儿不变，使劲优化。

JMM:
可见性和有序性:(禁用CPU缓存和编译优化) volatile、synchronized、final、8项happens-before规则（start、join）
原子性:（互斥锁）synchronized
  
  
  

1.原子性、可见性、有序性：并发bug的源头

可见性：一个线程对共享变量的修改,另外一个线程能够立刻看到。(CPU高速缓存和内存)  synchronized(monitorenter、monitorexit)、volatile、final
原子性：把一个或者多个操作在CPU执行的过程中不被终端的特性。(线程切换) synchronized
有序性:编译优化带来的有序性  synchronized、volatile

先行发生原则(happens before):
程序次序规则program order rule写在前面的先执行
管程锁定规则monitor lock rule(监视器锁规则) 
volatile变量规则volatile variable rule：对一个volatile变量的写操作先行发生于后面对这个变量的读操作，这里的后面同样是指时间上的先后顺序。
线程启动规则thread start rule：thread对象的start方法先行发生于此线程的每一个动作。
线程终止规则 thread termination rule线程中的所有操作都先行发生于对此线程的终止检测，我们可以通过thread。join方法结束、thread.isalive的返回值等后端检测到线程已经终止执行。



2.锁相关
发生死锁的四个条件:破坏一个就行
1.互斥,共享资源X和Y只能被一个线程占用
2.占有且等待，线程T1已经取得共享资源X，在等待共享资源Y的时候，不释放共享资源X
3.不可抢占，其他线程不能强行抢占线程T1占有的资源。
4.循环等待，线程T1等待线程T2占有的资源，线程T2等待T1占有的资源。


避免死锁的方法
1.破坏占用且等待条件-一次性申请所有资源
2.破坏不可抢占条件--主动释放自己占有的资源；synchronized 是做不到;Lock可以解决；占用资源的线程进一步申请资源时，如果申请不到，就主动释放自己占有的资源
3.破坏循环等待条件--对资源进行排序；资源有线性顺序，先申请资源号小的。


活锁：线程没有发生阻塞，但执行不下去（互相谦让）:解决:谦让时随机时间。
饥饿:无法访问所需资源而执行不下去。(CPU繁忙线程优先级低)。解决:




---------------------------------------------并发工具类-----------------------------------------------------------------------------



I.Lock和Condition模拟管程操作
管程组成:互斥锁mutex 、状态condition一个或多个条件变量、共享变量及方法
ReentrantLock
1.能够响应中断:释放已占用的资源、2.支持超时、3.非阻塞地获取锁:获取不到不阻塞，直接返回
4.公平性


I.Semaphore(赛卖for)信号量(操作系统PV)限流器并发池等
模拟信号量：计数器、等待队列、三个方法（初始值、加一、减一）


I.ReadWriteLock读写锁

I.StampedLock邮戳8新增的读写锁升级
三种锁模式
1.写锁writeLock
2.读锁readLock悲观
3.乐观读tryOptimisticRead:validate(验证是否修改)--无锁


I.CountDownLatch和CyclicBarrir（赛科雷 百瑞尔）:如何让多线程步调一致
CountDownLatch主要解决一个线程等待多个线程(不能循环利用)
CyclicBarrier主要解决一组线程之间互相等待(自动重置循环利用)


--------------------------------------------------------------------------------------------------------

I.无锁方案（最大的好处就是性能）:CAS
ABA问题：增加版本号
I.多线程的方法 Thread Runnable Callable(带返回值) 三种方式实现多线程。
start 开启多线程 执行run方法


I.Executor与线程池
corePoolSize ：核心线程数量
maximumPoolSize ：线程最大线程数
workQueue ：阻塞队列，存储等待执行的任务 很重要 会对线程池运行产生重大影响
keepAliveTime ：线程没有任务时最多保持多久时间终止
unit ：keepAliveTime的时间单位
threadFactory ：线程工厂，用来创建线程
rejectHandler ：当拒绝处理任务时的策略

Executors 一克在key特死
newCachedThreadPool() 可灵活回收空闲线程，若无可回收，则新建线程
newFixedThreadPool 无界的工作队列 固定的 可控制线程最大并发数，超出的线程会在队列中等待
newSingleThreadExecutor()  单独的 指定顺序(FIFO, LIFO, 优先级)
newScheduledThreadPoo 支持定时及周期性任务执行 定时延迟三秒执行
newWorkStealingPool(int parallelism)，并行地处理任务，不保证处理顺序

I.线程池放入顺序
1.new核心线程
2.放入队列
3.核心线程、队列都满了，创建新的线程到最大线程数。
4.最大线程和队列都满了：执行异常策略。

II.线程池执行顺序
1.核心线程
2.队列满了以后创建的最大线程。
3.队列的数据。








I.Future(飞偶车)接口和FutureTask实现类获得线程返回值
开启线程，执行内容 等待线程内容返回。
  Future future2 = executorService.submit(callableDemo);
  System.out.println(future2.get());

II.CompletableFuture（康姆铺莱特爆-飞偶车）:异步编程8才提供

串行关系、AND汇聚关系、OR汇聚关系

II.CompetionService(康姆铺莱特身)批量执行异步任务(Future+阻塞队列)
批量的future提交




I.避免共享
II. Immutability（不变）模式：利用不变性解决并发问题
final
享元模式 每个频道只创建一个


II. Copy-on-Write模式：不是延时策略的COW（通用的技术方案）
CopyOnWriteArrayList
CopyOnWriteArraySet
对读的性能要求很高、读多写少、弱一致性

II.线程本地存储模式:ThreadLocal每个线程一个对象

II.CAS无锁
CompareAndSwap
-最终hostsport 调用汇编指令 : lock cmpxchg指令  (多个CPU 先锁定、在cmpxchg)
-------------------------------------------------------------------------

Oject obj=new Object占用多少字节









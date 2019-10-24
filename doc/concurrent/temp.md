
 
 
 
 
 
 
 我们详细介绍了如何创建正确的线程池,那创建完线程池,我们该如何使用呢
 在上一篇文章中,我们仅仅介绍了ThreadPoolExecutor的void
 execute(Runnable command)方法,利用这个方法虽然可以提交热舞,但
 是却没有办法获得任务的执行结果execute方法没有返回值.而很多场景
 下,我们又都是需要获取任务的执行结果的.那threadPoolExecutor是否提
 供了相关功能呢?必须的,这么重要的功能当然需要提供了.
 下面我们就来介绍一下使用ThreadPoolExecutor的时候,如何获取任务执行
 结果.
 
 如何获取任务执行结果.
 Java通过ThreadPoolExecutor提供的三个submit方法和一个
 FutureTask工具类来支持获得任务执行结果的需求.下面我们先来介绍这三个
 submit方法,这三个方法的方法签名如下.
 Future submit
 Future submit callble task
 Future submit Runable task result
 你会发现他们的返回值都是Future接口,Future接口有5个方法,我都列在
 下面了,他们分别是需要任务的方法cancel,判断任务是否已取消的方法
 isCancelled 判断任务是否已结果的方法isDone以及2个获取任务执行
 结果的get 和get timeout unit 其中最后一个gettimeout unit 支持
 超时机制.通过future接口的这5个方法你会发现,我们提交的任务不但能够
 获取任务执行结果,还可以取消任务.不过需要注意的是:这两个get方法
 都是阻塞式的,如果被调用的时候,任务还没有执行完,那么调用get方法
 的线程会阻塞,知道任务执行完才会被唤醒.
 boolean cancel
 boolean isCancelled
 isDone
 get
 get timeout timeunit
 
 
 这三个submit方法之间的区别在于方法参数不同,下面我们简要介绍一下.
 
 1.提交runnable任务submit 这个方法的参数是一个
 runnbale接口,runnable接口的run方法是没有返回值的,所以
 submit这个方法返回的future仅可以用来断言任务已
 经结束了,类易于threadjoin
 
 2.提交callbale任务的submit 这个方法的参数是一个callable接口,他只有一个
 call方法,并且这个方法是有返回值的,所以这个方法返回的futrue对象可以通过调用其get
 方法来获取任务的执行结果
 
 3.提交runnable任务以及结果引用submit
 这个方法很有意思,假设这个方法返回的future对象是f
 f.get返回的值就是传给submit方法的参数result.这个方法该怎么用
 呢 下面这段示例代码展示了它的经典用法.需要你注意的是Runnable接口
 实现类tas声明了一个有参数构造函数task创建task
 对象的时候传入了result对象,这样就能在类task的run方法中对
 result进行各种操作了.result相当于主线程和子线程之间的桥梁.通过他
 珠子线程可以共享数据.
 
 下面我们来介绍futuretask工具类.前面我们提到的future是一个接口
 而futuretask是一个实实在在的工具类,这个工具类有两个构造函数,他们的
 参数和前面介绍的submit方法类似,所以这里我就不再赘述了.
 futuretask
 futuretask runnable
 那如何使用futuretask呢 其实很简单,futuretask实现了runnable和
 futrue接口,由于实现了runnable接口所以可以讲futuretask对象作为
 任务提交给threadpoolexecutor去执行,也可以直接呗thread执行,又因为
 实现了future接口,所以也能用来获得任务的执行结果.下面的实例代码是
 将futuretask对象提交给threadpoolexecutor去执行.
 
 FutureTask对象直接被thread执行的实例代码如下所示.相信你已经发现
 了,立勇futuretask对象可以很容易获取子线程的执行结果.
 
 实现最优的烧水泡茶程序
 记得以前初中语文课文里有一篇著名数学家华罗庚先生的文章统筹方法
 这篇文章里介绍了一个烧水泡茶的例子,稳重提到最优的工序应该是下面这样.
 
 下面我们用程序来模拟一下这个最优工序.我们专栏签名曾经提到,并发变成
 可以总结为三个核心问题:分工同步和互斥.编写并发程序,首先要做的就是
 分工,所以分工值得是如何高效地拆解任务并分配给线程.对于烧水泡茶这个
 程序,一种最优的分工方法可以是下图所示的这样:用两个线程T1 T2来
 完成烧水泡茶程序,T1负责洗水壶 烧开水 泡茶这三刀工序,T2负责洗茶胡
 洗茶杯 那茶叶三刀工序,其中T1在执行泡茶这道工序时需要等待T2完成
 拿茶叶的工序.对于T1的这个等待动作,你应该可以相处很多种办法,例如
 threadjoin countDownlatch 甚至苏塞队列都可以解决,不过今天我们
 用future特性来实现

首先我们创建了两个futuretask 
 
 
 总结
 利用java并发并提供的future可以很容易获得一部任务的执行结果,无论异步
 任务是通过线程池 threadpoolexecuor执行的,还是通过手工创建子线程
 来执行的.future可以类比为显示世界里的提货单,比如去蛋糕店订生日蛋糕
 ,蛋糕店都是先给你一张提货单,你拿到提货单以后,没有必要一直在店里
 等着,可以先去干点其他事,比如看电影
 
 立勇多线程可以快速将一些串行的任务并行化,从而提高性能;如果任务之间
 有依赖关系,比如当前任务依赖前一个任务的执行结果,这种问题基本上都可以
 用future来解决.在分析这种问题的过程中,建议你用有向图描述一下任务
 之间的依赖关系,同时将线程的分工也做好,类似于烧水泡茶最优分工方案那
 幅图.对照来写代码,好处是更形象,且不易出错.
 
 
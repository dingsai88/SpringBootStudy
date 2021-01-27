

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




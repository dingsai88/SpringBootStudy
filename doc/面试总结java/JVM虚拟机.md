I.JVM
II.运行时数据区
III.程序计数器ProgramCounterRegister
III.虚拟机栈VMStack
III.本地方法栈NativeMethodStack
III.java堆Heap存放数组和对象（所有线程共享）
III.方法区(虚拟机规范永久代是hotspot实现)

II.堆heap
1.新生代(Eden、From Survivor、To Survivor)8个EdenV1个Survivor，没被回收就到Survivor
2.老年代(多次GC未被回收的对象)
3.永久代（改为元空间存类方法、类名称、常量池信息）轻易不回收


II.方法区(虚拟机规范永久代是hotspot实现)  8之前在永久代，8之后在Metaspace原空间
1.已加载的类信息
2.运行时常量池 （String.intern） 8开始在堆
3.常量
4.静态变量
5.JIT即时编译器编译后的代码




II.对象的内存布局
III.对象头ObjectHead
1.MarkWord(标记字)64bit
1.1按照状态区分lock状态 无锁 偏向锁  轻量级锁 重量级锁定
1.2biased_lock:是否偏向锁只有lock状态是01时才有此字段（1bit）
1.3age:对象年龄4bit最大值是15
1.4thread:持有偏向锁的id
1.5ptr_to_lock_record:轻量级锁下指向栈记录的指针
1.6ptr_to_heavyweight_monitor:重量级锁下指向对象监视器monitor的指针

2.类型指针（指向方法区Class信息）64bit
3.记录数组长度（可选）64bit

III.实例数据InstanceData
III.对齐填充Padding



II.对象的创建

III.分配内存（非数组）
 1.Serial、ParNew等带Compact过程的收集器使用指针碰撞 内存规整使用指针碰撞(Bump the Pointer)
 2.CMS基于mark-sweep算法的收集器使用内存  内存不规整使用空闲列表(Free list)

III.防止内存分配时并发问题
1.分配内存的动作进行同步（采用CAS compare and swap）
2.按照线程划分为不同的空间


采集器 部署到磁盘空间大小

linux自带的
目录有多大，内容多大

每隔1分钟 发送的etl
采集组件
部署到每台机器

ETL集群感知机器分组的情况。
每隔MYSQL CPU 内存使用率


把mysql定义在组里。

发送道逻辑分组 

ELT 按照分组处理

负载均衡策略  d
定义分组的组件


发送端可感知 
接收端可感知

完整工程 可运行

java jar 

参数启动


-发送组件 采集的正确性 
-处理采集组件
-分组组件

发送请求同步
etcd 














I.类文件结构
魔数、常量池、类和接口访问标志、类父类接口索引集合、字段表集合、方法表集合、属性表





I.字节码指令
1.没有byte、char、short、boolean 转换成INT
2.对象创建和访问指令:new 数组 new array anewarray;访问getfield;putfield;getstatic;putstatic ;baload;bastore;arraylength;instanceof、checkcase
3.方法调用和返回指令
invokevirtual:最常用，调用对象的实例方法 invokeinterface:调用接口方法 invokespecial:特殊处理的实例方法,初始化方法、私有方法、父类方法
invokestatic:调用类静态方法   invokedynamic:动态解析出调用点限定符引用方法
4.同步指令 synchronized关键字需要管程（monitor）monitorenter、monitorexit支持；同步方法内部出现异常也会执行monitorexit释放同步


 




II.编译期优化（java>class）
1：词法分析、语法分析

2.注解处理器- 注解清除

3.语义分析与字节码生成
过程3.1：标注检查
过程3.2：数据流分析
过程3.3：解语法糖 泛型类型擦除、条件编译优化if(ture);while(false)拒绝编译、try定义和关闭资源






II.虚拟机类加载机制

1.类加载生命周期
加载loading：通过全名获得二进制字节流；Class特殊是对象，但存放在方法区
连接Linking(验证Verification、准备Preparation:给初始值int给0;final的时候会赋值、解析Resolution可以初始化以后再解析（常量池的符号引用替换为直接引用的过程）)
初始化Initialization（是clinit方法执行的过程）<clinit>方法只有在有静态代码块才有,且只执行一次
使用using
卸载Unloading

2.类加载器
类与类加载器（每个类加载器有自己的空间instanceof不相同）
2.1 双亲委派（防止重复加载；防止核心类库被窜改） 全新类先由启动类加载器加载，然后是拓展类加载器，然后是应用程序类加载器，最后是自定义加载器
启动类加载器BootstrapClassLoad(C++实现虚拟机一部分)加载jre/lib/rt.jar
拓展类加载器ExtensionClassloader:加载jre/lib/ext类
应用程序类加载器:加载环境里加载的java类 

  破坏双亲委派类加载器：线程上下文类加载器ThreadContextClassLoader

3.何时初始化
遇到new、getstatic、putstatic、invokestatic指令(读取静态字段，jinal编译器已放入常量池)
java.lang.reflect反射
初始化子类要先初始化父类
jdk7lang.invoke.methodHandle
虚拟机启动的main




II.字节码引擎
static、special(特殊的)可以在解析阶段确定唯一版本（静态、私有、构造、父类final）的方法非虚方法。其他都是虚方法
分派char->int->long->float->double>Character>Serializable>char...变长






II.第十一章（晚期）运行期优化
解释器和编译器（解释执行时，热点代码用即时编译器编译为本地代码）

1.默认混合模式（mixed mode）根据硬件性能选择C1或C2（-server -client强制指定）
Client Compiler（获得更高的编译速度）
Server Compiler（获得更高的编译质量）

2.分层编译（jdk6开始、7server模式默认）Client和Server会同时工作，代码会多次编译
第0层，程序解释执行，解释器不开启性能监控
第1层，C1编译，将字节码编译为本地代码，简单可靠优化
第2层，C2编译，将字节码编译为本地代码，激进优化，编译耗时较长

3.编译器-热点代码基于计数器的热点探测（hotspot使用）
方法调用计数器（invocation Counter）
回边计数器（Back Edge counter）一个方法中循环体代码执行的次数

4.编译器-代表性优化技术
公共子表达式消除(语言无关的优化):（(c*b)*12+a+(a+b*c)）优化为（E*12+a+(a+E)）合并相同的计算；
数组边界检查消除(Array Bounds Checking Elimination经典优化技术):不检查数组下标i>=0-&&i<foo.length;;还有隐式异常检查
方法内联inline（最重要的优化）b.get()替换为b.value：消除方法调用成本
逃逸分析(escape analysis默认不开启,前沿优化；为其他优化提供依据的分析技术)
锁消除(lock elision)
锁膨胀(lock coarsening)








II.Java内存模型:保证解决三种特性的问题(JMM来屏蔽各种硬件和操作系统的内存访问差异)
volatile易变（特性）禁用缓存(CPU缓存)、禁止优化(指令排序)
final告诉编译器：生儿不变，使劲优化。



三特性
原子性：一个多多个操作只能等一个线程完成以后另外一个线程才能进行  synchronized
可见性：一个线程修改了共享变量的值其他线程可以立刻知道这个修改  synchronized(monitorenter、monitorexit)、volatile、final
有序性:本线程观察都是有序的（线程内串行语义），其他线程观察都是无序的（指令重排序、工作内存和主内存同步延迟） synchronized、volatile


先行发生原则(happens before):
程序次序规则program order rule写在前面的先执行
管程锁定规则monitor lock rule(监视器锁规则) 
volatile变量规则volatile variable rule：对一个volatile变量的写操作先行发生于后面对这个变量的读操作，这里的后面同样是指时间上的先后顺序。
线程启动规则thread start rule：thread对象的start方法先行发生于此线程的每一个动作。
线程终止规则 thread termination rule线程中的所有操作都先行发生于对此线程的终止检测，我们可以通过thread。join方法结束、thread.isalive的返回值等后端检测到线程已经终止执行。




II.线程安全 




线程状态:
新建new:创建后尚未启动的线程处于这种状态
运行runnable:runnable包括了操作系统线程状态中的running和ready，也就是处于此状态的线程有可能正在执行，也有可能正在等待着CPU为它分配执行时间。
无限期等待waiting：处于这种状态的线程不会被分配CPU执行时间，他们要等待被其他线程显示的唤醒。
限期等待timed waiting ：处于这种状态的线程也不会被分配CPU执行时间，不过无须等待被其他线程显示地唤醒，在一定时间之后它们会由系统自动唤醒。
阻塞blocked 线程被阻塞了，阻塞状态与等待状态的区别是:阻塞状态synchronized
结束terminated :已终止线程的线程状态，线程已经结束执行。



线程安全:
同步互斥（多个线程访问共享数据时同一时刻只有一个线程可以访问共享数据） synchronized ReentrantLock可重入锁（jdk6以后性能一样） 读写锁ReentrantReadWriteLock

非阻塞同步:CAS(CompareAndSwap)

无同步方案:线程本地存储（一个数据被一个线程独享）








II.垃圾收集


对象已死判断
可达性分析算法：1.虚拟机栈;2.栈帧本地变量表引用的对象；3.方法区静态属性常量引用的对象；4.本地方法JNI引用对象。

强引用new
软引用soft reference内存不够时回收
弱引用weak reference第一次垃圾收集清除
虚引用phantom reference幽灵引用;加入finalize队列

标记死亡
1.garbage Collection垃圾收集 roots不能定位
2.是否需要调用finalize；
2.1不需要调用就回收；或者调用过一次也回收
2.2需要就放入低优先级队列调用finalize方法-如果在finalize方法逃脱就移除即将回收集合


回收方法区
1.常量和堆一样回收（类接口、方法和引用都一样）
2.  该类无任何实例、该类的classloader已被回收、该类的lang.class未被任何地方引用不能被反射


1.标记清除算法（mark sweep） 缺点1：标记和清除效率都不高；缺点2:收集后没有大块的空闲空间给大对象
2.复制算法(新生代用jvm8Eden：1From survivor：1To Survivor):把内存分为1V1，只用一半，收集时，把还存活的复制到另一半，把之前的整块清理
3.标记清除整理算法（老年代用）：比算法1多了一个整理；把存活的内存复制到一起，清理其余的内存
4.分代算法(Generational Collection)不同模块用不同算法
新生代：存活较小，用复制算法
老年代：存活较多，用标记清除算法；或者标记清除整理算法


垃圾收集默认版本
client模式win32  Seiral GC+Seiral Old

server模式
jdk6之前 Parallel Scavenge + Serial Old
jdk6、7、8 Parallel Scavenge + Parallel Old
jdk9 G1(Garbage First) 整理上看基于标记整理，局部Region之间基于复制算法实现；没有空间碎片




Eden区，两个Survivor(色外ver)区的内存空间比例为：8:1:1


II.新生代young收集器
1.Serial连续的（单个CPU效率最高，client模式下）：复制算法；STW(StopTheWorld)后单线程收集
2.ParNew新式的（多线程版本的serial）:复制算法；STW后多线程收集；在单CPU情况下效率不如Serial
3.Parallel scavenge平行的(比parnew多自适应调节策略制定最大停顿时间)  吞吐量Throughput=用户代码时间/(用户代码时间+垃圾收集时间)

II.老年代old收集器
1.Serial Old(MSC)全搭配(单线程标记清除整理算法)
2.Parallel Old（多线程标记清除整理算法）JDK6才开始提供
3.CMS（多线程标记清除无整理）并发收集低停顿
收集步骤
初始标记(需要STW)
并发标记(并发处理)
重新标记(需要STW)
并发清除(并发处理)

缺点:CPU核数少不适用、不能处理浮动垃圾（ConcurrentModeFailure出现时进行一次FullGC启用Serial Old收集器）、基于标记清除算法，每次整理后有很多空间碎片产生每次FullGC整理一次


CMS:始标(STW)>并发标(并发)>重标(STW)>并发清除
G1:初始标(需要STW)>并发标>最终标记>筛选回收

II.G1（面向服务端的收集器）新老都用（低停顿）jdk7时开始提供：

特点：
并行与并发:G1能充分利用多CPU
分代收集-管理整个堆
空间整合:整体上看基于标记整理，局部Region之间基于复制算法实现；没有空间碎片
可预测的停顿:对于CMS的优势可以指定一个长度X毫秒内的时间片段

步骤
初始标记(需要STW)
并发标记
最终标记
筛选回收

----------------------------------------------------------JVM结束------------------------------------------------------------------------------------------



Mac OS IOS 使用开源UNIX Darwin
android 使用 linux
SUSV1(Single UNIX specification):单一unix规范 1994 有1170个接口 Spec1170

SUSV4(Single UNIX specification):单一unix规范 2010 1833接口

Unix程序员手册有大部分接口。
本书精选了400多个系统调用和库函数，这些都是unix系统核心功能


IEEE POSIX :POSIX指的是可移植操作系统接口(Portable Operating System Interfacc)。

# 第一章 UNIX基础知识
I.第一章 UNIX基础知识


Unix系统结构

应用程序>(shell、公共函数库)> 系统调用>内核 kernel(仁，核、核心)


系统调用system call:内核 kernel 的接口称为系统调用


文件描述符file descriptor: 
通常是一个小的非负整数，内核用来标识特定进程正在访问的文件。
内核打开或创建一个文件时都会返回一个文件描述符。读写时就会用到文件描述符


程序program：
存储在磁盘上的可执行文件，内核使用exec函数，将程序读入内存，并执行。

进程process:
程序执行实例被称为进程。某些操作系统用任务task表示正在执行的程序。

进程ID process IO:
每个进程都有一个位移的数据标识符.非负整数

进程控制:
主要函数fork(新建进程)、exec、waitpid

线程、线程ID
通常一个进程只有一个控制线程thread，某一时刻执行的一组机器指令。
一个进程内的所有线程共享同一地址空间、文件描述符、栈、进行相关属性。
因为它们访问同一存储区，访问共享数据时要采取同步措施避免不一致。
线程ID是所述进程内起作用。

信号signal：
用于通知进程发生了某种情况。 除0时异常，发送sigfpe浮点数异常信号给进程。

进程处理信号的三种方式:
1.忽略信号。
2.系统默认方式处理。除以0系统默认是终止进程。
3.提供一个函数，信号发生时调用该函数，称为捕捉该信号。自编函数。

键盘产生信号:(用于中断当前运行的进程) 
中断键interrupt key(delect或者ctrl+c)
退出键quit key(ctrl + \)

调用系统kill函数，A进程向B进程发送一个信号。（必须是进程所有者、超级用户）


时间值:
日历时间  1970-01-01累计秒数。

进程时间 (CPU时间):时钟时间(wall clock time 进程运行总时间)、用户CPU时间、系统CPU时间
time -p 



系统调用和库函数:
用户>系统调用
用户>库函数>系统调用



I.第一章 UNIX标准及实现


II.unix标准化

**III.ISO C**
提供C程序的可移植性，让C程序适用于大量不同操作系统。

定义了C的语法、语义、标准库。

IOS C库分成24个区 ：四种unix实现(FreeBSD、Linux、MaxOS、Solaris)都支持

assert.h  验证程序断言
coplex.h 复数算术支持
ctype.h  字符分类和映射支持
errno.h  出错码
fenv.h  浮点环境
float.h 浮点常量以及特性
inttypes.h 整型格式
iso646.h  赋值、关系以及一元操作符
limits.h 实现常量
locale.h 本地化类别以及相关定义
math.h  数学函数
setjmp   非局部goto
signal.h   信号
stdarg.h 可变长度参数
stdbool.h  布尔
stddef.h   标准定义
stdint.h  整型
stdio.h  标准IO库
string.h  字符串
tgmath  通用类型数学
time    时间和日期
wchar 扩充字符
wctype  宽字符分类和映射支持


**III.IEEE POSIX**

POSIX:指的是可移植操作系统接口(Portable Operating System Interfacc)。

IEEE 1003.1标准:
是接口interface不是实现。并不区分系统调用和库函数，标准中都被称为函数。

四种：必须头文件、XSI可选头文件、可选头文件、可选接口组合选项码
数:23页

XSI :X/Open System Interface
只有遵循XSI的实现才能称为UNIX操作系统。

**POSIX 标准必须头文件:**

aio.h  异步IO
netdb.h 网络数据库操作
pthread.h 线程
semaphore.h 信号量
termios.h  终端IO
arpa/inet.h  因特网定义
net/if.h   套接字本地接口
netinet/in.h  因特网地址族
netinet/tcp.h  传输控制协议定义
sys/select.h  select函数
sys/socket.h  套接字接口
sys.stat.h  文件状态
sys/wati.h   进程控制

XSI 可选头文件
sys/ipc.h   IPC
sys/msg.h    XSI消息队列
sys/resouce.h  资源操作
sys/sem.h      XSI 信号量操作
sys/shm.h    XSI共享存储
sys/uio.h    矢量操作


**III.Single UNIX specification (SUS ,单一unix规范)**
是POSIX.1标准的超集(拓展)

定义了一些附加接口拓展了POSIX规范提供的功能。
XSI :X/Open System Interface
只有遵循XSI的实现才能称为UNIX操作系统。
XSI  XSI conforming规范


II.unix系统实现

SVR4:   AT&T 实验室
美国电话电报公司（AT&T）是一家美国电信公司

BSD (Berkeley software distribution):
加州大学伯克利分校

FreeBSD:
加州大学


Linux:
提供了类似unix的丰富变成环境，在GNU指导下


Mac OS x:
操作系统核心Darwin 基于 mach内核

Solaris:
Sun 操作系统

II.标准和实现关系


II. unix 限制
ISO C限制、POSIX限制、XSI限制






# 第三章 文件IO
I. 文件IO

文件描述符:
正整数,操作文件都会返回

打开文件:open、openat


创建文件:creat函数、open也可以创建


打开文件设置偏移量lseek:
当前文件偏移量:current file offset
度量文件开始处计算的字节数


读写数据:read 、write



IO效率:


文件共享:
支持不同进程之间共享打开文件



#第四章 文件和目录
I. 文件和目录


II.文件类型
普通文件regular file

目录文件directory file

块特殊文件block special file :磁盘

字符特殊文件:character special file:

FIFO进程间通信 (命名管道named pipe):

套接字socket: 进程间网络通信

符号链接 symbolic link:指向另外一个文件




#第四章 标准IO库
I.  标准IO库

IO函数:
所有IO函数都是围绕文件描述符的。打开一个文件，就会返回文件描述符。

标准IO库:
围绕流stream进行。打开一个文件，已使一个流和文件关联。

一个进程预定义了3个流:
标准输入、标准输出、标准错误。


II.缓冲
标准IO库提供缓冲的目的是尽可能减少使用read和write调用的次数。
它对每个IO流自动进行缓冲管理。

**3钟类型的缓冲:**
**全缓冲:**
在填满标准IO缓冲区后才进行实际IO造作。

冲洗flush说明标准IO缓冲区的写操作。
unix中 flush冲洗有两个意思.:
1.标准IO中冲洗是把缓冲区内容写到磁盘上
2.在终端驱动程序中flush表丢弃缓冲区的数据

**行缓冲**
在输入输出中遇到换行符时，标准IO库执行IO操作。
行缓存即使没到一行，但是超过缓冲区大小了也会IO操作。

**不带缓冲**
标准IO库 不对字符进行缓冲存储。 错误流就不带缓冲区


打开流 fopen、读写流fread、fwrite


定位流: ftell、fseek、SUS(ftello、fseeko)、ISOc(fgetpos、fsetpos)
 




**格式化IO:**


格式化输出:print、dprintf等等

格式化输入:scanf、fscanf



临时文件:IOSc(tmpnam、tmpfile)


内存流:fmemopen
缓存数据在内存中。在内存中创建操作文件



II.标准IO的替代
效率不高、



# 第六章 系统数据文件和信息


口令文件:posix用户数据
pwd.h 

阴影口令:
单向加密


组文件:posix 
grp.h





# 第七章 进程环境

开始函数:main 、exec函数


退出函数: exit

进程终止:8种
1.main返回
2.调用exit
3.调用_exit
4.最后一个线程从启动函数返回
5.最后一个线程调用pthread_exit

异常3种:
6.调用abort
7.接到一个信号
8.最后一个线程对去求请求做出响应



命令行参数:exec
通过启动时命令行给main函数传入参数


环境表:
每个程序都接收到一张环境表(字符指针数组)。

environ环境指针:environment pointer


C程序存储空间布局:
正文段
初始化数据段
未初始化数据段
栈
堆


存储空间分配:

malloc 
calloc
realloc

替代的存储空间分配:
libmalloc
vmalloc
quick-fit
jemalloc
TCMalloc
函数alloca



环境变量:
getenv
putenv
setenv
unsetenv


函数setjmp 和longjmp
goto语句使用的就是上边的函数。





# 第8章 进程控制

I.进程标识:进程ID

I.创建子进程:fork、vfork

终止进程:exit


等待:wait、waitpid、waitid、wati3

竞争条件:
多个进程竞争共享数据，


exec：打开另外程序新进程
fork函数创建子进程。子进程往往要调用exec函数执行另一个程序。


用户ID和组ID:读写，权限等


I.进程会计:选项启动后，CPU总时间、启动时间等。

process acounting 



I.用户标识
获得进程用户的登录名、用户ID


I.进度调度
基于调度优先级粗粒度的控制。调度策略和优先级是内核决定的。
降低nice值来降低优先级、只有特权用户可以提高调度权限。
nice值  范围是[-20, 19]，默认值是0
越高优先级越低

I.进程时间:
墙上时钟时间、用户CPU时间、系统CPU时间


# 进程关系
I 进程关系

I.终端登录
执行各个程序，早起用户使用终端登录(直接连接、远程)。
登录都是内核的终端的设备驱动程序。

I.网络登录
通过串行中断登录到系统和网络登录主要区别是：网络登录不是点对点。
网络情况下login是一种可用服务,ftp smtp


I.进程组
进程有ID，还属于进程组。进程组时多个进程的集合。


信号发送给一个进程、或者一个进程组


I.会话session
是一个或多个进程组的集合

I.控制终端
会话和进程组有一些特性:
一个会话可以有一个控制终端。controlling terminal
建立控制终端连接的会话首进程:控制进程
一个会话中的多个进程组分为:前台进程foreground process group、后台进程background process group
会话有一个控制终端，他有一个前台进程组，其他都是后台进程组
无论何时终端键入ctrl+\ 退出信号发送至前台进程组所有进程。
终端检测到调制解调器(或网络)断开,则将挂断信号发送至控制进程，会话首进程。


I.tcgetpgrp、tcsetpgrp、tcgetsid
通知内核进程组时前台进村组

tcgetpgrp:返回前台进程组的进程组ID

tcsetpgrp:将前台进程组ID设置为pgrpid。pgrpid的值应当是在同一个会话中的一个进程组的ID


I.作业控制
允许终端上启动多个作业(进程组)，控制那个作业可以访问终端或者后台运行
1.支持控制shell
2.终端驱动程序支持作业控制
3.信号控制支持

I.shell执行程序
shell与 进程组、控制终端、会话等关系

I.孤儿进程组
父进程终止的进程叫孤儿进程 orphan process
这种进程由init进程收养。



# 第十章 信号
I. 第十章 信号


信号是软件中断

信号命名规则: SIG*

I.信号概念

产生信号的方式:
终端按键delete、ctrl+c 产生中断信号
硬件异常
进程调用kill
用户调用kill
检测软件某些条件


内核信号的处理:
1.忽略
2.捕捉 调用用户函数
3.执行默认操作

常见信号:
10.2  p251


不可靠的信号:
信号可能丢失，进程不知道。


中断的系统调用:


信号集:
signal set数据类型



# 第11章 线程
I. 第十一章 线程

一个进程中所有线程都可以访问该进程的组成部件， 文件描述符和内存


线程接口: pthread、POSIX线程


线程标识：线程ID(只有在所属进程上下文中才有意义)


线程创建:
POSIX线程  phtread_create

线程终止:
三种方式:
1.启动例程中返回，返回值是线程的退出码
2.同一进程的其他线程取消
3.线程调用phtread_exit


I.线程同步
多个线程操作相同数据，

线程同步解决数据不一致问题


互斥量mutex:  互斥量mutex和信号量Semaphore
使用pthread互斥接口来保护数据，同一时间只有一个线程访问数据。
互斥量mutex本质是一把锁

phtread_mutex_lock、phtread_mutex_trylock、phtread_mutex_unlock


避免死锁:
1.对互斥量两次加锁
2.互相等待

phtread_mutex_timedlock:允许互斥量阻塞时间。
愿意等待多久


读写锁(reader-writer lock):与互斥量类似
读模式状态、写模式状态、不加锁状态
一次只有一个线程可以占有写模式，可以多个线程占有读模式。

又叫 共享互斥锁  shared-exclusive lock

pthread_rwlock_init、pthread_rwlock_destroy

带超时时间读写锁: pthread_rwlock_timedrdlock、pthread_rwlock_timedwrlock


条件变量:
是线程可用的另一种同步机制。(条件变量+互斥量)
pthread_condattr_init
pthread_condattr_destroy

自旋锁:
自旋锁与互斥量类似，不是通过修改使进程阻塞，获取锁之前一直处于忙等阻塞状态。
自旋浪费CPU资源。


屏障(barrier):
多用户协调多个线程并行工作的同步机制。

pthread_barrier_init
pthread_barrier_destroy
pthread_barrier_wait



# 第十二章线程控制

线程限制:
PTHREAD_DESTRUCTOR_ITERATIONS :线程退出时操作系统实现试图销毁线程特定数据的最大次数。

PTHREAD_KEYS_MAX:进程可以创建的最大数目
PTHREAD_STACK_MIN:线程的栈可用的最小字节
PTHREAD_THREADS_MAX:进程可以创建的最大线程

线程属性:
POSIX 线程执行调度  thread execution scheduling 
detachstate 线程分离状态属性
guardsize 线程栈末尾境界缓冲区大小 字节数
stackaddr 线程栈最低地址
stacksize 线程栈最小长度

**同步属性:**
进程共享属性

互斥量属性:
pthread_mutexattr_t  

读写锁属性:

条件变量属性:

屏障属性:


**重入** 可java重入不是一个意思， 本文中是并发调用
一个函数在相同时间可以被多个线程安全调用。 这个函数是线程安全的。



线程特定数据(thread-specific data):
线程私有数据 thread-private data,是存储和查询某个特定线程相关数据的机制。

pthread_key_create



线程取消选项：
当前线程可取消状态的设置
pthread_cancel 


线程和信号：
每个线程都有自己的信号屏蔽字,信号的处理是进程中所有线程共享的。



线程和fork
调用fork时，为子进程创建了整个进程地址空间的副本。


线程和IO



# 第十三章 守护进程

守护进程 daemon 。系统引导装入时启动，仅在系统关闭时才终止。
无控制终端，后台运行。

unix有很多守护进程。

ps -axj
-a 显示其他用户进程。 -x 显示没有终端的进程 -j 显示与作业有关的信息。

类似命令
ps -efj

# 第十四章 高级IO


**非阻塞IO**
低速系统调用和其他

低速系统调用:网络终端设备等调用可能永远阻塞
某些文件类型(管道、终端设备、网络设备)数据不存在，读可能永远阻塞。
如果数据不能被相同的文件类型立即接受(管道无空间、网络流控制)，写操作可能永远阻塞。
某种条件下打开某些文件类型可能发生阻塞。
强制性记录锁的文件进行读写。
ioctl操作
某些进程间通信函数


对于一个描述符，两种方式指定非阻塞IO方法
1.open获得描述符，指定 O_NONBLOCK 标志
2.已打开的描述符调用 fcntl 由该函数打开O_NONBLOCK 文件状态标志。





**记录锁(record locking):范围锁更准确 部分文件加锁**
一个进程在读写文件某部分时，记录锁阻止其他进程修改同一文件区。

flock函数，整个文件加锁
fcntl 记录锁 ,部分文件加密

锁的继承和释放 :
锁和进程和文件有关系:进程终止锁全释放，描述符关闭全部锁释放
fork产生的子进程不继承父进程设置的锁。
exec新程序可以继承原程序的锁。

文件末尾加锁:


建议性锁和强制锁

flock函数:
早期 只支持 flock函数  全文件加锁

fcntl记录锁

int fcntl(int fd ,int cmd ,struct flock *flockptr );

对于记录锁cmd是 F_GETLK、F_SETLK、F_SETLKW
F_GETLK: 判断由flockptr所描述的锁释放会被另外一把锁排斥(阻塞.)。
如果存在一把锁，它阻止创建由flockptr所描述的锁，则该现有锁的信息将重写flockptr
指向的信息。如果不存在这种情况，则除了将l_type设置未F_UNLCK之外
flockptr所指向的结构中其他信息不变

F_SETLK:设置由于flockptr锁描述的锁。如果我们试图获得一把读锁或写锁 l_type= f_rdlck、f_wrlck
而兼容性规则阻止系统给我们这把锁，那么fcntl会立刻返回错误，此时errno设置未EACCESS或者EAGAIIN

F_SETLKW:这个命令是F_SETLK的阻塞版本，命令中的W表示等待wait。
如果所有请求的读锁或写锁因一个进程当前已经对请求的区域某部分进行了加锁而不能被授予，
那么调用进程会被设置为休眠。如果请求创建的锁已经可用，或者休眠由信号中断，则会被唤醒。


flockptr指向flock结构的指针

struct flock{
short l_type; F_RDLCK共享读锁、F_WRLCK独占写锁、F_UNLCK解锁一个区域
short l_whence;
off_t l_start; 加锁解锁的起始偏移量
off_t l_len;  区域的字节长度
pid_t l_pid; 进程ID持有的锁能阻塞当前进程
}

flock结构说明
l_type锁类型:F_RDLCK共享读锁、F_WRLCK独占写锁、F_UNLCK解锁一个区域
加锁解锁的起始偏移量:l_start、l_whence
区域的字节长度:l_len

进程ID持有的锁能阻塞当前进程:l_pid

不同进程：读读共享、读写阻塞。
同进程：有写锁，可以再加读锁，会替换锁。


死锁：两个进程互相等待对方的资源。

锁隐含继承和释放:
1.锁和进程和文件关联。进程终止全释放，文件描述符关闭锁释放。
2.fork子进程不继承父锁。
3. 执行exec新程序继承原程序的锁。

记录锁的实现:

文件尾端加锁：
需要注意，因为是获得文件偏移量， 文件长度变化会影响加锁。

建议锁和强制锁





**II.IO多路转接 IO多路复用**

读取一个描述符写入到另外一个文件描述符
while(n=read(stdin_fileno,buf,bufsiz)>0)
   if(write(stdout_fileno,buf,n!=n))
     err_sys("write error");

问题:读取两个文件，写入到一个新文件怎么做。

解决:一个进程，使用非阻塞IO读取数据。轮询多个文件

1.两个输入A、B描述符都设置为非阻塞，
2.对A发一个read,有数据就用，没有数据立刻返回。
3.对B发一个read,有数据就用，没有数据立刻返回。
4.等待若干时间，重复2、3操作。

这种循环方式称为:轮询
缺点:CPU浪费。


除了上述方式意外，还可以使用IO multiplexing
调用poll、pselect、select函数


**poll、pselect、select函数:**

**I.select函数**

POSIX 兼容平台，select函数可以执行IO多路复用。

select (int maxfdpl, fd_set *restrict readfs, fd_set *restrict writefds,fd_set *restrict exceptfds,struct timeval *restrict tvptr)

II.maxfdpl 参数说明:
最大文件描述符编号加1 。描述符集合里最大描述符+1。

II.tvptr参数说明:
null 永远等待
0 不等待，测试所有指定的文件描述符立刻返回。
!=0 等待指定的秒数、微妙数。 当指定的描述符之一准备好，或者已经超时立即返回。超时返回0



II.readfs、writefds、exceptfds参数说明：
描述符集指针。三个描述符集说明了，可读、可写、异常的描述符集合。

fd_set结构体:
存储了一批文件描述符各自是否有事件触发。

II.函数返回值:
-1出错
0没有准备好的描述符
大于0 已经准备好的描述符.是三个描述符集准备好的描述符之和。



II.1024问题:
1.最多监听1024个socket
2.socket的文件描述符数值不能超出1024不然会数组越界





一个描述符阻塞不影响select是否阻塞。

文件描述符可以设置，阻塞非阻塞
select 都是阻塞等待的.






**pselect**
POSIX 定义了一个select 变体:pselect
区别:
时间参数，支持秒和纳秒。  不是秒和微秒
超时值定义成const,保证不可变
可选信号屏蔽字


**poll函数:**
类似select ,SystemV引入，poll支持任何类型的文件描述符
int poll(struct pollfd fdarray[], nfds_t nfds, int timeout);

poll不是为每个条件(可读性、可写性、异常条件)构造一个描述集，
而是构造一个pollfd结构的数组，每个数据元素指定一个描述符编号以及描述符条件。

struct pollfd{
int fd;
short events;
short revents;
}



应将每个数组元素的events成员设置所示中的一个或多个，
通过这些值告诉内核我们关系的是每个描述符的那些事件。
返回时，revents成员由内核设置，用于说明每个描述符
发生了那些事件。

timeout=-1永远等待
timeout=0不等待。测试所有描述符立刻返回。这是轮训系统的方法，可以找到多个描述符状态而不阻塞poll函数。
timeout>0

理解文件尾端与挂断之间的区别是很重要的。如果我们正从终端输入数据，并键入文件结束符，
那么就会打开POLLIN，于是我们就可以读文件结束指示read返回0. revents中得
pollhub没有打开。如果正在读调至解调器，并电话线已挂断，我们将接到pollhub通知。



select和poll的可中断性，
终端的系统调用的自动重启是由,BSD引入的，但当时select函数是不重启的。
这种特定在大多数系统中一直延续了下来，即使指定了sa_restart选项也是如此。但是，
在SVR4上，如果指定了SA_RESTART,那么select和poll也是自动重启的。为了在将软件
移植到SVR4派生的系统上时阻止这一点，如果信号有可能会中断select或poll就要使用
signal_intr函数






**异步IO:**
异步I/O asynchronous I/O
利用这种技术，进程告诉内核:当描述符准备好，可以进行IO时，用一个信号通知它。

有两个问题，尽管一些系统提供了各自的异步IO，但POSIX采纳另一套标准。
1.POSIX异步IO是 single UNIX Specification中可选设置。BSD:SIGIO、SystemV:SIGPOLL
2.信号对每个进程而言只有一个(SIGPOLL、SIGIO)如果两个文件都用这个来标识，程序无法区分是哪个文件的。


使用上一节说明的select和poll可以实现异步形式的通知。关于描述符的状态，系统并不主动
告诉我们任何信息，我们需要进行查询(select或poll)。信号机构提供了一种
以异步形式通知某些事件已发生的方法。由于BSD和SystemV派生的所有系统
都提供了某些形式的异步IO，使用一个信号(Systemv SIGPOLL \ BSD SIGIO)通知进程，对某个
描述符所关心的某个事件已经发生。我们在前面的章节中提到过，这些形式的异步IO是受限制的:
我们并不能在所有的文件类型上，而且只能使用一个信号。如果要对一个以上描述符进行异步IO
那么在进程接收到信号时并不知道这一信号对应于那一个描述符。

SUSv4中将通用的异步IO机制从实时扩展部分调整到基本规范部分。这种机制解决了这些
陈旧的异步IO设置存在的局限性。

在我们了解使用异步IO的不同方法之前，需要先讨论一下陈本。在用异步IO的时候，要通过
选择来灵活处理多个并发操作，这会使应用程序的设计复杂化。更简单的做法可能是使用多线程，
使用同步模型来编写程序，并让这些线程以异步的方式运行。
使用POSIX异步io接口，会带来下列麻烦。
1。每个异步操作由3处可能产生错误的地方:1在操作提交的部分，1在操作本身结果，1在异步操作状态的函数中。
2.与posix异步IO即可的传统方法项目，他们本身设计大量的额外设置和处理机制。
3.从错误中回复可能会比较困难。举例来说，如果提交了多个异步写操作，其中一个失败了，下一步我们应该怎么做
如果这些写操作是相关的，那么可能还需要撤销所有成功的写操作。


SystemV 异步AT公司
BSD异步
POSIX异步:aio



**SystemV 异步IO :**
Unix System V 是正统的 AT&T Unix 操作系统，
在systemv中 异步IO是 streams系统的一部分，它只对streams 设备部和 streams管道起作用。
systemV的异步信号是 SIGpoll
为了对一个streams设备启动异步IO，需要调用ioctl,将它的第二个参数request
设置成i_setsic。第三个参数是由一个或多个常量构成的整型值。 这些常量是在 stropts.h中定义的。
与streams机制相关的即可在susv4中已被标记为弃用，所以这里不讨论他们的任何细节。

除了调用ioctl指定产生SIGPOLL信号的条件意外，还应为改信号建立信号处理程序。
sigpoll默认动作是终止改进程，所以应当在调用ioctl之前建立信号处理程序。

**BSD异步IO**
在BSD派生的系统中，异步IO是信号 SIGIO和SIGurg的组合。 SIGio是通用异步IO信号，SIGURG则只用来通知
进程网络连接上的带外数据已经到达。
为了接收sigio信号，需执行以下3步。
1。调用signal sigaction为 SIGIO信号建立信号处理程序。
2。以命令 F_setown 调用fcntl来这是进程IO或者进程组ID，用于接收对于该描述符的信号。
3。 以命令f_setfl调用fcntl设置 O_ASYNC文件状态标志，使在该描述符上可以进行异步IO

第3步仅能对指向中断或网络的描述符执行，这是BSD异步IO设置的一个基本限制。
对于SIGurg信号，只需执行第一步和2步。该信号仅对引用支持带外数据的网络连接描述符而产生，如TCP连接。

**POSIX异步IO**
posix异步io接口为对不同类型的文件进行异步IO提交了一套一直的方法。这些接口来自实时
草案标准，该标准是single unix specification的可选项。在SUSV4中，这些接口被移到了基本部分中，
所以现在所有的平台都被要求支持这些接口。
  这些异步IO接口使用AIO控制块来描述IO操作。 aiocb结构定义了AIO控制块。该结构至少包括下面这些字段

struct aiocb{
int aio_files; //file descriptor 文件描述符  
off_t aio_offset; // file offset for IO  读写从指定的偏移量开始
volatile  void * aio_buf;  // buffer for IO  读操作数据会复制到缓冲区中，该缓冲区从 这个指定的地址开始。
size_t  aio_nbytes;  //number of bytes to transfer  包含了要读写的字节数
int aio_reqprio;   //priority  异步请求提示顺序
struct sigevent  aio_sigevent; // signal information  IO事件完成后如何通知程序。
int aio_lio_opcode;   // operation for list io  只能用于基于列表的异步IO
}

sigevent IO完成后如何通知程序

struct sigevent{
int sifev_notify;//通知的类型 三选一； 
int sigev_signo;
union sigval  sigev_value;
void (*sigev_notify_function)(union sigval);
pthread_attr_t * sigev_notify_attributes;

}

sifev_notify 控制通知类型 三选一
1.SIGEV_NONE  异步IO请求完成后,不通知进程
2.SIGEV_SIGNAL 异步IO请求完成后，产生由sigev_signo字段指定的信号。如果应用程序选择捕捉信号，
且在建立信号处理程序的时候指定了SA_SIGINFO标志，那么该信号将被入队。信号处理程序会传送给
一个siginfo结构，该结构的si_value字段被设置为sigev_value。
3.SIGEV_THREAD  异步IO请求完成后，sigev_notify_function字段指定的函数被调用。
sigev_value字段被传入作为它的唯一参数。除非sigev_notify_attributes字段被设定为pthread属性结构的地址，
且该结构指定了一个另外的线程属性，否则该函数将在分离状态下的一个单独线程中执行。

进行异步IO之前需要先初始化AIO控制块，调用aio_read函数来进行异步读操作，或者调用aio_write函数来进行异步写 操作。

当这些函数返回成功时，异步IO请求已经被操作系统放入等待处理的队列中了。这些返回值
与实际IO操作的结果没有任何关系。IO操作在等待时，必须注意确保AIO控制块和数据库缓冲区保持稳定；
他们下面对应的内存必须始终是合法的，除非IO操作完毕否则不能复用。

要想强制所有等待中得异步操作不等待而写入持久化的存储中，可以设立一个AIO控制块并调用
aio_fsync函数。

AIO控制块中得AIO_fildes字段指定了其异步写操作被同步的文件。如果OP参数设定为O_DSYNC,
那么操作执行起来就会像调用了fsync一样。

像aio_read和aio_write函数一样，在安排了同步时，aio_fsync操作返回。在异步同步操作完成之前，数据不会被
持久化。AIO控制块控制我们如何被通知，就像aio_read和aio_write函数一样。
为了获知一个异步读写或者同步操作的完成状态，需要调用aio_error函数

int aio_error(const struct aiocb *aiocb)
返回值为下面4钟情况中得一种。
0 异步操作成功完成。需要调用aio_return函数获取操作返回值     
-1  对aio_error调用失败。这种情况下，errno会告诉我们为什么
einprogress  异步读写或同步操作仍然在等待。
其他情况  其他任何返回值是相关的异步操作失败返回的错误码。
如果异步操作成功，可以调用aio return 函数来获取异步操作的返回值。

ssize_t aio_return(const struct aiocb *aiocb);

知道异步操作完成之前，都需要小心不要调用aio_return函数。操作完成之前的结果时未定义的。
还需要小心对每个异步操作只调用一次aio_return.一旦调用该函数，操作系统就可以
释放吊IO操作返回值的记录。

  如果aio_return函数本身失败，返回-1 ，并设置errno 。其他情况下，它将返回异步操作的结果，
会返回read write或者fsync 在被成功调用时可能返回的结果。

  执行IO操作时，如果还有其他事务要处理而不想被IO操作阻塞，就可以使用异步IO 。然而，如果在完成了所有事务时，
还有异步操作未完成时候，可以调用aio_suspend函数来阻塞进程，直到操作完成。

int aio_suspend(const struct aiocb *const list[],int nent,
  const struct timespec *timeout);

返回三种情况中得一种。被一个信号中断，返回-1，设置errno为EINTR.
没有任何io操作完成的情况下，阻塞的时间超过了函数中 可选的timeout参数指定的时间限制，
那么aio_suspend返回-1 ,并将errno设置为 eagain。
如果没有任何IO完成，aio_suspend返回0。  如果在我们调用aio_suspend操作时，所有的异步IO操作都已完成，
那么aio_suspend将不再阻塞的情况下直接返回。

list参数是一个指向aio控制块数组的指针，nent参数表明了数组中得条目数。
数组中得空指针会被跳过，其他条目都必须指向已用于初始化异步IO操作的AIO控制块。

当还有我们不想再完成的等待中得异步IO操作时，可以尝试使用aio_cancel函数来取消他们。

init aio_cancel(int fd,struct aiocb *aiocb);
fd 参数指定了那个未完成的异步IO操作的文件描述符。如果aiocb参数未NULL，系统将会尝试取消所有
该文件上未完成的异步IO操作。其他情况下，系统将尝试取消有由aio控制块描述的单个异步IO操作。
我们之所以说系统尝试取消操作，是因为无法保证系统能够取消正在进程中得任何操作。
aio_cancel函数可能会返回4个值中得一个
AIO_ALLDONE 所有操作在尝试取消它们之前已经完成。
AIO_CANCELED  所有要求的操作已被取消
AIO_NOTCANCELED 至少有一个要求的操作没有被取消。
-1   对aio_cancel调用失败，错误码将被存储在errno中

如果异步IO操作被成功取消，对响应的AIO控制块调用aio_error函数将会返回错误
ecanceled.如果操作不能被取消，那么响应的AIO控制块不会因为对aio_cancel的调用而被修改。
 
  还有一个函数也被包含在异步IO接口当中，尽管它既能以同步的方式来使用，又能以异步的方式来使用，
这个函数就是lio_listio 该函数提交一系列由于一个AIO 控制块列表描述的io请求。

int lio_listio (int mode ,struct aiocb *restrict const list [restrict], int newt,struct sigevent * restrict sigev)
返回值0 -1

mode决定是否异步。
如被设定为LIO_WAIT,lio_listio函数将在列表IO操作完成后返回，sigev被忽略。

如被设定为LIO_NOWAIT，lio_listio函数将在IO请求入队后立即返回。进程将在所有IO操作完成后，
按照sigev参数指定的，被异步地通知。如果不想被通知，可以把sigen设定为null.

list参数 指向AIO控制块列表，该列表指定了要运行的IO操作。nent参数指定了数组总的元素个数。
    aio控制块可以包含NULL指针，这些条目将被忽略。

每一个AIO控制块中，aio_lio_opcode字段指定了该操作是一个读操作lio_read lio_write lio_nop 读写忽略。
读操作会按照对应AIO控制块被传给aio_read函数来处理。 类似地，写操作会按照对应的AIO控制块被传给了aio_write来处理。

实现会限制我们不想完成的异步IO数量。这些限制都是运行时不变量

sysconf函数 把name参数设置 成

AIO_LISTIO_MAX  单个列表IO调用中得最大IO操作数
AIO_MAX  未完成的异步IO操作的最大数目
AIO_PRIO_DELTA_MAX  进程可以减少其异步IO优先级的最大值。













aio_read
aio_write

aio_error 获取异步读写状态
aio_return :操作完成前不能调用



**readv函数 writev函数:**
读写多个非连续缓冲区。撒布读scatter read 聚集写 gather write



**readn函数 writen函数:**
管道FIFO(网络终端)有两种性质
一次read返回部分数据
一次write返回部署数据



**存储映射IO:**
memory mapped Io
将磁盘文件映射到存储空间的一个缓冲区。

当从缓冲区中取数据时，就相当于读文件中得相应字节。与此类似，将数据存入缓冲区时，
响应字节就自动写入文件。这样就可以在不使用read和write情况下进行IO

首先告诉内核将一个给定的文件映射到一个存储区域中，由mmap函数实现。

void *mmap(void *addr, size_t len ,int prot ,int flag, int fd ,off_t off);

addr 用于指定映射存储区的起始地址。通常为0，由系统选择该影射去的起始位置。
此函数的返回值是该影射区的起始地址。

fd 参数是指定要被影射文件的描述符。在文件映射到地址空间之前，必须先打开该文件。

len 映射的字节数,

off是要映射字节在文件中得起始偏移量

prot 指定了映射存储区的保护要求
(prot_read 客户  prot_write可写 prot_exec 可执行  prot_none 不可访问)

flag 影响映射存储区的多种属性。
map_fixed 返回值必须等于addr. 因为这不利于可移植性，不鼓励使用此标志。

map_shared  本进程对映射区所进行的存储操作配置。存储操作修改映射文件。存储操作相当于对该文件write

map_private  映射区存储操作导致创建该映射文件的一个私有副本。









# 第十五章 进程间通信

进程间通信 InterProcess Communication IPC

可以通过fork exec传送， 文件系统传送。
四种方式:

管道(半双工[单向]、全双工[双向])
XSI(消息队列、信号量、共享存储)
消息队列、信号量、共享存储 
套接字

**管道**
管道是UNIX系统IPC最古老形式，所有unix系统都提供此通信机制。

局限性:
1.半双工(数据只能在一个方向上流动)。现在某些系统提供全双工管道，但是为了移植性，我们不预设系统支持全双工。
2.管道只能在具有公共祖先的两个进程之间使用。 父 fork 子 通信



**popen函数 pclose函数**
创建连接进程的管道


**协同进程 coprocess:**
unix系统过滤程序从标准输入读取数据，向标准输出写数据。


**FIFO :命名管道 (First Input First Output)先进先出**
未命名的管道只能在两个相关进程之间使用，相同祖先进程。
FIO不想管的进程也能交换数据。

用途:
1.shell命令使用FIFO数据从一条管道传送到另一条时，无需创建中间的临时文件。
2.客户进程-服务进程应用中FIFO用作汇聚点，在客户进程和服务器进程二者之间传递数据


**XSI  IPC(拓展进程间通信):**  XSI (X/Open System Interface )
有三种称作XSI IPC的IPC ：消息队列、信号量、共享存储器

内核中IPC结构(消息队列、信号量、共享存储器)都有一个整数标识符(identifier)加以引用。

标识符是IPC对象的内部名。

XSI IPC 权限结构:

**消息队列**
消息队列是消息的链接表，存储在内核中，由消息队列标识符标识。简称队列，标识符 队列ID

创建、打开队列 msgget

消息添加到队列尾部  msgsnd


速度:
最快: 消息队列> 全双工管道 >UNIX域套接字  最慢








**信号量 semaphore:**
信号量与已经介绍过的IPC结构（管道、FIFO、消息队列）不同。

信号量是一个计数器，用于为多个进程提供共享数据对象的访问。

操作流程:
1.测试控制资源的信号量
2.此信号量是正，则进程使用该资源，进程将信号量减一。
3.信号量为0，进程进入休眠状态，直至信号量大于0。进程被唤醒后返回步骤1。

struct semid_ds{
struct ipc_perm sem_perm;
unsigned short  sem_nsems;
time_t     sem_otime;
time_t    sem_ctime;

}






**信号量、记录锁(范围锁)、互斥量 比较**
多个进程之间共享一个资源，三种方式比较。

信号量，创建一个包含一个成员的信号量集合，初始化为1。
记录锁，创建一个空文件，该文件第一个字节作为锁字节。
互斥量，所有进程将相同的文件映射到他们的地址空间里。

linux中操作时间比较
信号量  > 记录锁  > 互斥量 时间最短

尽管对于这种平台来说，在共享存储中使用互斥量是一个更快的选择，但是我们依然使用记录锁，
除非要特别考虑性能。 
有两个原因，
1。在多个进程间共享的内存中使用互斥量来恢复一个终止的进程更难。
2。进程共享的互斥量属性还没有得到普遍的支持。

**共享存储**
共享存储允许两个或多个进程共享一个给定的存储区。 
因为数据不需要在客户进程和服务器进程之间复制，所以这是最快的IPC。

通常，信号量用于同步共享存储访问。


**POSIX信号量**

posix信号量机制是三种IPC机制之一，3种IPC机制源于posix拓展。

Single UNIX Specification 三种机制(消息队列、信号量、共享存储) 可选部分。

SUSv4之前信号量在可选，SUSv4之后信号量是基本规范，消息队列和共享存储时可选。

SUSv4:信号量必选，消息队列和共享存储可选

POSIX 信号量接口 在意解决 XSI 信号量接口的几个缺陷。
1.和XSI接口比， POSXI 信号量更高性能。
2. POSIX 信号量更简单：没有信号量集
3. POSIX 信号量在删除时表现更完美。

posix信号量有两种形式:
命名和未命名
差异是创建和销毁上。

未命名只存在于内存中，要求信号量进程必须可以访问内存。
命名可以通过名字访问，所有进程都可以访问。

命名信号量:
sem_t sem_open(const char *name ,int oflag, unsigned int value)

int sem_unlink(const char *name);销毁信号量


信号量减一 sem_trywait  、sem_wait
int sem_trywait(sem_t *sem) 0不阻塞 ,返回-1。
int sem_wait(sem_t *sem)  阻塞

int sem_timedwait(sem_t *restrict sem, const struct timespec )阻塞一段时间


信号量加1
int sem_post(sem_ * sem)


未命名:
创建:int sem_init (sem_t sem ,int pshared,unsigned int value)
销毁:int sem_destroy(sem_t *sem)
int sem_getvalue(sem_t restrict sem ,int restrict valp)检索值

XSI信号量 和POSIX信号量比较

POSIX信号量时间更短



# 第十六章 网络IPC(进程间通信 :管道、FIFO、消息队列、信号量、共享存储 ) :套接字

进程通信机制IPC: 管道、FIFO命名管道、消息队列、信号量、共享存储。

网络进程通信:network IPC.
即可用来计算机之间通信、也可用于计算机内通信。
尽管协议不同，但是标准协议是TCPIP协议栈。

**套接字描述符socket**
socket 套接字是通信端点的抽象。

创建:socket
int socket(int domain ,int type ,int protocol);

domain 确定通信特性， 地址格式  address family :
AF_inet IP4、 AF_INET6 IP6 、AF_UNIX unix域、 AF_UPSPEC未指定.

type 确定套接字类型
SOCK_DGRAM  :固定长度的、无连接的、不可靠的
SOCK_RAW  : IP协议的数据报接口
SOCK_SEQPACKET : 固定长度、有序的、可靠的、面向连接的 SCTP
SOCK_STREAM  :有序可靠、双向、面向连接的字节流

protocal 通常是0 标识为给定的域和套接字选择默认协议。
IPPROTO_IP
IPPROTO_IPV6  ip6
IPPROTO_ICMP  PING 因特网控制报文协议
IPPROTO_RAW  原始IP数据报协议
IPPROTO_TCP 传输控制协议
IPPROTO_UDP   用户数据报协议



**I.寻址:**
标识一个目标通信进程。  
进程标识有两部分组成:
一部分是计算机网络地址。
一部分是计算机端口号。


字节序
地址格式:
一个地址标识一个特定通信域的套接字端点，地址格式与这个特定的通信域相关。
struct sockaddr{
sa_family_t sa_family;
char    sa_data[];
}

地址查询:
应用程序不需要了解一个套接字地址内部结构。
域名系统Domain name system DNS 或者 网络信息服务 Network information services NIS

gethostent 找到给定计算机系统的主机信息

struct hostent *gethostent(void);

struct hostent{
char  *h_name;// name of host
char ** h_aliases ;// pointer to alternate host name arry
int h_addrtype ;  //address type
int h_length;  // length in bytes of address
char *h_addr_list;// pointer to array of network addresses
}

gethostent包含 gethostbyname、gethostbyaddr

struct netent *gethostbyaddr(uint32_t net ,int type);
struct netent *gethostbyname(const char *name);

struct  netent{
char *n_name; //network name
char *n_aliases  //alternate network name array pointer
int n_addritype  //address type
uint32_t n_net  ;// network number

}

getnameinfo 、getaddrinfo 替代  gethostbyname、gethostbyaddr

int getaddrinfo(const char * restrict host, const char * restrict service,
const struct addrinfo *restrict hint,struct addrinof * restrict res);

int getnameinfo(const struct sockaddr *restrict addr,socklen_t alen,
char *restrict host,socklen_t hostlen,
char *restrict service, socklen_t servlen, int flags);

struct addrinfo{
int ai_flags;   customize behavior 自定义行为
int ai_family   address family
int ai_socktype    
int ai_protocol;  协议
socklen_t  ai_addrlen;
struct sockaddr  ai_addr;
char   ai_canonname;       主机的规范名称canonical name of host
struct addrinfo  ai_next;  next in list
}


**套接字socket与地址关联**
对于服务器，服务器保留一个地址并注册在/etc/services或某个服务中.

int bind(int sockfd,const struct sockaddr *addr, socklen_t len);

限制:
进程在计算机上，地址有效。不能指定其他机器地址
地址必须和创建套接字地址族支持的格式匹配
端口号不小于1024，除非特权。
一般只能将一个套接字端点绑定到一个给定地址上。

getsockname函数发现绑定到套接字上的地址:

int getsockname

如果已连接，可以调用getpeername 函数来找到对方的地址

int getpeername;


 

**建立连接:**


connect(int sockfd,const struct sockaddr * addr, socklen_t len);
对方计算机是打开，正在运行

connect_retry : 指数补偿 exponential backoff算法
调用connect失败，进程会休眠一段，下次循环再尝试、最大延迟2分钟



listen函数:
宣告它愿意接收连接请求
int listen (int sockfd ,int backlog);

backlog 提供一个提示，该进程所要入队的未完成连接请求数量。
一旦队列满了系统会拒绝多余的请求连接。

一旦服务器调用listen ,所用的套接字就能接收连接其你去。
使用accept函数获得连接并建立连接。

accept函数获得连接并建立连接:
int accept(int sockfd, struct sockaddr * restrict addr ,soklen_t *restrict len);

accept返回的文件描述符是套接字描述符，该描述符连接到调用connect客户端。
这个新的套接字描述符和原始套接字sockfd具有相同的套接字类型和地址族。

如果服务器accept调用， 并没有连接请求，服务器会阻塞直到有请求来。
服务器可以使用poll或者select来等待一个请求的到来。






**数据传输:**
既然一个套接字端点表示为一个文件描述符，那么只要建立连接，就可以使用read write来通过套接字通信。

尽管可以通过read write交换数据，但是有专用的函数。
套接字6个专属函数。 三个发数据，三个接收数据。

send函数:

ssize_t send(int sockfd ,const void *buf,size_t nbytes, init flags);
类似write 使用send时套接字必须已经连接。

flags:
MSG_CONFIRM 提供链路层反馈以保持地址映射有效。
MSG_dontroute  :数据包路由出本地网络
MSG_DONTWAIT :允许非阻塞操作
MSG_EOF  : 发送数据库关闭套接字的发送端。
MSG_EOR  :协议支持，标记记录结束
MSG_MORE  : 延迟发送数据包允许写更多数据
MSG_NOSIGNAL :写无连接的套接字时不产生sigpipe信号
MSG_OOB : 如果协议支持，发送带外数据

send成功返回，也并不标识连接另一端进程就一定接收了数据。

sentto 函数:可以在无连接的套接字上指定一个目标地址
ssize_t sendto(int sockfd, const void * buf ,size_t nbytes ,int flags,
const struct sockaddr * destaddr ,socklen_t destlen);

sentmsg函数:带缓冲区
ssize_t sendmsg(int sockfd ,const struct msghdr * msg,int flags);

通过套接字发送数据时，还有一个选择。可以调用带有msghdr结构的sendmsg来指定多重缓冲区
传输数据，这个和writev 相似


**recv 函数:接收和read相似**

ssize_t recv (int sockfd ,void *buf ,size_t nbytes ,int flags);


**recvfrom函数 用于无连接的套接字**

ssize_t recvfrom(int sockfd ,void *restrict buf,size_t len, int flags  struct sockaddr * restrict addr ,socklen_t *restrict addrlen);

**recvmsg 接收带缓冲区**

ssize_t recvmsg(int sockfd ,struct msghdr *msg ,int flags);



**套接字选项**
套接字提供两个套接字选项接口来控制套接字行为。
一个接口用来设置选项
一个接口可以查询选项状态。

1.通用选项，工作在所有套接字上
2.在套接字层次管理的选项，但是依赖于下层协议支持。
3.特定于某协议的选项，每个协议独有的。

int setsockopt(int sockfd ,int level,int option, const void *val, socklen_t len);

level 标识了选项应用的协议，
如果是通用套接字层选项，设置层 SOL_SOCKET.
tcp 设置: IPPROTO_TCP
IP 设置: IPPROTO_OP

val
SO_ACCEPTCONN 返回信息知识该套接字是否能被监听
SO_BROADCAST  广播数据报
SO_DEBUG  网络驱动调试功能
SO_SONTROUTE  绕过通常路由
SO_ERROR    返回挂起的套接字错误并清除

val 根据选项不同指向一个数据结构或整数，有时候是开关。

lenp是一个指向整数的指针。
设置缓冲区长度，大于本值，就会被截断



**带外数据( out of band data):**  传输优先级
是一些通信协议锁支持的可选功能。 
对比普通数据，允许更高的优先级。TCP支持带外数据

TCP将带外数据称为:紧急数据 urgent data.  TCP仅支持一个字节的紧急数据。
UDP不支持带外数据
如果通过套接字安排了信号的产生，那么紧急数据被接收时，会发送SIGURG信号。





**非阻塞和异步IO:**
recv函数读没有数据时候会阻塞等待。
输出没有空间时，send会阻塞。

在套接字非阻塞模式下，行为会改变。
非阻塞模式下，这些函数不会阻塞而是会失败，将error设置未ewouldblock或者 eagain。
当这种情况发生时，可以使用poll或者select 判断能否接收或者传输数据。

Single UNIX Specification包含异步IO。
socket套接字有自己的异步处理IO方式。

socket异步IO中，读取数据时候，或写队列中，安排发送信号 SIGIO.

启用异步IO是两步骤过程：
1。建立套接字所有权，这样信号可以被传递到合适的进程。
2。通知套接字当IO操作不会阻塞时发信号。

第一步三种方式:
1.fcntl使用 F_SETOWN
2. ioctl使用 FIOSETOWN
3. ioctl 中使用 SIOCSPGRP

第二部 2种方式
1. fcntl使用 F_SETFL 
2. ioctl 中使用 FIOASYNC





上文中有IO异步机制。 套接字机制有自己的异步IO方式。

异步和非阻塞的套接字。



# 第十七章 高级进程间通信

UNIX域套接字机制，
这种形式的IPC可以在同一计算机系统上运行的两个进程之间传送打开文件描述符。
服务进程可以使它们的打开文件描述符与指定的名字相关联，
同一系统上运行的客户进程可以使用这些名字和服务器进程汇聚。


I.unix域套接字

在同一台计算机上运行进程之间通信。
虽然因特网套接字可用于同一目的，
但是unix域套接字效率更高。unix域套接字仅仅复制数据，他们并不执行协议处理，
不需要添加或删除网络报头，无需计算校验和，不要产生顺序号，无需发送确认报文。


unix域套接字提供流和数据报两种接口。UNIX域数据报服务是可靠的，既不会丢失报文也不会传递出错。

unix域套接字就像是套接字和管道的混合。 
可以使用他们面向网络的域套接字接口或者使用socketpair函数来创建一对无命名的、互相连接的UNIX域套接字。


int socketpair(int domain ,int type ,int protocal ,int sockfd);

虽然接口足够通用，允许socketpair用于其他域，但一般来说操作系统仅对unix域提供支持。



socketpair 函数能创建一对互相连接的套接字，但是每一个都没有名字。意味着无关进程都不能访问。



















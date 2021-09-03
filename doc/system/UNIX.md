

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
日历时间  19700101累计秒数。

进程时间 (CPU时间):时钟时间(wall clock time 进程运行总时间)、用户CPU时间、系统CPU时间
time -p 



系统调用和库函数:
用户>系统调用
用户>库函数>系统调用



I.第一章 UNIX标准及实现


II.unix标准化

III.ISO C
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


III.IEEE POSIX

POSIX:指的是可移植操作系统接口(Portable Operating System Interfacc)。

IEEE 1003.1标准:
是接口interface不是实现。并不区分系统调用和库函数，标准中都被称为函数。

四种：必须头文件、XSI可选头文件、可选头文件、可选接口组合选项码
数:23页

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


III.Single UNIX specification (SUS ,单一unix规范)
是POSIX.1标准的超集(拓展)


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


创建文件:creat函数
open也可以创建


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

I.进程标识
进程ID

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






II.IO多路转接 IO多路复用


select pselect函数:
POSIX 兼容平台


poll函数:
类似select 


**异步IO:**
SystemV 异步AT公司
BSD异步
POSIX异步:aio

aio_read
aio_write

aio_error 获取异步读写状态

aio_return :操作完成前不能调用



readv writev函数:
读写多个非连续缓冲区。撒布读scatter read 聚集写 gather write



**readn writen:**
管道FIFO(网络终端)有两种性质
一次read返回部分数据
一次write返回部署数据



存储映射IO:
memory mapped Io
将磁盘文件映射到存储空间的一个缓冲区






# 第十五章 进程间通信


**管道**


**函数popen pclose**
创建连接进程的管道


协同进程 coprocess:

FIFO :命名管道

XSI  IPC(进程间通信):消息队列、信号量、共享存储器



信号量semaphore:
计数器，用于为多个进程提供共享数据对象的范围。



信号量、记录锁(范围锁)、互斥量



POSIX信号量




# 第十六章 网络IPC(进程间通信) :套接字


socket 套接字是通信端点的抽象。


寻址:
getnameinfo

建立连接:
connect
listen
accept

数据传输:
send

带外数据( out of band data):
对比普通数据，允许更高的优先级。TCP支持带外数据

TCP将带外数据称为:紧急数据 urgent data.  TCP仅支持一个字节的紧急数据。


非阻塞和异步IO:

上文中有IO异步机制。 套接字机制有自己的异步IO方式。



# 第十七章 高级进程间通信













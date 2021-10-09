

# Netty源码剖析与实战


TCP 的keepalive
粘包、半包现象
封帧

三种IO
三种Reactor模式


对象池
对外内存
锁


韩国人 2004年开发
本质:网络应用程序框架
实现:异步、 事件驱动

特性：高性能、可维护、快速开发

用途： 开发服务器和客户端




DMA zero-copy-capable rich byte 



为什么舍近求远，不用JDK nio

Netty做的更多:
支持常用应用层协议
解决传输问题：粘包、半包现象。
指令流量整形。
完善的断连、Idle等异常处理。



Netty做的更好:
1.规避JDK NIO bug
epoll bug  异常唤醒空转导致CPU 100%
linux 2.4 下

netty解决之道：检测问题发生，然后处理。


2.IP_TOS参数 (IP包优先级和Qos选项)使用时抛出异常。
Fixed 半包 JDK 12

netty解决之道:不支持 IP_TOS功能。



3.API更友好更强大
JDK NIo 不友好,功能薄弱, bytebuffer > netty's bytebuf

Threadlocal >> netty's  FastThreadLocal

4.隔离变化、屏蔽细节
隔离JDK NIO变化  nio-> NIO2(aio)
屏蔽JDK NIO实现细节

5.自己直接用JDK nio实现
大概要写1.8W 代码
面对问题 400 open ,关闭了 4347closed的问题  2019/09统计

JDK NIO bug 5646个

netty已维护15年




**横向各种比较**

apache mina
Sun Grizzly
Apple Swift NIO 、 ACE
Cindy 
Tomcat 、Jetty

mina:
同一作者，推荐使用Netty。 mina和netty同一个作者
netty是mina的升级版。


Sun Grizzly:
用的比较少、文档少、更新少。社区小


Apple Swift NIO 、 ACE:
非java语言

Cindy:
生命周期短


Tomcat 、Jetty:
和netty不是同一层的东西。

tomcat为什么不用netty
tomcat是90年代的
netty比较新。


netty同一层没有竞争对手。



**Netty往事**

作者:Trustin lee 韩国 line公司 2004年开发

JBoss

Netty 废弃5.0原因:没有发布正式版。  不要用alpha 版本
2013 12 发布5.0.0.Alpha1
2015 11废弃5.0.0

现有netty 后有 mina 

Netty 和Mina 关系

原有Mina的作者拉 Netty作者 Trustin lee 过来一起搞


Netty是 trustion LEE 自己的。




**7Netty现状和趋势**

社区现状:
https://github.com/netty/netty

github java star 排行
https://github.com/search?l=Java&p=1&q=stars%3A%3E20000&type=Repositories


建议 4.1
4.1 master 支持android  2016年5月
https://github.com/netty/netty/releases/tag/netty-4.1.0.Final

4.1.68.Final  2021年 9月
https://github.com/netty/netty/releases/tag/netty-4.1.68.Final



应用现状:
3W+
统计方法pom中 io.netty:netty-all


经典项目:
数据库 cassandra
大数据: spark hadoop
message queue :rocketMQ

Elasticsearch
gRPC、dubbo、spring5、zookeeper

async-http-client


趋势:
支持更多协议:
codec-dns
codec-haproxy
codec-http
codec-http2
netty-codec-memcache
codec-mqtt
codec-redis
codec-smtp
codec-socks
codec-stomp
codec-xml

更多易用、人性化功能
IP白名单黑名单、流量整形


大纲

Netty 怎么切换三种I/O 模式
Netty 如何支持三种Reactor
TCP 粘包/半包Netty 全搞定
常用的“二次”编解码方式
keepalive 与Idle 监测
Netty 的那些“锁”事
Netty 如何玩转内存使用


Netty 怎么切换三种I/O 模式
什么是经典的三种I/O 模式
Netty 对三种I/O 模式的支持
为什么Netty 仅支持NIO 了？
为什么Netty 有多种NIO 实现？
NIO 一定优于BIO 么？
源码解读Netty 怎么切换I/O 模式？




# I. 什么是经典的三种I/O 模式

生活场景：
当我们去饭店吃饭时：

阻塞BIO(jdk1.4之前):食堂排队打饭模式：排队在窗口，打好才走；
非阻塞NIO(jdk1.4 nio):点单、等待被叫模式：等待被叫，好了自己去端；
异步AIO(jdk1.7 AIO):包厢模式：点单后菜直接被端上桌。




**阻塞和非阻塞:**

菜没做好要不要死等。

阻塞:没有数据传过来，读会一直等待到有数据来。
非阻塞:没数据过来，立刻返回，不等待。




**同步和异步**

菜准备好了谁端。

同步:数据就绪了自己去读
异步:数据准备好了，回调使用者。

09丨源码剖析：Netty对I-O模式的支持


**Netty对这三种模式的支持**
BIO、NIO、AIO

**BIO阻塞IO  :OIO** Deprecated 不建议使用
连接数高的情况下:  阻塞>> 耗资源、效率低
阻塞=等待=占用线程。


Netty中的BIO叫 OIO(old io)Deprecated:

ThreadPerChannelEventLoopGroup
ThreadPerChannelEventLoop
OioServerSocketChannel
OioSocketChannel



**NIO非阻塞IO  :Nio、Epoll、KQueue** 推荐使用

为什么有多种实现NIO:(Common、epoll、kqueue):
common在linux也是使用epoll为什么要单独实现Epoll?

因为更好:
LT（level trigger默认）:模式是只要有数据没有处理就会一直通知下去的
ET（edge trigger边缘）:仅当状态发生变化的时候才获得通知(数据处理一半，再次wait就不返回了，每次必须全部处理完)
当epoll_wait检测到描述符事件发生并将此事件通知应用程序，应用程序必须立即处理该事件。如果不处理，下次调用epoll_wait时，不会再次响应应用程序并通知此事件。



JDK NIO默认是Level Trigger水平触发
Netty默认是 edge trigger 边缘触发，可自由切换。

Netty垃圾回收更少，性能更好。



common通用方式 Nio:(linux下也是epoll)
NioEventLoopGroup
NioEventLoop
NioServerSocketChannel
NioSocketChannel


Linux下推荐Epoll:
EpollEventLoopGroup
EpollEventLoop
EpollServerSocketChannel
EpollSocketChannel


MaxOS/BSD下推荐Kqueue:
KQueueEventLoopGroup
KQueueEventLoop
KQueueServerSocketChannel
KQueueSocketChannel



**AIO阻塞IO  :Aio** Removed 删除
AioEventLoopGroup
AioEventLoop
AioServerSocketChannel
AioSocketChannel

windows下AIO实现成熟、但是很少用来做服务器
Linux服务器但是AIO不成熟。
Linux下AIO 比NIO性能提升不明显。





NIO 比较BIO
BIO 场景:连接数少，并发度低，BIO性能不输于NIO



**10丨Netty如何支持三种Reactor？[更多课程qq 2949651508]**

Netty 如何支持三种Reactor


什么是Reactor 及三种版本
如何在Netty 中使用Reactor 模式
解析Netty 对Reactor 模式支持的常见疑问



生活场景：饭店规模变化
• 一个人包揽所有：迎宾、点菜、做饭、上菜、送客等；
• 多招几个伙计：大家一起做上面的事情；
• 进一步分工：搞一个或者多个人专门做迎宾。

生活场景类比：
• 饭店伙计：线程
• 迎宾工作：接入连接
• 点菜：请求
• 做菜：业务处理
• 上菜：响应
• 送客：断连


1. 一个人包揽所有：迎宾、点菜、做饭、上菜、送客等-> Reactor 单线程
单Reactor 单线程进程


2. 多招几个饭店伙计：大家一起做上面的事情-> Reactor 多线程模式
单Reactor 多线程


3. 进一步分工：搞一个或者多个人专门做迎宾-> 主从Reactor 多线程模式
多Reactor 多进程(Nginx)、 多线程(Memcache 和 Netty)



# I. Reactor 是一种开发模式，模式的核心流程：

三种IO模型  BIO 阻塞  NIO 非阻塞  AIO 异步

**BIO模型  对应的模式是 TPC和PPC** 来一个连接创建一个进程或者线程
Process-Per-Connection
Thread-Per-Connection

PPC 模型是 Process Per Connection** 进程每连接
TPC模型 是 Thread Per Connection** 线程每连接

**NIO 非阻塞模型  对应Reactor模式**

Reactor模式 三种; 1V1  1vs  sVs

监听感兴趣的事件


**AIO 异步模型  对应Proactor模式**





**TPC  Thread-Per-Connection模式**

class Server implements Runnable{
   public void run(){
      //负责监听客户端的 连接请求
      ServerSocket ss= new ServerSocket(port);
      while(!Thread.interrupted()){ 
        //有新的客户端连接就创建新线程处理
        new Thread(new Handler(ss.accept())).start();
       }      
}
static class Handler implements Runnable{
  final Socket socket;
  Handler(Socket s){
     this.socket=s;
  }

   public void run(){
        byte[] input=new byte[MAX_INPUR];
        socket.getInputStream().read(input);
         byte[] output=proces(input);
          socket.getOutputStream().write(output);
         }
}
}




网络编程模型通常有如下几种：
Reactor,
Proactor, 
Asynchronous, 
Completion Token, 
 Acceptor-Connector.

本文主要对最主流的Reactor模型进行介绍。通常网络编程模型处理的主要流程如下 



Reactor, Proactor, Asynchronous, Completion Token, and Acceptor-Connector. 本文主要


##可折叠 开始
<details>

**18 | 单服务器高性能模式：PPC与TPC**  都无法支持高并发场景，几百个连接

磁盘、操作系统、CPU、内存、缓存、网络、编程语言、架构等，每个都有可能影响系统达到高性能，
一行不恰当的 debug 日志，就可能将服务器的性能从 TPS 30000 降低到 8000；
一个 tcp_nodelay 参数，就可能将响应时间从 2 毫秒延长到 40 毫秒。


关键之一就是服务器采取的并发模型

**并发模型**

服务器如何管理连接。服务器如何处理请求

I/O 模型：阻塞、非阻塞、同步、异步。

进程模型：单进程、多进程、多线程。

《UNIX 网络编程》

单服务器高性能模式：PPC 与 TPC


**PPC 模型是 Process Per Connection**  进程每连接

每个连接一个进程，多进程.


主进程，accept后， fork进程进行读写业务处理等。



连接数没那么多的情况，例如数据库服务器、CERN httpd

弊端:
fork代价高
父子进程通信复杂
并发数量有限

prefork 提前创建进程（pre-fork）:稍微快一些，缺点还是一样的。



**TPC模型 是 Thread Per Connection**  线程每连接
TPC和PPC本质一样，PPC没有死锁，多进程互相不影响，稳定性更高。

是指每次有新的连接就新建一个线程去专门处理这个连接的请求。

线程更轻量:与进程相比，线程更轻量级，创建线程的消耗比进程要少得多；
线程共享进程内存空间(通信更容易) :同时多线程是共享进程内存空间的，线程通信相比进程通信更简单。


弊端:

高并发性能问题:
创建线程虽然比创建进程代价低，但并不是没有代价，高并发时（例如每秒上万连接）还是有性能问题。


数据共享互斥死锁问题：
无须进程间通信，但是线程间的互斥和共享又引入了复杂度，可能一不小心就导致了死锁问题。

多线程互相影响，某线程异常导致进程退出:
多线程会出现互相影响的情况，某个线程出现异常时，可能导致整个进程退出（例如内存越界）。

CPU线程调度和切换



prethread(提前创建线程)


**19 | 单服务器高性能模式：Reactor 与 Proactor **

PPC、TPC连接结束进程、线程销毁，资源浪费。

资源复用

阻塞方式read，无法复用，read改成非阻塞。

IO多路复用+线程池  解决PPC、TPC性能不高问题。

select、epoll、kqueue
阻塞在select函数  wait函数等



**Reactor模型** IO多路复用+事件分配
Reactor，中文是“反应堆”。 
事件反应:来了一个事件我就有相应的反应

Reactor 模式也叫 Dispatcher 模式 (调度员)


I/O 多路复用统一监听事件，收到事件后分配（Dispatch）给某个进程。


**Reactor 模式有这三种典型的实现方案：**
单 Reactor 单进程 / 线程。
单 Reactor 多线程。
多 Reactor 多进程 / 线程。

例如，
Java 语言一般使用线程（例如，Netty）。
C 语言使用进程和线程都可以。

java虚拟机是一个进程，虚拟机很多线程。
C语言一般是Reactor单进程，没有必要在进程里多创建线程。


例如
Nginx 使用进程，
Memcache 使用线程。



1. 单 Reactor 单进程 / 线程  :应用场景不多: redis

Reactor单线程: 
java虚拟机是一个进程，虚拟机很多线程。
C语言一般是Reactor单进程，没有必要在进程里多创建线程。


缺点:
只有一个进程，无法发挥多核 CPU 的性能；
只能采取部署多个系统来利用多核 CPU，
但这样会带来运维复杂度，本来只要维护一个系统，
用这种方式需要在一台机器上维护多套系统。

Handler 在处理某个连接上的业务时，
整个进程无法处理其他连接的事件，
很容易导致性能瓶颈。


只适用于业务处理非常快速的场景。
目前比较著名的开源软件中使用单 Reactor 单进程的是 Redis。



2. 单 Reactor 多线程

为了克服Reactor单进程 线程方案的缺点，引入多进程  多线程显而易见，产生:单Reactor多线程


介绍下这个方案:

主线程中，Reactor对象通过select监控连接事件，收到事件后通过dispatch进程分发。

如果是连接建立的事件，则由acceptor处理，acceptor通过accept接受连接，并创建一个Handler来处理连接后续的各种事件。

如果不是连接建立事件，则Reactor会调用连接对应的handler来进行响应。

Handler只负责响应事件，不进行业务处理；Handler通过read读取到数据后，会发给Processor进行业务处理。

Processor会在独立的子线程中完成真正的业务处理，然后将响应结果发给主进程的
handler处理；handler收到响应后通过send将响应结果返回给client



单Reator多线程方案能够充分利用多核多CPU处理能力，但同时也存在下面的问题:

多线程数据共享和访问比较复杂。例如，子线程完成业务处理后，要把结果传递给主线程的
Reactor进行发送，这里涉及共享数据的互斥和保护机制。  以java的NIO为例，
Selector是线程安全的，但是通过Selector.selectKeys()返回的键的集合是非线程安全的，
对selected keys处理必须但线程处理或采取同步措施进行保护。

Reactor承担所有事件的监听和响应，只在主线程中运行，瞬间并发时会成为性能瓶颈。

你可能会发现，我只列出了 单Reactor多线程方案，没有列出 单Reactor多进程方案，
这是什么原因呢？ 主要原因在于如果采用多进程，子进程完成业务处理后，将结果返回给
父进程，并通知父进程发送给哪个client，这是很麻烦的事情。因为父进程只是通过
Reactor监听各个连接上的事件然后进程分配，子进程与父进程通信时并不是一个连接。
如果要将父进程和子进程之间的通信模拟为一个连接，并加入Reacot进行监听，则是比较复杂的。
而采用多线程时，因为多线程时共享数据的，因此线程间通信时非常方便的。虽然要额外
考虑线程间共享数据时的同步问题，但这个复杂的比进程间通信的复杂的要低很多。



3. 多 Reactor 多进程 / 线程 : Nginx、memcahce和netty


为了解决单Reactor多线程的问题，最直观的方法就是将单个Reactor该位多Reactor，这就
产生了第三个方案:多Reactor多进程/线程。


方案详细说明如下:
父进程中mainReactor 对象通过 select 监控连接建立事件，收到事件后通过Acceptor 接收， 将新的连接分配给某个子进程。

子进程的subReactor将 mainReactor分配的连接加入连接队列进行监听，并创建一个handler用于处理 连接的各种事件。

当有新的事件发生时，subReactor会调用连接对应的Handler来进程响应 handler  完成 read> 业务处理 > send的完整业务流程。


多Reactor多线程、多进程的方案看起来 比单reactor多线程要复杂，单实际实现时反而更加简单，主要原因。

父进程和子进程的职责非常明确，父进程只负责接收新连接，子进程负责完成行后续业务处理。

父子进程交互很加单，父进程只需要把新链接传给子进程，子进程无需返回数据。

子进程之间是互相独立的，无须同步共享之类的处理。

Nginx多Reactor多进程 Memcache和netty


Nginx采用的是多Reactor多进程的模式，但方案与标准的多Reactor多进程有差异。
具体差异表现为主进程中仅仅创建了监听端口，并没有创建mainReactor来accept连接，




**Proactor 前摄式**主动  异步IO
Proactor 中文翻译为“前摄器”比较难理解，与其类似的单词是 proactive，含义为“主动的”。

Reactor 是非阻塞同步网络模型，因为真正的read和send操作都需要用户进程同步操作。

这里的"同步"是指用户进程执行read和sent这类IO操作(内核态>用户态),如果把
IO操作改为异步就能进一步提升性能，这就是异步网络模型 Proactor

Reactor来事件我通知你，你处理。
Proactor来事件我处理，处理完通知你。

"我" 是内核
"事件" 就是有新链接、有数据可读、有数据可写 这些事件。
"你" 我们的程序代码


Proactor方案

Proactor Initiator 负责创建Proactor和handler 并将 Proactor 和handler都通过
Asynchronous Operation Processor 注册到内核。

Asynchronous Operation Processor 负责处理注册请求，并完成IO操作。

Asynchronous Operation Processor 完成IO操作后通知 Proactor

Proactor 根据不同的事件类型回调不同的Handler进行业务处理。

Handler 完成业务处理，Handler也可以注册新的Handler到内核进程。


理论上Proactor 比 Reactor 效率要高一些，异步IO能够充分利用DMA 特性，
让IO操作与计算重叠，但要实现真正的异步IO，操作系统需要做大量的工作。
目前Windows下通过IOCP实现了真正的异步IO，而在Linux系统下的AIO

</details>

##可折叠 结束





**如何在Netty 中使用Reactor 模式**



# I. Reactor 单线程模式 netty
EventLoopGroup eventGroup = new NioEventLoopGroup(1); // ## 这里设置1
ServerBootstrap serverBootstrap = new ServerBootstrap();
serverBootstrap.group(eventGroup);


# I. 非主从Reactor 多线程模式  :如果不设置，根据CPU情况设置线程数量。
EventLoopGroup eventGroup = new NioEventLoopGroup();  //根据CPU数量设置
ServerBootstrap serverBootstrap = new ServerBootstrap();
serverBootstrap.group(eventGroup);


# I. 主从Reactor 多线程模式(多Reactor多线程) : 一个负责建立连接  -一个负责其他事件处理
//主 负责监听连接事件
EventLoopGroup bossGroup = new NioEventLoopGroup();
//从 负责其他事件处理
EventLoopGroup workerGroup = new NioEventLoopGroup();
ServerBootstrap serverBootstrap = new ServerBootstrap();
serverBootstrap.group(bossGroup, workerGroup);



# 11丨源码剖析：Netty对Reactor的支持[更多课程qq 2949651508]

解析Netty 对Reactor 模式支持的常见疑问

• Netty 如何支持主从Reactor 模式的？
• 为什么说Netty 的main reactor 大多并不能用到一个线程组，只能线程组里面的一个？
• Netty 给Channel 分配NIO event loop 的规则是什么:  根据不同情况 分配不同算法
• 通用模式的NIO 实现多路复用器是怎么跨平台的


https://github.com/frohoff/jdk8ujdk/blob/master/src/macosx/classes/sun/nio/ch/DefaultSelectorProvider.java

I.
https://github.com/frohoff/jdk8u-jdk/blob/master/src/macosx/classes/sun/nio/ch/DefaultSelectorProvider.java



io.netty.example.echo.EchoServer#main

io.netty.example.echo.EchoServer

**• Netty 如何支持主从Reactor 模式的？**
 EventLoopGroup bossGroup = new NioEventLoopGroup();  // main Reactor
EventLoopGroup workerGroup = new NioEventLoopGroup();
ServerBootstrap b = new ServerBootstrap();
b.group(bossGroup, workerGroup);



**12丨TCP粘包-半包Netty全搞定**



• 什么是粘包和半包？  1500 MTU : 一个消息不到 一个包，  一个消息装不下
• 为什么TCP 应用中会出现粘包和半包现象？
• 解决粘包和半包问题的几种常用方法
• Netty 对三种常用封帧方式的支持
• 解读Netty 处理粘包、半包的源码



为什么TCP 应用中会出现粘包和半包现象？
粘包的主要原因：
• 发送方每次写入数据< 套接字缓冲区大小
• 接收方读取套接字缓冲区数据不够及时



半包的主要原因：
• 发送方写入数据> 套接字缓冲区大小
• 发送的数据大于协议的MTU（Maximum Transmission Unit，最大传输单元），必须拆包




换个角度看：
• 收发
一个发送可能被多次接收，多个发送可能被一次接收
• 传输
一个发送可能占用多个传输包，多个发送可能公用一个传输包






根本原因：
TCP 是流式协议，消息无边界。
UDP 每个包都有界限。(无粘包半包问题)

提醒：UDP 像邮寄的包裹，虽然一次运输多个，但每个包裹都有“界限”，一个一个签收，
所以无粘包、半包问题。























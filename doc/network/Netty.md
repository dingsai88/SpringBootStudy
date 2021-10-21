

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



• 什么是粘包和半包？  1500 MTU : 一个消息不到 一个包，  一个消息装不下  (最大传输单元（Maximum Transmission Unit，MTU）)
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


**解决问题的根本手段：找出消息边界:**

I.TCP改成短连接，一个请求一个连接。（不推荐）
寻找边界方式: 建立连接到释放连接之间的信息是传输信息。
优点:简单
缺点:效率低下

I.封装成帧Framing:固定长度 （不推荐）  FixedLengthFrameDecoder
寻找边界方式: 满足固定长度即可
优点:简单
缺点:浪费空间


I.封装成帧Framing:分隔符 （推荐） DelimiterBasedFrameDecoder
寻找边界方式: 分隔符之间
优点:空间不浪费、相对简单
缺点:内容本身出现分隔符需要转义，所以需要扫描内容



I.封装成帧Framing:固定长度字段存个内容的长度信息 （推荐+） LengthFieldBasedFrameDecoder
寻找边界方式: 先解析固定长度的字段获取长度，然后读取后续内容
优点:精确定位用户信息，内容也不用转义
缺点:长度理论上有限制，需要提前阈值可能的最大长度，定义长度占用的字节数.



I.封装成帧Framing:其他方式
寻找边界方式: 例如Json找括号
优点:
缺点:
衡量实际场景，很多是对现有协议的支持。



Netty 对三种常用封帧方式的支持:

封装成帧Framing(固定长度):FixedLengthFrameDecoder
封装成帧Framing(分割符):DelimiterBasedFrameDecoder
封装成帧Framing(固定长度字段存个内容的长度信息):LengthFieldBasedFrameDecoder:LengthFieldPrepender






**13丨源码剖析：Netty对处理粘包-半包的支持**

• 解码核心工作流程？
• 解码中两种数据积累器（Cumulator）的区别?
• 三种解码器的常用额外控制参数有哪些？






io.netty.handler.codec.ByteToMessageDecoder 解码核心类  三种方式都继承本类 (固定 分割 内容长度)

io.netty.handler.codec.ByteToMessageDecoder.channelRead

public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


{@link DelimiterBasedFrameDecoder}, {@link FixedLengthFrameDecoder}, {@link LengthFieldBasedFrameDecoder},





# **14丨常用的“二次”编解码方式**




**常用的“二次”编解码方式**
• 为什么需要“二次”解码？
• 常用的“二次”编解码方式
• 选择编解码方式的要点
• Protobuf 简介与使用
• 源码解读：Netty 对二次编解码的支持


**为什么需要“二次”编解码？**
假设我们把解决半包粘包问题的常用三种解码器叫一次解码器：



封装成帧Framing(固定长度):
解码:FixedLengthFrameDecoder
编码:不内置

封装成帧Framing(分割符):
解码:DelimiterBasedFrameDecoder
编码:不内置

封装成帧Framing(固定长度字段存个内容的长度信息)::
解码:LengthFieldBasedFrameDecoder
编码:LengthFieldPrepender



那么我们在项目中，除了可选的的压缩解压缩之外，还需要一层解码，因为一次解码的结
果是字节，需要和项目中所使用的对象做转化，方便使用，这层解码器可以称为“二次解
码器”，相应的，对应的编码器是为了将Java 对象转化成字节流方便存储或传输。




**• 一次解码器：ByteToMessageDecoder**  解决粘包 半包
• io.netty.buffer.ByteBuf （原始数据流）-> io.netty.buffer.ByteBuf （用户数据）



**• 二次解码器：MessageToMessageDecoder<I>**
io.netty.buffer.ByteBuf （用户数据）-> Java Object



为什么需要“二次”编解码？   

思考：是不是也可以一步到位？
合并1 次解码（解决粘包、半包）和2 次解码（解决可操作问题）？可以，但是不建议：
• 没有分层，不够清晰;
• 耦合性高，不容易置换方案。





• Java 序列化
• Marshaling
• XML
• JSON
• MessagePack
• Protobuf
• 其他


选择编解码方式的要点
• 空间：编码后占用空间
需要比较不同的数据大小情况
• 时间：编解码速度
需要比较不同的数据大小情况
• 是否追求可读性


• 多语言（Java、C、Python 等）的支持：例如msgpack 的多语言支持：


Google Protobuf 简介与使用

• Protobuf 是一个灵活的、高效的用于序列化数据的协议。
• 相比较XML 和JSON 格式，Protobuf 更小、更快、更便捷。
• Protobuf 是跨语言的，并且自带了一个编译器（protoc），只需要用它进行编译，可以自动生成Java、python、C++ 等代码，不需要再写其他代码。
 
byte[] bytes= person.toByteArray();
 
Person person2=PersonOuterClass.Person.parseFrom(bytes);



源码解读：Netty 对二次编解码的支持
• Protobuf 编解码怎么使用及原理？
• 自带哪些编解码？



#  **16丨keepalive与idle监测**


• 为什么需要keepalive ?
• 怎么设计keepalive ？以TCP keepalive 为例
• 为什么还需要应用层keepalive ?
• Idle 监测是什么？
• 如何在Netty 中开启TCP keepalive 和Idle 检测



**为什么需要keepalive ?**


生活场景:
假设你开了一个饭店，别人电话来订餐，电话通了后，订餐的说了一堆订餐要求，说着说着，对方就不讲话了（可能忘记挂机/出去办事/线路故障等）。

• 这个时候你会一直握着电话等么？
不会

• 如果不会，那你一般怎么去做？
会确认一句“你还在么？”，如果对方没有回复，挂机。这套机制即“keepalive”

https://www.zhihu.com/question/24437644

**HTTP 的 Keep-Alive，**
是由应用层（用户态） 实现的，称为 HTTP 长连接；

**TCP 的 Keepalive，**
是由 TCP 层（内核态） 实现的，称为 TCP 保活机制；


**Tcp Keepalive存在的作用**
 1.探测连接的对端是否存活

在应用交互的过程中，可能存在以下几种情况：
（1）客户端或服务器意外断电，死机，崩溃，重启。
（2）中间网络已经中断，而客户端与服务器并不知道。

2.防止中间设备因超时删除连接相关的连接表

中间设备如防火墙等，
会为经过它的数据报文建立相关的连接信息表，
并为其设置一个超时时间的定时器，
如果超出预定时间，某连接无任何报文交互的话，
中间设备会将该连接信息从表中删除，
在删除后，再有应用报文过来时，
中间设备将丢弃该报文，
从而导致应用出现异常，这个交互的过程大致如下图所示：


怎么设计keepalive ？以TCP keepalive 为例


TCP keepalive 核心参数：
# sysctl -a|grep tcp_keepalive

net.ipv4.tcp_keepalive_time = 7200    没有数据多少秒后发keepalive消息
net.ipv4.tcp_keepalive_intvl = 75     间隔多少秒重发
net.ipv4.tcp_keepalive_probes = 9    重发多少次

当启用（默认关闭）keepalive 时，TCP 在连接没有数据
通过的7200秒后发送keepalive 消息，当探测没有确认时，
按75秒的重试频率重发，一直发9 个探测包都没有确认，就认定连接失效。

所以总耗时一般为：2 小时11 分钟(7200 秒+ 75 秒* 9 次)



• 协议分层，各层关注点不同：
• TCP 层的keepalive 默认关闭，且经过路由等中转设备keepalive 包可能会被丢弃。
• TCP 层的keepalive 时间太长：
默认> 2 小时，虽然可改，但属于系统参数，改动影响所有应用。




提示：HTTP 属于应用层协议，但是常常听到名词“ HTTP Keep-Alive ”指的是对长连接和短连接的选择：
• Connection : Keep-Alive 长连接（HTTP/1.1 默认长连接，不需要带这个header  HTTP1.0还需要特殊指定）
• Connection : Close 短连接




**Idle 监测是什么**

Idle 监测，只是负责诊断，诊断后，做出不同的行为，决定Idle 监测的最终用途：

AIX 	# no -a | grep keep
tcp_keepcnt = 8
tcp_keepidle = 14400
tcp_keepintvl = 150

Linux 	# sysctl -A | grep keep
net.ipv4.tcp_keepalive_intvl = 75
net.ipv4.tcp_keepalive_probes = 9
net.ipv4.tcp_keepalive_time = 7200

FreeBSD 	#sysctl -A | grep net.inet.tcp
net.inet.tcp.keepidle=
net.inet.tcp.keepintvl=


Idle作用1: 发送keepalive :一般用来配合keepalive ，减少keepalive 消息。

• V1：keepalive 消息与服务器正常消息交换完全不关联，定时就发送；
• V2：有其他数据传输的时候，不发送keepalive ，无数据传输超过一定时间，判定 为Idle，再发keepalive 。

Idle作用2:
• 直接关闭连接：
• 快速释放损坏的、恶意的、很久不用的连接，让系统时刻保持最好的状态。
• 简单粗暴，客户端可能需要重连。
实际应用中：结合起来使用。按需keepalive ，保证不会空闲，如果空闲，关闭连接。


**如何在Netty 中开启TCP keepalive 和Idle 检测**


开启keepalive：  默认是不开启的，是系统设置

• Server 端开启TCP keepalive
bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true)
bootstrap.childOption(NioChannelOption.of(StandardSocketOptions.SO_KEEPALIVE), true)
提示：.option(ChannelOption.SO_KEEPALIVE,true) 存在但是无效

开启不同的Idle Check:
ch.pipeline().addLast(“idleCheckHandler", new IdleStateHandler(0, 20, 0, TimeUnit.SECONDS));
io.netty.handler.timeout.IdleStateHandler#IdleStateHandler(long, long, long, java.util.concurrent.TimeUnit)


**源码解读Netty 对TCP keepalive 和三种Idle 检测的支持**

源码解读：
• 设置TCP keepalive 怎么生效的？
• 两种设置keepalive 的方式有什么区别？
• Idle 检测类包（io.netty.handler.timeout）的功能浏览
• 读Idle 检测的原理
• 写Idle 检测原理和参数observeOutput 用途？




**17丨源码剖析：Netty对keepalive与idle监测的支持**



源码解读：
• 设置TCP keepalive 怎么生效的？
• 两种设置keepalive 的方式有什么区别？  普通channel模式  和NIOChannelOption
• Idle 检测类包（io.netty.handler.timeout）的功能浏览
• 读Idle 检测的原理
• 写Idle 检测原理和参数observeOutput 用途？

//两种设置keepalive风格
.childOption(ChannelOption.SO_KEEPALIVE, true)
.childOption(NioChannelOption.SO_KEEPALIVE, true)

io.netty.example.echo.EchoServer#main



ServerBootstrap b = new ServerBootstrap();
b.group(bossGroup, workerGroup)
.channel(NioServerSocketChannel.class)
.option(ChannelOption.SO_BACKLOG, 100)
.handler(new LoggingHandler(LogLevel.INFO))
//两种设置keepalive风格
.childOption(ChannelOption.SO_KEEPALIVE, true)
.childOption(NioChannelOption.SO_KEEPALIVE, true)

              //切换到unpooled的方式之一
             .childOption(ChannelOption.ALLOCATOR, UnpooledByteBufAllocator.DEFAULT)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline p = ch.pipeline();
                     if (sslCtx != null) {
                         p.addLast(sslCtx.newHandler(ch.alloc()));
                     }
                     p.addLast(new LoggingHandler(LogLevel.INFO));
                     p.addLast(serverHandler);
                 }
             });

io.netty.handler.timeout.IdleState









**18丨Netty的那些“锁”事**
I.
# 

I. 分析同步问题的核心三要素
I. 锁的分类
I. Netty 玩转锁的五个关键点：
II. 在意锁的对象和范围-> 减少粒度
II. 注意锁的对象本身大小-> 减少空间占用
II. 注意锁的速度-> 提高速度
II. 不同场景选择不同的并发类-> 因需而变
II. 衡量好锁的价值-> 能不用则不用


**分析同步问题的核心三要素**
• 原子性：“并无一气呵成，岂能无懈可击”
• 可见性：“你做的改变，别人看不见”
• 有序性：“不按套路出牌”



**锁的分类**
• 对竞争的态度：乐观锁（java.util.concurrent 包中的原子类）与悲观锁（Synchronized)
• 等待锁的人是否公平而言：公平锁new ReentrantLock (true)与非公平锁new ReentrantLock ()
• 是否可以共享：共享锁与独享锁：ReadWriteLock ，其读锁是共享锁，其写锁是独享锁


**II.在意锁的对象和范围-> 减少粒度**
**II.注意锁的对象本身大小-> 减少空间占用**

Atomic long VS long：

前者是一个对象，包含对象头（object header）以用来保存hashcode、lock 等信息，32 位系统占用8字节；64 位系统占16 字节，所以在64 位系统情况下：

• volatile long = 8 bytes
• AtomicLong = 8 bytes （volatile long）+ 16bytes （对象头）+ 8 bytes (引用) = 32 bytes
至少节约24 字节!


结论：Atomic* objects -> Volatile primary type + Static Atomic*FieldUpdater





**II.注意锁的速度-> 提高并发性**

例1：记录内存分配字节数等功能用到的LongCounter
（io.netty.util.internal.PlatformDependent#newLongCounter() ）

高并发时：java.util.concurrent.atomic.AtomicLong -> java.util.concurrent.atomic.LongAdder (JDK1.8)

结论： 及时衡量、使用JDK 最新的功能


例2：曾经根据不同情况，选择不同的并发包实现：JDK < 1.8 考虑
ConcurrentHashMapV8（ConcurrentHashMap 在JDK8 中的版本）

jdk>=8时   LongAdder
jdk老版本使用 AtomicLong




**II.不同场景选择不同的并发包-> 因需而变**
例1：关闭和等待关闭事件执行器（Event Executor）：

Object.wait/notify -> CountDownLatch

io.netty.util.concurrent.SingleThreadEventExecutor#threadLock：

例2：Nio Event loop中负责存储task的Queue

Jdk’s LinkedBlockingQueue (MPMC) -> jctools’ MPSC
io.netty.util.internal.PlatformDependent.Mpsc#newMpscQueue(int)：


**衡量好锁的价值-> 能不用则不用**

生活场景：
饭店提供了很多包厢，服务模式：
• 一个服务员固定服务某几个包厢模式；          一个线程服务多个channel
• 所有的服务员服务所有包厢的模式。            多个线程服务全部channel

表面上看，效率后者高，但实际上它避免了服务员之间的沟通（上下文切换）等 开销，避免客人和服务员之间到处乱串，管理简单。


局部串行：Channel 的I/O 请求处理 Pipeline 是串行的

多个线程EventLoop 并行，  每个EventLoop服务多个Channel

EventLoop(Thread) >>Channel 多个
EventLoop(Thread) >>Channel 多个


Netty 应用场景下： 对比
局部串行(EventLoop>>Channel)+ 整体并行(多个EventLoop)   >>性能高于>>    一个队列+ 多个线程模式:
• 降低用户开发难度、逻辑简单、提升处理性能
• 避免锁带来的上下文切换和并发保护等额外开销


避免用锁：用ThreadLocal 来避免资源争用，例如Netty 轻量级的线程池实现

io.netty.util.Recycler#threadLocal


小结
• 分析同步问题的核心三要素：分析多线程问题的关键
• 浏览了锁的分类
• 结合代码分析了Netty 玩转锁的五个关键点，具有普适性





**19丨Netty如何玩转内存使用**

Netty 如何玩转内存使用


• 内存使用技巧的目标
• Netty 内存使用技巧- 减少对像本身大小
• Netty 内存使用技巧- 对分配内存进行预估
• Netty 内存使用技巧- Zero-Copy
• Netty 内存使用技巧- 堆外内存
• Netty 内存使用技巧- 内存池



**内存使用技巧的目标**
目标：
• 内存占用少（空间）
• 应用速度快（时间）
对Java 而言：减少Full GC 的STW（Stop the world）时间


**Netty 内存使用技巧- 减少对像本身大小 （空间）**

例1：用基本类型就不要用包装类：


例2: 应该定义成类变量的不要定义为实例变量：

• 一个类-> 一个类变量
• 一个实例-> 一个实例变量
• 一个类-> 多个实例
• 实例越多，浪费越多。


例3: Netty 中结合前两者：
io.netty.channel.ChannelOutboundBuffer#incrementPendingOutboundBytes(long, boolean)
统计待写的请求的字节数

AtomicLong -> volatile long + static AtomicLongFieldUpdater






**Netty 内存使用技巧- 对分配内存进行预估**


例1：对于已经可以预知固定size 的HashMap避免扩容
可以提前计算好初始size或者直接使用
com.google.common.collect.Maps#newHashMapWithExpectedSize


例2：Netty 根据接受到的数据动态调整（guess）下个要分配的Buffer 的大小。可参考
io.netty.channel.AdaptiveRecvByteBufAllocator


**Netty 内存使用技巧- Zero-Copy** 零拷贝 
 
例1：使用逻辑组合，代替实际复制。
例如CompositeByteBuf：
io.netty.handler.codec.ByteToMessageDecoder#COMPOSITE_CUMULATOR


例2：使用包装，代替实际复制。

byte[] bytes = data.getBytes();
ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);



例3：调用JDK 的Zero-Copy 接口。
Netty 中也通过在DefaultFileRegion 中包装了NIO 的FileChannel.transferTo() 方法实
现了零拷贝：io.netty.channel.DefaultFileRegion#transferTo




**Netty 内存使用技巧- 堆外内存**

堆外内存生活场景：

I.夏日，小区周边的烧烤店铺，人满为患坐不下，店家常常怎么办？
  解决思路：店铺门口摆很多桌子招待客人。

II.店内-> JVM 内部-> 堆（heap) + 非堆（non heap）
II.店外-> JVM 外部-> 堆外（off heap）

• 优点：
• 更广阔的“空间”，缓解店铺内压力-> 破除堆空间限制，减轻GC 压力
• 减少“冗余”细节（假设烧烤过程为了气氛在室外进行：烤好直接上桌：vs 烤好还 要进店内）-> 避免复制


• 缺点：
• 需要搬桌子-> 创建速度稍慢
• 受城管管、风险大-> 堆外内存受操作系统管理



**Netty 内存使用技巧- 内存池**
内存池生活场景：

点菜单的演进：
• 一张纸：一桌客人一张纸
• 点菜平板：循环使用

为什么引入对象池：
• 创建对象开销大
• 对象高频率创建且可复用
• 支持并发又能保护系统
• 维护、共享有限的资源

如何实现对象池？
• 开源实现：Apache Commons Pool
• Netty 轻量级对象池实现io.netty.util.Recycler








**20丨源码解析：Netty对堆外内存和内存池的支持**

源码解读Netty 内存使用
源码解读：
问题1: 怎么从堆外内存切换堆内使用？以UnpooledByteBufAllocator为例
• 方法1：参数设置
io.netty.noPreferDirect = true;
• 方法2：传入构造参数false
ServerBootstrap serverBootStrap = new ServerBootstrap();
UnpooledByteBufAllocator unpooledByteBufAllocator = new UnpooledByteBufAllocator(false);
serverBootStrap.childOption(ChannelOption.ALLOCATOR, unpooledByteBufAllocator)




问题2:堆外内存的分配本质？
ByteBuffer.allocateDirect(initialCapacity)



问题3:内存池/非内存池的默认选择及切换方式？
io.netty.channel.DefaultChannelConfig#allocator

默认选择：安卓平台-> 非pooled 实现，其他-> pooled 实现。
• 参数设置：io.netty.allocator.type = unpooled;
• 显示指定：serverBootStrap.childOption(ChannelOption.ALLOCATOR, UnpooledByteBufAllocator.DEFAULT)





问题4:内存池实现（以PooledDirectByteBuf 为例）
io.netty.buffer.PooledDirectByteBuf
核心要点：有借有还，避免遗忘。

小结
• 介绍内存使用技巧的目标。
• Netty 内存五种使用技巧，也适用与其他Java 应用程序。





# I. 21丨Netty代码编译与总览 


编译Netty 常遇问题
Netty 源码核心包速览





# I. 22丨源码剖析：启动服务





主线:自己的线程mainThread  + boss thread

**our thread自己的线程**

• 创建selector
• 创建server socket channel
• 初始化server socket channel
• 给server socket channel 从boss group 中选择一个NioEventLoop


**boss thread**

• 将server socket channel 注册到选择的NioEventLoop 的selector(上一步创建的selector)
• 绑定地址启动
• 注册接受连接事件（OP_ACCEPT）到selector 上


自己创建 selector ，将selector 注册到 NioEventLoop 绑定地址启动。


• 启动服务的本质：
Selector selector = sun.nio.ch.SelectorProviderImpl.openSelector()
ServerSocketChannel serverSocketChannel = provider.openServerSocketChannel()
selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this);
javaChannel().bind(localAddress, config.getBacklog());
selectionKey.interestOps(OP_ACCEPT);


• Selector 是在new NioEventLoopGroup()（创建一批NioEventLoop）时创建。
• 第一次Register 并不是监听OP_ACCEPT，而是0:
selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this) 。

• 最终监听OP_ACCEPT 是通过bind 完成后的fireChannelActive() 来触发的。
• NioEventLoop 是通过Register 操作的执行来完成启动的。
• 类似ChannelInitializer，一些Hander 可以设计成一次性的，用完就移除，例如授权。



# 23丨源码剖析：构建连接

• 主线
• 源码演示
• 知识点


主线:boss thread + worker thread

boss thread
• NioEventLoop 中的selector 轮询创建连接事件（OP_ACCEPT）:
• 创建socket channel
• 初始化socket channel 并从worker group 中选择一个NioEventLoop


worker thread
• 将socket channel 注册到选择的NioEventLoop 的selector
• 注册读事件（OP_READ）到selector 上




• 接受连接本质：

selector.select()/selectNow()/select(timeoutMillis) 发现OP_ACCEPT 事件，处理：
• SocketChannel socketChannel = serverSocketChannel.accept()
• selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this);
• selectionKey.interestOps(OP_READ);


• 创建连接的初始化和注册是通过pipeline.fireChannelRead 在ServerBootstrapAcceptor 中完成的。
• 第一次Register 并不是监听OP_READ ，而是0 ：
selectionKey = javaChannel().register(eventLoop().unwrappedSelector(), 0, this) 。
• 最终监听OP_READ 是通过“Register”完成后的fireChannelActive
（io.netty.channel.AbstractChannel.AbstractUnsafe#register0中）来触发的
• Worker’s NioEventLoop 是通过Register 操作执行来启动。
• 接受连接的读操作，不会尝试读取更多次（16次）。




# 24丨源码剖析：接收数据

• 读数据技巧
• 主线
• 源码演示
• 知识点

1 自适应数据大小的分配器（AdaptiveRecvByteBufAllocator）：
发放东西时，拿多大的桶去装？小了不够，大了浪费，所以会自己根据实际装的情况猜一猜下次情况， 从而决定下次带多大的桶。


2 连续读（defaultMaxMessagesPerRead）：
发放东西时，假设拿的桶装满了，这个时候，你会觉得可能还有东西发放，所以直接拿个新桶等着装，
而不是回家，直到后面出现没有装上的情况或者装了很多次需要给别人一点机会等原因才停止，回家。



主线

• 多路复用器（ Selector ）接收到OP_READ 事件



• 处理OP_READ 事件：NioSocketChannel.NioSocketChannelUnsafe.read()

worker thread
• 分配一个初始1024 字节的byte buffer 来接受数据
• 从Channel 接受数据到byte buffer
• 记录实际接受数据大小，调整下次分配byte buffer 大小
• 触发pipeline.fireChannelRead(byteBuf) 把读取到的数据传播出去
• 判断接受byte buffer 是否满载而归：是，尝试继续读取直到没有数据或满16 次；否，结束本轮读取，等待下次OP_READ 事件



知识点
• 读取数据本质：sun.nio.ch.SocketChannelImpl#read(java.nio.ByteBuffer)
• NioSocketChannel read() 是读数据， NioServerSocketChannel read() 是创建连接
• pipeline.fireChannelReadComplete(); 一次读事件处理完成
pipeline.fireChannelRead(byteBuf); 一次读数据完成，一次读事件处理可能会包含多次读数据操作。
• 为什么最多只尝试读取16 次？“雨露均沾”
• AdaptiveRecvByteBufAllocator 对bytebuf 的猜测：放大果断，缩小谨慎（需要连续2 次判断）



# 25丨源码剖析：业务处理 

主线


• 多路复用器（ Selector ）接收到OP_READ 事件
• 处理OP_READ 事件：NioSocketChannel.NioSocketChannelUnsafe.read()
• 分配一个初始1024 字节的byte buffer 来接受数据
• 从Channel 接受数据到byte buffer
• 记录实际接受数据大小，调整下次分配byte buffer 大小
• 触发pipeline.fireChannelRead(byteBuf) 把读取到的数据传播出去
• 判断接受byte buffer 是否满载而归：是，尝试继续读取直到没有数据或满16 次；否，结束本轮读取，等待下次OP_READ事件




知识点
• 处理业务本质：数据在pipeline 中所有的handler 的channelRead() 执行过程
Handler 要实现io.netty.channel.ChannelInboundHandler#channelRead (ChannelHandlerContext ctx,
Object msg)，且不能加注解@Skip 才能被执行到。
中途可退出，不保证执行到Tail Handler。
• 默认处理线程就是Channel 绑定的NioEventLoop 线程，也可以设置其他：
pipeline.addLast(new UnorderedThreadPoolEventExecutor(10), serverHandler)



#26丨源码剖析：发送数据

源码剖析：发送数据
• 写数据三种方式
• 写数据要点
• 主线
• 源码演示
• 知识点



**写数据三种方式:write、flush、 writeAndFlush**
揽收到仓库write：写到一个buffer
从仓库发货flush: 把buffer 里的数据发送出去
揽收到仓库并立马发货（加急件） writeAndFlush：写到buffer，立马发送



揽收与发货之间有个缓冲的仓库Write 和Flush 之间有个 ChannelOutboundBuffer



**写数据要点**

1 对方仓库爆仓时，送不了的时候，会停止送，协商等电话通知什么时候好了，再送。
Netty 写数据，写不进去时，会停止写，然后注册一个OP_WRITE 事件，来通知什么时候可以写进去了再写。


2 发送快递时，对方仓库都直接收下，这个时候再发送快递时，可以尝试发送更多的快递试试，这样效果更好。
Netty 批量写数据时，如果想写的都写进去了，接下来的尝试写更多（调整maxBytesPerGatheringWrite）

3 发送快递时，发到某个地方的快递特别多，我们会连续发，但是快递车毕竟有限，也会考虑下其他地方。
Netty 只要有数据要写，且能写的出去，则一直尝试，直到写不出去或者满16 次（writeSpinCount）。

4 揽收太多，发送来不及时，爆仓，这个时候会出个告示牌：收不下了，最好过2 天再来邮寄吧。
Netty 待写数据太多，超过一定的水位线（writeBufferWaterMark.high()），会将可写的标志位改成
false ，让应用端自己做决定要不要发送数据了。



• Write - 写数据到buffer ：
ChannelOutboundBuffer#addMessage


• Flush - 发送buffer 里面的数据：
AbstractChannel.AbstractUnsafe#flush
• 准备数据: ChannelOutboundBuffer#addFlush
• 发送：NioSocketChannel#doWrite



• 写的本质：
• Single write: sun.nio.ch.SocketChannelImpl#write(java.nio.ByteBuffer)
• gathering write：sun.nio.ch.SocketChannelImpl#write(java.nio.ByteBuffer[], int, int)


• 写数据写不进去时，会停止写，注册一个OP_WRITE 事件，来通知什么时候可以写进去了。
• OP_WRITE 不是说有数据可写，而是说可以写进去，所以正常情况，不能注册，否则一直触发。


• 批量写数据时，如果尝试写的都写进去了，接下来会尝试写更多（maxBytesPerGatheringWrite）。
• 只要有数据要写，且能写，则一直尝试，直到16 次（writeSpinCount），写16 次还没有写完，就直 接schedule 一个task 来继续写，而不是用注册写事件来触发，更简洁有力。
• 待写数据太多，超过一定的水位线（writeBufferWaterMark.high()），会将可写的标志位改成false ， 让应用端自己做决定要不要继续写。
• channelHandlerContext.channel().write() ：从TailContext 开始执行；
channelHandlerContext.write() : 从当前的Context 开始。



# 27丨源码剖析：断开连接 

• 主线
• 源码演示
• 知识点


I. 多路复用器（Selector）接收到OP_READ 事件:

I. 处理OP_READ 事件：NioSocketChannel.NioSocketChannelUnsafe.read()：

II.接受数据
II. 判断接受的数据大小是否< 0 , 如果是，说明是关闭，开始执行关闭：
III. 关闭channel（包含cancel 多路复用器的key）。
III. 清理消息：不接受新信息，fail 掉所有queue 中消息。
III. 触发fireChannelInactive 和fireChannelUnregistered 。

• 关闭连接本质：
• java.nio.channels.spi.AbstractInterruptibleChannel#close
• java.nio.channels.SelectionKey#cancel


• 要点：
• 关闭连接，会触发OP_READ 方法。读取字节数是-1 代表关闭。
• 数据读取进行时，强行关闭，触发IO Exception，进而执行关闭。
• Channel 的关闭包含了SelectionKey 的cancel 。








# 28丨源码剖析：关闭服务

源码剖析：关闭服务

bossGroup.shutdownGracefully();
workerGroup.shutdownGracefully();

关闭所有Group 中的NioEventLoop:


• 修改NioEventLoop 的State 标志位
• NioEventLoop 判断State 执行退出


I.关闭服务本质：

II.关闭所有连接及Selector ：

III.java.nio.channels.Selector#keys
• java.nio.channels.spi.AbstractInterruptibleChannel#close
• java.nio.channels.SelectionKey#cancel
III.selector.close()

I.关闭所有线程：退出循环体for (;;)



**• 关闭服务要点：**


• 优雅（DEFAULT_SHUTDOWN_QUIET_PERIOD）
• 可控（DEFAULT_SHUTDOWN_TIMEOUT）
• 先不接活，后尽量干完手头的活（先关boss 后关worker：不是100%保证）





# 29丨遍写网络应用程序的基本步骤

I.需求分析
I.定义业务数据结构
I.实现业务逻辑


I.选择传输协议

I.定义传输信息结构
I.选择编码器
II.数据本身编解码
II.压缩等编解码
II.粘包半包处理编解码

I.实现所有的编解码
I.编写应用程序
II.服务器
II.客户端

I.测试




# 30丨案例介绍和数据结构设计


31丨实现服务器端编解码 

io.netty.example.study.server.Server



**36丨Netty编码中易错点解析**


• LengthFieldBasedFrameDecoder 中initialBytesToStrip 未考虑设置
• ChannelHandler 顺序不正确
• ChannelHandler 该共享不共享，不该共享却共享
• 分配ByteBuf ：分配器直接用ByteBufAllocator.DEFAULT 等，而不是采用ChannelHandlerContext.alloc()
• 未考虑ByteBuf 的释放
• 错以为ChannelHandlerContext.write(msg) 就写出数据了
• 乱用ChannelHandlerContext.channel().writeAndFlush(msg)




**37丨调优参数：调整System参数夯实基础  linux参数**



第五章：Netty 实战进阶把“玩具”变成产品


I. Linux 系统参数
例如：/proc/sys/net/ipv4/tcp_keepalive_time


I. Netty 支持的系统参数：
例如：serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
• SocketChannel -> .childOption
• ServerSocketChannel -> .option

I.Linux 系统参数
• 进行TCP 连接时，系统为每个TCP 连接创建一个socket 句柄，也就是一个文件句柄，但是Linux
对每个进程打开的文件句柄数量做了限制，如果超出：报错“Too many open file”。

ulimit -n [xxx]

注意：ulimit 命令修改的数值只对当前登录用户的目前使用环境有效，系统重启或者用户退出后就会失效，
所以可以作为程序启动脚本一部分，让它程序启动前执行。


**I. Netty 支持的系统参数(ChannelOption.[XXX] ) 讨论：**

II.不考虑UDP :
• IP_MULTICAST_TTL

II.不考虑OIO 编程：
• ChannelOption<Integer> SO_TIMEOUT = ("SO_TIMEOUT");


**II.SocketChannel（7个： childOption）:**


Netty系统相关参数     功能        默认值
**SO_SNDBUF TCP**   
数据发送缓冲区大小        /proc/sys/net/ipv4/tcp_wmem: 4K   [min, default, max]动态调整

**SO_RCVBUF TCP**   
数据接受缓冲区大小         /proc/sys/net/ipv4/tcp_rmem: 4K

**SO_KEEPALIVE** 
TCP 层keepalive              默认关闭


**SO_REUSEADDR**

地址重用，解决“Address already in use” 常用开启场景：多网卡（IP）绑定相同端口；让关闭连接释放的端 口更早可使用 默认不开启 
澄清：不是让TCP 绑定完全相同IP + Port 来重复启动

**SO_LINGER**    
关闭Socket的延迟时间，默认禁用该功能，socket.close()方法立即返回          默认不开启

**IP_TOS**
设置IP 头部的Type-of-Service 字段，用于描述IP 包的优先级和QoS 选项。例如倾向于延时还是吞吐量？

1000 - minimize delay
0100 - maximize throughput
0010 - maximize reliability
0001 - minimize monetary cost
0000 - normal service （默认值）
The value of the socket option is a hint. An
implementation may ignore the value, or ignore specific values.


**TCP_NODELAY** 
设置是否启用Nagle算法：用将小的碎片数据连接成更大的报文来提高发送效率。

False:  如果需要发送一些较小的报文，则需要禁用该算法



**II.ServerSocketChannel（3个： option ）:**

Netty系统相关参数  功能   备注

**SO_RCVBUF**  创建时就设置，
为Accept 创建的socket channel 设置SO_RCVBUF：
“Sets a default proposed value for the SO_RCVBUF option for sockets acceptedfrom this ServerSocket”

为什么有SO_RCVBUF 而没有SO_SNDBUF ？


**SO_REUSEADDR** 
是否可以重用端口

默认false

**SO_BACKLOG**
最大的等待连接数量

Netty 在Linux下值的获取（io.netty.util.NetUtil）：
• 先尝试：/proc/sys/net/core/somaxcon 配置文件
• 然后尝试：sysctl
• 最终没有取到，用默认：128

使用方式：javaChannel().bind(localAddress, config.getBacklog());

**IP_TOS**



**I.调优参数： 权衡Netty 核心参数**

II.参数调整要点：
• option/childOption 傻傻分不清：不会报错，但是会不生效；
• 不懂不要动，避免过早优化。
• 可配置（动态配置更好）


II.需要调整的参数：
• 最大打开文件数
（酌情处理）: TCP_NODELAY 是否启用Nagle算法、 SO_BACKLOG 最大的等待连接数量、 SO_REUSEADDR 是否可以重用端口




II.ChannelOption
• childOption(ChannelOption.[XXX], [YYY])
• option(ChannelOption.[XXX], [YYY])

II.System property
• -Dio.netty.[XXX] = [YYY]











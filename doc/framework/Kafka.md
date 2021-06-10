



**I.Kafka核心技术与实战（HTML版 -完结）**

**根据维基百科的定义:**
消息引擎系统是一组规范。企业利用这组规范在不同系统之间传递语义准确的消息，实现松耦合的异步式数据传递。



**II.重要模块**
消息传输的对象+消息传递模型

**III.消息引擎传输的对象是消息:纯二进制的字节序列**

**III.如何传输消息:两种消息引擎模型**

**1.点对点模型(消息队列模型)**(电话客服)
A 发送的消息只能被系统 B 接收

**2.发布 / 订阅模型**(报纸订阅)
它有一个主题（Topic）的概念
发布者（Publisher）
订阅者（Subscriber）

多个发布者可以向一个Topic发送消息
一个Topic可以被多个订阅者消费

**消息引擎系统和JMS(java message service)关系**
JMS 接口规范

**主要作用削峰填谷**
上游大流量有两种处理方式:
1.上游限流:影响上游的TPS 
2.消息队列:解决上下游系统 TPS 的错配


Apache Kafka 是一款开源的消息引擎系统:(忽视了这类系统引以为豪的消息传递属性)


Kafka 的选择：它使用的是纯二进制的字节序列。当然消息还是结构化的，只是在使用之前都要将其转换成二进制的字节序列。




----------------------------------------------------------------------------------------------

**02 | 一篇文章带你快速搞定Kafka术语** 


**II.Clients客户端**生产者+消费者
生产者和消费者统称为客户端（Clients）。


Producer> 发送Topic >Consumer


**II.服务器端**多个Broker经纪人
Broker 负责接收和处理客户端发送过来的请求，以及对消息进行持久化

**III.高可用:** Broker运行在多台;副本的备份机制
1.Broker 分散运行在不同的机器上，一个挂了其他的可用
2.备份机制（Replication） ：相同的数据拷贝到多台机器上，而这些相同的数据拷贝在 Kafka 中被称为副本（Replica）。
副本的数量是可以配置的，这些副本保存着相同的数据，但却有不同的角色和作用。

**Kafka 定义了两类副本：** 分区分片级别
领导者副本（Leader Replica）:与客户端程序进行交互
追随者副本（Follower Replica）:被动地追随领导者副本而已，不能与外界进行交互

MySQL 的从库是可以处理读操作的，但是在 Kafka 中追随者副本不会对外提供服务。

**Master-Slave>Leader-Follower**

**副本工作机制:**
领导者副本:1.生产者总是向领导者副本写消息；2.消费者总是从领导者副本读消息。
追随者副本:向领导者副本发送请求，请求领导者把最新生产的消息发给它，这样它能保持与领导者的同步。 


**III.高性能:**
伸缩性即所谓的 Scalability 单台 Broker 机器都无法容纳

**分区（Partitioning）：** 分片、分区
把数据分割成多份保存在不同的 Broker 上。

Kafka 中的分区机制指的是将每个topic主题划分成多个分区（Partition），
0,1平均分区
hash平均分区等


**副本和分区**
副本是在分区这个层级定义的

0分区:1个领导者副本+N个追随者副本
1分区:1个领导者副本+N个追随者副本

**位移（Offset）** (分区内的消息位置，它是不变的)
生产者向分区写入消息，每条消息在分区中的位置信息由一个叫位移（Offset）的数据来表征。
分区位移总是从 0 开始，假设一个生产者向一个空分区写入了 10 条消息，那么这 10 条消息的位移依次是 0、1、2、…、9。



**II.持久化** 一个log物理文件有多个日志段

Kafka 使用消息日志（Log）来保存数据
一个日志就是磁盘上一个只能追加写（Append-only）消息的物理文件。
因为只能追加写入，故避免了缓慢的随机 I/O 操作，改为性能较好的顺序 I/O 写操作，这也是实现 Kafka 高吞吐量特性的一个重要手段


**III.日志段（Log Segment）机制:**
日志分成多个日志段，消息被追加写到当前最新的日志段中，当写满了一个日志段后，Kafka 会自动切分出一个新的日志段，并将老的日志段封存起来。
Kafka 在后台还有定时任务会定期地检查老的日志段是否能够被删除，从而实现回收磁盘空间的目的。


**III.Consumer Group:消费者组**
多个消费者实例共同组成一个组来消费一组主题。

**每个分区**都只会被组内的一个消费者实例消费，其他消费者实例不能消费它

提升消费者端的吞吐量
消费者实例（Consumer Instance）:可以是运行消费者应用的进程，也可以是一个线程

“重平衡”（Rebalance）
组内某个实例挂掉了，Kafka 能够自动检测到，然后把这个 Failed 实例之前负责的分区转移给其他活着的消费者

**消费者位移（Consumer Offset）:**
字段记录它当前消费到了分区的哪个位置上。

每个消费者有着自己的消费者位移

**两个位移的区别**
上文的位移是写入时，在组内的相对位置：
消费者位移，是每个消费者记录的消费位置

II.名词


• 消息：Record。Kafka 是消息引擎嘛，这里的消息就是指 Kafka 处理的主要对象。
• 主题：Topic。主题是承载消息的逻辑容器，在实际使用中多用来区分具体的业务。
• 分区：Partition。一个有序不变的消息序列。每个主题下可以有多个分区。
• 消息位移：Offset。表示分区中每条消息的位置信息，是一个单调递增且不变的值。
• 副本：Replica。Kafka 中同一条消息能够被拷贝到多个地方以提供数据冗余，这些地方就是所谓的副本。副本还分为领导者副本和追随者副本，各自有不同的角色划分。副本是在分区层级下的，即每个分区可配置多个副本实现高可用。
• 生产者：Producer。向主题发布新消息的应用程序。
• 消费者：Consumer。从主题订阅新消息的应用程序。
• 消费者位移：Consumer Offset。表征消费者消费进度，每个消费者都有自己的消费者位移。
• 消费者组：Consumer Group。多个消费者实例共同组成的一个组，同时消费多个分区以实现高吞吐。
• 重平衡：Rebalance。消费者组内某个消费者实例挂掉后，其他消费者实例自动重新分配订阅主题分区的过程。Rebalance 是 Kafka 消费者端实现高可用的重要手段。





![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/生产者-消费者组-leaderFollower.png)



--------------------------------------------------------------

**03 | Kafka只是消息引擎系统吗？**  分布式流处理平台 Distributed Streaming Platform

消息引擎系统+分布式流处理平台

Apache Kafka 是消息引擎系统，也是一个分布式流处理平台  （Distributed Streaming Platform）。

**Kafka Streams**
实现精确一次（Exactly-once）处理语义的实时流处理平台

LinkedIn开发   ActiveMQ 来解决这些问题 > Kafka。 

大数据工程领域，Kafka 在承接上下游、串联数据流管道方面发挥了重要的作用。


**实时流处理平台**
Apache Kafka 
Apache Storm
Apache Spark 
Apache Flink




第一点是更容易实现端到端的正确性（Correctness）  。

要实现正确性和提供能够推导时间的工具。实现正确性是流处理能够匹敌批处理的基石  。



**04  我应该选择哪种Kafka？[更多课程qq 2949651508]**


Apache Kafka
仅仅提供最最基础的组件
Kafka Streams:Kafka Connect 而言，社区版 Kafka 只提供一种连接器

开源的监控框架可以帮助用于监控 Kafka（比如 Kafka manager）。


I.Apache Kafka
监控:Kafka manager
2. Confluent Kafka
   需要用到 Kafka 的一些高级特性，那么推荐你使用 Confluent Kafka
   

3. Cloudera/Hortonworks Kafka 



**05  聊聊Kafka的版本号**


都运行在JVM：  Java VS Scala(.class)

Kafka 服务器端的代码完全由 Scala 语言编写
Kafka 新版客户端代码完全由 Java 语言编


社区来了一批JAVA程序员，老的Scala程序员隐退



**06  Kafka线上集群部署方案怎么做？**

-------------------------------------
操作系统、磁盘、磁盘容量、带宽


**I.操作系统**
JVM 系的大数据框架
目前常见的操作系统有 3 种：Linux、Windows 和 macOS


**Linux**
1.I/O 模型的使用
2.数据网络传输效率
3.社区支持度 

**II.I/O 模型的使用**

阻塞式 I/O、非阻塞式 I/O、I/O 多路复用、信号驱动 I/O 和异步 I/O

Socket 对象的阻塞模式和非阻塞模式就对应于前两种模型

Linux 中的系统调用 select 函数就属于 I/O 多路复用模型

epoll 系统调用则介于第三种和第四种模型之间；


异步 I/O很少有 Linux 系统支持  Windows 系统 IOCP 线程模型

**Kafka 客户端底层**
selector 在 Linux 上的实现机制是 epoll
Kafka 部署在 Linux 上是有优势的，因为能够获得更高效的 I/O 性能。 



**II.数据网络传输效率**

Kafka 生产和消费的消息都是通过网络传输的,而消息保存在磁盘
Kafka 需要在磁盘和网络间进行大量数据传输。

Linux，你肯定听过零拷贝（Zero Copy）技术
Windows  Java 8才能“享受”

Linux 部署 Kafka 能够享受到零拷贝技术所带来的快速数据传输特性


**II.社区支持度** 

 



**I.磁盘选择:** 顺序读写操作
Kafka 大量使用磁盘

• 追求性价比的公司可以不搭建 RAID，使用普通磁盘组成存储空间即可。
• 使用机械磁盘完全能够胜任 Kafka 线上环境。 

**I.磁盘容量**
每天 1 亿条 1KB 大小的消息，保存两份且留存两周的时间

1 亿 * 1KB * 2 / 1000 / 1000 = 200GB
数据预留出 10% 的磁盘空间

220GB * 14，大约 3TB 左右

**I.带宽**
带宽资源不足导致 Kafka 出现性能问题的比例至少占 60% 以上
跨机房传输，那么情况可能就更糟


假设 Kafka 会用到 70% 的带宽资源
 
1Gbps 的千兆网络和 10Gbps 的万兆网络

业务目标或 SLA 是在 1 小时内处理 1TB 的业务数据


------------------------------------------------------
**07  最最最重要的集群参数配置（上）**


Broker 端参数 、Topic 级别参数 、JVM 参数 、操作系统参数 




**I.Broker 端参数**

II. log.dirs 保证这些目录挂载到不同的物理磁盘上
指定了 Broker 需要使用的若干个文件目录路径
/home/kafka1,/home/kafka2,/home/kafka3 

1.提升读写性能：比起单块磁盘，多块物理磁盘同时读写数据有更高的吞吐量。

2.能够实现故障转移：即 Failover。这是 Kafka 1.1 版本新引入的强大功能。



**I.Topic 级别参数**

retention.ms
Topic 消息被保存的时长。默认是 7 天


retention.bytes
规定了要为该 Topic 预留多大的磁盘空间


**I.JVM 参数**
Java 8

G1 


**I.操作系统参数** 

• 文件描述符限制
• 文件系统类型
• Swappiness
• 提交时间 





**09 | 生产者消息分区机制原理剖析** 

主题（Topic）的概念

主题（Topic） - 分区（Partition） - 消息


**分区的作用:**
提供负载均衡的能力，实现系统的高伸缩性（Scalability）。



**分区策略**
所谓分区策略是决定生产者将消息发送到哪个分区的算法。

默认的分区策略、自定义分区策略


II.轮询策略(默认策略)

轮询策略有非常优秀的负载均衡表现，它总是能保证消息最大限度地被平均分配到所有分区上，故默认情况下它是最合理的分区策略，也是我们最常用的分区策略之一。 


II.随机策略 


如果追求数据的均匀分布，还是使用轮询策略比较好  。
事实上，随机策略是老版本生产者使用的分区策略，在新版本中已经改为轮询了。 


II.按消息键保序策略  
Kafka 允许为每条消息定义消息键，简称为 Key。这个 Key 的作用非常大，它可以是一个有着明确业务含义的字符串，比如客户代码、部门编号或是业务 ID 等；也可以用来表征消息元数据。

相同的key放到相同的分区里
消息的顺序问题



II.其他分区策略
北京、广州新注册用户




**10  生产者压缩算法面面观**


Producer 端压缩、Broker 端保持、Consumer 端解压缩

压缩（compression），用时间去换空间的经典 trade-off权衡 思想

CPU 时间去换磁盘空间或网络 I/O 传输量




**Kafka 的消息层次都分为两层**  没看懂
消息集合（message set）、消息（message）


**何时压缩**

Producer生产者端(正常会压缩)和 Broker 端(正常不压缩和解压，原封存储)

生产者端:正常情况下，生产者端压缩，Broker端原封不动的存储，不会压缩，会解压校验。
Broker 端:
1.Producer 端和Broker 端压缩算法不一样， Broker 端会重新压缩
2.Broker 端发生了消息格式转换。为了兼容老版本消息格式会进行解压缩和重新压缩


**何时解压缩**

Consumer消费者 自行解压缩还原成之前的消息

Broker 端会解压缩:对消息执行各种验证
每个压缩过的消息集合在 Broker 端写入时都要发生解压缩操作，目的就是为了对消息执行各种验证。我们必须承认这种解压缩对 Broker 端性能是有一定影响的，特别是对 CPU 的使用率而言。 


**各种压缩算法对比**   LZ4 

指标:都是越高越好
1.压缩比:100 份空间的东西经压缩之后变成了占 20 份空间，那么压缩比就是 5
2.吞吐量:每秒能压缩或解压缩多少 MB 的数据

吞吐量:LZ4 > Snappy > zstd 和 GZIP
压缩比:zstd > LZ4 > GZIP > Snappy

CPU 使用率:压缩时 Snappy 最多；其他差不多。 加压时GZIP 使用最多



**最佳实践** 

CPU 很高的应用 最好别用
CPU 空闲，带宽不大的建议开启




**11 | 无消息丢失配置怎么实现？**


I.Kafka 只对“已提交”的消息（committed message）做有限度的持久化保证。 


II.已提交的消息
broker 接收到一条消息并写入到日志文件后，它们会告诉生产者程序这条消息已成功提交。


II.有限度的持久化保证
地球消失了，肯定丢了。
存在 N 个 Kafka Broker 上，只有一个存活就成立。




**I.“消息丢失”案例**
案例 1：生产者程序丢失数据 

Kafka Producer 是异步发送消息的。
producer.send(msg) 这个 API，那么它通常会立即返回


要使用 producer.send(msg, callback) 


案例 2：消费者程序丢失数据
Consumer 端要消费的消息不见了

**丢失情况1**
背景
标签：标记消费队列消费到第几个；
真实消费。

问题现象:
先挪动标签，再真实消费，但是消费中断了，下次启动的时候，就丢消息了。

问题解决:
先真实消费，再挪动标签

出现新问题
消息重复小王问题


**丢失情况2**
Consumer 程序从 Kafka 获取到消息后开启了多个线程异步处理消息，
而 Consumer 程序自动地向前更新位移。



**12 | 客户端都有哪些不常见但是很高级的功能？** 



**Kafka 拦截器**

生产者拦截器和消费者拦截器


**典型使用场景**  
客户端监控、端到端系统性能检测、消息审计等多种功能在内的场景


1.监控消息队列平均消费时间




**13 | Java生产者是如何管理TCP连接的？** 


Apache Kafka 的所有通信都是基于 TCP 的，而不是基于 HTTP 或其他协议。


**Kafka 生产者程序概览** 

Kafka 的 Java 生产者 API 主要的对象就是 KafkaProducer。

第 1 步：构造生产者对象所需的参数对象。
第 2 步：利用第 1 步的参数对象，创建 KafkaProducer 对象实例。
第 3 步：使用 KafkaProducer 的 send 方法发送消息。
第 4 步：调用 KafkaProducer 的 close 方法关闭生产者并释放各种系统资源。 



I.何时创建 TCP 连接？
Producer<String, String> producer = new KafkaProducer<>(props);  已创建连接
producer.send(new ProducerRecord<String, String>(……), callback);

在创建 KafkaProducer 实例时，生产者应用会在后台创建并启动一个名为 Sender 的线程，该 Sender 线程开始运行时首先会创建与 Broker 的连接  


TCP 连接还**可能**在两个地方被创建:
一个是在更新元数据后(集群新增节点)、另一个是在消息发送时 

Producer 更新了集群的元数据信息之后，如果发现与某些 Broker 当前没有连接，那么它就会创建一个 TCP 连接。
Producer 发现尚不存在与目标 Broker 的连接，也会创建一个。


**Producer 设计合理性**
有着 1000 台 Broker 的集群中,Producer会建立1000个tcp连接，不管用不用。




I.何时关闭 TCP 连接？

Producer 端关闭 TCP 连接的方式有两种：  
一种是用户主动关闭；一种是 Kafka 自动关闭  。 

用户主动关闭:
正常关闭、kill -9 等

Kafka 自动关闭 :
默认几分钟没有消息就关闭。 Producer 端设置 connections.max.idle.ms=-1 禁用自动关闭





**14 | 幂等生产者和事务生产者是一回事吗？** 


Kafka 消息交付可靠性保障以及精确处理一次语义的实现。 


Kafka 对 Producer 和 Consumer 消息交付可靠性保障(默认至少一次)
• 最多一次（at most once）：消息可能会丢失，但绝不会被重复发送。
• 至少一次（at least once）：消息不会丢失，但有可能被重复发送。(默认提供)
• 精确一次（exactly once）：消息不会丢失，也不会被重复发送。 

**最多一次（at most once）实现：**
让 Producer 禁止重试即可


**精确一次（exactly once）实现:**
幂等性（Idempotence）和事务（Transaction）

幂等性:某些操作或函数能够被执行多次，但每次得到的结果都是不变的。
幂等性有很多好处，  其最大的优势在于我们可以安全地重试任何幂等性操作，反正它们也不会破坏我们的系统状态  。


II.Producer幂等性:
Producer 默认不是幂等性的。打开属性即可
props.put(“enable.idempotence”, ture)，(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG， true)。 

当 Producer 发送了具有相同字段值的消息后，Broker 能够自动知晓这些消息已经重复了，于是可以在后台默默地把它们“丢弃”掉。

III.Producer 的作用范围
1.只能保证单分区上的幂等性。
一个幂等性 Producer 能够保证某个主题的一个分区上不出现重复消息，它无法实现多个分区的幂等性。

2.只能保证单会话上的幂等性
重启了 Producer 进程之后，这种幂等性保证就丧失了。

II.Producer事务
ACID，即原子性（Atomicity）、一致性 (Consistency)、隔离性 (Isolation) 和持久性 (Durability)。

 read committed 隔离级别上做事情。它能保证多条消息原子性地写入到目标分区，同时也能保证 Consumer 只能看到事务成功提交的消息。


事务型 Producer 能够保证将消息原子性地写入到多个分区中。这批消息要么全部写入成功，要么全部失败。
事务型 Producer 也不惧进程的重启。Producer 重启回来后，Kafka 依然保证它们发送消息的精确一次处理。 

III.开启生产者事务
enable.idempotence = true。 +设置 Producer 端参数 transctional. id

III.改变语法
producer.initTransactions();
try {
            producer.beginTransaction();
            producer.send(record1);
            producer.send(record2);
            producer.commitTransaction();
} catch (KafkaException e) {
            producer.abortTransaction();
}
 
III.消费者端修改代码
Consumer 端，读取事务型 Producer 发送的消息也是需要一些变更的
修改隔离级别isolation.level ：
read_uncommitted 默认读未提交、需要修改

read_committed:读取事务消息，需要使用这个级别


对比:
幂等性 Producer 只能保证单分区、单会话上的消息幂等性
事务能够保证跨分区、跨会话间的幂等性
比起幂等性 Producer，事务型 Producer 的性能要更差





**15 | 消费者组到底是什么？** 

Consumer Group 是 Kafka 提供的可扩展且具有容错性的消费者机制 

有多个消费者或消费者实例（Consumer Instance）

消费订阅主题（Subscribed Topics）的所有分区（Partition）。每个分区只能由同一个消费者组内的一个 Consumer 实例来消费

1 .Consumer Group 下可以有一个或多个 Consumer 实例。这里的实例可以是一个单独的进程，也可以是同一进程下的线程。在实际场景中，使用进程更为常见一些。
2. Group ID 是一个字符串，在一个 Kafka 集群中，它标识唯一的一个 Consumer Group。
3. Consumer Group 下所有实例订阅的主题的单个分区，只能分配给组内的某个 Consumer 实例消费。这个分区当然也可以被其他的 Group 消费。 


点对点模型和发布(消费队列) / 订阅模型 

国内很多文章都习惯把消息中间件这类框架统称为消息队列，我在这里不评价这种提法是否准确，只是想提醒你注意这里所说的消息队列，特指经典的消息引擎模型。 


**传统的消息队列模型:** 只能一个消费
缺陷在于消息一旦被消费，就会从队列中被删除，而且只能被下游的一个 Consumer 消费。
伸缩性（scalability）很差
下游的多个 Consumer 都要“抢”这个共享消息队列的消息。

**发布 / 订阅模型**  每个都要消费全部。
允许消息被多个 Consumer 消费。
伸缩性（scalability）很差，**每个订阅者都必须要订阅主题的所有分区**。




**避开这两种模型的缺陷又兼具它们的优点**。

**Consumer Group 机制:** 组内每个示例值消费部分分区


当 Consumer Group 订阅了多个主题后，组内的每个实例不要求一定要订阅主题的所有分区，它只会消费部分分区中的消息。 

Consumer Group 之间彼此独立，互不影响，它们能够订阅相同的一组主题而互不干涉。

Broker 端的消息留存机制，Kafka 的 Consumer Group 完美地规避了上面提到的伸缩性差的问题。


**同时实现了传统消息引擎系统的两大模型  :**
如果所有实例都属于同一个 Group，那么它实现的就是消息队列模型。
如果所有实例分别属于不同的 Group，那么它实现的就是发布 / 订阅模型。 

**Consumer Group实例个数**
Consumer 实例的数量应该等于该 Group 订阅主题的分区总数。 


三个topic A、B、C:  A1个分片、B有2个分片 、C有3个分片
那Consumer理论实例个数=A*1+B*2+C*3=6个实例。
最大限度地实现高伸缩性

如果有3个Consumer实例,每个实例消费2个分区=6/3。
如果有8个Consumer实例，会有2个实例不会被分到任何分区(8-6=2)

因此，在实际使用过程中一般不推荐设置大于总分区数的 Consumer 实例。设置多余的实例只会浪费资源，而没有任何好处。 


**Consumer Group 管理位移（Offset）**
对于 Consumer Group 而言 位移Offset 是一组 KV 对。
Key 是分区，V 对应 Consumer 消费该分区的最新位移

老版本的 Consumer Group 把位移保存在 ZooKeeper 中.不适合进行频繁的写更新

新版本的 Consumer Group 保存在 Kafka Broker端 内部主题。 
__consumer_offsets



**Consumer Group重平衡Rebalance**

Rebalance 是一种协议，规定了一个Consumer Group下的所有 Consumer如何达成一致，来分配订阅 Topic 的每个分区。

Group 下20 个 Consumer ,消费100 个分区的 Topic。正常1个Consumer会分到5个分区。

**Rebalance 的触发条件有 3 个**

I.组成员数发生变更。
新的 Consumer 实例加入组或者离开组、Consumer 实例崩溃被“踢出”组。

I.订阅主题数发生变更。
Group订阅所有以字母 t开头的主题。在Consumer Group的运行过程中，你新创建了一个满足这样条件的主题，那么该 Group 就会发生 Rebalance。 

I.订阅主题的分区数发生变更。
Kafka 当前只能允许增加一个主题的分区数。当分区数增加时，就会触发订阅该主题的所有 Group 开启 Rebalance。



Rebalance时所有的 Consumer 实例都会协调在一起共同参与


**Kafka 默认提供了 3 种分配策略**

Rebalance 所有 Consumer 实例都会停止消费，等待 Rebalance 完成
stop the world，简称 STW
所有 Consumer 实例都会停止消费，等待 Rebalance 完成


国外用户的 Group 内有几百个 Consumer 实例，成功 Rebalance 一次要几个小时！




------------------------------------
**16 | 揭开神秘的“位移主题”面纱** 

内部主题（Internal Topic）__consumer_offsets

位移主题:
消费者KV(topic,offsets) 消费那个主体topic和消费到那个位移offsets
位移主题是 Kafka 自动创建的，那么该主题的分区数是 50，副本数是 3  。


将 Consumer 的位移数据作为一条条普通的 Kafka 消息，提交到 __consumer_offsets 中。
可以这么说，__consumer_offsets 的主要作用是保存 Kafka 消费者的位移信息。


位移主题:
它的消息格式却是 Kafka 自己定义的  


Key: Group ID+主题名topic+分区号
value:

3 种消息格式


第一个 Consumer 程序启动时，Kafka 会自动创建位移主题




Consumer 提交位移的方式有两种： 
自动提交位移:只要 Consumer 一直启动着，它就会无限期地向位移主题写入消息。
手动提交位移


enable.auto.commit=true

enable.auto.commit = false;consumer.commitSync


删除位移主题中的过期消息:

Compact 策略:同一个 Key 的两条消息 M1 和 M2.如果 M1 的发送时间早于 M2，那么 M1 就是过期消息。

Kafka 提供了专门的后台线程定期地巡检待 Compact 的主题，看看是否存在满足条件的可删除数据  。




**17 | 消费者组重平衡能避免吗？** 



Coordinator协调者：
它专门为 Consumer Group 服务，负责为 Group 执行 Rebalance 以及提供位移管理和组成员管理等。 


所有 Broker 都有各自的 Coordinator 组件  :



Rebalance 的弊端:
1.Rebalance 影响 Consumer 端 TPS
2.Rebalance 很慢。如果你的 Group 下成员很多，就一定会有这样的痛点。
3.Rebalance 效率不高。当前 Kafka 的设计机制决定了每次 Rebalance 时，Group 下的所有成员都要参与进来，而且通常不会考虑局部性原理，但局部性原理对提升系统性能是特别重要的。 

**Rebalance 发生的时机有三个:**
• 组成员数量发生变化
• 订阅主题数量发生变化:无解
• 订阅主题的分区数发生变化:无解 


组成员数量发生变化:
1.真实添加删除成员
2.误删除

2.1 Consumer 实例都会定期地向 Coordinator 发送心跳请求: session.timeout.ms 默认值是 10 秒
2.2 Consumer 实际消费能力对 Rebalance 的影响:max.poll.interval.ms  默认值是 5 分钟
它限定了 Consumer 端应用程序两次调用 poll 方法的最大时间间隔。



第一类非必要 Rebalance 是因为未能及时发送心跳，导致 Consumer 被“踢出”Group 而引发的  。
设置 session.timeout.ms 和 heartbeat.interval.ms  的值

可以“无脑”地应用在你的生产环境中。
• 设置 session.timeout.ms = 6s。
• 设置 heartbeat.interval.ms = 2s。
• 要保证 Consumer 实例在被判定为“dead”之前，能够发送至少 3 轮的心跳请求，即 session.timeout.ms >= 3 * heartbeat.interval.ms。




第二类非必要 Rebalance 是 Consumer 消费时间过长导致的  。
消息处理时间缩短。






-------------------------------------------------
**18 | Kafka中位移提交那些事儿** 


Consumer 需要向 Kafka 汇报自己的位移数据，这个汇报过程被称为提交位移  。

Consumer 需要为分配给它的每个分区提交各自的位移数据  。

0-20位移
消费到4，下一个要消费5，就提交5. 如果提交10，5-9就丢了。提交2，就要重复消费3以后。

位移提交的语义保障是由你来负责的，Kafka 只会“无脑”地接受你提交的位移  。


用户的角度来说，位移提交分为：自动提交和手动提交
从 Consumer 端的角度来说，位移提交分为：同步提交和异步提交  。 


默认值是 5 秒自动提交一次位移

II.Consumer自动提交位移
Properties props = new Properties();

     props.put("enable.auto.commit", "true");
     props.put("auto.commit.interval.ms", "2000");

II.Consumer手工提交位移
 
props.put("enable.auto.commit", "false");
KafkaConsumer#commitSync()  
1.同步提交
consumer.commitSync();

2.异步提交: 回调函数（callback）
consumer.commitAsync((offsets, exception) -> {
	if (exception != null)
	handle(exception);
	});





Consumer自动提交位移:它可能会出现重复消费  

Rebalance 发生了，你执行了3秒，5秒才提交。 前3秒需要重复消费。




----------------------------
**19 | CommitFailedException异常怎么处理？** 



CommitFailedException
Consumer 客户端在提交位移时出现了错误或异常，而且还是那种不可恢复的严重异常  。


你的消费者实例连续两次调用 poll 方法的时间间隔超过了期望的 max.poll.interval.ms 参数值

1. 增加期望的时间间隔 max.poll.interval.ms 参数值。
2. 减少 poll 方法一次性返回的消息数量，即减少 max.poll.records 参数值。 



场景一  单条消费时间过长
1.缩短单条消息处理的时间  。
2.增加 Consumer 端允许下游系统消费一批消息的最大时长  。
3.减少下游系统一次性消费的消息总数  
4.下游系统使用多线程来加速消费  。


场景二 


20 | 多线程开发消费者实例 

**Kafka Java Consumer 设计原理 :**
用户主线程和心跳线程

启动 Consumer 应用程序 main 方法的那个线程，而新引入的心跳线程（Heartbeat Thread）只负责定期给对应的 Broker 机器发送心跳请求，以标识消费者应用的存活性（liveness）  



**多线程方案** 

自己开发多线程

启动多个 Consumer 进程



**21 | Java 消费者是如何管理TCP连接的?** 


**何时创建 TCP 连接？** 

程序入口是 KafkaConsumer 类
和生产者不同的是，构建 KafkaConsumer 实例时是不会创建任何 TCP 连接的

new KafkaConsumer(properties) 不会建立连接，和生产者不一样!


TCP 连接是在调用 KafkaConsumer.poll 方法时被创建的  

poll 方法内部有 3 个时机可以创建 TCP 连接:
1. 发起 FindCoordinator 请求时  。 协调者（Coordinator）
2. 连接协调者时。 
3. 消费数据时。 



**创建多少个 TCP 连接？**
消费者程序会创建 3 类 TCP 连接：

1. 确定协调者和获取集群元数据。
2. 连接协调者，令其执行组成员管理操作。
3. 执行实际的消息获取。 



**何时关闭 TCP 连接？** 

手动调用 KafkaConsumer.close() 方法，或者是执行 Kill 命令  
Kafka 自动关闭是由  消费者端参数 connection.max.idle.ms  控制的，该参数现在的默认值是 9 分钟



**22 | 消费者组消费进度监控都怎么实现？** 


监控它们的消费进度，或者说是监控它们消费的滞后程度。
这个滞后程度有个专门的名称：
消费者 Lag 或 Consumer Lag。 


滞后程度:就是指消费者当前落后于生产者的程度  。

100 万条，消费80 万，消费者滞后了 20 万，即 Lag 等于20万。 

Lag 的单位是消息数

Kafka 监控 Lag 的层级是在分区上的.
如果要计算主题级别的，你需要手动汇总所有主题分区的 Lag，将它们累加起来，合并成最终的 Lag 值。

消费者而言，Lag 应该算是最最重要的监控指标了


由于消费者的速度无法匹及生产者的速度,导致它消费的数据已经不在操作系统的页缓存中失去享有 Zero Copy 技术的资格。
进一步拉大了与生产者的差距，进而出现马太效应。


怎么监控:
1. 使用 Kafka 自带的命令行工具 kafka-consumer-groups 脚本。
2. 使用 Kafka Java Consumer API 编程。
3. 使用 Kafka 自带的 JMX 监控指标。  作者推荐



**Kafka 自带命令 :**
bin/kafka-consumer-groups.sh
$ bin/kafka-consumer-groups.sh --bootstrap-server <Kafka broker 连接信息 > --describe --group <group 名称 >


bin/kafka-consumer-groups.sh --bootstrap-server 127.0.0.1:9092 --describe --group TestGroup
消费者组名：testgroup

**Kafka Java Consumer API**
自动化监控,自己写代码

AdminClient.listConsumerGroupOffsets 




**Kafka JMX 监控指标** 

**Lead 值:**
消费者最新消费消息的位移与分区当前第一条消息位移的差值  。
默认7天删除，差值距离第一条越小，证明消息块删了，还没消费。

Lag 越大的话，Lead 就越小，反之也是同理  。 


同时监控 Lag 值和 Lead 值






**23 | Kafka副本机制详解** 


**副本机制（Replication）备份机制好处(非kafka好处)** 
1.  提供数据冗余  (高可用)。即使系统部分组件失效，系统依然能够继续运转，因而增加了整体可用性以及数据持久性。
2.  提供高伸缩性  (高性能，只读)。  支持横向扩展，能够通过增加机器的方式来提升读性能，进而提高读操作吞吐量。
3.  改善数据局部性  (高性能，距离)。 允许将数据放入与用户地理位置相近的地方，从而降低系统延时。 



Apache Kafka 只能提供数据冗余1。

主题>多个分区>每个分区有副本

所谓副本（Replica），本质就是一个只能追加写消息的提交日志  



副本角色 :
领导者副本（Leader Replica）
追随者副本（Follower Replica）

每个分区在创建时都要选举一个副本，称为领导者副本，其余的副本自动称为追随者副本。 

追随者副本不处理客户端请求，从领导者副本异步拉取 消息，并写入到自己的提交日志中，从而实现与领导者副本的同步。 


领导者副本挂掉:从追随者副本中选一个作为新的领导者。


**Kafka 为什么要这样设计呢**

1. 方便实现“Read-your-writes”  。

生产者 API 向 Kafka 成功写入消息后，马上使用消费者 API 去读取刚才生产的消息。
发完一条微博，肯定是希望能立即看到


2. 方便实现单调读（Monotonic Reads）  。
主从延迟
是对于一个消费者用户而言，在多次消费消息时，它不会看到某条消息一会儿存在一会儿不存在。 




**In-sync Replicas（ISR）** 同步副本: 不一定所有副本都在ISR中


这个标准就是 Broker 端参数 replica.lag.time.max.ms 参数值
Follower 副本能够落后 Leader 副本的最长时间间隔。
超出这个间隔就会被踢出ISR



**Unclean 领导者选举（Unclean Leader Election）** 

非同步副本:Kafka 把所有不在 ISR 中的存活副本

Broker 端参数 unclean.leader.election.enable 控制是否允许 Unclean 领导者选举  。

开启 Unclean 领导者选举：可能会造成数据丢失。提升了高可用性
禁止 Unclean 领导者选举：维护了数据的一致性，避免了消息丢失，但牺牲了高可用性。


CAP：
一致性（Consistency）、可用性（Availability）、分区容错性（Partition tolerance）中的两





**24 | 请求是怎么被处理的？** 



Kafka 客户端(produce+consumer)
“请求 / 响应
Broker 端


**Apache Kafka 定义请求协议，用于实现各种各样的交互操作:**
PRODUCE 请求是用于生产消息的
FETCH 请求是用于消费消息
METADATA 请求是用于请求 Kafka 集群元数据信息的。 


所有的请求都是通过 TCP 网络以 Socket 的方式进行通讯的 



**Reactor 模式:**
事件驱动架构的一种实现方式，特别适合应用于处理多个客户端并发向服务器端发送请求的场景  。

多个client>Reactor

Reactor(Acceptor线程[分发线程Dispatcher])

**Kafka实现**
多个client>Broker
 
1.分发 Broker
SocketServer 组件:作Dispatcher分发
Acceptor 线程和网络线程池

Broker 默认有: 3 个网络线程池
num.network.threads

Dispatcher 只是用于请求分发而不负责响应回传。

2.处理



3.流程
client(produce生产、fetch出售，消费)  >> SocketServer 组件(3个网络线程池分发) 
SocketServer 组件(3个网络线程池分发) >>网络线程(收到转发请求)
网络线程(收到转发请求)>>共享请求队列>> IO 线程池默认8线程(从队列中取出来请求) num.io.threads 

IO 线程池:(操作(produce生产写入磁盘、fetch请求磁盘缓存读数据))

IO 线程池处理完成>>网络线程池响应队列
IO 线程池处理完成>>网络线程池响应队列


Purgatory 的组件:缓存延时请求  （Delayed Request）
未满足条件不能立刻处理的请求


**请求队列响应队列区别**
请求队列是所有网络线程共享的，而响应队列则是每个网络线程专属的 


数据类请求:
PRODUCE 和 FETCH 这类请求


控制类请求:
LeaderAndIsr、StopReplica

控制类请求有这样一种能力：它可以直接令数据类请求失效！



----------------------------------------------------------------
25 | 消费者组重平衡全流程解析 


触发与通知 :

 3 个触发条件：
1. 组成员数量发生变化。
2. 订阅主题数量发生变化。
3. 订阅主题的分区数发生变化。 

**如何监听到需要重平衡** broker 协调者决定重平衡

重平衡过程是如何通知到其他消费者实例的
消费者端的心跳线程（Heartbeat Thread）


消费者定期>>发送心跳请求（Heartbeat Request）>>Broker 端的协调者

当协调者决定开启新一轮重平衡后:
Broker 端的协调者>> 响应内容增加 rebalance_in_progress响应中


**消费者端重平衡流程**  

两个步骤:
1.JoinGroup 请求加入组(第一个加入者就是领导者)
2.SyncGroup 请求 等待领导者消费者（Leader Consumer）分配方案
3.LeaveGroup 请求 离开组请求

JoinGroup加入组请求:
A JoinGroup请求>Broker协调者  >A 你是领导者
B JoinGroup请求>Broker协调者  >B 你是成员,A是领导者


SyncGroup同步组请求:
A 领导者(A消费topic1，B消费topic2)SyncGroup请求>Broker协调者 (A你消费topic1)> A(A你消费topic1)

B (非领导者null)SyncGroup>Broker协调者 (B你消费topic2)> B(B你消费topic2)



**Broker 端重平衡场景剖析** 


新组加入流程:

1.A新组(JoinGroup请求)>Broker协调者 ;一直保持
2.B心跳请求(我还活着)>Broker协调者(重平衡开始，你需要重新加入)>B
3.B(JoinGroup请求)>Broker协调者 (你已入组,你是成员)>B (成员)
4.Broker协调者响应A的join请求>> 返回A组的join请求  (你已入组,你是领导)>A
5.A 领导者(A消费topic1，B消费topic2)SyncGroup请求>Broker协调者 (A你消费topic1)> A(A你消费topic1)
6.B (非领导者null)SyncGroup>Broker协调者 (B你消费topic2)> B(B你消费topic2)



组异常离开流程:
1.A组断开超时，Broker协调者判断A离开。
2.B心跳请求(我还活着)>Broker协调者(重平衡开始，你需要重新加入)>B
3.B(JoinGroup请求)>Broker协调者 (你已入组,你是领导)>B (领导)
4.B 领导者(B消费topic1)SyncGroup请求>Broker协调者 (B你消费topic1)> B(B消费topic1)

组正常离开
1.A组我要离开了(LeaveGroup 请求) >Broker协调者>A组 拜拜
后边跟异常离开一样



--------------------------------------------------
26 | 你一定不能错过的Kafka控制器 


















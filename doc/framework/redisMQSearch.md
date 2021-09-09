I.redis http://www.redis.cn/
五种：压缩列表（可以看作一种特殊的数组）、有序数组、链表、散列表、跳表。实际上，Redis 就是这些常用数据结构的封装。
  
II.string,
II.hash, 哈希表
II.list,双向链表
II.set 底层哈希表不可重复 交集并集什么的
II.zset(sorted set)有序集合
 
  

 
 
 II.list,双向链表
 一种是压缩列表（ziplist） 单个小于 64 字节、个数少于512
 一种是双向循环链表
 
 
 II.hash, 哈希表
 一种是压缩列表（ziplist）   单个小于 64 字节、个数少于512
 
 散列表：自动扩容
 
 
 II.set不重复集合 交集并集
一种是基于有序数组  数据都是整数、不超过 512
 基于散列表
 
II.SortedSet有序不重复集合zset
 一种是压缩列表（ziplist）  小于 64 字节、小于 128 个

跳表（Skip list）： 索引+原始链表




 I.ziplist压缩双向链表（数组形式存储内存连续）：
 总字节数+最后一位偏移数(直接定位尾)+数据总个数+(数据+数据)+zlend(最后一位固定255)
 zlbytes
 zltail
 zllen
 entry+entry
 zlend
 
 
 http://www.redis.cn/topics/distlock.html
 Redis分布式锁
 
 I.持久化:RDB快照fork。AOF追加慢
 
 I.主从：replication ID(复制ID):(记录发送数据的多少)
 I.哨兵：高可用自动选主。分片：高性能 三倍





redis
II.string   {缓存、计数器(加一)、session共享} -二进制安全的字节数组 
II.list,双向链表 : 压缩列表（ziplist）、双向循环链表  {消息队列}
II.hash, 哈希表 : 压缩列表（ziplist）、散列表：自动扩容  {商品详情}
II.set 不可重复(底层哈希表)交集并集 : 有序数组、基于散列表 {黑名单、共同好友交集、推荐好友差集}
II.zset(sorted set)有序集合 : 压缩列表（ziplist）、跳表（Skip list）： 索引+原始链表  {排行榜}
II. Bitmaps  {签到记录、布隆过滤器（判断值释放存在，限流）}  底层是String
II.  HyperLogLog  {UV去重显示KEY count}海盆儿 :大数据基数统计
II.Pub/Sub   消息的广播
II.Geo 地理空间、计算距离等

传输用字节流       （字节1byte=8bit1） > 编码UTF-8等 >(字符char = 2* byte)


I.ziplist压缩双向链表（数组形式存储内存连续）：总字节数+最后一位偏移数(直接定位尾)+数据总个数+(数据+数据)+zlend(最后一位固定255)

I.持久化:RDB快照 开分支 copyOnwrit 和  AOF追加慢
I.主从：replication ID(复制ID):(记录发送数据的多少)
I.redis4.0新增混合模式 快照+AOF追加
I.哨兵：高可用自动选主。
I.集群Cluster 分片：高性能 三倍；高性能
I.Pipeline 批量执行脚本 减少请求

I.过期策略：  定期删除100毫秒随机删+惰性删除(调用了删)
I.内存淘汰机制:报错、最少使用key(过期的)、随机删key(过期的)、删除快死的key





I.set等基础命令
II.set 设置 可选参数: EX过期秒、 PX 过期毫秒、NX是否存在、XX键存在才操作。
SET key value [EX seconds] [PX milliseconds] [NX|XX] 
II.set可选参数有对应的新命令SETEX、PSETEX、SETNX
EX:SETEX(EXPIRE  second 失效时间 秒)
PX:PSETEX(过期时间 毫秒 SET key value PX millisecond )
NX:SETNX(SET if Not eXists如果不存在才设置)  key不存在返回1ture， key存在返回0false 
XX:(只在键已经存在时，才对键进行设置操作。)

**I.分布式锁单机:(官网建议)** Distributed locks
http://www.redis.cn/topics/distlock.html

II.单机redis 加锁 分布式锁 NX+ 过期时间:(只有key不存在才设置)
1. SET key my_random_value NX PX 30000  :单独命令设置 :NX 如果不存在才设置 、PX 超时时间、随机value
2. SETNX key my_random_value +  EXPIRE mykey 10
3. spring redis实现
bo = redisTemplate.opsForValue().setIfAbsent(jobNxKey,"1",REDIS_LOCK_EXPIRE_10MINUTES, TimeUnit.SECONDS);

II.单机redis 删除锁:只删除本机随机生成value的key(A获得锁超时，B获得锁，A继续执行后删了B的锁)。
  if( get key ==my_random_value){
   del key;
}

**I.分布式锁集群:(官网建议)** 大于(N/2+1)节点 
官网-分布式锁规范算法:
思路和设计方案: 独享（相互排斥）、 无死锁(有锁崩溃不影响其他获得)、容错(redis大部分节点存活就可读写)

redLock红锁思路:
5个master，同时向5个节点获取锁，大于3(N/2+1)节点成功就成功.
成功没大于3(N/2+1)，就全部节点释放锁。

**I.Redisson** java版本实现
https://www.cnblogs.com/qdhxhz/p/11046905.html
https://github.com/redisson/redisson
https://github.com/redisson/redisson/wiki/Table-of-Content
WatchDog  默认30 秒 每10秒看是否存活 -自动延期机制
II.Fair Lock 公平锁
II.读写锁:  ReadLock 所有者和仅一个 WriteLock
II.Semaphore 信号量:
II. CountDownLatch 加一减一
II.Spin Lock 自旋锁
 
**I.UV HyperLogLog**
pfadd key value4 value5 value6 value7  //新增多个元素
pfcount key //统计该key去重后的元素个数

布隆过滤器 判断这个值是否存在，功能存在误差 hyperloglog不带布隆过滤器功能


II.缓存雪崩:key同时过期--设置随机值; 或者预热数据--熔断直接返回错误。
II.缓存穿透:缓存和数据库中都没有的数据。
增加业务校验:数据不合法直接return.增加IP等并发规则、布隆过滤器



I.bitmap活跃用户数量、第一天登录、第二天登录: 签到表
setbit 20200101  1 1
setbit 20200102  1 1
setbit 20200102  2 1
BITOP  or res 20200101 20200102
bitcount res 0 -1
返回:2 个人


**Redis为什么变慢**
1.链路追踪-确定是redis
2.判断网络问题，还是就redis慢(基准测试) 网络带宽过载
3.判断是复杂命令 （慢日志）、还是bigkey(命令扫描线上)
4.如果时间有规律-是不是过期时间比较集中：（1.增加随机值、2.lazy-free释放内存后台操作）
5.内存达到上限 报错(默认)：最少使用(all、设置过期)、随机(all、设置过期)、马上快过期、访问频率最低(all,过期)。
6.持久化造成的fork耗时和页面大小有关系，设置10G以下。操作系统开启内存大页限制，有就关闭



LRU：最少使用页面置换算法(Least Recently Used),最长时间未被使用的页面!
LFU：最不常用页面置换算法(Least Frequently Used),一定时期内被访问次数最少的页!

**I.其他优化**
**Redis CPU绑定:不建议**
对主线程、后台线程、后台 RDB 进程、AOF rewrite 进程，绑定固定的 CPU 逻辑核心：

**操作系统内存不够置换硬盘swap操作**

**redis 4以后带碎片整理** 可能会影响redis性能

**短连接问题**
短连接问题 应该使用长连接较少三次握手四次挥手



redis分布式 ap模型







 --------------------------------------------------------------------------
 
搜索引擎

四个部分：
搜集、深度优先和广度优先
分析、
索引、
查询
 
 
 树中的元素我们称为节点，图中的元素我们就叫作顶点（vertex）
 ，图中的一个顶点可以与任意其他顶点建立连接关系。我们把这种建立的关系叫作边（edge）
 ，每个用户有多少个好友，对应到图中，就叫作顶点的度（degree），就是跟顶点相连接的边的条数。
 
 微博的社交关系跟微信还有点不一样 引入边的“方向”的概念
 
 
 我们把这种边有方向的图叫作“有向图
 我们把边没有方向的图就叫作“无向图”。
 
 在有向图中，我们把度分为入度（In-degree）和出度（Out-degree）。
 
 顶点的入度，表示有多少条边指向这个顶点
 顶点的出度，表示有多少条边是以这个顶点为起点指向其他顶点。  。对应到微博的例子，入度就表示有多少粉丝，出度就表示关注了多少人。
 
 带权图（weighted graph） 我们可以通过这个权重来表示 QQ 好友间的亲密度。
 
 
 图最直观的一种存储方法就是，邻接矩阵（Adjacency Matrix）。 二维数组
 
 
 
 
种子网页链接（权重高的主站）：广度优先查找
 
 
 
 
 
 ---------------------------------------------------------------------------
 
 Topic：消息主题，一级消息类型，生产者向其发送消息。
 
 
 
 多协议接入
 HTTP 协议：采用 RESTful 风格，方便易用，快速接入，跨网络能力强。支持 Java、C++、.NET、Go、Python、Node.js 和 PHP 七种语言客户端。
 TCP 协议：区别于 HTTP 简单的接入方式，提供更为专业、可靠、稳定的 TCP 协议的 SDK 接入服务。支持的语言包括 Java、C/C++ 以及 .NET。
 
 
 
 管理工具
 Web 控制台：支持 Topic 管理、Group 管理、消息查询、消息轨迹展示和查询、资源报表以及监控报警管理。
 OpenAPI：提供开放的 API 便于将消息队列 RocketMQ 版管理工具集成到自己的控制台。 
 
 
 消息类型
普通消息：消息队列 RocketMQ 版中无特性的消息，区别于有特性的定时/延时消息、顺序消息和事务消息。
事务消息：实现类似 X/Open XA 的分布事务功能，以达到事务最终一致性状态。
定时和延时消息：允许消息生产者对指定消息进行定时（延时）投递，最长支持 40 天。
顺序消息：允许消息消费者按照消息发送的顺序对消息进行消费。

 
 
 
重置消费位点(删除不需要信息)、死信队列（三天内处理多次重试异常的消息）、全球消息路由（不同地区同步数据）


I.不丢问题：
1.生产者带comfom机制、消费者正确消费以后再返回、中间件成功后再给生产者响应。
2.rocketMQ有事务消息；发送后会回调查询成功后再真实放入队列。

I.重复问题:
幂等(防重表、全局ID是否消费_版本号、状态机、数据库乐观锁version)

I.有序问题(全局有序、部分有序)
rocketMQ提供顺序消息
单消费者-组成一队后>工作线程消费



 
 
 
 
 ------------------------------------------------------
 
 zookeeper 应用场景
 I.配置管理
 II.DNS服务
 I.组成员管理
 I.分布式锁
 
 
 
 不适用场景:
 不能存大量数据
 
 zookeeper 数据模型：层次模型、文件系统模型

1.文件系统的树形结构便于表达数据之间的层次关系
2.文件系统的树形结构便于为不同的应用分配独立的命名空间

zk层次模型称作data tree:每个节点叫做znode


data tree接口:
znode数据只支持全量写入和读取。
data tree所有api都是wait free,互相API调用不会影响。



znode分类
可以持久可以临时

可以顺序行，单调递增整数
1.持久znode
2.临时znode
3.持久顺序znode 
4.临时顺序znode




master worker架构

1.只有一个master
2.多个 backup master . active master 失败了，一个backup master 升级
3. master 监控worker状态

 
 zookeeper 服务发现
 
 
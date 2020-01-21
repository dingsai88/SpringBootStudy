


这就意味着，一个用户成功建立连接后，即使你用管理员账号对这个用户的权限做了修改，也不会影响已经存在连接的权限。








索引的出现其实就是为了提高数据查询的效率，就像书的目录一样。



I.索引常见的数据结构：
哈希表:等值查询快，不能范围查询;更新维护快memcache:redis
有序数组:等值查询快，范围查询快(二分法O(logn));更新麻烦，适合静态存储
搜索树:
1.更新和查询都是O(log(N));不用二叉树(索引不止存在内存中，还要写到磁盘上)
2.使用N叉树，取决于数据块的大小。

跳表(跳跃列表):有序的链表，增加索引，比原有索引路径短。

LSM树(LogStructuredMergeTree)写性能高:两个或以上的存储结构组成:内存中C0结构(树)+磁盘中C1等结构(树)
一个存储结构常驻内存中，称为C0 tree 
当C0大小达到某一阈值时或者每隔一段时间，将C0中记录滚动合并到磁盘C1中


I.根据数据结构，决定应用场景.





页分裂

除了性能外，页分裂操作还影响数据页的利用率。原本放在一个页的数据，现在分到两个页中，整体空间利用率降低大约 50%。


你可能在一些建表规范里面见到过类似的描述，要求建表语句里一定要有自增主键。当然事无绝对，我们来分析一下哪些场景下应该使用自增主键，而哪些场景下不应该。




自增主键的适用场景：
业务逻辑字段作为主键：只有key value


https://blog.csdn.net/u011389515/article/details/80192996


有一道MySQL的面试题，为什么MySQL的索引要使用B+树而不是其它树形结构?比如B树？

现在这个问题的复杂版本可以参考本文；

他的简单版本回答是：

因为B树不管叶子节点还是非叶子节点，都会保存数据，这样导致在非叶子节点中能保存的指针数量变少（有些资料也称为扇出），指针少的情况下要保存大量数据，只能增加树的高度，导致IO操作变多，查询性能变低；


06 | 全局锁和表锁 ：给表加个字段怎么有这么多阻碍？



全局锁
表级锁
行锁


MySQL 提供了一个加全局读锁的方法
Flush tables with read lock (FTWRL)
-- 打开全局锁
FLUSH TABLES WITH READ LOCK ;
-- 解除全局锁
UNLOCK TABLES;

数据更新语句（数据的增删改）
数据定义语句（包括建表、修改表结构等）
更新类事务的提交语句

场景:全局锁的典型使用场景是，做全库逻辑备份


mysqldump 使用参数–single-transaction 的时候，
导数据之前就会启动一个事务，来确保拿到一致性视图。


set global readonly=true 和 FTWRL(flush tables with read lock)
一是，在有些系统中，readonly 的值会被用来做其他逻辑，比如用来判断一个库是主库还是备库。因此，修改 global 变量的方式影响面更大，我不建议你使用。

二是，在异常处理机制上有差异。如果执行 FTWRL 命令之后由于客户端发生异常断开，那么 MySQL 会自动释放这个全局锁，整个库回到可以正常更新的状态。而将整个库设置为 readonly 之后，如果客户端发生异常，则数据库就会一直保持 readonly 状态，这样会导致整个库长时间处于不可写状态，风险较高。
 DML(DataManipulationLanguage)数据操纵语言





表级锁MySQL 

里面表级别的锁有两种：
一种是表锁，
一种是元数据锁（meta data lock，MDL)。


表锁的语法是 lock tables … read/write。与 FTWRL 类似，
可以用 unlock tables 主动释放锁，也可以在客户端断开的时候自动释放。
需要注意，lock tables 语法除了会限制别的线程的读写外，也限定了本线程接下来的操作对象


即使是小表，操作不慎也会出问题





 


事务支持是在引擎层实现的


MyISAM 引擎就不支持事务

ACID（Atomicity、Consistency、Isolation、Durability，即原子性、一致性、隔离性、持久性）

SQL 标准的事务隔离级别包括：
读未提交（read uncommitted）:其他事务可以读取未提交的数据,脏读 dirty read
读提交（read committed）不可重复读:
可重复读（repeatable read）:幻读Phantom Read(A事务读取某个范围的数据，B事务插入记录，A事务再读出现幻行Phantom Row)
串行化（serializable ）能读


Oracle 数据库的默认隔离级别其实就是“读提交”read committed
Mysql 默认隔离级别是 可重复读repeatable read
 

1、脏读：事务A读取了事务B更新的数据，然后B回滚操作，那么A读取到的数据是脏数据
　　2、不可重复读：事务 A 多次读取同一数据，事务 B 在事务A多次读取的过程中，对数据作了更新并提交，导致事务A多次读取同一数据时，结果 不一致。



08 | 事务到底是隔离的还是不隔离的？

我在第三章和你讲事务隔离级别的时候提到过，如果是可重复度隔离级别，事务T启动
的时候会创建一个视图read-view,之后事务T执行期间，即使有其他事务修改了数
据，事务T看到的仍然跟在启动看到的一样。也就是说，一个在可重复度隔离级别下执行
的事务，好像与世无争，不受外接影响。

但是，我在上一篇文章中，和你分享行锁的时候又提到，一个事务要更新一行，如果刚好有
另外一个事务拥有这一行的行锁，他又不能这么超然了，会被锁住，进入等待状态。问题是，
既然进入了等待状态，那么等到这个事务自己获取到行锁要更新数据的时候，他读到的值又是什么呢？


start transaction with consistent snapshot;
update product set expire_period=expire_period+1  where id=69;
SELECT id,expire_period from product where id=69;
COMMIT;


begin /start transaction 命令并不是一个事务的起点，在执行到他们之后的第一个操作
innodb表的语句，事务才真正启动。

如果你想马上启动一个事务，可以使用start transaction with consistent snapshot;

第一种启动方式，一致性视图是在执行第一个快照读语句时创建的；
第二种启动方式，一致性视图是在执行start transaction with consistent snapshot时候创建的。

整个专栏里面，默认都是autocommit=1都是默认自动提交的。


在这个例子中，事务C没有显示地使用begincommit 标识这个update语句不嗯是就是一个事务，
语句完成的时候会自动提交。事务B在更新了行之后查询事物A在一个制度事务中查询，
并且时间顺序上是在事务B的查询之后，
这时，如果我告诉你事务B查到的k值是3，而事务A查到的K值是1，你是不是感觉有点晕呢


在mysql里，有两个视图的概念

一个是view。它是一个用查询语句定义的虚拟表，在调用的时候执行查询语句并生成结果
。创建视图的语法是create view 而它的查询方法与表一样。

另一个是innodb 在实现MVCC时用到的一致性读视图，即 consistent read view ,用于
支持 RC read committed 读提交和 RR Repeatable read 可重复度隔离级别的实现。

他没有物理结构，作用是事务执行期间用来定义 我能看到什么数据。


我跟你解释过一遍MVCC的实现逻辑。
今天为了说明查询和更新的区别，我们换一个方式来说明，把readview 拆开
你可以结合这两篇文章的说明来更深一步地地接mvcc


快照在MVCC里是怎么工作的？
在可重复读隔离级别下,事务在启动的时候就拍了个快照。注意，这个快照是基于整库的。

未提交读  脏读
提交读   不可重复读
可重复读  幻行

这时，你会说这看上去不太现实啊。如果一个库有100G，那么我启动一个事务，mysql
就要拷贝100G数据出来，这个过程得多慢啊。可是，我平时的事务执行起来很快啊。

实际上，我们并不需要拷贝出100G的数据。我们先来看看这个快照是怎么实现的。

innodb里面每一个事务都有一个唯一的事务ID，叫做transaction id.他是在事务开始的时候向
innodb的事务系统申请的，是按申请顺序严格递增的。


而每行数据也都是有多个版本的。每次事务更新数据的时候，都会生成一个新的数据版本，
并且吧transactionid 复制给这个数据版本的事务ID，记为 rowtrxid。同时，旧的数据版本
要保留，并且在新的数据版本中，能够有信息可以直接拿到它。

也就是说，数据表中的一行记录，其实可能有多个版本row，每个版本有自己的row trxid

如图2所示，就是一个记录被多个事务联系更新后的状态。


图中虚线框里是同一行数据的四个版本，当前最新版本是V4，k的值是22，它是被
transaction id为25的事务更新的，因为他的row trxid也是25.

你可能会问，前面的文章不是说，语句更新会生成 undo log回滚日志吗？那么
undo log在哪呢？

实际上，图2中的三个虚线箭头，就是undolog；而V1，V2v3并不是屋里上真实存在的，
而是每次需要的时候根据当前版本和undolog计算出来的。




 -- start transaction with consistent snapshot;


 update product set expire_period=1  where id=69;
-- COMMIT;
SELECT id,expire_period from product where id=69;



1.查看当前会话隔离级别
select @@tx_isolation;

2.查看系统当前隔离级别
select @@global.tx_isolation;

3.设置当前会话隔离级别
set session transaction isolatin level repeatable read;

4.设置系统当前隔离级别
set global transaction isolation level repeatable read;

set global transaction isolation level SERIALIZABLE;
set global transaction isolation level  READ   UNCOMMITTED;
set global transaction isolation level READ   COMMITTED;

5.命令行，开始事务时
set autocommit=off 或者 
start transaction|start transaction with consistent snapshot;

明白了多版本和row trxid的概念以后，我们再来想一下，innodb是怎么定义那个
100G的快照的。

按照可重复度的定义，一个事务启动的时候，能够看到所有已经提交的事务结果。但是之后，
这个事务执行期间，其他事务的更新对它不可见。

因此，一个事务只需要在启动的时候声明说，以我启动的时刻为准，如果一个数据版本是
在我启动之前成的，就人；如果是我启动以后才生成的，我就不认，我必须要找到他的上一个版本。

当然如果上一个版本也不可见，那就的继续往前找。那就得继续往前找。还有，如果是这个事务自己更新的数据
他自己还是要人的。
在实现上，innodb为每个事务构造了一个数组，用来保存这个事务启动顺序，当前正在活血的
所有事务ID。活跃值得就是 启动了但还没提交。

数组里面事务ID的最小值记为低水位，当前系统里面已经创建过的事务ID的最大值家1记为高水位。

这个事务数组和高水位，就组成了当前事务的一致性视图read view
而数据版本的可见性规则，就是基于数据的rowtrxid和这个一致性视图的对比结果得到的。

这个视图数组把所有的rowtrxid分成了集中不同的情况。

这样，对于当前事务启动瞬间来说，一个数据版本的rowtrxid有以下集中可能

1.如果落在绿色区域，标识这个班是已提交的事务或者是当前事务自己生成的，这个数据是课件的。

2.如果落在红色部分，标识这个版本是由将来启动的事务生成的，是肯定不可见的。

3.如果落在黄色部分，那就包括两种情况
a若row trxid在数组中，标识这个版本是由还没提交的事务生成的饿，不可见；
b若rowtrxid不在数组中，标识这个版本是已经提交了的事务生成的课件。





比如，对于图2中的数据来说，如果有一个事务，它的低水位是18，那么当他访问这一行数据时，
就会从V4通过U3计算出V3，所以在它看来，这一行的值是11.

你看，有了这个生命后，系统里面随后发生的更新，是不是就跟这个事务看到的内容无关了呢？
因为之后的更新，生成的版本一定属于上面的2或者32的情况，而对他来说，这些数据
是不存在的，所以这个事务的快照就是静态的了。

所以你现在知道了 innodb利用了所有数据都有多个版本的这个特性，实现了秒级创建快照的能力。


接下来，我们继续看一下图1中的三个事务，分析下事务A的语句返回的记过，为什么是K=1


这里，我们不妨做如下假设：
1.事务A开始前，系统里面只有一个活跃事务ID是99
2.事务ABC的版本号分别是100.101，102 且当前系统里只有这是个事务；
3.三个事务开始前 1，1 这一行数据的 row trxid是90

这样，事务A的视图数组就是99 100 事务B的视图数据就是99 100 101 

事务C就是 99 100 101 102

为了简化分析，我先吧其他干扰语句去掉，只画出跟事务A 查询逻辑有关的操作。

从图中可以看到，第一个有效更新是事务C，吧数据从1改成2，这个时候，这个数据
的最新版本的row trxid 是102，而90 这个版本已经成为了历史版本。

第二个有效更新是事务B，吧数据从2改成了3 这个是，这个数据最新版本 row trxid是101，而102又称为了历史版本。

你可能注意到了，在事务A查询的时候，其实事务B还没有提交，但是它生成的3这个
版本已经变成当前版本了。但这个版本对事务A必须是不可见的，否则就变成脏读了。

好，现在事务A要来读数据了，他的视图数组是99 100 当然了，读数据都是从当前版本读起的。
所以，事务A查询数据的度流程是这样的。

找到3的时候，判断row trxid101 比高水位打，处于红色区域，不可见；
接着，找到上一个历史版本 一看 row trxid 102比高水位打，处于红色区域，不可见

再往前找，终于找到了1 他的rowtrxid90 比低水位小，处于绿色区域，可见。

这样执行下来，虽然期间这一行数据被修改过，但是事务a不论在什么时候查询，看到这行
数据的结果都是一致的，所以我们称之为一致性读。

这个判断规则是从代码逻辑直接转移过来的，但是正如你缩减，用于人肉分析可见性很麻烦

所以我来给你翻译一下。一个数据版本，对于一个事务视图来说，除了自己的跟新总是课件以为，有三种情况。

1.版本未提交 不可见
2. 版本已提交，但是是在事务创建后提交的，不可见
3.版本已提交，而且是在事务创建钱提交的，尅建。

现在，我们用这个规则来判断图4中的查询结果，事务A的查询语句的事务数组是在事务A
启动的时候生成的，这时候：
3还没提交属于1不可见
2提交了，但是是在事务数据创建以后提交的属于情况2 不可见
1.1是在事务数组创建之前提交的课件。

你看，去掉数字对比后，只用是假先后顺序来判断，分析起来是不是轻松多了。所以后面
我们就多用这个规则来分析。


更新逻辑

细心的同学可能有疑问：事务B的update语句，如果按照一致性读，好像结果不对哦

你看图5中，事务B的视图数组是先生称的，之后事务C才提交，不是应该不可见吗
增算出来3


是的，如果事务B在更新之前查询一次数据，这个查询返回的K的值确实是1

但是，当他要去更新数据的时候，就补鞥呢在历史版本上更新了，否则事务C的跟你下能就丢失了。
因此，事务B此时的set k=k+2 实在2的基础上进行的操作。

所以，这里就用到了这样一条规则：更新数据都是先读后写的，而这个读，只能读当前的值，称为当前度。
current read
因此，在更新的时候，当前读拿到的数据是2，更新后生成了新版本数据3.这个新版本的row trxid101


所以，在执行事务B查询语句的时候，一看自己的版本好是101 ，最新数据的版本号也是101
是自己的更新，可以直接使用，所以查询得到的k值是3.

这里我们提到了一个概念，叫做当前读，其实，除了updae语句外，selece语句如果加锁，也是当前度。

所以，如果吧事务A的查询语句 修改以下，加上 lock in share mode 或者for update ,耶都可以读到版本好191
的数据，返回的K 的只是3。下面这两个select语句，就分别加锁了 读锁s锁 共享锁，和写锁X锁 排他锁。
select k from t where id lock in share mode ;
select k form t where id=1 for update;

再往前异步，假设事务C不是马上提交，而是变成下面的事务C 会曾阳呢。

事务C是不同是，更新后没有马上提交，在提交前，事务B的更新语句先发起来。
前面说过了，虽然事务C还没交，大事2这个版本有几个生成了，并且是当前的最新版本。
那么事务B的更新语句会怎么处理呢

这时候，我们在上一篇文章中提高的，两阶段锁协议就要上场了。事务C没提交，也就是说2这个版本的写锁还没有释放

二十五B是当前度，必须要读最新版本，而且必须加锁，因此就被锁住了，必须等到事务C释放这个锁，才能继续他的当前度。

到这里，我们把一致性读、当前读、行锁就串起来了。

现在，owm就再回到文章开头的问题：事务的可重复度的能力是怎么实现的

可重复度的核心就是一致性读 consistentread 儿事务更新数据的时候，只能用当前度。
如果当前的记录的行锁被其他事务占用的话，就需要进入锁等待。
而读提交的逻辑和可重复度的逻辑类似，他们最主要的区别是：
在可重复度隔离级别下，只需要在事务开始的时候创建一致性视图，
之后事务里的其他查询都公共一个一致性视图。


在读提交隔离级别下，每一个语句执行前都会重复算出一个新的视图。


那么，我们再看一下，在读提交隔离级别下，事务A和事务B的查询语句查询到的K，分别应该是多少呢

这里需要说明一下， start transaction with consistent snapshot 的意思是从这个语句开始
，床架那一个持续整个事务的一致性快照。所以，在读提交隔离级别下，这个用户就没意义了，
等效于普通的 start transaction。

下面是读提交时的状态图，可以看到这两个查询语句的创建视图数组的实际发生了变化，就是图中的readview框

这里，我们用的还是事务C的逻辑直接提交，而不是事务C


小结
innodb的行数据有多个版本，每个数据版本有自己的row trxid，每个事务或者语句有自己的
一致性视图。普通查询语句是一致性读，一致性读会根据 row trxid和一致性视图确定
数据版本的可见性。

对于可重复度，查询只承认在事务启动前就已经提交完成的数据
对于读提交，查询只承认在语句启动前就已经提交完成的数据。

而当前读，总是读取已经提交完成的最新版本。

你也可以想一下，为什么表结构不支持 可重复读？这是因为表结构没有对应的航数组，
也没有row trxid 因此只能遵循当前读的逻辑

当然 mysql 8已经可以把表结构放在innodb字典里了，也许以后会支持表结构的可重复度。
有道思考题时间了。我用下面的表结构和初始化语句座位试验环境，事务隔离级别是可重复度。
现在，我要吧所哟字段C和ID值相等的行 C值清零，但是却发现了一个诡异的改不掉的情况。请你构造出这种情况，并
说明其原理。







默认事务自动提交;

事务启动方式:

1.begin/start transaction; 第一个操作语句后才真正生效;

2.start transaction with consistent snapshot; 立刻生效



视图:
1.view 虚拟表
2.MVCC 一致性视图 consistent read view;支持RC提交读和RR重复读的实现


一致性读视图 read view
分为:
1.查询
2.更新

事务启动时，基于整库建立一个快照;



事务日志:存储引擎修改表数据时，只修改内存数据拷贝，再把修改行为追加到硬盘上的事务日志。
然后内存中被修改的数据在后台慢慢刷回磁盘。

innodb 的MVCC 每行记录增加两个隐藏列
1.行的创建时间
2.行的过期时间（删除时间）
 
不是真实的时间是系统版本好 .
没开始一个新的事务，系统版本号都会自动递增。


可重复度RR下的

select
a.只查找遭遇当前事务版本的数据和。
b.行删除版本找未定义，要么大于当前事务版本号；

insert 
保存当前系统版本号。行版本号。

delect
删除每一行报错当前系统版本号作为删除标识。

update
为插入一行新纪录，报错当前系统版本号作为行版本号，同时保存当前系统版本号到原来的行作为行删除标识。





redo log 是独立的log日志

执行更新操作时，redo log 会被写入log buffer ；当
commit 时，log buffer 刷新到磁盘。


undo log
回滚用，copy事务钱的数据内存到undo buffer,在适合的时间吧undo buffer 刷新到磁盘。


redo undo 都是环形缓冲


rollback segment：每个undo log被划分成多个段，每段叫rollback segment





Innodb每行增加三个隐藏字段
6字节的事务ID(DB_TRX_ID)
7字节的回滚指针(DB_ROLL_PTR)
隐藏ID



原子性、一致性、持久性通过数据库的redolog 和undo log完成。
redolog重组哦日志，保证事务的原子性和持久性。顺序写
undolog保证事务的一致性。 进行随机读写

(隔离性)


持久性:redo log
1.内存redo log:redo log buffer
2.重做日志文件redo log file

commit时,事务的所有日志写入到日志文件持久化。

undo log帮助事务回滚和mvcc。





undo log事务回滚操作。
存放在数据库内部的一个特殊段segment中，成为undo段(undo segment)






每个事务有个版本
事务版本号

事务 A 的视图数组就是 [99,100]


事务 B 的视图数组是 [99,100,101]

事务 C 的视图数组是 [99,100,101,102]



transaction id 记为 row trx_id

一行记录可以有多个 trx_id


undo log
102
101
100
99


读分为:
一致性读
当前读 current read



更新数据都是先读后写的，而这个读，只能读当前的值，称为“当前读”（current read）。



事务的可重复读的能力是怎么实现的？可重复读的核心就是一致性读（consistent read）；而事务更新数据的时候，只能用当前读。如果当前的记录的行锁被其他事务占用的话，就需要进入锁等待。



InnoDB 的行数据有多个版本，每个数据版本有自己的 row trx_id，每个事务或者语句有自己的一致性视图。普通查询语句是一致性读，一致性读会根据 row trx_id 和一致性视图确定数据版本的可见性。对于可重复读，查询只承认在事务启动前就已经提交完成的数据；对于读提交，查询只承认在语句启动前就已经提交完成的数据；





redo log（重做日志）和 binlog（归档日志）


WAL 技术，WAL 的全称是 Write-Ahead Logging，它的关键点就是先写日志，再写磁盘



InnoDB 引擎就会先把记录写到 redo log（粉板）里面，并更新内存，这个时候更新就算完成了。


同时，InnoDB 引擎会在适当的时候，将这个操作记录更新到磁盘里面，而这个更新往往是在系统比较空闲的时候做，这就像打烊以后掌柜做的事。



InnoDB 的 redo log 是固定大小的，比如可以配置为一组 4 个文件，每个文件的大小是 1GB，那么这块“粉板”总共就可以记录 4GB 的操作。从头开始写，写到末尾就又回到开头循环写，如下面这个图所示。


，而 Server 层也有自己的日志，称为 binlog（归档日志）



redolog是InnoDB 引擎特有的；binlog是MySQ的Server 层实现的，所有引擎都可以使用。
redolog是物理日志，记录的是“在某个数据页上做了什么修改”；binlog 是逻辑日志，记录的是这个语句的原始逻辑。
redolog是循环写的，空间固定会用完；binlog 是可以追加写入的。“追加写”是指 binlog 文件写到一定大小后会切换到下一个，并不会覆盖以前的日志。





两阶段提交保证 redolog和binglog一致

1.执行器调用存储引擎找到这一行（内存或硬盘）
2.数据运算加一调用存储引擎接口记录
3.存储引擎将数据更新到内存中，写入redolog处于prepare状态，告诉执行器已完成。
4.执行器生成操作binlog，并写入磁盘
5.执行器调用存储引擎提交事务接口，redolog 改成commit状态


为什么必须有“两阶段提交”呢？这是为了让两份日志之间的逻辑一致。





CREATE TABLE `t` (
  `id` int(11) NOT NULL,
  `a` int(11) DEFAULT NULL,
  `b` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a` (`a`),
  KEY `b` (`b`)
) ENGINE=InnoDB;




delimiter ;;
create procedure idata()
begin
  declare i int;
  set i=1;
  while(i<=100000)do
    insert into t values(i, i, i);
    set i=i+1;
  end while;
end;;
delimiter ;
call idata();



mysql> select * from t where a between 10000 and 20000;



set long_query_time=0;
select * from t where a between 10000 and 20000; /*Q1*/
select * from t force index(a) where a between 10000 and 20000;/*Q2*/














XA协议包含
两阶段提交（2PC）
三阶段提交（3PC）两种实现





I.



查询日志:0723-PC.log


拒绝访问的请求access denied
未能正确执行的SQL




select * from mysql.general_log;



二进制日志binary log



作用:
恢复recovery:数据恢复需要，进行point-in-time恢复

复制replication:一般master slave

审计audit:判断是否有攻击或者异常操作

0723-PC-bin.000001默认文件
0723-PC-bin.000003
0723-PC-bin.index索引文件




max_binlog_size
单个文件的最大值，超过新建文件，后缀+1，记录到.index文件


binlog_cache_size默认32K:未提交的日志记录在缓冲区:每个会话有一个

sync_binlog默认值0:是否同步写入磁盘

binlog-do-db默认为空:需要写入那些到日志
binlog-ignore-db默认为空:需要忽略那些到日志


log-slave-update:slave时候不会记录master的binlog。打开就会本地也记录binlog




show global status like 'binlog_cache%';查看缓冲次数和临时文件写入次数


Binlog_cache_use使用缓冲次数
Binlog_cache_disk_use使用临时文件写的次数



set @@session.binlog_format='ROW'


binlog_format:binlog记录格式

statement、row、mixed

statement:记录的是逻辑sql
row:记录表更改的情况。建议隔离级别read commited
mixed:记录的是逻辑SQL，有一些情况下会使用row格式。
1.存储引擎为NDB
2.使用了UUID()、user()、current_user()、fund_rows()、row_count()等不确定函数
3.使用了insert delay语句 延迟插入.
4.使用了用户定义函数UDF
5.使用了临时表temporary table




mysqlbinlog --start-position=203 C:\ProgramData\MySQL\MySQL Server 5.6\Data\0723-PC-bin.000003





mysqlbinlog.exe --no-defaults --database=db   c:\zmysqltemp\0723-PC-bin.000003 | more



--no-defaults --database=db  --base64-output=decode-rows -v --start-datetime='2019-04-11 00:00:00' --stop-datetime='2019-04-11 15:00:00'  mysql-bin.000007 >/tmp/binlog007.sql



mysqlbinlog.exe --no-defaults --database=db  --base64-output=decode-rows -v   c:\zmysqltemp\0723-PC-bin.000003 >test.sql








套接字文件socket:unix内核下用于连接(mysql.sock)


pid文件:启动时进程ID写入文件中(0723-PC.pid)


表结构定义文件:user.frm






innodb存储引擎文件:



表空间文件(ibdata1)ibdata1:12M:autoextend:

 可设置多个在不同磁盘

show variables like '%innodb_data_file_path%';







show variables like 'innodb_file_per_table';(ON的时候,独立表空间:表名.ibd)



tb_tdc_user.ibd


数据、索引、插入缓冲bitmap等信息;其余信息还在默认表空间





重做日志文件:ib_logfile0、ib_logfile1


至少一个重做日志文件组group,每组下至少两个重做日志.


innodb_log_file_size(老版4G,新版512G):每个重做日志的大小:show variables like '%innodb_log_file_size%';

innodb_log_files_in_group(默认2):重做日志个数

innodb_mirrored_log_groups(默认1)：日志镜像文件组数量，1表示只有日志文件组，没有镜像。

innodb_log_group_home_dir路径:






体系架构

后台线程:
1.master thread
缓冲池数据异步刷新到磁盘，保证数据一致性
脏页刷新、合并插入缓冲、undo页回收。

2.IO Thread
innodb使用AIO asyncIO
包含四个IO线程:
write、read、insert buffer、log IO thread

3.purge thread清除线程
回收undolog

4.page cleaner thread
脏页刷新操作线程


内存:
1.缓冲池
磁盘读到缓存池中:页FIX缓冲池中。
读取页时判断是否在缓冲池，命中直接读取。
修改数据也是修改缓冲池中的页，然后刷新到磁盘（checkpoint）


show variables like 'innodb_buffer_pool_size';


缓冲池-数据页类型:

索引页
数据页
undo页
插入缓冲insert buffer
自适应哈希索引 adaptive hash index
innodb锁信息lock info
数据字典信息data dictionary


设置多个缓冲池
show variables like 'innodb_buffer_pool_instances';




2.LRU list 、Free List 、Flush List

缓冲池最少使用算法管理LRU。

缓冲池页默认大小16K

Free List空闲页列表Free buffers空闲数量

缓冲池运行状态:
select pool_id,hit_rate,pages_made_young,pages_not_made_young from information_schema.innodb_buffer_pool_stats;

缓冲池LRU列表 space
select * from information_schema.innodb_buffer_page_lru where space=1;


LRU列表中的页被修改后，脏页dirty page（缓冲池和磁盘不一致）.
通过checkpoint机制刷新脏页dirty page到磁盘。

Flush列表中的页：脏页列表。

脏页既存在LRU列表中也存在Flush列表。
查看脏页:
show engine innodb status ;
select * from information_schema.innodb_buffer_page_lru where oldest_modification>1;







3.重做日志缓冲(默认8mb)
redo log buffer

show variables like 'innodb_log_buffer_size';

刷新到重做日志文件中:
1.master thread每秒刷新到重做日志文件中;
2.每个事务提交时刷新
3.重做日志缓冲小于二分之一时。





4.额外的内存池
内存堆heap







2.4 checkpoint 技术(脏页刷盘:缓冲页和磁盘不一致)

checkpoint检查点解决问题
1.缩短数据库的恢复时间
2.缓冲池不够用时，将脏页刷新到磁盘
3.重做日志不可用时，刷新脏页





最高优先级

多个循环组成loop:
主循环loop(每秒和10秒)
后台循环backgroup loop
刷新循环flush loop(脏页到磁盘)
暂停循环suspend loop

Master thread根据库运行状态在loop、backgroup 、flush、suspend切换

主循环loop每秒
重做日志缓冲刷新到磁盘，即使没有提交(总是)
合并插入缓冲(可能)
至多刷新100个innodb的缓冲池中的脏页到磁盘(可能)
没有活动切换到backgroundLoop(可能)

主循环loop10秒
刷新100个innodb的缓冲池中的脏页到磁盘(可能)
合并至多5个插入缓冲(总是)
将日志缓冲刷新到磁盘（总是）
删除无用的undo页(总是)
刷新100个或者10个脏页到磁盘(总是??)


关键特性：
插入缓冲 insert buffer
两次写 double write
自适应哈希索引adaptive hash index
异步IO asyncIO
刷新邻接页FlushNeighborPage



purge



当前没有用户活动，或者关闭数据库

删除无用的undo页(总是)
合并20个插入缓冲(总是)
跳回到主循环(总是)
不断刷新100个页直到符合条件>>跳转到flush loop完成




插入缓冲
1.insert buffer提高插入效率(是个B树)
同时满足两个条件:
索引是辅助索引
索引不是唯一




2.change buffer(插入缓存升级版B树) 


3.Insert buffer内部实现

4.merge insert buffer



两次写:可靠性



doublewrite

流程：
1.从page复制数据到 内存中的doublewrite buffer
2.从doublewrite buffer顺序写入到共享表空间
3.从doublewrite buffer写入到数据文件.ibd



自适应哈希(adaptive hash index)AHI数据库自优化self tuning(默认开启)

以该模式访问了100次
页通过该模式访问了N次，

show engine innodb status;





show engine innodb mutex;


show engine innodb status;


fsync




内存中重做日志缓冲redo log buffer
重做日志文件redo log file



fsync




2.log block

重做日志都是以512字节进行存储的.

日志缓存和日志文件都是块block每个512字节

重做日志头header 12字节
重做日志内容body 492字节存储内存
重做日志尾tailer 8字节


1比特bit 1个0、1；
1字节byte 8bit(ascii一个字节、utf8中3英1、unicode2字节)
1024字节=1KB


log buffer 类似数组，


log_block_hdr_no4字节:标记这个数组中的位置。标记数组最大2G

log_block_hdr_data_len2字节:表示log block占用大小。全部占用（0x200十六进制的200等于512字节）

log_block_first_rec_group2字节：第一个日志所在的偏移量；两个事务并存时存第二个事务的地址

log_block_checkpoint_no4字节:最后被写入的检查点的值




log_block_trl_no4字节：值和log_block_hdr_no相同









3.
log group：多个redolog

roud-robin:redo log file被写满，接着写下一个。



4.redo log日志格式






7.2.2 undo

存放在segment内undo segment(共享表空间内)

undolog需要持久性保护，所以也会产生redolog

记录更改的相反语句



purge




type_cmpl:更新有三种状态:non-delete-mark、将delete记录标记为not delete、将记录标记为delete

undo no:记录事务的ID

table_id:记录undo log对应的对象

所有的主键的列和值

start:2字节，记录undo log开始位置


select * from  INNODB_TRX_rollback_segment


1个或多个资源管理器resource managers:提供访问事务资源的方法。通常一个数据库就是一个资源管理器
一个事务管理器transaction manager：协调全局事务中的各个事务。参与全局事务的所有资源管理器进行通信
一个应用程序application program：定义事务边界，指定全局事务操作。

1.程序调用各个数据库进行事务操作
2.都执行没问题了
3.程序>各个资源管理器(库)要执行的命令>告诉事务管理器已经prpare
4.事务管理器>告诉各个资源管理器执行rollback还是commit;



1.程序>各个资源管理器(库)要执行的命令>告诉事务管理器已经prpare
2.事务管理器>告诉各个资源管理器执行rollback还是commit;



mysql --help|grep my.cnf





错误日志error log
show variables like 'log_error';

慢查询日志slow query log

show variables like 'long_query_time';

show variables like 'log_slow_queries';

查询日志log

二进制日志binlog


log_queries_not_using_indexes


select @@version ;


select @@tx_isolation;




select @@global.tx_isolation;





show global variables like "%datadir%";


show variables;

show global variables like "%datadir%";
show variables like 'log_error';

show variables like 'long_query_time';



show variables like 'slow_query_log';


show variables like 'slow_query_log_file';


show variables like 'log_queries_not_using_indexes';




mysqldumpslow 0723-PC-slow.log



执行时间最长的10条SQL:mysqldumpslow.pl -s al -n 10   > C:\0723-PC-slow.log



set global|session   
set @@global @@session



set

alter table mysql.slow_log engine=Innodb;




第四章 表



索引组织(index organized)：索引即数据
索引组织表(index organized table)每张表有primary key没有默认建立
1.非空唯一索引(第一个)
2.自动创建6字节大小的指针



innodb逻辑存储结构




表空间tablespace:
段segment
区extent
页page



表空间tablespace:
共享表空间ibddata1(undo信息、插入缓冲索引页、系统事务信息、二次写缓冲double write buffer)
独立表空间.ibd(数据、索引、插入缓冲bitmap)




段segment:
数据段
索引段
回滚段

索引组织(index organized)：索引即数据




区extent:连续的页组成的空间(固定1MB):64个连续page
默认情况下页page(16KB)

页大小可以调整,区extent大小不变



页page(16k)也叫块
数据页B-tree node
undo页undo Log Page
系统页system page
事务数据页transaction system page
插入缓冲位图页insert buffer bitmap
插入换从空闲列表页insert buffer free list
未压缩的二进制大对象页 uncompressed blob page
压缩的二进制大对象页 compressed blob page




行:innodb面向列的row-oriented
最多允许存放16K/2-200行记录，即7992行.





Innodb行记录格式



4.3.1 compact 行记录格式

一页存放的行数越多性能越高。





latinl




BLOB、LOB大对象存放在数据页外:也可以不存在溢出页面

varchar也可能存放为行溢出数据(65535指所有列的长度总和)




File Header 文件头(38字节):(页在表空间中的偏移值、上页、下页、LSN日志序列、)
Page Header 页头(56字节):(索引ID、可重用空间首指针、已删除记录字节数、最后插入位置、最后插入方向、页中记录数量、修改页的最大事务ID)
Infimun和Supremun Records:(虚拟行记录，infimun比该页任何主键都小的值、supremun主键都大的值)
User Records用户记录，行记录:实际存储记录的内容(B+树)
Free Space空闲空间:空闲空间(Link链表结构)
Page Directory 页目录:存放了记录的相对位置（不是偏移量）
File trailer文件结尾信息(8字节):检测页是否完整地写入磁盘，校验用






6.锁(数据完整性和一致性)

latch门闩锁(轻量级锁)锁定时间必须非常短:
mutex互斥量
rwlock读写锁

show engine innodb mutex;


lock对象是事务
锁表、页、行。
在commit或rollback才释放




show engine innodb status;
--当前运行的所有事务
select * from information_schema.innodb_trx;
--当前出现的锁
select * from information_schema.innodb_locks;
--锁等待的对应关系
select * from information_schema.innodb_lock_waits;





6.3 Inodb存储引擎中的锁

两种表中的行级锁(行级别):
共享锁 Slock 允许事务读一行数据
排他锁 Xlock 允许事务删除或更新一行数据



多粒度锁定granular锁定:允许事务在行级表级锁同时存在。

为了支持不同粒度加锁操作，新增意向锁 Intention lock

意向锁(IX)：将锁定对象分为多个层次，事务希望在更细粒度fine granularity加锁。



两种意向锁(表级别):表锁和行锁共存而使用了意向锁(只会和表级的X，S发生冲突)
1.意向共享锁IS lock，事务要获得一张表中某几行的共享锁
2.意向排他锁IX lock，事务要获得一张表中某几行的排他锁

申请行排他锁的时候自动申请表意向排他锁。
申请共享锁的时候自动申请表意向共享锁。

 IX，IS是表级锁，不会和行级的X，S锁发生冲突。只会和表级的X，S发生冲突

show engine innodb status;



6.3.2一致性非锁定读(consistent nonlocking read)
MVCC多版本并发控制，别人锁定也能读

6.3.3一致性锁定读
select for update;加排他锁读
select  lock in share mode;加共享锁读


6.3.4 自增长与锁
自增长计数器auto-increment counter
获得最新值:select max(id) from t for update;
在事务里立刻释放锁:增加并发性能


自增长插入分类:
insert-like :like语句
simple inserts :知道插入行数
bulk inserts:不能确定插入语句 insert select
mixed-mode inserts:一部分是自增长，一部分是确定行数。




show variables like 'innodb_autoinc_lock_mode';





innodb_autoinc_lock_mode：

0：老版本方式，通过表锁auto-inclocking
1:默认值,simple inserts用互斥量mutex;bulk inserts表锁auto-inc locking
2.都是互斥量mutex性能高，并发时自增长不是连续的。




 6.3.5外键和锁(完整性约束检查)
外键自动加索引(oracle外键不会自动加索引)
 
读主表的时候一定要用一致性锁定读或者写
 
 
 
 6.4 锁的算法
 
 行锁的3种算法
 Record lock:当个行记录上的锁:只有唯一索引的情况下才生效，辅助索引不生效。
 Gap lock:间隙锁，锁定一个范围，但是不包含记录本身。
 next-key lock:Gap lock+record lock，锁定一个范围，并且锁定记录本身。
 
 
 record lock总是会锁住索引记录。
 Next-key lock解决幻读问题
 
 
 previous key locking
 
 

 create table z(a int ,b int , key(a),key(b));


 create table z(a int ,b int ,primary key(a),key(b));
 insert into z select 1,1;
 insert into z select 3,1;
 insert into z select 5,3;
 insert into z select 7,6;
 insert into z select 10,8;
 
 
insert into z select 1,1;
 insert into z select 3,3;

 insert into z select 6,6;
 
 insert into z select 10,10;
 
 
 start transaction;
  SELECT * from z where b=3 for update;-- 锁定 a=5和[b(1-3)(3)(3-6),不含3和6]
 select * from z;
 
 被锁住
   SELECT * from z where a=5 lock in share mode;
    insert into z select 4,2;
	insert into z select 4,3;
	insert into z select 4,4;
    insert into z select 6,5;
 
 没被锁
  insert into z select 2,1;
  insert into z select 4,6;
  insert into z select 8,6;
 
 
 关闭gap lock：锁这个值前后已存在的值。
 隔离级别改成提交读。
 设置:innodb_locks_unsale_for_binlog:1
 
 
show variables like 'innodb_locks_unsafe_for_binlog';
 
 
I.
（2、3）没锁  推论1.辅助临界值没锁定
(4、3)  锁了  推论2.主键也锁了
(4、1) 没锁
 (2、4)锁了 辅助区间内 锁了
 
1.主键区间内会锁定 (错误)
2.附件索引只会锁定区间内不会锁定临界值
 
   insert into z select 2,4;
 
 
 
 
 
 6.5锁问题
 
 脏读:本事务读取到其他事务未提交的数据。
 
 不可重复读:提交读隔离级别下，本事务两次读取不一致。
 
 
 
 丢失更新:第二个事务覆盖第一个事务的更新（任何隔离级别都不会有问题）
 
 
 
 
 6.6阻塞：不同锁之间兼容性问题，等待另外一个事务释放占用资源。
 
 
 设置阻塞时间(默认50秒)
 set @@innodb_lock_wait_timeout=60;
 set @@innodb_rollback_on_timeout=on;不能修改
 
 超时后抛出异常，但是没有回滚或者提交（需要小心注意）
 
 
 
 6.7死锁:两个或以上事务，互相等待资源。

 
 
 解决死锁
 1. 锁超时时间
  set @@innodb_lock_wait_timeout=60;
 更新较多行的时候不适用undo log比较大。
 
 2.等待图wait for graph死锁检测(innodb采用的方式)
 wait for graph(深度优先算法)保存以下两种信息:
 锁的信息链表
 事务等待链表
 
 
 选择回滚undo量最小的事务
 
 
 死锁的概率：
 1.事务数量越多概率越大
 2.每个事务操作的数量越多概率越大
 3.操作数据的集合越小则概率越大
 
 
数据库会 回滚死锁的事务rollback




6.8 锁升级Lock escalation

innodb不存在锁升级的问题，sqlserver会锁升级.


6  44

2 3 55 99




 

select @@global.tx_isolation;


set global transaction isolation level repeatable read;



































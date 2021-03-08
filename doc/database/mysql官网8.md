


**I.MySQL 8.0的新增功能**


I.数据字典

.frm,.opt,.par,.TRN,.TRG,.isl文件都移除了，不再通过文件的方式存储数据字典信息。




I.InnoDB增强功能

II.自增ID入库记录，不在内存里记录了。
重新启动服务器将不再取消 AUTO_INCREMENT = Ntable选项的作用。如果将自动递增计数器初始化为特定值，或者将自动递增计数器值更改为更大的值，则新值将在服务器重新启动后保留。


II.NOWAIT 和SKIP LOCKED锁定读取并发 ( 仅适用于行级锁)
NOWAIT:no wait
使用NOWAIT永不等待的锁定读取将等待获取行锁。查询将立即执行，如果请求的行被锁定，则会失败并显示错误。

SKIP LOCKED: skip locked
使用SKIP LOCKED 永不等待的锁定读取将等待获取行锁。查询将立即执行，从结果集中删除锁定的行。



InnoDB支持 NOWAIT和SKIP LOCKED选项SELECT ... FOR SHARE以及SELECT ... FOR UPDATE锁定读取语句。 NOWAIT如果请求的行被另一个事务锁定，则导致该语句立即返回。SKIP LOCKED从结果集中删除锁定的行。请参阅 使用NOWAIT和SKIP LOCKED锁定读取并发。

SELECT ... FOR UPDATE或SELECT ... FOR SHARE事务必须等待


1、2、3 三条记录

SELECT * FROM `tb_tdc_email_user` where id=3 for update;
SELECT * FROM `tb_tdc_email_user` where id=3  lock in share mode;

SELECT * FROM `tb_tdc_email_user` where id=3 for update NOWAIT;
ERROR 3572 (HY000): Do not wait for lock.

SELECT * FROM `tb_tdc_email_user`  for update  SKIP LOCKED;
返回id=1、2的数据 跳过3



FOR SHARE 替换  LOCK IN SHARE MODE


I.字符集支持
默认字符集已从更改 latin1为utf8mb4


--------------------------------------------------------基础功能复习---------------------------------------------------------------------------
**基础功能复习**

-- 查询生日，两个日期差几年
SELECT name, birth, CURDATE(),   TIMESTAMPDIFF(YEAR,birth,CURDATE()) AS age  FROM pet;

-- 查询几月的生日
SELECT name, birth, MONTH(birth) FROM pet;

-- 日期 interval间隔 时间加减年月日
SELECT date,
DATE_ADD(date,INTERVAL 1 YEAR),
DATE_ADD(date,INTERVAL 1 MONTH),
DATE_ADD(date,INTERVAL 1 DAY)
FROM tb_tdc_call51_brief;

-- name是三个字符:xx苗、张浩楠
SELECT * FROM `t`  where name LIKE '___';

-- 正则表达式 匹配
SELECT * FROM pet WHERE REGEXP_LIKE(name, '^b');


**优化**

I.在数据库级别进行优化

I.在硬件级别进行优化
磁盘、CPU、带宽等

I.平衡便携性和性能

----------------------------------------------------

I.在数据库级别进行优化


**II.优化SQL语句**
优化查询的主要考虑因素：
1.where语句进行优化。是否加索引; EXPLAIN分析语句

2.尽量在结果集上加函数
3.最小化查询时 全表扫描 (full table scans)

4.ANALYZE TABLE(分析表) 定期使用该语句来使表统计信息保持最新 

**SHOW INDEX 查看表索引的散列程度** 查看表索引

SHOW INDEX FROM order_investment;

索引信息中的列的信息说明：
Table :表的名称。
Non_unique:如果索引不能包括重复词，则为0。如果可以，则为1。
Key_name:索引的名称。
Seq_in_index:索引中的列序列号，从1开始。
Column_name:列名称。
Collation:列以什么方式存储在索引中。在MySQLSHOW INDEX语法中，有值’A’（升序）或NULL（无分类）。
Cardinality:索引中唯一值的数目的估计值。通过运行ANALYZE TABLE或myisamchk -a可以更新。基数根据被存储为整数的统计数据来计数，所以即使对于小型表，该值也没有必要是精确的。基数越大，当进行联合时，MySQL使用该索引的机会就越大。
Sub_part:如果列只是被部分地编入索引，则为被编入索引的字符的数目。如果整列被编入索引，则为NULL。
Packed:指示关键字如何被压缩。如果没有被压缩，则为NULL。
Null:如果列含有NULL，则含有YES。如果没有，则为空。
Index_type：存储索引数据结构方法（BTREE, FULLTEXT, HASH, RTREE）

**ANALYZE  TABLE** 分析表 ，上边语句的Cardinality唯一值估算并不准确，分析后查询更快。

ANALYZE  table order_investment;


**CHECK TABLE**
检查表和视图  :试图里的表是否存在等问题
CHECK TABLE  order_investment ;


QUICK:不扫描行，不检查错误的链接。
FAST:只检查没有被正确关闭的表。
CHANGED:只检查上次检查后被更改的表，和没有被正确关闭的表。
MEDIUM:扫描行，以验证被删除的链接是有效的。也可以计算各行的关键字校验和，并使用计算出的校验和验证这一点。
EXTENDED:对每行的所有关键字进行一个全面的关键字查找。这可以确保表是100％一致的，但是花的时间较长。

**Optimize Table**
优化表，删除大段数据等以后操作
Optimize Table  order_investment  ;


**repair table**
数据表修复myisam用


5.不同存储引擎有不同的优化策略
innodb


6.Innodb优化只读事务
避免和已有事务设置TRX_ID的相关开销，可以开启只读事务，提升性能
START transaction read only;
https://dev.mysql.com/doc/refman/8.0/en/innodb-performance-ro-txn.html

7.避免以难以理解的方式转换查询，特别是当优化器自动执行某些相同的转换时。

8.当 sql 语句性能不佳时, 使用 EXPLAIN 查看 plan , 以便在 where , join 条件中使用索引. ( EXPLAIN 是所有调优的第一步)

9.优化缓冲池可以提升二次命中率
调整 mysql cache 的相关参数, 来高效使用 InnoDB 的 buffer pool; 
MyISAM 的 key cache, 和 MySQL query cache. 这回加速 sql 语句第二次及以后重复查询的执行速度. 因为查询结果可以直接从内存中获取.

10.高速缓存区也可以优化。例如占用更少的缓存区。
即使对于使用高速缓存区域快速运行的查询，您仍然可以进一步优化，使它们需要更少的高速缓存，从而使您的应用程序更具可扩展性。
可伸缩性意味着您的应用程序可以处理更多的并发用户、更大的请求等，而不会出现性能的大幅下降。

11.处理locking问题，其中其他会话同时访问表可能会影响查询速度。


**II.WHERE子句优化**

1.1删除不必要的括号、常量折叠、相同条件删除
1.2 index 上的常量表达式只会被计算一次
1.3 在单表上执行不带 where 条件的 coun(*), 可以直接从 MyISAM 的 table information , 或内存表中直接获取
1.4 如果不使用 GROUP BY , 则 HAVING 条件会被合并到 WHERE 中  join 表上的 where 条件可以让表跳过一些行
1.5 相比于 query 中的其它表, 常量表会被第一个读取. 常量表包括:
空表或只有一行的表
WHERE 条件执行在主键, 或唯一索引 (PRIMARY KEY or a UNIQUE index) 上的表
1.6 通过尝试所有可能的方法，找到用于联接表的最佳联接组合。如果ORDER BY 、GROUP BY子句中的所有列 都来自同一表，则在连接时优先使用该表。
1.7 如果ORDER BY和GROUP BY来自不同表，则会创建一个临时表。
1.8 如果使用SQL_SMALL_RESULT 修饰符，则MySQL使用内存中的临时表。
1.9 优化器优先会选择走索引，除非扫描索引达到表的30%以上,现在优化器更加复杂，不一定是固定的30%，还有其他因素例如：表大小，行数和I / O块大小。
1.10 某些情况下，查询可以不去查数据文件就能返回数据，从索引树就能获得数据。
1.11 在输出每一行之前，将跳过与HAVING子句不匹配的行。




**II.范围优化Range Optimization**


**II.索引合并优化**


**index-merge-intersection:索引合并-交集算法 and**
1.key_part1 = const1 AND key_part2 = const2 ... AND key_partN = constN



**index-merge-unin:索引合并-并集算法 or**
user_id=1000061540  or  manager_id= 338655

;
**index-merge-sort-union:索引合并-排序-并集算法 sort or**
user_id<1000061540  or  manager_id< 338655
先排序再返回行数据




**II.哈希联接优化**



**II.Index Condition Pushdown Optimization索引下推优化**

联合索引(name,age),name like '丁%' and age >10; 先走索引查询name,然后直接在索引里直接搜索age>10以后再回表到聚簇索引。




**II.IS NULL Optimization** 


**II. order by Optimization**:和回表一样，看索引能否覆盖返回值。

当order by 不能使用 索引时 就使用 filesort。

**两个字段联合索引返回全部数据:使用filesort**
index on (key_part1, key_part2)
filesort(文件排序): SELECT * FROM t1 ORDER BY key_part1, key_part2;
Using index(索引排序): SELECT pk, key_part1, key_part2 FROM t1 ORDER BY key_part1, key_part2;
Using index(索引排序): SELECT pk, key_part1, key_part2 FROM t1 where key_part1>1 ORDER BY key_part1, key_part2;



**II.GROUP BY Optimization**(联合索引下，全部字段是覆盖索引内的字段)
常规操作:扫整表并且创建包含连续的分组行的临时表.Using temporary;
避免临时表创建:所有的group by子句中的列属性必须来至同一个index(联合):**(所有列都是来自同一个索引)**

**Loose Index Scan:松散索引扫描**
1.单表上查询
2.group by子句中的列属性满足最左前缀原则.
3：select子句中的列属性只能包含min(),max()聚合函数，并且它们都引用group by中一个列属性。
4：查询语句中的index关键字（except group by子句的那些index关键字）必须为常量（意味着，他们必须通过 = constra的形式被引用），除非是min(),max();
5:index中所有列关键字，列值必须完整索引，而不是一个前缀索引，e.g. c1 varchar(20), index (c1(10)),该索引不会被loose index scan使用。

**Tight Index Scan:紧密索引扫描**
SELECT c1, c2, c3 FROM t1 WHERE c2 = 'a' GROUP BY c1, c3;
SELECT c1, c2, c3 FROM t1 WHERE c1 = 'a' GROUP BY c2, c3;



**II. DISTINCT优化**
大多数情况下，DISTINCT子句可被视为的特殊情况GROUP BY。

以下两个查询是等效的
SELECT DISTINCT c1, c2, c3 FROM t1
WHERE c1 > const;

SELECT c1, c2, c3 FROM t1
WHERE c1 > const GROUP BY c1, c2, c3;




**II.LIMIT查询优化**

当 ORDER BY 列的字段值存在重复，那么这条 ORDER BY 语句返回的数据顺序会因为LIMIT的存在而变得不一样：

https://blog.csdn.net/weixin_39604280/article/details/111689218

select * from ratings order by category,id;



**II.row-constructor-optimization行构造器表达式优化**
SELECT * FROM `a_study`  where (c1,c2)=(aaa,bbb);




**II.Avoiding Full Table Scans避免全表扫描**






















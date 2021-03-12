


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

**Optimize Table** 优化表
优化表，删除大段数据等以后操作
Optimize Table  order_investment  ;

一旦数据达到稳定的大小，或者正在增长的表增加了几十或几百兆字节，请考虑使用该OPTIMIZE TABLE语句重新组织表并压缩所有浪费的空间。
重组后的表需要较少的磁盘I / O来执行全表扫描。这是一种直接的技术，可以在其他技术（如改善索引使用率或调整应用程序代码）不可行时提高性能。

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





---------------------------------------------------------------------------------------------
优化数据更改语句



**INVISIBLE** 8.0新增功能
不可见索引隐士

加减索引很消耗性能、不可见所以可以测试删除索引以后的影响。


CREATE INDEX j_idx ON t1 (j) INVISIBLE;
ALTER TABLE t1 ADD INDEX k_idx (k) INVISIBLE;

ALTER TABLE t1 ALTER INDEX i_idx INVISIBLE;
ALTER TABLE t1 ALTER INDEX i_idx VISIBLE;


**降序索引 desc** 8.0新增功能
8.0 之前，两个排序顺序不一样的时候 就要filesort


8之前的创建语句。
CREATE TABLE t (
c1 INT, c2 INT,
INDEX idx1 (c1 ASC, c2 ASC),
INDEX idx2 (c1 ASC, c2 DESC),
INDEX idx3 (c1 DESC, c2 ASC),
INDEX idx4 (c1 DESC, c2 DESC)
);

建完以后的语句 已经忽略了 desc
CREATE TABLE `t` (
`c1` int(11) DEFAULT NULL,
`c2` int(11) DEFAULT NULL,
KEY `idx1` (`c1`,`c2`),
KEY `idx2` (`c1`,`c2`),
KEY `idx3` (`c1`,`c2`),
KEY `idx4` (`c1`,`c2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




explain select * from  t  ORDER BY c1 ASC, c2 ASC  ;  --  Using index;
explain select * from  t  ORDER BY c1 DESC, c2 DESC ; --  Using index;    
explain select * from  t  ORDER BY c1 ASC, c2 DESC ;  --  Using index; Using filesort
explain select * from  t  ORDER BY c1 DESC, c2 ASC  ; -- Using index; Using filesort



**data-size-Optimization 优化数据大小**

Table Columns 表字段，建议符合规定的最小值。
MEDIUMINT通常比INT因为MEDIUMINT列占用的空间少25％更好，这 是一个更好的选择。







**Temporary Table  临时表(Using temporary)** TempTable 、Memory、 Innodb磁盘
https://dev.mysql.com/doc/refman/8.0/en/internal-temporary-tables.html

EXPLAIN 并检查该 Extra列是否表明其 含义Using temporary

UNION、views、derived-tables派生表(as生成的表)、公用表表达式的求值
ORDER BY(非覆盖索引等) GROUP BY(非覆盖索引等)、

与DISTINCT结合的 评估ORDER BY可能需要一个临时表。
INSERT ... SELECT 

1.内部临时表可以保存在内存中，并由TempTable（默认值）或 MEMORY存储引擎处理，或者由InnoDB存储引擎存储在磁盘上。

以下情况不在内存创建临时表，而在磁盘创建:
I.表中存在:BLOB或 TEXT列
I.select String字段超过512 使用union 或者 union all的时候
I.超出temptable_max_ram限制



TempTable存储引擎管理时 :temptable_max_ram 限制 InnoDB 磁盘上的内部临时表。

MEMORY存储引擎管理时:



**表列数和行大小的限制**
列数限制:
4096列的硬限制 (InnoDB每个表的限制为1017列)


行大小限制:
65,535字节



**优化InnoDB事务管理**

1.默认的MySQL设置AUTOCOMMIT=1 ,如果流量很大的，SET AUTOCOMMIT=0或START TRANSACTION声明 。多个操作一起提交

2.只有 select 的最好打开只读事务

3.避免大量update insert delete 之后回滚
加大缓冲池、在缓存中修改不写入磁盘修改
进行设置， innodb_change_buffering=all 以便除了插入操作外还缓冲更新和删除操作。：允许将更新和删除操作缓存在内存中，从而使它们首先可以更快地执行，
小批量的先提交

4.undo日志不会立刻删除，提交了也不一定立刻删。
修改或删除行时，不会立即删除行和关联的 撤消日志，甚至不会在事务提交后立即删除。保留旧数据，直到更早或同时开始的事务完成为止，以便那些事务可以访问已修改或已删除行的先前状态。因此，长时间运行的事务可以防止InnoDB清除由其他事务更改的数据。


5.长时间运行的事务 由于可重复读，会有很多日志

6.当长时间运行的事务修改表时，来自其他事务的对该表的查询不会使用覆盖索引技术。通常可以从二级索引检索所有结果列，而从表数据中查找适当值的查询。


**优化InnoDB查询**
不建议主键过长，因为二级索引辅助索引都会重复保存它。
不建议每一列都加索引：尝试创建组合索引。最好都是覆盖索引  (覆盖索引：包含查询检索的所有列 的索引)
如果列不能包含null,请创建表的时候就指定，这样方便索引对查询更优。

https://dev.mysql.com/doc/refman/8.0/en/optimizing-innodb-queries.html



**优化InnoDB DDL操作** CREATE, ALTER,   DROP

InnoDB和在线DDL
创建不具有二级索引的表，然后在数据加载后添加二级索引，从而加快创建和加载表及关联索引的过程。

drop (删除表)：删除内容和定义，释放空间。简单来说就是把整个表去掉.以后要新增数据是不可能的,除非新增一个表。
drop语句将删除表的结构被依赖的约束（constrain),触发器（trigger)索引（index);依赖于该表的存储过程/函数将被保留，但其状态会变为：invalid。

truncate (清空表中的数据)没有undolog 不能恢复：删除内容、释放空间但不删除定义(保留表的数据结构)。与drop不同的是,只是清空表数据而已。
注意:truncate 不能删除行数据,要删就要把表清空。

delete语句是数据库操作语言(dml)，这个操作会放到 rollback segement 

执行速度，一般来说: drop> truncate > delete。

truncate、drop 是数据库定义语言(ddl)，操作立即生效，原数据不放到 rollback segment 中，不能回滚，操作不触发 trigger。 


**优化InnoDB磁盘I / O**
CPU使用百分比小于70％您的工作负载可能是磁盘绑定的
https://dev.mysql.com/doc/refman/8.0/en/optimizing-innodb-diskio.html

1.增加缓冲池大小  
可以通过查询重复访问它，而无需任何磁盘I / O。 系统内存的50％到75％


2.调整刷盘方法
fsync()



**优化InnoDB配置变量**
https://dev.mysql.com/doc/refman/8.0/en/optimizing-innodb-configuration-variables.html




**execution-plan-information 执行计划信息explain**


EXPLAIN作品有 SELECT， DELETE， INSERT， REPLACE，和 UPDATE语句。

EXPLAIN对于检查涉及分区表的查询很有用。


**explain  Output Format输出格式**format、traditional、json
https://dev.mysql.com/doc/refman/8.0/en/explain-output.html

该FORMAT选项可用于选择输出格式。TRADITIONAL以表格格式显示输出。如果不FORMAT存在任何选项，则为默认设置 。 JSONformat以JSON格式显示信息。

三种输出格式:默认(format=format) 
explain format=TRADITIONAL SELECT * FROM `tb_tdc_call51_brief`;
explain  SELECT * FROM `tb_tdc_call51_brief`;
explain format=json SELECT * FROM `tb_tdc_call51_brief`;


**explain各项说明 很重要**
https://dev.mysql.com/doc/refman/8.0/en/explain-output.html

id、select_type、table、type、possible_keys、key、key_len、ref、rows、Extra


**I.select_type**

II.SIMPLE:  简单SELECT（不使用 UNION或子查询）
explain SELECT * FROM a;

II.PRIMARY、UNION、DEPENDENT UNION、UNION RESULT
--执行出来有三个分析
explain SELECT id FROM a union SELECT id FROM b ;

PRIMARY：最外层 SELECT
UNION：SELECT陈述中的 第二条或更高条UNION
DEPENDENT UNION： 第二个或更高版本的SELECT语句 UNION，取决于外部查询
UNION RESULT：结果UNION

II.table:输出行所引用的表的名称。这也可以是以下值之一：
用到的表明
<unionM,N>：该行指的是具有和id值的行 的 M并集 N。
<derivedN>：该行是指用于与该行的派生表结果id的值 N
<subqueryN>：该行是指该行的物化子查询的结果






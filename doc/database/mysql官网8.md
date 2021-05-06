
I.DDL(Data Definition Language 数据定义语言)用于操作对象和对象的属性，
Create语句：可以创建数据库和数据库的一些对象。
Drop语句：可以删除数据表、索引、触发程序、条件约束以及数据表的权限等。
Alter语句：修改数据表定义及属性。

I.DML(Data Manipulation Language 数据操控语言)用于操作数据库对象中包含的数据，也就是说操作的单位是记录。
insert update Delete

DCL(Data Control Language 数据控制语句)的操作是数据库对象的权限，这些操作的确定使数据更加的安全。
InnoDB是一种兼顾了高可靠性和高性能的通用存储引擎。


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

**ANALYZE  TABLE** 分析表 ，上边语句的 Cardinality 唯一值估算并不准确，分析后查询更快。

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

SURNAME_SID无索引

explain SELECT * FROM tb_tdc_gjj_user GROUP BY SURNAME_SID ;


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

**I.table:输出行所引用的表的名称。这也可以是以下值之一：**
用到的表明
<unionM,N>：该行指的是具有和id值的行 的 M并集 N。
<derivedN>：该行是指用于与该行的派生表结果id的值 N
<subqueryN>：该行是指该行的物化子查询的结果



**I.partitions（JSON名： partitions） 查询将从中匹配记录的分区。该值适用NULL于未分区的表**
5.7 版本没看见



**I.type（JSON名： access_type）**
https://dev.mysql.com/doc/refman/8.0/en/explain-output.html#jointype_system
 

system>const>eq_ref>ref>range>index>ALL

system：1.只有一条数据的系统表 。 2.或衍生表只能有一条数据的主查询
const：(单表唯一可为空)仅仅能查出一条的SQL语句并且用于Primary key 或 unique索引；
eq_ref：(连表查唯一不可为空)token必须是非空非空非空唯一索引( 单独的唯一索引不可以) (explain SELECT * FROM t1 , t2 where t1.token=t2.token;)
ref: 单表时非唯一性索引等于匹配的行; 连表时 普通索引相等的
fulltext:使用FULLTEXT 索引执行联接。
ref_or_null:和ref很像多个or null，column='aa' OR  column is  null;
index_merge:索引合并优化  两个带索引的 and or 操作
unique_subquery：主键:value IN (SELECT primary_key FROM single_table WHERE some_expr)
index_subquery：普通索引:value IN (SELECT key_column FROM single_table WHERE some_expr)
range:  索引 in (1000,2000)、BETWEEN
index:和all 一样是全表扫描 只不过返回的是ID
ALL:全表扫描

unique_subquery:能出来的例子
explain select straight_join * from fund_product fp  join fund_manager fm on fm.fund_code = fp.fund_code where group_manager_id in (select id from fund_group_manager fgm where fm.user_name = fgm.user_name);

index_subquery:能出来的例子
explain select straight_join * from fund_product fp  join fund_manager fm on fm.fund_code = fp.fund_code where user_id in (select user_id from fund_group_manager fgm where fm.user_name = fgm.user_name);




CREATE TABLE `fund_product` (
`product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`fund_code` varchar(50) NOT NULL COMMENT '',
PRIMARY KEY (`product_id`),
UNIQUE KEY `fundcode` (`fund_code`),
KEY `super_baby` (`super_baby`) USING BTREE
) ;

CREATE TABLE `fund_manager` (
`manager_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '',
`fund_code` varchar(50) NOT NULL COMMENT '',
`user_name` varchar(50) DEFAULT '' COMMENT '',
PRIMARY KEY (`manager_id`),
KEY `fund_code` (`fund_code`) USING BTREE,
KEY `group_manager_id` (`group_manager_id`) USING BTREE
) ;


CREATE TABLE `fund_group_manager` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`user_name` varchar(50) DEFAULT '' COMMENT '',
PRIMARY KEY (`id`),
KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=553 DEFAULT CHARSET=utf8 COMMENT='';




**possible_keys（JSON名： possible_keys）**
可能用到的索引。
但不一定被查询实际使用。


SHOW INDEX FROM tbl_name

explain SELECT * FROM `tb_tdc_gjj_user`  FORCE INDEX(un_lp) where LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';
explain SELECT * FROM `tb_tdc_gjj_user`  FORCE INDEX(idx_LOCATION_CID)  where  LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';
explain SELECT * FROM `tb_tdc_gjj_user`  USE  INDEX(idx_LOCATION_CID)  where  LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';
explain SELECT * FROM `tb_tdc_gjj_user`  IGNORE   INDEX(un_lp)  where  LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';
explain SELECT * FROM `tb_tdc_gjj_user`  IGNORE   INDEX(un_lp,idx_LOCATION_CID)  where  LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';


CREATE TABLE `tb_tdc_gjj_user` (
`ID` int(11) NOT NULL AUTO_INCREMENT,
`USER_UUID` varchar(32) DEFAULT NULL,
`LOCATION_CID` varchar(32) NOT NULL COMMENT '',
`PASSPORT` varchar(20) NOT NULL COMMENT '',
PRIMARY KEY (`ID`),
UNIQUE KEY `un_lp` (`LOCATION_CID`,`PASSPORT`) USING BTREE,
KEY `idx_USER_UUID` (`USER_UUID`),
KEY `idx_LOCATION_CID` (`LOCATION_CID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



**key（JSON名：key）**
实际使用的索引: 如果MySQL决定使用possible_keys 索引之一来查找行，则将该索引列为键值。

1.NULL，则没有使用索引。（或者索引失效）
2.覆盖索引时，possible_keys=null  ，索引只出现在key explain SELECT LOCATION_CID,PASSPORT FROM `tb_tdc_gjj_user`  ;
3.数据量小有可能为null不使用索引
4.可以强制指定索引:
强制使用:FORCE INDEX(un_lp)、
推荐使用mysql自己决定是否使用 USE  INDEX(idx_LOCATION_CID)、
忽略某个索引IGNORE   INDEX(un_lp,idx_LOCATION_CID)


explain SELECT * FROM `tb_tdc_gjj_user`  FORCE INDEX(un_lp) where LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';
explain SELECT * FROM `tb_tdc_gjj_user`  FORCE INDEX(idx_LOCATION_CID)  where  LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';
explain SELECT * FROM `tb_tdc_gjj_user`  USE  INDEX(idx_LOCATION_CID)  where  LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';
explain SELECT * FROM `tb_tdc_gjj_user`  IGNORE   INDEX(un_lp)  where  LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';
explain SELECT * FROM `tb_tdc_gjj_user`  IGNORE   INDEX(un_lp,idx_LOCATION_CID)  where  LOCATION_CID='c147b4f3fb9e4948a535e4fef8cc1590';



**key_len（JSON名： key_length）**
表示索引中使用的字节数，可通过该列计算查询中使用的索引的长度。在不损失精确性的情况下，长度越短越好
key_len显示的值为索引字段的最大可能长度，并非实际使用长度，即key_len是根据表定义计算而得，不是通过表内检索出的



**ref（JSON名：ref）** 使用什么和索引比较的
哪些列或常量与该key列中命名的索引进行比较
const 使用常量和索引比较的
func 使用函数和key比较的




**rows（JSON名： rows）** 影响行数 不准确可以使用
MySQL认为执行查询必须检查的行数

分析表  优化表可以精确影响行数
ANALYZE  table order_investment;

Optimize Table  order_investment  ;



**filtered（JSON名： filtered）**  
按表条件过滤的表行的估计百分比。最大值为100，这表示未对行进行过滤。
值从100减小表示过滤量增加。 
rows显示检查的估计行数，rows× filtered显示与下表连接的行数。
例如，如果 rows为1000且 filtered为50.00（50％），则与下表连接的行数为1000×50％= 500。




**Extra （JSON名称：无）**
https://dev.mysql.com/doc/refman/8.0/en/explain-output.html#explain-extra-information

II.Child of 'table' pushed join@1（JSON：message 文本）  :没试
NDB Cluster 存储引擎


II.const row not found（JSON属性： const_row_not_found）  :没试出来

对于诸如之类的查询，该表为空。 SELECT ... FROM tbl_name


II.Deleting all rows（JSON属性： message）
对于DELETE，某些存储引擎（例如MyISAM）支持一种处理程序方法，该方法以一种简单而快速的方式删除所有表行。Extra如果引擎使用此优化，则显示此值


II.Distinct（JSON属性： distinct）
MySQL正在寻找不同的值，因此在找到第一个匹配的行后，它将停止为当前行组合搜索更多行。


II.FirstMatch(tbl_name) (JSON property: first_match)
The semijoin FirstMatch join shortcutting strategy is used for tbl_name.


II.Full scan on NULL key  (JSON property: message)
This occurs for subquery optimization as a fallback strategy when the optimizer cannot use an index-lookup access method.
子查询中的一种优化方式，主要在遇到无法通过索引访问null值的使用


II.Impossible HAVING (JSON property: message)
The HAVING clause is always false and cannot select any rows.
HAVING子句始终为false，不能选择任何行

explain SELECT count(*) FROM t2 GROUP BY user_id HAVING 1<0 ;

Extra :Impossible HAVING



II.Impossible WHERE（JSON属性： message）
The WHERE clause is always false and cannot select any rows.
WHERE子句始终为false，不能选择任何行

explain SELECT * FROM t2 where 1<0;



II.Impossible WHERE noticed after reading const tables 
MySQL has read all const (and system) tables and notice that the WHERE clause is always false.
MySQL已经读取了所有 const（和 system）表，并注意到该WHERE子句始终为false。


explain SELECT min(id) FROM t2 where un='222';


II.LooseScan(m..n) （JSON属性：message）

The semijoin LooseScan strategy is used. m and n are key part numbers. 
使用半连接的LooseScan策略。 m和 n是关键部件号。



II.No matching min/max row (JSON property: message)
没有行满足查询的条件，例如 。 SELECT MIN(...) FROM ... WHERE condition

explain SELECT min(id) FROM t2 ;  空表


II.no matching row in const table（JSON属性：message）:没试出来
表为空或者表中根据唯一键查询时没有匹配的行


II.No matching rows after partition pruning

对于DELETE或 UPDATE，在分区修剪后，优化器未发现任何要删除或更新的内容。它的含义类似于Impossible WHERE forSELECT语句。

II.No tables used（JSON属性： message）
查询没有FROM子句，或者有 FROM DUAL子句。


II.Not exists（JSON属性： message）
MySQL能够对LEFT JOIN 查询进行优化，并且在找到符合LEFT JOIN条件的一行后，不检查该表中的更多行是否为上一行组合。这是可以通过这种方式优化的查询类型的示例：
explain
SELECT * FROM t3 LEFT JOIN t2 ON t3.id=t2.id
WHERE t2.id IS NULL;



II.Plan isn't ready yet 
II.Range checked for each record (index map: N)
II.Recursive
II.Rematerialize
II.Scanned N databases
II.Select tables optimized away
II.Skip_open_table， Open_frm_only， Open_full_table
II.Start temporary，End temporary
II.unique row not found


https://dev.mysql.com/doc/refman/8.0/en/explain-output.html#explain-extra-information


II.Using filesort
使用了文件排序 order by 没有覆盖索引 
https://dev.mysql.com/doc/refman/8.0/en/explain-output.html#explain-extra-information
https://dev.mysql.com/doc/refman/8.0/en/order-by-optimization.html

user_id有索引，但是不是覆盖索引
explain SELECT * FROM t2   order by user_id;


II.Using index
单索引不用回表
explain SELECT id,USER_UUID FROM tb_tdc_gjj_user  where USER_UUID ='2';

当查询仅使用作为单个索引的一部分的列时，可以使用此策略。
仅使用索引树中的信息从表中检索列信息，而不需要进行附加搜索来读取实际行(使用二级覆盖索引即可获取数据)


II.Using index condition
通过访问索引元组并首先对其进行测试以确定是否读取完整的表行来读取表。这样，除非有必要，否则索引信息将用于延迟（“下推”）整个表行的读取。
会先条件过滤索引，过滤完索引后找到所有符合索引条件的数据行，随后用 WHERE 子句中的其他条件去过滤这些数据行；

一个有索引再加另外一个条件
explain SELECT id,USER_UUID FROM tb_tdc_gjj_user  where USER_UUID >'2' and LOCATION_CID='1';


II.Using index for group-by:没试出来

与Using index表访问方法类似，Using index for group-by 表示MySQL找到了一个索引，该索引可用于检索aGROUP BY或 DISTINCT查询的所有列，
而无需对实际表进行任何额外的磁盘访问。此外，以最有效的方式使用索引，因此对于每个组，仅读取少数索引条目。有关详细信息


II.Using index for skip scan
II.Using join buffer (Block Nested Loop)， Using join buffer (Batched Key Access)， Using join buffer (hash join
II.Using MRR
II.Using sort_union(...)，Using union(...)，Using intersect(...)


II.Using temporary 临时表 using_temporary_table

为了解决查询，MySQL需要创建一个临时表来保存结果。如果查询包含GROUP BY和 ORDER BY子句以不同的方式列出列，通常会发生这种情况。

SURNAME_SID无索引
explain SELECT * FROM tb_tdc_gjj_user GROUP BY SURNAME_SID ;


II.Using where
 过滤条件字段无索引；

SURNAME_SID无索引
explain SELECT * FROM tb_tdc_gjj_user where SURNAME_SID='111';



II.Using where with pushed condition:没试出来
NDB存储引擎 NDB Cluster

II.Zero limit:没试出来

该查询有一个LIMIT 0子句，不能选择任何行。


https://dev.mysql.com/doc/refman/8.0/en/explain-output.html#explain-extra-information



--------------------------------------------------------------------------------------------------------------------------------------------------
**SHOW WARNINGS** 输出上一条语句的错误 ：没试出来

EXPLAIN
SELECT t2.id, t2.id IN (SELECT t3.id FROM t3) FROM t2;
SHOW WARNINGS  ;


SELECT CONNECTION_ID();
SELECT * FROM `PROCESSLIST`;



---------------------------------------------------------------------------------------------
控制查询优化器
 
I.SELECT @@optimizer_switch;
https://dev.mysql.com/doc/refman/8.0/en/switchable-optimizations.html

控制优化器:优化器开关(全局都修改)、优化器提示(只运用于单条-优先级更高)

**优化器开关 optimize switch**

SELECT @@optimizer_switch;

修改值:
SET [GLOBAL|SESSION] optimizer_switch='command[,command]...';
opt_name=default、on、off

-- SET GLOBAL optimizer_switch='index_merge=default';




index_merge=on,   --控制所有索引合并优化。
index_merge_union=on,   --控制索引合并联合访问优化。
index_merge_sort_union=on,  --控制索引合并排序访问优化。
index_merge_intersection=on,
engine_condition_pushdown=on, --控制发动机状态下推。
index_condition_pushdown=on, --控制索引条件下推。
mrr=on,
mrr_cost_based=on,
block_nested_loop=on,
batched_key_access=off,
materialization=on,
semijoin=on,
loosescan=on,
firstmatch=on,
subquery_materialization_cost_based=on,
use_index_extensions=on --控制索引扩展的使用。

https://dev.mysql.com/doc/refman/8.0/en/switchable-optimizations.html





**优化器提示Optimizer Hints**
控制优化器的另一种方法是使用优化器提示：优先级大于优化器开关 optimize switch

控制优化器的另一种方法是使用优化器提示，该提示可以在各个语句中指定。
由于优化程序提示是基于每个语句应用的，因此它们提供了比使用更好的控制语句执行计划 optimizer_switch。
例如，可以在语句中为一个表启用优化，而对另一表禁用优化。
语句中的提示优先于 optimizer_switch标志。



I.优化器提示-作用域
全局：提示会影响整个语句
查询块：提示会影响语句中的特定查询块
表级别：提示会影响查询块中的特定表
索引级：提示会影响表中的特定索引

https://dev.mysql.com/doc/refman/8.0/en/optimizer-hints.html#optimizer-hints-overview



I.优化器提示-语法

优化器提示必须在/*+ ... */注释中指定。也就是说，优化程序提示使用/* ... */ C样式注释语法的变体，并+在/*注释打开序列之后添加一个字符。

/*+ BKA(t1) */
/*+ BNL(t1, t2) */
/*+ NO_RANGE_OPTIMIZATION(t4 PRIMARY) */
/*+ QB_NAME(qb2) */




I.Join-Order Optimizer Hints 联合排序优化器提示

影响优化器联接表的顺序。


II.hint_name：允许使用这些提示名称：

JOIN_FIXED_ORDER：强制优化器使用表在FROM 子句中出现的顺序来联接表。这与指定相同SELECT STRAIGHT_JOIN。

JOIN_ORDER：指示优化器使用指定的表顺序联接表。提示适用于命名表。优化器可以将未命名的表放置在连接顺序中的任何位置，包括在指定的表之间。

JOIN_PREFIX：指示优化器使用指定的表顺序为联接执行计划的前几个表联接表。提示适用于命名表。优化器将所有其他表放在命名表之后。

JOIN_SUFFIX：指示优化器使用指定的表顺序连接表，以执行连接执行计划的最后一个表。提示适用于命名表。优化器将所有其他表放在命名表之前。



II.tbl_name :
语句中使用的表的名称。命名表的提示适用于它命名的所有表。



II.query_block_name：
提示适用于的查询块。如果提示中不包含任何前导 ，则该提示适用于出现该查询的查询块。对于 语法，提示适用于命名查询块中的命名表。要将名称分配给查询块




I.Table-Level Optimizer Hints  表级优化器提示



**I.Index-Level Optimizer Hints  索引级 优化器提示**
索引级优化器提示 优先级大于 force index、  IGNORE   INDEX、use index


索引级优化标记 GROUP_INDEX，INDEX， JOIN_INDEX，和 ORDER_INDEX所有优先于等效FORCE INDEX的提示; 也就是说，它们导致FORCE INDEX提示被忽略。同样， NO_GROUP_INDEX， NO_INDEX， NO_JOIN_INDEX，和 NO_ORDER_INDEX提示都优先于任何IGNORE INDEX 等价物，也使他们被忽略。




II.hint_name：

允许使用这些提示名称：


**GROUP_INDEX， NO_GROUP_INDEX：**
启用或禁用指定的一个或多个索引以进行GROUP BY 操作的索引扫描。相当于索引提示 FORCE INDEX FOR GROUP BY， IGNORE INDEX FOR GROUP BY。在MySQL 8.0.20及更高版本中可用。

**INDEX， NO_INDEX：**
作为的组合 JOIN_INDEX， GROUP_INDEX以及 ORDER_INDEX，迫使服务器以用于任何指定索引或索引和所有范围，或为一体的组合 NO_JOIN_INDEX， NO_GROUP_INDEX和 NO_ORDER_INDEX，这使得服务器忽略任何指定索引或索引和所有范围。相当于 FORCE INDEX，IGNORE INDEX。从MySQL 8.0.20开始可用。

**INDEX_MERGE， NO_INDEX_MERGE：**
启用或禁用指定表或索引的索引合并访问方法。有关此访问方法的信息，请参见 第8.2.1.3节“索引合并优化”。这些提示适用于所有三种索引合并算法。

该INDEX_MERGE提示会强制优化器使用指定的索引集对指定的表使用索引合并。如果未指定索引，则优化器将考虑所有可能的索引组合并选择最便宜的索引组合。如果索引组合不适用于给定的语句，则可以忽略该提示。

该NO_INDEX_MERGE 提示将禁用涉及任何指定索引的索引合并组合。如果提示未指定索引，则表不允许索引合并。

**JOIN_INDEX， NO_JOIN_INDEX：**
强制MySQL使用或忽略指定的索引或索引为任何存取方法，诸如 ref，range， index_merge，等。相当于FORCE INDEX FOR JOIN，IGNORE INDEX FOR JOIN。在MySQL 8.0.20及更高版本中可用。

**MRR， NO_MRR：**
启用或禁用指定表或索引的MRR。MRR提示仅适用于InnoDB和 MyISAM表。有关此访问方法的信息，请参见 第8.2.1.11节“多范围读取优化”。

**NO_ICP：**
对指定的表或索引禁用ICP。默认情况下，ICP是一种候选优化策略，因此没有启用它的提示。有关此访问方法的信息，请参见 第8.2.1.6节“索引条件下推优化”。

**NO_RANGE_OPTIMIZATION：**
禁用指定表或索引的索引范围访问。此提示还禁用表或索引的索引合并和松散索引扫描。默认情况下，范围访问是一种候选优化策略，因此没有启用它的提示。

当范围数可能很高并且范围优化将需要许多资源时，此提示可能会很有用。

**ORDER_INDEX， NO_ORDER_INDEX：**
使MySQL使用或忽略指定的一个或多个用于对行进行排序的索引。相当于FORCE INDEX FOR ORDER BY，IGNORE INDEX FOR ORDER BY。从MySQL 8.0.20开始可用。

**SKIP_SCAN， NO_SKIP_SCAN：**
为指定的表或索引启用或禁用“跳过扫描”访问方法。有关此访问方法的信息，请参见 跳过扫描范围访问方法。这些提示自MySQL 8.0.13起可用。

该SKIP_SCAN提示会强制优化器使用指定索引集对指定表使用“跳过扫描”。如果未指定索引，则优化器将考虑所有可能的索引并选择最便宜的索引。如果索引不适用于给定的语句，则可以忽略该提示。

该NO_SKIP_SCAN 提示禁用指定索引的“跳过扫描”。如果提示未指定索引，则不允许对该表进行“跳过扫描”。





I.Subquery Optimizer Hints 子查询优化器提示
https://dev.mysql.com/doc/refman/8.0/en/optimizer-hints.html#optimizer-hints-subquery



I.语句执行优化器提示 Statement Execution Time Optimizer Hints

SELECT /*+ MAX_EXECUTION_TIME(1000) */ * FROM t1 INNER JOIN t2 WHER



I.Variable-Setting Hint Syntax 变量设置提示语法

SELECT /*+ SET_VAR(sort_buffer_size = 16M) */ name FROM people ORDER BY name;
INSERT /*+ SET_VAR(foreign_key_checks=OFF) */ INTO t2 VALUES(2);
SELECT /*+ SET_VAR(optimizer_switch = 'mrr_cost_based=off') */ 1;




I.Resource Group Hint Syntax 资源组提示 语法
此提示将执行语句的线程临时分配给命名资源组（在语句持续时间内）
SELECT /*+ RESOURCE_GROUP(USR_default) */ name FROM people ORDER BY name;
INSERT /*+ RESOURCE_GROUP(Batch) */ INTO t2 VALUES(2);



I.Optimizer Hints for Naming Query Blocks 用于命名查询块的优化器提示
SELECT /*+ QB_NAME(qb1) */ ...
FROM (SELECT /*+ QB_NAME(qb2) */ ...
FROM (SELECT /*+ QB_NAME(qb3) */ ... FROM ...)) ...



----------------------------------------------------------------------------------------
**Index Hints索引提示**

索引提示为优化器提供有关在查询处理期间如何选择索引的信息。
索引和优化器提示optimizer hints可以单独使用，也可以一起使用。
索引提示仅适用于SELECT 和UPDATE语句。

USE INDEX (index_list) 
IGNORE INDEX (index_list)   
FORCE INDEX (index_list)


服务器支持该索引级优化标记JOIN_INDEX， GROUP_INDEX， ORDER_INDEX，和 INDEX，其中等同于和用于替代FORCE INDEX 索引提示，
以及所述 NO_JOIN_INDEX， NO_GROUP_INDEX， NO_ORDER_INDEX，和 NO_INDEX优化标记，这相当于和意图取代 IGNORE INDEX索引提示。

因此，您应该期望USE INDEX，FORCE INDEX和IGNORE INDEX在将来的MySQL版本中被弃用，并在之后的某个时间将其完全删除。

要引用主键，请使用名称PRIMARY

**USE INDEX、IGNORE INDEX、FORCE INDEX 后期要被删除** 

**FORCE INDEX强制使用索引:** 替换为
JOIN_INDEX， GROUP_INDEX， ORDER_INDEX，和 INDEX

**IGNORE INDEX 强制不使用某个索引** 替换为
NO_JOIN_INDEX， NO_GROUP_INDEX， NO_ORDER_INDEX，和 NO_INDEX




--------------------------------------------------------------------------
**The Optimizer Cost Model 优化器覆盖模型**


为了生成执行计划，优化器使用成本模型，该模型基于对查询执行期间发生的各种操作的成本进行估算。
优化器具有一组内置的默认“成本常数”，可用于制定有关执行计划的决策。


Cost Model General Operation成本模型一般运作




The Cost Model Database成本模型数据库



Making Changes to the Cost Model Database更改成本模型数据库




-----------------------------------------------------
**Optimizer Statistics优化器统计**









-------------------------------------------------------------------------
**8.10 Buffering and Caching缓冲和缓存**

https://dev.mysql.com/doc/refman/8.0/en/buffering-caching.html



**8.10.1 InnoDB缓冲池优化**


InnoDB维护一个称为缓冲池的存储区， 用于在内存中缓存数据和索引。


8.10.2 MyISAM密钥缓存 忽略

配置InnoDB缓冲池预取（预读）

配置缓冲池刷新





-------------------------------------------------------------------------------------------
**8.10 Optimizing Locking Operations 优化锁操作**
内部锁定：在MySQL服务器本身内执行，以管理多个线程对表内容的争用。这种类型的锁定是内部锁定，因为它完全由服务器执行，并且不涉及其他程序。

外部锁定：当服务器和其他程序锁定MyISAM表文件以相互协调哪个程序可以在何时访问表时，就会发生外部锁定。




**8.11.1内部锁定方法**


+ 外部锁定:使用文件系统锁定来管理MyISAM多个进程对数据库表的争用。

+ 内部锁定方法
   + 行级锁
   + 表级锁
   + 选择锁类型 Choosing the Type of Locking


**行级锁**
MySQL对表使用行级锁定，InnoDB以支持多个会话同时进行写访问，从而使其适用于多用户，高度并发和OLTP应用程序。

I.死锁:
禁用死锁检测:在高并发系统上，当多个线程等待相同的锁时，死锁检测会导致速度变慢。
默认情况下会InnoDB自动 检测死锁条件，并回滚受影响的事务之一。




I.行级锁定的优点：
+ 当不同的会话访问不同的行时，锁冲突减少。
+ 回滚的更改较少。
+ 可以长时间锁定单个行。



**表级锁**
https://dev.mysql.com/doc/refman/8.0/en/internal-locking.html
MySQL uses 表级锁 在 MyISAM、MEMORY、MERGE 存储引擎的表



I.表级锁定的优点：
+ 所需的内存相对较少（行锁定需要锁定每行或每组行的内存）
+ 在表的大部分上使用时非常快，因为只涉及一个锁。
+ 如果您经常GROUP BY 对大部分数据进行操作，或者必须经常扫描整个表，则速度很快。


**选择锁类型 Choosing the Type of Locking**


通常，在以下情况下，表锁优于行级锁：
+ 该表的大多数语句均为读取。
+ 该表的语句是读和写的混合，其中写是对单行的更新或删除，可通过一次按键读取来获取：
+ SELECT与并发INSERT 语句结合使用，很少有 UPDATE or DELETE语句。
+ GROUP BY无需任何编写程序即可对整个表进行 许多扫描或操作。





行级锁定以外的选项：

+ 版本控制（例如在MySQL中用于并发插入的版本控制），可以同时拥有一个编写者和多个阅读者。
这意味着数据库或表根据访问开始的时间支持数据的不同视图。这个其他常见的术语是 “时间旅行， ” “上写副本， ” 或“按需复制。”

+ 在许多情况下，按需复制优于行级锁定。但是，在最坏的情况下，与使用普通锁相比，它可以使用更多的内存。
+ 除了使用行级锁，您还可以使用应用程序级锁，例如MySQLGET_LOCK()和 RELEASE_LOCK()MySQL提供的锁 。这些是咨询锁，因此它们仅适用于相互协作的应用程序。





**8.11.2表锁定问题**


性能方面的考虑使用InnoDB



**8.11.3 Concurrent Inserts**
MyISAM存储引擎支持并发插入





**8.11.4元数据锁定 Metadata Locking**
https://dev.mysql.com/doc/refman/8.0/en/metadata-locking.html

MySQL使用元数据锁定来管理对数据库对象的并发访问并确保数据一致性。

元数据锁定不仅适用于表，而且还适用于模式，存储程序（过程，函数，触发器，计划的事件），
表空间，使用该GET_LOCK()函数获取的用户锁 和使用该函数获取的锁。



I.元数据锁定获取
RENAME TABLE一条DDL语句按名称顺序获取锁：

RENAME TABLE tbla TO tbld, tblc TO tbla;


I.元数据锁定释放
为确保事务可串行化，服务器不得允许一个会话对在另一会话中未完成的显式或隐式启动的事务中使用的表执行数据定义语言（DDL）语句。




**8.11.5外部锁定**
使用文件系统锁定来管理MyISAM多个进程对数据库表的争用。




-------------------------------------------8.12优化MySQL服务器----------------------------------------------------------------------------
8.12优化MySQL服务器

https://dev.mysql.com/doc/refman/8.0/en/optimizing-server.html

8.12.1优化磁盘I / O
8.12.2使用符号链接
8.12.3优化内存使用



8.13评估效果（基准测试）


SELECT BENCHMARK(1000000,1+1);

它表明MySQL可以在0.32秒内在该系统上执行1,000,000个简单加法表达式。



----------------------------------------------------------------------------------------------------
**第11章数据类型**
https://dev.mysql.com/doc/refman/8.0/en/data-types.html



11.1数值数据类型
11.2日期和时间数据类型
11.3字符串数据类型
11.4空间数据类型
11.5 JSON数据类型

**超出范围和溢出处理**


1.如果启用了严格的SQL模式，则根据SQL标准，MySQL会拒绝超出范围的值并产生错误，并且插入将失败。

2.如果没有启用限制模式，MySQL会将值裁剪到列数据类型范围的适当端点，并存储结果值。




**11.1数值数据类型**


+ 整数类型（精确值）-INTEGER，INT，SMALLINT，TINYINT，MEDIUMINT，BIGINT

    + TINYINT
    + SMALLINT
    + MEDIUMINT
    + INT
    + BIGINT
 https://dev.mysql.com/doc/refman/8.0/en/integer-types.html

+ 定点类型（精确值）-DECIMAL，NUMERIC
   + DECIMAL       DECIMAL maximum  65       DECIMAL(5,2)范围是从-999.99到 999.99
   + NUMERIC NUMERIC实现为DECIMAL，因此以下有关的说明DECIMAL同样适用于 NUMERIC。

DECIMAL列 的声明语法为 。自变量的值范围如下： DECIMAL(M,D)
M是最大位数（精度）。范围是1到65。
D是小数点右边的位数（小数位数）。范围是0到30，并且不能大于M。


+ 浮点类型（近似值）-FLOAT，DOUBLE
   + FLOAT
   + DOUBLE

+ 位值类型-BIT





**11.2日期和时间数据类型**


+ DATE，DATETIME和TIMESTAMP类型
   + DATE 只到天  'YYYY-MM-DD''1000-01-01''9999-12-31'
   + DATATIME  'YYYY-MM-DD hh:mm:ss''1000-01-01 00:00:00''9999-12-31 23:59:59'
   + TIMESTAMP '1970-01-01 00:00:01'UTC到'2038-01-19 03:14:07' 


+ TIME类型 
  'hh:mm:ss'格式（或 'hhh:mm:ss'大小时数格式）显示值 。TIME值的范围可以从 '-838:59:59'到 '838:59:59'


+  YEAR
   1字节类型 等效于YEAR(4)显式显示宽度   范围 1901为2155，和 0000。
   会将无效YEAR值转换 为0000





**11.3字符串数据类型**



+  CHAR和VARCHAR类型 ：它们被存储和检索的方式不同。它们的最大长度以及是否保留尾随空格也不同
   + CHAR   长度可以是0到255之间的任何值     固定占用
   + VARCHAR 长度可以指定为0到65535之间的值  变长
     https://dev.mysql.com/doc/refman/8.0/en/char.html

+ BINARY和VARBINARY类型 和上边的类似，不过是二进制存储
  

+ BLOB和TEXT类型
   + BLOB 二进制字符串（照片等文件）
      + TINYBLOB    0-255字节
      + BLOB        0-65535字节
      + MEDIUMBLOB   0-16MB
      + LONGBLOB     0-4G
   + TEXT 非二进制字符串(只能保存字符串)
      + TINYTEXT   0-255字节
      + TEXT          0-65535字节
      + MEDIUMTEXT   0-16MB
      + LONGTEXT     0-4G
 

+  ENUM 枚举类型 节省空间 最多可包含65,535个不同的元素。 可以有null
   size ENUM('x-small', 'small', 'medium', 'large', 'x-large')
   INSERT INTO shirts (name, size) VALUES ('dress shirt','large')

+ SET类型  具有零个或多个值的字符串对象
  SET('one', 'two')  :''  'one'  'two'  'one,two'



**11.4空间数据类型** GIS
https://dev.mysql.com/doc/refman/8.0/en/spatial-types.html
GEOMETRY、POINT、LINESTRING、POLYGON、MULTIPOINT、MULTILINESTRING、MULTIPOLYGON、GEOMETRYCOLLECTION

几何类：球、多边形
Point类：坐标类 X\Y
曲线类:
多表面累



**11.5 JSON数据类型**
https://dev.mysql.com/doc/refman/8.0/en/json.html
CREATE TABLE t1 (jdoc JSON);
INSERT INTO t1 VALUES('{"key1": "value1", "key2": "value2"}');



---------------------------------------------------------------------------------------


**第十二章函数和运算符**
https://dev.mysql.com/doc/refman/8.0/en/functions.html



12.1 SQL函数和运算符参考：全部函数 巨多
https://dev.mysql.com/doc/refman/8.0/en/sql-function-reference.html

%， MOD :	模运算符
ABS()   :  返回绝对值
ADDTIME() : 加时间

12.2用户定义的功能参考
https://dev.mysql.com/doc/refman/8.0/en/udf-reference.html


12.4运算符
https://dev.mysql.com/doc/refman/8.0/en/non-typed-operators.html


12.5流量控制功能 Flow Control Functions 流程控制 case when

SELECT CASE 1 WHEN 1 THEN 'one'
WHEN 2 THEN 'two' ELSE 'more' END;

SELECT CASE WHEN 1>0 THEN 'true' ELSE 'false' END;

SELECT CASE BINARY 'C'
WHEN 'a' THEN 1 WHEN 'b' THEN 2 END;


12.7日期和时间函数
https://dev.mysql.com/doc/refman/8.0/en/date-and-time-functions.html

12.8字符串函数和运算符



12.10全文搜索功能


12.11强制转换函数和运算符

BINARY	将字符串转换为二进制字符串
CAST()	将值强制转换为特定类型
CONVERT()	将值强制转换为特定类型


12.20.1汇总功能说明
MAX()	返回最大值
MIN()	返回最小值
STD()	返回人口标准差
STDDEV()	返回人口标准差
STDDEV_POP()	返回人口标准差
https://dev.mysql.com/doc/refman/8.0/en/aggregate-functions.html




**12.20.2 GROUP BY修饰符**
https://dev.mysql.com/doc/refman/8.0/en/group-by-modifiers.html
with rollup 汇总之前分组的全部数据
SELECT year, SUM(profit) AS profit
FROM sales
GROUP BY year WITH ROLLUP;



SELECT user_id ,sum(buy_amount) FROM `order_investment`  where 1=1
group by user_id WITH ROLLUP



**12.21.1窗口功能说明 Window Function Descriptions**  8.0新增

CUME_DIST()	累积分布值
DENSE_RANK()	当前行在其分区内的排名，没有空格
FIRST_VALUE()	窗框第一行的自变量值
LAG()	分区中当前行滞后的参数值
LAST_VALUE()	窗口框架最后一行的参数值
LEAD()	来自分区内当前行的行的参数值
NTH_VALUE()	窗框第N行的自变量值
NTILE()	分区中当前行的存储桶号。
PERCENT_RANK()	百分比排名值
RANK()	当前行在其分区内的排名，带有空格
ROW_NUMBER()	分区中当前行的数量



create table TEST_ROW_NUMBER_OVER(
id varchar(10) not null,
name varchar(10) null,
age varchar(10) null,
salary int null
);
select * from TEST_ROW_NUMBER_OVER t;

insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(1,'a',10,8000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(1,'a2',11,6500);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(2,'b',12,13000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(2,'b2',13,4500);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(3,'c',14,3000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(3,'c2',15,20000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(4,'d',16,30000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(5,'d2',17,1800);


    select id,name,age,salary,  row_number()over(order by salary desc) 
    from TEST_ROW_NUMBER_OVER 




**12.25精确数学**
https://dev.mysql.com/doc/refman/8.0/en/precision-math.html

Numeric
DECIMAL




172

**第13章SQL语句**
https://dev.mysql.com/doc/refman/8.0/en/sql-statements.html


13.1数据定义语句
13.1.1原子数据定义语句支持
13.1.2 ALTER DATABASE语句
13.1.3 ALTER EVENT语句
13.1.4 ALTER FUNCTION语句
13.1.5 ALTER INSTANCE语句
13.1.6 ALTER LOGFILE GROUP语句
13.1.7 ALTER PROCEDURE语句
13.1.8 ALTER SERVER语句
13.1.9 ALTER TABLE语句
13.1.10 ALTER TABLESPACE语句
13.1.11 ALTER VIEW语句
13.1.12 CREATE DATABASE语句
13.1.13 CREATE EVENT语句
13.1.14 CREATE FUNCTION语句
13.1.15 CREATE INDEX语句
13.1.16 CREATE LOGFILE GROUP语句
13.1.17 CREATE PROCEDURE和CREATE FUNCTION语句
13.1.18 CREATE SERVER语句
13.1.19创建空间参考系统语句
13.1.20 CREATE TABLE语句
13.1.21 CREATE TABLESPACE语句
13.1.22 CREATE TRIGGER语句
13.1.23 CREATE VIEW语句
13.1.24 DROP DATABASE语句
13.1.25 DROP EVENT语句
13.1.26 DROP FUNCTION语句
13.1.27 DROP INDEX语句
13.1.28 DROP LOGFILE GROUP语句
13.1.29 DROP PROCEDURE和DROP FUNCTION语句
13.1.30 DROP SERVER语句
13.1.31 DROP空间参考系统声明
13.1.32 DROP TABLE语句
13.1.33 DROP TABLESPACE语句
13.1.34 DROP TRIGGER语句
13.1.35 DROP VIEW语句
13.1.36 RENAME TABLE语句
13.1.37 TRUNCATE TABLE语句

https://dev.mysql.com/doc/refman/8.0/en/sql-data-definition-statements.html

13.2数据处理语句

13.2.1 CALL声明  该CALL语句调用先前用定义的存储过程 CREATE PROCEDURE
13.2.2 DELETE语句
13.2.3 DO声明
13.2.4处理程序声明
13.2.5导入表声明
13.2.6 INSERT语句
13.2.7 LOAD DATA语句
13.2.8 LOAD XML语句
13.2.9更换声明
13.2.10 SELECT语句
13.2.11子查询
13.2.12 TABLE语句
13.2.13 UPDATE语句
13.2.14 VALUES语句
13.2.15 WITH（公用表表达式）


 


https://dev.mysql.com/doc/refman/8.0/en/sql-data-manipulation-statements.html



**13.3交易和锁定声明**


13.3.1 START TRANSACTION，COMMIT和ROLLBACK语句

**I.事务控制**

**II.开始事务**  开始事务后 autocommit会关闭
1.START TRANSACTION; == BEGIN; == START TRANSACTION READ WRITE;
2.BEGIN开始新的交易。
3.WITH CONSISTENT SNAPSHOT
4.READ WRITE
5.READ ONLY

**III.默认值**
START TRANSACTION或 BEGIN;

START TRANSACTION READ WRITE;

**III. with consistent snapshot**
第一种启动方式，一致性视图是在执行第一个快照读语句时创建的；
第二种启动方式，一致性视图是在执行start transaction with consistent snapshot时候创建的。

**III. read only;**
start transaction read only;
只能读取,写删改等会报错


COMMIT 提交当前事务，使其更改永久生效。

ROLLBACK 回滚当前事务，取消其更改。

SET autocommit 禁用或启用当前会话的默认自动提交模式。

**II.显式禁用自动提交**
SET autocommit=0;commit;
您必须使用COMMIT将更改存储到磁盘或ROLLBACK忽略更改。



**13.3.2无法回滚的语句**
II.无法回滚的语句

DDL语句，例如创建或删除数据库的语句
，创建，删除或更改表或存储例程的语句。



**13.3.3导致隐式提交的语句**
II.定义或修改数据库对象的数据定义语言（DDL）语句。
ALTER EVENT， ALTER FUNCTION， ALTER PROCEDURE， ALTER SERVER， ALTER TABLE， ALTER VIEW， CREATE DATABASE， CREATE EVENT， CREATE FUNCTION， CREATE INDEX， CREATE PROCEDURE， CREATE ROLE， CREATE SERVER， CREATE SPATIAL REFERENCE SYSTEM，CREATE TABLE， CREATE TRIGGER， CREATE VIEW， DROP DATABASE， DROP EVENT， DROP FUNCTION， DROP INDEX， DROP PROCEDURE， DROP ROLE， DROP SERVER， DROP SPATIAL REFERENCE SYSTEM， DROP TABLE， DROP TRIGGER， DROP VIEW， INSTALL PLUGIN， RENAME TABLE， TRUNCATE TABLE， UNINSTALL PLUGIN。




II.隐式使用或修改mysql数据库中表的语句。
ALTER USER， CREATE USER， DROP USER， GRANT， RENAME USER， REVOKE， SET PASSWORD。


II.事务控制和锁定语句。

BEGIN， LOCK TABLES，SET autocommit = 1（如果该值不是1）， ， 。 START TRANSACTION UNLOCK TABLES
UNLOCK TABLES仅LOCK TABLES当当前已锁定任何表以获取非事务性表锁时，才提交事务。由于后面的语句不获取表级锁，因此不会进行UNLOCK TABLES后续的提交 FLUSH TABLES WITH READ LOCK。
事务不能嵌套。 



II.数据加载语句。 LOAD DATA。 LOAD DATA导致仅对使用NDB存储引擎的表进行隐式提交 。



II.行政声明。分析表等 

ANALYZE TABLE， CACHE INDEX， CHECK TABLE， FLUSH， LOAD INDEX INTO CACHE，OPTIMIZE TABLE，REPAIR TABLE， RESET（但不是 RESET PERSIST）。

II.复制控制语句。主从等 

START REPLICA | SLAVE， STOP REPLICA | SLAVE， RESET REPLICA | SLAVE，CHANGE REPLICATION SOURCE TO，CHANGE MASTER TO。




**13.3.4 SAVEPOINT**
SAVEPOINT, ROLLBACK TO SAVEPOINT, and RELEASE SAVEPOINT Statements


SAVEPOINT identifier;-- 创建某个点
ROLLBACK TO  identifier; -- 回滚到某个点
RELEASE SAVEPOINT identifier; -- 释放某个点



**13.3.5 BACK和LOCK INSTANCE语句的锁定实例**
https://dev.mysql.com/doc/refman/8.0/en/lock-instance-for-backup.html
轻量级的备份锁
MySQL8.x 中新增了一个轻量级的备份锁，它允许在 online 备份的时候进行 DML 操作，同时可防止快照不一致。

II.禁止的内容
文件的创建、删除、改名
账户的管理
REPAIR TABLE
TRUNCATE TABLE
OPTIMIZE TABLE


II.语句

备份锁由 lock instance for backup 和 unlock instance 语法组成。使用这些语句需要 BACKUP_ADMIN 权限。



**13.3.6 LOCK TABLES和UNLOCK TABLES语句**

在更新表时，可以使用锁来模拟事务或提高速度。表锁定限制和条件中对此进行了详细说明 。
LOCK TABLES
tbl_name [[AS] alias] lock_type
[, tbl_name [[AS] alias] lock_type] ...

lock_type: {
READ [LOCAL]| [LOW_PRIORITY] WRITE
}

UNLOCK TABLES;

----------------------------------

lock tables user read;
unlock tables;


lock tables user read local;
unlock tables;

lock tables user write;
unlock tables;

**I.锁获取**

**锁类型**

II.READ [LOCAL] 锁：

持有锁的会话可以读取表（但不能写入表）。
多个会话可以同时获取READ该表的锁。
其他会话可以在不显式获取READ锁的情况下读取表。
该LOCAL修改使不冲突的 INSERT其他会话语句（并发插入），而持有锁来执行。


II.[LOW_PRIORITY] WRITE 锁：
持有锁的会话可以读取和写入表。
只有持有锁的会话才能访问该表。在释放锁之前，没有其他会话可以访问它。
保持锁定状态时，其他会话对表的锁定请求将阻塞WRITE。
该LOW_PRIORITY修饰符无效。在以前的MySQL版本中，它影响了锁定行为，但现在不再如此。现在已弃用它，并且使用它会产生警告。WRITE不 使用LOW_PRIORITY而代之。



WRITE锁通常比READ锁具有更高的优先级


**I.锁释放**

显式释放其锁 UNLOCK TABLES。


隐式释放锁:LOCK TABLES时如果之前有锁，会先释放
会话发出LOCK TABLES已持有锁的同时获取锁的语句，则在授予新锁之前会隐式释放其现有锁。

会话开始事务:（例如，使用START TRANSACTION 则将 UNLOCK TABLES执行隐式 操作）

客户端断开链接会自动释放锁


**I.表锁定与事务的交互**

LOCK TABLES并 UNLOCK TABLES与交易进行交

1.LOCK TABLES 不是事务安全的，而是在尝试锁定表之前隐式提交任何活动事务。
2.UNLOCK TABLES隐式提交任何活动事务，但前提是LOCK TABLES已用于获取表锁。
3. START TRANSACTION 隐式提交任何当前事务并释放现有的表锁。
4. FLUSH TABLES WITH READ LOCK 获得全局读锁定，而不是表锁，所以它不会受到相同的行为 LOCK TABLES，并 UNLOCK TABLES相对于表锁定和隐式的提交。
5. 其他隐式导致事务提交的语句不会释放现有的表锁。

当您调用时LOCK TABLES， InnoDB内部会使用自己的表锁，而MySQL则会使用自己的表锁。



**I.锁定表和触发器**
如果LOCK TABLES使用显式锁定表，则触发器中使用的任何表也将隐式锁定：





**13.3.7 SET TRANSACTION语句**
SELECT @@tx_isolation; -- 查询事务隔离级别 8之前
select @@transaction_isolation; -- 查询事务隔离级别 8之后
show variables like 'transaction_isolation'; -- 查询事务隔离级别 8之后

SET [GLOBAL | SESSION] TRANSACTION
transaction_characteristic [, transaction_characteristic] 
transaction_characteristic: {
ISOLATION LEVEL level| access_mode
}

level: {
REPEATABLE READ
| READ COMMITTED
| READ UNCOMMITTED
| SERIALIZABLE
}

access_mode: {
READ WRITE
| READ ONLY
}

修改隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL READ COMMITTED  ;
只读
set transaction read only；



**隔离级别:**
REPEATABLE READ
READ COMMITTED
READ UNCOMMITTED
SERIALIZABLE

**访问模式:**
READ WRITE
READ ONLY


**特征范围:**
GLOBAL:
该声明适用于所有后续会话。现有会话不受影响。

SESSION:
当前会话中执行的所有后续事务、不会影响当前正在进行的事务。

在没有任何SESSION或 GLOBAL关键字：
仅适用于会话中执行的下一个单个事务。
不允许现有事务中

SET GLOBAL TRANSACTION transaction_characteristic	全球的
SET SESSION TRANSACTION transaction_characteristic	会议
SET TRANSACTION transaction_characteristic	仅下一笔交易




**13.3.8 XA交易**
https://dev.mysql.com/doc/refman/8.0/en/xa.html


MySQL服务器的XA接口由以XA关键字开头的SQL语句组成。


**应用程序涉及一个或多个资源管理器和一个事务管理器：**
资源管理器（RM):(单个DB)提供对事务性资源的访问。数据库服务器是一种资源管理器。必须能够提交或回滚RM管理的事务。

事务管理器（TM）协调作为全局事务一部分的事务。它与处理每个事务的RM通信。全局事务中的单个事务是全局事务的 “分支”。全局事务及其分支由后面描述的命名方案标识。

**两阶段提交（2PC）**

在第一阶段，准备所有分支。也就是说，TM告诉他们准备提交。通常，这意味着管理分支的每个RM在稳定的存储中记录分支的动作。分支指示它们是否能够执行此操作，并将这些结果用于第二阶段。

在第二阶段，TM告诉RM，是提交还是回滚。如果所有分支在准备就绪时都表示可以提交，则将告知所有分支进行提交。如果有任何分支在准备就绪时指示无法提交，则将告知所有分支回滚。



**13.4 Replication Statements** 复制说明  主从等

SHOW BINARY LOGS; 查看binlog大小等

SHOW BINLOG EVENTS; binlog事件

SHOW MASTER STATUS; 

SHOW REPLICAS 高版本以后;SHOW SLAVE HOSTS;老版本  显示副本 


**13.4.1.1 PURGE BINARY LOGS语句  purge清除**
删除指定索引文件名或日期之前在日志索引文件中列出的所有二进制日志文件。


PURGE BINARY LOGS TO 'mysql-bin.010';
PURGE BINARY LOGS BEFORE '2019-04-02 22:46:26';


要安全清除二进制日志文件，请按照以下步骤操作：

1.在每个副本上，用于 SHOW REPLICA | SLAVE STATUS检查它正在读取哪个日志文件。
2.使用来获取源中二进制日志文件的列表 SHOW BINARY LOGS。
3.确定所有副本中最早的日志文件。这是目标文件。如果所有副本都是最新的，则这是列表上的最后一个日志文件。
4.对要删除的所有日志文件进行备份。（此步骤是可选的，但始终建议这样做。）
5.清除所有日志文件，但不包括目标文件。

默认的二进制日志有效期为30天。您可以使用binlog_expire_logs_seconds 系统变量指定替代的有效期限 。



**13.4.1.2 RESET MASTER语句**
请谨慎使用此语句
https://dev.mysql.com/doc/refman/8.0/en/reset-master.html

RESET MASTER删除所有现有的二进制日志文件并重置二进制日志索引文件，将服务器重置为开始二进制日志记录之前的状态。将创建一个新的空二进制日志文件，以便可以重新启动二进制日志记录。






**13.4.1.3 SET sql_log_bin语句**
https://dev.mysql.com/doc/refman/8.0/en/set-sql-log-bin.html
控制是否为当前会话启用到二进制日志的日志记录（假设二进制日志本身已启用）。默认值为ON。

要为当前会话禁用或启用二进制日志记录，请将会话sql_log_bin变量设置为 OFF或ON。



**13.4.2用于控制副本服务器的SQL语句**
MySQL 8.0.22开始;

SHOW REPLICA STATUS;从MySQL 8.0.22开始替换 SHOW SLAVE STATUS;

SHOW RELAYLOG EVENTS；


**13.4.2.1更改主报表**
https://dev.mysql.com/doc/refman/8.0/en/change-master-to.html

8.0.23起，使用 CHANGE REPLICATION SOURCE TO代替CHANGE MASTER TO，


13.4.2.1更改主报表
13.4.2.2更改复制过滤器语句
13.4.2.3更改声明的复制源
13.4.2.4 MASTER_POS_WAIT（）语句
13.4.2.5重置副本| 奴隶声明
13.4.2.6复位从动| REPLICA声明
13.4.2.7开始替换| 奴隶声明
13.4.2.8启动从属| REPLICA声明
13.4.2.9停止复制| 奴隶声明
13.4.2.10停止从动| REPLICA声明
13.4.2.11配置源列表的功能

13.4.2.1 CHANGE MASTER TO Statement
13.4.2.2 CHANGE REPLICATION FILTER Statement
13.4.2.3 CHANGE REPLICATION SOURCE TO Statement
13.4.2.4 MASTER_POS_WAIT() Statement
13.4.2.5 RESET REPLICA | SLAVE Statement
13.4.2.6 RESET SLAVE | REPLICA Statement
13.4.2.7 START REPLICA | SLAVE Statement
13.4.2.8 START SLAVE | REPLICA Statement
13.4.2.9 STOP REPLICA | SLAVE Statement
13.4.2.10 STOP SLAVE | REPLICA Statement
13.4.2.11 Functions which Configure the Source List





**13.5 Prepared Statements**
预处理占位符


1.预处理
2.执行
3.取消
PREPARE stmt1 FROM 'SELECT SQRT(POW(?,2) + POW(?,2)) AS hypotenuse';
SET @a = 3;
SET @b = 4;

EXECUTE stmt1 USING @a, @b;

DEALLOCATE PREPARE stmt1;  --取消

在使用准备好语句之后 PREPARE，可以使用EXECUTE引用准备好的语句名称的语句来执行它 。
如果prepared语句包含任何参数标记，则必须提供一个USING 子句，该子句列出包含要绑定到参数的值的用户变量。
参数值只能由用户变量提供，并且USING子句的命名变量必须与语句中参数标记的数量一样多。



**13.6复合语句语法**

13.6.1 BEGIN ... END复合语句
13.6.2语句标签
13.6.3 DECLARE声明
13.6.4存储程序中的变量
13.6.5流控制语句
13.6.6游标
13.6.7条件处理
13.6.8条件处理的限制

 
CREATE PROCEDURE doiterate(p1 INT)
BEGIN
label1: LOOP
SET p1 = p1 + 1;
IF p1 < 10 THEN ITERATE label1; END IF;
LEAVE label1;
END LOOP label1;
END;


DECLARE仅允许在BEGIN ... END 复合语句内部使用




**13.7数据库管理语句**
https://dev.mysql.com/doc/refman/8.0/en/account-management-statements.html
13.7.1.1 ALTER USER语句
13.7.1.2 CREATE ROLE语句
13.7.1.3 CREATE USER语句
13.7.1.4 DROP ROLE语句
13.7.1.5 DROP USER语句
13.7.1.6 GRANT语句
13.7.1.7 RENAME USER语句
13.7.1.8 REVOKE语句
13.7.1.9 SET DEFAULT ROLE语句
13.7.1.10 SET PASSWORD语句
13.7.1.11 SET ROLE语句


**13.7.2资源组管理声明**
https://dev.mysql.com/doc/refman/8.0/en/resource-group-statements.html

13.7.2.1 ALTER RESOURCE GROUP语句
13.7.2.2 CREATE RESOURCE GROUP语句
13.7.2.3 DROP RESOURCE GROUP语句
13.7.2.4 SET RESOURCE GROUP语句


**13.7.3表维护声明**
13.7.3.1 ANALYZE TABLE语句
13.7.3.2 CHECK TABLE语句
13.7.3.3 CHECKSUM TABLE语句
13.7.3.4优化表语句
13.7.3.5 REPAIR TABLE语句




**13.7.7 SHOW语句**

13.7.7.1 SHOW BINARY LOGS Statement
13.7.7.2 SHOW BINLOG EVENTS Statement
13.7.7.3 SHOW CHARACTER SET Statement
13.7.7.4 SHOW COLLATION Statement
13.7.7.5 SHOW COLUMNS Statement
13.7.7.6 SHOW CREATE DATABASE Statement
13.7.7.7 SHOW CREATE EVENT Statement
13.7.7.8 SHOW CREATE FUNCTION Statement
13.7.7.9 SHOW CREATE PROCEDURE Statement
13.7.7.10 SHOW CREATE TABLE Statement
13.7.7.11 SHOW CREATE TRIGGER Statement
13.7.7.12 SHOW CREATE USER Statement
13.7.7.13 SHOW CREATE VIEW Statement
13.7.7.14 SHOW DATABASES Statement
13.7.7.15 SHOW ENGINE Statement
13.7.7.16 SHOW ENGINES Statement
13.7.7.17 SHOW ERRORS Statement
13.7.7.18 SHOW EVENTS Statement
13.7.7.19 SHOW FUNCTION CODE Statement
13.7.7.20 SHOW FUNCTION STATUS Statement
13.7.7.21 SHOW GRANTS Statement
13.7.7.22 SHOW INDEX Statement
13.7.7.23 SHOW MASTER STATUS Statement
13.7.7.24 SHOW OPEN TABLES Statement
13.7.7.25 SHOW PLUGINS Statement
13.7.7.26 SHOW PRIVILEGES Statement
13.7.7.27 SHOW PROCEDURE CODE Statement
13.7.7.28 SHOW PROCEDURE STATUS Statement
13.7.7.29 SHOW PROCESSLIST Statement
13.7.7.30 SHOW PROFILE Statement
13.7.7.31 SHOW PROFILES Statement
13.7.7.32 SHOW RELAYLOG EVENTS Statement
13.7.7.33 SHOW REPLICAS | SHOW SLAVE HOSTS Statement
13.7.7.34 SHOW SLAVE HOSTS | SHOW REPLICAS Statement
13.7.7.35 SHOW REPLICA | SLAVE STATUS Statement
13.7.7.36 SHOW SLAVE | REPLICA STATUS Statement
13.7.7.37 SHOW STATUS Statement
13.7.7.38 SHOW TABLE STATUS Statement
13.7.7.39 SHOW TABLES Statement
13.7.7.40 SHOW TRIGGERS Statement
13.7.7.41 SHOW VARIABLES Statement
13.7.7.42 SHOW WARNINGS Statement



**13.7.8其他行政声明**
13.7.8.1 BINLOG语句
13.7.8.2 CACHE INDEX语句
13.7.8.3 FLUSH声明
13.7.8.4 KILL声明
13.7.8.5 LOAD INDEX INTO CACHE语句
13.7.8.6 RESET语句
13.7.8.7 RESET PERSIST语句
13.7.8.8 RESTART语句
13.7.8.9 SHUTDOWN语句





**13.8实用程序声明**
13.8.1 DESCRIBE陈述
13.8.2 EXPLAIN声明
13.8.3帮助声明
13.8.4 USE声明  USE db1;

DESCRIBE SQL; == EXPLAIN SQL; 

I.获取表结构信息 几个相等
explain `order_investment`; ==  DESCRIBE `order_investment`;
SHOW COLUMNS from `order_investment`;


explain SELECT * FROM `order_investment`;
DESCRIBE SELECT * FROM `order_investment`;


**13.8.3帮助声明**

HELP 'create table';
HELP 'contents';
HELP 'status';


--------------------------------------------------------------------------------------------------

Chapter 15 The InnoDB Storage Engine
**第15章InnoDB存储引擎**
15.1 InnoDB简介
15.2 InnoDB和ACID模型
15.3 InnoDB多版本
15.4 InnoDB架构
15.5 InnoDB内存结构
15.6 InnoDB磁盘结构
15.7 InnoDB锁定和事务模型
15.8 InnoDB配置
15.9 InnoDB表和页面压缩
15.10 InnoDB行格式
15.11 InnoDB磁盘I / O和文件空间管理
15.12 InnoDB和在线DDL
15.13 InnoDB静态数据加密
15.14 InnoDB启动选项和系统变量
15.15 InnoDB INFORMATION_SCHEMA表
15.16 InnoDB与MySQL性能架构的集成
15.17 InnoDB监视器
15.18 InnoDB备份和恢复
15.19 InnoDB和MySQL复制
15.20 InnoDB memcached插件
15.21 InnoDB故障排除
15.22 InnoDB限制
15.23 InnoDB的限制和局限性


**15.1 InnoDB简介**
I.DDL(Data Definition Language 数据定义语言)用于操作对象和对象的属性，
Create语句：可以创建数据库和数据库的一些对象。
Drop语句：可以删除数据表、索引、触发程序、条件约束以及数据表的权限等。
Alter语句：修改数据表定义及属性。

I.DML(Data Manipulation Language 数据操控语言)用于操作数据库对象中包含的数据，也就是说操作的单位是记录。
insert update Delete 

DCL(Data Control Language 数据控制语句)的操作是数据库对象的权限，这些操作的确定使数据更加的安全。
InnoDB是一种兼顾了高可靠性和高性能的通用存储引擎。




**15.1.1使用InnoDB表的好处**
缓冲池，在主内存缓存表和索引数据作为数据被访问。

压缩表和关联的索引

创建和删除索引并执行其他DDL操作，而对性能和可用性的影响要小得多。 在线DDL online


查询性能架构表来监视存储引擎的性能详细信息



**15.1.2 InnoDB表的最佳实践**

15.1.3验证InnoDB是默认存储引擎

SHOW ENGINES;
SELECT * FROM INFORMATION_SCHEMA.ENGINES;



**15.2 InnoDB和ACID模型**
https://dev.mysql.com/doc/refman/8.0/en/mysql-acid.html
A: atomicity. 原子性

C: consistency.一致性

I: isolation.隔离性

D: durability.持久性



**II.atomicity原子性**
主要涉及InnoDB 事务:(事务自动提交、手工commit或者rollback)
+ The autocommit setting.
+ The COMMIT statement.
+ The ROLLBACK statement.




**II.consistency.一致性**
主要涉及内部InnoDB处理，以防止数据崩溃。:

+ 双InnoDB写缓冲区。
+ InnoDB崩溃恢复。


**II.isolation.隔离性**

主要涉及InnoDB 事务，尤其是适用于每个事务的隔离级别。:

+ The autocommit setting.
+ 事务隔离级别和SET TRANSACTION语句。
+ InnoDB锁定的底层细节。



**II.durability.持久性**
您的特定硬件配置交互的MySQL软件功能:(由于取决于您的CPU，网络和存储设备的功能的可能性很多，因此为具体的准则提供最复杂的方面。)

+ 双InnoDB写缓冲区。
+ innodb_flush_log_at_trx_commit:刷盘策略 :(要完全符合ACID，必须使用默认设置1。日志在每次事务提交时写入并刷新到磁盘。) https://dev.mysql.com/doc/refman/8.0/en/innodb-parameters.html#sysvar_innodb_flush_log_at_trx_commit
+ 该sync_binlog变量。:sync_binlog=1：在提交事务之前启用二进制日志到磁盘的同步。
+ 存储设备（例如磁盘驱动器，SSD或RAID阵列）中的写缓冲区。
+ 存储设备中由电池供电的高速缓存。
+ 用来运行MySQL的操作系统，特别是它对fsync()系统调用的支持。
+ 不间断电源（UPS）保护运行MySQL服务器并存储MySQL数据的所有计算机服务器和存储设备的电源。
+ 您的备份策略，例如备份的频率和类型以及备份保留期。
+ 对于分布式或托管数据应用程序，MySQL服务器的硬件所位于的数据中心的特定特性，以及数据中心之间的网络连接。





**15.3 InnoDB多版本** MVCC

https://dev.mysql.com/doc/refman/8.0/en/innodb-multi-versioning.html

InnoDB是一个多版本的存储引擎。 它保留有关已更改行的旧版本的信息，以支持事务功能，
例如并发和回滚。此信息存储在 undo tablespaces(undo 表空间) 的数据结构中，该数据结构称为rollback segment 回滚段。

Innodb使用硅谷你的中的信息来执行事务回滚中所需的撤销操作。
它还使用该信息来构建行的早期版本，以实现一致性读。

在内部,innodb向数据库中存储的每一行添加三个字段:
+ DB_TRX_ID(事务id) 6字节(8位1字节;共6*8位)  字段指示 插入或更新该行的最后一笔交易的标识符。此外，删除在内部被视为更新，在该更新中，该行中的特殊位被设置为将其标记为已删除。

+ DB_ROLL_PTR(滚动指针、指向undo log) 7字节(8位1字节;共7*8位)  字段称为 滚动指针。
滚动指针指向undo log 写入回滚段。如果这一行更新，undo log 记录 包含更新之前的全部信息。
  

+ DB_ROW_ID(聚簇索引ID 行ID) 行id 6字节(8位1字节;共6*8位) 字段包含一个行ID，该行ID随着插入新行而单调增加。如果innodb自动生成局促索引，则该索引包含行ID值。否则，该DB_ROW_ID 列不会出现在任何索引中



![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/mysql隐藏行_undo.png)

回滚段中undo log 日志分别为:insert undolog 和 update undolog 。
insert undolog在事务回滚时才需要，并且在失误提交后可以立刻丢弃。
update undolog也作用于一致性读取中，但是只有不存在快照的事务后才可以删除。

建议您定期提交事务，包括仅发出一致性读取的事务。否则,innodb将服务丢弃更新undolog中的数据，
别切回滚段可能会变得太大，从而填满了它所驻留的undolog表空间。


回滚段中undo log 记录的物理大小通常小于相应的插入或更新的行。您可以使用次信息来计算回滚段所需的空间。

在innodb MVCC中，当您使用SQL 删除时，并不会立即将其物理删除。innodb仅在丢弃为删除而编写的更新撤销日志记录时
才物理删除相应的行以及其索引记录。次删除称作,它非常快，通常花费与执行删除操作的SQL语句相同的顺序。

如果您以大约相同的速率在表中以较小的批次插入和删除行，则由于所有死行，清除线程可能会开始滞后
并且表可能变得越来越大，从而使所有内容都受磁盘约束慢的。在这种
情况下，请限制新行的操作，并通过innodb_max_purge_lay系统变量来向清除线程分配跟多资源。



**多版本和二级索引**
InnoDB多版本并发控制（MVCC）对次级索引的处理方式与对聚簇索引的处理方式不同。
聚簇索引中的记录将就地更新，其隐藏的系统列指向撤消日志条目，可以从中重建记录的早期版本。
与聚簇索引记录不同，辅助索引记录不包含隐藏的系统列，也不会就地更新。

二级索引、辅助索引不包含隐藏列。


**15.4 InnoDB架构**
https://dev.mysql.com/doc/refman/8.0/en/innodb-architecture.html


![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/innodb架构.png)






**15.5 InnoDB内存结构**

15.5.1缓冲池 Buffer Pool
15.5.2更改缓冲区  Change Buffer（写缓存）
15.5.3自适应哈希索引 Adaptive Hash Index
15.5.4日志缓冲区 Log Buffer


**15.5.1缓冲池**


缓冲池是主内存中的一个区域，在InnoDB访问表和索引数据时会在其中进行 高速缓存。缓冲池允许直接从内存访问经常使用的数据，从而加快了处理速度。在专用服务器上，通常将多达80％的物理内存分配给缓冲池。
为了提高大容量读取操作的效率，缓冲池被划分为多个页面，这些页面可以潜在地容纳多行。为了提高缓存管理的效率，缓冲池被实现为页面的链接列表。使用最近最少使用（LRU）算法的变体，将很少使用的数据从缓存中老化掉。
知道如何利用缓冲池将经常访问的数据保留在内存中是MySQL调优的重要方面。


**缓冲池LRU算法**
LRU：最少使用页面置换算法(Least Recently Used),最长时间未被使用的页面!
LFU：最不常用页面置换算法(Least Frequently Used),一定时期内被访问次数最少的页!



最前面是最近访问过的新页面（“年轻”） 的子列表
在末尾，是最近访问过的旧页面的子列表


默认情况下，查询读取的页面会立即移入新的子列表，这意味着它们在缓冲池中的停留时间更长。
针对mysqldump操作或SELECT不带WHERE子句的 语句 执行的表扫描可以将大量数据带入缓冲池，并驱逐同等数量的旧数据，即使不再使用新数据也是如此。


**缓冲池配置 Buffer Pool** 读请求优化

配置缓冲池的各个方面以提高性能:
+ 加大缓冲池
+ 64位缓冲池分多个
+ 将频繁访问的数据保留在内存中，而不必考虑操作突然导致的活动高峰，这些操作会将大量不经常访问的数据带入缓冲池。
+ 执行预读请求，以异步地将页面预取到缓冲池中，以预见对它们的需求。
+ 控制何时进行后台刷盘flushing ，以及是否根据工作负荷动态调整冲洗速率。
+ 配置如何InnoDB保留当前缓冲池状态，以免在服务器重新启动后进行冗长的预热。

SHOW ENGINE INNODB STATUS;

https://dev.mysql.com/doc/refman/8.0/en/innodb-buffer-pool.html



**15.5.2 Change buffer 更改缓冲区、写入缓冲区**   针对二级索引的优化，二级不在缓冲区 就在缓冲区先缓存变更
5.5之前叫insert buffer只支持插入缓冲、后边更新插入都支持就改名叫更新缓冲区。

Change buffer的主要目的是将对二级索引的数据操作缓存下来，以此减少二级索引的随机IO，并达到操作合并的效果。


Change buffer是一种特殊的数据结构,当二级索引页不在缓冲池中时,这些 高速缓存将缓存这些更改。
当页面通过其他读取操作加载到缓冲池中时，可能由INSERT， UPDATE或 DELETE操作（DML）导致的缓冲更改 将在以后合并。

与聚簇索引不同，耳机索引通常是非唯一的，并且二级索引中的插入以相对随机的顺序发生。
同意，删除和更新可能会影响索引树中不相邻的二级索引页。当稍后通过其他操作将
受影响的页读入缓冲池时，合并缓存的更改将避免从磁盘将辅助索引页读入缓冲池所需的大量
随机访问IO。

在系统大部分处于空闲状态或缓慢关机期间运行的清楚操作会定期将更新的索引页写入磁盘。
与将每个值立即写入磁盘相比，清除操作可以更有效的为一系列索引值写入磁盘块。

当有许多受影响的行和许多辅助索引要更新时，更改缓冲区合并可能需要几个小时。
在此期间，IO会增加，这可能会导致磁盘绑定查询的速度显著下降。
提交事务之后，甚至在服务器关闭并重新启动之后，更改缓冲区合并也可能继续发生。

内存中，change buffer 占用了缓冲池的一部分。 
在磁盘上，change buffer 是系统表空间的一部分。
当数据库服务器关闭时，索引更改将存储在其中。





**15.5.3自适应哈希索引** innodb myisam 不支持 hash索引，假hash。innodb会自动建自适应哈希.
个全自动的，内部的行为，用户无法控制或者配置，不过如果有必要，可以选择关闭这个功能（innodb_adaptive_hash_index=OFF，默认为ON）。

SHOW ENGINE INNODB STATUS;

自适应哈希索引可以在innodb在不牺牲事务功能可靠性的情况下，在工作负载和缓冲池
有足够内存的适当组合的系统上，更像是内存数据库。自适应哈希索引由
innodb_adaptive_hash_index 变量启用，或在服务器启动时由禁用

根据观察到的搜索模式，使用索引关键字的前缀构建哈希索引。前缀可以是任何长度，
也可以是哈希树索引中仅B树中的某些值出现。哈希索引是根据对经常访问的索引页面需求而建立
的。

如果表几乎完全适合主内存，则散列索引可以通过启用直接查找任何元素并将
索引值转换为某种指针的方式来加快查询速度。
Innodb具有见识索引搜索的机制。如果innodb发现查询
可以从构建哈希索引中收益，它会自动这样做。

在某些工作负载下，哈希索引查询的速度大大超过了监视索引查找和维护哈希索引
结构的额外工作。在繁重的工作负载下，对自适应哈希索引的访问有时可能
会成为征用的源。带有like运算符和%通配符的查询也往往不会受益。
对于无法从自适应哈希索引中收益的工作负载，将其关闭可以较少不必要的性能开销。
由于很难预测自适应哈希索引是否合适特点的系统和工作负载，因此请考虑启用
和禁用该基准的运行基准测试。

自适应哈希索引功能已分区。每个索引都绑定到特定分区，并且每个分区均受单独锁
存器保护。


    CREATE TABLE `data_dict` (  
      `data_type` varchar(32) NOT NULL COMMENT '数据字典类型',  
      `data_code` tinyint(4) NOT NULL COMMENT '数据字典代码',  
      `data_name` varchar(64) NOT NULL COMMENT '数据字典值',  
      PRIMARY KEY (`data_type`,`data_code`)  
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';  


    ALTER TABLE data_dict ADD INDEX data_dict_dn USING HASH (data_name);  



查看索引类型
    SHOW CREATE TABLE data_dict;  
查看自适应哈希
SHOW ENGINE INNODB STATUS;

http://blog.itpub.net/26736162/viewspace-2144680







**15.5.4日志缓冲区** log buffer      redolog

日志缓冲区是存储区域，用于保持要写入磁盘上的日志文件的数据。日志缓冲区默认16MB
日志缓冲区的内容会定期刷新到磁盘。较大的日志缓冲区使大型事务可以运行，
而无需在事务提交之前将redolog重做日志数据写入磁盘。因此，如果您有更新，插入、删除许多事务，
则增加日志缓冲区的大小可以节省磁盘IO



--------------------------------

**15.6 InnoDB磁盘结构**
15.6.1表格
15.6.2索引
15.6.3表空间
15.6.4双写缓冲区
15.6.5重做日志
15.6.6撤消日志




**15.6.1table**
https://dev.mysql.com/doc/refman/8.0/en/using-innodb-tables.html
15.6.1.1创建InnoDB表
15.6.1.2在外部创建表
15.6.1.3导入InnoDB表
15.6.1.4移动或复制InnoDB表
15.6.1.5将表从MyISAM转换为InnoDB
15.6.1.6 InnoDB中的AUTO_INCREMENT处理


创建InnoDB表
CREATE TABLE t1 (a INT, b CHAR (20), PRIMARY KEY (a)) ENGINE=InnoDB;

-- 查看默认存储引擎
SELECT @@default_storage_engine;

**row 格式**
行格式：InnoDB表 的行格式决定了其行在磁盘上的物理存储方式

InnoDB支持四种行格式：ROW_FORMAT=4
REDUNDANT，COMPACT， DYNAMIC（默认），COMPRESSED 
冗余，紧凑，动态，压缩 


**主键**
具有以下特征的列:
+ 最重要的查询所引用的列。
+ 永远不会留空的列。
+ 永远不会有重复值的列。
+ 插入后很少更改值的列。


查看表属性 innodb属性

SHOW TABLE STATUS ;
SHOW TABLE STATUS  LIKE 'test%' ;
SHOW TABLE STATUS FROM test  LIKE 'test%' ;



**15.6.1.2在外部创建表**
create table Externally;
https://dev.mysql.com/doc/refman/8.0/en/innodb-create-table-external.html

数据目录之外创建表。

**使用数据目录子句**

CREATE TABLE t1 (c1 INT PRIMARY KEY) DATA DIRECTORY = '/external/directory';



**15.6.1.3导入InnoDB表**
15.6.1.4移动或复制InnoDB表


**15.6.1.6 InnoDB中的AUTO_INCREMENT处理**
自增长ID  自动增长ID id自增  ID自增长

https://dev.mysql.com/doc/refman/8.0/en/innodb-auto-increment-handling.html

InnoDB提供了一种可配置的锁定机制，该机制可以显着提高将行添加到带有AUTO_INCREMENT列的表的SQL语句的可伸缩性和性能 。

插入数据的模式:
+ Simple inserts (简单插入)；预先知道插入的行数，普通的insert insert多条
+ Bulk inserts(大量插入):insert ...select；等 replace ...select; load data;
+ Mixed-mode inserts(混合模式插入:不全是简单插入):insert into t1(id,name)values(1,'a')(2,'b')(null,'c')



+ AUTO_INCREMENT Lock Modes:锁定模式
  1.innodb_autoinc_lock_mode = 0 传统模式:5.0前默认 (语句级别，事务里会锁)。
  2.innodb_autoinc_lock_mode = 1 （“连续”锁定模式） mysql8之前 默认 (insert into  select;会执行完以后才释放) 
  3.innodb_autoinc_lock_mode = 2 （“交错”锁定模式） mysql8 默认  (申请完就释放)

binlog：
基于语句的复制(存储空间少)（对于SBR不安全的语句。）
基于行数据的复制(最安全的复制)(性能不高、不能看执行的是什么语句)


+ Lock Mode Usage Implications:锁定模式含义和用法
+ InnoDB AUTO_INCREMENT Counter Initialization：计数器初始化

 

innodb_autoinc_lock_mode=0;
传统锁定模式提供了与innodb_autoinc_lock_mode引入变量之前相同的行为。
由于予以可能存在差异，提供了传统的锁定模式选项以实现向后兼容性，性能测试以及
解决混合模式插入问题。

这种锁定模式下，所有类似与insert的语句将获得特殊的表级autoince,以将其出入具有uato_increment列
的表中。此锁通常保持在语句的末尾，以确保为给定的insert语句序列可以预测和可重复的顺序
分配自动递增值，病确保自动递增值给任何给定语句分配的都是连续的。

对于给予语句的复制，这 意味着副本服务器上复制SQL语句时，自动增量列使用与原服务器相同值。
执行多条insert语句的结果是确定性的，并且副本将复制 与源上相同的数据。
如果对多个inset语句生产的自动增量值进行脚趾，则两个并非insert语句的结果将是不确定的，
并且无法使用给予语句的复制可靠的传播到副本服务器。
TX1： insert into t1 (c2) select 1000 rows from another table;
TX2: inset into t1(c2)values ('xxx');
innodb无法预先判断从tx1 select语句中检索了多少行，并且随着语句的进行，它每次一次分配自动增量值。
使用表级锁，一次执行执行一个insert应用表的语句T1，并且不会交错使用不同国语剧生成自动递增编号。
tx1 insert select 语句生成的自动增量值是连续的，并且TX1语句使用的自动增量值
是连续的。 insert tx2红的语句小于或大于用于TX1的所有语句，具体取决于那个语句首先执行。

只要从二进制日志中重放SQL语句时执行顺序相同，结果与TX1和TX2首次 运行时候的结果相同。因此，
在语句结束之前报纸的标记所使用insert使用自动增量的语句可以安全第一用于给予语句的复制。但是，当
多个事务通知实行insert语句时，这些表及本所会限制并发性和可伸缩性。

在前面的实例中，如果没有标记所，则insert tx2中用于 auto increment列的只完全去决定语句执行的时间。如果inserttx2的ts在insertTX1的运行时
执行，则由两个insert语句分配的特定个自动增量值是不确定的，并且可能因运行而异。

在连续锁定模式下，innodb可以避免对行数一直的简单插入语句使用表级autoinc锁定，并且仍然保留确定性执行
和给予语句的复制的安全性。

如果您不使用二进制日志来重播SQL语句作为回复或复制的一部分，则可以使用交错摸使用来autoinc所的所有使用
以实现更大的并发性和性能，但以云溪自动讲个的代为为代价，语句分配的怎量编号，病可能适同事执行的 语句分配的编号交错。



**InnoDB AUTO_INCREMENT锁定模式的用法含义**
+ 在复制中使用自动增量:
  binlog基于语句复制(statement-based replication) 是用0或1模式 ；使用2模时或者主从使用不同的模式时，不能保证两个相同。
  binlog基于行数据或混合模式(row-based或者mixed-format 复制),则所有自动增量锁定模式都是安全的。

+ auto_increment指定 null或者0 都视为未指定，并为其生成新值。

+ auto_increment 指定 负值 结果是不确定的。

+ auto_increment 指定 大于最大值 结果是不确定的。

+ 批量插入时 0、1都是连续的，没有间隙。2可能有间隙

+ 由“混合模式插入”分配的自动增量值 0、1、2三种模式都不同

+ AUTO_INCREMENT在INSERT语句 序列的中间 修改列值。8以后会自动变成最大值。



**InnoDB AUTO_INCREMENT计数器初始化**

5.7之前在内存中重启就清空。 再启动就是 select max(id)

8.0以后记在redolog 重启不清空




**15.6.2索引**
https://dev.mysql.com/doc/refman/8.0/en/innodb-indexes.html
15.6.2.1聚集索引和二级索引
15.6.2.2 InnoDB索引的物理结构
15.6.2.3排序索引构建
15.6.2.4 InnoDB全文索引

**聚集索引**
+ 表上定义primary key就是聚集索引、聚簇索引。逻辑唯一非空、或者使用主键的列，就增加一个自增列。
+ 没有primary key innodb就会选择一个唯一和not null的为聚集索引。
+ 没有适合的 就建隐藏的狙击索引 gen_clust_index,单调递增。


**二级索引**
除了聚簇索引以外所有的索引都是二级索引。innodb中辅助索引的每个记录都包含改行的主键列和辅助索引指定的列。

主键较长，则辅助索引使用更多的空间。




**15.6.2.2 InnoDB索引的物理结构**

除空间索引外，InnoDB 索引是B树数据结构。

+ InnoDB 索引是B树数据结构
+ 空间索引使用 R树（R-tree是一种将Ｂ树扩展到多维情况下得到的数据结构）


索引记录存储在其B树或R树数据结构的叶页中。
索引页的默认大小为16KB。


**15.6.2.3排序索引构建**

索引构建分为三个阶段：

第一阶段，将 扫描聚簇索引，并生成索引条目并将其添加到排序缓冲区（或者写入临时文件中）0。
第二阶段中，将一个或多个运行写入临时中间文件，对文件中的所有条目执行合并排序。
第三个阶段，将已排序的条目插入 B-tree中



**15.6.2.4 InnoDB全文索引**

InnoDB全文索引具有倒排索引设计






**15.6.3表空间**
15.6.3.1系统表空间
15.6.3.2每表文件表空间
15.6.3.3通用表空间
15.6.3.4撤消表空间
15.6.3.5临时表空间
15.6.3.6在服务器离线时移动表空间文件
15.6.3.7禁用表空间路径验证
15.6.3.8在Linux上优化表空间空间分配
15.6.3.9表空间AUTOEXTEND_SIZE配置



**15.6.3.1系统表空间**
5.7 系统表空间是: InnoDB数据字典，双写缓冲区，更改缓冲区和撤消日志的存储区域
8.0 系统表空间是 :change buffer(更改缓冲区)的存储区。

在以前的MySQL版本中，系统表空间还包含doublewrite缓冲区存储区。(MySQL 8.0.20起，此存储区位于单独的doublewrite文件中)
系统表空间可以具有一个或多个数据文件。ibdata1在数据目录中创建一个名为的系统表空间数据文件 。


元数据:描述数据的数据。



**15.6.3.2 File-Per-Table Tablespaces**
表文件空间 :innodb表的数据和索引。存储在单个数据文件中(table.ibd)

**表文件空间的优势:**

与共享表空间（例如系统表空间change buffer存储区或常规表空间）相比，每表文件表空间具有以下优点。
+ 表文件空间删除磁盘会回收，共享表空间(系统、常规)删除不会
+ alter table 对共享表空间会增加磁盘占用，并且不会释放
+ TRUNCATE TABLE 在每个表文件表空间中的表上执行时，性能会更好。
+ 可以在单独存储设备上创建数据文件，优化IO
+ 单独导入一张表
+ 数据损坏时 单表恢复快
+ 单表空间容易观察文件大小等
+ 单个文件有64T限制，每个文件都有64t 

与共享表空间(changebuffer和常规空间)比缺点:
+ 使用每表文件表空间，每个表可能有未使用的空间，只能由同一表的行使用，如果管理不当，则会浪费空间。
+ fsync对每个表的多个数据文件而不是单个共享表空间数据文件执行操作。
+ mysqld必须为每个表文件空间保留一个打开的文件句柄，如果每个表文件空间中有许多表，则可能会影响性能。
+ 当每个表都有其自己的数据文件时，需要更多的文件描述符。
+ 可能存在更多碎片，这可能会影响 DROP TABLE表扫描性能。但是，如果管理碎片，则每表文件表空间可以提高这些操作的性能。
+ 删除每个表文件表空间中的表时，将扫描缓冲池，对于大型缓冲池可能要花费几秒钟的时间。使用宽泛的内部锁定执行扫描，这可能会延迟其他操作。


**15.6.3.3通用(常规)表空间**
https://dev.mysql.com/doc/refman/8.0/en/general-tablespaces.html
InnoDB 使用CREATE TABLESPACE语法创建的共享表空间。 


**通用(常规)表空间功能:**

+ 常规表空间是共享表空间，能够存储多个表的数据。
+ 常规表空间比每表文件表空间具有潜在的内存优势 。服务器在表空间的生存期内将表空间元数据保留在内存中。
+ 常规表空间支持所有表行格式和相关功能。
+  CREATE TABLE 有TABLESPACE 选项
+   ALTER TABLE 有TABLESPACE 选项


**通用(常规)表空间限制**

+ 生成的表空间或现有表空间不能更改为常规表空间。
+ 不支持创建临时通用表空间。
+ 常规表空间不支持临时表。



**15.6.3.4 Undo Tablespaces 撤销表空间**

撤消表空间包含undo log，undo log是记录的集合，其中包含有关如何通过事务撤消对聚集索引记录的最新更改的信息。

5.7撤消日志默认情况下存储在:系统表空间
8.0初始化MySQL实例时，会创建两个默认的撤消表空间。undo_001、 undo_002  :




**15.6.3.5临时表空间**
InnoDB 使用会话临时表空间(Session Temporary Tablespaces)和全局临时表空间(Global Temporary Tablespace)。




会话临时表空间(Session Temporary Tablespaces):
存储用户创建的临时表 和 当InnoDB配置为磁盘内部临时表的存储引擎时由优化器创建的内部临时表。

全局临时表空间(Global Temporary Tablespace)：ibtmp1
用户创建的临时表进行更改的回滚段。








--------------------------------------------------------------------
**15.6.4双写缓冲区**


doublewrite缓冲区是一个存储区域：

5.7doublewrite缓冲区存储区位于InnoDB系统表空间中。
8.0doublewrite缓冲区存储区位于doublewrite文件中

**Double Write的思路很简单:**
A. 在覆盖磁盘上的数据前，先将Page的内容写入到磁盘上的其他地方(InnoDB存储引擎中的doublewrite  buffer，这里的buffer不是内存空间，是持久存储上的空间).
B. 然后再将Page的内容覆盖到磁盘上原来的数据。

A异常:原来的数据没有被覆盖，还是完整的。
B异常:原来的数据不完整了，但是新数据已经被完整的写入了doublewrite buffer.  因此系统恢复时就可以用doublewrite buffer中的新Page来覆盖这个不完整的page.



尽管数据被写入两次，但双写缓冲区不需要两倍的I / O开销或两倍的I / O操作。只需一次fsync()调用操作系统即可将数据按较大的顺序块写入doublewrite缓冲区（除非 innodb_flush_method设置为 O_DIRECT_NO_FSYNC）。



**15.6.5重做日志redo log** ib_logfile0 ib_logfile1

重做日志redo log 是基于磁盘的数据结构,在崩溃恢复期间用于纠正不完整事务写入的数据。
在正常操作期间，重做日志对更改表数据的请求进行编码，这些请求是由SQL语句或低级API调用产生的。在初始化过程中以及接受连接之前，将自动重播在意外关闭之前未完成更新数据文件的修改。


**默认情况两个文件物理表示:**
ib_logfile0、ib_logfile1

MySQL以循环方式写入重做日志文件




**组提交以重做日志刷新Group Commit for Redo Log Flushing**
innodb 像任何符合acid的数据库引擎一样，在提交事务之前刷新事务的重做日志。
innodb使用组提交功能将多个此类刷新请求分组在一起，以避免每次提交都进行一次刷新。
使用组提交，innodb可以对日志文件进行一次写入操作，以对大约同事提交的多个用户
事务执行提交操作，从而显著提高了吞吐量。





**15.6.6撤消日志undo log**
撤消日志是与单个读写事务关联的撤消日志记录的集合。


撤消日志记录包含有关如何撤消事务对**聚簇索引** 记录的最新更改的信息。



-------------------------------------------------------------------------------

**15.7 InnoDB锁定和事务模型**
15.7.1 InnoDB锁定
15.7.2 InnoDB交易模型
15.7.3在InnoDB中由不同的SQL语句设置的锁
15.7.4幻影行
15.7.5 InnoDB中的死锁
15.7.6事务调度




**15.7.1 InnoDB locking**

I.共享锁和排他锁
InnoDB实现标准的行级锁定，其中有两种类型的锁： 共享（S）锁和排他（X）锁。

+ 共享锁读锁 shared (S) lock :事务持有这一行的读锁.
+ 排他锁写锁 exclusive (X) lock permits the transaction that holds the lock to update or delete a row.


如果T1持有 r行的 **S共享锁**，则来自不同事务T2对 r行有以下处理方式:
情况1:T2 事务请求 r行的 S共享锁，则立刻可以获得，并且T1、T2事务都持有r行的 S共享锁。
情况2:T2 事务请求 r行的 X排他锁,不能获得立刻授权。



如果T1持有 r行的 **X排他锁**，则不允许T2任何 类型锁对r行的请求。T2必须等待T2释放对r行的锁定。

Mysql有表锁X、S:innodb有行锁。

**Intention Locks 意向锁**

Innodb支持多种粒度锁定，允许行锁和表锁并存。例如，诸如的语句在制定表上lock tables write采用
排他锁X锁。为了使在多个粒度级别上的锁定变得切实可行，请
innodb使用意向锁。意向锁是表级锁，指定事务稍后对表中的行需要哪种类型的锁(共享锁或排他锁)有两种类型的意向锁。
意向共享锁IS，指示一个事务打算设置一个共享上各行锁定在表中。
意向独占锁IX,指示一个事务打算设定各行的排他锁在表中。
 例如
select  for  share 设置一个IS锁。
select for update  设置一个IX锁。

意向锁协议如下：
在事务可以获取表中某行上的共享锁之前，它必须首先获取该表意向共享锁或者更高级别的锁。
在事务可以获取表中某行的排它锁之前，必须首先获取该表的意向排他锁。


如果锁与校友锁兼容，则将其授予请求的事务，但如果与现有锁冲突，则不授予该锁。失误等待
直到冲突的现有锁被释放。如果多订请求与现有锁定发生冲突，并且由于可能导致思索
而无法被授权许可，则会发生错误。



意向锁不会阻止除全表请求以外的任何内容。意向锁的主要目的就是表明 有人正在锁定表中的行，或者将要锁定表中的行。


**Record Locks 记录锁**
record locks是对索引记录的锁定。  例如, select c1 from t where c1=10 for update;
可以防止从插入、更新或删除行。其中的值的任何其他交易t.c1 是10。

record locks始终是锁定索引记录，及时没有定义索引的表也是如此。
这种情况下，请innodb创建一个隐藏的聚集索引，并将该索引用于记录锁定。


**Gap Locks 间隙锁**
gap locks是对索引记录之间的间隙的锁定，或者是对第一个或最后一个索引记录之前的间隙的锁定。
例如，select c2 from t where c1 between 10 and 20 for update;防止
其他失误将value 15 插入到t.c1字段，无论现在是否有这样的值，因为该范围中所有现有
值之间的间隙都是锁定的。

间隙可能跨越单个索引值，多个索引值，甚至空值。

间隙锁是性能和并发性之间权衡的一部分，并且在某些失误隔离级别而非其他级别中使用。


对于使用唯一索引来锁定唯一行来锁定的语句，不需要间隙锁定。(这不包括搜素条件仅保护多列唯一索引的某些列的情况，)
SELECT * FROM child WHERE id = 100;

如果ID 未建立索引或具有非唯一索引，则该语句会锁定前面的间隙。

间隙锁定innodb是 纯粹抑制性的，这意味着它们的唯一目的就是防止其他事务插入间隙。间隙锁可以共存。
一个事务进行的间隙锁定不会阻止另一个事务对相同间隙进行锁定。
共享间隙锁和排他间隙锁之间没有区别。它们彼此不冲突，并且执行相同的功能。

间隙锁定可以显示禁用。如果将事务隔离级别更改为，read committed .将禁用间隙锁进行搜索和索引扫描，
并且仅仅将用于外键约束检查和重复键检查。

使用read committed隔离级别还有其他影响。MYSQL 评估where条件后，将释放不匹配行的记录锁定。对于对于update语句，
innodb执行半一制读取，以便将最新的提交版本返回给mysql，以便mysql可以确定行是否与where条件匹配update




**Next-key Locks**

next key 锁是索引记录上的record lock和 gap lock的组合。

innodb执行行级锁锁定的方式是，当它搜索或扫描表索引时，会在遇到的索引记录上设置共享
或互斥锁。因此，行级锁实际上是索引记录锁定。索引记录上的next key lock也会影响该索引
记录之前的间隙。即，next key lock 是索引记录锁加上索引记录之前的间隙上的间隙锁定。
如果一个会话R在索引中的记录上具有 共享锁 或排他锁，则另一会话不能在R索引顺序之前
的间隙中插入新的索引记录。


假设索引包含10 11 13 和20 .次索引可能的next key 锁定涵盖了以下间隔，其中() 表示不包含  []表示包含：
(negative infinity, 10]
(10, 11]
(11, 13]
(13, 20]
(20, positive infinity)

对于最后一个间隔，next-key锁定将间隙锁定在索引中的最大值之上，并且 supremum 伪记录的值
高于索引中的任何实际值。最高不是真正的索引记录，因此，实际上，在nextkeylock仅锁定最大
索引值之后的间隙。

默认情况下，innodb 以为 repeatable read 事务隔离界别运行。
在这种情况下，请innodb使用next key 锁定进行搜索和索引扫描，以防止产生幻象行。

用于next key 锁定事务数据出现类似于下面 show engine innodb status



**插入意图锁Insert Intention locks**
insert intention locks是一种通过 insert 行插入之前的操作设置的间隙锁。此锁发出插入意图的信号是，
如果多个失误未插入间隙中的相同位置，则无需等待插入到同意索引间隙中的多个事务。
假设有索引记录，其值分别是4\5。单独的事务分别尝试插入值5和6，在获得插入行的排他锁之前，
每个事务都使用插入意图锁来锁定4和7之前的间隙，但不要互相阻塞，因为
行是无冲突的。

下面的实例演示了在获得对插入记录的排他锁之前，使用插入意图锁的事务。该实力设计两个客户端A和B。

 CREATE TABLE child (id int(11) NOT NULL, PRIMARY KEY(id)) ENGINE=InnoDB;
INSERT INTO child (id) values (90),(102);

T1：
START TRANSACTION;
 SELECT * FROM child WHERE id > 100 FOR UPDATE;


T2：
START TRANSACTION;
INSERT INTO child (id) VALUES (101);  -- 会被锁

T2：
SELECT * FROM child WHERE id = 103 FOR UPDATE;  -- 103 不存在 不会被锁

SELECT * FROM child WHERE id = 102 FOR UPDATE;  -- 102 存在 会被锁


SHOW ENGINE INNODB STATUS;


**AUTO-INC Locks**

一个auto-inc 锁是通过交易将与表中取得一个特殊的表级别锁 auto_increment列。
在最简单的情况下，如果一个事务正在向表中插入值，则任何其他失误都必须等待自己在该表中进行插入，
，以便第一个事务插入的行接收连续主键值。

该innodb_autoinc_lock_mode 配置选项控制用于自动增加锁定的算法。它使您可以选择
如何在可预测的自动增量值序列与插入操作的最大并发性之间进行权衡。


**空间索引的谓词锁 Predicate Locks for Spatial Indexes**

spatial 空间索引 多维数据 GIS相关



---------------------Transaction  事务模型-------------------------------------------------------
https://dev.mysql.com/doc/refman/8.0/en/innodb-transaction-model.html
**15.7.2 InnoDB交易模型**

15.7.2.1事务隔离级别
15.7.2.2自动提交，提交和回滚
15.7.2.3一致的非锁定读取
15.7.2.4锁定读取


在innodb transaction model中，目标是将多版本数据库的最佳属性与传统的两阶段锁定结合。
InnoDB在默认情况下，采用oracle风格，在行级别执行锁定 并以非锁定一致读的形式运行查询。
锁信息以innodb节省空间的方式存储，因此不需要锁升级。通常允许多个用户锁定innodb表中国年的每一行
或者该行的任何随机子集，而不会导致innodb内存耗尽。


**15.7.2.1 Transaction Isolation Levels事务隔离级别**
https://dev.mysql.com/doc/refman/8.0/en/innodb-transaction-isolation-levels.html
事务隔离级别是数据库处理的基础之一。隔离是ACID中的I;隔离级别是一种设置，用于在多个失误同事进行更改和执行查询时微调性能与结果的可靠性，
一致性和可重复性之间的平衡。
原子性
一致性
隔离性
持久性

Innodb报价由SQL描述的所有四个事务隔离级别:
read Uncommitted.
read committed
repeatable read：默认
serializable.

用户可以使用set transaction语句更改单个回话或者所有后续链接的隔离级别。
要为所有链接设置服务器的默认隔离级别， -- transaction - isolation

Innodb支持使用不同的锁定策略在此描述的每个事务隔离级别。
您可以对默认数据 repeatable read级别强制执行高度一致性，一遍在重要的ACID合规数据上进行操作。
或者，在批量报告之类的情况下，精确的一致性和可重复的结果与最小化锁定开销相比，不那么重要，
您read committed设置可以放宽甚至使用一致性规则 read uncommitted。
 serializable 实施比甚至更严格的规则 repeatable read ,并且主要 用于特殊情况下 XA
失误以及用于解决并发和死锁问题。

下边描述了mysql 如何支持不同的失误级别。列表从最常用的级别到最不常用的级别。


**repeatable read** 可重复读
这是默认隔离级别innodb。同一个事务中的一致性读取将要讲读取 第一次读取建立的快照。
这意味着,如果您 select 在同一事务中发出多个普通非锁定语句，则这些select语句
彼此之间也是一致的。

对于锁定读取 for update 、for share ,update delete语句，锁定取决于语句是使用具有
唯一搜索条件的唯一索引还是范围类型搜索条件。

+ 对于具有唯一搜索条件的唯一索引，innodb仅锁定找到的索引记录，而不锁定其前的间隙。
+ 对于其他搜索条件，innodb使用间隙锁定或者 next-key locks来锁定扫描的索引范围，以
组织其他会话插入该范围所覆盖的间隙。
  

**read committed** 读提交
即使在同一个事务中，每个一致的读取都将设置并且读取其自己的新快照。

对于锁定读取 for update for share update delete 语句，innodb 仅锁定索引记录，
而不锁定他们之间的间隙，因此允许在多ing记录旁边自由插入新纪录。
间隙锁定仅用于外键约束检查和重复建检查。

由于禁用了间隙锁定，因此可能胡IC喊声欢度问题，因为其他回话可以在间隙中插入新行。

Read committed隔离级别仅支持基于行的二进制日志记录(row-based、不支持statment-base 记录SQL语句)
如果read committed 配合 binlog_format=mixed混合模式，服务器将自动使用基于row-base 的binlog


使用read committed具有其他效果;

+ 对于update 或者delete语句,innodb仅对其更新或删除的行持有锁定。mysql评估where 条件以后，
将释放不匹配行的记录锁。这大大降低了死锁的可能性，但是仍然可以发生。

+ 对于update语句，如果某行已被锁定，则innodb执行半一致读取，将最新的提交版本返回给mysql,
以便mysql可以确定该行是否与where 条件匹配update。 如果该行匹配更新，则mysql再次读取改行，
  这一次将innodb其锁定或等待对其进行锁定。
  



**Read uncommitted 读未提交**
select 语句以非锁定方式执行，但是可能会使用行的早期版本。因此使用此隔离级别，此类读取不一致。
这也称为脏读。否则，次隔离级别的工作方式类似与 read commited.


**Serializable串行化**
此级别类似于 Repeatable read 但是innodb将所有普通select 语句隐式转换为 select for share if automommit 禁用。
如果autocommit启用，则select是其自身的事务。因此，他被认为是只读的，
如果作为一致性读取非锁定并且不需要阻塞其他事务，则可以序列号。
select 如果其他事务已经修改所选行，则要强制平原组织，请禁用automooit .




**15.7.2.2自动提交，提交和回滚**
https://dev.mysql.com/doc/refman/8.0/en/innodb-autocommit-commit-rollback.html

在innodb中，所有用户活动都发生在交易内部。如果autocommit启用了，则每个SQL语句自己形成一个事务。
默认情况下，mysql为每个autocommit启用的新连接启动回话，因此如果该SQL语句未返回错误，
则mysql在每个SQL语句之后进行提交。如果一条语句返回错误，则提交或回滚行为取决于该错误。

具有回话autocommit启动可以通过显示启动它制定多语句事务
start transaction 或者begin 语句，并用它结束commit或者rollback声明。

如果autocommit与set autocommit=0的会话中禁用了mode，则该会话将始终打开一个事务。
一个commit或者rollback语句结束当前事务和一个新的开始。

如果禁用autocommit回话，没有显示提交最终事务的情况下结束，则mysql将要回滚该事务。

某些语句隐式结束事务，就好像您commit在执行该语句之前已经完成了。

这commit意味着当前失误总所做的更改将要变成永久性，并在其他回话中课件。一个rollback声明，
而另外一方面，取消当前事务中的所有修改。双方commit并rollback释放所有innodb
当当前事务期间设置的锁。


将DML操作与事务分组

默认情况下，链接到mysql服务器开始与自动提交模式启用，当你执行它自动提交每个SQL语句。如果您有
其他数据库系统的经验，则可能不熟悉这种操作方式，在标准实践中，
发出一些列DML语句并提交它们全部回滚。

要使用多语句事务，请使用SQL语句关闭自动提交功能，set autocommit=0 并使用commit 或 rollback视情况结束每个事务。
要保留自动提交功能，请以开始每个交易，start transaction \ commit\rollback结束。



**15.7.2.3一致的非锁定读取Consistent Nonlocking Reads**
一致性读取，该装置innodb在一个时间点使用多版本存在于查询数据库快照。
该查询将看到在该时间点之前提交的事务所做的更改，而看不到以后或未提交的事务所做的更改。
该规则的例外是查询可以看到同一事务中较早的语句所做的更改。此异常导致以下异常：
如果更新表中得某些行，则select 查看已经更新行得最新版本，但也可能会看到任何行得旧版本。
如果其他回话同时更新同一张表，则异常意味着您可能会以数据库中不存在状态查看该表。


如果事务隔离级别为repeatable read 重复读 ,则同一事务中所有一直读取将读取由该事务中第一个此类读取
建立的快照。 可以通过提交当前事务并在此之后发出新
查询来获取查询的更新快照。

使用 read committed隔离级别，事务中得每个一致性读取都会社这并读取其自己的新快照。


一致性读取是在默认模式innodb检查select中的语句read committed repeatable read隔离级别。
一致性读取不会在它访问的表上设置任何锁，因此其他回话可以自由地在对表
执行一致性读取的同时修改这些表。


假设您以默认 repeatable read 隔离级别运行。当您发出一致性读取，innodb将为您的事务提供一个
时间点，根据时间点查询您看到数据库。
如果分配了时间点后另一个事务删除了一行并提交，则看不到该行已被删除。插入和更新的处理方式相似。


您可以通过提交交易，然后再进行另一个select 或者 提前交易时间 start transaction with consistent snapshot;

这称为多版本并发控制。

以下示例中，回话A仅在B提交了插入并且A也提交了时，才看到B插入的行，因此时间点比B提交提前了。


如果要查数据库的最新状态，请使用read committed隔离级别或者 锁定读
select * from t for share;


使用read committed隔离级别，事务中得每一个一致性读取都会设置并读取自己的新快照。使用for
share，将发生锁定读取； select 阻塞直到包含最新行得事务结束。


一致性读取不适合某些DDL语句
+ 一致性读取无法姐姐问题 drop table,因为mysql无法使用已删除innodb表并被破坏该表。
+ 一直读取不能姐姐 alter table 创建临时表时创建原始表的临时副本并删除原始表的操作。
当您在事务内重新发出一致IDE读取时，新表中得行时不可见得，因为在获取
  快照时这些行不存在。这种情况下，事务返回一个错误，

读取的类型因选择子句 insert into select 中的选择而有所不同update select create table select 未指定 for update for share 
+ 默认情况下，innodb对这些语句使用更强的锁定，并且该select部件的行为类似于read committed,其中即使
在同一事务总的每次一致性读取，也会设置并读取其自己的新快照。
  
+ 要在这种情况下执行非锁定读取，请将事务的隔离级别设置为 read uncommittet 或read committed避免
在从选点表读取的行上设置锁定。


I.问题。可重复读，B会话已经提交，A会话读取到旧知识，数据是否 已经更新。
**15.7.2.4锁定读取Locking Reads** 没有一致性，只有锁定读

https://dev.mysql.com/doc/refman/8.0/en/innodb-locking-reads.html

如果查询数据，然后在同一事务中插入或更新相关数据，则常规select语句无法提供足够的保护。
其他事务还可以更新或者删除刚查询的相同行。
innodb支持两种类型的锁定读取，这些读取提供了额外的安全性"
**for share**
在读取的任何行上设置共享模式锁定。其他回话可以读取行，但是在事务提交之前不能修改它们。
如果这些行中得任何一个被尚未提交的另一事务更改，则查询将等待直到该 事务结束，然后使用最新值。

for share 替代 lock in share mode(向后兼容) 。这些语句是等效的。 

使用NOWAIT和
SKIP LOCKED锁定读取并发

Grant Table Concurrency（授权并发读）。

**for update**
对于索引记录，搜索遇到的情况，锁定行和任何关联的索引条目，就像您update对这些行发出语句一样。
其他事务被阻止 for share 在某些事务隔离级别中更新这些行，执行操作或者读取数据。
一致性读取将忽略读取试图中存在的记录上设置的任何锁。(记录的旧版本无法锁定；可以通过在记录的内存副本上应用撤销日志来重构它们)

for update 需要select权限和至少一个delete、 lock tables 或者update权限。

这些子句在处理单个表中或查分成多个表的树结构或图结构数据时最有用。您从一处到另外一处遍历边缘或树枝，
同时保留返回的权利并更改任何这些指针值。

提交或回滚事务时，将释放for share 和设置所有锁定for update.

笔记
锁定读取仅仅在autocommit 时不可用时才生效
start transaction
autocommit=0;


**不会锁定子句的行**

SELECT * FROM t1 WHERE c1 = (SELECT c1 FROM t2) **FOR UPDATE**;

**会锁定子句的行**

SELECT * FROM t1 WHERE c1 = (SELECT c1 FROM t2 **FOR UPDATE**) **FOR UPDATE**;


**例子1-锁定读for share**
插入子表前需要父表存在数据.(防止在两句话中间，有人把爹给删了)
SELECT * FROM parent WHERE NAME = 'Jones' FOR SHARE;
insert child();


**例子2-锁定读for update**
有一个计数器 两个回话相同的数据时不对的，需要用for update;
SELECT counter_field FROM child_codes FOR UPDATE;
UPDATE child_codes SET counter_field = counter_field + 1;

for update 读取最新的可用数据，并在读取的每一行上设置排它锁。因此，它设置了与update SQL的相同锁。
 
UPDATE child_codes SET counter_field = LAST_INSERT_ID(counter_field + 1);
SELECT LAST_INSERT_ID();

LAST_INSERT_ID(counter_field + 1) 返回了最后更新完的值。



**锁定读取并发:NOWAIT和 skip locked**
Locking Read Concurrency with NOWAIT and SKIP LOCKED

如果某行被某个事务锁定，则请求同一被锁定的行 for update 、for share事务必须等待，
直到阻塞的事务释放该行锁为止。此行为可以防止事务更新或删除其他事务为更新而查询的行。
但是如果希望在锁定请求的行时立即返回查询，或者可以接受从结果集中排出锁定的行，则不必等待释放行锁。

为了避免等待其他事务释放行锁，nowait并且skip locked选项可以与使用for update forshare 语句使用。

**NOWAIT** 有锁定返回失败
永不等待的锁定读取，将等待获取行锁。查询将立即执行，如果请求的行被锁定，则会失败并显示错误。

**SKIP locked**
使用skip locked永不等待的锁定读取将等待获取行锁。查询立即执行，从结果集中删除锁定的行。

笔记
跳过锁定行得查询将返回不一致的数据视图。skip locked因此不适合一般交易工作。但是，当多个
会话访问相同的类似队列的表时，可以使用它来避免锁征用。


nowait 、skip locked 仅适用于行级锁。

对于基于语句的肤质 使用 nowait skip locked 不安全。

CREATE TABLE t (i INT, PRIMARY KEY (i)) ENGINE = InnoDB;
INSERT INTO t (i) VALUES(1),(2),(3);

session1
START TRANSACTION;
SELECT * FROM t WHERE i = 2 FOR UPDATE;




Session 2:
SELECT * FROM t WHERE i = 2 FOR UPDATE NOWAIT;
ERROR 3572 (HY000): Do not wait for lock.


Session 3:
START TRANSACTION;
SELECT * FROM t FOR UPDATE SKIP LOCKED;

返回1、3 没有2.


**15.7.3在InnoDB中由不同的SQL语句设置的锁**
https://dev.mysql.com/doc/refman/8.0/en/innodb-locks-set.html

locking read(for share 、for update) 、update、delete一般使用 record lock 作用在索引上。
where语句中是否存在排除该行得条件并不重要。innodb不记得确切的where 条件，
而是只知道扫描了那个索引范围。这些锁通常是 next-key ,也可以阻止紧接在记录之前插入 gap中。
但是，可以明确禁用gap lock，这将导致不使用next key 锁定。 


如果在搜索中使用二级索引，并且要设置的索引记录锁时互斥的，那么innodb还将索引相应的
聚集索引记录并在其上设置锁。

如果没有合适您的语句索引，并且mysql必须扫描整个表以便于处理该语句，则表的每一行都将被锁定，
从而阻塞其他用户对该表的所有插入。

Innodb设置特定类型的锁
+ select ...from 是一致性读取，读取数据库的快照并且不设置锁定，除非将事务隔离级别设置为serializable
对于serializable级别，搜索在遇到的索引记录上设置共享的 next-key锁定。但是，对于使用唯一索引
来搜索唯一行的行锁定语句，仅需要索引记录锁定。

+ select for update 、for share使用唯一索引获取锁扫描行，并释放不包含在结果集中的资格
(例如，如果他们不符合给的条件行语句where条款)。但是，在某些情况下，行可能不会立即被解锁，
因为结果行与其原始源头之间的关系在查询执行过程中会丢失。例如，union从表中扫描并锁定的行坑你
会在评估它们是否符合结果集之前插入到临时表中。在这种情况下，临时表中得行与原始表中得行
之间的关系将丢失，并且直到查询执行结束后，侯航才被解锁。


+ 对于锁定读取 select forupdate share、update和delete语句，采用取决于语句是使用具有唯一搜索条件
的唯一索引还是范围类型的搜索条件
   + 对于具有唯一索引条件的唯一索引，innodb仅锁定找到的索引记录，而不锁定其前的空白。
   + 对于其他搜索条件和非唯一索引，innodb使用gap锁 或者 next-key锁定索引范围，以阻止其他回话插入该范围所覆盖的间隙。
 
（一致性非锁定读取忽略所有锁）对于索引记录，搜索遇到,select for update阻止其他回话执行 forshare 或读取某些事务隔离级别。
一致性读取将忽略读取试图中存在的记录上设置的任何锁定。


UPDATE ... WHERE ...在搜索遇到的每条记录上设置排他的下一键锁定。但是，对于使用唯一索引来搜索唯一行的行锁定的语句，仅需要索引记录锁定。

update修改一个聚簇索引记录，将隐式锁定对应的二级索引。update在插入新的二级索引记录之前执行重复检查扫描时，以及在插入新的
二级索引记录时，该操作还将对受影响的二级索引记录进行共享锁定。



DELETE FROM ... WHERE ...在搜索遇到的每条记录上设置排他的下一键锁定。但是，对于使用唯一索引来搜索唯一行的行锁定的语句，仅需要索引记录锁定。


INSERT在插入的行上设置排他锁。该锁是索引record lock记录锁，不是下一键锁（即没有gap lock），并且不会阻止其他会话插入到插入行之前的间隙中。

在插入行之前，设置了一种称为插入意向间隙锁的间隙锁。


**insertOrUpdate**
INSERT ... ON DUPLICATE KEY UPDATE与简单的区别在于， INSERT在发生重复键错误时，将排他锁而不是共享锁放在要更新的行上。对重复的主键值采用排它索引记录锁定。唯一的下一键锁定用于重复的唯一键值。

REPLACE is done like an INSERT if there is no collision on a unique key. Otherwise, an exclusive next-key lock is placed on the row to be replaced.



15.7.4幻影行 Phantom Rows
https://dev.mysql.com/doc/refman/8.0/en/innodb-next-key-locking.html

在一个事务内，同一查询，在不同时间产生的结果不同，就是幻行幽灵行问题。
为了防止在表中插入任何 id大于100的内容，设置的锁 InnoDB包括在id值102之后的间隙上的锁 。



禁用间隙锁定 。这可能会导致幻影问题，因为在禁用间隙锁定时，其他会话可以在间隙中插入新行。
90、102
session1:

start TRANSACTION;
SELECT * FROM child WHERE id > 100 FOR UPDATE;


session2: 无法插入
start TRANSACTION;
insert into child (id)values(105);



session1:

start TRANSACTION;
SELECT * FROM child WHERE id > 100;

session2 commit 以后:前后查询的结果一样，查不到新数据
SELECT * FROM child WHERE id > 100;

session2: 可以插入
start TRANSACTION;
insert into child (id)values(105);
commit;




**15.7.5 InnoDB中的死锁**

https://dev.mysql.com/doc/refman/8.0/en/innodb-deadlocks.html

15.7.5.1 InnoDB死锁示例
15.7.5.2死锁检测
15.7.5.3如何最小化和处理死锁

死锁是指由每个事务都持有对方需要的锁而无法进行其他事务的情况。
因为两个事务都在等待资源变为可用，所以两个都不会释放它持有的锁。

当事务锁定多个表的行 但是相反的顺序锁定时，可能会发生死锁。当此类语句锁定索引记录
和间隙范围时，由于时序问题，每个事务都获得了一些锁而没有获得其他锁，也会发生死锁。

为了减少死锁的可能性，请使用事务而不是lock tables语句；保持插入或者更新数据的事务足够小，
以使长时间不保持打开状态；当不同的事务更新多个表或大范文的行时，请
select for update在每个事务中使用相同的操作顺序
死锁的坑那些不受隔离级别影响，因为隔离级别更改了读取操作的行为，而死锁则是由于写入操作而发生的。

启用死锁检测并且发生死锁后，innodb检查条件并回滚事务一方。 innodb_deallock_detect禁用死锁，
则在死锁情况下 死锁依靠innodb lock wait_time设置回滚事务。
因此，即使您的应用程序逻辑正确，您仍然必须处理充实事务的情况。要查看innodb事务中最后一个死锁，
使用 show engine innodb status.



**15.7.5.1 InnoDB死锁示例**

事务1有锁定共享锁for share; 事务2有锁定排它锁在等待事务1结束；事务1拿着锁定共享锁，获取锁定排他锁，这时候有死锁了。




CREATE TABLE t (i INT) ENGINE = InnoDB;


 INSERT INTO t (i) VALUES(1);

session 1:

START TRANSACTION;
SELECT * FROM t WHERE i = 1 FOR SHARE;

session2 执行以后执行
DELETE FROM t WHERE i = 1;


session 2:
START TRANSACTION;
DELETE FROM t WHERE i = 1;  -- 会阻塞等待事务1 



ERROR 1213 (40001): Deadlock found when trying to get lock;try restarting transaction



**15.7.5.2死锁检测**
当死锁检测被使用(默认开启) innodb自动检测事务的死锁和回滚事务或交易打破僵局。
InnoDB尝试选择要回滚的小事务，其中事务的大小由插入、更新或者删除行确定。
innodb标所 innodb_table_locks=1 和autocommit=0 (不自动提交)并且它上面的mysql层知道行级锁。
否则,innodb无法检测死锁，该死锁时由mysql lock tables语句设置的表锁或由
存储引擎设置的锁innodb涉及的锁。

1.回顾选择小事务(插入、更新、删除的行数确定)


**15.7.5.3如何最小化和处理死锁**
有关死锁的概念性信息为基础。它说明了如何组织数据库操作以最大程度地减少死锁和应用程序中所需
的后续错误处理。
死锁时事务数据库中的经典问题，但是除非死锁如此频繁以至于您根本无法运行某些事务，否则它们并不
危险。通常，您必须编写应用程序，以便在由于死锁回滚事务时，它们始终准备重新发出事务。

Innodb使用自动行级锁定。即使在仅插入或删除单行的事务中，您也可能会陷入僵局。这是因为
这些操作并不是真正的原子操作；它们会自动对插入或删除的行得索引记录设置锁定。

您可以使用以下技术来处理死锁并减少发生死锁的可能性。
+ 在任何时候，发出 show engine innodb status;命令以确定最近死锁的原因。
这可以帮助您调整应用程序以避免死锁。

+ 如果频繁出现死锁警告引起关注，请通过启用innodb_print_all_deadlocks配置选项来手机更广泛的调试信息。
有关每个死锁的信息，而不仅是最新的死锁，都记录在mysql错误日志中。完成调试后，请禁用此选项。
  
+ 如果由于死锁而失败，请始终准备重新发出事务。死锁并不危险。请再试一次。

+ 保持交易小巧且持续时间短，以使交易不易发生冲突。

+ 进行一些列相关更改后立即提交事务，以减少冲突的发生。特别是，不要长时间未提交事务而使交互
式mysql回话保持打开状态。
  
+ 如果您使用锁定读取 for update for share 请尝试使用较低的隔离级别，例如read committed.

+ 修改事务中的多个表或同一表中得不同行集时，每次都要以一直的顺序执行这些操作。然后，
事务形成良好的队列，并不会死锁。

+ 将精选的索引添加到表中。然后您的查询需要扫描更少的索引记录，并因此设置更少的锁。
使用explain select 确定那些索引mysql认为最合适。

+ 使用更少的锁定。如果负担的起，允许select 读取一个旧快照返回数据，不要加for update share.
  使用read committed时不错的，因为同一事务中每个一致性读取均从自己新快照读取。
  
+ 如果没有其他帮助，请使用表级锁序列化事务。lock tables与食物表一起使用的正确方法时
set autocommit=0 (start transaction)后 开始事务,直到明确事务后才lock tables 调用 unlock tables.
  SET autocommit=0;
  LOCK TABLES t1 WRITE, t2 READ, ...;
  ... do something with tables t1 and t2 here ...
  COMMIT;
  UNLOCK TABLES;

表级锁可以防止对表的并发更新，从而避免死锁，但代价时对繁忙系统的响应速度较慢。

+ 序列化事务的另一种方法时创建一个仅包含一行的辅助信号量表。在访问其他表之前，让每个事务更新该行。
这样，所有交易都已串行方式发生。请注意，innodb即使死锁检测算法在这种情况下也适用，
  因为序列化锁时行级锁。对于mysql表级锁，必须适用超时方法来解决死锁。



**15.7.6事务调度**
InnoDB使用竞争感知事务调度cats 算法对等待锁的事务进行优先级排序。
当多个事务正在等待同一对象上的锁时，CATS算法将确定那个事务首先接收到该锁。

CATS算法通过分配调用权重来确定等待的事务的优先级，调度权是基于事务阻止的事务数来计算的。
例如，如果两个事务正在等待对同一对象的锁定，则会为阻塞最多事务的事务
分配更大的调度权重。如果权重相等，则优先考虑等待时间最长的事务。

笔记
在mysql8之前，innodb还使用先进先出FIFO 算法来调度事务，并且cats算法仅在重锁用下使用。
mysql8中得cats算法增强功能使FIFO算法变得多余，从而可以删除它。
以前由FIFO算法执行的事务调度由cats算法执行。在某些情况下，此更改可能会影响授予事务的锁的顺序。

您可以通过查询表中trx_schedule_weight列 来查看事务调度权重 information_scahema.innodb_trx.
权重仅针对等待的交易进行计算。等待的事务时处于lock wait事务执行状态的事务，
如该trx_state列所报告。不等待锁的事务将报告null  trx_schedule_weight


Innodb_metrics提供计数器用于监视代码级事务调度事件。

+ lock_rec_release_attempts
尝试释放记录锁次数，一次尝试可能导致释放0个多个记录锁，因为单个结构中可能存在0个多个记录。
  
+ lock_rec_grant_attempts
授予记录锁定的尝试次数。一次尝试可能导致授予0个多个记录锁。
  
+ lock_schedule_refreashes
分析等待图标以更新计划的交易权重的次数。






**15.8 InnoDB配置**
15.8.1 InnoDB启动配置
15.8.2将InnoDB配置为只读操作
15.8.3 InnoDB缓冲池配置
15.8.4为InnoDB配置线程并发
15.8.5配置后台InnoDB I / O线程数
15.8.6在Linux上使用异步I / O
15.8.7配置InnoDB I / O容量
15.8.8配置自旋锁定轮询
15.8.9清除配置
15.8.10为InnoDB配置优化器统计信息
15.8.11配置索引页的合并阈值
15.8.12为专用的MySQL服务器启用自动配置
https://dev.mysql.com/doc/refman/8.0/en/innodb-configuration.html


https://dev.mysql.com/doc/refman/8.0/en/innodb-configuring-io-capacity.html


15.8.8配置自旋锁定轮询
Innodb互斥锁和rw锁通常保留较短的时间间隔。在多核系统系统上，线程在睡眠之前的一段时间内连续检测它
是否可以获取互斥或rw锁可能会更高效。
如果在此期间互斥锁或rw-lock可用，则线程可以在同一时间片中立即继续。
但是通过多个线程对共享对象例如互斥锁或rw-lock进行太频繁的轮训会导致高速缓存乒乓，
这导致处理器彼此的高速缓存部分无效。innodb通过强制轮训之间的随机延迟使轮训活动不同步，
可以最大程序地减少此问题。随机延迟被实现为旋转等待循环。



**15.8.9清除配置**
https://dev.mysql.com/doc/refman/8.0/en/innodb-purge-configuration.html
InnoDB使用SQL语句删除行时，不会立即从数据库中物理删除行。仅当innodb丢弃为删除而编写的撤销日志记录时，
才物理删除行以及索引记录。这种删除操作仅在不再需要多版本并发控制MVCC或回滚行之后发生，称为清除。

清除会定期执行。它从历史记录列表中解析和处理撤销日志页面，该历史记录列表时由innodb事务系统维护的已提交事务的撤销日志
页面的列表。清除后，将从历史记录列表中释放撤销日志页面。

**配置清除线程**
清除操作由一个或者多个清除线程在后台执行。清除线程的数量由innodb_purge_threads变量控制。
缺省值为4.如果DML数据操作语言 操作分散在许多表中，请增加设置。清除线程的最大数量为32.

innodb_purge_threads设置时允许的最大清除线程数。吹扫系统会根据需要自动调整吹扫线程的数量。



**配置清除批次大小**
purge batch变量定义从历史记录列表中批量清除解析和处理的撤销日志页面数量。默认时300.在一个多线程
配置中，协调器精华线程分割

清除系统还胡释放不再需要的撤销日志页面。它会在撤销日志中每128此迭代执行一次。






**15.9 InnoDB表和页面压缩**
https://dev.mysql.com/doc/refman/8.0/en/innodb-compression.html

**row 格式**
行格式：InnoDB表 的行格式决定了其行在磁盘上的物理存储方式

InnoDB支持四种行格式：ROW_FORMAT=4
REDUNDANT(冗余)，COMPACT(紧凑)， DYNAMIC（动态-默认），COMPRESSED(压缩)
冗余，紧凑，动态，压缩 


![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/LZ77压缩.png)


![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/LZ77解压.png)

**15.9.1.1表压缩概述** 较小的CPU利用率为代价换取IO吞吐量的提升。
Because processors and cache memories have increased in speed more than disk storage devices
CPU和高速缓存速度比磁盘速度高很多，系统瓶颈在磁盘IO上。数据压缩以较小的CPU利用率增加为代价，实现了较小的数据库大小，减少的I / O和改进的吞吐量。
在具有足够RAM以便将经常使用的数据保留在内存中的系统上，压缩对于读取密集型应用程序特别有价值。







**15.9.1.3调整InnoDB表的压缩**
大多数情况下，innodb数据存储和压缩中描述的内部优化可确保系统在压缩数据下正常允许。但是，由于
压缩效率取决于数据的性质，因此您可以做出影响压缩包性能的决定。
+ 压缩那些表
+ 要使用压缩页面大小
+ 释放根据允许时性能特征(系统话费在压缩和解压缩数据上的时间)来调整缓冲池大小。
工作负载更像是数据仓库(主要是查询)还是OLTP系统(查询和DML的混合)
+ 如果系统对压缩表执行DML 操作，并且数据的分发方式导致运行时代价高昂的压缩失败，则可以调整其他高级配置。

使用本节中得准则可帮助做出那些体系结构和配置选择。当您准备进行长期测试并将压缩包投入生产时，
以了解在现实条件下雅正这些选择的有效性的方法。


**何时使用压缩**
通常，压缩在包含合理数量的字符串列且数据读取的频率比写入频率高的表上最有效。
因为没有保证可以预测压缩释放在特定情况下有用的方法，所以请始终对在代表性配置上运行
的特定工作负载和数据集进行测试。在决定压缩那些表时，请考虑以下因素。


**数据特征与压缩**
减少数据文件大小时压缩效率的关键因素时数据本身的性质。回想以下，压缩时通过识别数据库中重复字符串来实现的。
完全随机的数据时最坏的情况。典型的数据通常具有重复值，因此可以有效压缩。字符串经常压缩的很好，
在是否定义char,varchar,text,blob列.另一方面，大多数包含二进制数据或先前已经压缩的数据表
通常可能无法很好地压缩或完全压缩。

您选择是否为买个innodb表打开压缩。一个表以及所有索引都使用相同压缩的页面大小。包含表的所有列
的数据的主键聚集索引可能比辅助索引更有效地压缩。对于长行得情况，如dynamic 行格式中所述，
使用压缩可能导致长列值页外存储。这些一处页面可能压缩的很好。考虑到这些因素，对于许多应用
程序而言，某些表的压缩比其他程序更有效，并且您可能会发现工作负载仅在压缩了一部分表的情况下才
表现最佳。

要确定是否压缩特定表，请进行实验。您可以使用在未压缩表gzip的.ibd文件副本上实施LZ77压缩的使用程序来
粗略估计数据的压缩效率。与基于文件的压缩工具相比，mysql亚索表的压缩量可能会更少，
因为mysql会根据页面大小一块的形式压缩数据，默认16KB.除用户数据外，页面格式还包括一些未压缩的内部
系统数据。基于文件的压缩使用程序可以检查更大的数据库，因此在大型文件中发现的重复字符串可能比
mysql在单个页面中找到的字符串还要多。

测试特定表压缩的另一种方法时将每个表文件空间中得一些数据从未压缩表复制到类似的亚索表(具有相同的索引)中，
并查看生成的.ibd文件大小。




**15.9.1.5 InnoDB表的压缩方式**

**压缩算法:zlib库实现了LZ77压缩算法**
这种压缩算法在CPU利用率和数据大小减小方面都是成熟，健壮和高效的。
该算法是 “无损的”，因此原始的未压缩数据始终可以从压缩形式中重建。
LZ77压缩通过查找在要压缩的数据内重复的数据序列来工作。数据中的值模式决定了压缩的程度，但是典型的用户数据通常压缩50％或更多。

![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/LZ77压缩.png)


![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/LZ77解压.png)


**InnoDB数据存储和压缩**
+ 聚簇索引
+ 二级索引
+ 溢出页(VARCHAR，BLOB或TEXT)
+ InnoDB缓冲池压缩
+ InnoDB重做日志文件压缩


**II.B树页面的压缩** 大概意思是被分割压缩

由于B树页面经常更新，因此需要特殊对待。重要的是，最小化B树节点的分割次数，以及最小化解压缩和重新压缩其内容的需求。

MySQL使用的一种技术是以未压缩的形式维护B树节点中的某些系统信息，从而促进某些就地更新。例如，这允许对行进行删除标记和删除，而无需任何压缩操作。

另外，MySQL试图避免在更改索引页时不必要的解压缩和重新压缩。在每个B树页面中，系统保留一个未压缩的 “修改日志”以记录对该页面所做的更改。小记录的更新和插入可以写入此修改日志，而无需完全重建整个页面。

当修改日志的空间用完时，InnoDB解压缩页面，应用更改并重新压缩页面。如果再压缩失败（称为一个的情况 压缩破坏），B-树节点被分割，并重复该过程，直到更新或插入成功。

**II.溢出页(VARCHAR，BLOB或TEXT)**
在一个InnoDB表，BLOB， VARCHAR，和 TEXT列不属于主键的一部分可以被存储在单独分配 溢出页。我们将这些列称为 页外列。它们的值存储在溢出页面的单链接列表中。


**II.InnoDB缓冲池压缩** 相当于有时存两份  压缩的和没压缩的
为了最大程度地减少I / O并减少解压缩页面的需求，缓冲池有时会同时包含数据库页面的压缩形式和未压缩形式。
为了为其他必需的数据库页面腾出空间，MySQL可以 从缓冲池中逐出未压缩的页面，而将压缩后的页面保留在内存中
。或者，如果一段时间未访问页面，则页面的压缩形式可能会写入磁盘，以释放其他数据的空间。
因此，在任何给定时间，缓冲池可能包含页面的压缩形式和未压缩形式，或者仅包含页面的压缩形式，或者都不包含。

MySQL使用最近使用最少的（LRU）列表来跟踪哪些页面保留在内存中以及哪些页面被逐出，以使 热（频繁访问）的数据倾向于保留在内存中。当访问压缩表时，MySQL使用自适应LRU算法来实现内存中已压缩和未压缩页面的适当平衡。此自适应算法对系统是在I / O绑定还是在 CPU绑定下运行非常敏感方式。目的是避免在CPU繁忙时花费过多的处理时间来解压缩页面，并避免在CPU拥有可用于解压缩压缩页面（可能已经在内存中）的空闲周期时执行过多的I / O。当系统受I / O限制时，该算法更喜欢逐出页面的未压缩副本，而不是两个副本，以腾出更多空间让其他磁盘页面驻留在内存中。当系统受CPU限制时，MySQL倾向于退出压缩和未压缩页面，以便更多的内存可用于“热”页面，并减少仅以压缩形式解压缩内存中数据的需求。



**II.InnoDB重做日志文件压缩**
在将压缩页面写入 数据文件之前，MySQL将页面的副本写入重做日志（如果自上次写入数据库以来已对其进行了重新压缩）。这样做是为了确保重做日志可用于 崩溃恢复，即使在极少数情况下zlib库已升级且更改引起压缩数据的兼容性问题也是如此。
因此，日志文件的大小有所增加 ，或者需要更频繁的 检查点，在使用压缩时可以预期。日志文件大小或检查点频率的增加量取决于以需要重组和重新压缩的方式修改压缩页面的次数。




**15.9.2 InnoDB Page Compression页压缩** 透明页面压缩
https://dev.mysql.com/doc/refman/8.0/en/innodb-page-compression.html
针对每个表的.idb文件进行压缩， 操作系统文件级别的压缩。

支持的压缩算法包括Zlib和 LZ4。







**15.10 InnoDB行格式 (InnoDB Row Formats)**
表的行格式决定了其行得物理存储方式，进而会影响查询和DML操作的性能。随着更多的行适合
单个磁盘页面，查询和索引查询可以更快地工作，缓冲池中需要的缓存内存更少，以及写出
更新值所需的IO更少。

每个表中的数据分为几页。构成每个表的页面以称为B树索引的树数据结构排列。表数据和二级索引
都使用这种类型的结构。代表整个表的B树索引称为聚簇索引，该聚簇索引时根据
主键列进行阻止的。聚簇索引数据结构的节点包含该行中所有列的值。二级索引结构的节点
包含索引列和主键列的值。

可变长度列时将列值存储在B树索引节点中得规则的例外。太长而无法容纳在B树页面上的可变长度列存储
在单独分配的磁盘页面上，这些磁盘页面称为溢出页面。这样的列称为页外列。
页面外列的值存储在溢出页面的单链接列表中，每个这样的列都有其自己的一个或多个溢出页面的列表。
根据列的长度，所有或可变长度列值的前缀都存储在B树种，以避免浪费
存储空间并不得不读取单独的页面。

该innodb存储银行支持四种格式:
redundant 冗余剪裁的、 compact (紧凑)、dynamic（动态-默认）、compressed(压缩)



**redundant(冗余格式)**
redundant格式提供了与旧版本mysql的兼容性。
使用redundant行格式的表将前768个字节的变长列值varchar、varbinary、blob、text类型存储在B树
节点内的索引记录中，其余的存储在溢出页上。大于或等于768字节的固定长度列被编码为可变长度列，
可以在页面外存储。例如char255如果字符集的最大字节长度大于3，则该列可以超过768个字节utf8mb4.

如果列的值小于或等于768个字节，则不使用溢出页，并且由于该值完全存储在B树节点中，因此
可能会节省一些IO。这对于相对较短的BLOB列值很好用，但是可能导致B树节点填充
数据而不是键值，从而降低了效率。具有许多BLOB列的表可能会导致B树节点变得太慢，并且包含的行太少，
从而使整个索引的效率低于行较短或列值存储在页面外的情况。

redundant冗余行格式存储特征
+ 每个索引记录包含一个6字节的标头。标头用于将连续的记录链接在一起，并用于行级锁定。
+ 聚集索引中的记录包含所有用户定义列的字段。此外，还有一个6字节的事务ID字段和一个7字节的滚动指针字段。
+ 如果未为表定义主键，则每个聚集索引记录还包含一个6字节的行ID字段。
+ 每个辅助索引记录都包含为聚集索引键定义的所有不在辅助索引中的主键列。
+ 一条记录包含一个指向该记录的每个字段的指针。如果记录中字段的总长度小于128个字节，则指针为1个字节；否则，指针为1个字节。否则为两个字节。指针数组称为记录目录。指针指向的区域是记录的数据部分。
+ 在内部，固定长度的字符列（例如CHAR(10)以固定长度格式存储的字符列） 。尾随空格不会从VARCHAR列中截断 。
+ 大于或等于768字节的固定长度列被编码为可变长度列，可以在页面外存储。例如，CHAR(255)如果字符集的最大字节长度大于3，则该列可以超过768个字节（与相同） utf8mb4。
+ 一个SQLNULL值在记录目录中保留一个或两个字节。NULL如果存储在可变长度列中，则SQL值在记录的数据部分中保留零字节。对于固定长度的列，该列的固定长度保留在记录的数据部分中。保留NULL 值的固定空间可将列从原位置更新 NULL为非NULL值，而不会导致索引页碎片。


**compact (紧凑)行格式**
比redundant少20%存储空间，代价时增加某些操作的CPU使用率。如果您的工作量时典型的工作，并且
受高速缓存命中率和磁盘速度的限制，那么compact格式可能会更快。如果工作量受到cpu速度限制，则
紧凑格式可能会更慢。

使用COMPACT行格式的表将前768个字节的变长列值（VARCHAR， VARBINARY和 BLOB和 TEXT类型）存储在B树节点内的索引记录中，其余的存储在溢出页上。大于或等于768字节的固定长度列被编码为可变长度列，可以在页面外存储。例如， CHAR(255)如果字符集的最大字节长度大于3，则该列可以超过768个字节（与相同）utf8mb4。

如果列的值小于或等于768个字节，则不使用溢出页，并且由于该值完全存储在B树节点中，因此可能会节省一些I / O。这对于相对较短的BLOB列值很好用，但是可能导致B树节点填充数据而不是键值，从而降低了效率。具有许多BLOB列的表可能会导致B树节点变得太满，并且包含的​​行太少，从而使整个索引的效率低于行较短或列值存储在页面外的情况。


**COMPACT行格式存储特征**


+ 每个索引记录都包含一个5字节的标头，该标头之前可以有一个可变长度的标头。标头用于将连续的记录链接在一起，并用于行级锁定。

+ 记录头的可变长度部分包含用于指示NULL列的位向量。如果索引中可以为的列数 NULL为N，则位向量占用 字节。（例如，如果有9到16列可以是，则位向量使用两个字节。）除此向量中的位以外，不占用其他列的列。标头的可变长度部分还包含可变长度列的长度。每个长度占用一个或两个字节，具体取决于列的最大长度。如果索引中的所有列都是CEILING(N/8)NULLNULLNOT NULL 并且具有固定的长度，记录头没有可变长度的部分。

+ 对于每个非NULL可变长度字段，记录头均以一或两个字节的形式包含列的长度。仅当列的一部分存储在外部溢出页面中或最大长度超过255个字节且实际长度超过127个字节时，才需要两个字节。对于外部存储的列，2字节的长度表示内部存储部分的长度加上指向外部存储部分的20字节指针。内部部分是768个字节，因此长度是768 + 20。20字节的指针存储列的真实长度。

+ 记录头后跟非NULL列的数据内容。

+ 聚集索引中的记录包含所有用户定义列的字段。此外，还有一个6字节的事务ID字段和一个7字节的滚动指针字段。

+ 如果未为表定义主键，则每个聚集索引记录还包含一个6字节的行ID字段。

+ 每个辅助索引记录都包含为聚集索引键定义的所有不在辅助索引中的主键列。如果任何主键列的长度是可变的，则即使在固定长度列上定义了辅助索引，每个辅助索引的记录头都具有可变长度部分来记录其长度。

+ 在内部，对于非可变长度字符集，固定长度字符列（例如）以 CHAR(10)固定长度格式存储。
尾随空格不会从VARCHAR列中截断 。

+  在内部，对于可变长度字符集（例如 utf8mb3和）utf8mb4， InnoDB尝试通过修剪尾随空格来 以字节为单位进行存储 。如果 列值的字节长度 超过字节，则尾随空格将被修剪为列值字节长度的最小值。 列的最大长度 是最大字符字节长度× 。 CHAR(N)NCHAR(N)NCHAR(N)N

+  N保留了 最少的字节数 。在许多情况下，保留最小空间可使列更新就位，而不会导致索引页碎片。相比之下，使用行格式时 ， 列占据最大字符字节长度× 。 CHAR(N)NCHAR(N)NREDUNDANT

+  大于或等于768字节的固定长度列被编码为可变长度字段，可以在页面外存储。例如，CHAR(255)如果字符集的最大字节长度大于3，则该列可以超过768个字节（与相同） utf8mb4。




**DYNAMIC 行格式**
该DYNAMIC行格式提供相同的存储特性的COMPACT行格式，但增加了增强的长期可变长度列和支持大型索引键前缀的存储能力。

使用创建表时 ROW_FORMAT=DYNAMIC，InnoDB 可以完全在页外存储长的可变长度列值（针对 VARCHAR， VARBINARY和 BLOB和 TEXT类型），而聚集索引记录仅包含指向溢出页的20字节指针。大于或等于768字节的固定长度字段被编码为可变长度字段。例如， CHAR(255)如果字符集的最大字节长度大于3，则该列可以超过768个字节（与相同）utf8mb4。

列是否存储在页面外取决于页面大小和行的总大小。当一行太长时，将选择最长的列进行页外存储，直到聚簇索引记录适合B树页面为止。 TEXT并且 BLOB是小于或等于40个字节的列被存储在线路。

如果DYNAMIC行格式适合，行格式可以保持将整个行存储在索引节点中的效率（ COMPACT和和REDUNDANT 格式一样），但是DYNAMIC行格式避免了用大量长列数据字节填充B树节点的问题。该DYNAMIC行格式是基于这样的思想，如果一个长的数据值的一部分被存储关闭页，它通常是最有效的存储关闭页整个值。使用DYNAMICformat时，较短的列可能会保留在B树节点中，从而最大程度地减少了给定行所需的溢出页数。

该DYNAMIC行的格式支持索引键的前缀可达3072个字节。

使用DYNAMIC行格式的表可以存储在系统表空间，每表文件表空间和常规表空间中。要将DYNAMIC表存储在系统表空间中，请禁用 innodb_file_per_table并使用常规CREATE TABLE或ALTER TABLE语句，或者将TABLESPACE [=] innodb_system表选项与CREATE TABLE或一起使用ALTER TABLE。该 innodb_file_per_table变量不适用于常规表空间，也不适用于使用TABLESPACE [=] innodb_systemtable选项将DYNAMIC表存储在系统表空间中的情况。


**COMPRESSED压缩行格式**


该COMPRESSED行格式提供相同的存储特性和功能的 DYNAMIC行格式，但增加了对表和索引数据压缩的支持。

该COMPRESSED行格式使用类似的内部细节关闭页存储为DYNAMIC行格式，从表和索引数据的附加存储和性能的考虑被压缩，并使用较小的页大小。使用COMPRESSED行格式时，该 KEY_BLOCK_SIZE选项控制在聚集索引中存储多少列数据，以及在溢出页面上放置多少列数据。有关COMPRESSED行格式的更多信息 ，请参见 第15.9节“ InnoDB表和页面压缩”。

该COMPRESSED行的格式支持索引键的前缀可达3072个字节。

COMPRESSED可以在每表文件表空间或常规表空间中创建 使用行格式的表。系统表空间不支持 COMPRESSED行格式。要将COMPRESSED表存储 在每表文件表空间中，innodb_file_per_table必须启用该 变量。该 innodb_file_per_table变量不适用于常规表空间。常规表空间支持所有行格式，但要注意的是，由于物理页大小不同，压缩表和未压缩表不能在同一常规表空间中共存

压缩行格式存储特征
该COMPRESSED行格式是一个偏差 COMPACT行格式。有关存储特征，




**15.11 InnoDB磁盘I / O和文件空间管理**

https://dev.mysql.com/doc/refman/8.0/en/innodb-disk-management.html
















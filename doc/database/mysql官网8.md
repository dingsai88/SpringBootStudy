


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













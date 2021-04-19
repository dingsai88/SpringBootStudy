


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

InnoDB支持四种行格式：
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













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


II.优化SQL语句
优化查询的主要考虑因素：
1.是否加索引; EXPLAIN分析语句
2.
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




















 
 
 
 
 
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


--------------------------------------------------------------------------

 ziplist压缩双向链表（数组形式存储内存连续）：
 总字节数+最后一位偏移数(直接定位尾)+数据总个数+(数据+数据)+zlend(最后一位固定255)
 zlbytes
 zltail
 zllen
 entry+entry
 zlend
 
 
 
 
 
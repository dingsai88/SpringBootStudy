镜像下载地址:
https://elasticsearch.cn/download/


**为什么要学Elasticsearch**




Kibana:可视化
Elasticsearch:数据搜索、分析和存储
Beats、Logstash：数据采集


主要功能:
分布式搜索引擎
大数据近实时分析引擎


产品特性:
高性能,和T+1说不
容易使用、容易拓展


学习目标:
开发:产品基本功能，底层工作原理，数据建模最佳实践
运维:容量规划；性能优化；问题诊断；滚动升级
方案:搜索与如何解决搜索的相关性问题；大数据分析实践与项目实战，理论知识运用到实际场景。



特色覆盖elastic 认证全部知识考点。


**I.课程内容 与结构**

II.Elasticsearch 如何与深入
环境搭建、搜索与聚合、架构原理、数据建模


II.Elasticsearch 集群管理
水平拓展以及性能优化、最佳实践

II.ELK 进行大数据分析
可视化分析、时序型数据、异常检测

II.项目实战和知识点回顾
电影搜索、文件分析、Elastic认证



**Elastic生态圈**

![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/Elasticsearch_1ShengTai.png)










**I.elasticsearch安装和启动**
https://github.com/geektime-geekbang/geektime-ELK

历史版本
https://www.elastic.co/cn/downloads/past-releases/

https://www.elastic.co/cn/downloads/past-releases#elasticsearch
https://www.elastic.co/cn/downloads/past-releases/elasticsearch-6-8-14

https://www.elastic.co/cn/downloads/past-releases/kibana-6-8-14

F:\DingSaiElastic\Elasticsearch\elasticsearch-6.8.14\bin
elasticsearch.bat

https://www.elastic.co/cn/support/matrix
https://www.elastic.co/cn/downloads/elasticsearch

http://localhost:9200




问题1:
ElasticsearchException[X-Pack is not supported and Machine Learning is not a

解决：在config/elasticsearch.yml添加一条配置
xpack.ml.enabled: false


**I.kibana安装**

F:\DingSaiElastic\kibana\kibana-6.8.14-windows-x86_64\bin

kibana.bat


http://localhost:5601

**开箱即用-导入数据**


**dev-tools工具**

GET /_cat/nodes

运行的节点
GET /_cat/nodes?v



**I.Logstash安装**

F:\DingSaiElastic\Logstash\logstash-6.8.14\bin
F:\DingSaiElastic\Logstash\logstash-6.8.14\bin>logstash -f F:\DingSaiElastic\Logstash\logstash-6.8.14\data\logstash.conf

logstash -f F:\DingSaiElastic\Logstash\logstash-6.8.14\data\logstash.conf


修改配置后启动 路径斜杠要注意
file {
path => "F:/DingSaiElastic/Logstash/logstash-6.8.14/data/movies.csv"
start_position => "beginning"
}



数据最后输出到 Elasticsearch



问题1：卡在这里没动静
Successfully started Logstash API endpoint {:port=>9600}

    path => "F:/DingSaiElastic/Logstash/logstash-6.8.14/data/movies.csv"
    start_position => "beginning"


https://blog.csdn.net/LJFPHP/article/details/89340807



---------------------------------------------------------------------------------------------------------------
**Elasticsearch入门**
基本概念(1)- 索引，文档和 REST API


**I.文档 document**

+ elasticsearch是面向文档的，文档是所有可搜索数据的最小单位
  + 日志文件中的日志项
  + 一本电影的具体信息
  + MP3播放器歌

+ 文档会被序列号成json格式，保存在Elasticsearch中
  + Json 对象由字段组成
  + 每个字段都有对应的字段类型(字符串、布尔、数值、日期、二进制、范围类型)


+ 每个文档都有一个Unique ID
  + 你可以自己指定ID
  + 通过elasticsearch自动生成



**I.Json文档**

+ 一篇文档包含了一系列的字段。类似数据库表中的一条记录
+ JSON文档，格式灵活，不需要预先定义格式
   + 字段的类型可以指定或者通过Elasticsearch自动推算
   + 支持数组/支持嵌套
    


**I.文档的元数据**

+ 元数据，用于标注文档的相关信息
  + _index 文档所属的索引名
  + _type 文档所属的类型名
  + _id  文档唯一ID
  + _source  文档原始json数据
  + _all  整合所有字段内容到该字段，已被删除
  + _version 文档的版本信息
  + _score 相关性打分



**I.索引**
Index 索引是文档的容器，是一类文档的结合


索引在不同地方意思不一样，可以是 类似表、可以是B树或者倒排、数据录入到elasticsearch过程也叫索引


**I.mysql和Elasticsearch对比**

表table== index(Type)索引
行数据Row==Document文档
字段Column==Filed
Schema(DataBase数据库)==Mapping


对外提供restAPi 容易被调用













镜像下载地址:
https://elasticsearch.cn/download/

**I.mysql和Elasticsearch对比**

表table== index(Type)索引
行数据Row==Document文档
字段Column==Filed
Schema(DataBase数据库)==Mapping


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

**II.elasticsearch启动**
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

---------------------------------------基本概念：节点、集群、分片及副本-----------------------------------------------------------------











---------------------------------------常用命令--------------------------------------------
增删改查
GET users/_doc/1

###  Index & Update
#Update 指定 ID  (先删除，在写入)  两次调用版本号会增加
PUT users/_doc/1
{
"user" : "Mike"

}

#在原文档上增加字段
POST users/_update/1/
{
"doc":{
"post_date" : "2019-05-15T14:12:12",
"message" : "trying out Elasticsearch"
}
}

**I.常用命令**


# 列出所有节点
GET /_cat/nodes?v
http://localhost:9200/_cat/nodes?v

#列出所有索引 所有表
GET /_cat/indices?v
http://localhost:9200/_cat/indices?v


#创建索引  都要是小写，大写会报错
PUT /customer?pretty


############Create Document############
#create document. 自动生成 _id
POST users/_doc
{
"user" : "Mike1",
"post_date" : "2019-04-15T14:12:12",
"message" : "trying out Kibana"
}


#create document. 指定Id。如果id已经存在，报错
PUT users/_doc/1?op_type=create
{
"user" : "Jack",
"post_date" : "2019-05-15T14:12:12",
"message" : "trying out Elasticsearch"
}


GET users/_doc/1

#create document. 指定 ID 如果已经存在，就报错
#第一次请求会成功、第二次会报错
PUT users/_create/1
{
"user" : "Jack",
"post_date" : "2019-05-15T14:12:12",
"message" : "trying out Elasticsearch"
}

### Get Document by ID
#Get the document by ID
GET users/_doc/1


###  Index & Update
#Update 指定 ID  (先删除，在写入)
GET users/_doc/1

PUT users/_doc/1
{
"user" : "Mike"

}


GET users/_doc/1


#在原文档上增加字段 原来只有 user:mike ---不能用
POST users/_update/1/
{
"doc":{
"post_date" : "2019-05-15T14:12:12",
"message" : "trying out Elasticsearch",
"age" : 1
}
}

#在原文档上增加字段 原来只有 user:mike 可以用
POST users/_doc/1/_update?pretty
{
"doc":{
"post_date" : "2019-05-15T14:12:12",
"message" : "trying out Elasticsearch",
"age" : 1
}
}

#在原文档上增加字段 把原有age+=5;
POST users/_doc/1/_update?pretty
{
"script" : "ctx._source.age += 5"
}




### Delete by Id
# 删除文档
DELETE users/_doc/1




**Bulk API 一次网络请求多次API操作**  都没使出来


一次网络请求多次索引操作
只支持四种操作
Index、create、Update、Delete

### mget批量读取

GET /_mget
{
"docs" : [
{
"_index" : "test",
"_id" : "1",
"_source" : false
},
{
"_index" : "test",
"_id" : "2",
"_source" : ["field3", "field4"]
},
{
"_index" : "test",
"_id" : "3",
"_source" : {
"include": ["user"],
"exclude": ["user.location"]
}
}
]
}

### msearch 操作  可用
POST kibana_sample_data_ecommerce/_msearch
{}
{"query" : {"match_all" : {}},"size":1}
{"index" : "kibana_sample_data_flights"}
{"query" : {"match_all" : {}},"size":2}


### 清除测试数据
#清除数据
DELETE users
DELETE test
DELETE test2



------------------------倒排索引------------------------------------------------

I.正常目录
正排索引-文档ID 到文档内核和单词的关联 ：图书目录
倒排索引-单词到文档ID



**I.倒排索引的核心组成**

+ 单词词典 Term Dictionary,记录所有文档的单子，记录单词到倒排列表的关联关系
   + 单词词典比较大，可以通过B+数或哈希拉链法实现，以满足高性能的插入与查询
  
+ 倒排列表 Posting List 记录了单词对应的文档结合，由倒排索引项组成
   + 倒排索引项 Posting
      + 文档ID 
      + 词频TF - 该单词在文档中出现的次数，用于相关性评分
      +  位置Position 单词在文档中分词的位置。 用于语句搜素 phrase query
      +  偏移 Offset 记录单词的开始结束位置，实现高亮显示

POST _analyze
{
"analyzer": "standard",
"text": "Mastering Elasticsearch"
}



https://github.com/dingsai88/geektime-ELK/blob/master/part-1/3.4-倒排索引入门/README.md




**Analyzer分析器 进行分词**
https://github.com/dingsai88/geektime-ELK/tree/master/part-1/3.5-通过分析器进行分词
+ Analysis也叫分词，就是将文档转换成一系列单词的过程
+ Elasticsearch内置一些分词器
+ 中文分词器




#Simple Analyzer – 按照非字母切分（符号被过滤），小写处理
#Stop Analyzer – 小写处理，停用词过滤（the，a，is）
#Whitespace Analyzer – 按照空格切分，不转小写
#Keyword Analyzer – 不分词，直接将输入当作输出
#Patter Analyzer – 正则表达式，默认 \W+ (非字符分隔)
#Language – 提供了30多种常见语言的分词器
#2 running Quick brown-foxes leap over lazy dogs in the summer evening


#standard
GET _analyze
{
"analyzer": "standard",
"text": "2 running Quick brown-foxes leap over lazy dogs in the summer evening."
}



**中文分词器**

中文分词难点
+ 中文句子，切分成一个一个词（不是一个个字）
+ 英文中，单词有自然的空格作为分隔
+ 一句中文，在不同的上下文，有不同的理解
   + 这个苹果，不大好吃；  这个苹果，不大，好吃
+ 一些例子
   + 他说的确实在理/ 这事的确定不下来

IK中文分词器

THULAC 清华大学的分词器




--------------------------------查询----------------------------------------
SearchAPI

**+ URI Search**
   GET 方式
**+ Request Body Search**


集群上所有的索引
GET /_search


GET /index1/_search


GET kibana_sample_data_ecommerce/_search?q=customer_first_name:Eddie

使用q,指定查询字符串
搜索叫做Eddie的客户 



#REQUEST Body
POST kibana_sample_data_ecommerce/_search
{
"profile": true,
"query": {
"match_all": {}
}
}


curl -XGET/POST "http://a:9200/kibana_sample_data_ecommerce/_search" -H
'Content-Type:application/json' -d '{"query":{"match_all":{}}}'






{
"took" : 6,    **took花费时间**
"timed_out" : false,
"_shards" : {
"total" : 1,
"successful" : 1,
"skipped" : 0,
"failed" : 0
},
"hits" : {
"total" : 4675,    **total符合条件总数**
"max_score" : 1.0,
"hits" : [     **hits结果集，默认前10个**
{
"_index" : "kibana_sample_data_ecommerce", **索引名**
"_type" : "_doc",
"_id" : "6xgQXngBoai3J6BcSKMn",   **文档ID**
"_score" : 1.0,  **相关度评分**
"_source" : {   **文档原始信息**
"category" : [
"Men's Clothing"
],
"currency" : "EUR",
"customer_first_name" : "Eddie",
"customer_full_name" : "Eddie Underwood",
"customer_gender" : "MALE",
"customer_id" : 38, 



**搜索的相关性 Relevance**

+ 搜索是用户和搜索引擎的对话
+ 用户关心的是搜索结果的相关性
   + 是否可以找到所有相关的内容
   + 有所少不相关的内容被返回了
   + 文档的打分是否合理
   + 结合业务需求，平衡结果查询


**衡量相关性**

Information Retrieval 
+ Precision 查询率-尽可能返回少的无关文档
+ Recall 查全率 -尽量返回较多的相关文档
+ Ranking - 是否能够按照相关度进行排序



**15-URI-Search详解**
https://github.com/dingsai88/geektime-ELK/tree/master/part-1/3.7-URISearch%E8%AF%A6%E8%A7%A3

**指定字段 VS 泛查询**

II.指定字段

#指定字段
GET /movies/_search?q=title:2012&sort=year:desc&from=0&size=10&timeout=1s
{
"profile":"true"
}


#指定字段  只查询title字段里包含2012的数据，返回2行
GET /movies/_search?q=title:2012
{
"profile":"true"  **开启查询分析**
}

"type" : "TermQuery", **使用TermQuery搜索**
"description" : "title:2012",   **只查询title字段里包含2012的数据**

II.泛查询
#泛查询，正对_all,所有字段
GET /movies/_search?q=2012
{
"profile":"true" **开启查询分析**
}

"type" : "DisjunctionMaxQuery",  **使用DisjunctionMaxQuery搜索**
"description" : "(title.keyword:2012 | id.keyword:2012 | year:[2012 TO 2012] | genre:2012 | @version:2012 | @version.keyword:2012 | id:2012 | genre.keyword:2012 | title:2012)",
**使用搜索很多字段2012**



II.  **Term  VS Phrase**  or and 

Term Query 期限搜索       :Beautiful Mind 等于  Beautiful  OR  Mind
Phrase  Query 短语搜索    :"Beautiful Mind" 等于  Beautiful  AND   Mind，还要求顺序要一致

分组与引号
title:(Beautiful AND Mind)
title="Beautiful Mind"



# 查找美丽心灵, Mind为泛查询
GET /movies/_search?q=title:Beautiful Mind
{
"profile":"true"
}

"type" : "BooleanQuery",
"description" : """title:beautiful (title.keyword:Mind | id.keyword:Mind | MatchNoDocsQuery("failed [year] query, caused by number_format_exception:[For input string: "Mind"]") | genre:mind | @version:mind | @version.keyword:Mind | id:mind | genre.keyword:Mind | title:mind)""",
#分组，Bool查询
GET /movies/_search?q=title:(Beautiful Mind)




#使用引号，Phrase查询
GET /movies/_search?q=title:"Beautiful Mind"
{
"profile":"true"
}

"type" : "PhraseQuery",  **两个都出现而且要保证顺序**
"description" : """title:"beautiful mind"""",



II. 布尔操作  AND OR NOT && ||  !
必须大写
title:(matrix  NOT reloaded)

II.分组
+ 表示 must
- 表示 must_not 
title:(+aaa -bbb)

II.范围
区间表示:   [] 闭区间             {}开区间

year:{2019 TO 2018}
year:[* TO 2018]

II.算数符号

year:>2010
year:>2010
year:(>2010&&<=2018)
year:(+>2010 +<=2018)

II.通配符查询 (效率低、占内存大，不建议使用。遵循左前缀，不建议放在最前)

?表示一个字符
*代表0或者多个

title:mi?d
title:be*


II.正则
title:[bt]oy


II.模糊匹配和近似查询

单词拼错了，近似查询
title:befutifl~1


不考虑位置

title:"lord rings"~2

具体语句
https://github.com/dingsai88/geektime-ELK/tree/master/part-1/3.7-URISearch%E8%AF%A6%E8%A7%A3







**16-Request-Body与Query-DSL简介**


https://github.com/dingsai88/geektime-ELK/tree/master/part-1/3.8-RequestBody%E4%B8%8EQueryDSL%E7%AE%80%E4%BB%8B

II.分页

POST /kibana_sample_data_ecommerce/_search
{
"from":10,
"size":20,
"query":{
"match_all": {}
}
}

II. #对日期排序
POST kibana_sample_data_ecommerce/_search
{
"sort":[{"order_date":"desc"}],
"query":{
"match_all": {}
}}

II. #source filtering   针对返回的信息进行过滤
"_source":["order_date"],  只返回 order_date
"_source":["order_id"],

II.脚本字段 
查询全部数据，返回一个新字段 new_field  =order_date+'hello'
#脚本字段  script_fields  
GET kibana_sample_data_ecommerce/_search
{
  "script_fields": {
       "new_field": {
              "script": {
                         "lang": "painless",
                          "source": "doc['order_date'].value+'hello'"
                          }}},
     "query": {
          "match_all": {} 
               }
}


II.  **Term  VS Phrase**  or and 

III. or 
"title": "last christmas"

III. and
"title": {
"query": "last christmas",
"operator": "and"
}

III. 中间可以空字符

"title":{
"query": "one love",
"slop": 1 }






-------------------------------------------------------------------------------------
**17-Query-String&Simple-Query-String查询**
**18-Dynamic-Mapping和常见字段类型**

**查看Mapping文件 表结构类似**

Dynamic Mapping 和常见字段类型


PUT mapping_test/_doc/1
{
"firstName":"Chan",
"lastName": "Jackie",
"loginDate":"2018-07-24T10:29:48.103Z"
}

#查看 Mapping文件 返回各个字段的类型 type=text、date
GET mapping_test/_mapping

GET mapping_test/_doc/1

#Delete index
DELETE mapping_test









表table== index(Type)索引
行数据Row==Document文档
字段Column==Filed
Schema(DataBase数据库)==Mapping


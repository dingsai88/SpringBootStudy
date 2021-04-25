镜像下载地址:
https://elasticsearch.cn/download/

**I.mysql和Elasticsearch对比**

表table== index(Type)索引(录入数据叫索引indexing) :(7版之前index可以多个type，已不建议)
行数据Row==Document文档 (包含元数据:_index、_type、_id、_source、_version、_score)
字段Column==Filed
Schema(DataBase数据库)==Mapping(一个index字段名、数据类型、是否分词和加入倒排索引)
SQL===DSL






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
JDK支持
https://www.elastic.co/cn/support/matrix#matrix_jvm

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




------------------------------------------------------------------------------------
7.11.2安装

ElasticSearch


F:\DingSaiElastic\Elasticsearch\elasticsearch-7.11.2\bin
elasticsearch.bat
 
自带JDK 需要修改配置:
elasticsearch-env.bat  46行附近 新增 set java_home 指定为自带jdk

set JAVA_HOME=F:\DingSaiElastic\Elasticsearch\elasticsearch-7.11.2\jdk
if "%JAVA_HOME%" == "" (
set JAVA="%ES_HOME%\jdk\bin\java.exe"
set "JAVA_HOME=%ES_HOME%\jdk"
set JAVA_TYPE=bundled jdk
) else (
set JAVA="%JAVA_HOME%\bin\java.exe"
set JAVA_TYPE=JAVA_HOME
)


http://localhost:9200


Kibana:


F:\DingSaiElastic\kibana\kibana-7.11.2-windows-x86_64\bin

 

kibana.bat


http://localhost:5601


F:\DingSaiElastic\kibana\kibana-7.11.2-windows-x86_64\bin>kibana.bat
Node.js is only supported on Windows 8.1, Windows Server 2012 R2, or higher.
Setting the NODE_SKIP_PLATFORM_CHECK environment variable to 1 skips this
check, but Node.js might not execute correctly. Any issues encountered on
unsupported platforms will not be fixed.
F:\DingSaiElastic\kibana\kibana-7.11.2-windows-x86_64\bin>


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


表table== index(Type)索引(录入数据叫索引indexing) :(7版之前index可以多个type，已不建议)
行数据Row==Document文档 (包含元数据:_index、_type、_id、_source、_version、_score)
字段Column==Filed
Schema(DataBase数据库)==Mapping
SQL===DSL

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

**Dynamic Mapping 和常见字段类型**


**I.什么是mapping**

Mapping类似数据库中的schema定义作用如下:
+ 定义索引中的字段的名称
+ 定义字段的数据类型，例如字符串，数字，布尔
+ 字段，倒排索引的相关配置

Mapping会把Json 文档映射成Lucene所需要的扁平格式

一个mapping 属于一个索引的type
+ 每个文档都属于一个type
+ 一个type有一个mapping定义
+ 7.0开始，不需要在mapping定义中指定type信息


**I.字段的数据类型:**
https://baijiahao.baidu.com/s?id=1661467987954314526&wfr=spider&for=pc

字符串:
text 类型适用于需要被全文检索的字段、会被分词 倒排。text 字段不能被用于排序

keyword 适合简短、结构化的字符串(姓名、商品名).可以用于过滤、排序、精确查找


Dynamic Mapping 
写入索引时，如果不存在索引，则自动创建

类型的自动识别:

Json串>匹配日期。


**19-显式Mapping设置与常见参数介绍[更多课程qq 2949651508]**
https://www.elastic.co/guide/en/elasticsearch/reference/7.1/mapping-params.html

**I.Mapping建议**

**II.index是否被索引**
index:false可以控制是否被索引

index_options:offsets 可以设置索引级别

docs    
freqs
positions : text类型默认docs其他类型默认都是 docs
offsets


**null_value空值**
需要对null搜索
只有keyword类型支持设定null_value

**copy_to设置**
_all 在7中被 copy_to 替代

满足特定需求
copy_to 将字段数值拷贝到目标字段。

copy_to 不会出现在_source中

**数组类型**





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




**20-多字段特性及Mapping中配置自定义Analyzer**

**字符串:**
text 类型适用于需要被全文检索的字段、会被分词 倒排。text 字段不能被用于排序

keyword 适合简短、结构化的字符串(姓名、商品名).可以用于过滤、排序、精确查找



**多字段特性**
给索引定制mapping时可以给text类型的字段指定一个keyword字段实现精准匹配

使用不同analyzer分词器，可以针对不同语言进行分词等
+ 不同语言
+ pinyin字段的搜索
+ 支持为搜索和索引指定不同的analyzer


**Exact values 精确 VS full text 全文本**

Exact values:精确值不需要被分词
full text :全文本，非结构化的文本数据 需要text类型




**21-Index-Template和Dynamic-Template**
更好的设置索引

日志索引
logs-20120202
logs-20120203
logs-20120204

**什么是Index-Template**
帮助你设定mapping和settings 并按照一定的规则，自动匹配到新创建的索引之上。

+ 模板仅在一个索引被新创建时，才会产生作用。修改模板不会影响已创建的索引
+ 可以设定多个索引模板，设置会被merge
+ 可以指定order数值,控制merging的过程


**Index Template的工作方式**

新索引被创建时
+ 应用ES默认的setting和mapping
+ 应用order 数值低的index template设定
+ 应用order 高的 index template ,之前的设置被覆盖
+ 应用指定的setting 和mapping 并覆盖之前的



什么是Dynamic Template
根据ES 识别的数据类型，结合字段名称，来动态设定字段类型
+ 所有字符串都设定成keyword,或者关闭keyword字段
+ is开头的字段都设成boolean
+ long_开头的都设置成long类型




**22-Elasticsearch聚合分析简介**


什么是聚合 Aggregation 聚集集合 四种

Es 除了搜索以外，提供针对ES数据进行实时统计分析的功能
+ 实时分析
+  hadoop T+1


通过聚合，我们会得到一个数据概览，是分析和总结全套的数据，而不是寻找单个文档
+ 尖沙咀和香港岛的客房数量
+ 不同价格区间，经济和五星

高性能，只需要一条语句，可以得到ES的分析结果
+ 无需在客户端自己去实现分析逻辑



**集合的分类**

+ Bucket Aggregation 一些累满足特定条件的文档集合
+ Metric Aggregation 一些数学运算，可以对文档字段进行统计分析
+ Pipeline Aggregation 对其他的聚合结果进行二次聚合
+ Matrix Aggregation 支持对多个字段的操作并提供一个结果矩阵



**Bucket 桶 & Metric 度量**

Bucket >> count  桶

Metric >> group   度量

select count(id) -- Metric度量 一系列的统计方法
from cars
Group by age     -- Bucket桶 一组满足条件的文档


**II.Bucket 桶 count**

Term& range 时间、年龄、地理位置



**II.Metric 度量 group**

min 、max、 sum、avg、cardinality





可视化报表--聚合分析





**57-集群内部安全通信**



I.分布式特性

ES分布式好处
+ 存储的水平拓展，支持PB数据
+ 提高系统的可用性，部分节点停止服务，整个集群的服务不受影响

ES分布式架构
+ 不同机器通过不同的名字来区分
+ 修改配置文件 或者命令进行修改


ElasticSearch监控工具 - cerebro



segment



**59-常见的集群部署方式[更多课程qq 2949651508]**



**节点类型** 建议单一角色的节点


**不同角色的节点**
+ Master eligible / Data / Ingest / Coordinating / Machine Learning

● 在开发环境中，一个节点可承担多种角色

● 在生产环境中，
+ 根据数据量，写入和查询的吞吐量，选择合适的部署方式
+ 建议设置单一角色的节点（dedicated node）



**节点参数配置**

+ master eligible
  配置参数:node.master
  默认值:true
  
+ data node
  配置参数:node.data
  默认值:true
  
+ ingest node
  配置参数:node.ingest
  默认值:true

+ coordinating only
  配置参数:无
  默认值:设置上面三个参数全部为false

+ machine learning
  配置参数:node.ml
  默认值:true (需要enable x-pack)


**单一职责的节点**


+ master eligible
  node.master: true
  node.ingest: false
  node.data: false

+ data node
  node.master: false
  node.ingest: false
  node.data: true

+ ingest node
  node.master: false
  node.ingest: true
  node.data: false

+ coordinating only
  node.master: false
  node.ingest: false
  node.data: false

https://www.cnblogs.com/liang1101/p/7284205.html

**ES node单一角色：职责分离的好处**

+ master  nodes：负责集群状态(cluster state)的管理
   + 使用低配置的 CPU，RAM 和磁盘

+ data nodes：负责数据存储及处理客户端请求
   + 使用高配置的 CPU, RAM 和磁盘

+ ingest nodes：负责数据处理-- 用于预处理数据（索引和搜索阶段都可以用到）
   + 使用高配置 CPU；中等配置的RAM； 低配置的磁盘

+ Coordinating Only Node (Client Node)
   + 配置：将 Master，Data，Ingest 都配置成 False
    Medium/High CUP；Medium/High RAM；Low Disk
   + 生产环境中，建议为一些大的集群配置 Coordinating Only Nodes
     + 扮演 Load Balancers。降低 Master 和 Data Nodes 的负载
     + 负责搜索结果的 Gather/Reduce
     + 有时候无法预知客户端会发送怎么样的请求 ( 大量占用内存的结合操作，一个深度聚合可能会引发 OOM)


+ Master Node

● 从高可用 & 避免脑裂的角度出发
+ 一般在生产环境中配置 3 台
+ 一个集群只有 1 台活跃的主节点 (负责分片管理，索引创建，集群管理等操作)

● 如果和数据节点或者 Coordinate 节点混合部署
+ 数据节点相对有比较大的内存占用
+ Coordinate 节点有时候可能会有开销很高的查询，导致 OOM
+ 这些都有可能影响 Master 节点，导致集群的不稳定















**61-分片设计及管理[更多课程qq 2949651508]**


单个分片
+ 7.0 开始，新创建一个索引时，默认只有一个主分片
   + 单个分片，查询算分，聚合不准的问题都可以得以避免
    
+ 单个索引，单个分片时候，集群无法实现水平扩展
   + 即使增加新的节点，无法实现水平扩展


两个分片
集群增加一个节点后，Elasticsearch 会自动进行分片的移动，也叫 Shard Rebalancing



**如何设计分片数**

+ 当分片数 > 节点数时
   + 一旦集群中有新的数据节点加入，分片就可以自动进行分配
   + 分片在重新分配时，系统不会有 downtime


+ 多分片的好处：一个索引如果分布在不同的节点，多个节点可以并行执行
   + 查询可以并行执行
   + 数据写入可以分散到多个机器


**分片过多所带来的副作用**
Shard 是 Elasticsearch 实现集群水平扩展的最小单位


+ 过多设置分片数会带来一些潜在的问题

每个分片是一个 Lucene 的 索引，会使用机器的资源。过多的分片会导致额外的性能开销


Lucene Indices / File descriptors / RAM / CPU
每次搜索的请求，需要从每个分片上获取数据
分片的 Meta 信息由 Master 节点维护。过多，会增加管理的负担。经验值，控制分片总数在 10 W 以内



**如何确定主分片数**

+ 从存储的物理角度看
  + 日志类应用，单个分片不要大于 50 GB
  + 搜索类应用，单个分片不要超过20 GB


+ 为什么要控制分片存储大小
  + 提高 Update 的性能
  + Merge 时，减少所需的资源
  + 丢失节点后，具备更快的恢复速度 / 便于分片在集群内 Rebalancing




**如何确定副本分片数**

副本是主分片的拷贝
+ 提高系统可用性：相应查询请求，防止数据丢失
+ 需要占用和主分片一样的资源


对性能的影响
+ 副本会降低数据的索引速度：有几份副本就会有几倍的 CPU 资源消耗在索引上
+ 会减缓对主分片的查询压力，但是会消耗同样的内存资源
   如果机器资源充分，提高副本数，可以提高整体的查询 QPS



**调整分片总数设定，避免分配不均衡**
ES 的分片策略会尽量保证节点上的分片数大致相同:

扩容的新节点没有数据，导致新索引集中在新 的节点
热点数据过于集中，可能会产生新能问题


**62-如何对集群进行容量规划[更多课程qq 2949651508]**

**容量规划**
一个集群总共需要多少个节点？ 一个索引需要设置几个分片？
规划上需要保持一定的余量，当负载出现波动，节点出现丢失时，还能正常运行


做容量规划时，一些需要考虑的因素
+ 机器的软硬件配置
+ 单条文档的尺寸 / 文档的总数据量 / 索引的总数据量（Time base 数据保留的时间）/ 副本分片数
+ 文档是如何写入的（Bulk的尺寸）
+ 文档的复杂度，文档是如何进行读取的（怎么样的查询和聚合）


**评估业务的性能需求**
+ 数据吞吐及性能需求
  + 数据写入的吞吐量，每秒要求写入多少数据？
  + 查询的吞吐量？
  + 单条查询可接受的最大返回时间？

+ 了解你的数据
  + 数据的格式和数据的 Mapping
  + 实际的查询和聚合长的是什么样的



**常见用例**

搜索：固定大小的数据集(搜索的数据集增长相对比较缓慢)

日志：基于时间序列的数据
 + 使用 ES 存放日志与性能指标。数据每天不断写入，增长速度较快
 + 结合 Warm Node 做数据的老化处理



**硬件配置**
+ 选择合理的硬件，数据节点尽可能使用 SSD
+ 搜索等性能要求高的场景，建议 SSD(按照 1 ：10 的比例配置内存和硬盘)
+ 单节点数据建议控制在 2 TB 以内，最大不建议超过 5 TB(按照 1：50 的比例配置内存和硬盘)
+ JVM 配置机器内存的一半，JVM 内存配置不建议超过 32 G


**部署方式**

+ 按需选择合理的部署方式
+ 如果需要考虑可靠性高可用，建议部署 3 台 dedicated 的 Master 节点
+ 如果有复杂的查询和聚合，建议设置 Coordinating 节点


**容量规划案例 1: 固定大小的数据集**
一些案例：唱片信息库 / 产品信息


一些特性:
+ 被搜索的数据集很大，但是增长相对比较慢（不会有大量的写入）。更关心搜索和聚合的读取性能
+ 数据的重要性与时间范围无关。关注的是搜索的相关度

估算索引的的数据量，然后确定分片的大小
+ 单个分片的数据不要超过 20 GB
+ 可以通过增加副本分片，提高查询的吞吐量


**容量规划案例 2: 基于时间序列的数据**

相关的用案
+ 日志 / 指标 / 安全相关的 Events
+ 舆情分析


一些特性
+ 每条数据都有时间戳；文档基本不会被更新（日志和指标数据）
+ 用户更多的会查询近期的数据；对旧的数据查询相对较少
+ 对数据的写入性能要求比较高


63-在私有云上管理Elasticsearch集群的一些方法[更多课程qq 2949651508]




**65-生产环境常用配置与上线清单**













表table== index(Type)索引(录入数据叫索引indexing) :(7版之前index可以多个type，已不建议)
行数据Row==Document文档 (包含元数据:_index、_type、_id、_source、_version、_score)
字段Column==Filed
Schema(DataBase数据库)==Mapping(一个index字段名、数据类型、是否分词和加入倒排索引)
SQL===DSL










**Elasticsearch SQL是一个X-Pack组件**
6.3 版本以后就开始自带了  7.0开始变


https://www.elastic.co/guide/en/elasticsearch/reference/current/sql-getting-started.html
https://www.elastic.co/guide/en/elasticsearch/reference/7.12/sql-getting-started.html

curl -X PUT "localhost:9200/library/book/_bulk?refresh&pretty" -H 'Content-Type: application/json' -d'
{"index":{"_id": "Leviathan Wakes"}}
{"name": "Leviathan Wakes", "author": "James S.A. Corey", "release_date": "2011-06-02", "page_count": 561}
{"index":{"_id": "Hyperion"}}
{"name": "Hyperion", "author": "Dan Simmons", "release_date": "1989-05-26", "page_count": 482}
{"index":{"_id": "Dune"}}
{"name": "Dune", "author": "Frank Herbert", "release_date": "1965-06-01", "page_count": 604}
'


PUT /library/book/_bulk?refresh
{"index":{"_id": "Leviathan Wakes"}}
{"name": "Leviathan Wakes", "author": "James S.A. Corey", "release_date": "2011-06-02", "page_count": 561}
{"index":{"_id": "Hyperion"}}
{"name": "Hyperion", "author": "Dan Simmons", "release_date": "1989-05-26", "page_count": 482}
{"index":{"_id": "Dune"}}
{"name": "Dune", "author": "Frank Herbert", "release_date": "1965-06-01", "page_count": 604}




--新版本语法
POST /_sql?format=txt
{
"query": "SELECT * FROM library WHERE release_date < '2300-01-01'"
}

-- 本机老版本语法

POST /_xpack/sql?format=txt
{
"query": "SELECT * FROM library WHERE release_date <'2300-01-01'"
}


POST /_xpack/sql?format=txt
{
"query": "SELECT * FROM library WHERE release_date < 'now/d-15d'"
}





https://www.elastic.co/guide/en/elasticsearch/reference/7.1/mapping-params.html




**71-集群压力测试[更多课程qq 2949651508]**

基准测试:
https://elasticsearch-benchmarks.elastic.co/


**压力测试**
+ 压力测试的目的
   + 容量规划 / 性能优化 / 版本间性能比较 / 性能问题诊断
   + 确定系统稳定性，考察系统功能极限和隐患


+ 压力测试的方法与步骤
   + 测试计划（确定测试场景和测试数据集）
   + 脚本开发
   + 测试环境搭建（不同的软硬件配置） & 运行测试
   + 分析比较结果





**测试目标 & 测试数据**
+ 测试目标
   + 测试集群的读写性能 / 做集群容量规划
   + 对 ES 配置参数进行修改，评估优化效果
   + 修改 Mapping 和 Setting，对数据建模进行优化，并测试评估性能改进
   + 测试 ES 新版本，结合实际场景和老版本进行比较，评估是否进行升级
  
+ 测试数据
   + 数据量 / 数据分布




**测试脚本**
传统调用

ES Rally 简介
+ Elastic 官方开源，基于 Python 3 的压力测试工具
   + https://github.com/elastic/rally
   + 性能测试结果比较： https://elasticsearch-benchmarks.elastic.co

https://www.elastic.co/cn/blog/rally-1-0-0-released-benchmark-elasticsearch-like-we-do
https://esrally.readthedocs.io/en/stable/




 功能介绍
 + 自动创建，配置，运行测试，并且销毁 ES 集群
 + 支持不同的测试数据的比较，也支持将数据导入 ES 集群，进行二次分析
 + 支持测试时指标数据的搜集，方便对测试结果进行深度的分析



**Rally 的安装以及入门**
+ 安装运行
   + Python 3.4+ 和 pip3 / JDK 8 / git 1.9+
   + 运行 pip3 install esrally
   + 运行 esrally configure

+  运行
    + 运行 esrally –distribution-version=7.1.0
    + 运行 1000 条测试数据： esrally –distribution-version=7.1.0 --test-mode


I.redhat安装 rally

II.基础环境等安装参考
https://blog.csdn.net/qq_41425925/article/details/109162814
 
II.rally安装参考
https://github.com/elastic/rally

https://www.python.org/downloads/source/
Python-3.8.0.tgz

https://www.python.org/ftp/python/3.8.0/Python-3.8.0.tgz

II.离线版本
https://esrally.readthedocs.io/en/latest/offline.html





I.
cd /usr/local

代理:
www.代理域名:8000

II.离线python
wget --no-check-certificate https://www.python.org/ftp/python/3.8.0/Python-3.8.0.tgz -e use_proxy=yes -e https_proxy=www.代理域名:8000




yum install gcc

https://blog.csdn.net/qq_41425925/article/details/109162814


II.离线setuptools

wget –no-check-certificate https://pypi.python.org/packages/source/s/setuptools/setuptools-19.6.tar.gz  -e use_proxy=yes -e https_proxy=www.代理域名:8000



https://pypi.org/project/wheel/0.35.1/#files





II.离线  Rally
pip3 --proxy="http://www.代理域名:8000"   install esrally

报错需要升级
pip3  --proxy="http://www.代理域名:8000"  install --upgrade pip





II.又报错
FileNotFoundError: [Errno 2] No such file or directory: '/usr/local/python3/lib/python3.8/site-packages/setuptools-19.6-py3.8.egg'


https://pypi.org/project/setuptools/#files


tar -zxvf setuptools-56.0.0.tar.gz
cd setuptools-56.0.0
python3 setup.py build
python3 setup.py install





II.运行
注意: 有坑!! esrally
-bash: esrally: command not found


cd /usr/local/python3/bin



./esrally race --distribution-version=7.11.2 --track=geonames


-- 检测JDK 等
./esrally configure 









./esrally race --track=geonames --pipeline=benchmark-only --target-hosts=aaa.aa.aa.aa:9200,




II.
Python 3.8 解决ModuleNotFoundError: No module named '_bz2'

https://www.jianshu.com/p/b722adc2ba52

/usr/local/python3/lib/python3.8/lib-dynload

chmod +x _bz2.cpython-38-x86_64-linux-gnu.so



cd /usr/local/python3/bin





I.查看代理
env|grep -i proxy

export http_proxy=http://www.代理域名:8000
export https_proxy=http://www.代理域名:8000

II.取消代理
unset http_proxy
unset https_proxy


env|grep -i proxy


tar -xzf esrally-dist-linux-2.2.0.tar.gz

esrally-dist-linux-2.2.0.tar.gz


./esrally-dist-2.2.0/install.sh



II.下载数据
# downloads the script from Github

curl -O https://raw.githubusercontent.com/elastic/rally-tracks/master/download.sh
chmod u+x download.sh

# download all data for the geonames track
./download.sh geonames


cp -r /usr/local/rally-track-data-geonames.tar /home/elsearch/

cd /usr/local
cd  /home/elsearch

tar -xf rally-track-data-geonames.tar








esrally list tracks


II.错误
Cannot list. Your git version is [['git version 1.8.3.1']] but Rally requires at least git 1.9. Please update git.

cd /usr/local/

下载需要安装的版本号
wget https://mirrors.edge.kernel.org/pub/software/scm/git/git-2.24.0.tar.gz
安装需求
yum install curl-devel expat-devel gettext-devel openssl-devel zlib-devel
卸载Centos自带的git:
yum remove git
git --version
安装
tar -zxf git-2.24.0.tar.gz
cd git-2.24.0
make prefix=/usr/local/git all
make prefix=/usr/local/git install
添加环境变量
vim /etc/profile
export PATH=$PATH:/usr/local/git/bin
source /etc/profile
查看版本号
git --version
git version 2.24.0


vim /etc/profile



/usr/bin








II.错误
In file included from http.c:2:0:
http.h:6:23: fatal error: curl/curl.h: No such file or directory
#include <curl/curl.h>


yum install libcurl-dev libcurl-devel

yum install expat-devel




/usr/local/python3/bin/esrally --help




/usr/local/python3/bin/esrally list tracks

/usr/local/python3/bin/esrally list tracks --offline

git config --global http.proxy $http_proxy

    git config --global https.proxy $https_proxy




cd /home/elsearch/.rally/

cd  /home/elsearch

tar -xf rally-track-data-geonames.tar







/usr/local/python3/bin/esrally race --track=geonames --pipeline=benchmark-only --target-hosts=aa.aa.vv.45:9200










----------------------------------------------------
ROOT账户执行-不知道为啥

[root@localhost .rally]# rm -rf benchmarks/
[root@localhost .rally]# ls
logging.json  logs  rally.ini
[root@localhost .rally]#  cd  /home/elsearch
[root@localhost elsearch]# tar -xf rally-track-data-geonames.tar
[root@localhost elsearch]# cd /home/elsearch/.rally/
[root@localhost .rally]# ls
benchmarks  logging.json  logs  rally.ini
[root@localhost .rally]# rm -rf benchmarks/
[root@localhost .rally]# ls
logging.json  logs  rally.ini
[root@localhost .rally]# cd  /home/elsearch
[root@localhost elsearch]# tar -xf rally-track-data-geonames.tar
[root@localhost elsearch]# cd /home/elsearch/.rally/
[root@localhost .rally]# ls
benchmarks  logging.json  logs  rally.ini
[root@localhost .rally]#  /usr/local/python3/bin/esrally list tracks


[root@localhost .rally]#  /usr/local/python3/bin/esrally list tracks

    ____        ____
/ __ \____ _/ / /_  __
/ /_/ / __ `/ / / / / /
/ _, _/ /_/ / / / /_/ /
/_/ |_|\__,_/_/_/\__, /
/____/

[WARNING] No Internet connection detected. Automatic download of track data sets etc. is disabled.
Available tracks:

Name           Description                                                                                                                                                                        Documents    Compressed Size    Uncompressed Size    Default Challenge        All Challenges
-------------  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------  -----------  -----------------  -------------------  -----------------------  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
eql            EQL benchmarks based on endgame index of SIEM demo cluster                                                                                                                         60,782,211   4.5 GB             109.2 GB             default                  default
eventdata      This benchmark indexes HTTP access logs generated based sample logs from the elastic.co website using the generator available in https://github.com/elastic/rally-eventdata-track  20,000,000   756.0 MB           15.3 GB              append-no-conflicts      append-no-conflicts,transform
geonames       POIs from Geonames                                                                                                                                                                 11,396,503   252.9 MB           3.3 GB               append-no-conflicts      append-no-conflicts,append-no-conflicts-index-only,append-sorted-no-conflicts,append-fast-with-conflicts
geopoint       Point coordinates from PlanetOSM                                                                                                                                                   60,844,404   482.1 MB           2.3 GB               append-no-conflicts      append-no-conflicts,append-no-conflicts-index-only,append-fast-with-conflicts
geopointshape  Point coordinates from PlanetOSM indexed as geoshapes                                                                                                                              60,844,404   470.8 MB           2.6 GB               append-no-conflicts      append-no-conflicts,append-no-conflicts-index-only,append-fast-with-conflicts
geoshape       Shapes from PlanetOSM                                                                                                                                                              60,523,283   13.4 GB            45.4 GB              append-no-conflicts      append-no-conflicts
http_logs      HTTP server log data                                                                                                                                                               247,249,096  1.2 GB             31.1 GB              append-no-conflicts      append-no-conflicts,runtime-fields,append-no-conflicts-index-only,append-sorted-no-conflicts,append-index-only-with-ingest-pipeline,update,append-no-conflicts-index-reindex-only
metricbeat     Metricbeat data                                                                                                                                                                    1,079,600    87.7 MB            1.2 GB               append-no-conflicts      append-no-conflicts
nested         StackOverflow Q&A stored as nested docs                                                                                                                                            11,203,029   663.3 MB           3.4 GB               nested-search-challenge  nested-search-challenge,index-only
noaa           Global daily weather measurements from NOAA                                                                                                                                        33,659,481   949.4 MB           9.0 GB               append-no-conflicts      append-no-conflicts,append-no-conflicts-index-only,top_metrics,aggs
nyc_taxis      Taxi rides in New York in 2015                                                                                                                                                     165,346,692  4.5 GB             74.3 GB              append-no-conflicts      append-no-conflicts,append-no-conflicts-index-only,append-sorted-no-conflicts-index-only,update,append-ml,date-histogram
percolator     Percolator benchmark based on AOL queries                                                                                                                                          2,000,000    121.1 kB           104.9 MB             append-no-conflicts      append-no-conflicts
pmc            Full text benchmark with academic papers from PMC                                                                                                                                  574,199      5.5 GB             21.7 GB              append-no-conflicts      append-no-conflicts,append-no-conflicts-index-only,append-sorted-no-conflicts,append-fast-with-conflicts
so             Indexing benchmark using up to questions and answers from StackOverflow                                                                                                            36,062,278   8.9 GB             33.1 GB              append-no-conflicts      append-no-conflicts

-------------------------------
[INFO] SUCCESS (took 8 seconds)








II.仅仅基准测试

https://esrally.readthedocs.io/en/latest/pipelines.html







I.结论

II.阿里云提供的集群指标
https://www.alibabacloud.com/help/zh/doc-detail/127658.htm?spm=a2c63.p38356.b99.17.302b5c89i5lcNq


https://www.alibabacloud.com/help/zh/doc-detail/127662.htm?spm=a2c63.p38356.879954.7.518c704bzY7a6R#concept-1443296



II.官网指标说明
https://esrally.readthedocs.io/en/latest/summary_report.html



curl http://aa.bb.cc.dd:9200/_cat/health\?v











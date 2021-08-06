https://www.jianshu.com/p/c59f5f353390
https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/production-ready-metrics.html
http://c.biancheng.net/view/5362.html



I.介绍Spring
第一章：初识Spring (4讲)
01 | Spring课程介绍


II.数据库相关
第二章：JDBC必知必会 (10讲)
第三章：O/R Mapping实践 (9讲)
第四章：NoSQL实践 (7讲)
第五章：数据访问进阶 (8讲)


III.SpringMVC相关
第六章：Spring MVC实践 (14讲)
第七章：访问Web资源 (5讲)
第八章： Web开发进阶 (9讲)



IV.SpringBoot相关
第九章：重新认识Spring Boot (8讲)
第十章：运行中的Spring Boot (11讲)



V.Spring Cloud相关
第十一章：Spring Cloud及Cloud Native概述 (5讲)
第十二章：服务注册与发现 (9讲)
第十三章：服务熔断 (7讲)
第十四章：服务配置 (7讲)
第十五章：Spring Cloud Stream (4讲)
第十六章：服务链路追踪 (6讲)




02 - 一起认识Spring家族的主要成员:
Spring Boot用最少的精力快速构建基于Spring的应用程序
Spring cloud 服务化类似dubbo
Spring cloud Data Flow 类似ETL 数据处理


03 - 跟着Spring了解技术趋势:
从Spring矿建的 release note了解技术框架发展

Spring 5.X仅支持java8
Kotlin 语言
webFlux 异步编程
portlet 不维护了
velocity 不维护了
jasperReport 报表不维护了



04 - 编写你的第一个Spring程序:
1.@RestController
2.@RequestMapping("/hello")
3.run Spring2020StudyApplication
4.curl localhost:8080/hello
5.Spring Boot Actuator:健康检查审计统计用(致动（促动，激励，调节）器)
curl localhost:8080/actuator/health；返回{"status":"UP"}
6.maven 父类依赖<groupId>org.springframework.boot</groupId>
		
7.plugin 生成可执行jar包
spring-boot-maven-plugin
7.1生成JAR包mvn clean package -Dmaven.test.skip=true
7.2原始包spring-2020-study-0.0.1-SNAPSHOT.jar.original
实际运行包spring-2020-study-0.0.1-SNAPSHOT.jar
7.3Jar包运行:C:\spring-2020-study\target\  java -jar spring-2020-study-0.0.1-SNAPSHOT.jar

8.dependenciesmanager修改不继承spring-boot-starter-parent



06 - 如何配置多数据源

1.不同数据源的配置分开

2.使用注意事项
2.1使用的是那个数据源
2.2使用的设施事务、orm使用的是那个数据源



3.多数据源配置方法1.
排除springboot依赖纯手工datasource以及相关内容

4.Springboot协同工作
4.1 在datasource上配置@primary类型的bean


4.2排除springboot的自动配置exclude
exclude={a.class,b.class}
DataSourceAutoConfiguration
DataSourceTransactionManagerAutoConfiguration
JdbcTemplateAutoConfiguration

使用
@Bean
@ConfigurationProperties





---------------------------三太子------------------------------------------




**I.一分钟带你玩转 Spring IoC**

Ioc ( Inversion of Control控制反转)

控制反转就是把创建和管理 bean 的过程转移给了第三方。而这个第三方，就是 Spring IoC Container，对于 IoC 来说，最重要的就是容器。



IoC 容器


何为控制，控制的是什么？
答：是 bean 的创建、管理的权利，控制 bean 的整个生命周期。



何为反转，反转了什么？

答：把这个权利交给了 Spring 容器，而不是自己去控制，就是反转。由之前的自己主动创建对象，变成现在被动接收别人给我们的对象的过程，这就是反转。



**I.Spring事务传播行为**

PROPAGATION_REQUIRED:如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
PROPAGATION_SUPPORTS:支持当前事务，如果当前没有事务，就以非事务方式执行。
PROPAGATION_MANDATORY:使用当前的事务，如果当前没有事务，就抛出异常。
PROPAGATION_REQUIRES_NEW:新建事务，如果当前存在事务，把当前事务挂起。
PROPAGATION_NOT_SUPPORTED:以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
PROPAGATION_NEVER:以非事务方式执行，如果当前存在事务，则抛出异常。
PROPAGATION_NESTED:如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。
                   


**I.Spring 循环依赖**

当前bean不能创建



**I.分布式**

**II.集群特点:**
高性能:通过多台计算机完成同一个工作，达到更高的效率。
高可用:两机或多机内容、工作过程等完全一样。如果一台死机，另一台可以起作用。
    
**II.分布式:**
一个业务分拆多个子业务，部署在不同的服务器上(不同的服务器，运行不同的代码，为了同一个目的)。


分布式/微服务/SOA:

CAP:
C数据一致性
A可用性
P分区容错性


**I.SpringCloud**

服务治理：Spring  Cloud Eureka  类似ZK
客户端负载均衡：Spring Cloud Ribbon  类似客户端负载均衡
服务容错保护：Spring  Cloud Hystrix  解决雪崩-资源隔离、降级机制、融断、缓存
声明式服务调用：Spring  Cloud Feign  (Ribbon+Feign)  代理的方式都加入 防止雪崩的功能
API网关服务：Spring Cloud Zuul  网关 （负载均衡+雪崩）
分布式配置中心：Spring Cloud Config  配置类似ZK



--------------------------------------------------------------------------------------------


05 - 如何配置单数据源

06 - 如何配置多数据源

07 - 那 些好用的连接池们：HikariCP
https://github.com/brettwooldridge/HikariCP
 
1.HikariCP 字节码级别的优化 JavaAssist生成
2.大量小改动
fastStatementList 替换arrayList
无锁集合ConcurrentBag
代理类的优化  用invokestatic 代替了 invokevirtual


Sprin gBoot 2.* 默认使用 HikariCP
配置 spirng.datasource.hikari.* 配置

SpringBoot 1.* 需要自己改




08 - 那些好用的连接池们：Alibaba Druid

详细的监控
exceptionSorter 主流数据库返回码都支持
sql 防注入
内置加密配置
众多拓展点，方便进行定制


在连接前后 做一些操作


选择连接池：可靠性、性能、功能、可运维、可拓展、其他


09 - 如何通过Spring JDBC访问数据库.mp4



**注解定义bean:**
Component
Repository
Service
Controller、RestController



**JdbcTemplate的方法:**
query
queryForObject
queryForList
update
execute


JdbcTemplate批处理
batchUpdate 、 BatchPreparedStatementSetter

NamedParameterJdbcTemplate
batchUpdate、SqlParameterSourceUtils.createBatch




10 - 什么是Spring的事务抽象（上）

Spring的事务传播特性
PROPAGEATION_REQUIRED : 0  当前有事务就用当前，没有就用新的 (默认事务传播特性)
PROPAGEATION_SUPPORTS : 1  事务可有可无、不是必须的        
PROPAGEATION_MANDATORY ：2  当前一定要有事务，不然就抛异常
PROPAGEATION_REQUIRES_NEW ：3 无论是否有事务，都启新的事务
PROPAGEATION_NOT_SUPPORTED ：4 不支持事务，按分事务方式进行
PROPAGEATION_NEVER ：5   不支持事务，如果有事务则抛异常
PROPAGEATION_NESTED ：6  当前有事务就在当前事务里再启一个事务


事务隔离级别：默认是-1  走数据库的隔离级别


11 - 什么是Spring的事务抽象（下）
 

**I.编程式事务**
I.TransactionTemplate  自己写
II.TransactionCallback
II.TransactionCallbackWithoutResult 重写方法 TransactionTemplate.execute执行

I.PlatformTransactionManager
II.可以传入TransactionDefinition进行定义

**II.代码样例**
private TransactionTemplate transactionTemplate;
private JdbcTemplate jdbcTemplate;
transactionTemplate.execute(new TransactionCallbackWithoutResult() {
@Override
protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'aaa')");
log.info("COUNT IN TRANSACTION: {}", getCount());
  transactionStatus.setRollbackOnly();
}
});




**I.声明式事务**  
面向切面:

Caller>AOP Proxy>Transaction createOrRollback > Custom Advisor(befor、after) > TargetMethod

基于注解的配置


**开启事务注解方式:**
@EnableTransactionManagement
<tx:annotation-driven/>


**一些配置**

proxyTargetClass :true false:是基于接口的事务还是基于类的事务
mode:
order:事务AOP拦截顺序、默认最低，


**@Transactional** 
transactionManager
propagation
isolation
timeout
readOnly
怎么回滚等 可以配置遇到特定异常才回滚




**12 - 了解Spring的JDBC异常抽象**

Spring将 各个数据的异常都转换成  ：DataAccessException

不管使用 jdbc hibernate都会翻译成 :DataAccessException


Spring是怎么认识数据库各个错误码的
通过SQLErrorCodeSQLExceptionTranslator解析错误码

ErrorCode定义
support/sql-error-codes.xml
Classpath下的 sql-error-codes.xml 可以自己定义来替换SQL的定义


**定制错误码解析逻辑**
sql-error-codes.xml

https://gitee.com/geektime-geekbang/geektime-spring-family/tree/master/Chapter%202/errorcode-demo


单测 单元测试 spring

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorCodeDemoApplicationTests {
@Autowired
private JdbcTemplate jdbcTemplate;
	@Test(expected = CustomDuplicatedKeyException.class)
	public void testThrowingCustomException() {
		jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'a')");
	}
}





13 - 课程答疑（上） 14 - 课程答疑（下）

**开发环境**
java8 ide lombak 

**I.spring常见注解**

**II.java Config相关**
@Configuration
@ImportResource
@ComponentScan
@Bean
@ConfigurationProperties

**II.定义相关注解**
@Component @Repository数据库 @Service
@Controller @RestController
@RequestMapping

**II.注入相关注解**
@Autowired @Qualifier @Resource
@Value




**Actuator Endpoints访问不到**
健康检查、查看容器中的所有bean、查看web Url映射、 查看环境信息

生产不要用这些功能




**多数据源 分库分表 读写分离**
中间件进行路由






**内部方法调用和事务问题**



**REQUIRES_NEW 和 NESTED 事务传播的说明**



**Alibaba Druid 展开说明**
系统属性配置
慢SQL配置 





**15 - 认识Spring Data JPA**



JPA 为对象关系映射提供了一种基于 POJO 的持久化模型
简化数据持久化代码的开发⼯作
为 Java 社区屏蔽不同持久化 API 的差异




**Spring Data**

在保留底层存储特性的同时，提供相对一致的、基于 Spring 的编程模型


主要模块：
Spring Data Commons
Spring Data JDBC
Spring Data JPA
Spring Data Redis






**16 - 定义JPA的实体对象**

**常⽤ JPA 注解**

**I.实体**
• @Entity、@MappedSuperclass
• @Table(name)



**I.主键**
• @Id
•• @GeneratedValue(strategy, generator)
•• @SequenceGenerator(name, sequenceName)


**I.映射**
• @Column(name, nullable, length, insertable, updatable)
• @JoinTable(name)、@JoinColumn(name)

**I.关系**
• @OneToOne、@OneToMany、@ManyToOne、@ManyToMany
• @OrderBy






**17 - 开始我们的线上咖啡馆实战项目：SpringBucks**



实体定义
org.joda
joda-money
1.0.1

org.jadira.usertype
usertype.core

@Column
@Type(type="PersistentMoneyAmount",@org.hibernate.Parameter(name="currencyCode",value="CNY"))
private Money price;




18 - 通过Spring Data JPA操作数据库
在保留底层存储特性的同时，提供相对一致的、基于 Spring 的编程模型

JPA 为对象关系映射提供了一种基于 POJO 的持久化模型
• 简化数据持久化代码的开发⼯作
• 为 Java 社区屏蔽不同持久化 API 的差异

**Spring Data**
在保留底层存储特性的同时，提供相对一致的、基于 Spring 的编程模型
主要模块
• Spring Data Commons
• Spring Data JDBC
• Spring Data JPA
• Spring Data Redis









**常⽤ JPA 注解**
 

@EnableJpaRepositories
@EnableJpaRepositories( basePackages = "com.ding")取代xml

Repository<T, ID> 接⼝
• CrudRepository<T, ID>
• PagingAndSortingRepository<T, ID>
• JpaRepository<T, ID>


实体
• @Entity、@MappedSuperclass
• @Table(name)


主键
• @Id
• @GeneratedValue(strategy, generator)
• @SequenceGenerator(name, sequenceName)

映射
• @Column(name, nullable, length, insertable, updatable)
• @JoinTable(name)、@JoinColumn(name)


关系
• @OneToOne、@OneToMany、@ManyToOne、@ManyToMany
• @OrderBy









Project Lombok
Project Lombok 能够自动嵌⼊ IDE 和构建⼯具，提升开发效率
常⽤功能
• @Getter / @Setter
• @ToString
• @NoArgsConstructor / @RequiredArgsConstructor / @AllArgsConstructor
• @Data
• @Builder
• @Slf4j / @CommonsLog / @Log4j2





@EnableJpaRepositories
@EnableJpaRepositories( basePackages = "com.ding")取代xml

Repository<T, ID> 接⼝
• CrudRepository<T, ID>
• PagingAndSortingRepository<T, ID>
• JpaRepository<T, ID>



**定义查询**

根据方法名定义查询
• find…By… / read…By… / query…By… / get…By…
• count…By…
• …OrderBy…[Asc / Desc]
• And / Or / IgnoreCase
• Top / First / Distinct


**分⻚查询**
• PagingAndSortingRepository<T, ID>
• Pageable / Sort
• Slice<T> / Page<T>



**保存实体**
https://gitee.com/geektime-geekbang/geektime-spring-family/tree/master/Chapter%203/springbucks/src/main/java/geektime/spring/springbucks/model





**查询实体**


@Slf4j
@Service
public class CoffeeService {
@Autowired
private CoffeeRepository coffeeRepository;
    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(
                Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Coffee Found: {}", coffee);
        return coffee;
    }
}

https://gitee.com/geektime-geekbang/geektime-spring-family/tree/master/Chapter%203





**Repository 是怎么从接⼝变成 Bean 的**

JpaRepositoriesRegistrar
• 激活了 @EnableJpaRepositories
• 返回了 JpaRepositoryConfigExtension
RepositoryBeanDefinitionRegistrarSupport.registerBeanDefinitions
• 注册 Repository Bean（类型是 JpaRepositoryFactoryBean ）
RepositoryConfigurationExtensionSupport.getRepositoryConfigurations
• 取得 Repository 配置
JpaRepositoryFactory.getTargetRepository
• 创建了 Repository



**接⼝中的方法是如何被解释的**
RepositoryFactorySupport.getRepository 添加了Advice
• DefaultMethodInvokingMethodInterceptor
• QueryExecutorMethodInterceptor
AbstractJpaQuery.execute 执行具体的查询
语法解析在 Part 中




**通过 MyBatis 操作数据库**







**20 - 通过MyBatis操作数据库**


认识 MyBatis
MyBatis（https://github.com/mybatis/mybatis-3）
• 一款优秀的持久层框架
• ⽀持定制化 SQL、存储过程和⾼级映射


在 Spring 中使⽤ MyBatis
• MyBatis Spring Adapter（https://github.com/mybatis/spring）
• MyBatis Spring-Boot-Starter（https://github.com/mybatis/spring-boot-starter）


简单配置
• mybatis.mapper-locations = classpath*:mapper/**/*.xml
• mybatis.type-aliases-package = 类型别名的包名
• mybatis.type-handlers-package = TypeHandler扫描包名
• mybatis.configuration.map-underscore-to-camel-case = true




Mapper 的定义与扫描
• @MapperScan 配置扫描位置
• @Mapper 定义接⼝
• 映射的定义—— XML 与注解





Mapper 的定义与扫描




**21 - 让MyBatis更好用的那些工具：MyBatis Generator**
自动生成 语句

MyBatis Generator（http://www.mybatis.org/generator/）
• MyBatis 代码⽣成器
• 根据数据库表⽣成相关代码
• POJO
• Mapper 接⼝
• SQL Map XML


**运行 MyBatis Generator**
**I.命令行   我自己用的方式**
• java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml


**I.Maven Plugin（mybatis-generator-maven-plugin）**
• mvn mybatis-generator:generate
• ${basedir}/src/main/resources/generatorConfig.xml

**I.Eclipse Plugin**
**I.Java 程序**
**I.Ant Task**






配置 MyBatis Generator
generatorConfiguration
context
• jdbcConnection
• javaModelGenerator
• sqlMapGenerator
• javaClientGenerator （ANNOTATEDMAPPER / XMLMAPPER / MIXEDMAPPER）
• table




**22 - 让MyBatis更好用的那些工具：MyBatis PageHelper**

MyBatis PageHepler（https://pagehelper.github.io）
• ⽀持多种数据库
• ⽀持多种分⻚方式
• SpringBoot ⽀持（https://github.com/pagehelper/pagehelper-spring-boot ）
• pagehelper-spring-boot-starter





**24 - 通过 Docker 辅助开发**



**不同⼈眼中的 Docker**


开发眼中的 Docker
• 简化了重复搭建开发环境的⼯作

运维眼中的 Docker
• 交付系统更为流畅
• 伸缩性更好




**Docker 常⽤命令**

**镜像相关**
• docker pull <image>
• docker search <image>

**容器相关**
• docker run
• docker start/stop <容器名>
• docker ps <容器名>
• docker logs <容器名>



docker run 的常⽤选项
docker run [OPTIONS] IMAGE [COMMAND] [ARG…]
选项说明
• -d，后台运行容器
• -e，设置环境变量
• --expose / -p 宿主端⼝:容器端⼝
• --name，指定容器名称
• --link，链接不同容器
• -v 宿主⽬录:容器⽬录，挂载磁盘卷



**国内 Docker 镜像配置**


官方 Docker Hub
• https://hub.docker.com
官方镜像
• 镜像 https://www.docker-cn.com/registry-mirror
• 下载 https://www.docker-cn.com/get-docker
阿⾥云镜像
• https://dev.aliyun.com 





通过 Docker 启动 MongoDB
官方指引
• https://hub.docker.com/_/mongo
获取镜像
• docker pull mongo
运行 MongoDB 镜像
• docker run --name mongo -p 27017:27017 -v ~/dockerdata/mongo:/data/db -e MONGO_INITDB_ROOT_USERNAME=admin
-e MONGO_INITDB_ROOT_PASSWORD=admin -d mongo 



**通过 Docker 启动 MongoDB**

登录到 MongoDB 容器中
• docker exec -it mongo bash
通过 Shell 连接 MongoDB
• mongo -u admin -p admin





**25 - 在 Spring 中访问 MongoDB**
https://gitee.com/geektime-geekbang/geektime-spring-family/tree/master/Chapter%204



在 Spring 中访问 MongoDB

MongoDB 是一款开源的⽂档型数据库
• https://www.mongodb.com
Spring 对 MongoDB 的⽀持
• Spring Data MongoDB
• MongoTemplate
• Repository ⽀持



**Spring Data MongoDB 的基本⽤法**


注解
• @Document
• @Id

MongoTemplate
• save / remove
• Criteria / Query / Update



初始化 MongoDB 的库及权限
创建库
use springbucks;
创建⽤户
db.createUser(
{
user: "springbucks",
pwd: "springbucks",
roles: [
{ role: "readWrite", db: "springbucks" }
]
}
)


Spring Data MongoDB 的 Repository
@EnableMongoRepositories
对应接⼝
• MongoRepository<T, ID>
• PagingAndSortingRepository<T, ID>
• CrudRepository<T, ID>


**26 - 在 Spring 中访问 Redis**
在 Spring 中访问 Redis


Redis 是一款开源的内存 KV 存储，⽀持多种数据结构
• https://redis.io
Spring 对 Redis 的⽀持
• Spring Data Redis
• ⽀持的客户端 Jedis / Lettuce
• RedisTemplate
• Repository ⽀持


Jedis 客户端的简单使⽤
• Jedis 不是线程安全的
• 通过 JedisPool 获得 Jedis 实例
• 直接使⽤ Jedis 中的方法



**通过 Docker 启动 Redis**

官方指引
• https://hub.docker.com/_/redis
获取镜像
• docker pull redis
启动 Redis
• docker run --name redis -d -p 6379:6379 redis


**27 - Redis 的哨兵与集群模式**
Redis 的哨兵与集群模式



**Redis 的哨兵模式**
Redis Sentinel 是 Redis 的一种⾼可⽤方案
• 监控、通知、自动故障转移、服务发现


JedisSentinelPool 


**Redis 的集群模式**

Redis Cluster
• 数据自动分⽚（分成16384个 Hash Slot ）
• 在部分节点失效时有一定可⽤性
JedisCluster
• Jedis 只从 Master 读数据，如果想要自动读写分离，可以定制


了解 Spring 的缓存抽象




**28 - 了解 Spring 的缓存抽象**



为不同的缓存提供一层抽象
• 为 Java 方法增加缓存，缓存执行结果
• ⽀持ConcurrentMap、EhCache、Caffeine、JCache（JSR-107）
• 接⼝
• org.springframework.cache.Cache
• org.springframework.cache.CacheManager




**基于注解的缓存**
@EnableCaching
• @Cacheable
• @CacheEvict
• @CachePut
• @Caching
• @CacheConfig



**通过 Spring Boot 配置 Redis 缓存**
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>



spring.cache.type=redis
spring.cache.cache-names=coffee
spring.cache.redis.time-to-live=5000
spring.cache.redis.cache-null-values=false


@CacheConfig(cacheNames = "coffee")
public class CoffeeService {
    @Cacheable
    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }
    @CacheEvict
    public void reloadCoffee() {
    }


**Redis 在 Spring 中的其他⽤法**






**29 - Redis 在 Spring 中的其他用法**



与 Redis 建⽴连接
配置连接⼯⼚
• LettuceConnectionFactory 与 JedisConnectionFactory
• RedisStandaloneConfiguration
• RedisSentinelConfiguration
• RedisClusterConfiguration

**读写分离**

Lettuce 内置⽀持读写分离
• 只读主、只读从
• 优先读主、优先读从
LettuceClientConfiguration
LettucePoolingClientConfiguration
LettuceClientConfigurationBuilderCustomizer


**RedisTemplate**

RedisTemplate<K, V>
• opsForXxx()
StringRedisTemplate



**Redis Repository**

实体注解
• @RedisHash
• @Id
• @Indexed


处理不同类型数据源的 Repository

如何区分这些 Repository
• 根据实体的注解
• 根据继承的接⼝类型
• 扫描不同的包






**31 - Project Reactor 介绍（上）**

“在计算机中，响应式编程或反应式编程（英语：Reactive
Programming）是一种面向数据流和变化传播的编程范式。这
意味着可以在编程语⾔中很方便地表达静态或动态的数据流，
⽽相关的计算模型会自动将变化的值通过数据流进行传播。


33 - 通过 Reactive 的方式访问 Redis

Spring Data Redis


Lettuce 能够⽀持 Reactive 方式
Spring Data Redis 中主要的⽀持
• ReactiveRedisConnection
• ReactiveRedisConnectionFactory
• ReactiveRedisTemplate
• opsForXxx()

通过 Reactive 的方式访问数据
MongoDB

Spring Data MongoDB
MongoDB 官方提供了⽀持 Reactive 的驱动
• mongodb-driver-reactivestreams
Spring Data MongoDB 中主要的⽀持
• ReactiveMongoClientFactoryBean
• ReactiveMongoDatabaseFactory
• ReactiveMongoTemplate



通过 Reactive 的方式访问数据
RDBMS

R2DBC （https://spring.io/projects/spring-data-r2dbc）
• Reactive Relational Database Connectivity
⽀持的数据库
• Postgres（io.r2dbc:r2dbc-postgresql）
• H2（io.r2dbc:r2dbc-h2）
• Microsoft SQL Server（io.r2dbc:r2dbc-mssql）


一些主要的类
• ConnectionFactory
• DatabaseClient
• execute().sql(SQL)
• inTransaction(db -> {})
• R2dbcExceptionTranslator
• SqlErrorCodeR2dbcExceptionTranslator



一些主要的类
• @EnableR2dbcRepositories
• ReactiveCrudRepository<T, ID>
• @Table / @Id
• 其中的方法返回都是 Mono 或者 Flux
• ⾃定义查询需要⾃⼰写 @Query


**36 - 通过 AOP 打印数据访问层的摘要（上）**


通过 AOP 打印数据访问层摘要

**Spring AOP 的一些核心概念**
概念 含义
Aspect 切面
Join Point 连接点，Spring AOP⾥总是代表一次方法执行
Advice 通知，在连接点执行的动作
Pointcut 切⼊点，说明如何匹配连接点
Introduction 引⼊，为现有类型声明额外的方法和属性
Target object ⽬标对象
AOP proxy AOP 代理对象，可以是 JDK 动态代理，也可以是 CGLIB 代理
Weaving 织⼊，连接切面与⽬标对象或类型创建代理的过程



常⽤注解
• @EnableAspectJAutoProxy
• @Aspect
• @Pointcut
• @Before
• @After / @AfterReturning / @AfterThrowing
• @Around
• @Order






如何打印 SQL
HikariCP:本身不支持
• P6SQL，https://github.com/p6spy/p6spy


Alibaba Druid
• 内置 SQL 输出
• https://github.com/alibaba/druid/wiki/Druid中使⽤log4j2进行⽇志输出







**39 - 编写第一个 Spring MVC Controller**



**认识 Spring MVC**
DispatcherServlet(所有请求的入口)
I.Controller

II.xxxResolver解析器
III. ViewResolver(视图解析器)
III. HandlerExceptionResolver(异常解析器)
III. MultipartResolver（文件上传等解析器）

II.HandlerMapping（请求映射到controller）



Spring MVC 中的常⽤注解
I. @Controller
II. @RestController

I.@RequestMapping
II.@GetMapping / @PostMapping
II. @PutMapping / @DeleteMapping

I. @RequestBody / @ResponseBody / @ResponseStatus(返回的http相应吗)




**40 - 理解 Spring 的应用上下文**



理解 Spring 的应⽤上下⽂：是一个容器(业务容器+配置)、管理对象的生命周期


**关于上下⽂常⽤的接⼝**
I.  BeanFactory(Spring里最低层的接口，提供了最简单的容器的功能，只提供了实例化对象和拿对象的功能；)
II.DefaultListableBeanFactory

I. ApplicationContext(继承BeanFactory接口，它是Spring的一各更高级的容器，提供了更多的有用的功能)
II.ClassPathXmlApplicationContext
II.FileSystemXmlApplicationContext
II.AnnotationConfigApplicationContext

I. WebApplicationContext




**41 - 理解请求的处理机制**



一个请求的大致处理流程
绑定一些 Attribute
• WebApplicationContext / LocaleResolver / ThemeResolver

处理 Multipart
• 如果是，则将请求转为 MultipartHttpServletRequest

Handler 处理
• 如果找到对应 Handler，执行 Controller 及前后置处理器逻辑

处理返回的Model ，呈现视图





如何定义处理方法


定义映射关系
@Controller
@RequestMapping
• path / method 指定映射路径与方法
• params / headers 限定映射范围
• consumes / produces 限定请求与响应格式
一些快捷方式
• @RestController
• @GetMapping / @PostMapping / @PutMapping / @DeleteMapping / @PatchMapping


定义处理方法
• @RequestBody / @ResponseBody / @ResponseStatus
• @PathVariable / @RequestParam / @RequestHeader
• HttpEntity / ResponseEntity


定义处理方法
详细参数
• https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-frameworkreference/web.html#mvc-ann-arguments
详细返回
• https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-frameworkreference/web.html#mvc-ann-return-types




42 - 如何定义处理方法（上）

定义类型转换
⾃⼰实现 WebMvcConfigurer
• Spring Boot 在 WebMvcAutoConfiguration 中实现了一个
• 添加⾃定义的 Converter  转换器
• 添加⾃定义的 Formatter 格式化程序





定义校验
• 通过 Validator 对绑定结果进行校验
•• Hibernate Validator
• @Valid 注解
• BindingResult 



Multipart 上传
• 配置 MultipartResolver
• Spring Boot 自动配置 MultipartAutoConfiguration
• ⽀持类型 multipart/form-data
• MultipartFile 类型


**44 - Spring MVC 中的视图解析机制（上）**

ViewResolver 与 View 接⼝
• AbstractCachingViewResolver
• UrlBasedViewResolver
• FreeMarkerViewResolver
• ContentNegotiatingViewResolver
• InternalResourceViewResolver




DispatcherServlet 中的视图解析逻辑
I.initStrategies()
• initViewResolvers() 初始化了对应 ViewResolver
I.doDispatch()
• processDispatchResult()
II.没有返回视图的话，尝试 RequestToViewNameTranslator
II. resolveViewName() 解析 View 对象




DispatcherServlet 中的视图解析逻辑
使⽤ @ResponseBody 的情况
• 在 HandlerAdapter.handle() 的中完成了 Response 输出
• RequestMappingHandlerAdapter.invokeHandlerMethod()
• HandlerMethodReturnValueHandlerComposite.handleReturnValue()
• RequestResponseBodyMethodProcessor.handleReturnValue()



重定向
两种不同的重定向前缀
• redirect:发送到客户端重新访问新地址
• forward:


**46 - Spring MVC 中的常用视图（上）**  JSP 等展示
SpringBoot支持如下页面模板语言

    Thymeleaf
    FreeMarker
    Velocity
    Groovy
    JSP



**Spring MVC ⽀持的视图**
⽀持的视图列表
https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-frameworkreference/web.html#mvc-view
https://docs.spring.io/spring-framework/docs/5.1.5.RELEASE/spring-framework-reference/web.html#mvc-view



• Jackson-based JSON / XML
Thymeleaf 
FreeMarker
Thymeleaf



配置 MessageConverter
• 通过 WebMvcConfigurer 的 configureMessageConverters()
• Spring Boot 自动查找 HttpMessageConverters 进行注

Spring Boot 对 Jackson 的⽀持
• JacksonAutoConfiguration
• Spring Boot 通过 @JsonComponent 注册 JSON 序列化组件
• Jackson2ObjectMapperBuilderCustomizer
• JacksonHttpMessageConvertersConfiguration
• 增加 jackson-dataformat-xml 以⽀持 XML 序列化




使⽤ Thymeleaf
添加 Thymeleaf 依赖
• org.springframework.boot:spring-boot-starter-thymeleaf
Spring Boot 的自动配置
• ThymeleafAutoConfiguration
• ThymeleafViewResolver



Thymeleaf 的一些默认配置
• spring.thymeleaf.cache=true
• spring.thymeleaf.check-template=true
• spring.thymeleaf.check-template-location=true
• spring.thymeleaf.enabled=true
• spring.thymeleaf.encoding=UTF-8
• spring.thymeleaf.mode=HTML
• spring.thymeleaf.servlet.content-type=text/html
• spring.thymeleaf.prefix=classpath:/templates/
• spring.thymeleaf.suffix=.html


**48 - 静态资源与缓存**

Spring Boot 中的静态资源配置
核心逻辑
• WebMvcConfigurer.addResourceHandlers()
常⽤配置
• spring.mvc.static-path-pattern=/**
• spring.resources.static-locations=classpath:/META-INF/
resources/,classpath:/resources/,classpath:/static/,classpath:/public/

常⽤配置（默认时间单位都是秒）
• ResourceProperties.Cache
• spring.resources.cache.cachecontrol.max-age=时间
• spring.resources.cache.cachecontrol.no-cache=true/false
• spring.resources.cache.cachecontrol.s-max-age=时间












**49 - Spring MVC 中的异常处理机制**



Spring MVC 的异常解析
核心接⼝
• HandlerExceptionResolver
实现类
• SimpleMappingExceptionResolver
• DefaultHandlerExceptionResolver
• ResponseStatusExceptionResolver
• ExceptionHandlerExceptionResolver




异常处理方法
处理方法
• @ExceptionHandler
添加位置
• @Controller / @RestController
• @ControllerAdvice / @RestControllerAdvice



**50 - 了解 Spring MVC 的切入点**




Spring MVC 的拦截器
核心接⼝
HandlerInteceptor
• boolean preHandle()
• void postHandle()
• void afterCompletion()




针对 @ResponseBody 和 ResponseEntity 的情况
• ResponseBodyAdvice
针对异步请求的接⼝
• AsyncHandlerInterceptor
• void afterConcurrentHandlingStarted()




常规方法
• WebMvcConfigurer.addInterceptors()
Spring Boot 中的配置
• 创建一个带 @Configuration 的 WebMvcConfigurer 配置类
• 不能带 @EnableWebMvc（想彻底⾃⼰控制 MVC 配置除外）











**53 - 通过 RestTemplate 访问 Web 资源**
https://docs.spring.io/spring-framework/docs/5.1.5.RELEASE/spring-framework-reference/web.html#webmvc-client



Spring Boot 中的 RestTemplate
• Spring Boot 中没有自动配置 RestTemplate
• Spring Boot 提供了 RestTemplateBuilder
• RestTemplateBuilder.build()


常⽤方法
GET 请求
• getForObject() / getForEntity()
POST 请求
• postForObject() / postForEntity()
PUT 请求
• put()
DELETE 请求
• delete() 




构造 URI
• UriComponentsBuilder
构造相对于当前请求的 URI
• ServletUriComponentsBuilder
构造指向 Controller 的 URI
• MvcUriComponentsBuilder


	URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/{id}")
				.build(1);
		ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
		log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
		log.info("Coffee: {}", c.getBody());

		String coffeeUri = "http://localhost:8080/coffee/";
		Coffee request = Coffee.builder()
				.name("Americano")
				.price(BigDecimal.valueOf(25.00))
				.build();
		Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
		log.info("New Coffee: {}", response);

		String s = restTemplate.getForObject(coffeeUri, String.class);
		log.info("String: {}", s);








**54 - RestTemplate 的高阶用法**


		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/coffee/?name={name}")
				.build("mocha");
		RequestEntity<Void> req = RequestEntity.get(uri)
				.accept(MediaType.APPLICATION_XML)
				.build();
		ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
		log.info("Response Status: {}, Response Headers: {}", resp.getStatusCode(), resp.getHeaders().toString());
		log.info("Coffee: {}", resp.getBody());

		String coffeeUri = "http://localhost:8080/coffee/";
		Coffee request = Coffee.builder()
				.name("Americano")
				.price(Money.of(CurrencyUnit.of("CNY"), 25.00))
				.build();
		Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
		log.info("New Coffee: {}", response);

		ParameterizedTypeReference<List<Coffee>> ptr =
				new ParameterizedTypeReference<List<Coffee>>() {};
		ResponseEntity<List<Coffee>> list = restTemplate
				.exchange(coffeeUri, HttpMethod.GET, null, ptr);
		list.getBody().forEach(c -> log.info("Coffee: {}", c));


传递 HTTP Header
• RestTemplate.exchange()
• RequestEntity<T> / ResponseEntity<T>

类型转换
• JsonSerializer / JsonDeserializer
• @JsonComponent
解析泛型对象
• RestTemplate.exchange()
• ParameterizedTypeReference<T>




**55 - 简单定制 RestTemplate**


RestTemplate ⽀持的 HTTP 库
通⽤接⼝
• ClientHttpRequestFactory
默认实现
• SimpleClientHttpRequestFactory

Apache HttpComponents
• HttpComponentsClientHttpRequestFactory
Netty
• Netty4ClientHttpRequestFactory
OkHttp
• OkHttp3ClientHttpRequestFactory




优化底层请求策略
连接管理
• PoolingHttpClientConnectionManager
• KeepAlive 策略
超时设置
• connectTimeout / readTimeout
SSL校验
• 证书检查策略



连接复⽤
• org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy

	@Bean
	public HttpComponentsClientHttpRequestFactory requestFactory() {
		PoolingHttpClientConnectionManager connectionManager =
				new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
		connectionManager.setMaxTotal(200);
		connectionManager.setDefaultMaxPerRoute(20);

		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager)
				.evictIdleConnections(30, TimeUnit.SECONDS)
				.disableAutomaticRetries()
				// 有 Keep-Alive 认里面的值，没有的话永久有效
				//.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
				// 换成自定义的
				.setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
				.build();

		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory(httpClient);

		return requestFactory;
	}




**56 - 通过 WebClient 访问 Web 资源**
**通过 WebClient 访问 Web 资源**


了解 WebClient


WebClient
• 一个以 Reactive 方式处理 HTTP 请求的⾮阻塞式的客户端
⽀持的底层 HTTP 库
• Reactor Netty - ReactorClientHttpConnector
• Jetty ReactiveStream HttpClient - JettyClientHttpConnector


WebClient 的基本⽤法
创建 WebClient
• WebClient.create()
• WebClient.builder()
发起请求
• get() / post() / put() / delete() / patch()




WebClient 的基本⽤法
获得结果
• retrieve() / exchange()
处理 HTTP Status
• onStatus()
应答正⽂
• bodyToMono() / bodyToFlux()






**58 - 设计好的 RESTful Web Service（上）**


“REST提供了一组架构约束，当作为一个整体来应⽤时，强调组件交互的
可伸缩性、接⼝的通⽤性、组件的独⽴部署、以及⽤来减少交互延迟、增
强安全性、封装遗留系统的中间组件。”



如何实现 Restful Web Service
• 识别资源
• 选择合适的资源粒度
• 设计URI
• 选择合适的HTTP方法和返回码
• 设计资源的表述




识别资源
• 找到领域名词
•• 能⽤ CRUD 操作的名词
• 将资源组织为集合（即集合资源）
• 将资源合并为复合资源
• 计算或处理函数




资源的粒度

站在服务端的⻆度，要考虑
• ⽹络效率
• 表述的多少
• 客户端的易⽤程度



站在客户端的⻆度，要考虑
• 可缓存性
• 修改频率
• 可变性



构建更好的 URI
• 使⽤域及⼦域对资源进行合理的分组或划分
• 在 URI 的路径部分使⽤斜杠分隔符 ( / ) 来表示资源之间的层次关系
• 在 URI 的路径部分使⽤逗号 ( , ) 和分号 ( ; ) 来表示⾮层次元素
• 使⽤连字符 ( - ) 和下划线 ( _ ) 来改善⻓路径中名称的可读性
• 在 URI 的查询部分使⽤“与”符号 ( & ) 来分隔参数
• 在 URI 中避免出现⽂件扩展名 ( 例如 .php，.aspx 和 .jsp )




认识 HTTP 方法
动作 安全/幂等 ⽤途
GET Y / Y 信息获取
POST N / N 该方法⽤途⼴泛，可⽤于创建、更新或一次性对多个资源进行修改
DELETE N / Y 删除资源
PUT N / Y 更新或者完全替换一个资源
HEAD Y / Y 获取与GET一样的HTTP头信息，但没有响应体
OPTIONS Y / Y 获取资源⽀持的HTTP方法列表
TRACE Y / Y 让服务器返回其收到的HTTP头








URI 与 HTTP 方法的组合
URI HTTP方法 含义
/coffee/ GET 获取全部咖啡信息
/coffee/ POST 添加新的咖啡信息
/coffee/{id} GET 获取特定咖啡信息
/coffee/{id} DELETE 删除特定咖啡信息
/coffee/{id} PUT 修改特定咖啡信息




认识 HTTP 状态码
状态码 描述 状态码 描述
200 OK 400 Bad Request
201 Created 401 Unauthorized
202 Accepted 403 Forbidden
301 Moved Permanently 404 Not Found
303 See Other 410 Gone
304 Not Modified 500 Internal Server Error
307 Temporary Redirect 503 Service Unavailable






选择合适的表述
JSON
• MappingJackson2HttpMessageConverter
• GsonHttpMessageConverter
• JsonbHttpMessageConverter
XML
• MappingJackson2XmlHttpMessageConverter
• Jaxb2RootElementHttpMessageConverter
HTML
ProtoBuf
• ProtobufHttpMessageConverter 






什么是 HATEOAS

什么是 HATEOAS
Richardson 成熟度模型
• Level 3 - Hypermedia Controls
HATEOAS
• Hybermedia As The Engine Of Application State
• REST 统一接⼝的必要组成部分


HATEOAS v.s. WSDL
HATEOAS
• 表述中的超链接会提供服务所需的各种 REST 接⼝信息
• ⽆需事先约定如何访问服务
传统的服务契约
• 必须事先约定服务的地址与格式





常⽤的超链接类型
http://www.iana.org/assignments/link-relations/link-relations.xhtml
REL 描述
self 指向当前资源本身的链接
edit 指向一个可以编辑当前资源的链接
collection 如果当前资源包含在某个集合中，指向该集合的链接
search 指向一个可以搜索当前资源与其相关资源的链接
related 指向一个与当前资源相关的链接
first 集合遍历相关的类型，指向第一个资源的链接
last 集合遍历相关的类型，指向最后一个资源的链接
previous 集合遍历相关的类型，指向上一个资源的链接
next 集合遍历相关的类型，指向下一个资源的链接




**61 - 使用 Spring Data REST 实现简单的超媒体服务（上）**


使⽤ Spring Data REST 实现简单的超媒体服务


认识 HAL
HAL
• Hypertext Application Language
• HAL 是一种简单的格式，为 API 中的资源提供简单一致的链接
HAL 模型
• 链接
• 内嵌资源
• 状态


Spring Data REST
Spring Boot 依赖
• spring-boot-starter-data-rest
常⽤注解与类
• @RepositoryRestResource
• Resource<T>
• PagedResource<T> 



如何访问 HATEOAS 服务
配置 Jackson JSON
• 注册 HAL ⽀持
操作超链接
• 找到需要的 Link
• 访问超链接





**63 - 分布式环境中如何解决 Session 的问题**


常⻅的会话解决方案
• 粘性会话 Sticky Session    ：永远请求到一台机器
• 会话复制 Session Replication ：会话复制 每台都有全量（数据同步问题）
• 集中会话 Centralized Session  ：JDBC redis集中存储会话信息





**认识 Spring Session**
Spring Session (集中会话 Centralized Session)
• 简化集群中的⽤户会话管理
• ⽆需绑定容器特定解决方案
⽀持的存储
• Redis
• MongoDB
• JDBC
• Hazelcast




实现原理
定制 HttpSession
• 通过定制的 HttpServletRequest 返回定制的 HttpSession
• SessionRepositoryRequestWrapper
• SessionRepositoryFilter
• DelegatingFilterProxy 



基于 Redis 的 HttpSession
引⼊依赖
• spring-session-data-redis
基本配置
• @EnableRedisHttpSession
• 提供 RedisConnectionFactory
• 实现 AbstractHttpSessionApplicationInitializer
• 配置 DelegatingFilterProxy 



Spring Boot 对 Spring Session 的⽀持
application.properties
• spring.session.store-type=redis
• spring.session.timeout=
• server.servlet.session.timeout=
• spring.session.redis.flush-mode=on-save
• spring.session.redis.namespace=spring:session 



**64 - 使用 WebFlux 代替 Spring MVC（上）**

使⽤ WebFlux 代替 Spring MVC




认识 WebFlux
什么是 WebFlux
• ⽤于构建基于 Reactive 技术栈之上的 Web 应⽤程序
• 基于 Reactive Streams API ，运行在⾮阻塞服务器上
为什么会有 WebFlux
• 对于⾮阻塞 Web 应⽤的需要
• 函数式编程




认识 WebFlux
关于 WebFlux 的性能
• 请求的耗时并不会有很大的改善
• 仅需少量固定数量的线程和较少的内存即可实现扩展




WebMVC v.s. WebFlux
• 已有 Spring MVC 应⽤，运行正常，就别改了
• 依赖了大量阻塞式持久化 API 和⽹络 API，建议使⽤ Spring MVC
• 已经使⽤了⾮阻塞技术栈，可以考虑使⽤ WebFlux
• 想要使⽤ Java 8 Lambda 结合轻量级函数式框架，可以考虑 WebFlux



WebFlux 中的编程模型
两种编程模型
• 基于注解的控制器
• 函数式 Endpoints



基于注解的控制器
常⽤注解
• @Controller
• @RequestMapping 及其等价注解
• @RequestBody / @ResponseBody
返回值
• Mono<T> / Flux<T>








**67 - 认识 Spring Boot 的组成部分**

认识 Spring Boot的组成部分




Spring Boot 不是什么
• 不是应⽤服务器
• 不是 Java EE 之类的规范
• 不是代码⽣成器
• 不是 Spring Framework 的升级版



Spring Boot 的特性
• 方便地创建可独⽴运行的 Spring 应⽤程序
• 直接内嵌 Tomcat、Jetty 或 Undertow
• 简化了项⽬的构建配置
• 为 Spring 及第三方库提供自动配置
• 提供⽣产级特性
• ⽆需⽣成代码或进行 XML 配置


 
**Spring Boot 的四大核心**
• 自动配置 - Auto Configuration   
• 起步依赖 - Starter Dependency  
• 命令行界面 - Spring Boot CLI   
• Actuator 运行时生产特性




**68 - 了解自动配置的实现原理**
了解自动配置的实现原理

了解自动配置


自动配置
• 基于添加的 JAR 依赖自动对 Spring Boot 应⽤程序进行配置
• spring-boot-autoconfiguration

开启自动配置
• @EnableAutoConfiguration
••  exclude = Class<?>[]
• @SpringBootApplication





自动配置的实现原理
@EnableAutoConfiguration
• AutoConfigurationImportSelector
• META-INF/spring.factories
• org.springframework.boot.autoconfigure.EnableAutoConfiguration


自动配置的实现原理
条件注解
• @Conditional
• @ConditionalOnClass
• @ConditionalOnBean
• @ConditionalOnMissingBean
• @ConditionalOnProperty



了解自动配置的情况
观察自动配置的判断结果
• --debug
ConditionEvaluationReportLoggingListener
• Positive matches
• Negative matches
• Exclusions
• Unconditional classes



69 - 动手实现自己的自动配置

动⼿实现⾃⼰的自动配置

主要⼯作内容
编写 Java Config
• @Configuration

添加条件
• @Conditional


定位自动配置
• META-INF/spring.factories



条件注解大家庭
条件注解
• @Conditional
类条件
• @ConditionalOnClass
• @ConditionalOnMissingClass
属性条件
• @ConditionalOnProperty

Bean 条件
• @ConditionalOnBean
• @ConditionalOnMissingBean
• @ConditionalOnSingleCandidate
资源条件
• @ConditionalOnResource




Web 应⽤条件
• @ConditionalOnWebApplication
• @ConditionalOnNotWebApplication
其他条件
• @ConditionalOnExpression
• @ConditionalOnJava
• @ConditionalOnJndi


执行顺序
• @AutoConfigureBefore
• @AutoConfigureAfter
• @AutoConfigureOrder






70 - 如何在低版本 Spring 中快速实现类似自动配置的功能


需求与问题
核心的诉求
• 现存系统，不打算重构
• Spring 版本3.x，不打算升级版本和引⼊ Spring Boot
• 期望能够在少改代码的前提下实现一些功能增强
面临的问题
• 3.x 的 Spring 没有条件注解
• ⽆法自动定位需要加载的自动配置







核心解决思路
条件判断
• 通过 BeanFactoryPostProcessor 进行判断
配置加载
• 编写 Java Config 类
• 引⼊配置类
• 通过 component-scan
• 通过 XML ⽂件 import



Spring 的两个扩展点
BeanPostProcessor
• 针对 Bean 实例
• 在 Bean 创建后提供定制逻辑回调
BeanFactoryPostProcessor
• 针对 Bean 定义
• 在容器创建 Bean 前获取配置元数据
• Java Config 中需要定义为 static 方法




关于 Bean 的一些定制
Lifecycle Callback
• InitializingBean / @PostConstruct / init-method
• DisposableBean / @PreDestroy / destroy-method
XxxAware 接⼝
• ApplicationContextAware
• BeanFactoryAware
• BeanNameAware



一些常⽤操作
判断类是否存在
• ClassUtils.isPresent()
判断 Bean 是否已定义
• ListableBeanFactory.containsBeanDefinition()
• ListableBeanFactory.getBeanNamesForType()
注册 Bean 定义
• BeanDefinitionRegistry.registerBeanDefinition()
• GenericBeanDefinition
• BeanFactory.registerSingleton()






71 - 了解起步依赖及其实现原理

很久以前……
• 你能记得多少 Maven 依赖
• 要实现一个功能，需要引⼊哪些依赖
• 多个依赖项⽬之间是否会有兼容问题




关于 Maven 依赖管理的一些⼩技巧
了解你的依赖
• mvn dependency:tree
• IDEA Maven Helper 插件

排除特定依赖
• exclusion

统一管理依赖
• dependencyManagement
• Bill of Materials - bom




Spring Boot 的起步依赖
Starter Dependencies 依靠，依赖; 附属国; 附属地
• 直接面向功能
• 一站获得所有相关依赖，不再复制粘贴
官方的 Starters
• spring-boot-starter-* 

https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-running-with-the-maven-plugin
1.5. Starters







**72 - 定制自己的起步依赖**



你的 Starter
主要内容
• autoconfigure 模块，包含自动配置代码
• starter 模块，包含指向自动配置模块的依赖及其他相关依赖

命名方式
• xxx-spring-boot-autoconfigure
• xxx-spring-boot-starter


一些注意事项
• 不要使⽤ spring-boot 作为依赖的前缀
• 不要使⽤ spring-boot 的配置命名空间
• starter 中仅添加必要的依赖
• 声明对 spring-boot-starter 的依赖





**73 - 深挖 Spring Boot 的配置加载机制**





外化配置加载顺序
• 开启 DevTools 时，~/.spring-boot-devtools.properties
• 测试类上的 @TestPropertySource 注解
• @SpringBootTest#properties 属性
• 命令行参数（ --server.port=9000 ）
• SPRING_APPLICATION_JSON 中的属性
• ServletConfig 初始化参数
• ServletContext 初始化参数
• java:comp/env 中的 JNDI 属性
• System.getProperties()  启动 -d 的一些参数
• 操作系统环境变量
• random.* 涉及到的 RandomValuePropertySource
• jar 包外部的 application-{profile}.properties 或 .yml
• jar 包内部的 application-{profile}.properties 或 .yml
• jar 包外部的 application.properties 或 .yml
• jar 包内部的 application.properties 或 .yml
• @Configuration 类上的 @PropertySource
• SpringApplication.setDefaultProperties() 设置的默认属性



application.properties
默认位置
• ./config
• ./
• CLASSPATH 中的 /config
• CLASSPATH 中的 /


application.properties
修改名字或路径
• spring.config.name
• spring.config.location
• spring.config.additional-location




Relaxed Binding
命名⻛格 使⽤范围 示例

Properties ⽂件
YAML ⽂件
系统属性
短划线分隔
geektime.spring-boot.first-demo
驼峰式 geektime.springBoot.firstDemo
下划线分隔 geektime.spring_boot.first_demo
全大写，下划线分隔 环境变量 GEEKTIME_SPRINGBOOT_FIRSTDEMO


短划线分隔、驼峰式、下划线分隔、全大写下划线分隔




**74 - 理解配置背后的 PropertySource 抽象**


@PropertySource(value = "classpath:spring/config.properties")


org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
@ConfigurationProperties(
prefix = "spring.datasource"
)


PropertySource
添加 PropertySource
• <context:property-placeholder>
• PropertySourcesPlaceholderConfigurer
• PropertyPlaceholderConfigurer
• @PropertySource
• @PropertySources 




Spring Boot 中的 @ConfigurationProperties
• 可以将属性绑定到结构化对象上
• ⽀持 Relaxed Binding
• ⽀持安全的类型转换
• @EnableConfigurationProperties






定制 PropertySource
主要步骤
• 实现 PropertySource<T>
• 从 Environment 取得 PropertySources
• 将⾃⼰的 PropertySource 添加到合适的位置
切⼊位置
• EnvironmentPostProcessor
• BeanFactoryPostProcessor









**75 - 认识 Spring Boot 的各类 Actuator Endpoint**


运行中的 Spring Boot


认识 Spring Boot 的各类 Actuator Endpoint


Actuator
⽬的
• 监控并管理应⽤程序
访问方式
• HTTP
• JMX
依赖
• spring-boot-starter-actuator




一些常⽤ Endpoint
ID 说明 默认开启 默认 HTTP 默认 JMX
beans 显示容器中的 Bean 列表 Y N Y
caches 显示应⽤中的缓存 Y N Y
conditions 显示配置条件的计算情况 Y N Y
configprops 显示 @ConfigurationProperties 的信息 Y N Y
env 显示 ConfigurableEnvironment 中的属性 Y N Y
health 显示健康检查信息 Y Y Y
httptrace 显示 HTTP Trace 信息 Y N Y
info 显示设置好的应⽤信息 Y Y Y






一些常⽤ Endpoint
ID 说明 默认开启 默认 HTTP 默认 JMX
loggers 显示并更新⽇志配置 Y N Y
metrics 显示应⽤的度量信息 Y N Y
mappings 显示所有的 @RequestMapping 信息 Y N Y
scheduledtasks 显示应⽤的调度任务信息 Y N Y
shutdown 优雅地关闭应⽤程序 N N Y
threaddump 执行 Thread Dump Y N Y
heapdump 返回 Heap Dump ⽂件，格式为 HPROF Y N N/A
prometheus 返回可供 Prometheus 抓取的信息 Y N N/A




如何访问 Actuator Endpoint
HTTP 访问
• /actuator/<id>
端⼝与路径
• management.server.address=
• management.server.port=
• management.endpoints.web.base-path=/actuator
• management.endpoints.web.path-mapping.<id>=路径


如何访问 Actuator Endpoint
开启 Endpoint
• management.endpoint.<id>.enabled=true
• management.endpoints.enabled-by-default=false
暴露 Endpoint
• management.endpoints.jmx.exposure.exclude=
• management.endpoints.jmx.exposure.include=*
• management.endpoints.web.exposure.exclude=
• management.endpoints.web.exposure.include=info, health





**76 - 动手定制自己的 Health Indicator**

动⼿定制⾃⼰的 Health Indicator



Spring Boot ⾃带的 Health Indicator
⽬的
• 检查应⽤程序的运行状态
状态
• DOWN - 503
• OUT_OF_SERVICE - 503
• UP - 200
• UNKNOWN - 200



Spring Boot ⾃带的 Health Indicator
机制
• 通过 HealthIndicatorRegistry 收集信息
• HealthIndicator 实现具体检查逻辑
配置项
• management.health.defaults.enabled=true|false
• management.health.<id>.enabled=true
• management.endpoint.health.show-details=never|whenauthorized|always







Spring Boot ⾃带的 Health Indicator
内置 HealthIndicator 清单
CassandraHealthIndicator ElasticsearchHealthIndicator MongoHealthIndicator SolrHealthIndicator
CouchbaseHealthIndicator InfluxDbHealthIndicator Neo4jHealthIndicator
DiskSpaceHealthIndicator JmsHealthIndicator RabbitHealthIndicator
DataSourceHealthIndicator MailHealthIndicator RedisHealthIndicator



⾃定义 Health Indicator
方法
• 实现 HealthIndicator 接⼝
• 根据⾃定义检查逻辑返回对应 Health 状态
• Health 中包含状态和详细描述信息



**77 - 通过 Micrometer 获取运行数据**
https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/production-ready-metrics.html

Micrometer(给监控提供度量)
为主流监控系统提供探针等功能  

认识 Micrometer
特性

• 多维度度量
•• ⽀持 Tag

• 预置大量探针
•• 缓存、类加载器、GC、CPU 利⽤率、线程池……

• 与 Spring 深度整合



**⽀持多种监控系统**
• Dimensional
•• AppOptics, Atlas, Azure Monitor, Cloudwatch, Datadog,
Datadog StatsD, Dynatrace, Elastic, Humio, Influx, KairosDB,
New Relic, Prometheus, SignalFx, Sysdig StatsD, Telegraf
StatsD, Wavefront


• Hierarchical
•• Graphite, Ganglia, JMX, Etsy StatsD





一些核心度量指标
核心接⼝
• Meter
内置实现
• Gauge, TimeGauge
• Timer, LongTaskTimer, FunctionTimer
• Counter, FunctionCounter
• DistributionSummary





Micrometer in Spring Boot 2.x
一些 URL
• /actuator/metrics
• /actuator/prometheus
一些配置项
• management.metrics.export.*
• management.metrics.tags.*
• management.metrics.enable.*
• management.metrics.distribution.*
• management.metrics.web.server.auto-time-requests   web请求的耗时监控

核心度量项
• JVM、CPU、⽂件句柄数、⽇志、启动时间
其他度量项
• Spring MVC、Spring WebFlux
• Tomcat、Jersey JAX-RS
• RestTemplate、WebClient
• 缓存、数据源、Hibernate
• Kafka、RabbitMQ



Datadog



⾃定义度量指标
• 通过 MeterRegistry 注册 Meter
• 提供 MeterBinder Bean 让 Spring Boot 自动绑定
• 通过 MeterFilter 进行定制



**78 - 通过 Spring Boot Admin 了解程序的运行状态**

Spring Boot Admin（后台管理Actuator监控）
⽬的
• 为 Spring Boot 应⽤程序提供一套管理界面
主要功能
• 集中展示应⽤程序 Actuator 相关的内容
• 变更通知



快速上⼿
服务端
• de.codecentric:spring-boot-admin-starter-server:2.1.3
• @EnableAdminServer
客户端
• de.codecentric:spring-boot-admin-starter-client:2.1.3
• 配置服务端及Endpoint
• spring.boot.admin.client.url=http://localhost:8080
• management.endpoints.web.exposure.include=*







安全控制
安全相关依赖
• spring-boot-starter-security
服务端配置
• spring.security.user.name
• spring.security.user.password



安全控制
客户端配置
• spring.boot.admin.client.username
• spring.boot.admin.client.password
• spring.boot.admin.client.instance.metadata.user.name
• spring.boot.admin.client.instance.metadata.user.password




**79 - 如何定制 Web 容器的运行参数**


内嵌 Web 容器
可选容器列表
• spring-boot-starter-tomcat
• spring-boot-starter-jetty
• spring-boot-starter-undertow
• spring-boot-starter-reactor-netty 


修改容器配置
端⼝
• server.port
• server.address
压缩
• server.compression.enabled
• server.compression.min-response-size
• server.compression.mime-types




Tomcat 特定配置
• server.tomcat.max-connections=10000
• server.tomcat.max-http-post-size=2MB
• server.tomcat.max-swallow-size=2MB
• server.tomcat.max-threads=200
• server.tomcat.min-spare-threads=10 




修改容器配置
错误处理
• server.error.path=/error
• server.error.include-exception=false
• server.error.include-stacktrace=never
• server.error.whitelabel.enabled=true
其他
• server.use-forward-headers
• server.servlet.session.timeout



修改容器配置
编程方式
• WebServerFactoryCustomizer<T>
• TomcatServletWebServerFactory
• JettyServletWebServerFactory
• UndertowServletWebServerFactory







**80 - 如何配置容器支持 HTTP-2（上）**

配置 HTTPS ⽀持
通过参数进行配置
• server.port=8443
• server.ssl.*
• server.ssl.key-store
• server.ssl.key-store-type，JKS或者PKCS12
• server.ssl.key-store-password=secret


⽣成证书⽂件
命令
• keytool -genkey -alias 别名
-storetype 仓库类型 -keyalg 算法 -keysize ⻓度
-keystore ⽂件名 -validity 有效期
说明
• 仓库类型，JKS、JCEKS、PKCS12 等
• 算法，RSA、DSA 等
• ⻓度，例如 2048


客户端 HTTPS ⽀持
配置 HttpClient （ >= 4.4 ）
• SSLContextBuilder 构造 SSLContext
• setSSLHostnameVerifier(new NoopHostnameVerifier())
配置 RequestFactory
• HttpComponentsClientHttpRequestFactory
• setHttpClient()




配置 HTTP/2 ⽀持
前提条件
• Java >= JDK 9
• Tomcat >= 9.0.0
• Spring Boot 不⽀持 h2c，需要先配置 SSL
配置项
• server.http2.enabled




客户端 HTTP/2 ⽀持
HTTP 库选择
• OkHttp（ com.squareup.okhttp3:okhttp:3.14.0 ）
• OkHttpClient
RestTemplate 配置
• OkHttp3ClientHttpRequestFactory






**82 - 如何编写命令行运行的程序**



关闭 Web 容器
控制依赖
• 不添加 Web 相关依赖
配置方式
• spring.main.web-application-type=none


编程方式
• SpringApplication
• setWebApplicationType()
• SpringApplicationBuilder
• web()
• 在调⽤ SpringApplication 的 run() 方法前
设置 WebApplicationType




常⽤⼯具类
不同的 Runner
• ApplicationRunner
• 参数是 ApplicationArguments
• CommandLineRunner
• 参数是 String[]
返回码
• ExitCodeGenerator

**83 - 了解可执行 Jar 背后的秘密**

了解可执行 Jar 背后的秘密


认识可执行 Jar
其中包含
• Jar 描述，META-INF/MANIFEST.MF
• Spring Boot Loader，org/springframework/boot/loader
• 项⽬内容，BOOT-INF/classes
• 项⽬依赖，BOOT-INF/lib
其中不包含
• JDK / JRE 



如何找到程序的⼊⼝
Jar 的启动类
• MANIFEST.MF
• Main-Class: org.springframework.boot.loader.JarLauncher
项⽬的主类
• @SpringApplication
• MANIFEST.MF
• Start-Class: xxx.yyy.zzz

再进一步：可直接运行的 Jar
如何创建可直接执行的 Jar
• 打包后的 Jar 可直接运行，⽆需 java 命令
• 可以在 .conf 的同名⽂件中配置参数


默认脚本中的一些配置项
配置项 说明 备注
CONF_FOLDER 放置 .conf 的⽬录位置 只能放环境变量中
JAVA_OPTS JVM 启动时的参数 ⽐如 JVM 的内存和 GC
RUN_ARGS 传给程序执行的参数







**84 - 如何将 Spring Boot 应用打包成 Docker 镜像文件**



什么是 Docker 镜像


• 镜像是静态的只读模板
• 镜像中包含构建 Docker 容器的指令
• 镜像是分层的
• 通过 Dockerfile 来创建镜像


Dockerfile
指令 作⽤ 格式举例
FROM 基于哪个镜像 FROM <image>[:<tag>] [AS <name>]
LABEL 设置标签 LABEL maintainer=“Geektime"
RUN 运行安装命令 RUN ["executable", "param1", "param2"]
CMD 容器启动时的命令 CMD ["executable","param1","param2"]
ENTRYPOINT 容器启动后的命令 ENTRYPOINT ["executable", "param1", "param2"]
VOLUME 挂载⽬录 VOLUME ["/data"]
EXPOSE 容器要监听的端⼝ EXPOSE <port> [<port>/<protocol>...]
ENV 设置环境变量 ENV <key> <value>
ADD 添加⽂件 ADD [--chown=<user>:<group>] <src>... <dest>
WORKDIR 设置运行的⼯作⽬录 WORKDIR /path/to/workdir
USER 设置运行的⽤户 USER <user>[:<group>]





通过 Maven 构建 Docker 镜像
准备⼯作
• 提供一个 Dockerfile
• 配置 dockerfile-maven-plugin 插件
执行构建
• mvn package
• mvn dockerfile:build
检查结果
• docker images






**86 - 简单理解微服务**


Spring Cloud 及 Cloud Native 概述


简单理解微服务
“微服务就是一些协同⼯作的⼩⽽⾃治的服务。”



微服务的优点
异构性• 语⾔、存储……

弹性• 一个组件不可⽤，不会导致级联故障

扩展• 单体服务不易扩展，多个较⼩的服务可以按需扩展


微服务的优点
• 易于部署
• 与组织结构对⻬
• 可组合性
• 可替代性


实施微服务的代价
• 没有银弹！！！
• 分布式系统的复杂性
• 开发、测试等诸多研发过程中的复杂性
• 部署、监控等诸多运维复杂性




**87 - 如何理解云原生(Cloud Native)**


“云原⽣技术有利于各组织在公有云、私有云和混合云等新型动态环境中，构建和运行可弹性扩展的应⽤。”


云原⽣应⽤要求……
DevOps(开发运维高品质软件服务)
• 开发与运维一同致⼒于交付⾼品质的软件服务于客户

持续交付
• 软件的构建、测试和发布，要更快、更频繁、更稳定

微服务
• 以一组⼩型服务的形式来部署应⽤

容器
• 提供⽐传统虚拟机更⾼的效率


Cloud Native Computing Foundation

DevOps(开发运维高品质软件服务)、持续交付(构建测试和发布)、微服务、容器(比VM更高效率)


**88 - 12-Factor App（上）**

云原生(Cloud Native) 的实践
12-Factor App
Factor App-云原生(Cloud Native) 的实践

THE TWELVE-FACTOR APP
⽬的
• 为构建 SaaS 应⽤提供行之有效的方法论
• 适⽤于任意语⾔和后端服务的开发的应⽤程序
• https://12factor.net/zh_cn/



了解 12-Factors
基准代码（Codebase）:• 一份基准代码，多份部署

依赖（Dependencies）:• 显式声明依赖关系

配置（Config）:• 在环境中存储配置

后端服务（Backing services）:• 把后端服务当作附加资源

 
构建，发布，运行（Build, release, run）:• 严格分离构建和运行

进程（Processes）:• 以一个或多个⽆状态进程运行应⽤

端⼝绑定（Port Binding）:• 通过端⼝绑定提供服务

并发（Concurrency）:• 通过进程模型进行扩展

 
易处理（Disposability）:• 快速启动和优雅终⽌可最大化健壮性

开发环境与线上环境等价（Dev / Prod parity）:• 尽可能的保持开发，预发布，线上环境相同

⽇志（Logs）:• 把⽇志当作事件流

管理进程（Admin processes）:• 后台管理任务当作一次性进程运行


一份基准代码，多份部署
• 使⽤版本控制系统加以管理
• 基准代码与应⽤保持一一对应的关系
• 尽管每个应⽤只对应一份基准代码，但可以同时存在多份部署



显式声明依赖关系
• 12-Factor 的应⽤程序不会隐式依赖系统级的类库
• 它一定通过依赖清单，确切地声明所有依赖项
• 在运行过程中，通过依赖隔离⼯具来确保程序不会调⽤系统中存
在但清单中未声明的依赖项



严格分离构建和运行
• 12-Facfor 应⽤严格区分构建、发布、运行三个步骤
• 部署⼯具通常都提供了发布管理⼯具
• 每一个发布版本必须对应一个唯一的发布 ID


以一个或多个⽆状态进程运行应⽤
• 12-Factor 应⽤的进程必须⽆状态且⽆共享
• 任何需要持久化的数据都要存储在后端服务内


快速启动和优雅终⽌可最大化健壮性
• 进程应当追求最⼩启动时间
• 进程一旦接收终⽌信号就会优雅的终⽌
• 进程应当在面对突然死亡时保持健壮


尽可能的保持开发，预发布，线上环境相同
• 想要做到持续部署就必须缩⼩本地与线上差异
• 后端服务是保持开发与线上等价的重要部分
• 应该反对在不同环境间使⽤不同的后端服务




**90 - 认识Spring Cloud的组成部分**


“Spring Cloud offers a simple and accessible programming
model to the most common distributed system patterns,
helping developers build resilient, reliable, and coordinated  applications.”



Spring Cloud 的主要功能
• 服务发现
• 服务熔断
• 配置服务
• 服务安全
• 服务⽹关
• 分布式消息
• 分布式跟踪
• 各种云平台⽀持


Spring Cloud 的版本号规则
• Spring Cloud 是个大⼯程，其中包含多个独⽴项⽬
• BOM - Release Train
• London Tube Stations
• 字⺟序排列
• Greenwich, Finchley, Edgware …
• SR - Service Release






**91 - 使用Eureka作为服务注册中心** 不建议学习和使用了
https://spring.io/guides/gs/service-registration-and-discovery/

认识 Eureka
什么是 Eureka
• Eureka 是在 AWS 上定位服务的 REST 服务
Netflix OSS
• https://netflix.github.io
Spring 对 Netflix 套件的⽀持
• Spring Cloud Netflix


在本地启动一个简单的 Eureka 服务
Starter
• spring-cloud-dependencies
• spring-cloud-starter-netflix-eureka-starter
声明
• @EnableEurekaServer
注意事项
• 默认端⼝8761
• Eureka ⾃⼰不要注册到 Eureka 了

将服务注册到 Eureka Server
Starter
• spring-cloud-starter-netflix-eureka-client
声明
• @EnableDiscoveryClient
• @EnableEurekaClient
一些配置项
• eureka.client.service-url.default-zone
• eureka.client.instance.prefer-ip-address


关于 Bootstrap 属性
Bootstrap 属性
• 启动引导阶段加载的属性
• bootstrap.properties | .yml
• spring.cloud.bootstrap.name=bootstrap
常⽤配置
• spring.application.name=应⽤名
• 配置中心相关












**92 - 使用Spring Cloud Loadbalancer访问服务**


如何获得服务地址
EurekaClient
• getNextServerFromEureka()

DiscoveryClient
• getInstances()


Spring Cloud Ribbon（负载均衡器）:



Load Balancer Client
RestTemplate 与 WebClient


• @LoadBalaced
• 实际是通过 ClientHttpRequestInterceptor 实现的
•• LoadBalancerInterceptor
•• LoadBalancerClient
••• RibbonLoadBalancerClient





**93 - 使用Feign访问服务** feign份





认识 Feign


Feign
• 声明式 REST Web 服务客户端
• https://github.com/OpenFeign/feign

Spring Cloud OpenFeign
• spring-cloud-starter-openfeign



Feign 的简单使⽤
开启 Feign ⽀持
• @EnableFeignClients

定义 Feign 接⼝
• @FeignClient


简单配置
• FeignClientsConfiguration
• Encoder / Decoder / Logger / Contract / Client …








Feign 的一些其他配置
• feign.okhttp.enabled=true
• feign.httpclient.enabled=true
• feign.compression.response.enabled=true
• feign.compression.request.enabled=true
• feign.compression.request.mime-types=
text/xml,application/xml,application/json
• feign.compression.request.min-request-size=2048









**94 - 深入理解服务发现背后的DiscoveryClient**




Spring Cloud Commons 提供的抽象
服务注册抽象
• 提供了 ServiceRegistry 抽象
客户发现抽象
• 提供了 DiscoveryClient 抽象
• @EnableDiscoveryClient
• 提供了 LoadBalancerClient 抽象



自动向 Eureka 服务端注册
ServiceRegistry
• EurekaServiceRegistry
• EurekaRegistration
自动配置
• EurekaClientAutoConfiguration
• EurekaAutoServiceRegistration
• SmartLifecycle




**95 - 使用Zookeeper作为服务注册中心**




认识 Zookeeper
Zookeeper
• A Distributed Coordination Service for Distributed Applications
• http://zookeeper.apache.org
设计⽬标
• 简单
• 多副本
• 有序
• 快


使⽤ Zookeeper 作为注册中心
Spring Cloud Zookeeper
• spring-cloud-starter-zookeeper-discovery
• Apache Curator
简单配置
• spring.cloud.zookeeper.connect-string=localhost:2181
提示
• 注意 Zookeeper 的版本
• 3.5.x 还是 Beta，但很多⼈在⽣产中使⽤它





使⽤ Zookeeper 作为注册中心的问题
两篇⽂章值得阅读
• 《阿⾥巴巴为什么不⽤ Zookeeper 做服务发现》
• 《Eureka! Why You Shouldn’t Use ZooKeeper for Service Discovery》

核心思想
• 在实践中，注册中心不能因为⾃身的任何原因破坏服务之间本身的可连通性
• 注册中心需要 AP，⽽ Zookeeper 是 CP
• CAP - 一致性、可⽤性、分区容忍性


通过 Docker 启动 Zookeeper
官方指引
• https://hub.docker.com/_/zookeeper
获取镜像
• docker pull zookeeper:3.5
运行 Zookeeper 镜像
• docker run --name zookeeper -p 2181:2181 -d zookeeper:3.5






**96 - 使用Consul作为服务注册中心**



“Consul is a distributed, highly available, and data center
aware solution to connect and configure applications across
dynamic, distributed infrastructure.”



https://github.com/hashicorp/consul



认识 HashiCorp Consul
Consul
• https://www.consul.io


关键特性
• 服务发现
• 健康检查
• KV 存储
• 多数据中心⽀持
• 安全的服务间通信



使⽤ Consul 提供服务发现能⼒
Consul 的能⼒
• Service registry, integrated health checks, and DNS and HTTP
interfaces enable any service to discover and be discovered by
other services


好⽤的功能
• HTTP API
• DNS（ xxx.service.consul ）
• 与 Nginx 联动，⽐如 ngx_http_consul_backend_module



使⽤ Consul 作为注册中心
Spring Cloud Consul
• spring-cloud-starter-consul-discovery
简单配置
• spring.cloud.consul.host=localhost
• spring.cloud.consul.port=8500
• spring.cloud.consul.discovery.prefer-ip-address=true





通过 Docker 启动 Consul
官方指引
• https://hub.docker.com/_/consul

获取镜像
• docker pull consul


运行 Consul 镜像
• docker run --name consul -d -p 8500:8500 -p 8600:8600/udp consul




**97 - 使用Nacos作为服务注册中心** 推荐 阿里巴巴开源

认识 Nacos(阿里巴巴)
Nacos
• 一个更易于构建云原⽣应⽤的动态服务发现、配置管理和服务管理平台。
• https://nacos.io/zh-cn/index.html

功能
• 动态服务配置  ：配置
• 服务发现和管理 ：服务发现
• 动态 DNS 服务

认识 Nacos



使⽤ Nacos 作为注册中心
Spring Cloud Alibaba
• spring-cloud-alibaba-dependencies
• spring-cloud-starter-alibaba-nacos-discovery
简单配置
• spring.cloud.nacos.discovery.server-addr





通过 Docker 启动 Nacos
官方指引
• https://hub.docker.com/r/nacos/nacos-server
获取镜像
• docker pull nacos/nacos-server
运行 Nacos 镜像
• docker run --name nacos -d -p 8848:8848 -e MODE=standalone
nacos/nacos-server
• ⽤户名密码为 nacos








**如何定制⾃⼰的 DiscoveryClient**



已经接触过的 Spring Cloud 类
DiscoveryClient
• EurekaDiscoveryClient
• ZookeeperDiscoveryClient
• ConsulDiscoveryClient
• NacosDiscoveryClient


LoadBalancerClient
• RibbonLoadBalancerClient





实现⾃⼰的 DiscoveryClient
需要做的：
• 返回该 DiscoveryClient 能提供的服务名列表
• 返回指定服务对应的 ServiceInstance 列表
• 返回 DiscoveryClient 的顺序
• 返回 HealthIndicator ⾥显示的描述




实现⾃⼰的 RibbonClient ⽀持
需要做的：
• 实现⾃⼰的 ServerList<T extends Server>
• Ribbon 提供了 AbstractServerList<T extends Server>
• 提供一个配置类，声明 ServerList Bean 实例







**99 - SpringBucks实战项目进度小结**



各种服务注册中心
• Eureka、Zookeeper、Consul、Nacos


**如何在服务间进行负载均衡**
• Ribbon(RestTemplat、WebClient)、OpenFeign


I.Spring Cloud Ribbon:是一个基于HTTP和TCP的客户端负载均衡工具 ，它基于Netflix Ribbon实现。通过Spring Cloud的封装
II.spring提供(RestTemplat(带有注解LoadBalanced) 
II.WebClient(spring提供)： reactive版的RestTemplate。


I.Feign(OpenFeign (Netflix 的产品))：
II.OpenFeign (Netflix 的产品)



Spring Cloud 的服务注册与发现机制
• ServiceRegistry、DiscoveryClient
• LoadBalancerClient





**100 - 使用Hystrix 实现服务熔断（上）**  官方不再支持

服务熔断

降级:保核心业务非核心不保:抛异常、返回NULL、调用Mock数据、调用Fallback处理逻辑

限流:超出请求返回错误





断路器模式


断路器
• Circuit Breaker pattern - Release It, Michael Nygard
• CircuitBreaker, Martin Fowler
• https://martinfowler.com/bliki/CircuitBreaker.html



**核心思想**
• 在断路器对象中封装受保护的方法调⽤
• 该对象监控调⽤和断路情况
• 调⽤失败触发阈值后，后续调⽤直接由断路器返回错误，不再执行实际调⽤

断路器
1.前几次服务之间调用都是超时
2.超过阈值以后，直接返回错误，不往后台再请求了。





Netflix Hystrix
• 实现了断路器模式
• @HystrixCommand
• fallbackMethod / commandProperties
• @HystrixProperty(name="execution.isolation.strategy",
value=“SEMAPHORE")
• https://github.com/Netflix/Hystrix/wiki/Configuration







Netflix Hystrix


**Spring Cloud ⽀持**
• spring-cloud-starter-netflix-hystrix
• @EnableCircuitBreaker



**Feign ⽀持**
• feign.hystrix.enabled=true

• @FeignClient
•• fallback / fallbackFactory










**102 - 如何观察服务熔断**

如何了解熔断的情况
打⽇志
• 在发⽣熔断时打印特定该⽇志



看监控
• 主动向监控系统埋点，上报熔断情况
• 提供与熔断相关的 Endpoint，让第三方系统来拉取信息



Hystrix Dashboard

Spring Cloud 为我们提供了

• Hystrix Metrics Stream
•• spring-boot-starter-actuator
••• /actuator/hystrix.stream


• Hystrix Dashboard

•• spring-cloud-starter-netflix-hystrix-dashboard

••• @EnableHystrixDashboard
••• /hystrix




聚合集群熔断信息
Spring Cloud 为我们提供了
• Netflix Turbine
• spring-cloud-starter-netflix-turbines
• @EnableTurbine
• /turbine.stream?cluster=集群名





**103 - 使用Resilience4j实现服务熔断**


Hystrix 以外的选择


Hystrix
• Netflix 停⽌维护，给了官方推荐

Resilience4j
• https://github.com/resilience4j/resilience4j
• 一款受 Hystrix 启发的轻量级且易于使⽤的容错库
• 针对 Java 8 与函数式编程设计


核心组件
组件名称 功能
resilience4j-circuitbreaker Circuit breaking
resilience4j-ratelimiter 频率控制
resilience4j-bulkhead 依赖隔离&负载保护
resilience4j-retry 自动重试
resilience4j-cache 应答缓存
resilience4j-timelimiter 超时控制


附加组件
组件名称 功能
resilience4j-reactor Spring Reactor ⽀持
resilience4j-micrometer Micrometer Metrics 输出
resilience4j-prometheus Prometheus Metrics 输出
resilience4j-spring-boot2 Spring Boot 2 Starter
resilience4j-feign Feign 适配器




断路器
实现
• 基于 ConcurrentHashMap 的内存断路器
• CircuitBreakerRegistry
• CircuitBreakerConfig


依赖
• resilience4j-spring-boot2
• resilience4j-circuitbreaker
• resilience4j-micrometer





断路器
注解方式
• @CircuitBreaker(name = "名称")
配置
• CircuitBreakerProperties
•• resilience4j.circuitbreaker.backends.名称
••• failure-rate-threshold
••• wait-duration-in-open-state




**104 - 使用Resilience4j实现服务限流（上）**


Bulkhead
⽬的
• 防⽌下游依赖被并发请求冲击
• 防⽌发⽣连环故障


⽤法
• BulkheadRegistry / BulkheadConfig
• @Bulkhead(name = "名称")



Bulkhead
配置
• BulkheadProperties
• resilience4j.bulkhead.backends.名称
• max-concurrent-call
• max-wait-time





RateLimiter
⽬的
• 限制特定时间段内的执行次数
⽤法
• RateLimiterRegistry / RateLimiterConfig
• @RateLimiter(name = "名称")


配置
• RateLimiterProperties
• resilience4j.ratelimiter.limiters.名称
• limit-for-period
• limit-refresh-period-in-millis
• timeout-in-millis









**106 - SpringBucks实战项目进度小结**





本章⼩结
⼏种模式
• 断路器 / 隔舱 / 速率限制器

两个⼯具
• Netflix Hystrix / Resilience4j
• 建议学习，Google Guava

观察与监控
• Hystrix Dashboard / Micrometer







**107 - 基于Git的配置中心（上）**




基于 Git 的配置中心




Spring Cloud Config Server
⽬的
• 提供针对外置配置的 HTTP API
依赖
• spring-cloud-config-server
•• @EnableConfigServer
•• ⽀持 Git / SVN / Vault / JDBC …


使⽤ Git 作为后端存储

配置
• MultipleJGitEnvironmentProperties
•• spring.cloud.config.server.git.uri


配置⽂件的要素
• {application}，即客户端的 spring.application.name
• {profile}，即客户端的 spring.profiles.active
• {label}，配置⽂件的特定标签，默认 master




使⽤ Git 作为后端存储
访问配置内容
• HTTP 请求
• GET /{application}/{profile}[/{label}]
• GET /{application}-{profile}.yml
• GET /{label}/{application}-{profile}.yml
• GET /{application}-{profile}.properties
• GET /{label}/{application}-{profile}.properties




Spring Cloud Config Client


依赖
• spring-cloud-starter-config



发现配置中心
• bootstrap.properties | yml
• spring.cloud.config.fail-fast=true
• 通过配置
• spring.cloud.config.uri=http://localhost:8888 




Spring Cloud Config Client


发现配置中心
• bootstrap.properties | yml
• 通过服务发现
• spring.cloud.config.discovery.enabled=true
• spring.cloud.config.discovery.service-id=configserver


配置刷新
• @RefreshScope
• Endpoint - /actuator/refresh






**109 - 基于Zookeeper的配置中心**  可以自动刷新




Spring Cloud Zookeeper Config


依赖
• spring-cloud-starter-zookeeper-config
• 注意 Zookeeper 版本


启⽤
• bootstrap.properties | yml
• spring.cloud.zookeeper.connect-string=localhost:2181
• spring.cloud.zookeeper.config.enabled=true




Zookeeper 中的数据怎么存


配置项
• /config/应⽤名,profile/key=value
• /config/application,profile/key=value


如何定制
• spring.cloud.zookeeper.config.root=config
• spring.cloud.zookeeper.config.default-context=application
• spring.cloud.zookeeper.config.profile-separator=','






110 - 深入理解Spring Cloud的配置抽象
深⼊理解 Spring Cloud 的配置抽象





Spring Cloud Config
⽬标
• 在分布式系统中，提供外置配置⽀持


实现
• 类似于 Spring 应⽤中的 Environment 与 PropertySource
• 在上下⽂中增加 Spring Cloud Config 的 PropertySource





Spring Cloud Config 的 PropertySource


**PropertySource:**
• Spring Cloud Config Client - CompositePropertySource
• Zookeeper - ZookeeperPropertySource
• Consul - ConsulPropertySource / ConsulFilesPropertySource


PropertySourceLocator
• 通过 PropertySourceLocator 提供 PropertySource


Spring Cloud Config Server


EnvironmentRepository
• Git / SVN / Vault / JDBC …


功能特性
• SSH、代理访问、配置加密 …


配置刷新
• /actuator/refresh
• Spring Cloud Bus - RefreshRemoteApplicationEvent



Spring Cloud Config Zookeeper


ZookeeperConfigBootstrapConfiguration
• 注册 ZookeeperPropertySourceLocator
• 提供 ZookeeperPropertySource



ZookeeperConfigAutoConfiguration
• 注册 ConfigWatcher


配置的组合顺序

以 yml 为例
• 应⽤名-profile.yml
• 应⽤名.yml
• application-profile.yml
• application.yml






**111 - 基于Consul的配置中心**

Spring Cloud Consul Config
依赖
• spring-cloud-starter-consul-config

启⽤
• bootstrap.properties | yml
• spring.cloud.consul.host=localhost
• spring.cloud.consul.port=8500
• spring.cloud.consul.config.enabled=true




Consul 中的数据怎么存
配置项
• spring.cloud.consul.config.format=KEY_VALUE | YAML | PROPERTIES | FILES


• /config/应⽤名,profile/data
• /config/application,profile/data





Consul 中的数据怎么存
如何定制
• spring.cloud.consul.config.data-key=data
• spring.cloud.consul.config.root=config
• spring.cloud.consul.config.default-context=application
• spring.cloud.consul.config.profile-separator=','




配置项变更
自动刷新配置
• spring.cloud.consul.config.watch.enabled=true
• spring.cloud.consul.config.watch.delay=1000

实现原理
• 单线程 ThreadPoolTaskScheduler
• ConsulConfigAutoConfiguration.CONFIG_WATCH_TASK_SCHEDULER_NAME








**112 - 基于Nacos的配置中心**


Spring Cloud Alibaba Nacos Config

依赖
• spring-cloud-starter-alibaba-nacos-config
• spring-cloud-alibaba-dependencies:0.9.0
• 注意 Spring Cloud 与 Spring Boot 的对应版本


启⽤
• bootstrap.properties | yml
• spring.cloud.nacos.config.server-addr=127.0.0.1:8848
• spring.cloud.nacos.config.enabled=true






Nacos 中的数据怎么存
配置项
• dataId
•• ${prefix}-${spring.profile.active}.${file-extension}
•• spring.cloud.nacos.config.prefix
•• spring.cloud.nacos.config.file-extension

• spring.cloud.nacos.config.group




本章⼩结
**⼏种不同的配置中心**
• Spring Cloud Config Server(springcloud bus自己广播修改)
•• Git / SVN / RDBMS / Vault ： 需要刷新自己的加东西

• Zookeeper (自己节点监听)
• Consul (定时刷新)
• Nacos (定时刷新)
• 携程 Apollo 配置中心


SpringBucks 进度⼩结
waiter-service
• 增加了订单⾦额与折扣
• 增加了 Waiter 名称
• 使⽤了不同的配置中心
• Spring Cloud Config Client
•• 使⽤ Zookeeper
•• 使⽤ Consul
•• 使⽤ Nacos




配置中心
携程 Apollo
官方地址
• https://github.com/ctripcorp/apollo

特性
• 统一管理不同环境、不同集群的配置
• 配置修改实时⽣效（热发布）
• 版本发布管理
• 灰度发布
• 权限管理、发布审核、操作审计
• 客户端配置信息监控
• 提供开放平台API







**114 - 认识Spring Cloud Stream** 消息中间件


认识 Spring Cloud Stream




Spring Cloud Stream 是什么
• 一款⽤于构建消息驱动的微服务应⽤程序的轻量级框架


**特性**
• 声明式编程模型

• 引⼊多种概念抽象
••  发布订阅、消费组、分区

• ⽀持多种消息中间件
•• RabbitMQ、Kafka 


**Spring Cloud Stream 的一些核心概念**


Binder
• RabbitMQ
• Apache Kafka
• Kafka Streams
• Amazon Kinesis
• RocketMQ


**I.Binding** 
应⽤中⽣产者、消费者与消息系统之间的桥梁

• @EnableBinding
• @Input / SubscribableChannel
• @Output / MessageChannel


**I.消费组**
• 对同一消息，每个组中都会有一个消费者收到消息


**I.如何发送与接收消息**


⽣产消息
• 使⽤ MessageChannel 中的 send()
• @SendTo

消费消息
• @StreamListener
• @Payload / @Headers / @Header

其他说明
• 可以使⽤ Spring Integration





**115 - 通过Spring Cloud Stream访问RabbitMQ**



“RabbitMQ is the most widely deployed open source message broker.”



**Spring Cloud Stream 对 RabbitMQ 的⽀持**

依赖
• Spring Cloud - spring-cloud-starter-stream-rabbit
• Spring Boot - spring-boot-starter-amqp


配置
• spring.cloud.stream.rabbit.binder.*
• spring.cloud.stream.rabbit.bindings.<channelName>.consumer.*
• spring.rabbitmq.*



通过 Docker 启动 RabbitMQ
官方指引
• https://hub.docker.com/_/rabbitmq

获取镜像
• docker pull rabbitmq
• docker pull rabbitmq:3.7-management

运行 RabbitMQ 镜像
• docker run --name rabbitmq -d -p 5672:5672 -p 15672:15672
-e RABBITMQ_DEFAULT_USER=spring -e RABBITMQ_DEFAULT_PASS=spring
rabbitmq:3.7-management


**116 - 通过Spring Cloud Stream访问Kafka**


认识 Apache Kafka
什么是 Kafka
• 诞⽣之初被⽤作消息队列，现已发展为强大的分布式事件流平台
• LinkedIn 在 2011 年开源


Spring Cloud Stream 对 Kafka 的⽀持

依赖
• Spring Cloud - spring-cloud-starter-stream-kafka

配置
• spring.cloud.stream.kafka.binder.*
• spring.cloud.stream.kafka.bindings.<channelName>.consumer.*
• spring.kafka.*




通过 Docker 启动 Kafka

官方指引
• https://hub.docker.com/r/confluentinc/cp-kafka
• https://docs.confluent.io/current/quickstart/cos-docker-quickstart.html

运行镜像
• https://github.com/confluentinc/cp-docker-images
•• kafka-single-node/docker-compose.yml

• docker-compose up -d




**Spring 中的定时任务**

Spring 的抽象
• TaskScheduler / Trigger / TriggerContext


配置定时任务
• @EnableScheduling
• <task:scheduler />
• @Scheduled




Spring 中的事件机制

Spring 中的事件
• ApplicationEvent

发送事件
• ApplicationEventPublisherAware
• ApplicationEventPublisher.publishEvent()


监听事件
• ApplicationListener<T>
• @EventListener

**117 - SpringBucks实战项目进度小结**



本章⼩结

Spring Cloud Stream
• Spring Cloud Stream 对消息的抽象
• 对不同中间件的⽀持
• RabbitMQ
• Apache Kafka



Spring 的一些机制
• 上下⽂中的事件机制
• 定时任务



SpringBucks 进度⼩结

waiter-service
• 增加⽀付功能
• 在⽀付后发送消息通知制作订单
• 接收订单完成通知

customer-service
• 增加⽀付功能
• 查询订单状态并取⾛咖啡

barista-service
• 等待通知制作订单











**118 - 通过Dapper理解链路治理**


通过 Dapper 理解链路治理



我们在关注什么？
• 系统中都有哪些服务
• 服务之间的依赖关系是什么样的
• 一个常⻅请求具体的执行路径是什么样的
• 请求每个环节的执行是否正常与耗时情况


Google Dapper 的一些术语
• Span - 基本的⼯作单元
• Trace - 由一组 Span 构成的树形结构
• Annotation - ⽤于及时记录事件
• cs - Client Sent
• sr - Server Received
• ss - Server Sent
• cr - Client Received





Google Dapper


**119 - 使用Spring Cloud Sleuth实现链路追踪**

通过 Spring Cloud Sleuth 实现链路追踪




Spring Cloud 提供的服务治理功能

依赖
• Spring Cloud Sleuth - spring-cloud-starter-sleuth
• Spring Cloud Sleuth with Zipkin - spring-cloud-starter-zipkin

⽇志输出
• [appname,traceId,spanId,exportable]







Spring Cloud 提供的服务治理功能
配置
• spring.zipkin.base-url=http://localhost:9411/
•• spring.zipkin.discovery-client-enabled=false
• spring.zipkin.sender.type=web | rabbit | kafka
• spring.zipkin.compression.enabled=false
• spring.sleuth.sampler.probability=0.1






通过 Docker 启动 Zipkin

官方指引
• https://hub.docker.com/r/openzipkin/zipkin
• https://github.com/openzipkin/docker-zipkin

获取镜像
• docker pull openzipkin/zipkin

运行 Zipkin 镜像
• docker run --name zipkin -d -p 9411:9411 openzipkin/zipkin






**120 - 如何追踪消息链路**


如何追踪消息链路



⽤ Spring Cloud Sleuth 追踪消息
依赖
• Spring Cloud Sleuth with Zipkin - spring-cloud-starter-zipkin
• 如需通过 MQ 埋点，需增加 RabbitMQ 或 Kafka 依赖


配置
• 如使⽤ HTTP 埋点，则与追踪 HTTP 服务完全一致
• spring.zipkin.sender.type=rabbit
• spring.zipkin.rabbitmq.queue=zipkin
• spring.rabbitmq.*






让 Zipkin 能通过 RabbitMQ 接收消息

环境变量
• RABBIT_ADDRESSES=<RabbitMQ地址>
• RABBIT_USER / RABBIT_PASSWORD
• https://github.com/apache/incubator-zipkin/tree/master/zipkin-collector/rabbitmq

运行 Zipkin 镜像
• docker run --name rabbit-zipkin -d -p 9411:9411
--link rabbitmq -e RABBIT_ADDRESSES=rabbitmq:5672
-e RABBIT_USER=spring -e RABBIT_PASSWORD=spring openzipkin/
zipkin



完整的应⽤链路


**121 - 除了链路还要治理什么**
除了链路还要追踪什么

服务治理关心什么才好

我们已经看过了
• 简单服务之间的依赖关系
• 一个请求的同步、异步链路

我们还需要关注
• 很多……很多……

“一个企业实施的⽤以保障事情正确完成的流程，即遵循最佳
实践，体系架构原则，治理条例，法律和其他决定因素。SOA
治理是指⽤于管理SOA的采⽤和实现的流程。”


**服务治理关心什么才好**

**宏观上**
• 架构设计是否合理
• 哪些链路算是关键链路
• 链路的容量⽔位趋势
• 对系统变更的管理与审计

**微观上**
• 一个系统都依赖了什么
• 一个系统都有哪些配置
• 一个系统的主观与客观质量



**122 - SpringBucks实战项目进度小结**

SpringBucks 实战项⽬进度⼩结


本章⼩结
Spring Cloud 的服务治理功能
• 借鉴⾃ Google Dapper

• Spring Cloud Sleuth
•• Zipkin
••• Web
••• RabbitMQ

• 我们应该关心更多


SpringBucks 进度⼩结
waiter-service / customer-service
• 增加基于 Web 向 Zipkin 埋点功能

barista-service
• 增加基于 MQ 向 Zipkin 埋点功能

最终的成品
• 通过 Docker 运行整个 SpringBucks







I.Spring cache缓存 本地缓存
https://blog.csdn.net/clementad/article/details/53009899

1、引入依赖： 
<!-- local cache -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
		<dependency>
			<groupId>com.github.ben-manes.caffeine</groupId>
			<artifactId>caffeine</artifactId>
		</dependency>


2、配置：
有两种方法：
- application.yml配置文件中配置：
    - 优点：简单
    - 缺点：无法针对每个cache配置不同的参数，比如过期时长、最大容量
- 配置类中配置
    - 优点：可以针对每个cache配置不同的参数，比如过期时长、最大容量
    - 缺点：要写一点代码
 



2.1、配置文件中直接配置：

    spring:
      cache:
        type: CAFFEINE
        cache-names:
          - getPersonById
          - name2
        caffeine:
          spec: maximumSize=500,expireAfterWrite=5s


spring:
cache:
type: CAFFEINE
cache-names:
- getIndexName
- getMainDataVersion
caffeine:
spec: maximumSize=500,expireAfterWrite=50s

  

然后还要在主类中加上@EnableCaching注解：

    @SpringBootApplication
    @EnableScheduling
    @EnableCaching
    public class MySpringBootApplication




2.2、另外一种更灵活的方法是在配置类中配置：

    package com.xjj.config;
     
    import java.util.ArrayList;
    import java.util.concurrent.TimeUnit;
     
    import org.springframework.cache.CacheManager;
    import org.springframework.cache.annotation.EnableCaching;
    import org.springframework.cache.caffeine.CaffeineCache;
    import org.springframework.cache.support.SimpleCacheManager;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.Primary;
     
    import com.github.benmanes.caffeine.cache.Caffeine;
     
     
    /**
     * Cache配置類，用于缓存数据
     * @author XuJijun
     *
     */
    @Configuration
    @EnableCaching
    public class CacheConfig {
     
    	public static final int DEFAULT_MAXSIZE = 50000;
    	public static final int DEFAULT_TTL = 10;
    	
    	/**
    	 * 定義cache名稱、超時時長（秒）、最大容量
    	 * 每个cache缺省：10秒超时、最多缓存50000条数据，需要修改可以在构造方法的参数中指定。
    	 */
    	public enum Caches{
    		getPersonById(5), //有效期5秒
    		getSomething, //缺省10秒
    		getOtherthing(300, 1000), //5分钟，最大容量1000
    		;
    		
    		Caches() {
    		}
     
    		Caches(int ttl) {
    			this.ttl = ttl;
    		}
     
    		Caches(int ttl, int maxSize) {
    			this.ttl = ttl;
    			this.maxSize = maxSize;
    		}
    		
    		private int maxSize=DEFAULT_MAXSIZE;	//最大數量
    		private int ttl=DEFAULT_TTL;		//过期时间（秒）
    		
    		public int getMaxSize() {
    			return maxSize;
    		}
    		public int getTtl() {
    			return ttl;
    		}
    	}
    	
    	/**
    	 * 创建基于Caffeine的Cache Manager
    	 * @return
    	 */
    	@Bean
    	@Primary
    	public CacheManager caffeineCacheManager() {
    		SimpleCacheManager cacheManager = new SimpleCacheManager();
    		
    		ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();
    		for(Caches c : Caches.values()){
    			caches.add(new CaffeineCache(c.name(), 
    				Caffeine.newBuilder().recordStats()
    				.expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
    				.maximumSize(c.getMaxSize())
    				.build())
    			);
    		}
    		
    		cacheManager.setCaches(caches);
     
    		return cacheManager;
    	}
    	
    }




3、代码中使用：

    package com.xjj.service;
     
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.cache.annotation.Cacheable;
    import org.springframework.stereotype.Service;
     
    import com.xjj.dao.PersonDAO;
    import com.xjj.entity.Person;
     
    @Service
    public class PersonService {
    	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    	
    	@Autowired
    	private PersonDAO personDao;
    	
     
    	/**
    	 * 根据id获取Person对象，使用缓存
    	 * @param id
    	 * @return Person对象
    	 */
    	@Cacheable(value="getPersonById", sync=true)
    	public Person getPersonById(int id){
    		logger.debug("getting data from database, personId={}", id);
    		return personDao.getPersonById(id);
    	}
    	
    }
 



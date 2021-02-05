https://www.jianshu.com/p/c59f5f353390
https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/production-ready-metrics.html




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


JdbcTemplat e批处理
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



JPA 为对象关系映射提供了⼀种基于 POJO 的持久化模型
简化数据持久化代码的开发⼯作
为 Java 社区屏蔽不同持久化 API 的差异




**Spring Data**

在保留底层存储特性的同时，提供相对⼀致的、基于 Spring 的编程模型


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


**Repository**

@EnableJpaRepositories 

Repository<T, ID> 接⼝
• CrudRepository<T, ID>
• PagingAndSortingRepository<T, ID>
• JpaRepository<T, ID>

**定义查询**

根据⽅法名定义查询
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



**接⼝中的⽅法是如何被解释的**
RepositoryFactorySupport.getRepository 添加了Advice
• DefaultMethodInvokingMethodInterceptor
• QueryExecutorMethodInterceptor
AbstractJpaQuery.execute 执⾏具体的查询
语法解析在 Part 中




**通过 MyBatis 操作数据库**







**20 - 通过MyBatis操作数据库**


认识 MyBatis
MyBatis（https://github.com/mybatis/mybatis-3）
• ⼀款优秀的持久层框架
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


**运⾏ MyBatis Generator**
**I.命令⾏   我自己用的方式**
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
• ⽀持多种分⻚⽅式
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
• -d，后台运⾏容器
• -e，设置环境变量
• --expose / -p 宿主端⼝:容器端⼝
• --name，指定容器名称
• --link，链接不同容器
• -v 宿主⽬录:容器⽬录，挂载磁盘卷



**国内 Docker 镜像配置**


官⽅ Docker Hub
• https://hub.docker.com
官⽅镜像
• 镜像 https://www.docker-cn.com/registry-mirror
• 下载 https://www.docker-cn.com/get-docker
阿⾥云镜像
• https://dev.aliyun.com 





通过 Docker 启动 MongoDB
官⽅指引
• https://hub.docker.com/_/mongo
获取镜像
• docker pull mongo
运⾏ MongoDB 镜像
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

MongoDB 是⼀款开源的⽂档型数据库
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


Redis 是⼀款开源的内存 KV 存储，⽀持多种数据结构
• https://redis.io
Spring 对 Redis 的⽀持
• Spring Data Redis
• ⽀持的客户端 Jedis / Lettuce
• RedisTemplate
• Repository ⽀持


Jedis 客户端的简单使⽤
• Jedis 不是线程安全的
• 通过 JedisPool 获得 Jedis 实例
• 直接使⽤ Jedis 中的⽅法



**通过 Docker 启动 Redis**

官⽅指引
• https://hub.docker.com/_/redis
获取镜像
• docker pull redis
启动 Redis
• docker run --name redis -d -p 6379:6379 redis


**27 - Redis 的哨兵与集群模式**
Redis 的哨兵与集群模式



**Redis 的哨兵模式**
Redis Sentinel 是 Redis 的⼀种⾼可⽤⽅案
• 监控、通知、⾃动故障转移、服务发现


JedisSentinelPool 


**Redis 的集群模式**

Redis Cluster
• 数据⾃动分⽚（分成16384个 Hash Slot ）
• 在部分节点失效时有⼀定可⽤性
JedisCluster
• Jedis 只从 Master 读数据，如果想要⾃动读写分离，可以定制


了解 Spring 的缓存抽象




**28 - 了解 Spring 的缓存抽象**



为不同的缓存提供⼀层抽象
• 为 Java ⽅法增加缓存，缓存执⾏结果
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


33 - 通过 Reactive 的方式访问 Redis

Spring Data Redis


Lettuce 能够⽀持 Reactive ⽅式
Spring Data Redis 中主要的⽀持
• ReactiveRedisConnection
• ReactiveRedisConnectionFactory
• ReactiveRedisTemplate
• opsForXxx()

通过 Reactive 的⽅式访问数据
MongoDB

Spring Data MongoDB
MongoDB 官⽅提供了⽀持 Reactive 的驱动
• mongodb-driver-reactivestreams
Spring Data MongoDB 中主要的⽀持
• ReactiveMongoClientFactoryBean
• ReactiveMongoDatabaseFactory
• ReactiveMongoTemplate



通过 Reactive 的⽅式访问数据
RDBMS

R2DBC （https://spring.io/projects/spring-data-r2dbc）
• Reactive Relational Database Connectivity
⽀持的数据库
• Postgres（io.r2dbc:r2dbc-postgresql）
• H2（io.r2dbc:r2dbc-h2）
• Microsoft SQL Server（io.r2dbc:r2dbc-mssql）


⼀些主要的类
• ConnectionFactory
• DatabaseClient
• execute().sql(SQL)
• inTransaction(db -> {})
• R2dbcExceptionTranslator
• SqlErrorCodeR2dbcExceptionTranslator



⼀些主要的类
• @EnableR2dbcRepositories
• ReactiveCrudRepository<T, ID>
• @Table / @Id
• 其中的⽅法返回都是 Mono 或者 Flux
• ⾃定义查询需要⾃⼰写 @Query


**36 - 通过 AOP 打印数据访问层的摘要（上）**


通过 AOP 打印数据访问层摘要

**Spring AOP 的⼀些核⼼概念**
概念 含义
Aspect 切⾯
Join Point 连接点，Spring AOP⾥总是代表⼀次⽅法执⾏
Advice 通知，在连接点执⾏的动作
Pointcut 切⼊点，说明如何匹配连接点
Introduction 引⼊，为现有类型声明额外的⽅法和属性
Target object ⽬标对象
AOP proxy AOP 代理对象，可以是 JDK 动态代理，也可以是 CGLIB 代理
Weaving 织⼊，连接切⾯与⽬标对象或类型创建代理的过程



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
• https://github.com/alibaba/druid/wiki/Druid中使⽤log4j2进⾏⽇志输出







**39 - 编写第一个 Spring MVC Controller**



**认识 Spring MVC**
DispatcherServlet(所有请求的入口)
I.Controller

II.xxxResolver
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



理解 Spring 的应⽤上下⽂


**关于上下⽂常⽤的接⼝**
I.  BeanFactory
II.DefaultListableBeanFactory

I. ApplicationContext
II.ClassPathXmlApplicationContext
II.FileSystemXmlApplicationContext
II.AnnotationConfigApplicationContext

I. WebApplicationContext




**41 - 理解请求的处理机制**



⼀个请求的⼤致处理流程
绑定⼀些 Attribute
• WebApplicationContext / LocaleResolver / ThemeResolver
处理 Multipart
• 如果是，则将请求转为 MultipartHttpServletRequest
Handler 处理
• 如果找到对应 Handler，执⾏ Controller 及前后置处理器逻辑
处理返回的 Model ，呈现视图





如何定义处理⽅法


定义映射关系
@Controller
@RequestMapping
• path / method 指定映射路径与⽅法
• params / headers 限定映射范围
• consumes / produces 限定请求与响应格式
⼀些快捷⽅式
• @RestController
• @GetMapping / @PostMapping / @PutMapping / @DeleteMapping / @PatchMapping


定义处理⽅法
• @RequestBody / @ResponseBody / @ResponseStatus
• @PathVariable / @RequestParam / @RequestHeader
• HttpEntity / ResponseEntity


定义处理⽅法
详细参数
• https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-frameworkreference/web.html#mvc-ann-arguments
详细返回
• https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-frameworkreference/web.html#mvc-ann-return-types




42 - 如何定义处理方法（上）

定义类型转换
⾃⼰实现 WebMvcConfigurer
• Spring Boot 在 WebMvcAutoConfiguration 中实现了⼀个
• 添加⾃定义的 Converter
• 添加⾃定义的 Formatter





定义校验
• 通过 Validator 对绑定结果进⾏校验
• Hibernate Validator
• @Valid 注解
• BindingResult 



Multipart 上传
• 配置 MultipartResolver
• Spring Boot ⾃动配置 MultipartAutoConfiguration
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
• redirect:
• forward:


**46 - Spring MVC 中的常用视图（上）**  JSP 等展示
SpringBoot支持如下页面模板语言

    Thymeleaf
    FreeMarker
    Velocity
    Groovy
    JSP



Spring MVC ⽀持的视图
⽀持的视图列表
• https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-frameworkreference/web.html#mvc-view
• Jackson-based JSON / XML
• Thymeleaf & FreeMarker


配置 MessageConverter
• 通过 WebMvcConfigurer 的 configureMessageConverters()
• Spring Boot ⾃动查找 HttpMessageConverters 进⾏注

Spring Boot 对 Jackson 的⽀持
• JacksonAutoConfiguration
• Spring Boot 通过 @JsonComponent 注册 JSON 序列化组件
• Jackson2ObjectMapperBuilderCustomizer
• JacksonHttpMessageConvertersConfiguration
• 增加 jackson-dataformat-xml 以⽀持 XML 序列化




使⽤ Thymeleaf
添加 Thymeleaf 依赖
• org.springframework.boot:spring-boot-starter-thymeleaf
Spring Boot 的⾃动配置
• ThymeleafAutoConfiguration
• ThymeleafViewResolver



Thymeleaf 的⼀些默认配置
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
核⼼逻辑
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
核⼼接⼝
• HandlerExceptionResolver
实现类
• SimpleMappingExceptionResolver
• DefaultHandlerExceptionResolver
• ResponseStatusExceptionResolver
• ExceptionHandlerExceptionResolver




异常处理⽅法
处理⽅法
• @ExceptionHandler
添加位置
• @Controller / @RestController
• @ControllerAdvice / @RestControllerAdvice



**50 - 了解 Spring MVC 的切入点**




Spring MVC 的拦截器
核⼼接⼝
HandlerInteceptor
• boolean preHandle()
• void postHandle()
• void afterCompletion()




针对 @ResponseBody 和 ResponseEntity 的情况
• ResponseBodyAdvice
针对异步请求的接⼝
• AsyncHandlerInterceptor
• void afterConcurrentHandlingStarted()




常规⽅法
• WebMvcConfigurer.addInterceptors()
Spring Boot 中的配置
• 创建⼀个带 @Configuration 的 WebMvcConfigurer 配置类
• 不能带 @EnableWebMvc（想彻底⾃⼰控制 MVC 配置除外）











**53 - 通过 RestTemplate 访问 Web 资源**


Spring Boot 中的 RestTemplate
• Spring Boot 中没有⾃动配置 RestTemplate
• Spring Boot 提供了 RestTemplateBuilder
• RestTemplateBuilder.build()


常⽤⽅法
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
• ⼀个以 Reactive ⽅式处理 HTTP 请求的⾮阻塞式的客户端
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


“REST提供了⼀组架构约束，当作为⼀个整体来应⽤时，强调组件交互的
可伸缩性、接⼝的通⽤性、组件的独⽴部署、以及⽤来减少交互延迟、增
强安全性、封装遗留系统的中间组件。”



如何实现 Restful Web Service
• 识别资源
• 选择合适的资源粒度
• 设计 URI
• 选择合适的 HTTP ⽅法和返回码
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
• 使⽤域及⼦域对资源进⾏合理的分组或划分
• 在 URI 的路径部分使⽤斜杠分隔符 ( / ) 来表示资源之间的层次关系
• 在 URI 的路径部分使⽤逗号 ( , ) 和分号 ( ; ) 来表示⾮层次元素
• 使⽤连字符 ( - ) 和下划线 ( _ ) 来改善⻓路径中名称的可读性
• 在 URI 的查询部分使⽤“与”符号 ( & ) 来分隔参数
• 在 URI 中避免出现⽂件扩展名 ( 例如 .php，.aspx 和 .jsp )




认识 HTTP ⽅法
动作 安全/幂等 ⽤途
GET Y / Y 信息获取
POST N / N 该⽅法⽤途⼴泛，可⽤于创建、更新或⼀次性对多个资源进⾏修改
DELETE N / Y 删除资源
PUT N / Y 更新或者完全替换⼀个资源
HEAD Y / Y 获取与GET⼀样的HTTP头信息，但没有响应体
OPTIONS Y / Y 获取资源⽀持的HTTP⽅法列表
TRACE Y / Y 让服务器返回其收到的HTTP头








URI 与 HTTP ⽅法的组合
URI HTTP⽅法 含义
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
• REST 统⼀接⼝的必要组成部分


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
edit 指向⼀个可以编辑当前资源的链接
collection 如果当前资源包含在某个集合中，指向该集合的链接
search 指向⼀个可以搜索当前资源与其相关资源的链接
related 指向⼀个与当前资源相关的链接
first 集合遍历相关的类型，指向第⼀个资源的链接
last 集合遍历相关的类型，指向最后⼀个资源的链接
previous 集合遍历相关的类型，指向上⼀个资源的链接
next 集合遍历相关的类型，指向下⼀个资源的链接




**61 - 使用 Spring Data REST 实现简单的超媒体服务（上）**


使⽤ Spring Data REST 实现简单的超媒体服务


认识 HAL
HAL
• Hypertext Application Language
• HAL 是⼀种简单的格式，为 API 中的资源提供简单⼀致的链接
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


常⻅的会话解决⽅案
• 粘性会话 Sticky Session    ：永远请求到一台机器
• 会话复制 Session Replication ：会话复制 每台都有全量（数据同步问题）
• 集中会话 Centralized Session  ：JDBC redis集中存储会话信息





**认识 Spring Session**
Spring Session (集中会话 Centralized Session)
• 简化集群中的⽤户会话管理
• ⽆需绑定容器特定解决⽅案
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
• 基于 Reactive Streams API ，运⾏在⾮阻塞服务器上
为什么会有 WebFlux
• 对于⾮阻塞 Web 应⽤的需要
• 函数式编程




认识 WebFlux
关于 WebFlux 的性能
• 请求的耗时并不会有很⼤的改善
• 仅需少量固定数量的线程和较少的内存即可实现扩展




WebMVC v.s. WebFlux
• 已有 Spring MVC 应⽤，运⾏正常，就别改了
• 依赖了⼤量阻塞式持久化 API 和⽹络 API，建议使⽤ Spring MVC
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

认识 Spring Boot 的组成部分




Spring Boot 不是什么
• 不是应⽤服务器
• 不是 Java EE 之类的规范
• 不是代码⽣成器
• 不是 Spring Framework 的升级版



Spring Boot 的特性
• ⽅便地创建可独⽴运⾏的 Spring 应⽤程序
• 直接内嵌 Tomcat、Jetty 或 Undertow
• 简化了项⽬的构建配置
• 为 Spring 及第三⽅库提供⾃动配置
• 提供⽣产级特性
• ⽆需⽣成代码或进⾏ XML 配置




**Spring Boot 的四⼤核⼼**
• ⾃动配置 - Auto Configuration   
• 起步依赖 - Starter Dependency  
• 命令⾏界⾯ - Spring Boot CLI   
• Actuator 运行时生产特性




**68 - 了解自动配置的实现原理**
了解⾃动配置的实现原理

了解⾃动配置


⾃动配置
• 基于添加的 JAR 依赖⾃动对 Spring Boot 应⽤程序进⾏配置
• spring-boot-autoconfiguration
开启⾃动配置
• @EnableAutoConfiguration
••  exclude = Class<?>[]
• @SpringBootApplication





⾃动配置的实现原理
@EnableAutoConfiguration
• AutoConfigurationImportSelector
• META-INF/spring.factories
• org.springframework.boot.autoconfigure.EnableAutoConfiguration


⾃动配置的实现原理
条件注解
• @Conditional
• @ConditionalOnClass
• @ConditionalOnBean
• @ConditionalOnMissingBean
• @ConditionalOnProperty



了解⾃动配置的情况
观察⾃动配置的判断结果
• --debug
ConditionEvaluationReportLoggingListener
• Positive matches
• Negative matches
• Exclusions
• Unconditional classes



69 - 动手实现自己的自动配置

动⼿实现⾃⼰的⾃动配置

主要⼯作内容
编写 Java Config
• @Configuration
添加条件
• @Conditional
定位⾃动配置
• META-INF/spring.factories



条件注解⼤家庭
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


执⾏顺序
• @AutoConfigureBefore
• @AutoConfigureAfter
• @AutoConfigureOrder






70 - 如何在低版本 Spring 中快速实现类似自动配置的功能


需求与问题
核⼼的诉求
• 现存系统，不打算重构
• Spring 版本3.x，不打算升级版本和引⼊ Spring Boot
• 期望能够在少改代码的前提下实现⼀些功能增强
⾯临的问题
• 3.x 的 Spring 没有条件注解
• ⽆法⾃动定位需要加载的⾃动配置







核⼼解决思路
条件判断
• 通过 BeanFactoryPostProcessor 进⾏判断
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
• Java Config 中需要定义为 static ⽅法




关于 Bean 的⼀些定制
Lifecycle Callback
• InitializingBean / @PostConstruct / init-method
• DisposableBean / @PreDestroy / destroy-method
XxxAware 接⼝
• ApplicationContextAware
• BeanFactoryAware
• BeanNameAware



⼀些常⽤操作
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
• 要实现⼀个功能，需要引⼊哪些依赖
• 多个依赖项⽬之间是否会有兼容问题




关于 Maven 依赖管理的⼀些⼩技巧
了解你的依赖
• mvn dependency:tree
• IDEA Maven Helper 插件
排除特定依赖
• exclusion
统⼀管理依赖
• dependencyManagement
• Bill of Materials - bom




Spring Boot 的起步依赖
Starter Dependencies 依靠，依赖; 附属国; 附属地
• 直接⾯向功能
• ⼀站获得所有相关依赖，不再复制粘贴
官⽅的 Starters
• spring-boot-starter-* 

https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-running-with-the-maven-plugin
1.5. Starters







**72 - 定制自己的起步依赖**



你的 Starter
主要内容
• autoconfigure 模块，包含⾃动配置代码
• starter 模块，包含指向⾃动配置模块的依赖及其他相关依赖
命名⽅式
• xxx-spring-boot-autoconfigure
• xxx-spring-boot-starter


⼀些注意事项
• 不要使⽤ spring-boot 作为依赖的前缀
• 不要使⽤ spring-boot 的配置命名空间
• starter 中仅添加必要的依赖
• 声明对 spring-boot-starter 的依赖





**73 - 深挖 Spring Boot 的配置加载机制**





外化配置加载顺序
• 开启 DevTools 时，~/.spring-boot-devtools.properties
• 测试类上的 @TestPropertySource 注解
• @SpringBootTest#properties 属性
• 命令⾏参数（ --server.port=9000 ）
• SPRING_APPLICATION_JSON 中的属性

外化配置加载顺序
 
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



外化配置加载顺序
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
短划线分隔
Properties ⽂件
YAML ⽂件
系统属性
geektime.spring-boot.first-demo
驼峰式 geektime.springBoot.firstDemo
下划线分隔 geektime.spring_boot.first_demo
全⼤写，下划线分隔 环境变量 GEEKTIME_SPRINGBOOT_FIRSTDEMO







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


运⾏中的 Spring Boot


认识 Spring Boot 的各类 Actuator Endpoint


Actuator
⽬的
• 监控并管理应⽤程序
访问⽅式
• HTTP
• JMX
依赖
• spring-boot-starter-actuator




⼀些常⽤ Endpoint
ID 说明 默认开启 默认 HTTP 默认 JMX
beans 显示容器中的 Bean 列表 Y N Y
caches 显示应⽤中的缓存 Y N Y
conditions 显示配置条件的计算情况 Y N Y
configprops 显示 @ConfigurationProperties 的信息 Y N Y
env 显示 ConfigurableEnvironment 中的属性 Y N Y
health 显示健康检查信息 Y Y Y
httptrace 显示 HTTP Trace 信息 Y N Y
info 显示设置好的应⽤信息 Y Y Y






⼀些常⽤ Endpoint
ID 说明 默认开启 默认 HTTP 默认 JMX
loggers 显示并更新⽇志配置 Y N Y
metrics 显示应⽤的度量信息 Y N Y
mappings 显示所有的 @RequestMapping 信息 Y N Y
scheduledtasks 显示应⽤的调度任务信息 Y N Y
shutdown 优雅地关闭应⽤程序 N N Y
threaddump 执⾏ Thread Dump Y N Y
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
• 检查应⽤程序的运⾏状态
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
⽅法
• 实现 HealthIndicator 接⼝
• 根据⾃定义检查逻辑返回对应 Health 状态
• Health 中包含状态和详细描述信息



**77 - 通过 Micrometer 获取运行数据**
https://docs.spring.io/spring-boot/docs/2.1.12.RELEASE/reference/html/production-ready-metrics.html


为主流监控系统提供探针等功能  

认识 Micrometer
特性

• 多维度度量
•• ⽀持 Tag

• 预置⼤量探针
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





⼀些核⼼度量指标
核⼼接⼝
• Meter
内置实现
• Gauge, TimeGauge
• Timer, LongTaskTimer, FunctionTimer
• Counter, FunctionCounter
• DistributionSummary





Micrometer in Spring Boot 2.x
⼀些 URL
• /actuator/metrics
• /actuator/prometheus
⼀些配置项
• management.metrics.export.*
• management.metrics.tags.*
• management.metrics.enable.*
• management.metrics.distribution.*
• management.metrics.web.server.auto-time-requests   web请求的耗时监控

核⼼度量项
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
• 提供 MeterBinder Bean 让 Spring Boot ⾃动绑定
• 通过 MeterFilter 进⾏定制



**78 - 通过 Spring Boot Admin 了解程序的运行状态**

Spring Boot Admin
⽬的
• 为 Spring Boot 应⽤程序提供⼀套管理界⾯
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
编程⽅式
• WebServerFactoryCustomizer<T>
• TomcatServletWebServerFactory
• JettyServletWebServerFactory
• UndertowServletWebServerFactory







**80 - 如何配置容器支持 HTTP-2（上）**

配置 HTTPS ⽀持
通过参数进⾏配置
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
配置⽅式
• spring.main.web-application-type=none


编程⽅式
• SpringApplication
• setWebApplicationType()
• SpringApplicationBuilder
• web()
• 在调⽤ SpringApplication 的 run() ⽅法前
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

了解可执⾏ Jar 背后的秘密


认识可执⾏ Jar
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

再进⼀步：可直接运⾏的 Jar
如何创建可直接执⾏的 Jar
• 打包后的 Jar 可直接运⾏，⽆需 java 命令
• 可以在 .conf 的同名⽂件中配置参数


默认脚本中的⼀些配置项
配置项 说明 备注
CONF_FOLDER 放置 .conf 的⽬录位置 只能放环境变量中
JAVA_OPTS JVM 启动时的参数 ⽐如 JVM 的内存和 GC
RUN_ARGS 传给程序执⾏的参数







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
RUN 运⾏安装命令 RUN ["executable", "param1", "param2"]
CMD 容器启动时的命令 CMD ["executable","param1","param2"]
ENTRYPOINT 容器启动后的命令 ENTRYPOINT ["executable", "param1", "param2"]
VOLUME 挂载⽬录 VOLUME ["/data"]
EXPOSE 容器要监听的端⼝ EXPOSE <port> [<port>/<protocol>...]
ENV 设置环境变量 ENV <key> <value>
ADD 添加⽂件 ADD [--chown=<user>:<group>] <src>... <dest>
WORKDIR 设置运⾏的⼯作⽬录 WORKDIR /path/to/workdir
USER 设置运⾏的⽤户 USER <user>[:<group>]





通过 Maven 构建 Docker 镜像
准备⼯作
• 提供⼀个 Dockerfile
• 配置 dockerfile-maven-plugin 插件
执⾏构建
• mvn package
• mvn dockerfile:build
检查结果
• docker images






**86 - 简单理解微服务**










https://www.jianshu.com/p/c59f5f353390


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
















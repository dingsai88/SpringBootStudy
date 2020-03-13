


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














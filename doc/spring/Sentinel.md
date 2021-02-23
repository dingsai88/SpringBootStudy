

**I.网关**

http://gitlab.creditease.corp/fund-inner/gateway

spring-cloud-dependencies 版本列表可查看：
https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies


spring-boot-starter-parent 版本列表可查看：
https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent

https://mvnrepository.com/artifact/com.alibaba.cloud/spring-cloud-starter-alibaba-sentinel


https://start.spring.io/actuator/info

https://sentinelguard.io/zh-cn/docs/quick-start.html


https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel






**I.spring-cloud-alibaba**
:Spring Cloud Starter Alibaba Sentinel 功能版本说明  ：spring-cloud-starter-alibaba-sentinel对应版本信息

spring-cloud-alibaba:Nacos、sentinel、rocketmq、dubbo、seata版本对应关系:springboot springcloud和 sentinel对应关系

https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E
https://github.com/alibaba/spring-cloud-alibaba/wiki/版本说明

II.一共两部分：

	<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>2.1.2.RELEASE</version>
		</dependency>


		<dependency>
			<groupId>com.alibaba.csp</groupId>
			<artifactId>sentinel-dubbo-adapter</artifactId>
			<version>1.8.0</version>
		</dependency>




Spring Cloud Greenwich
如果需要使用 Spring Cloud Greenwich 版本，请在 dependencyManagement 中添加如下内容

https://github.com/alibaba/spring-cloud-alibaba/wiki/版本说明
Spring Cloud Greenwich=== Spring Cloud Alibaba Version(2.1.2.RELEASE) ======Spring Boot Version(2.1.X.RELEASE)



	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>com.alibaba.cloud</groupId>
				<artifactId>spring-cloud-alibaba-dependencies</artifactId>
				<version>2.1.3.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>






**II.org.springframework.cloud**

https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-alibaba-sentinel

org\springframework\cloud\spring-cloud-alibaba-dependencies

springboot对应的sentinel版本信息在:spring-cloud-alibaba-dependencies





F:\DingSaiWealthYiXin\Bank2020\bankfinance\bank-finance\bank-finance\bank-finance
mvn dependency:tree



---------------------------------------服务端开始-----------------------------------------------------------

**I.启动sentinel 1.8.1**


cd D:\jar

java -Dserver.port=1111 -Dcsp.sentinel.dashboard.server=localhost:1111 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.1.jar


http://127.0.0.1:1111/#/login

user:sentinel
pass:sentinel

日志路径：
C:\Users\Administrator.CE-20160511RDFS\logs\csp





----------------------------------------客户端开始----------------------------------------------------------
I.客户端增加org.springframework.cloud
默认为 Sentinel 整合了 Servlet、RestTemplate、FeignClient 和 Spring WebFlux

  		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>0.2.2.RELEASE</version>
		</dependency>


对于 Dubbo 2.6.x 及以下版本，使用时需引入以下模块（以 Maven 为例）：
<dependency>
<groupId>com.alibaba.csp</groupId>
<artifactId>sentinel-dubbo-adapter</artifactId>
<version>1.5.2</version>
</dependency>



对于 Apache Dubbo 2.7.x 及以上版本，使用时需引入以下模块（以 Maven 为例）：
<dependency>
<groupId>com.alibaba.csp</groupId>
<artifactId>sentinel-apache-dubbo-adapter</artifactId>
<version>1.5.2</version>
</dependency>





II.客户端增加com.alibaba.cloud
:Spring Cloud Starter Alibaba Sentinel


	<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>2.1.2.RELEASE</version>
		</dependency>


		<dependency>
			<groupId>com.alibaba.csp</groupId>
			<artifactId>sentinel-dubbo-adapter</artifactId>
			<version>1.8.0</version>
		</dependency>



Spring Cloud Greenwich=== Spring Cloud Alibaba Version(2.1.2.RELEASE) ======Spring Boot Version(2.1.X.RELEASE)



	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>com.alibaba.cloud</groupId>
				<artifactId>spring-cloud-alibaba-dependencies</artifactId>
				<version>2.1.3.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>






II.客户端-项目-增加配置

1.
spring.cloud.sentinel.transport.dashboard=127.0.0.1:1111

2.
spring:
cloud:
sentinel:
transport:
dashboard:127.0.0.1:1111


3.或者命令行启动

-Dspring.cloud.config.watcher.enabled=false -Dspring.cloud.zookeeper.config.root=/aa -Dspring.application.name=aa-config  -Dspring.cloud.config.enabled=true  -Dspring.cloud.zookeeper.enabled=true  -Dspring.cloud.zookeeper.connectString=zzzz2181 -Dspring.cloud.sentinel.transport.dashboard=127.0.0.1:1111

-Dspring.cloud.sentinel.transport.dashboard=127.0.0.1:1111


-Dcsp.sentinel.dashboard.server=127.0.0.1:1111



-----------------------------------------------结束-------------------------------------------------------




spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://xxxxxx:3306/xxxxx?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
spring.datasource.username=xxxxxxx
spring.datasource.password=xxxx





C:\Users\Administrator.CE-20160511RDFS\.IntelliJIdea2016.1\config\extensions\com.intellij.database\schema\Generate POJOs.clj

SpringDataJPA_POJO.groovy




E:\DingSai\DingProjectAs_new\SpringBootStudy



F:\DingSaiWealthYiXin\Bank2020\bankfinance



I.docker 安装
安装centos 8

mirrors.aliyun.com/centos/8/isos/x86_64/CentOS-8.3.2011-x86_64-boot.iso


选择的安装源头：
mirrors.aliyun.com/centos/8/BaseOS/x86_64/os





 


sentinel-core-1.5.2



------------------------------------------------------
I.springCloud容器 这两个版本对应的客户端是 1.5.2

	    <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>0.2.2.RELEASE</version>
		</dependency>
		
		
				<dependency>
			<groupId>com.alibaba.csp</groupId>
			<artifactId>sentinel-dubbo-adapter</artifactId>
			<version>1.5.2</version>
		</dependency>

sentinel-core-1.5.2


 	<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>0.9.0.RELEASE</version>
		</dependency>

sentinel-core-1.5.2

------------------------------------------

阿里巴巴

	<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>2.2.5.RELEASE</version>
		</dependency>


sentinel-core:jar:1.8.0

		
------------------------------------------


[INFO] +- javax.servlet:javax.servlet-api:jar:3.1.0:compile
[INFO] +- com.yirendai.cat:cat-tool:jar:1.2.03:compile
[INFO] +- com.jcraft:jsch:jar:0.1.55:compile
[INFO] \- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.5.RELEASE:compile
[INFO]    +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.0:compile
[INFO]    |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.0:compile
[INFO]    |     \- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.0:compile
[INFO]    +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.0:compile
[INFO]    |  +- com.alibaba.csp:sentinel-core:jar:1.8.0:compile
[INFO]    |  \- org.aspectj:aspectjrt:jar:1.9.2:compile
[INFO]    +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2.2.5.RELEASE:compile
[INFO]    |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.0:compile
[INFO]    +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.0:compile
[INFO]    +- com.alibaba.csp:sentinel-spring-webmvc-adapter:jar:1.8.0:compile
[INFO]    +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.0:compile
[INFO]    |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO]    +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.0:compile
[INFO]    |  \- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.0:compile
[INFO]    +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.0:compile
[INFO]    \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2.2.5.RELEASE:compile
[INFO]       \- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2.2.5.RELEASE:compile



		<!-- Alibaba sentinel 对应的服务器版本 sentinel-dashboard-1.8.1.jar

		<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>2.2.5.RELEASE</version>
		</dependency>
-->

		<!-- spring sentinel

			<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>0.2.2.RELEASE</version>
		</dependency>

<dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
     <version>0.9.0.RELEASE</version>
</dependency>
		-->





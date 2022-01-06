

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



https://github.com/alibaba/Sentinel/wiki/如何使用


**I.spring-cloud-alibaba**
:Spring Cloud Starter Alibaba Sentinel 功能版本说明  ：spring-cloud-starter-alibaba-sentinel对应版本信息

spring-cloud-alibaba:Nacos、sentinel、rocketmq、dubbo、seata版本对应关系:springboot springcloud和 sentinel对应关系

https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E
https://github.com/alibaba/spring-cloud-alibaba/wiki/版本说明

II.一共两部分：  导入 引入项目

		<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
			<version>2.1.2.RELEASE</version>
			<!--去除jackson-dataformat-xml，否则会返回xml文件，而不是JSON-->
			<exclusions>
				<exclusion>
					<groupId>com.fasterxml.jackson.dataformat</groupId>
					<artifactId>jackson-dataformat-xml</artifactId>
				</exclusion>
			</exclusions>
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
spring.application.name=NameProject

项目名称: project.name=dingsai

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



**I.B站课程**
--------------------------------------------B站课程开始------------------------------------------------------------


I.课程
https://www.aliyun.com/product/ahas?spm=5176.19720258.J_8058803260.91.45dc2c4aYOqXiD



 <dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-core</artifactId>
    <version>1.8.1</version>
</dependency>


启动时:
流控降级日志:C:\Users\Administrator.CE-20160511RDFS\logs\csp\
sentinel-block.log  限流日志



INFO: Sentinel log output type is: file
INFO: Sentinel log charset is: utf-8
INFO: Sentinel log base directory is: C:\Users\Administrator.CE-20160511RDFS\logs\csp\
INFO: Sentinel log name use pid is: false


II.本地用法不用后台，自动生效。
1.客户端引入jar包和配置启动 sentinel-core
2.定义资源 SphU.entry("HelloWorld") 和 entry.exit()
3.定义规则 FlowRuleManager.loadRules(rules);
4.检查效果 http://localhost:8080/study/qpsTest?mobile=1111111


    /**
     * 当前类的构造函数之后执行以下信息。
     */
    @PostConstruct
    public static void initFlowRulesStudy(){
        log.info("ZStudyController.initFlowRulesStudy");
        List<FlowRule> rules=new ArrayList<>();
        FlowRule rule=new FlowRule();
        rule.setResource("HelloWorld");
        //限流类型是 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //每秒能通过的个数
        rule.setCount(2);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

 
     @GetMapping("/qpsTest")
    @ResponseBody
    public AppResult<Phone> getPhoneMessage(String mobile) throws Exception {
        AppResult<Phone> result = new AppResult<>();
        Entry entry = null;
        try {
            entry = SphU.entry("HelloWorld");
            /*您的业务逻辑 - 开始*/
            mobile="15901229166";
            Phone phone = phoneMapper.selectByPhone(mobile.substring(0, 7));
            result.setData(phone);
            /*您的业务逻辑 - 结束*/
        } catch (BlockException e1) {
            /*流控逻辑处理 - 开始*/
            log.info("触发限流了:block!");
            result.setCode("9999");
            result.setDesc("限流器限流了");
            /*流控逻辑处理 - 结束*/
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }

        return result;
    }



II.定义资源的方式
https://github.com/alibaba/Sentinel/wiki/%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8
https://github.com/alibaba/Sentinel/wiki/如何使用


1.正常框架兼容

2.抛出异常的方式 try catch的方式:
http://localhost:8080/study/qpsTest

       try (Entry entry = SphU.entry("HelloWorld")){
            /*您的业务逻辑 - 开始*/
            log.info("业务逻辑:xxx");
            /*您的业务逻辑 - 结束*/
        } catch (BlockException e1) {
            /*流控逻辑处理 - 开始*/
            log.info("触发限流了:block!");
            /*流控逻辑处理 - 结束*/
        }

3.返回boolean值的方式

localhost:8080/study/qpsTestBoolean

// 资源名可使用任意有业务语义的字符串
if (SphO.entry("自定义资源名")) {  
try {
// 务必保证finally会被执行
} finally {
SphO.exit();
}
} else {
// 资源访问阻止，被限流或被降级
// 进行相应的处理操作
}


4.注解方式定义资源
localhost:8080/study/qpsTestAnnotation
https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81
https://github.com/alibaba/Sentinel/wiki/注解支持

优先级:blockHandler>fallback
@SentinelResource(value = "HelloWorld_Annotation", blockHandler = "blockHandler", fallback = "fallback")





5.异步调用方式

try {
AsyncEntry entry = SphU.asyncEntry(resourceName);
// 异步调用.
doAsync(userId, result -> {
try {
} finally {
// 在回调结束后 exit.
entry.exit();
}
});
} catch (BlockException ex) {
// Request blocked.Handle the exception (e.g. retry or fallback).
}



II.全局自定义错误:重写接口

servlet:UrlBlockHandler

spring.webmvc:BlockExceptionHandler

@Configuration
public class SentinelException implements BlockExceptionHandler









II.定义规则方式：规则的种类
https://github.com/alibaba/Sentinel/wiki/%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8
https://github.com/alibaba/Sentinel/wiki/如何使用


规则的种类:流量控制规则、熔断降级规则、系统保护规则、来源访问控制规则 和 热点参数规则。



III.流量控制规则 (FlowRule):一个资源可以设置多个

grade:
1.QPS 模式
2.并发线程数模式

limitApp:1.流控针对的调用来源

controlBehavior流控效果:
1.直接拒绝:默认
2.WarmUp:匀速增加到阈值-防止一下全上来
3.匀速+排队等待



III.熔断降级规则 (DegradeRule):慢请求了:返回错误
https://github.com/alibaba/Sentinel/wiki/熔断降级


grade熔断策略:
1.慢调用比例:默认
2.异常比例
3.异常数策略

minRequestAmount默认5：
熔断触发的最小请求数
请求数小于该值时即使异常比率超出阈值也不会熔断







III.系统自适应限流--后台"系统规则"功能
https://github.com/alibaba/Sentinel/wiki/系统自适应限流

Load、CPU 使用率、总体平均 RT、入口 QPS 和并发线程数等几个维度的监控指标




III.授权规则
https://github.com/alibaba/Sentinel/wiki/黑白名单控制




III.热点参数限流
https://github.com/alibaba/Sentinel/wiki/热点参数限流





III.动态规则扩展
https://github.com/alibaba/Sentinel/wiki/动态规则扩展


1.通过 API 直接修改 (loadRules)

手动通过 API 修改比较直观，可以通过以下几个 API 修改不同的规则：

// 修改流控规则
FlowRuleManager.loadRules(List<FlowRule> rules);
// 修改降级规则
DegradeRuleManager.loadRules(List<DegradeRule> rules); 

手动修改规则（硬编码方式）一般仅用于测试和演示，生产上一般通过动态规则源的方式来动态管理规则。


2.通过 DataSource 适配不同数据源修改




FlowRuleManager.loadRules(List<FlowRule> rules);// 修改流控规则
DegradeRuleManager.loadRules(List<DegradeRule> rules);// 修改降级规则














--------------------------------------------B站课程结束------------------------------------------------------------



--------------------------------------------实例代码------------------------------------------------------------

/**
* 定义异常1的方式限流1
*
* @return
* @throws Exception
*/

    @GetMapping("/qpsTest")
    @ResponseBody
    public AppResult<Phone> getPhoneMessage() throws Exception {
        AppResult<Phone> result = new AppResult<>();
        Entry entry = null;
        try {
            entry = SphU.entry("HelloWorld");
            /*您的业务逻辑 - 开始*/
            Phone phone = phoneMapper.selectByPhone("15901229166".substring(0, 7));
            result.setData(phone);
            /*您的业务逻辑 - 结束*/
        } catch (BlockException e1) {
            /*流控逻辑处理 - 开始*/
            log.info("触发限流了:block!");
            result.setCode("9999");
            result.setDesc("限流器限流了");
            /*流控逻辑处理 - 结束*/
        } catch (Exception ex) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(ex, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return result;
    }

    /**
     * 定义异常2的方式限流2
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/qpsTest2")
    @ResponseBody
    public AppResult<Phone> getPhoneMessage2() throws Exception {
        AppResult<Phone> result = new AppResult<>();

        try (Entry entry = SphU.entry("HelloWorld")) {
            /*您的业务逻辑 - 开始*/
            log.info("业务逻辑:xxx");
            /*您的业务逻辑 - 结束*/
        } catch (BlockException e1) {
            /*流控逻辑处理 - 开始*/
            log.info("触发限流了:block!");
            /*流控逻辑处理 - 结束*/
        }
        return result;
    }


    /**
     * @return
     * @throws Exception
     */
    @GetMapping("/qpsTestBoolean")
    @ResponseBody
    public AppResult<Phone> getPhoneMessageBoolean() throws Exception {
        AppResult<Phone> result = new AppResult<>();

        if (SphO.entry("HelloWorld_Boolean")) {
            try {
                /*您的业务逻辑 - 开始*/
                Phone phone = phoneMapper.selectByPhone("15901229166".substring(0, 7));
                result.setData(phone);
                log.info("业务逻辑:xxx");
                /*您的业务逻辑 - 结束*/
            } finally {
                SphO.exit();
            }
        } else {
            log.info("触发限流了:block!");
            result.setCode("9999");
            result.setDesc("限流器限流了");
        }
        return result;
    }

    /**
     * https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81
     * https://github.com/alibaba/Sentinel/wiki/注解支持
     *
     * 优先级:blockHandler>fallback
     * @return
     * @throws Exception
     */
    @GetMapping("/qpsTestAnnotation")
    @ResponseBody
    @SentinelResource(value = "HelloWorld_Annotation", blockHandler = "blockHandler", fallback = "fallback")
    public AppResult<Phone> qpsTestAnnotation() throws Exception {
        log.info("qpsTestAnnotation!");
        AppResult<Phone> result = new AppResult<>();
        /*您的业务逻辑 - 开始*/
        Phone phone = phoneMapper.selectByPhone("15901229166".substring(0, 7));
        result.setData(phone);
        return result;
    }

    public AppResult<Phone> blockHandler( BlockException ex) throws Exception {
        AppResult<Phone> result = new AppResult<>();
        result.setCode("9999");
        result.setDesc("blockHandler限流器限流了");
        log.info("blockHandler:触发限流了:block!");
        log.error("异常了",ex);
        return result;
    }


    public AppResult<Phone> fallback() throws Exception {
        AppResult<Phone> result = new AppResult<>();
        result.setCode("9999");
        result.setDesc("fallback限流器限流了");
        log.info("fallback:触发限流了:block!");
        return result;
    }


    /**
     * @return
     * @throws Exception
     */
    @GetMapping("/qpsTestDegradeRule")
    @ResponseBody
    public AppResult<Phone> DegradeRule() throws Exception {
        AppResult<Phone> result = new AppResult<>();
        log.info("qpsTestDegradeRule:xxx");
            try {
                Thread.sleep(1111);
                /*您的业务逻辑 - 开始*/
                Phone phone = phoneMapper.selectByPhone("15901229166".substring(0, 7));
                result.setData(phone);
                log.info("业务逻辑:xxx");
                /*您的业务逻辑 - 结束*/
            } finally {
            }
        return result;
    }

    /**
     * 当前类的构造函数之后执行以下信息:
     * 限流自定义
     */
@PostConstruct
public static void initFlowRulesStudy() {
log.info("ZStudyController.initFlowRulesStudy");
List<FlowRule> rules = new ArrayList<>();
FlowRule rule = new FlowRule();
rule.setResource("HelloWorld");
//限流类型是 QPS
rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//每秒能通过的个数
rule.setCount(2);
rules.add(rule);
FlowRuleManager.loadRules(rules);
}



-----------------------------------------------------------------------------

全局返回异常

/**
* @author daniel 2021-2-24 0024.
  */
  @Configuration
  public class SentinelException implements BlockExceptionHandler {

  @Override
  public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
  MyResponse errorResponse = new MyResponse();
  // 不同的异常返回不同的提示语
  if (e instanceof FlowException) {
  errorResponse.setMsg("被限流了");
  errorResponse.setStatus(1);
  } else if (e instanceof DegradeException) {
  errorResponse.setMsg("服务降级了");
  errorResponse.setStatus(2);
  } else if (e instanceof ParamFlowException) {
  errorResponse.setMsg("被限流了");
  errorResponse.setStatus(3);
  } else if (e instanceof SystemBlockException) {
  errorResponse.setMsg("被系统保护了");
  errorResponse.setStatus(4);
  } else if (e instanceof AuthorityException) {
  errorResponse.setMsg("被授权了");
  errorResponse.setStatus(5);
  }      
       httpServletResponse.setStatus(500);
       httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
       httpServletResponse.setCharacterEncoding("utf-8");
       httpServletResponse.getWriter().print(new Gson().toJson(errorResponse));
  }
  }

class MyResponse {
private Integer status;
private String msg;
    public Integer getStatus() {
        return status;
    }    public static Object builder() {
        return null;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}




-----------------------------------------------------------------------------------------------------------------------------

I.Sentinel实现原理
https://github.com/alibaba/Sentinel/wiki/Sentinel工作主流程
https://github.com/alibaba/Sentinel/wiki/Sentinel-核心类解析
https://github.com/alibaba/Sentinel/wiki/限流---冷启动
https://github.com/alibaba/Sentinel/wiki/流量控制-匀速排队模式
https://github.com/alibaba/Sentinel/wiki/Sentinel工作主流程
https://github.com/alibaba/Sentinel/wiki/流量控制
https://github.com/alibaba/Sentinel/wiki/在生产环境中使用-Sentinel#同类组件功能对比






核心功能:slot 
通过:slot chain 进行串联



I.DefaultSlotChainBuilder:

I.ProcessorSlot:功能模块
II.NodeSelectorSlot:收集资源的路径，并将这些资源的调用路径，以树状结构存储起来，用于根据调用路径来限流降级；
II.ClusterBuilderSlot:用于存储资源的统计信息以及调用者信息，例如该资源的 RT, QPS, thread count 等等，这些信息将用作为多维度限流，降级的依据；
II.LogSlot:
II.StatisticSlot:用于记录、统计不同纬度的 runtime 指标监控信息；
II.AuthoritySlot:根据配置的黑白名单和调用来源信息，来做黑白名单控制；
II.SystemSlot:通过系统的状态，例如 load1 等，来控制总的入口流量；
II.FlowSlot:用于根据预设的限流规则以及前面 slot 统计的状态，来进行流量控制；
II.DegradeSlot:通过统计信息以及预设的规则，来做熔断降级；

I.Constants:链路顺序  order = Constants.ORDER_LOG_SLOT

    public static final int ORDER_NODE_SELECTOR_SLOT = -10000;
    public static final int ORDER_CLUSTER_BUILDER_SLOT = -9000;
    public static final int ORDER_LOG_SLOT = -8000;
    public static final int ORDER_STATISTIC_SLOT = -7000;
    public static final int ORDER_AUTHORITY_SLOT = -6000;
    public static final int ORDER_SYSTEM_SLOT = -5000;
    public static final int ORDER_FLOW_SLOT = -2000;
    public static final int ORDER_DEGRADE_SLOT = -1000;


I.限流算法 --漏桶 Leaky bucket :不能处理瞬间流量

* This strategy is an implement of <a href="https://en.wikipedia.org/wiki/Leaky_bucket">leaky bucket</a>.
* It is used to handle the request at a stable rate and is often used in burst traffic (e.g. message handling).
* When a large number of requests beyond the system’s capacity arrive
* at the same time, the system using this strategy will handle requests and its
* fixed rate until all the requests have been processed or time out.

com.alibaba.csp.sentinel.slots.block.flow.FlowSlot

II.限流算法
![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/限流算法.png)
III.计数器:(滑动窗口)
III.漏桶算法(Leaky bucket)：入口宽，出口速率匀速，不能处理瞬间流量（当大于漏桶时就丢弃）。
III.令牌桶算法(漏桶优化Token Bucket)：每隔1/N时间往桶里放一个令牌(可以处理瞬间大流量)






http://bank-finance.167.test.yirendai.com/sentinel/circuitBreaker

http://bank-finance.167.test.yirendai.com/sentinel/slow





I.熔断 问题发现 minRequestAmount 设置问题

com.alibaba.csp.sentinel.slots.DefaultSlotChainBuilder

com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager#newCircuitBreakerFrom

com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.ResponseTimeCircuitBreaker

com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.ResponseTimeCircuitBreaker#handleStateChangeWhenThresholdExceeded

        List<DegradeRule> rules = new ArrayList<>();
        //毫秒 Max allowed response time 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
        double count=1500;
        //熔断时长，单位为 s
        int timeWindow=30;
      // 慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）  Circuit breaker opens when slow request ratio > 60%   :5%
        double slowRatioThreshold=0.05;
        //熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入）
        int minRequestAmount=20;
       //统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入）  2分钟
        int statIntervalMs=2*60*1000;

        DegradeRule ruleSearchUserTag = new DegradeRule(searchUserTag_Sentinel)
                .setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType())
                //毫秒 Max allowed response time 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
                .setCount(count)
                // Retry timeout (in second) 熔断时长，单位为 s
                .setTimeWindow(timeWindow)
                // Circuit breaker opens when slow request ratio > 60%  慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）
                .setSlowRatioThreshold(slowRatioThreshold)
                //熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入）
                .setMinRequestAmount(minRequestAmount)
                //统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入）
                .setStatIntervalMs(statIntervalMs);
        rules.add(ruleSearchUserTag);





--------------------------------------------------20210531介入--------------------------------------------------------------------------------------


I.启动sentinel 1.8.1
cd D:\jar
cd C:\Work\jar
java -Dserver.port=1111 -Dcsp.sentinel.dashboard.server=localhost:1111 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.1.jar

http://127.0.0.1:1111/#/login

user:sentinel


日志路径：
C:\Users\Administrator.CE-20160511RDFS\logs\csp
C:\Users\Daniel_MacPro\logs\csp\



I.20210531
新系统增加sentinel支持
https://github.com/alibaba/spring-cloud-alibaba/wiki/版本说明
Hoxton.SR8>2.2.5.RELEASE


<spring.cloud-version>Hoxton.SR8</spring.cloud-version>
  
<!-- https://github.com/alibaba/spring-cloud-alibaba/wiki/版本说明 -->
<dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
            <version>2.2.5.RELEASE</version>
 </dependency>




II.用法1:
https://github.com/alibaba/Sentinel/wiki/熔断降级
熔断 配置
@PostConstruct
public static void initDegradeRuleStudy() {
log.info("ZStudyController.initDegradeRuleStudy");
List<DegradeRule> rules = new ArrayList<>();
DegradeRule rule = new DegradeRule();
rule.setResource("HelloWorldDegradeRule");
// set threshold RT, 10 ms  慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
rule.setCount(10);
//熔断策略，支持慢调用比例/异常比例/异常数策略 
rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
rule.setTimeWindow(10);
rules.add(rule);
DegradeRuleManager.loadRules(rules);
}*


熔断2 
        List<DegradeRule> rules = new ArrayList<>();
        //毫秒 Max allowed response time 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
        double count=1500;
        //熔断时长，单位为 s
        int timeWindow=30;
      // 慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）  Circuit breaker opens when slow request ratio > 60%   :5%
        double slowRatioThreshold=0.05;
        //熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入）
        int minRequestAmount=10;
       //统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入）  2分钟
        int statIntervalMs=2*60*1000;

        DegradeRule ruleSearchUserTag = new DegradeRule(searchUserTag_Sentinel)
                .setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType())
                //毫秒 Max allowed response time 慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
                .setCount(count)
                // Retry timeout (in second) 熔断时长，单位为 s
                .setTimeWindow(timeWindow)
                // Circuit breaker opens when slow request ratio > 60%  慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）
                .setSlowRatioThreshold(slowRatioThreshold)
                //熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入）
                .setMinRequestAmount(minRequestAmount)
                //统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入）
                .setStatIntervalMs(statIntervalMs);
        rules.add(ruleSearchUserTag);

        DegradeRuleManager.loadRules(rules);







限流  配置
    public static void initFlowRulesStudy() {
        log.info("ZStudyController.initFlowRulesStudy");
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        //限流类型是 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //每秒能通过的个数
        rule.setCount(2);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }


代码执行
@GetMapping("/qpsTest")
    @ResponseBody
 public AppResult<Phone> getPhoneMessage() throws Exception {
        AppResult<Phone> result = new AppResult<>();
        Entry entry = null;
        try {
            entry = SphU.entry("HelloWorld");
            /*您的业务逻辑 - 开始*/
            Phone phone = phoneMapper.selectByPhone("15901229166".substring(0, 7));
            result.setData(phone);
            /*您的业务逻辑 - 结束*/
        } catch (BlockException e1) {
            /*流控逻辑处理 - 开始*/
            log.info("触发限流了:block!");
            result.setCode("9999");
            result.setDesc("限流器限流了");
            /*流控逻辑处理 - 结束*/
        } catch (Exception ex) {
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(ex, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return result;
    }


II.用法2 注解:

@SentinelResource(value = "searchUserTag_Sentinel", blockHandler = "searchUserTagBlockHandler",blockHandlerClass = SentinelUtil.class,fallback = "searchUserTagFallback",fallbackClass = SentinelUtil.class)
    //一定要小心，后台配置的是searchUserTag_Sentinel不是自动的接口名；1.6以后blockHandler含有限流和降级,fallback1.6以后只有接口错误才会调用，没有降级。
//searchUserTagFallback 都要是static方法，如果是本类的就不需要
 public SearchUserTagDataResponse searchUserTagData(@Valid SearchUserTagDataRequest request, BindingResult bindingResult) {


public static SearchUserTagDataResponse searchUserTagBlockHandler(@Valid SearchUserTagDataRequest request, BindingResult bindingResult, BlockException ex) {
SearchUserTagDataResponse result = new SearchUserTagDataResponse();
result.setCode(SENTINEL_BLOCK_CODE);
result.setMsg("searchUserTag限流");
log.info("searchUserTagBlockHandler:限流:{}",request);
return result;
}

    /**
     * searchUserTag 接口熔断
     * @param request
     * @param bindingResult
     * @return
     * @throws Exception
     */
 public static SearchUserTagDataResponse searchUserTagFallback(@Valid SearchUserTagDataRequest request, BindingResult bindingResult) {
        SearchUserTagDataResponse result = new SearchUserTagDataResponse();
        result.setCode(SENTINEL_BLOCK_CODE);
        result.setMsg("searchUserTag熔断");
        log.info("searchUserTagFallback:降级:{}",request);
        return result;
 }




II.用法3.全局



@Configuration
public class SentinelConfig   implements BlockExceptionHandler {
*//**
* 全局异常时，临时使用
* @param request
* @param response
* @param e
* @throws Exception
*//*
@Override
public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
BaseResponse errorResponse = new BaseResponse();
// 不同的异常返回不同的提示语
if (e instanceof FlowException) {
errorResponse.setMsg("被限流了");
errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
} else if (e instanceof DegradeException) {
errorResponse.setMsg("服务降级了");
errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
} else if (e instanceof ParamFlowException) {
errorResponse.setMsg("被限流了");
errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
} else if (e instanceof SystemBlockException) {
errorResponse.setMsg("被系统保护了");
errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
} else if (e instanceof AuthorityException) {
errorResponse.setMsg("授权被限制");
errorResponse.setCode(Constant.SENTINEL_BLOCK_CODE);
}
response.setStatus(200);
response.setHeader("Content-type", "text/html;charset=UTF-8");
response.setCharacterEncoding("utf-8");
response.getWriter().print(new Gson().toJson(errorResponse));
}
}





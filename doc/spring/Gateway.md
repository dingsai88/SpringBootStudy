


I.网关

http://gitlab.creditease.corp/fund-inner/gateway

II.网关后台地址:
http://gitlab.creditease.corp/fund-inner/fortune-gateway-mis


http://fortune-gateway-mis.155.test.yirendai.com/login
root/a

http://fortune-gateway-mis.167.test.yirendai.com/login


fortune-gateway-mis.167.test.yirendai.com




I.后台mis系统和数据表对应关系
fortune_gateway_interface_config:接口配置


fortune_gateway_operation_log:操作日志--用户操作记录

fortune_gateway_permission:不知道干啥 许可

fortune_gateway_role:用户类型 ROLE_ROOT、ROLE_USER

fortune_gateway_role_permission:描述角色拥有的权限
fortune_gateway_route:路由查看  /activityController/**	http://yingApi.155.test.yirendai.com/activityController

fortune_gateway_user：用户表
fortune_gateway_user_role:描述用户所属的角色

t_sys_config:系统配置页面






100191111900286


I.路由规则


II.Path Route Predicate Factory

类似Nginx

spring:
cloud:
gateway:
routes:
- id: path_route
uri: https://example.org
predicates:
- Path=/red/{segment},/blue/{segment}




II.Query Route Predicate Factory


spring:
cloud:
gateway:
routes:
- id: query_route
uri: https://example.org
predicates:
- Query=green






C:\Program Files (x86)\Tencent




http://gitlab.creditease.corp/architecture/GateWay



------------------------------------zuul过滤器-----------------------------------------
https://github.com/Netflix/zuul
创建: 监听器-->过滤器-->Servlet.



zuul过滤器关键词：
过滤器类型、执行顺序、条件、动作。



I.过滤器类型
II.Pre routing Filter：认证、选路由、请求日志
II.Rounting Filter :
II.Post rounting Filter:响应增加http头、收集统计和度量、响应以流的方式发送到客户端。
II.Error ：上述流程执行的过滤器



I.执行顺序(每个阶段都有执行顺序)
Execution Order

I.条件Criteria(过滤器被执行需要满足的条件)



I.动作action(满足条件时需要执行的动作)




---------------------------------------------
过滤器管理模块、过滤器加载模块、过滤器运行时模块。


Filter Publisher
Filter Persister
Filter Poller




Filter Directory
Filter File Manager
Filter  Loader




Zuul网关路由管理实践：

基于Eureka自发现(Netflix做法)：注册发现 类似dubbo

基于域名做法:






**阻塞同步模式优劣:**

优:
编程模型简单
开发调试运维简单


劣:
线程上下文切换开销
连接数限制
延迟阻塞耗尽线程连接资源



适用: 计算密集型(CPU bound)场景





**非阻塞异步模式:**
适用: IO密集型(IO bound)场景

非阻塞异步模式优劣


**优:**
线程开销少
连接数易扩展


**劣:**
编程模型复杂
开发调试运维复杂
ThreadLocal不work (cat不好用基于 ThreadLocal)


**Zuul 2额外功能亮点**



**服务器协议**
HTTP/2
Mutual TLS

**弹性**
Adaptive Retries(自适应重试)
Origin Concurrency Protection(源并发保护)


**运维**
Request Passport
Status Categories(状态码分类)
Request Attempts




**Zuul 2架构概览**

个人建议
生产使用Zuul1.0
• 同步模型简单
• 容易监控埋点(CAT)
• 稳定成熟
• 大部分公司量级OK
• 可以集成Hystrix熔断
• 可以使用AsyncServlet优化






**Zuul网关最佳实践**

异步AsyncServlet优化连接数
Apollo配置中心集成动态配置
Hystrix熔断限流 ：信号量隔离
连接池管理
CAT和Hystrix监控
过滤器调试技巧
网关无业务逻辑
自助路由(需定制扩展)





**参考资源和后续课程预览**






Netflix Zuul
https://github.com/Netflix/zuul

Spring2go定制版Zuul

https://github.com/spring2go/s2g-zuul


**其它开源网关产品**
Nginx+lua:

Kong(核心开源):基于nginx 性能高
https://github.com/Kong/kong

Traefik:go语言开发

Spring cloud Netflix zuul:

Tyk(核心开源)
https://github.com/TykTechnologies/tyk

悟空API网关(部分开源+商业支持)
https://github.com/eolinker/GoKu-API-Gateway


小豹API网关(商业)
http://www.xbgateway.com/





metrics监控:kairosDB


调用链监控:大众点评cat

健康检查和告警:ZMon、zalando



-----------------------------------Spring cloud gateway---------------------------------------------------------------------

https://www.cnblogs.com/mrhelloworld/p/gateway1.html

https://www.cnblogs.com/mrhelloworld/tag/%E7%BD%91%E5%85%B3/
https://www.cnblogs.com/mrhelloworld/tag/网关/


**Glossary**词汇表

**路由（Route）：**
路由是网关最基础的部分，路由信息由 ID、目标 URI、一组断言和一组过滤器组成。如果断言路由为真，则说明请求的 URI 和配置匹配。

**断言（Predicate）：**
Java8 中的断言函数。Spring Cloud Gateway 中的断言函数输入类型是 Spring 5.0 框架中的 ServerWebExchange。Spring Cloud Gateway 中的断言函数允许开发者去定义匹配来自于 Http Request 中的任何信息，比如请求头和参数等。

**过滤器（Filter）：**
一个标准的 Spring Web Filter。Spring Cloud Gateway 中的 Filter 分为两种类型，分别是 Gateway Filter 和 Global Filter。过滤器将会对请求和响应进行处理。





1.Gateway Handler Mapping

2.Gateway Web Handler



spring.application.name=gateway-server # 应用名称
spring.cloud.gateway.routes.id=product-service           # 路由 ID，唯一
spring.cloud.gateway.routes.uri=http://localhost:7070/   # 目标 URI，路由到微服务的地址
spring.cloud.gateway.routes.predicates.Path=/product/**          # 匹配对应 URL 的请求，将匹配到的请求追加在目标 URI 之后


gateway:
# 路由规则
  routes:
      - id: product-service           # 路由 ID，唯一
uri: http://localhost:7070/   # 目标 URI，路由到微服务的地址
predicates:                   # 断言（判断条件）
- Path=/product/**          # 匹配对应 URL 的请求，将匹配到的请求追加在目标 URI 之后









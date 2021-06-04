

https://spring.io/projects/spring-cloud-bus#learn

I.spring cloud config





I.spring cloud bus
https://spring.io/projects/spring-cloud-bus#learn

3.0.1 文档
https://docs.spring.io/spring-cloud-bus/docs/current/reference/html/


spring cloud Bus配合 spring cloud Config实现配置更新

主动调用一台app server  全部机器都刷新
http://localhost:9006/actuator/refresh




I.Spring Cloud Connectors 






I.Spring Cloud Contract合同合约
测试驱动开发 mock数据用



I.



Netflix (net flex) > Spring Cloud > Alibaba


1.Eureka服务注册和发现

2.断路器Hystrix :不更新

3.网络访问Feign  Netflix: SpringCloud2后F改为OpenFeign

4.客户端负载均衡 Ribbon 负载均衡

5.网关路由和过滤 Zuul :不更新

6.动态配置Archaius  ：不更新





I.Netflix (net flex) > Spring Cloud > Alibaba

**II.netflix**
1.Eureka服务注册和发现

2.断路器Hystrix :不更新

3.网络访问Feign  Netflix: SpringCloud2后F改为OpenFeign

4.客户端负载均衡 Ribbon

5.网关路由和过滤 Zuul :不更新

6.动态配置Archaius  ：不更新


**II.Alibaba**

1.流量控制和服务降级:Alibaba Sentinel

2.服务注册和发现:Alibaba Nacos 

3.配置中心:Alibaba Nacos 

4.Spring Cloud Stream流消息 :RocketMQ (java版本的kafka)

5.消息总线(动态刷新配置): Spring Cloud Bus RocketMQ

6.分布式事务:Seata

7.网络调用:dubbo




**I.Spring Cloud Stream**

Spring Cloud Stream 是什什么
• ⼀一款⽤用于构建消息驱动的微服务应⽤用程序的轻量量级框架
特性
• 声明式编程模型
• 引⼊入多种概念抽象
• 发布订阅、消费组、分区
• ⽀支持多种消息中间件
• RabbitMQ、Kafka ……






Binder  结合剂
• RabbitMQ
• Apache Kafka
• Kafka Streams
• Amazon Kinesis
• RocketMQ
•

Binding
• 应⽤用中⽣生产者、消费者与消息系统之间的桥梁梁
• @EnableBinding
• @Input / SubscribableChannel
• @Output / MessageChannel




消费组
• 对同⼀一消息，每个组中都会有⼀一个消费者收到消息
分区



如何发送与接收消息
⽣生产消息
• 使⽤用 MessageChannel 中的 send()
• @SendTo
消费消息
• @StreamListener
• @Payload / @Headers / @Header
其他说明
• 可以使⽤用 Spring Integration



**I.通过 Spring Cloud Stream 访问 RabbitMQ**

“RabbitMQ is the most widely deployed open source message broker.”
– RabbitMQ 官⽹网


依赖
• Spring Cloud - spring-cloud-starter-stream-rabbit
• Spring Boot - spring-boot-starter-amqp
配置
• spring.cloud.stream.rabbit.binder.*
• spring.cloud.stream.rabbit.bindings.<channelName>.consumer.*
• spring.rabbitmq.*


II.服务员项目
1.添加start

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-stream-rabbit</artifactId>
		</dependency>


2.
@EnableBinding(Barista.class)
public class xxxxServiceApplication 


3.加入消费和生产 newOrders

public interface Barista {
String NEW_ORDERS = "newOrders";
String FINISHED_ORDERS = "finishedOrders";

    //默认不写是本方法名字
    @Input(FINISHED_ORDERS)  
    SubscribableChannel finishedOrders();

    @Output("newOrders")
    MessageChannel newOrders();
}

4.生产消息-发送消息
@Autowired
private Barista barista;

    barista.newOrders().send(MessageBuilder.withPayload(order.getId()).build());


5.消费消息 finishedOrders

@Component
@Slf4j
public class OrderListener {
@StreamListener(Barista.FINISHED_ORDERS)
public void listenFinishedOrders(Long id) {
log.info("We've finished an order [{}].", id);
}
}


II.生产咖啡项目

1. 只需要连接mq 不需要服务发现等，没太懂 
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-stream-binder-rabbit</artifactId>
	</dependency>

监听组，如果有多个组，每个组都能有一个队列消费一次。
spring.cloud.stream.bindings.newOrders.group=barista-service


2.

@EnableBinding(Waiter.class)

@EnableJpaRepositories
@SpringBootApplication
@EnableBinding(Waiter.class)
public class BaristaServiceApplication {

3.定义接受消息和发送消息

public interface Waiter {
String NEW_ORDERS = "newOrders";
String FINISHED_ORDERS = "finishedOrders";

    @Input(NEW_ORDERS)
    SubscribableChannel newOrders();

    @Output(FINISHED_ORDERS)
    MessageChannel finishedOrders();
}

4.业务类--接收消息

@Component
@Slf4j
public class OrderListener {

@StreamListener(Waiter.NEW_ORDERS)
    public void processNewOrder(Long id) {
     log.info("Receive a new Order {}. Waiter: {}. Customer: {}", id);  
    }
}

5.业务类--发送消息

发送方法1：
    @Qualifier(Waiter.FINISHED_ORDERS)
    private MessageChannel finishedOrdersMessageChannel;

  finishedOrdersMessageChannel.send(MessageBuilder.withPayload(id).build());


发送方法2：
@Autowired
private Waiter waiter;
waiter.finishedOrders().send(MessageBuilder.withPayload(id).build());




I.配置随机生成语句
1.配置文件定义一个变量
order.barista-prefix=springbucks-

2. 注入到字段。
@Value("${order.barista-prefix}${random.uuid}")
private String barista;



**I.通过 Spring Cloud Stream 访问 Kafka**


流程:
创建订单(waiter服务员)发消息
> >> 
制作咖啡接收消息，制作咖啡(barista咖啡制作员)，成功后发消息
> >>
接收订单成功信息(waiter服务员)



**II.waiter服务员项目**

1.maven配置和 参数配置
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-stream-kafka</artifactId>
		</dependency>


spring.cloud.stream.kafka.binder.brokers=localhost
spring.cloud.stream.kafka.binder.defaultBrokerPort=9092
##一条消息，每个组都能有一个节点消费一次：（多个组时，一个消息能消费多次）
spring.cloud.stream.bindings.finishedOrders.group=waiter-service


2.启动类配置
@EnableBinding(Barista.class)
public class WaiterServiceApplication 


3.消息定义接口(有消费者、有生产者)

public interface Barista {
String NEW_ORDERS = "newOrders";
String FINISHED_ORDERS = "finishedOrders";

//默认@Input不写就是获得这个方法的方法名。@Input(FINISHED_ORDERS)==@Input
    @Input(FINISHED_ORDERS)
    SubscribableChannel finishedOrders();

//默认@Output不写就是获得这个方法的方法名。@Output(NEW_ORDERS)==@Output
    @Output
    MessageChannel newOrders();
}

4.业务类调用生产并发送消息:需要一杯咖啡
@Autowired
private Barista barista;

Boolean bo=barista.newOrders().send(MessageBuilder.withPayload(order.getId()).build());


5.接收咖啡制作成功的消息

@Component
@Slf4j
public class OrderListener {
@StreamListener(Barista.FINISHED_ORDERS)
public void listenFinishedOrders(Long id) {
log.info("We've finished an order [{}].", id);
}
}



**II.barista咖啡制作员项目**

1.maven 配置和配置文件配置

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-stream-binder-kafka</artifactId>
		</dependency>

spring.cloud.stream.kafka.binder.brokers=localhost
spring.cloud.stream.kafka.binder.defaultBrokerPort=9092
spring.cloud.stream.bindings.newOrders.group=barista-service


2.启动类配置
@EnableBinding(Waiter.class)
public class BaristaServiceApplication 


3.消息接收和发送 Waiter
public interface Waiter {
String NEW_ORDERS = "newOrders";
String FINISHED_ORDERS = "finishedOrders";

    @Input(NEW_ORDERS)
    SubscribableChannel newOrders();

    @Output(FINISHED_ORDERS)
    MessageChannel finishedOrders();
}
 


4.接收制作咖啡的消息和返回成功的消息
 
实现方法1：
   //接收消息
    @StreamListener(Waiter.NEW_ORDERS)
     //把这个方法的返回值当做消息体返回
    @SendTo(Waiter.FINISHED_ORDERS)
    public Long processNewOrder(Long id) {
        log.info("Order {} is READY.", id);
        return id;
    }




实现方法2：


    @Qualifier(Waiter.FINISHED_ORDERS)
    private MessageChannel finishedOrdersMessageChannel;
 
    @StreamListener(Waiter.NEW_ORDERS)      
    public Long processNewOrder(Long id) {
        log.info("Order {} is READY.", id);
      finishedOrdersMessageChannel.send(MessageBuilder.withPayload(id).build());
        return id;
    }




实现方法3：
 
@Autowired
private Waiter waiter;
    @StreamListener(Waiter.NEW_ORDERS)      
    public Long processNewOrder(Long id) {
        log.info("Order {} is READY.", id);
        waiter.finishedOrders().send(MessageBuilder.withPayload(id).build());
        return id;
    }













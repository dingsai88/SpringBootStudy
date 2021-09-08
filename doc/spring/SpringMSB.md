

004-1 spring，ioc入门与详解 

Spring 是一个轻量级 控制反转IOC  面向切面AOP

Ioc ( Inversion of Control控制反转)

控制反转就是把创建和管理 bean 的过程转移给了第三方。而这个第三方，就是 Spring IoC Container，对于 IoC 来说，最重要的就是容器。


IoC 容器


何为控制，控制的是什么？
答：是 bean 的创建、管理的权利，控制 bean 的整个生命周期。



何为反转，反转了什么？

答：把这个权利交给了 Spring 容器，而不是自己去控制，就是反转。由之前的自己主动创建对象，变成现在被动接收别人给我们的对象的过程，这就是反转。


1. IoC容器

1.1. Spring IoC Container(控制反转容器又称依赖注入) 和 Bean 介绍

1.2. 容器概述

1.3. bean概述

1.4. 依赖注入
1.5. Bean 作用域
1.6 、7. Bean 相关
1.8. 容器扩展点

1.9. 基于注解的容器配置

1.10. 类路径扫描和托管组件
1.11. 使用 JSR 330 标准注解
1.12. 基于 Java 的容器配置
1.13. 环境抽象  Environment
1.14. 注册一个LoadTimeWeaver
1.15. 附加功能ApplicationContext 国际化等
1.16. BeanFactory
BeanFactory和 ApplicationContext容器级别和引导的
建议使用 ApplicationContext 



2. Resources 资源(IO加载)

2.2. Resource接口
IO用 url用

2.3. 内置Resource实现
UrlResource
ClassPathResource
FileSystemResource
PathResource
ServletContextResource
InputStreamResource
ByteArrayResource

2.4. ResourceLoader接口 读取io
Resource template = ctx.getResource("file:///some/resource/path/myTemplate.txt");


类路径：classpath:com/myapp/config.xml
文件：file:///data/config.xml
https:https://myserver/logo.png
取决于底层ApplicationContext /data/config.xml

2.5. ResourcePatternResolver接口 批量读 ResourceLoader的拓展

2.6. ResourceLoaderAware
加载前后特殊处理

2.7. Resources as Dependencies
资源依赖 资源名称路径前缀后缀等

2.8. Application Contexts and Resource Paths应用上下文和资源路径
ApplicationContext ctx =new FileSystemXmlApplicationContext("conf/appContext.xml");
ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/appContext.xml");
ApplicationContext ctx =new FileSystemXmlApplicationContext("classpath:conf/appContext.xml");
ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"services.xml", "repositories.xml"}, MessengerService.class);




3. Validation, Data Binding, and Type Conversion 验证、数据绑定和类型转换
   https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validator
3.1. 使用 Spring 的 Validator 接口进行验证

org.springframework.validation.Validator接口的以下两个方法为类提供验证行为：
supports(Class): Can this Validator validate instances of the supplied Class?
validate(Object, org.springframework.validation.Errors): 验证给定的对象，并在验证错误的情况下向给定的Errors对象注册这些对象。



3.2. 将代码解析为错误消息

3.3. Bean 操作和BeanWrapper

3.4. Spring Type Conversion   类型转换
ConverterFactory

3.5. Spring Field Formatting 字段格式化
https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#format
@DateTimeFormat(iso=ISO.DATE)
private Date date;
@NumberFormat(style=Style.CURRENCY)
private BigDecimal decimal;



3.6. 配置全局日期和时间格式

3.7. Java Bean 验证
@NotNull
@Size(max=64)
private String name;
@Min(0)


4. Spring 表达式语言 (SpEL)

4.2. Bean 定义中的表达式
<property name="randomNumber" value="#{ T(java.lang.Math).random() * 100.0 }"/>


5. Spring  AOP 面向切面编程
   AOP 在 Spring 框架中用于：
提供声明式企业服务。最重要的此类服务是 声明式事务管理。
让用户实现自定义方面，用 AOP 补充他们对 OOP 的使用。

schema-based approach

5.1. AOP 概念
https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-introduction-defn

AOP 代理：由 AOP 框架创建的对象，用于实现方面契约（建议方法执行等）。在 Spring Framework 中，AOP 代理是 JDK 动态代理或 CGLIB 代理。


5.2. Spring AOP 能力和目标
Spring AOP 是用纯 Java 实现的。不需要特殊的编译过程。Spring AOP 不需要控制类加载器层次结构，因此适合在 servlet 容器或应用程序服务器中使用。
Spring AOP 没有实现字段拦截，如果您需要建议字段访问和更新连接点，请考虑使用 AspectJ 之类的语言。

目的不是提供最完整的 AOP 实现。
目的是提供 AOP 实现和 Spring IoC 之间的紧密集成，以帮助解决企业应用程序中的常见问题。


5.3. AOP 代理  :spring 默认使用JDK动态代理，类没有实现接口就用CGLIB
Spring AOP 默认为 AOP 代理使用标准的 JDK 动态代理。这允许代理任何接口（或接口集）。
Spring AOP 也可以使用 CGLIB 代理。这是代理类而不是接口所必需的。默认情况下，如果业务对象未实现接口，则使用 CGLIB。


5.4. @AspectJ 支持
@EnableAspectJAutoProxy
@Pointcut("execution(* transfer(..))")  

5.5. Schema-based AOP Support  基于概要的 AOP 支持


5.6. 选择 AOP  : Spring AOP 或 AspectJ  (xml注解等)
Spring AOP 比使用完整的 AspectJ 更简单，因为不需要将 AspectJ 编译器/编织器引入您的开发和构建过程。
如果只需要在 Spring bean 上执行操作，Spring AOP 是正确的选择。
不由 Spring 容器管理的对象（例如域对象，通常），您需要使用 AspectJ


5.7. Mixing Aspect Types 混合切面类型


5.8. 代理机制
Spring AOP 使用 JDK 动态代理或 CGLIB 为给定的目标对象创建代理。JDK 动态代理内置于 JDK 中，而 CGLIB 是一个常见的开源类定义库（重新打包成spring-core）。


5.9. @AspectJ 使用等 和介绍
https://www.eclipse.org/aspectj/

6. Spring AOP APIs

6.1. Spring 中的切入点 API

6.2. Spring 中的建议 API

6.3. Spring 中的 Advisor API

6.4. 使用ProxyFactoryBean来创建 AOP 代理
ProxyConfig
proxyTargetClass:true如果要代理目标类，而不是目标类的接口。如果此属性值设置为true，则会创建 CGLIB 代理（
目标对象的类（以下简称为目标类）没有实现任何接口，则创建基于 CGLIB 的代理。

6.5. 简洁的代理定义


6.6. 以编程方式创建 AOP 代理ProxyFactory

6.7. 操作建议对象



7. Null-safety 空值安全
   @Nullable: 表示特定参数、返回值或字段的注解可以是null.

@NonNull: 用于指示特定参数、返回值或字段不能为的注释null（参数/返回值和字段 where@NonNullApi和@NonNullFieldsapply 分别不需要）。

@NonNullApi: 包级别的注解，将非空声明为参数和返回值的默认语义。

@NonNullFields: 包级别的注释，将非空声明为字段的默认语义。


8. 数据缓冲区和编解码器
   Java NIO 提供了ByteBuffer但许多库在顶部构建了自己的字节缓冲区 API

spring-core模块提供了一组抽象来处理各种字节缓冲区 API，如下所示：

DataBufferFactory 抽象数据缓冲区的创建。
DataBuffer表示一个字节缓冲区，它可以被 合并。
DataBufferUtils 提供数据缓冲区的实用方法。
编解码器：将流数据缓冲区流解码或编码为更高级别的对象。Encoder、Decoder


9. Logging  日志
   Log4j 2.x 或 Logback（或其他 SLF4J 提供程序）
   





# I.Spring官网 

Spring Framework提高 JDK基线

依赖注入dependency injection、事件events、资源resources、i18n、验证validation、数据绑定data binding、类型转换type conversion、SpEL、AOP。

AspectJ 切面

##  II.控制反转 (IoC) 容器 又称依赖注入。Inversion of Control (IoC)  也称为  dependency injection 依赖注入 (DI)

Spring 的控制反转 (IoC) 容器。

Inversion of Control (IoC)  也称为  依赖注入 (DI) dependency injection

### III.容器概述:
org.springframework.context.ApplicationContext
Spring IoC 容器，负责实例化、配置和组装 bean


### III.bean概述
Spring IoC 容器管理一个或多个 bean

在容器本身内，这些 bean 定义表示为BeanDefinition 对象

**bean 定义**
class、name、Scope作用域、
Constructor arguments 构造函数、Properties属性、Autowiring mode自动装配注入 
Lazy initialization mode延迟初始化模式、Initialization method初始化回调、Destruction method销毁回调


**bean 命名**
Bean 命名约定
以小写字母开头，并从那里开始使用驼峰式大小写。



**循环依赖问题**
Spring IoC 容器在运行时检测到此循环引用，并抛出一个 BeanCurrentlyInCreationException
BeanCurrentlyInCreationException



**Bean scopes 作用域**

Scope:

singleton 单例:（默认）将单个 bean 定义范围限定为每个 Spring IoC 容器的单个对象实例。
prototype : 将单个 bean 定义范围限定为任意数量的对象实例。
request  : 将单个 bean 定义范围限定为单个 HTTP 请求的生命周期。也就是说，每个 HTTP 请求都有自己的 bean 实例，该 bean 实例是在单个 bean 定义的后面创建的。仅在 web-aware Spring 的上下文中有效ApplicationContext。
session   :将单个 bean 定义范围限定为 HTTP 的生命周期Session。仅在 web-aware Spring 的上下文中有效ApplicationContext。
application 应用:将单个 bean 定义范围限定为ServletContext. 仅在 web-aware Spring 的上下文中有效ApplicationContext。
websocket :将单个 bean 定义范围限定为WebSocket



#### IV.基于注解的容器配置


该@Required注解适用于 bean 属性 setter 方法，如下例所示：

@Required
public void setMovieFinder(MovieFinder movieFinder) {
   this.movieFinder = movieFinder;
}

@Autowired
应用于构造方法、set方法、混合使用等
非必须项    @Autowired(required = false)

注入顺序@Order 、@Priority
bean 可以实现org.springframework.core.Ordered接口或使用@Order或 标准@Priority注释。
否则，它们的顺序遵循容器中相应目标 bean 定义的注册顺序。



@Primary主要
@Primary表示当多个 bean 是自动装配到单值依赖项的候选时，应优先考虑特定 bean
自动装配可能会导致多个候选对象
多个对象主要选1


@Resource 
@Resource和@Autowired都是做bean的注入时使用。使用过程中，有时候@Resource 和 @Autowired可以替换使用；有时，则不可以。

@Resource是Java自己的注解，@Resource有两个属性是比较重要的，分是name和type；Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。

@Autowired是spring的注解，是spring2.5版本引入的，Autowired只根据type进行注入，不会去匹配name。如果涉及到type无法辨别注入对象时，那需要依赖@Qualifier或@Primary注解一起来修饰。



@Value注入外化属性：
@Value("${catalog.name}")

@PropertySource("classpath:application.properties")
catalog.name=MovieCatalog

默认值
public MovieRecommender(@Value("${catalog.name:defaultCatalog}") String catalog) {
this.catalog = catalog;
}

SpEL表达式
public MovieRecommender(@Value("#{systemProperties['user.catalog'] + 'Catalog' }") String catalog) {}

public MovieRecommender(@Value("#{{'Thriller': 100, 'Comedy': 300}}") Map<String, Integer> countOfMoviesPerCatalog) {}


@PostConstruct 初始化回调 构造
@PreDestroy    销毁回调


@Component
@Component是任何 Spring 管理的组件的通用构造型。

@Repository持久层dao、@Service服务层和@Controller表示层是@Component针对更具体用例 的特化



Spring 和javax注解对比
https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-standard-annotations-limitations
@Autowired @Inject

@Component  @Named / @ManagedBean

@Scope("singleton") @Singleton

@Qualifier @Qualifier / @Named

@Value
@Required
@Lazy

ObjectFactory Provider


#### IV. 基于 Java 的容器配置

完整的@Configuration 与“精简版”@Bean 模式



##  II.AOP 面向切面编程
https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-introduction-defn

Spring 框架的核心原则之一是非侵入性原则
AOP 概念
通过使用常规类（基于模式的方法Schema-based AOP Support）  或   使用@Aspect注解注解的常规类 （@AspectJ 风格）实现的。
schema-based approach 和@AspectJ annotation style

**两种实现:Spring AOP 、Full AspectJ**

使用可以工作的最简单的东西。Spring AOP 比使用完整的 AspectJ 更简单，因为不需要将 AspectJ 编译器/编织器引入您的开发和构建过程。
如果您需要建议不由 Spring 容器管理的对象（例如域对象，通常），您需要使用 AspectJ。






III. AOP 代理
Spring AOP 默认为 AOP 代理使用标准的 JDK 动态代理。这允许代理任何接口（或接口集）。
Spring AOP 使用 JDK 动态代理或 CGLIB (底层ASM)为给定的目标对象创建代理。




III. @AspectJ 支持
启用@AspectJ 支持

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

}



AOP 示例


Schema-based AOP Support

<aop:config>

    <aop:aspect id="myAspect" ref="aBean">

        <aop:pointcut id="businessService"
            expression="execution(* com.xyz.myapp.service.*.*(..)) &amp;&amp; this(service)"/>

        <aop:before pointcut-ref="businessService" method="monitor"/>

        ...
    </aop:aspect>

</aop:config>





I.探针等·······················



I.探针 Agent代理 :实现java字节码注入
JavaAgent(java代理)
II.在main premain > main

1.启动时，加入 -javaagent:C:/springloaded-1.2.5.RELEASE.jar -noverify
2.在main方法前执行  premain(String args, Instrumentation inst)方法
3.Instrumentation接口加入addTransformer 在类加载前后做 一些操作。


我理解的skywalking是通过 javaagent+bytebuddy+plugins方式实现的。


javaagent(Instrumentation)+bytebuddy

-----------------------------------------------------------------------------------------------------------

# I.动态代理、静态代理

**II.静态代理**
代理模式可以在不修改被代理对象的基础上，通过扩展代理类，进行一些功能的附加与增强。
值得注意的是，**代理类和被代理类应该共同实现一个接口，或者是共同继承某个类。**


**II.动态代理**
使用反射的方式利用 InvocationHandler类，实现invoke方法
java.lang.reflect Proxy
java.lang.reflect InvocationHandler
比如日志系统、事务、拦截器、权限控制等。这也就是AOP(面向切面编程)的基本原理。

-------------------------------------------------------------------------------------------------------

**Spring AOP**  
1.默认接口实现使用java动态代理
java.lang.reflect Proxy
java.lang.reflect InvocationHandler

2.无接口的类使用 AspectJ 切面J 

-------------------------------------javaagent java探针Java代理(虚拟机层面)------------------------------------------------------------
# **I.javaagent java探针(代理)**  jvm加载class文件时对字节码进行修改增强
Instrumentation仪器 相当于一个JVM级别的AOP


**I.javaagent java探针、代理**
https://www.cnblogs.com/rickiyang/p/11368932.html
JavaAgent 是JDK 1.5 以后引入的，也可以叫做Java代理(JVM级别虚拟机级别的代理)。
在JVM 加载class文件的时候 对class进行修改

**II.使用 javaagent 需要几个步骤：**
    1.定义一个 MANIFEST.MF 文件，必须包含 Premain-Class 选项，通常也会加入Can-Redefine-Classes 和 Can-Retransform-Classes 选项。
    2.创建一个Premain-Class 指定的类，类中包含 premain 方法，方法逻辑由用户自己确定。
    3.将 premain 的类和 MANIFEST.MF 文件打成 jar 包。
    4.使用参数 -javaagent: jar包路径 启动要代理的方法。

例skywalking

1.skywalking-agent.jar//META-INF//MANIFEST.MF
Premain-Class: org.apache.skywalking.apm.agent.SkyWalkingAgent
Can-Redefine-Classes: true
Can-Retransform-Classes: true

2.SkywalkingAgent.premain()

3.skywalking-agent.jar

4.启动参数
-Xms1024M -javaagent://opt/yrd_soft/skywalking-agent/skywalking-agent.jar  -Xms1024M 

**II.javaagent代理实现原理**

**1.premain方法会先于main方法执行。全部用户类加载都在探针之后。**

JVM 会先执行 premain 方法，大部分类加载都会通过该方法，**注意：是大部分，不是所有**。
当然，遗漏的主要是系统类，因为很多系统类先于 agent 执行，而用户类的加载肯定是会被拦截的。
java -javaagent:agent1.jar -javaagent:agent2.jar -jar MyProgram.jar

public static void premain(String agentArgs, Instrumentation inst) 带有Instrumentation优先级更高，成功以后就执行下边的方法
public static void premain(String agentArgs)


程序执行的顺序将会是：
MyAgent1.premain -> MyAgent2.premain -> MyProgram.main

**2.javaagent 探针 +结合字节码 可以对各个加载类进行修改。**

第三方的字节码编译工具，javassist，cglib(ASM)、javassist、byteBuddy等等来改写实现类。



------------------------------java agent代理 Instrumentation仪器  JVM的Attach机制----------------------------------------------------------------
**Instrumentation 仪器** :虚拟机JVM级别的代理。代码无感知 : JVM字节码加载的时候，对字节码进行修改。

可以用独立于应用程序之外的代理（agent）程序来监测和协助运行在JVM上的应用程序。

**JDK1.5 Instrumentation仪器 探针用只能main启动前用**
Instrumentation仪器仪表 1.5新增，支持在main方法启动之前启动，相当于系统级jvm的代理。
但是在实际的很多的情况下，我们没有办法在虚拟机启动之时就为其设定代理，这样实际上限制了instrument的应用

在JDK6中，针对这点做了改进，开发者可以在main开始执行以后，再开启自己的Instrucment程序

**JDK1.6 Instrumentation仪器 探针可以在 main启动后使用**  arthas 阿里巴巴 原理
JDK1.6之后提供了attach机制，工具类都处于com.sun.tools.attach包下，
VirtualMachine.attach方法attach到指定JVM进程上


JVM的Attach机制

----------------------------字节码增强------------------------------------------
# I.字节码增强
**I.字节码增强**
https://www.cnblogs.com/jackion5/p/10771609.html
https://github.com/dingsai88/SpringBootStudy/blob/master/img/AopAsmCglibAspectJ.png
Instrumentation java代理(JVM级别) 在类加载时修改class :可以使用字节码增强技术

ASM(cglib)、javassist帮助、byteBuddy、java proxy反射、Javassist

**II.ASM(CGLIB) 字节码指令 动态修改字节码文件** 创建class时字节码指令级别修改。不能动态修改
Spring AOP 无接口代理类时用CGLIB基于ASM
https://asm.ow2.io/

不过ASM在创建class字节码的过程中，操纵的级别是底层JVM的汇编指令级别，这要求ASM使用者要对class组织结构和JVM汇编指令有一定的了解。

**II.javassist 任意时间都能对字节码进行增强**  小日本工具 不需要字节码指令就能修改
可动态生成增强class
修改方法内容，增加方法等

https://zhuanlan.zhihu.com/p/141449080
官网
http://www.javassist.org/tutorial/tutorial.html

1. ClassPool：javassist的类池，使用ClassPool 类可以跟踪和控制所操作的类，它的工作方式与 JVM 类装载器非常相似
2. CtClass： CtClass提供了类的操作，如在类中动态添加新字段、方法和构造函数、以及改变类、父类和接口的方法。
3. CtField：类的属性，通过它可以给类创建新的属性，还可以修改已有的属性的类型，访问修饰符等
4. CtMethod：类中的方法，通过它可以给类创建新的方法，还可以修改返回类型，访问修饰符等， 甚至还可以修改方法体内容代码
5. CtConstructor：与CtMethod类似



**II.AspectJ** (Spring AOP [动态代理+CGLIB ASM] + AspectJ框架) 基于 apache BCEL框架汇编层
https://www.eclipse.org/aspectj/
https://www.jianshu.com/p/2e8409bc8c3b
https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-ataspectj

Java 中流行的 AOP（aspect-oriented programming） 编程扩展框架，其内部使用的是 BCEL框架 来完成其功能。


基于apache的BCEL(Byte Code Engineering Library)框架 汇编层



Byte Code Engineering Library(BCEL)
Apache Software Foundation 的 Jakarta 项目的一部分。
BCEL 是 Java classworking 最广泛使用的一种框架,它可以让您深入 JVM 汇编语言进行类操作的细节。
BCEL 与 Javassist 有不同的处理字节码方法，BCEL 在实际的 JVM 指令层次上进行操作(BCEL 拥有丰富的 JVM 指令级支持)
而 Javassist 所强调的源代码级别的工作。

横切关注点、切面（Aspect）、连接点（JoinPoint）、切入点（PointCut）、通知（Advice）


**II.bytebuddy**

获得甲骨文颁奖、基于ASM字节码指令级别










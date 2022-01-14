

**I.tomcat 组成**


## org.apache.catalina.Lifecycle 生命周期接口
## org.apache.catalina.Container 容器接口继承 Lifecycle





II.Connector 连接器 绑定多个端口   port="8080" 、port="8081"
可配置多个


II.容器分类
Tomcat内部有4个级别的容器，分别是Engine、Host、Context和Wrapper


Engine 1VS Host
Host  1vs   Context
Context 1vs Wrapper
Wrapper  1vs Servlet


II.Engine整个tomcat引擎(指定默认的Host域名) 

 


II.Host 多个域名,不同域名下，走不同配置(默认localhost)


II.Context 上下文 ： 对应webapps下的一个项目： yixin-data-platform-0.0.1-SNAPSHOT.war
context.xml 配置 /META-INF/context.xml














II.Wrapper

List<Servlet>
一个Servlet对应一个 Wrapper ,一个Wrapper对应多个 Servlet实例.



I.如何解析请求

http://localhost"8080/HelloServelet/servletDemo

Adapter组件 ：根据请求信息，生成request对象,找到该请求所对应的
Host、Context、wrapper 等信息设置给request

II.coyote包中的Reques只是包含了解析出来的http协议的数据
org.apache.coyote.Request
org.apache.coyote.Response

II.connector包中的Request才是真正Servlet容器中的HttpServletRequest
org.apache.catalina.connector.Request
org.apache.catalina.connector.Response

包含了完成请求需要的host,context和wrapper信息,在这里每一个wrapper其实都对应web.xml配置的一个Servlet


request=new Request();
request.setHost(host);
request.setContext(Context);
request.setWrapper(wrapper);

getEngine().getPipeline().getFirstValue().invoke(request);


II.Valve  阀门 :可以在任何容器类型中
Adapter组件

StandardEngineValve  ：Pipeline

StandardHostValve  ：Pipeline

StandardContextValve  ：Pipeline

StandardWrapperValve (servlet类)    ：Pipeline

ApplicationFilterChain.doFilter

javax.servlet.http.HttpServlet.service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
servlet.service(request, response);

if (method.equals(METHOD_GET)) {
} else if (method.equals(METHOD_POST)) {
} else if (method.equals(METHOD_OPTIONS)) {


Servlet实例:service方法(request,response)

![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/tomcat容器.png)

![RUNOOB 图标](https://github.com/dingsai88/SpringBootStudy/blob/master/img/tomcat容器调用关系.png)


# **II.获取字节流**
org.apache.catalina.startup.Tomcat.silences  NIO BIO配置
org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.maxThreads
tomcat配置

NIO启动入口
org.apache.tomcat.util.net.NioEndpoint.startInternal
NIO、BIO默认配置
org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory#DEFAULT_PROTOCOL




我们先从获取字节流开始，Tomcat底层是通过TCP协议，也就是Socket来获取网络数据的，那么从Socket上获取数据，就涉及到IO模型，在Tomcat8以后，就同时支持了NIO和BIO。
Connector

在Tomcat中，有一个组件叫做 Connector ，他就是专门用来接收Socket连接的，在Connector内部有一个组件叫ProtocolHandler，它有好几种实现：

Http11Protocol

Http11NioProtocol

Http11AprProtocol

##　III.Http11Protocol（BIO）

在Http11Protocol中存在一个组件，叫JIoEndpoint，而在JIoEndpoint中存在一个组件，
叫Acceptor，Acceptor是一个线程，这个线程会不停的从ServerSocket中获取Socket连接，每接收到一个Socket连接之后，
就会将这个Socket连接扔到一个线程池中，由线程池中的线程去处理Socket，包括从Socket中获取数据，将响应结果写到Socket中。

所以，如果大家现在用的是Tomcat中的BIO模式，如果要进行性能调优，就可以调整该线程池的大小，默认10个核心线程，最大为200个线程。

##　III.Http11NioProtocol（NIO） springBoot默认

再来看Http11NioProtocol，和Http11Protocol非常类似，在它的底层有一个NioEndpoint，NioEndpoint中也存在一个Acceptor线程，
但是需要注意的是，现在虽然是NIO模型，但是Acceptor线程在接收Socket连接时，并不是非阻塞的方式，仍然是通过阻塞的方式来接收Socket连接。

Acceptor线程每接收到一个Socket连接后，就会将该Socket连接注册给一个Poller线程，后续就由这个Poller线程来负责处理该Socket上读写事件，
默认情况下Tomcat中至少存在两个Poller线程，要么就是CPU核心数个Poller线程，值得注意的是，Poller线程不是通过线程池来实现的，
是通过一个Poller数组来实现的，因为在NIO模型下，一个Poller线程要负责处理多个Socket连接中的读写事件，
所以Acceptor线程在接收到一个Socket连接后，要能够比较方便的拿到Poller线程，如果用线程池，那么就不方便拿线程对象了，
而用Poller数组，就会轮询拿到Poller线程，并把接收到的Socket连接注册给Poller线程。


Acceptor线程负责接收Socket连接

Poller线程负责接收读写事件

线程池负责处理读写事件

所以，如果大家现在用的是Tomcat中的NIO模式，如果要进行性能调优，可以调整该Poller数组的大小，也可以调整线程池的大小。


##　相较于BIO模型的tomcat，NIO的优势分析：
1、BIO中的流程应该是接收到请求之后直接把请求扔给线程池去做处理，在这个情况下一个连接即需要一个线程来处理，线程既需要读取数据还需要处理请求，线程占用时间长，很容易达到最大线程

2、NIO的流程的不同点在于Poller类采用了多路复用模型，即Poller类只有检查到可读或者可写的连接时才把当前连接扔给线程池来处理，这样的好处是大大节省了连接还不能读写时的处理时间（如读取请求数据），也就是说NIO“读取socket并交给Worker中的线程”这个过程是非阻塞的，当socket在等待下一个请求或等待释放时，并不会占用工作线程，因此Tomcat可以同时处理的socket数目远大于最大线程数，并发性能大大提高。



# **II.解析字节流**
不同的IO模型只是表示从Socket上获取字节流的方式不同而已


Tomcat需要按照HTTP协议的格式来解析字节流：

浏览器或者HttpClient在发送数据时，同样需要按照Http协议来构造数据（字符串），然后将字符串转成字节发送出去，所以Tomcat解析字节流的逻辑就是：
从获得的第一个字节开始，遍历每个字节，当遇到空格时，那么之前所遍历到的字节数据就是请求方法
然后继续遍历每个字节，当遇到空格时，那么之前遍历到的字节数据就是URL
然后继续遍历每个字节，当遇到回车、换行符时，那么之前遍历到的字节数据就是协议版本，并且表示请求行遍历结束
然后继续遍历当遇到一个回车符和换行符时，那么所遍历的数据就是一个请求头
继续遍历当遍历到两个回车符和换行符时，那么所遍历的数据就是一个请求头，并且表示请求头全部遍历完毕
剩下的字节流数据就表示请求体

### III.TCP 粘包/半包Netty 全搞定：
封装成帧Framing:固定长度字段存个内容的长度信息 （推荐+） LengthFieldBasedFrameDecoder
TCP改成短连接，一个请求一个连接。（不推荐）
封装成帧Framing:固定长度 （不推荐） FixedLengthFrameDecoder
封装成帧Framing:分隔符 （推荐） DelimiterBasedFrameDecoder

### 有两种方式:
1.设置Content-Length：在发送请求时直接设置请求体的长度

2.设置Transfer-Encoding为chunk：也就是分块传输，在发送请求时，按如下格式来传输请求体，
[chunk size][\r\n][chunk data][\r\n][chunk size][\r\n][chunk data][\r\n][chunk size = 0][\r\n][\r\n]，
注意最后的chunk size=0和两个回车换行符，只要Tomcat解析到这些时，就表示接收到了最后一块，也就表示请求体结束了




大总结
经过两篇文章的分析，我们可以总结一下Tomcat处理请求的流程：

浏览器在请求一个Servlet时，会按照HTTP协议构造一个HTTP请求，通过Socket连接发送给Tomcat

Tomcat通过不同的IO模型都可以接收到Socket的字节流数据

接收到数据后，按HTTP协议解析字节流，得到HttpServletRequest对象

再通过HttpServletRequest对象，也就是请求信息，找到该请求对应的Host、Context、Wrapper

然后将请求交给Engine层处理

Engine层处理完，就会将请求交给Host层处理

Host层处理完，就会将请求交给Context层处理

Context层处理完，就会将请求交给Wrapper层处理

Wrapper层在拿到一个请求后，就会生成一个请求所要访问的Servlet实例对象

StandardWrapperValve (servlet类)    ：Pipeline  》 》ApplicationFilterChain.doFilter 过滤器

调用Servlet实例对象的service()方法，并把HttpServletRequest对象当做入参

从而就调用到Servlet所定义的逻辑。









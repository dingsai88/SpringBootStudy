

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





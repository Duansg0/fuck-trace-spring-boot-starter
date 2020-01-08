# fuck-trace-spring-boot-starter(摘要日志)

#### 前言
    没啥好说的

#### 配置

```java
spring.boot.trace.appName=api-facade
spring.boot.trace.digestDaoLogOpen=true
spring.boot.trace.traceDaoExecution=* com.melot.talkee.api.facade.dao..*.*(..)
spring.boot.trace.digestPvLogOpen=true
spring.boot.trace.tracePvExecution=* com.melot.talkee.api.facade.controller..*.*(..)
spring.boot.trace.digestFeignLogOpen=true
```
###### Feign

```java
//Feign的使用,要在启动类加入额外配置
@EnableFeignClients(basePackages = {"com.melot.planet"},defaultConfiguration = FeignDigestConfiguration.class)
```

###### Spring Cloud Config

```java
//已兼容配置中心,需要继承GobalConfigContext,并为digestSwitch全局开关赋值.
@RefreshScope
@Configuration
public class RefreshAutoConfig extends GobalConfigContext {
    
}
```

###### Custom Interceptor

```java
//可以根据需要实现自定义的拦截器,需要指定自定义的类型,
@TraceCustomInterceptor(customType = TraceCustomConstants.DAO)
public class CustomDaoInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //....
        return null;
    }
}
```

###### Pressure test

```
//压测数据的分析,需要在请求头中加入trace-loadTest
```

#### 使用

###### 内部打印操作

```java
LoggerFormatUtil.info(logger,"UserService get user info ,userId:{0} ,userName:{1}",10254, "Duansg");
```

###### 上下文操作相关

```java
TraceContext traceContext = TraceUtil.getTraceContext();//获取当前上下文对象
TraceUtil.setTraceContext(new TraceContext());//设置上下文
TraceUtil.setTraceContext(new TraceContext("eyJhbGciOiJIUzI1NiJ9"));//设置带TraceId的上下文
TraceUtil.clearTraceContext();//清理上下文
TraceContext traceContext = TraceUtil.cloneTraceContext();//克隆当前上下文对象,为了防止清理操作或者参数篡改
String traceId = TraceUtil.getTraceId();//获取当前上下文中的TraceId
```

###### 扩展信息操作相关

```java
String contextExtendParam = TraceUtil.getContextExtendParam("traceId");//获取指定的扩展信息
Map<String, String> contextExtendField = TraceUtil.getContextExtendField();//获取扩展信息
TraceUtil.putContextExtendParam("traceId","eyJhbGciOiJIUzI1NiJ9");//设置扩展信息
```


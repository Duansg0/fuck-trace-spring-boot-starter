# fuck-trace-spring-boot-starter(摘要日志)

#### 前言
    //TODO

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


# fuck-trace-spring-boot-starter(摘要日志)

#### 前言
    它能解决什么问题?
        1:对接ELK
        2:统计数据
        3:耗时优化
        4:问题排查
        5:链路跟踪

#### 配置

```java
spring.boot.trace.appName=api-facade //项目名称
spring.boot.trace.traceSwitch=true //PV-DAO开关
spring.boot.trace.traceDaoExecution=* com.melot.talkee.api.facade.dao..*.*(..)
spring.boot.trace.tracePvExecution=* com.melot.talkee.api.facade.controller..*.*(..)
spring.boot.trace.traceSwitchFeign=true //Feign开关
spring.boot.trace.traceSwitchDubbo=true //Dubbo开关
```
###### Feign

```java
//Feign的使用,要在启动类加入额外配置
@EnableFeignClients(basePackages = {"com.melot.planet"},defaultConfiguration = FeignDigestConfiguration.class)
```

###### Spring Cloud Config/XDiamond等

```java
//已兼容配置中心,需要在项目中实现如下操作
@Configuration
public class AutoRefreshConfiguration extends RefreshConfigPublish {

    @Override
    public void publish(String message) {
        //message format
        RefreshConfigModel build = new RefreshConfigModel.Builder()
                .buildTraceSwitch(true)
                .buildGobalSwitch(true)
                .buildTraceSwitchDubbo(true)
                .buildTraceSwitchFeign(false)
                .build(this);
        applicationEventPublisher.publishEvent(build);
    }
}
```

###### Custom Interceptor（已废弃,//TODO）

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

###### Dubbo

```java
//直接使用
```

###### Pressure test

```
//压测数据的分析,需要在请求头中加入key:trace-loadTest,value:custorm
(test)(c04338872a8046c689889ec1f4d55d97)(infra-user,/user/queryUser,50,springmvc,S)
```

#### 使用

###### 内部打印操作

```java
LoggerFormatUtil.info(logger,"UserService get user info ,userId:{0} ,userName:{1}",10254, "Duansg");
LoggerFormatUtil.debug(logger,"UserService get user info ,userId:{0} ,userName:{1}",10254, "Duansg");
LoggerFormatUtil.error(logger,"UserService get user info ,userId:{0} ,userName:{1}",10254, "Duansg");
LoggerFormatUtil.warn(logger,"UserService get user info ,userId:{0} ,userName:{1}",10254, "Duansg");
```

###### 上下文操作相关

```java
TraceContext traceContext = TraceUtil.getTraceContext();//获取当前上下文对象
TraceUtil.setTraceContext(new TraceContext());//设置上下文,默认生成traceId
TraceUtil.setTraceContext(new TraceContext("eyJhbGciOiJIUzI1NiJ9"));//设置带TraceId的上下文
TraceInitUtil.initTraceContext();//初始化上下文对象,并设置到全局变量中
TraceContext traceContext = TraceInitUtil.generateTraceContext();//构建上下文对象
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


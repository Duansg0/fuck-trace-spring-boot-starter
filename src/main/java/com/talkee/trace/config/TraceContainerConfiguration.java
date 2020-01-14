package com.talkee.trace.config;

import com.talkee.trace.TraceException;
import com.talkee.trace.TraceProperties;
import com.talkee.trace.annotation.TraceCustomInterceptor;
import com.talkee.trace.base.GobalConfigContext;
import com.talkee.trace.constants.TraceCustomConstants;
import com.talkee.trace.interceptor.SpringPvDigestInterceptor;
import com.talkee.trace.support.AssertSupport;
import com.talkee.trace.support.ClazzBuildSupport;
import com.talkee.trace.util.DynamicPropertyUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class TraceContainerConfiguration implements ApplicationContextAware, SmartInitializingSingleton {

    private static final Logger logger = LoggerFactory.getLogger(TraceContainerConfiguration.class);

    private ConfigurableApplicationContext applicationContext;

    private AtomicLong counter = new AtomicLong(0L);

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DynamicPropertyUtil.setApplicationContext(applicationContext);
        this.applicationContext = ((ConfigurableApplicationContext)applicationContext);
    }

    public void afterSingletonsInstantiated() {
//        TraceProperties traceProperties = DynamicPropertyUtil.getTraceProperties();
//        AssertSupport.isNotEmpty(traceProperties,"TraceProperties is Empty.");
//        String customIncerptor = traceProperties.getCustomIncerptor();
//        if (StringUtils.isNotBlank(customIncerptor)){
//            try {
//                //获取自定义拦截器的所有的实例的对象
////                //List<?> gobalConfigContexts = ClazzBuildSupport.newInstanceList(customIncerptor);
////                Class<?> clazz = Class.forName(customIncerptor);
////
////
////                //手动注册
////                GenericApplicationContext genericApplicationContext = (GenericApplicationContext)this.applicationContext;
////                DynamicMethodMatcherPointcut methodInterceptor = (DynamicMethodMatcherPointcut) clazz.newInstance();
////
////        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
////        pointcut.setExpression(String.format("execution(%s)", traceProperties.getTracePvExecution()));
//////        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
//////        advisor.setPointcut(pointcut);
//////        advisor.setAdvice(methodInterceptor);
////
////                //核心注册逻辑,所有的自定义的拦截器！
////               // gobalConfigContexts.forEach(object->{
////
////                    //删除现有的拦截器
////                System.out.println(clazz.getAnnotations().getClass().getName());
////
////                if (!clazz.isAnnotationPresent(TraceCustomInterceptor.class)){
////                    throw new TraceException("The TraceCustomInterceptor annotation does not exist.");
////                }
//////                TraceCustomInterceptor traceCustomInterceptor = clazz.getAnnotation(TraceCustomInterceptor.class);
//////                    AssertSupport.isNotBlank(traceCustomInterceptor.customType(),"");
//////                    switch (traceCustomInterceptor.customType()){
//////                        case TraceCustomConstants.DAO :
//////                            genericApplicationContext.removeBeanDefinition("daoDigestInterceptor"); break;
//////                        case TraceCustomConstants.PV :
//////                            BeanDefinitionRegistry beanDefReg = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
//////                            beanDefReg.getBeanDefinition("springPvDigestInterceptor");
//////                            beanDefReg.removeBeanDefinition("springPvDigestInterceptor");
//////                            //genericApplicationContext.removeBeanDefinition("springPvDigestInterceptor");
//////                            break;
//////                    }
////                BeanDefinitionBuilder hBaseBuilder = BeanDefinitionBuilder.genericBeanDefinition(DefaultPointcutAdvisor.class);
////                //配置相关
////                hBaseBuilder.addPropertyValue("pointcut",methodInterceptor);
////                hBaseBuilder.addPropertyValue("advice",new SpringPvDigestInterceptor());
////                String registerBeanName = String.format("%s_%s", "springPvDigestInterceptor" , Long.valueOf(this.counter.incrementAndGet()));
////                AbstractBeanDefinition beanDefinition = hBaseBuilder.getBeanDefinition();
////                genericApplicationContext.registerBeanDefinition("springPvDigestInterceptor_1",beanDefinition );
////                System.out.println(genericApplicationContext.getBean("springPvDigestInterceptor_1"));
////
////                //});
////            } catch (Exception e) {
////                logger.error(String.format("TraceContainerConfiguration throws an exception, the exception content is:%s",e.getMessage()));
////            }
////        }
    }

}

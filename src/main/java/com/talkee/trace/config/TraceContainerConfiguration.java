package com.talkee.trace.config;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.annotation.TraceCustomInterceptor;
import com.talkee.trace.base.AbstractTraceInterceptor;
import com.talkee.trace.base.GobalConfigContext;
import com.talkee.trace.constants.TraceCustomConstants;
import com.talkee.trace.interceptor.DaoDigestInterceptor;
import com.talkee.trace.interceptor.SpringPvDigestInterceptor;
import com.talkee.trace.support.ClazzBuildSupport;
import com.talkee.trace.util.DynamicPropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class TraceContainerConfiguration implements ApplicationContextAware, SmartInitializingSingleton {

    private ConfigurableApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DynamicPropertyUtil.setApplicationContext(applicationContext);
        this.applicationContext = ((ConfigurableApplicationContext)applicationContext);
    }

    public void afterSingletonsInstantiated() {

        TraceProperties traceProperties = DynamicPropertyUtil.getTraceProperties();
        String customIncerptor = traceProperties.getCustomIncerptor();
        try {
            //获取自定义拦截器的所有的实例的对象
            List<GobalConfigContext> gobalConfigContexts = ClazzBuildSupport.newInstanceList(customIncerptor);
            //注册
            //删除现有的拦截器


        } catch (Exception e) {
            e.printStackTrace();
        }


        DefaultListableBeanFactory autowireCapableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(TraceCustomInterceptor.class);
        if (!ObjectUtils.isEmpty(beans)&&!ObjectUtils.isEmpty(autowireCapableBeanFactory)) {
            for(Map.Entry<String, Object> entry : beans.entrySet()){
                String beanName = entry.getKey();
                Class<?> clazz = entry.getValue().getClass();
                TraceCustomInterceptor traceCustomInterceptor = clazz.getAnnotation(TraceCustomInterceptor.class);
                if (!ObjectUtils.isEmpty(clazz)){
                    try {
                        Object object = clazz.newInstance();
                        //优先使用注解属性加载自定义拦截器,没有使用类型加载,原因是比较懒,有空再实现。
                        if (StringUtils.isNotBlank(traceCustomInterceptor.customType())&&object instanceof AbstractTraceInterceptor){
                            switch (traceCustomInterceptor.customType()){
                                case TraceCustomConstants.DAO :
                                    //删除
                                    autowireCapableBeanFactory.removeBeanDefinition("daoDigestInterceptor");
                                    //注册自定义的拦截器
                                    //TODO
                                    break;
                                case TraceCustomConstants.PV :
                                    autowireCapableBeanFactory.removeBeanDefinition("springPvDigestInterceptor");
                                    //注册自定义的拦截器
                                    //TODO
                                    break;
                                case TraceCustomConstants.FEIGN :
                                    autowireCapableBeanFactory.removeBeanDefinition("feignDigestConfiguration");
                                    //注册自定义的拦截器
                                    //TODO
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

package com.talkee.trace.config;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.util.DynamicPropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
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
        TraceProperties traceProperties = DynamicPropertyUtil.getTraceProperties();
        if (!ObjectUtils.isEmpty(traceProperties)){
            String customIncerptor = traceProperties.getCustomIncerptor();


        }
    }

}

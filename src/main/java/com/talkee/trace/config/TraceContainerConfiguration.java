package com.talkee.trace.config;

import com.talkee.trace.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Slf4j
@Configuration
public class TraceContainerConfiguration implements ApplicationContextAware, SmartInitializingSingleton {

    private ConfigurableApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = ((ConfigurableApplicationContext)applicationContext);
    }

    public void afterSingletonsInstantiated() {
        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(Trace.class);
        if (beans != null) {
            for(Map.Entry<String, Object> entry : beans.entrySet()){
                entry.getKey();
                entry.getValue();
            }
        }
    }

}

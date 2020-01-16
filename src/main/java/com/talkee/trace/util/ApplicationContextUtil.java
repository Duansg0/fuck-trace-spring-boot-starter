package com.talkee.trace.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);

    private static ApplicationContext springContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setSpringContext(applicationContext);
    }

    private static void setSpringContext(ApplicationContext springContext) {
        ApplicationContextUtil.springContext = springContext;
    }

    public static Object getBean(String name){
        try {
            if (springContext.containsBean(name))
                return springContext.getBean(name);
        }catch (Exception e) {
            logger.error("getBean error, beanName[" + name + "]", e);
        }
        return null;
    }

    public static <T> T getBean(String name, Class<T> requiredType){
        try {
            return springContext.getBean(name, requiredType);
        }catch (Exception e) {
            logger.error("getBean error, beanName[" + name + "]", e);
        }
        return null;
    }
    public static <T> T getBean(Class<T> requiredType){
        try {
            return springContext.getBean(requiredType);
        } catch (Exception e) {
            logger.error("getBean error, beanName[" + requiredType.getName() + "]", e);
        }
        return null;
    }
}

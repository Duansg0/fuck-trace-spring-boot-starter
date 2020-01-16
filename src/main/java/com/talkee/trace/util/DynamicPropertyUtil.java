package com.talkee.trace.util;

import com.talkee.trace.TraceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * @author Duansg
 * @desc DynamicPropertyUtil
 * @date @date 2020-01-08 19:22:11
 */
public class DynamicPropertyUtil {

    /**
     * @desc
     */
    private static final Logger logger = LoggerFactory.getLogger(DynamicPropertyUtil.class);
    /**
     * @desc ApplicationContext
     */
    private static ApplicationContext applicationContext ;

    /**
     * @desc setApplicationContext
     * @param applicationContext
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        DynamicPropertyUtil.applicationContext = applicationContext;
    }

    /**
     * @desc get traceProperties
     * @return
     */
    public static TraceProperties getTraceProperties(){
        return applicationContext.getBean(TraceProperties.class);
    }

    /**
     * @desc get appName
     * @return
     */
    public static String getAppName() {
        TraceProperties traceProperties = applicationContext.getBean(TraceProperties.class);
        if (ObjectUtils.isEmpty(applicationContext)||ObjectUtils.isEmpty(traceProperties)){
            return null;
        }
        return traceProperties.getAppName();
    }
}

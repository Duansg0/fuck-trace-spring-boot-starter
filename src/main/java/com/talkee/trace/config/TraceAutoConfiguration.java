package com.talkee.trace.config;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.interceptor.DaoDigestInterceptor;
import com.talkee.trace.support.AssertSupport;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Duansg
 * @desc 全局自动配置类
 * @date 2019-12-31 17:18:12
 */
@Import({TraceContainerConfiguration.class})
@EnableConfigurationProperties({TraceProperties.class})
public class TraceAutoConfiguration {

    public static final String execution = "execution(%s)";

    /**
     * @desc Dao层拦截器自动注册
     * @param traceProperties
     * @return
     */
    @Bean(name = "daoDigestInterceptor")
    public DefaultPointcutAdvisor defaultPointcutAdvisor(TraceProperties traceProperties) {
        DaoDigestInterceptor interceptor = new DaoDigestInterceptor();
        AssertSupport.isNotBlank(traceProperties.getAppName(),"trace appName cannot be empty !");
        interceptor.setAppName(traceProperties.getAppName());
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        AssertSupport.isNotBlank(traceProperties.getTraceExecution(),"trace execution cannot be empty !");
        pointcut.setExpression(String.format(execution,traceProperties.getTraceExecution()));
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

}

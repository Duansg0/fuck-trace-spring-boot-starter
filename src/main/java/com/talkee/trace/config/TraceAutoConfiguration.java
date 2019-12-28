package com.talkee.trace.config;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.interceptor.DaoDigestInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({TraceContainerConfiguration.class})
@EnableConfigurationProperties({TraceProperties.class})
public class TraceAutoConfiguration {

    public static final String execution = "execution(%s)";

    @Bean(name = "DaoDigestInterceptor")
    public DefaultPointcutAdvisor defaultPointcutAdvisor(TraceProperties traceProperties) {
        DaoDigestInterceptor interceptor = new DaoDigestInterceptor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(String.format(execution,traceProperties.getTraceExecution()));
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

}

package com.talkee.trace.base;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.support.AssertSupport;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author Duansg
 * @desc
 * @date 2020-01-03 10:33：22
 */
public class InterceptorBuilder {

    /**
     *
     */
    public static final String execution = "execution(%s)";

    /**
     *
     * @param interceptor
     * @param traceProperties
     * @return
     */
    public static DefaultPointcutAdvisor build(AbstractTraceInterceptor interceptor, TraceProperties traceProperties) {
        AssertSupport.isNotBlank(traceProperties.getAppName(),"trace appName cannot be empty !");
        interceptor.setAppName(traceProperties.getAppName());
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        AssertSupport.isNotBlank(traceProperties.getTraceExecution(),"trace execution cannot be empty !");
        pointcut.setExpression(String.format(execution,traceProperties.getTraceMvcExecution()));
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}

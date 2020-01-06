package com.talkee.trace.base;

import com.talkee.trace.model.InterceptorInitInfoModel;
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
     * @param model
     * @return
     */
    public static DefaultPointcutAdvisor build(AbstractTraceInterceptor interceptor, InterceptorInitInfoModel model) {
        AssertSupport.isNotBlank(model.getAppName(),"trace appName cannot be empty !");
        interceptor.setAppName(model.getAppName());
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        AssertSupport.isNotBlank(model.getExecution(),"trace execution cannot be empty !");
        pointcut.setExpression(String.format(execution, model.getExecution()));
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }
}

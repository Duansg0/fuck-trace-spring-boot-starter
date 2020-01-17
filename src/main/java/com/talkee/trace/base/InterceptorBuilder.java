package com.talkee.trace.base;

import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.model.InterceptorInitInfoModel;
import com.talkee.trace.support.AssertSupport;
import com.talkee.trace.util.TraceUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author Duansg
 * @desc Interceptor builder.
 * @date 2020-01-03 22:33:22
 */
public class InterceptorBuilder {

    /**
     *  @desc Execution expression format.
     */
    public static final String execution = "execution(%s)";

    /**
     * @desc Build the execution
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
        advisor.setAdvice((MethodInterceptor) interceptor);
        return advisor;
    }
}

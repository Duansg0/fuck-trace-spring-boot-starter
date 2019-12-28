package com.talkee.trace.interceptor;

import com.talkee.trace.base.AbstractTraceInterceptor;
import com.talkee.trace.model.DalDigestModel;
import com.talkee.trace.util.LoggerFormatUtil;
import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;

@Data
public class DaoDigestInterceptor extends AbstractTraceInterceptor implements MethodInterceptor {

    /**
     * @desc 摘要日志
     */
    private static final Logger digestLogger = LoggerFactory.getLogger("DAL_DIGEST");
    /**
     * @desc 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DaoDigestInterceptor.class);

    private String appName;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            Object result = invocation.proceed();
            isSuccess = true;
            return result;
        } finally {
            try {
                String namespace =  AopUtils.getTargetClass(invocation.getThis()).getName();
                String actionName = invocation.getMethod().getName();
                String url = namespace + "." + actionName;
                long costTime = System.currentTimeMillis() - startTime;
                DalDigestModel dalDigestModel = new DalDigestModel(appName, url, isSuccess ? "S" : "F", costTime);
                logDigest(dalDigestModel, digestLogger);
            } catch (Throwable ignore) {
                LoggerFormatUtil.error(ignore, logger, "数据库摘要日志异常");
            }
        }
    }
}

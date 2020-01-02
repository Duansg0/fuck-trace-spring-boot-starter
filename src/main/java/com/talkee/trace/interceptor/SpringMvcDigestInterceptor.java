package com.talkee.trace.interceptor;

import com.talkee.trace.base.AbstractTraceInterceptor;
import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.enums.BoolEnum;
import com.talkee.trace.model.DaoDigestModel;
import com.talkee.trace.model.PvDigestModel;
import com.talkee.trace.util.LoggerFormatUtil;
import com.talkee.trace.util.TraceInitUtil;
import com.talkee.trace.util.TraceUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Duansg
 * @desc springMvc拦截器
 * @date 2020-01-02 15:13:22
 */
public class SpringMvcDigestInterceptor extends AbstractTraceInterceptor implements MethodInterceptor {

    private static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.MVC_DIGEST_LOG);

    private static final Logger logger = LoggerFactory.getLogger(SpringMvcDigestInterceptor.class);


    /**
     * 解析压测参数
     */
    public void resolveLoadTest(HttpServletRequest request) {
        if (request != null
                && StringUtils.isNotBlank(request.getHeader(TraceConstants.HTTP_LOAD_TEST_KEY))) {
            String loadTest = request.getHeader(TraceConstants.HTTP_LOAD_TEST_KEY);
            LoggerFormatUtil.debug(logger, "loadTest={0}", loadTest);
            TraceUtil.putContextExtendParam(TraceConstants.LOAD_TEST_KEY, loadTest);
        }
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        boolean isSuccess = false;
        try {
            //统一上下文前置操作
            TraceInitUtil.initTraceContext();
            Object result = invocation.proceed();
            isSuccess = true;
            return result;
        } finally {
            try {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                //resolveLoadTest();
                long costTime = System.currentTimeMillis() - startTime;
                PvDigestModel pvDigestModel = new PvDigestModel(request.getRequestURI(), costTime, TraceConstants.MVC_FRAM_NAME, BoolEnum.get(isSuccess), appName);
                //打印摘要日志
                logDigest(pvDigestModel, digestLogger);
            } catch (Throwable ignore) {
                LoggerFormatUtil.error(ignore, logger, "数据库摘要日志异常!");
            }
            TraceUtil.clearTraceContext();
        }
    }
}

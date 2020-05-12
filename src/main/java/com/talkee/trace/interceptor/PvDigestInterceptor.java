package com.talkee.trace.interceptor;

import com.talkee.trace.base.AbstractTraceInterceptor;
import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.enums.BoolEnum;
import com.talkee.trace.model.PvDigestModel;
import com.talkee.trace.model.TraceContext;
import com.talkee.trace.util.LoggerFormatUtil;
import com.talkee.trace.util.TraceInitUtil;
import com.talkee.trace.util.TraceUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Duansg
 * @desc Pv摘要日志拦截器
 * @date 2020-01-02 19:13:22
 */
public class PvDigestInterceptor extends AbstractTraceInterceptor implements MethodInterceptor{

    /**
     * @desc Digestpv's logger.
     */
    private static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.PV_DIGEST_LOG);

    /**
     * @desc PvDigestInterceptor's logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(PvDigestInterceptor.class);

    /**
     * @desc Analyze the pressure parameters
     */
    public void resolveLoadTest(HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(request) && StringUtils.isNotBlank(request.getHeader(TraceConstants.HTTP_LOAD_TEST_KEY))) {
            String loadTest = request.getHeader(TraceConstants.HTTP_LOAD_TEST_KEY);
            LoggerFormatUtil.debug(logger, "loadTest={0}", loadTest);
            TraceUtil.putContextExtendParam(TraceConstants.LOAD_TEST_KEY, loadTest);
        }
    }

    /**
     * @desc invoke
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //解析压测请求。
        resolveLoadTest(request);
        boolean isSuccess = false;
        try {
            TraceInitUtil.initTraceContext();
            Object result = invocation.proceed();
            isSuccess = true;
            return result;
        } finally {
            try {
                long costTime = System.currentTimeMillis() - startTime;
                PvDigestModel pvDigestModel = new PvDigestModel(request.getRequestURI(), costTime, TraceConstants.MVC_FRAM_NAME, BoolEnum.get(isSuccess), appName);
                logDigest(pvDigestModel, digestLogger);
            } catch (Throwable ignore) {
                LoggerFormatUtil.error(ignore, logger, "PV摘要日志发生了一个异常。请关注!");
            }
            TraceUtil.clearTraceContext();
        }
    }

}

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
 * @desc SpringMvc interceptor
 * @date 2020-01-02 19:13:22
 */
public class SpringPvDigestInterceptor extends AbstractTraceInterceptor implements MethodInterceptor{

    /**
     * @desc Digestpv's logger.
     */
    private static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.MVC_DIGEST_LOG);

    /**
     * @desc SpringPvDigestInterceptor's logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(SpringPvDigestInterceptor.class);

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
        boolean isSuccess = false;
        try {
            TraceInitUtil.initTraceContext();
            Object result = invocation.proceed();
            isSuccess = true;
            return result;
        } finally {
            resolveLoadTest(request);
            try {
                long costTime = System.currentTimeMillis() - startTime;
                PvDigestModel pvDigestModel = new PvDigestModel(request.getRequestURI(), costTime, TraceConstants.MVC_FRAM_NAME, BoolEnum.get(isSuccess), appName);
                logDigest(pvDigestModel, digestLogger);
            } catch (Throwable ignore) {
                LoggerFormatUtil.error(ignore, logger, "Trace pv digest log exception");
            }
            TraceUtil.clearTraceContext();
        }
    }

}

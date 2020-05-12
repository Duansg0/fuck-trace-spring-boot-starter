package com.talkee.trace.config;

import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.model.TraceContext;
import com.talkee.trace.util.LoggerFormatUtil;
import com.talkee.trace.util.TraceUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Duansg
 * @desc Feign摘要日志配置
 * @date 2020-01-07 22:12:03
 */

@Data
public class FeignDigestConfiguration implements RequestInterceptor {
    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(FeignDigestConfiguration.class);
    /**
     * @desc 应用名称
     */
    private String appName;

    /**
     * @desc Constructor
     * @param appName
     */
    public FeignDigestConfiguration(String appName) {
        this.appName = appName;
    }
    /**
     * @desc Constructor
     */
    public FeignDigestConfiguration() {

    }
    /**
     * @desc
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (TraceUtil.getPerprotey(TraceConstants.DIGEST_LOG_SWITCH_FEIGN) && !ObjectUtils.isEmpty(attributes)){
                HttpServletRequest request = attributes.getRequest();
                //获取请求地址
                String requestURI = ObjectUtils.isEmpty(request)? null: request.getRequestURI();
                //获取上下文中的traceId
                String traceId = TraceUtil.getTraceId();
                //获取调用地址
                String url = requestTemplate.url();
                //解析是否携带了压测参数
                String extendField = TraceUtil.getContextExtendParam(TraceConstants.LOAD_TEST_KEY);
                if (StringUtils.isNotBlank(extendField)){
                    requestTemplate.header(TraceConstants.LOAD_TEST_KEY, extendField);
                }
                if (StringUtils.isBlank(traceId)){
                    //上下文中没有traceId,初始化TraceContext
                    TraceContext traceContext = new TraceContext();
                    traceId = traceContext.getTraceId();
                    //保证后续操作上下文可以获取
                    TraceUtil.setTraceContext(traceContext);
                }
                LoggerFormatUtil.info(logger,"Feign调用拦截,({0})({1}),appName={2},请求路径为:{3},调用路径为{4}",StringUtils.defaultIfBlank(extendField, TraceConstants.EMPTY_DIGEST_VALUE),traceId,appName,requestURI,url);
                requestTemplate.header(TraceConstants.TRACE_ID_KEY, traceId);
            }
        }catch (Throwable ignore) {
            LoggerFormatUtil.error(ignore, logger, "Feign摘要日志异常!");
        }
    }
}

package com.talkee.trace.interceptor;

import com.talkee.trace.model.TraceContext;
import com.talkee.trace.util.LoggerFormatUtil;
import com.talkee.trace.util.TraceUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
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
public class FeignDigestConfiguration implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(FeignDigestConfiguration.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (!ObjectUtils.isEmpty(attributes)){
                HttpServletRequest request = attributes.getRequest();
                String requestURI = request.getRequestURI();
                String traceId = TraceUtil.getTraceId();//获取上下文中的traceId
                if (StringUtils.isBlank(traceId)){
                    //上下文中没有traceId,此处打印调用链路,并初始化traceId
                    TraceContext traceContext = new TraceContext();
                    traceId = traceContext.getTraceId();
                    //保证后续操作上下文可以获取
                    TraceUtil.setTraceContext(traceContext);
                }
                String url = requestTemplate.url();
                LoggerFormatUtil.info(logger,"Feign调用拦截,({0})请求路径为:{1},调用路径为{2}",traceId,requestURI,url);
                requestTemplate.header("traceId", traceId);
            }
        }catch (Throwable ignore) {
            LoggerFormatUtil.error(ignore, logger, "Feign摘要日志异常!");
        }
    }
}

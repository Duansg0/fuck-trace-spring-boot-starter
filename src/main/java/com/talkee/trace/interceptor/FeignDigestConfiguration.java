package com.talkee.trace.interceptor;

import com.talkee.trace.util.LoggerFormatUtil;
import com.talkee.trace.util.TraceUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            HttpServletRequest request = attributes.getRequest();
            String traceId = TraceUtil.getTraceId();//获取上下文中的traceId
            if (StringUtils.isBlank(traceId)){

            }
            if (StringUtils.isNotBlank(traceId)){
                requestTemplate.header("traceId", traceId);
            }else {

            }
        }catch (Throwable ignore) {
            LoggerFormatUtil.error(ignore, logger, "Feign摘要日志异常!");
        }
    }
}

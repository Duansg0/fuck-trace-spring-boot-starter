package com.talkee.trace.util;

import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.model.TraceContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Duansg
 * @desc RpcContextAttachmentUtil
 * @date @date 2020-01-08 19:22:11
 */
public class RpcContextAttachmentUtil {

    /**
     * @desc traceContext conversion rpcAttachments
     * @param traceContext
     * @return
     */
    public static Map<String, String> assemAttachments(TraceContext traceContext){
        Map<String, String> attachments = new HashMap<String, String>();
        if (ObjectUtils.isEmpty(traceContext)) {
            return attachments;
        }
        String prefix = TraceConstants.RPC_ATTACHMENT_PREFIX;
        attachments.put(prefix + TraceConstants.TRACE_ID_KEY, traceContext.getTraceId());
        for (Map.Entry<String, String> entry : traceContext.getExtendField().entrySet()){
            attachments.put(prefix + entry.getKey(), entry.getValue());
        }
        return attachments;
    }

    /**
     * @desc rpcAttachments conversion traceContext
     * @param attachments
     * @return
     */
    public static TraceContext parseAttachments(Map<String, String> attachments){
        String prefix = TraceConstants.RPC_ATTACHMENT_PREFIX;
        String dubboTraceIdKey = prefix + TraceConstants.TRACE_ID_KEY;
        if (ObjectUtils.isEmpty(attachments) || StringUtils.isBlank(attachments.get(dubboTraceIdKey))) {
            return null;
        }
        TraceContext traceContext = new TraceContext(attachments.get(dubboTraceIdKey));
        for (Map.Entry<String, String> entry : attachments.entrySet()){
            if (StringUtils.startsWith(entry.getKey(), prefix)
                    && !StringUtils.equals(entry.getKey(), dubboTraceIdKey)) {
                String realKey = StringUtils.substring(entry.getKey(), prefix.length());
                traceContext.getExtendField().put(realKey, entry.getValue());
            }
        }
        return traceContext;
    }
}

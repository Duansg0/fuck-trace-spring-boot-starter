package com.talkee.trace.util;

import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.model.TraceContext;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RpcContextAttachmentUtil {

    /**
     * traceContext -> rpcAttachments
     *
     * @param traceContext
     * @return
     */
    public static Map<String, String> assemAttachments(TraceContext traceContext){
        Map<String, String> attachments = new HashMap<String, String>();
        //非空校验
        if (traceContext == null) {
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
     * rpcAttachments -> traceContext
     *
     * @param attachments
     * @return
     */
    public static TraceContext parseAttachments(Map<String, String> attachments){
        String prefix = TraceConstants.RPC_ATTACHMENT_PREFIX;
        String dubboTraceIdKey = prefix + TraceConstants.TRACE_ID_KEY;
        if (attachments == null || StringUtils.isBlank(attachments.get(dubboTraceIdKey))) {
            return null;
        }
        String traceId = attachments.get(dubboTraceIdKey);
        TraceContext traceContext = new TraceContext(traceId);

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

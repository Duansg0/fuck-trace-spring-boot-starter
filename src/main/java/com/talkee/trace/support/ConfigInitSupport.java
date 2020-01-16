package com.talkee.trace.support;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.util.TraceUtil;

/**
 * @author Duansg
 * @desc
 * @date 2020-01-16 20:56:10
 */
public class ConfigInitSupport {
    /**
     * @desc
     * @param traceProperties
     */
    public static void init(TraceProperties traceProperties) {
        AssertSupport.isNotEmpty(traceProperties,"traceProperties exception.");
        TraceUtil.setPerproteyByTrue(traceProperties.isTraceSwitch(),TraceConstants.DIGEST_LOG_SWITCH,String.valueOf(traceProperties.isTraceSwitch()));
        TraceUtil.setPerproteyByTrue(traceProperties.isTraceSwitchFeign(),TraceConstants.DIGEST_LOG_SWITCH_FEIGN,String.valueOf(traceProperties.isTraceSwitchFeign()));
        TraceUtil.setPerproteyByTrue(traceProperties.isTraceSwitchDubbo(),TraceConstants.DIGEST_LOG_SWITCH_DUBBO,String.valueOf(traceProperties.isTraceSwitchFeign()));
        TraceUtil.setPerprotey(TraceConstants.DIGEST_SWITCH,String.valueOf(false));
    }
}

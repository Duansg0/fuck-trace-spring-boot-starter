package com.talkee.trace.base;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.support.AssertSupport;
import com.talkee.trace.util.TraceUtil;

/**
 * @author Duansg
 * @desc 自动配置基类.
 * @date 2019-12-26 20:42:02
 */
public abstract class AbstractConfiguration {
    /**
     * @param traceProperties
     */
    protected void init(TraceProperties traceProperties) {
        AssertSupport.isNotEmpty(traceProperties,"未能获取到配置信息.");
        TraceUtil.setPerprotey(TraceConstants.DIGEST_SWITCH,String.valueOf(traceProperties.isTraceSwitch()));
        //TODO
        TraceUtil.setPerproteyByTrue(traceProperties.isTraceSwitchFeign(),
                TraceConstants.DIGEST_LOG_SWITCH_FEIGN,String.valueOf(traceProperties.isTraceSwitchFeign()));
        TraceUtil.setPerproteyByTrue(traceProperties.isTraceSwitchDubbo(),
                TraceConstants.DIGEST_LOG_SWITCH_DUBBO,String.valueOf(traceProperties.isTraceSwitchDubbo()));
    }
}

package com.talkee.trace.util;

import com.talkee.trace.model.TraceContext;
import com.talkee.trace.support.TraceContextSupport;

/**
 * @author Duansg
 * @desc TraceUtil.
 * @date 2020-01-08 19:22:11
 */
public class TraceInitUtil {
    /**
     * @desc init traceContext
     */
    public static void initTraceContext() {
        TraceContextSupport.initTraceContext();
    }

    /**
     * @desc generate traceContext
     * @return TraceContext
     */
    public static TraceContext generateTraceContext(){
        return TraceContextSupport.generateTraceContext();
    }
}

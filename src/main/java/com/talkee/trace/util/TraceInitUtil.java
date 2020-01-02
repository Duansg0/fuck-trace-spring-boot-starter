package com.talkee.trace.util;

import com.talkee.trace.model.TraceContext;
import com.talkee.trace.support.TraceContextSupport;

public class TraceInitUtil {
    /**
     * 初始化当前统一上下文
     */
    public static void initTraceContext() {
        TraceContextSupport.initTraceContext();
    }

    /**
     * 构造TraceContext
     *
     * @return TraceContext
     */
    public static TraceContext generateTraceContext(){
        return TraceContextSupport.generateTraceContext();
    }
}

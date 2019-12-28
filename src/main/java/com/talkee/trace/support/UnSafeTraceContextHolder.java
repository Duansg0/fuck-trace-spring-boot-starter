package com.talkee.trace.support;

import com.talkee.trace.model.TraceContext;

public class UnSafeTraceContextHolder {

    /** ThreadLocal的TraceContext  */
    private static final ThreadLocal<TraceContext> TRACE_CONTEXT = new ThreadLocal<TraceContext>();

    /**
     * 获取当前统一上下文
     *
     * @return TraceContext
     */
    protected static TraceContext getTraceContext(){
        return TRACE_CONTEXT.get();
    }

    /**
     * 初始化当前统一上下文
     */
    protected static void initTraceContext() {
        TRACE_CONTEXT.set(generateTraceContext());
    }

    /**
     * 克隆当前统一上下文，深拷贝
     *
     * @return TraceContext
     */
    protected static TraceContext cloneTraceContext() {
        TraceContext traceContext = TRACE_CONTEXT.get();
        return (traceContext != null) ? traceContext.clone() : null;
    }

    /**
     * 清理统一上下文
     */
    protected static void clearTraceContext() {
        //set null性能优于remove
        TRACE_CONTEXT.set(null);
    }

    /**
     * 设置当前统一上下文
     *
     * @param traceContext
     */
    protected static void setTraceContext(TraceContext traceContext) {
        TRACE_CONTEXT.set(traceContext);
    }

    /**
     * 构造TraceContext
     *
     * @return TraceContext
     */
    protected static TraceContext generateTraceContext(){
        TraceContext traceContext = new TraceContext();
        return traceContext;
    }
}

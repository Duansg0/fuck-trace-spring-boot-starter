package com.talkee.trace.support;

import com.talkee.trace.model.TraceContext;
/**
 * @author Duansg
 * @desc TraceContextSupport
 * @date 2020-01-06 20:10:11
 */
public class UnSafeTraceContextHolder {

    /**
     * @desc
     */
    private static final ThreadLocal<TraceContext> TRACE_CONTEXT = new ThreadLocal<TraceContext>();

    /**
     * @desc Get the current unified context
     * @return TraceContext
     */
    protected static TraceContext getTraceContext(){
        return TRACE_CONTEXT.get();
    }

    /**
     * @desc Initializes the current unified context
     */
    protected static void initTraceContext() {
        TRACE_CONTEXT.set(generateTraceContext());
    }

    /**
     * @desc Clone the current unified context, deep copy
     * @return TraceContext
     */
    protected static TraceContext cloneTraceContext() {
        TraceContext traceContext = TRACE_CONTEXT.get();
        return (traceContext != null) ? traceContext.clone() : null;
    }

    /**
     *  @desc Clean up uniform context
     */
    protected static void clearTraceContext() {
        //Set null performance better than remove.
        TRACE_CONTEXT.set(null);
    }

    /**
     * @desc Set the current unified context
     * @param traceContext
     */
    protected static void setTraceContext(TraceContext traceContext) {
        TRACE_CONTEXT.set(traceContext);
    }

    /**
     * @desc build context
     * @return TraceContext
     */
    protected static TraceContext generateTraceContext(){
        TraceContext traceContext = new TraceContext();
        return traceContext;
    }
}

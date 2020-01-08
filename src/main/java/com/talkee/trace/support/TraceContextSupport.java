package com.talkee.trace.support;

import com.talkee.trace.model.TraceContext;

import java.util.Map;
/**
 * @author Duansg
 * @desc TraceContextSupport
 * @date 2020-01-08 23:10:33
 */
public class TraceContextSupport {

    /**
     * @desc Get the nullable traceId of the current uniform context
     * @return traceId
     */
    public static String getTraceId() {
        TraceContext traceContext = UnSafeTraceContextHolder.getTraceContext();
        return (traceContext == null) ? null : traceContext.getTraceId();
    }

    /**
     * @desc Get the current unified context
     * @return TraceContext
     */
    public static TraceContext getTraceContext(){
        return UnSafeTraceContextHolder.getTraceContext();
    }

    /**
     * @desc Initializes the current unified context
     */
    public static void initTraceContext() {
        UnSafeTraceContextHolder.initTraceContext();
    }

    /**
     * @desc Clone the current unified context, deep copy
     * @return TraceContext
     */
    public static TraceContext cloneTraceContext() {
        try {
            return UnSafeTraceContextHolder.cloneTraceContext();
        } catch(Throwable ignore) {
            //gitignore
            return null;
        }
    }

    /**
     * @desc Clean up uniform context
     */
    public static void clearTraceContext() {
        UnSafeTraceContextHolder.clearTraceContext();
    }

    /**
     * @desc Set the current unified context
     * @param traceContext
     */
    public static void setTraceContext(TraceContext traceContext) {
        UnSafeTraceContextHolder.setTraceContext(traceContext);
    }

    /**
     * @desc build context
     * @return TraceContext
     */
    public static TraceContext generateTraceContext(){
        return UnSafeTraceContextHolder.generateTraceContext();
    }

    /**
     * @desc Get expansion parameters
     * @param key
     * @return value
     */
    public static String getContextExtendParam(String key){
        TraceContext traceContext = UnSafeTraceContextHolder.getTraceContext();
        return (traceContext == null) ? null : traceContext.getExtendField().get(key);
    }

    /**
     * @desc Set expansion parameters
     * @param key
     * @param value
     */
    public static void putContextExtendParam(String key, String value){
        TraceContext traceContext = UnSafeTraceContextHolder.getTraceContext();
        if (traceContext != null){
            traceContext.getExtendField().put(key, value);
        }
    }

    /**
     * @desc Get expansion map of the business context.
     * @return
     */
    public static Map<String, String> getContextExtendField(){
        TraceContext traceContext = UnSafeTraceContextHolder.getTraceContext();
        return (traceContext == null) ? null : traceContext.getExtendField();
    }
}

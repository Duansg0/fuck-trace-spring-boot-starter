package com.talkee.trace.support;

import com.talkee.trace.model.TraceContext;

import java.util.Map;

public class TraceContextSupport {

    /**
     * 获取当前统一上下文的traceId，可空
     *
     * @return traceId
     */
    public static String getTraceId() {
        TraceContext traceContext = UnSafeTraceContextHolder.getTraceContext();
        return (traceContext == null) ? null : traceContext.getTraceId();
    }

    /**
     * 获取当前统一上下文
     *
     * @return TraceContext
     */
    public static TraceContext getTraceContext(){
        return UnSafeTraceContextHolder.getTraceContext();
    }

    /**
     * 初始化当前统一上下文
     */
    public static void initTraceContext() {
        UnSafeTraceContextHolder.initTraceContext();
    }

    /**
     * 克隆当前统一上下文，深拷贝
     *
     * @return TraceContext
     */
    public static TraceContext cloneTraceContext() {
        try {
            return UnSafeTraceContextHolder.cloneTraceContext();
        } catch(Throwable ignore) {
            //抛出异常不应该影响业务
            return null;
        }
    }

    /**
     * 清理统一上下文
     */
    public static void clearTraceContext() {
        UnSafeTraceContextHolder.clearTraceContext();
    }

    /**
     * 设置当前统一上下文
     *
     * @param traceContext
     */
    public static void setTraceContext(TraceContext traceContext) {
        UnSafeTraceContextHolder.setTraceContext(traceContext);
    }

    /**
     * 构造TraceContext
     *
     * @return TraceContext
     */
    public static TraceContext generateTraceContext(){
        return UnSafeTraceContextHolder.generateTraceContext();
    }

    /**
     * 获取拓展参数
     *
     * @param key
     * @return value
     */
    public static String getContextExtendParam(String key){
        TraceContext traceContext = UnSafeTraceContextHolder.getTraceContext();
        return (traceContext == null) ? null : traceContext.getExtendField().get(key);
    }

    /**
     * 设置拓展参数
     *
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
     * 获取业务上下文的拓展map
     *
     * @return
     */
    public static Map<String, String> getContextExtendField(){
        TraceContext traceContext = UnSafeTraceContextHolder.getTraceContext();
        return (traceContext == null) ? null : traceContext.getExtendField();
    }
}

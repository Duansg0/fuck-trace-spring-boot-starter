package com.talkee.trace.util;

import com.talkee.trace.model.TraceContext;
import com.talkee.trace.support.TraceContextSupport;
import java.util.Map;

/**
 * @author Duansg
 * @desc TraceUtil.
 * @date 2020-01-08 19:22:11
 */
public class TraceUtil {

    /**
     * @desc Gets the nullable traceId of the current uniform context
     * @return traceId
     */
    public static String getTraceId() {
        return TraceContextSupport.getTraceId();
    }

    /**
     * @desc Gets the current uniform context, nullable
     * @return
     */
    public static TraceContext getTraceContext(){
        return TraceContextSupport.getTraceContext();
    }

    /**
     * @desc Clean up the current unified context
     */
    public static void clearTraceContext() {
        TraceContextSupport.clearTraceContext();
    }

    /**
     * @desc Clone the current unified context
     * @return
     */
    public static TraceContext cloneTraceContext() {
        return TraceContextSupport.cloneTraceContext();
    }

    /**
     * @desc Sets the current unified context
     * @param traceContext
     */
    public static void setTraceContext(TraceContext traceContext) {
        TraceContextSupport.setTraceContext(traceContext);
    }

    /**
     * @desc Obtain expansion parameters
     * @param key
     * @return value
     */
    public static String getContextExtendParam(String key){
        return TraceContextSupport.getContextExtendParam(key);
    }

    /**
     * @desc Set expansion parameters
     * @param key
     * @param value
     */
    public static void putContextExtendParam(String key, String value){
        TraceContextSupport.putContextExtendParam(key, value);
    }

    /**
     * @desc Get an extended map of the business context
     * @return
     */
    public static Map<String, String> getContextExtendField(){
        return TraceContextSupport.getContextExtendField();
    }

}

package com.talkee.trace.util;

import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.model.TraceContext;
import com.talkee.trace.support.TraceContextSupport;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class TraceUtil {

    /**
     * 获取当前统一上下文的traceId，可空
     *
     * @return traceId
     */
    public static String getTraceId() {
        return TraceContextSupport.getTraceId();
    }

    /**
     * 获取当前统一上下文，可空
     *
     * @return
     */
    public static TraceContext getTraceContext(){
        return TraceContextSupport.getTraceContext();
    }

    /**
     * 清理当前统一上下文
     */
    public static void clearTraceContext() {
        TraceContextSupport.clearTraceContext();
    }

    /**
     * 克隆当前统一上下文
     *
     * @return
     */
    public static TraceContext cloneTraceContext() {
        return TraceContextSupport.cloneTraceContext();
    }

    /**
     * 设置当前统一上下文
     *
     * @param traceContext
     */
    public static void setTraceContext(TraceContext traceContext) {
        TraceContextSupport.setTraceContext(traceContext);
    }

    /**
     * 获取拓展参数
     *
     * @param key
     * @return value
     */
    public static String getContextExtendParam(String key){
        return TraceContextSupport.getContextExtendParam(key);
    }

    /**
     * 设置拓展参数
     *
     * @param key
     * @param value
     */
    public static void putContextExtendParam(String key, String value){
        TraceContextSupport.putContextExtendParam(key, value);
    }

    /**
     * 获取业务上下文的拓展map
     *
     * @return
     */
    public static Map<String, String> getContextExtendField(){
        return TraceContextSupport.getContextExtendField();
    }

    /**
     * 判断是否是压测环境
     *
     * @return
     */
    public static boolean isLoadTest() {
        return DynamicPropertyUtil.getProperty(TraceConstants.LOAD_TEST_SWITCH, false)
                && StringUtils.endsWithIgnoreCase("true", getContextExtendParam(TraceConstants.LOAD_TEST_KEY));
    }
}

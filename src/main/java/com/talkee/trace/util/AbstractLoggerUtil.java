package com.talkee.trace.util;

import com.talkee.trace.constants.TraceConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class AbstractLoggerUtil {

    /** 摘要日志: ERROR_DIGEST */
    protected static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.ERROR_DIGEST_LOG);

    /**
     * desc: 记录错误摘要日志
     *
     */
    protected static void logErrorDigest(String errorMsg){
        try {
            StackTraceElement[] stackElements = new Throwable().getStackTrace();
            StringBuilder builder = new StringBuilder();
            String traceId = TraceUtil.getTraceId();
            traceId = StringUtils.isBlank(traceId) ? TraceConstants.EMPTY_DIGEST_VALUE : traceId;
            builder.append(TraceConstants.LEFT_DIGEST_CHAR).append(traceId);
            builder.append(TraceConstants.RIGHT_DIGEST_CHAR).append(TraceConstants.LEFT_DIGEST_CHAR);
            if (stackElements != null && stackElements.length > 2) {
                StackTraceElement stackElement = stackElements[2];
                //添加应用名称
                builder.append(StringUtils.defaultIfBlank(geneAppName(), TraceConstants.EMPTY_DIGEST_VALUE));
                builder.append(",").append(stackElement.getClassName());
                builder.append(",").append(stackElement.getMethodName());
                builder.append(",").append(stackElement.getLineNumber());
            }
            builder.append(TraceConstants.RIGHT_DIGEST_CHAR);
            builder.append(TraceConstants.LEFT_DIGEST_CHAR);
            builder.append(StringUtils.defaultIfBlank(errorMsg, TraceConstants.EMPTY_DIGEST_VALUE));
            builder.append(TraceConstants.RIGHT_DIGEST_CHAR);
            digestLogger.error(builder.toString());
        } catch (Throwable ignore) {
            //忽略，不影响业务
        }
    }

    /**
     * desc: 获取appName
     *
     * @return
     */
    protected static String geneAppName() {
        String dubboAppName = System.getProperty(TraceConstants.DUBBO_APP_NAME_KEY);
        //优先获取dubbo.appName
        return StringUtils.isBlank(dubboAppName) ? System.getProperty(TraceConstants.APP_NAME_KEY) : dubboAppName;
    }

    /**
     * 构造Trace信息
     *
     * @return
     */
    protected static String generateTraceString(){
        StringBuilder builder = new StringBuilder();
        String traceId = TraceUtil.getTraceId();
        traceId = StringUtils.isBlank(traceId) ? TraceConstants.EMPTY_DIGEST_VALUE : traceId;
        builder.append("[").append(traceId).append("]");
        return builder.toString();
    }

    /**
     * @desc 获取调用堆栈信息
     * @return
     */
    public static String geneInvokeTraceMsg() {
        try {
            StackTraceElement[] stes = Thread.currentThread().getStackTrace();
            return MessageFormat.format("[{0}.{1}({2})]", stes[4].getClassName(), stes[4].getMethodName(), stes[4].getLineNumber());
        } catch (Throwable t) {
            return "[-]";
        }
    }
}

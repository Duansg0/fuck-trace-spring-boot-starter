package com.talkee.trace.util;

import com.talkee.trace.constants.TraceConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.MessageFormat;

/**
 * @author Duansg
 * @desc AbstractLoggerUtil
 * @date 2020-01-08 19:22:11
 */
public class AbstractLoggerUtil {

    /**
     *
     */
    protected static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.ERROR_DIGEST_LOG);

    /**
     * @desc Record the error in this paper,
     * @param errorMsg
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
                builder.append(StringUtils.defaultIfBlank(DynamicPropertyUtil.getAppName(), TraceConstants.EMPTY_DIGEST_VALUE));
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
            //ignore
        }
    }

    /**
     * @desc Wrapper trace info.
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
     * @desc Gets the stackTrace info.
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

package com.talkee.trace.util;

import com.talkee.trace.constants.TraceConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.MessageFormat;

public class LoggerFormatUtil extends AbstractLoggerUtil {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LoggerFormatUtil.class);

    /**
     * debug日志输出，需要占位index，JDK标准MessageFormat
     *
     * 示例： debug(logger, "({0}={1})", "key", "value");
     *
     * @param logger   日志实例，非空
     * @param format   日志内容格式，非空
     * @param params   参数列表
     */
    public static void debug(Logger logger, String format, Object... params){
        if (logger != null && logger.isDebugEnabled()) {
            logger.debug(traceWrap(format, params));
        }
    }

    /**
     * info日志输出，需要占位index，JDK标准MessageFormat
     *
     * 示例： info(logger, "({0}={1})", "key", "value");
     *
     * @param logger   日志实例，非空
     * @param format   日志内容格式，非空
     * @param params   参数列表
     */
    public static void info(Logger logger, String format, Object... params){
        if (logger != null && logger.isInfoEnabled()) {
            logger.info(traceWrap(format, params));
        }
    }



    /**
     * warn日志输出，需要占位index，JDK标准MessageFormat
     *
     * 示例：warn(logger, "({0}={1})", "key", "value");
     *
     * @param logger   日志实例，非空
     * @param format   日志内容格式，非空
     * @param params   参数列表
     */
    public static void warn(Logger logger, String format, Object... params){
        if (logger != null && logger.isWarnEnabled()) {
            logger.warn(traceWrap(format, params));
        }
    }

    /**
     * warn日志输出，需要占位index，JDK标准MessageFormat
     *
     * 示例：warn(throwable, logger, "({0}={1})", "key", "value");
     *
     * @param throwable 异常
     * @param logger    日志实例，非空
     * @param format    日志内容格式，非空
     * @param params    参数列表
     */
    public static void warn(Throwable throwable, Logger logger, String format, Object... params){
        if (logger != null && logger.isWarnEnabled()) {
            logger.warn(traceWrap(format, params), throwable);
        }
    }

    /**
     * error日志输出，需要占位index，JDK标准MessageFormat
     *
     * 示例：error(logger, "({0}={1})", "key", "value");
     *
     * @param logger   日志实例，非空
     * @param format   日志内容格式，非空
     * @param params   参数列表
     */
    public static void error(Logger logger, String format, Object... params){
        if (logger != null && logger.isErrorEnabled()) {
            //记录错误摘要
            logErrorDigest(formatMsg(format, params));
            logger.error(traceWrap(format, params));
        }
    }

    /**
     * error日志输出，需要占位index，JDK标准MessageFormat
     *
     * 示例：error(throwable, logger, "({0}={1})", "key", "value");
     *
     * @param throwable 异常
     * @param logger    日志实例，非空
     * @param format    日志内容格式，非空
     * @param params    参数列表
     */
    public static void error(Throwable throwable, Logger logger, String format, Object... params){
        if (logger != null && logger.isErrorEnabled()) {
            //记录错误摘要
            logErrorDigest(formatMsg(format, params));
            logger.error(traceWrap(format, params), throwable);
        }
    }

    /**
     * 包装Trace信息
     *
     * @param format  日志内容格式，非空
     * @param params  参数列表
     * @return
     */
    public static String traceWrap(String format , Object... params) {
        StringBuilder builder = new StringBuilder();
        builder.append(geneInvokeTraceMsg());
        builder.append(generateTraceString()).append(TraceConstants.POINT_DIGEST_CHAR);
        String formatMessage = format;
        //校验message非空
        if (StringUtils.isNotBlank(format)) {
            try{
                formatMessage = MessageFormat.format(format, params);
            }catch(Throwable ignore){
                //不处理该异常
                logger.warn(builder.toString() + "MessageFormat日志格式不正确，请关注，" + format);
            }
        }
        builder.append(formatMessage);
        return builder.toString();
    }

    /**
     * @desc 格式化msg
     * @param format
     * @param params
     * @return
     */
    public static String formatMsg(String format , Object... params) {
        String formatMessage = format;
        //校验message非空
        if (StringUtils.isNotBlank(format)) {
            try{
                formatMessage = MessageFormat.format(format, params);
            }catch(Throwable ignore){
                //不处理该异常
            }
        }
        return formatMessage;
    }
}

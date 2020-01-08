package com.talkee.trace.util;

import com.talkee.trace.constants.TraceConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import java.text.MessageFormat;

/**
 * @author Duansg
 * @desc LoggerFormatUtil
 * @date 2020-01-08 19:22:11
 */
public class LoggerFormatUtil extends AbstractLoggerUtil {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggerFormatUtil.class);

    /**
     * @desc Debuglog output，placeholders are needed index , JDK standard messageFormat
     * @sample debug(logger, "({0}={1})", "key", "value");
     * @param logger    logger, not null
     * @param format    Log content format, not null
     * @param params
     */
    public static void debug(Logger logger, String format, Object... params){
        if (!ObjectUtils.isEmpty(logger) && logger.isDebugEnabled()) {
            logger.debug(traceWrap(format, params));
        }
    }

    /**
     * @desc Infolog output，placeholders are needed index , JDK standard messageFormat
     * @sample info(logger, "({0}={1})", "key", "value");
     * @param logger    logger, not null
     * @param format    Log content format, not null
     * @param params
     */
    public static void info(Logger logger, String format, Object... params){
        if (logger != null && logger.isInfoEnabled()) {
            logger.info(traceWrap(format, params));
        }
    }

    /**
     * @desc Warnlog output，placeholders are needed index , JDK standard messageFormat
     * @sample warn(logger, "({0}={1})", "key", "value");
     * @param logger    logger, not null
     * @param format    Log content format, not null
     * @param params
     */
    public static void warn(Logger logger, String format, Object... params){
        if (logger != null && logger.isWarnEnabled()) {
            logger.warn(traceWrap(format, params));
        }
    }

    /**
     * @desc Warnlog output，placeholders are needed index , JDK standard messageFormat
     * @sample warn(throwable, logger, "({0}={1})", "key", "value");
     * @param throwable Exception
     * @param logger    logger, not null
     * @param format    Log content format, not null
     * @param params
     */
    public static void warn(Throwable throwable, Logger logger, String format, Object... params){
        if (logger != null && logger.isWarnEnabled()) {
            logger.warn(traceWrap(format, params), throwable);
        }
    }

    /**
     * @desc Errorlog output，placeholders are needed index , JDK standard messageFormat
     * @sample error(logger, "({0}={1})", "key", "value");
     * @param logger   logger, not null
     * @param format   Log content format, not null
     * @param params
     */
    public static void error(Logger logger, String format, Object... params){
        if (logger != null && logger.isErrorEnabled()) {
            //Record the error in this paper,
            logErrorDigest(formatMsg(format, params));
            logger.error(traceWrap(format, params));
        }
    }

    /**
     * @desc Errorlog output，placeholders are needed index , JDK standard messageFormat
     * @sample error(throwable, logger, "({0}={1})", "key", "value");
     * @param throwable Exception
     * @param logger    logger, not null
     * @param format    Log content format, not null
     * @param params
     */
    public static void error(Throwable throwable, Logger logger, String format, Object... params){
        if (logger != null && logger.isErrorEnabled()) {
            //Record the error in this paper,
            logErrorDigest(formatMsg(format, params));
            logger.error(traceWrap(format, params), throwable);
        }
    }

    /**
     * @desc Wrapper trace
     * @param format  Log content format, not null
     * @param params
     * @return
     */
    public static String traceWrap(String format , Object... params) {
        StringBuilder builder = new StringBuilder();
        //2020-01-07 18:13:26.677  INFO 28128 --- [nio-8908-exec-1] c.m.p.i.u.s.impl.UserServiceImpl
        builder.append(geneInvokeTraceMsg());
        //[eyjhbgcioijiuzi1nij9]->
        builder.append(generateTraceString()).append(TraceConstants.POINT_DIGEST_CHAR);
        String formatMessage = format;
        if (StringUtils.isNotBlank(format)) {
            try{
                formatMessage = MessageFormat.format(format, params);
            }catch(Throwable ignore){
                //ignore
                logger.warn(builder.toString() + "MessageFormat log format is not correct, please pay attention，" + format);
            }
        }
        builder.append(formatMessage);
        return builder.toString();
    }

    /**
     * @desc formatMsg
     * @param format
     * @param params
     * @return
     */
    public static String formatMsg(String format , Object... params) {
        String formatMessage = format;
        if (StringUtils.isNotBlank(format)) {
            try{
                formatMessage = MessageFormat.format(format, params);
            }catch(Throwable ignore){
                //ignore
            }
        }
        return formatMessage;
    }
}

package com.talkee.trace.base;

import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.model.DigestModel;
import com.talkee.trace.util.DynamicPropertyUtil;
import com.talkee.trace.util.TraceInitUtil;
import com.talkee.trace.util.TraceUtil;
import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author Duansg
 * @desc Global summary log print base class.
 * @date 2020-01-08 20:12:22
 */
@Data
public abstract class AbstractTraceInterceptor {
    /**
     * @desc
     */
    protected boolean digestLogOpen = true;
    /**
     * @desc
     */
    public String appName;

    /**
     */
    public void preTrace() {
        //初始化统一上下文
        TraceInitUtil.initTraceContext();
    }

    public void postTrace() {
        //清理统一上下文
        TraceUtil.clearTraceContext();
    }

    /**
     * Digest log printing.
     * @param digestModel
     * @param digestLogger
     */
    protected void logDigest(DigestModel digestModel, Logger digestLogger) {
        //判断摘要日志全局总开关 ，校验参数
        if (digestLogOpen && digestModel != null){
            StringBuilder builder = new StringBuilder();
            String traceId = TraceUtil.getTraceId();
            traceId = StringUtils.isBlank(traceId) ? TraceConstants.EMPTY_DIGEST_VALUE : traceId;
            //判断压测参数.
            String extendField = TraceUtil.getContextExtendParam(TraceConstants.LOAD_TEST_KEY);
            if (StringUtils.isNotBlank(extendField)){
                builder.append(TraceConstants.LEFT_DIGEST_CHAR)
                        .append(extendField)
                        .append(TraceConstants.RIGHT_DIGEST_CHAR);
            }
            builder.append(TraceConstants.LEFT_DIGEST_CHAR).append(traceId).append(TraceConstants.RIGHT_DIGEST_CHAR);
            builder.append(digestModel.toString());
            digestLogger.info(builder.toString());
        }
    }
}

package com.talkee.trace.base;

import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.model.DigestModel;
import com.talkee.trace.util.DynamicPropertyUtil;
import com.talkee.trace.util.TraceUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

@Data
public abstract class AbstractTraceInterceptor {

    protected boolean digestLogOpen = true;

    public String appName;

    protected void logDigest(DigestModel digestModel, Logger digestLogger) {
        //摘要日志开关
        boolean digestLogSwitch = DynamicPropertyUtil.getProperty(TraceConstants.DIGEST_LOG_SWITCH, true);
        //判断摘要日志开关 && 校验参数
        if (digestLogSwitch && digestLogOpen && digestModel != null){
            StringBuilder builder = new StringBuilder();
            String traceId = TraceUtil.getTraceId();
            traceId = StringUtils.isBlank(traceId) ? TraceConstants.EMPTY_DIGEST_VALUE : traceId;
            builder.append(TraceConstants.LEFT_DIGEST_CHAR).append(traceId).append(TraceConstants.RIGHT_DIGEST_CHAR);
            builder.append(digestModel.toString());
            digestLogger.info(builder.toString());
        }
    }
}

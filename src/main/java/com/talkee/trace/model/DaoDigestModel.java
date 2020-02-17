package com.talkee.trace.model;

import com.talkee.trace.constants.TraceConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.text.MessageFormat;

/**
 * @author Duansg
 * @desc Dao摘要日志数据模型
 * @date 2020-01-07 21:42:12
 */
@Data
public class DaoDigestModel extends DigestModel{

    /**
     * @desc 应用名称
     */
    private String appName;

    /**
     * @desc className + method
     */
    private String url;

    /**
     * @desc 有参构造.
     * @param appName
     * @param url
     * @param resultCode
     * @param costTime
     */
    public DaoDigestModel(String appName, String url, String resultCode, long costTime) {
        super(resultCode, costTime);
        this.url = url;
        this.appName = appName;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return MessageFormat.format("({0},{1},{2},{3})",
                StringUtils.defaultIfBlank(appName, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(url, TraceConstants.EMPTY_DIGEST_VALUE), String.valueOf(costTime),
                StringUtils.defaultIfBlank(resultCode, TraceConstants.EMPTY_DIGEST_VALUE));
    }
}

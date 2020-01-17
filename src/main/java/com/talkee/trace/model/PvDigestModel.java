package com.talkee.trace.model;

import java.text.MessageFormat;

import com.talkee.trace.constants.TraceConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Duansg
 * @desc
 * @date 2020-01-15 20:35:22
 */
@Data
public class PvDigestModel extends DigestModel {

    /**
     * url
     */
    private String url;

    /**
     * 框架名称，SpringMvc、Struts2
     */
    private String framName;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 空构造函数
     * <p>
     * Creates a new instance of PvDigestModel.
     */
    public PvDigestModel() {

    }

    /**
     * 构造函数
     * <p>
     * Creates a new instance of PvDigestModel.
     *
     * @param url
     * @param costTime
     * @param framName
     * @param resultCode
     * @param appName
     */
    public PvDigestModel(String url, long costTime, String framName, String resultCode, String appName) {
        super(resultCode, costTime);
        this.url = url;
        this.framName = framName;
        this.appName = appName;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        //(appName,url,costTime,framName,resultCode)
        return MessageFormat.format("({0},{1},{2},{3},{4})",
                StringUtils.defaultIfBlank(appName, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(url, TraceConstants.EMPTY_DIGEST_VALUE), String.valueOf(costTime),
                StringUtils.defaultIfBlank(framName, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(resultCode, TraceConstants.EMPTY_DIGEST_VALUE));
    }
}

package com.talkee.trace.model;

import com.talkee.trace.constants.TraceConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.text.MessageFormat;

@Data
public class DalDigestModel extends DigestModel{
    /**
     * 应用名称
     * */
    private String appName;

    /**
     * dao class + method
     * */
    private String url;

    /**
     * Creates a new instance of DalDigestModel.
     *
     * @param appName
     * @param url
     * @param resultCode
     * @param costTime
     */
    public DalDigestModel(String appName, String url, String resultCode, long costTime) {
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

package com.talkee.trace.model;

import lombok.Data;

@Data
public class DigestModel {

    /**
     * 耗时，ms
     */
    protected long costTime;

    /**
     * 结果码
     */
    protected String resultCode;

    /**
     * 无参构造函数
     * Creates a new instance of DigestModel.
     */
    public DigestModel() {}

    /**
     * 构造函数
     * Creates a new instance of DigestModel.
     *
     * @param resultCode
     * @param costTime
     */
    public DigestModel(String resultCode, long costTime) {
        this.resultCode = resultCode;
        this.costTime = costTime;
    }
}

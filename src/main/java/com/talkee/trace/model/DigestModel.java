package com.talkee.trace.model;

import lombok.Data;

@Data
public class DigestModel {

    /**
     * @desc 耗时，ms
     */
    protected long costTime;

    /**
     * @desc 结果码
     */
    protected String resultCode;

    /**
     * @desc 无参构造函数
     */
    public DigestModel() {}

    /**
     * @desc 构造函数
     * @param resultCode
     * @param costTime
     */
    public DigestModel(String resultCode, long costTime) {
        this.resultCode = resultCode;
        this.costTime = costTime;
    }

}

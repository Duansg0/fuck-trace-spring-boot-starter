package com.talkee.trace;

import lombok.Data;

/**
 * @author Duansg
 * @desc 自定义异常
 * @date 2019-12-16
 */
@Data
public class TraceException extends RuntimeException {
    /**
     * @desc 错误码
     */
    private Integer code;
    /**
     * @desc 描述
     */
    private String msg;

    public TraceException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public TraceException(String msg){
        super(msg);
        this.msg = msg;
    }
}

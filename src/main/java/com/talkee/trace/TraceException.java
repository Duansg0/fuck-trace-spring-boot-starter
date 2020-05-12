package com.talkee.trace;

import lombok.Data;

/**
 * @author Duansg
 * @desc 自定义异常。
 * @date 2019-12-16 18:44:21
 */
@Data
public class TraceException extends RuntimeException {

    /**
     * @desc 异常码.
     */
    private Integer code;
    /**
     * @desc 描述信息.
     */
    private String msg;
    /**
     * @desc 有参构造.
     * @param code
     * @param msg
     */
    public TraceException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    /**
     * @desc 有参构造.
     * @param msg
     */
    public TraceException(String msg){
        super(msg);
        this.msg = msg;
    }
}

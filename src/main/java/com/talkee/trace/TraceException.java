package com.talkee.trace;

import lombok.Data;

/**
 * @author Duansg
 * @desc Custom exception
 * @date 2019-12-16 18:44:21
 */
@Data
public class TraceException extends RuntimeException {
    //TEST
    /**
     * @desc Exception code.
     */
    private Integer code;
    /**
     * @desc Describe
     */
    private String msg;
    /**
     * @desc There are parameter constructors.
     * @param code
     * @param msg
     */
    public TraceException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    /**
     * @desc There are parameter constructors.
     * @param msg
     */
    public TraceException(String msg){
        super(msg);
        this.msg = msg;
    }
}

package com.talkee.trace.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class TraceContext implements java.io.Serializable, Cloneable{

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /** 统一上下文ID */
    private final String traceId;

    /** 统一上下文拓展字段 */
    private final Map<String, String> extendField = new HashMap<String, String>();

    /**
     * 无参构造函数
     *
     * Creates a new instance of TraceContext.
     */
    public TraceContext(){
        this.traceId = UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 带traceId的构造函数
     *
     * Creates a new instance of TraceContext.
     *
     * @param traceId
     */
    public TraceContext(String traceId){
        traceId = (StringUtils.isBlank(traceId)) ?
                UUID.randomUUID().toString().replace("-", "") : traceId;
        this.traceId = traceId;
    }

    /**
     * 深拷贝
     *
     * throws RuntimeException
     */
    public TraceContext clone(){
        TraceContext cloneTraceContext = null;
        try {
            cloneTraceContext = (TraceContext) super.clone();
        } catch (Throwable e){
            //没有实现Cloneable接口或者强转子类型时抛出，理论上不会出现
            throw new RuntimeException("clone TraceContext exception.", e);
        }
        return cloneTraceContext;
    }

    /**
     * 重写toString方法
     *
     * @see java.lang.Object#toString()
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(traceId).append(",");
        builder.append(extendField).append("]");
        return builder.toString();
    }
}

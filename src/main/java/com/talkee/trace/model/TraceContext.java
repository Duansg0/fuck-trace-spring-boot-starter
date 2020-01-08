package com.talkee.trace.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Duansg
 * @desc TraceContextSupport
 * @date 2020-01-08 23:12:11
 */
@Data
public class TraceContext implements java.io.Serializable, Cloneable{

    /**
     * @desc serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @desc traceId
     */
    private final String traceId;

    /**
     * @desc Uniform context extension field
     */
    private final Map<String, String> extendField = new HashMap<String, String>();

    /**
     * @desc Creates a new instance of TraceContext.
     */
    public TraceContext(){
        this.traceId = UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @desc Creates a new instance of TraceContext.
     * @param traceId
     */
    public TraceContext(String traceId){
        traceId = (StringUtils.isBlank(traceId)) ?
                UUID.randomUUID().toString().replace("-", "") : traceId;
        this.traceId = traceId;
    }

    /**
     * @desc clone
     * @desc throws RuntimeException
     */
    public TraceContext clone(){
        TraceContext cloneTraceContext = null;
        try {
            cloneTraceContext = (TraceContext) super.clone();
        } catch (Throwable e){
            //Thrown when no Cloneable interface or strong rotor type is implemented, which theoretically does not occur.
            throw new RuntimeException("clone TraceContext exception.", e);
        }
        return cloneTraceContext;
    }

    /**
     * @desc Override
     * @see java.lang.Object#toString()
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(traceId).append(",");
        builder.append(extendField).append("]");
        return builder.toString();
    }
}

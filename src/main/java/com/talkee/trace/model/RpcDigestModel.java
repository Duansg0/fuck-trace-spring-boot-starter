package com.talkee.trace.model;

import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.enums.InvokeSideTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import java.text.MessageFormat;

/**
 * @Description RPC摘要日志模型
 *
 * 	 结果码 (-1:成功，0:UNKNOWN_EXCEPTION,1:NETWORK_EXCEPTION,
 *   2:TIMEOUT_EXCEPTION,3:BIZ_EXCEPTION,4:FORBIDDEN_EXCEPTION,5:SERIALIZATION_EXCEPTION)
 */
public class RpcDigestModel extends DigestModel {

    /**
     * 客户端名称
     */
    @Getter
    @Setter
    private String clientName;

    /**
     * 服务端名称
     */
    @Getter
    @Setter
    private String serverName;

    /**
     * 服务名称
     */
    @Getter
    @Setter
    private String service;

    /**
     * 方法名称
     */
    @Getter
    @Setter
    private String method;

    /**
     * 服务所属group
     */
    @Getter
    @Setter
    private String group;

    /**
     * 版本号
     */
    @Getter
    @Setter
    private String version;

    /**
     * 客户端host
     */
    @Getter
    @Setter
    private String clientHost;

    /**
     * 服务端host
     */
    @Getter
    @Setter
    private String serverHost;

    /**
     * 调用端类型
     */
    @Getter
    @Setter
    private InvokeSideTypeEnum invokeSide;

    /**
     * 构造函数
     * <p>
     * Creates a new instance of RpcDigestModel.
     */
    public RpcDigestModel() {
    }

    /**
     * 构造函数
     * <p>
     * Creates a new instance of RpcDigestModel.
     */
    public RpcDigestModel(String clientName, String serverName, String service,
                          String method, String group, String version, String clientHost,
                          String serverHost, long costTime, String resultCode, InvokeSideTypeEnum invokeSide) {
        super(resultCode, costTime);
        this.clientName = clientName;
        this.serverName = serverName;
        this.service = service;
        this.method = method;
        this.group = group;
        this.version = version;
        this.clientHost = clientHost;
        this.serverHost = serverHost;
        this.invokeSide = invokeSide;
    }

    /**
     * 重写toString
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        //(clientName,serverName,service,method,group,version,costTime,resultCode,invokeSide,clientHost,serverHost)
        return MessageFormat.format("({0},{1},{2},{3},{4},{5},{6},{7},{8},{9},{10})",
                StringUtils.defaultIfBlank(clientName, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(serverName, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(service, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(method, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(group, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(version, TraceConstants.EMPTY_DIGEST_VALUE),
                String.valueOf(costTime),
                String.valueOf(resultCode),
                StringUtils.defaultIfBlank((invokeSide == null) ? "" : invokeSide.getName(),
                        TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(clientHost, TraceConstants.EMPTY_DIGEST_VALUE),
                StringUtils.defaultIfBlank(serverHost, TraceConstants.EMPTY_DIGEST_VALUE));
    }
}
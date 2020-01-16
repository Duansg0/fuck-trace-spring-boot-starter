package com.talkee.trace.base;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.rpc.*;
import com.talkee.trace.TraceProperties;
import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.enums.InvokeSideTypeEnum;
import com.talkee.trace.model.RpcDigestModel;
import com.talkee.trace.util.DynamicPropertyUtil;
import com.talkee.trace.util.LoggerFormatUtil;
import com.talkee.trace.util.TraceUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author Duansg
 * @desc
 * @date
 */
public abstract class AbstractTraceFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTraceFilter.class);
    /**
     * @desc 记录摘要日志
     * @param invoker
     * @param invocation
     * @param clientHost
     * @param serverHost
     * @param start
     * @param resultCode
     * @param digestLogger
     * @param invokeSide
     * @param clientName
     * @param serverName
     */
    protected void logRpcDigest(Invoker<?> invoker, Invocation invocation, String clientHost,
                                String serverHost, long start, int resultCode, Logger digestLogger, InvokeSideTypeEnum invokeSide,
                                String clientName, String serverName) {
        try {
            if (TraceUtil.getPerprotey(TraceConstants.DIGEST_LOG_SWITCH_DUBBO)&&TraceUtil.getPerprotey(TraceConstants.DIGEST_SWITCH)) {
                RpcDigestModel rpcDigestModel = builderRpcDigestModel(invoker, invocation,
                        clientHost, serverHost, start, resultCode, invokeSide, clientName, serverName);
                StringBuilder builder = new StringBuilder();
                String traceId = TraceUtil.getTraceId();
                traceId = StringUtils.isBlank(traceId) ? TraceConstants.EMPTY_DIGEST_VALUE : traceId;
                builder.append(TraceConstants.LEFT_DIGEST_CHAR).append(traceId).append(TraceConstants.RIGHT_DIGEST_CHAR);
                builder.append(rpcDigestModel.toString());
                digestLogger.info(builder.toString());
            }
        } catch (Throwable t) {
            LoggerFormatUtil.error(logger, "记录rpc摘要日志异常.");
        }
    }

    /**
     * @desc 构造RpcDigestModel
     * @param invoker
     * @param invocation
     * @param clientName
     * @param serverName
     * @param clientHost
     * @param serverHost
     * @param start
     * @param resultCode
     * @param invokeSide
     * @param clientName
     * @param serverName
     * @return
     */
    protected RpcDigestModel builderRpcDigestModel(Invoker<?> invoker, Invocation invocation, String clientHost,
                                                   String serverHost, long start, int resultCode, InvokeSideTypeEnum invokeSide,
                                                   String clientName, String serverName){
        RpcDigestModel rpcDigestModel = new RpcDigestModel();
        rpcDigestModel.setClientName(clientName);
        rpcDigestModel.setServerName(serverName);
        String service = getInvokerUrlParam(invoker, Constants.INTERFACE_KEY);
        rpcDigestModel.setService(service);
        String method = invocation.getMethodName();
        rpcDigestModel.setMethod(method);
        String group = getInvokerUrlParam(invoker, Constants.GROUP_KEY);
        rpcDigestModel.setGroup(group);
        String version = getInvokerUrlParam(invoker, Constants.VERSION_KEY);
        rpcDigestModel.setVersion(version);
        rpcDigestModel.setCostTime(System.currentTimeMillis() - start);
        //错误码转换
        String resultCodeString = (resultCode == -1) ? "S" : String.valueOf(resultCode);
        rpcDigestModel.setResultCode(resultCodeString);
        rpcDigestModel.setInvokeSide(invokeSide);
        rpcDigestModel.setClientHost(clientHost);
        rpcDigestModel.setServerHost(serverHost);
        return rpcDigestModel;
    }

    /**
     * @desc 获取RpcContextAttachment
     * @param paramKey
     * @return
     */
    public String getRpcContextAttachmentParam(String paramKey){
        try{
            return RpcContext.getContext().getAttachment(paramKey);
        }catch(Throwable t) {
            LoggerFormatUtil.error(logger, "获取RpcContextAttachment异常,key={0}", paramKey);
            return null;
        }
    }

    /**
     * @desc 获取InvokerUrlParam
     * @param invoker
     * @return
     */
    public String getInvokerUrlParam(Invoker<?> invoker, String paramKey){
        try{
            return invoker.getUrl().getParameter(paramKey);
        }catch(Throwable t) {
            LoggerFormatUtil.error(logger, "获取InvokerUrlParam异常,key={0}", paramKey);
            return null;
        }
    }

    /**
     * //TODO
     * @desc 初始化统一上下文是否开启
     * @return
     */
    protected boolean openInitTrace(){
        return true;
    }
}

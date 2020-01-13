package com.talkee.trace.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.rpc.*;
import com.talkee.trace.base.AbstractTraceFilter;
import com.talkee.trace.constants.TraceConstants;
import com.talkee.trace.enums.InvokeSideTypeEnum;
import com.talkee.trace.model.TraceContext;
import com.talkee.trace.util.RpcContextAttachmentUtil;
import com.talkee.trace.util.TraceInitUtil;
import com.talkee.trace.util.TraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = { Constants.PROVIDER })
public class DubboProviderTraceFilter extends AbstractTraceFilter {

    private static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.RPC_SERVER_DIGEST_LOG);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();
        //获取remoteHost
        String remoteHost = RpcContext.getContext().getRemoteHost();
        String clientName = null;
        String serverName = null;
        try {
            serverName = getInvokerUrlParam(invoker, Constants.APPLICATION_KEY);
            clientName = getRpcContextAttachmentParam(TraceConstants.CONSUMER_APPLICATION_KEY);
            TraceContext traceContext = RpcContextAttachmentUtil.parseAttachments(RpcContext.getContext().getAttachments());
            //如果为空则新生成一个
            traceContext = (traceContext == null && openInitTrace()) ? TraceInitUtil.generateTraceContext() : traceContext;
            //设置统一上下文
            TraceUtil.setTraceContext(traceContext);
            Result result =  invoker.invoke(invocation);
            logRpcDigest(invoker, invocation, remoteHost, NetUtils.getLocalHost(), start, -1, digestLogger,
                    InvokeSideTypeEnum.PROVIDER, clientName, serverName);
            return result;
        } catch (RpcException e) {
            int resultCode = (e != null) ? e.getCode() : 0;
            logRpcDigest(invoker, invocation, remoteHost, NetUtils.getLocalHost(), start, resultCode, digestLogger,
                    InvokeSideTypeEnum.PROVIDER, clientName, serverName);
            throw e;
        } finally {
            TraceUtil.clearTraceContext();
        }
    }
}

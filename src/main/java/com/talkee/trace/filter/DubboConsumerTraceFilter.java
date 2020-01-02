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
import java.util.Map;

@Activate(group = { Constants.CONSUMER },order = -99)
public class DubboConsumerTraceFilter extends AbstractTraceFilter {

    private static final Logger digestLogger = LoggerFactory.getLogger(TraceConstants.RPC_CLIENT_DIGEST_LOG);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();
        //服务端IP
        String remoteHost = null;
        //客户端应用名称
        String cApplication = null;
        TraceContext traceContext = TraceUtil.getTraceContext();
        boolean isTraceEmpty = (traceContext == null);
        try {
            //如果为空则新生成一个
            traceContext = (isTraceEmpty && openInitTrace()) ? TraceInitUtil.generateTraceContext() : traceContext;
            //设置统一上下文
            TraceUtil.setTraceContext(traceContext);
            Map<String, String> attachments = RpcContextAttachmentUtil.assemAttachments(traceContext);
            //消费端应用名称
            cApplication = getInvokerUrlParam(invoker, Constants.APPLICATION_KEY);
            attachments.put(TraceConstants.CONSUMER_APPLICATION_KEY, cApplication);
            for (Map.Entry<String, String> entry : attachments.entrySet()){
                RpcContext.getContext().setAttachment(entry.getKey(), entry.getValue());
            }
            Result result =  invoker.invoke(invocation);
            //获取remoteHost
            remoteHost = RpcContext.getContext().getRemoteHost();
            logRpcDigest(invoker, invocation, NetUtils.getLocalHost(), remoteHost, start, -1, digestLogger,
                    InvokeSideTypeEnum.CONSUMER, cApplication, null);
            return result;
        } catch (RpcException e) {
            int resultCode = (e != null) ? e.getCode() : 0;
            logRpcDigest(invoker, invocation, NetUtils.getLocalHost(), remoteHost, start, resultCode, digestLogger,
                    InvokeSideTypeEnum.CONSUMER, cApplication, null);
            throw e;
        } finally {
            if (isTraceEmpty) {
                //清理统一上下文
                TraceUtil.clearTraceContext();
            }
        }
    }
}

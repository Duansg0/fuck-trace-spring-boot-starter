package com.talkee.trace.constants;

public class TraceConstants {

    /** traceId的key */
    public static final String TRACE_ID_KEY = "traceId";

    /** 摘要日志中的空值占位符  */
    public static final String EMPTY_DIGEST_VALUE = "-";

    /** 摘要日志中的左括号  */
    public static final String LEFT_DIGEST_CHAR = "(";

    /** 摘要日志中的右括号  */
    public static final String RIGHT_DIGEST_CHAR = ")";

    /** 摘要日志中的指向箭头  */
    public static final String POINT_DIGEST_CHAR = " -> ";

    /** MVC摘要日志 */
    public static final String MVC_DIGEST_LOG = "PV_DIGEST";

    /** mvc框架名称 */
    public static final String MVC_FRAM_NAME = "springmvc";

    /** 错误摘要日志  */
    public static final String ERROR_DIGEST_LOG = "ERROR_DIGEST";

    /** appName的key */
    public static final String APP_NAME_KEY = "appName";

    /** dubbo.appName的key */
    public static final String DUBBO_APP_NAME_KEY = "dubbo.appName";

    /** 压测的key */
    public static final String LOAD_TEST_KEY = "trace.loadTest";

    /** HTTP头部压测的key */
    public static final String HTTP_LOAD_TEST_KEY = "trace-loadTest";

    /** 压测的开关 */
    public static final String LOAD_TEST_SWITCH = "loadTest.switch";

    /** 摘要日志开关 */
    public static final String DIGEST_LOG_SWITCH = "digestLog.switch";

    /** RpcAttachment前缀 */
    public static final String RPC_ATTACHMENT_PREFIX = "dubboTrace.";

    /** RPC客户端摘要日志 */
    public static final String RPC_CLIENT_DIGEST_LOG = "RPC_CLIENT_DIGEST";

    /** RPC服务端摘要日志 */
    public static final String RPC_SERVER_DIGEST_LOG = "RPC_SERVER_DIGEST";

    /** 消费端应用名称 */
    public static final String CONSUMER_APPLICATION_KEY = "consumer.application";

    /** 提供者端应用名称 */
    public static final String PROVIDER_APPLICATION_KEY = "provider.application";
}

package com.talkee.trace.config;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.base.AbstractConfiguration;
import com.talkee.trace.base.InterceptorBuilder;
import com.talkee.trace.interceptor.DaoDigestInterceptor;
import com.talkee.trace.interceptor.SpringPvDigestInterceptor;
import com.talkee.trace.listern.RefreshConfigListern;
import com.talkee.trace.model.InterceptorInitInfoModel;
import com.talkee.trace.model.RefreshConfigModel;
import feign.RequestInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Duansg
 * @desc Global auto configuration
 * @date 2019-12-31 19:18:12
 */
@Import({TraceContainerConfiguration.class})
@EnableConfigurationProperties({TraceProperties.class})
public class TraceAutoConfiguration extends AbstractConfiguration {

    /**
     * @desc Register the request interceptor for the Dao layer.
     * @param traceProperties
     * @return
     */
    @Bean(name = "daoDigestInterceptor")
    @ConditionalOnProperty(prefix="spring.boot.trace",name = "traceSwitch", havingValue = "true")
    public DefaultPointcutAdvisor defaultPointcutAdvisorDao(TraceProperties traceProperties) {
        super.init(traceProperties);
        return InterceptorBuilder.build(new DaoDigestInterceptor(), new InterceptorInitInfoModel.Builder().buildAppName(traceProperties.getAppName()).buildExecution(traceProperties.getTraceDaoExecution()).build());
    }

    /**
     * @desc Register the Controller's request interceptor.
     * @param traceProperties
     * @return
     */
    @Bean(name = "springPvDigestInterceptor")
    @ConditionalOnProperty(prefix="spring.boot.trace",name = "traceSwitch", havingValue = "true")
    public DefaultPointcutAdvisor defaultPointcutAdvisorPv(TraceProperties traceProperties) {
       super.init(traceProperties);
       return InterceptorBuilder.build(new SpringPvDigestInterceptor(), new InterceptorInitInfoModel.Builder().buildAppName(traceProperties.getAppName()).buildExecution(traceProperties.getTracePvExecution()).build());
    }

    /**
     * @desc Register Feign's request interceptor.
     * @param traceProperties
     * @return
     */
    @Bean(name = "feignDigestConfiguration")
    @ConditionalOnProperty(prefix="spring.boot.trace",name = "traceSwitch.Feign", havingValue = "true")
    public RequestInterceptor requestInterceptor(TraceProperties traceProperties) {
        return new FeignDigestConfiguration(traceProperties.getAppName());
    }

    /**
     * @desv Refresh config listern .
     * @return
     */
    @Bean
    public ApplicationListener<RefreshConfigModel> refreshConfigListern(){
        return new RefreshConfigListern();
    }

}

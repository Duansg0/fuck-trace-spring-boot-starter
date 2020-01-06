package com.talkee.trace.config;

import com.talkee.trace.TraceProperties;
import com.talkee.trace.base.InterceptorBuilder;
import com.talkee.trace.interceptor.DaoDigestInterceptor;
import com.talkee.trace.interceptor.FeginInterceptor;
import com.talkee.trace.interceptor.SpringMvcDigestInterceptor;
import com.talkee.trace.model.InterceptorInitInfoModel;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Duansg
 * @desc 全局自动配置类
 * @date 2019-12-31 17:18:12
 */
@Import({TraceContainerConfiguration.class})
@EnableConfigurationProperties({TraceProperties.class})
public class TraceAutoConfiguration {


    /**
     * @desc Dao层拦截器自动注册
     * @param traceProperties
     * @return
     */
    @Bean(name = "daoDigestInterceptor")
    @ConditionalOnProperty(prefix="spring.boot.trace",name = "digestDaoLogOpen", havingValue = "true")
    public DefaultPointcutAdvisor defaultPointcutAdvisorDao(TraceProperties traceProperties) {
        return InterceptorBuilder.build(new DaoDigestInterceptor(), new InterceptorInitInfoModel.Builder().buildAll(traceProperties.getTraceDaoExecution(), traceProperties.getAppName()).build());
    }

    /**
     * @desc
     * @param traceProperties
     * @return
     */
    @Bean(name = "springMvcDigestInterceptor")
    @ConditionalOnProperty(prefix="spring.boot.trace",name = "digestPvLogOpen", havingValue = "true")
    public DefaultPointcutAdvisor defaultPointcutAdvisorMvc(TraceProperties traceProperties) {
        return InterceptorBuilder.build(new SpringMvcDigestInterceptor(), new InterceptorInitInfoModel.Builder().buildAll(traceProperties.getTracePvExecution(), traceProperties.getAppName()).build());
    }

    /**
     *
     * @param traceProperties
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix="spring.boot.trace",name = "digestFeginLogOpen", havingValue = "true")
    public DefaultPointcutAdvisor defaultPointcutAdvisorMvcFegin(TraceProperties traceProperties) {
        return InterceptorBuilder.build(new FeginInterceptor(), new InterceptorInitInfoModel.Builder().buildAll(traceProperties.getTraceFeignExecution(), traceProperties.getAppName()).build());
    }

}

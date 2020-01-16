package com.talkee.trace.annotation;

import java.lang.annotation.*;

/**
 * @author Duansg
 * @desc Custom interceptor annotation.
 * @see com.talkee.trace.config.TraceContainerConfiguration
 * @date 2019-12-27 14:57:12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TraceCustom {

}

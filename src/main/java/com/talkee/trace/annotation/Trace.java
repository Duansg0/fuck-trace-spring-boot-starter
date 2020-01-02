package com.talkee.trace.annotation;

import java.lang.annotation.*;

/**
 * @author Duansg
 * @desc 单类使用,只会扫描这一个类
 * @date 2019-12-27 14:57:12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trace {

}

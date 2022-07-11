package com.digov.api.config.redis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * 用于防止请求 重复提交
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface RepeatSubmit {

    /**
     * 防止重复提交的间隔 毫秒
     * @return
     */
    long minIntervalMS() default 1000 * 1;

}

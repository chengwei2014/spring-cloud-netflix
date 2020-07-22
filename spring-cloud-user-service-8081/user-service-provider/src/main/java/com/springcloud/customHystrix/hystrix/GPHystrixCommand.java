package com.springcloud.customHystrix.hystrix;

import java.lang.annotation.*;

/**
 * @GPHystrixCommand
 * 2020-07-22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPHystrixCommand {
    /**
     * 默认超时时间
     * */
    int timeout() default 1000;

    /**
     * 回退方法
     * */
    String fallback() default "";
}

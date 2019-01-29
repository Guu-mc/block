package com.mc.block.redis.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cacheable {
    String value() default "";
    /**
     * el 表达式
     * @return
     */
    String key() default "";
    long timeout() default 432000000;
}

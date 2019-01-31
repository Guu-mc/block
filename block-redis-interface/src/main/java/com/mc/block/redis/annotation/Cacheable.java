package com.mc.block.redis.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cacheable {

    /**
     *
     * @return cacheName 默认类全路径名
     */
    String value() default "";

    /**
     *
     * @return keyGroup 默认方法名
     */
    String keyGroup() default "";

    /**
     *
     * @return el 表达式 默认全参数
     */
    String key() default "";

    /**
     *
     * @return timeout 过期时间 默认5天
     */
    long timeout() default 432000000;
}

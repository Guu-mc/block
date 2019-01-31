package com.mc.block.redis.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheEvict {

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
     * @return allEntries 是否全清空（cacheName相同）， 否 （cacheName相同，keyGroup相同，key相同）
     */
    boolean allEntries() default true;
}
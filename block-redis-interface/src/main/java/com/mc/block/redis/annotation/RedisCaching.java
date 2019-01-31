package com.mc.block.redis.annotation;

import com.mc.block.redis.aop.CachingAop;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CachingAop.class})
public @interface RedisCaching {

}

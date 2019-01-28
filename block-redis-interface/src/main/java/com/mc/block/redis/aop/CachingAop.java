package com.mc.block.redis.aop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mc.block.redis.annotation.CacheEvict;
import com.mc.block.redis.annotation.CachePut;
import com.mc.block.redis.annotation.Cacheable;
import com.mc.block.redis.interfaces.IRedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Set;

@Aspect
@Configuration
public class CachingAop {
    @Reference
    private IRedisService redisService;

    @Around("@annotation(cacheable)")
    public Object cacheable(ProceedingJoinPoint po, Cacheable cacheable) throws Throwable {
        Object obj;
        String key = getKey(cacheable.key(), po.toLongString(), po.getArgs());
        if(redisService.hasKey(key)){
            obj = redisService.get(key);
        } else {
            obj = po.proceed();
            redisService.set(key, obj, cacheable.timeout());
        }
        return obj;
    }

    @AfterReturning(value = "@annotation(cachePut)", returning="retVal")
    public Object cachePut(JoinPoint point, Object retVal, CachePut cachePut) {
        String key = getKey(cachePut.key(), point.toLongString(), point.getArgs());
        redisService.set(key, retVal, cachePut.timeout());
        return retVal;
    }

    @Before("@annotation(cacheEvict)")
    public void cacheEvict(JoinPoint point, CacheEvict cacheEvict) {
        String frontKey = cacheEvict.key();
        if(frontKey.isEmpty()){
            frontKey = getFrontKey(point.toLongString());
        }
        if(cacheEvict.allEntries()){
            Set<String> keys = redisService.keys(frontKey + "::*");
            for (String key : keys) {
                redisService.del(key);
            }
        } else {
            String endKey = getEndKey(point.getArgs());
            String key = getKey(frontKey, endKey);
            redisService.del(key);
        }
    }

    private String getEndKey(Object[] args){
        return String.valueOf(Arrays.hashCode(args));
    }

    private String getFrontKey(String longString){
        return String.valueOf(longString.hashCode());
    }

    private String getKey(String frontKey, String endKey){
        return frontKey+"::"+endKey;
    }

    private String getKey(String key, String longString, Object[] args){
        String frontKey = key;
        String endKey = getEndKey(args);
        if(frontKey.isEmpty()){
            frontKey = getFrontKey(longString);
        }
        return getKey(frontKey, endKey);
    }
}